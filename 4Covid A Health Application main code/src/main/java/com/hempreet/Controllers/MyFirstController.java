package com.hempreet.Controllers;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hempreet.bean.Appointments;
import com.hempreet.bean.Doctor;
import com.hempreet.bean.Patient;
import com.hempreet.service.AppointmentsServiceImpl;
import com.hempreet.service.DoctorServiceImpl;
import com.hempreet.service.PatientsServiceImpl;

@Controller
//@SessionAttributes({"patId"})
public class MyFirstController {

	@Autowired
	DoctorServiceImpl doctorServiceImpl;
	@Autowired
	PatientsServiceImpl patientServiceImpl;
	@Autowired
	AppointmentsServiceImpl appointmentsServiceImpl;

	@RequestMapping("/")
	public ModelAndView welcomePageController() {
		return new ModelAndView("index");
	}

	@RequestMapping("/doctorHomePage")
	public ModelAndView doctorHomePageController() {
		return new ModelAndView("doctorHomePage");
	}

	@RequestMapping("/doctorsList")
	public ModelAndView doctorsListController() {
		Collection<Doctor> doctors = null;
		try {
			doctors = doctorServiceImpl.fetchDoctors();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("doctorsList", "DOC", doctors);
	}

	@RequestMapping("/doctorsSchedule")
	public ModelAndView searchEmpPageController() {
		return new ModelAndView("searchDocById");
	}
/*
 * <form action="./searchDocById" method="post">
	Enter Doctor ID  :<input type="text" name="docId"> <br><br>
	<input type="submit" value="Search">
</form>
 */
	@RequestMapping("/searchDocById")
	public ModelAndView searchEmpController(@RequestParam("item") String Id) {
		int docId=Integer.valueOf(Id);
		Doctor doctor = null;
		try {
			doctor = doctorServiceImpl.fetchDoctorById(docId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("displaySchedule", "doctor", doctor);
	}

	@RequestMapping("/changeSchedule")
	public ModelAndView updateDoctorScheduleController() {
		return new ModelAndView("updateDocPage");
	}

	@RequestMapping("/updateDocPage")
	public ModelAndView changeDoctorSchedule(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("docId"));
		String date = (request.getParameter("date"));
		String startingTime = (request.getParameter("from"));
		String stoppingTime = (request.getParameter("to"));

		LocalDateTime from = LocalDateTime.parse(date + "T" + startingTime);
		LocalDateTime to = LocalDateTime.parse(date + "T" + stoppingTime);

		String message = "Hello";
		try {
			if (doctorServiceImpl.changeDoctorAppointment(id, from, to))
				message = "Doctor Schedule Updated Successfully";
			else
				message = "Doctor Schedule not Updated";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = "Doctor Schedule not Updated";
		}

		return new ModelAndView("output", "message", message);
	}

	// *****************************************************************************************************//

	@RequestMapping("/PatientHomePage")
	public ModelAndView insertEmployee() {
		return new ModelAndView("PatientHomePage");
	}

	@RequestMapping("/alreadyPatientHomePage")
	public ModelAndView deleteEmpPageController() {
		ModelAndView modelAndView = new ModelAndView("alreadyPatientHomePage", "pat", new Patient());
 
		return modelAndView;
//		return new ModelAndView("alreadyPatientHomePage");
	}

	@RequestMapping("/newPatientHomePage")
	public ModelAndView newPatientHomePage() {
		return new ModelAndView("newPatientAddDetails");
	}

	@RequestMapping("/savePatient")
	public ModelAndView newPatient(HttpServletRequest request) {
		String name = request.getParameter("name");
		String medicalHistory = request.getParameter("medicalHistory");
		String phone = request.getParameter("phone");
		int patId = 0;
		String message = null;

		try {
			patientServiceImpl.addPatient(name, medicalHistory, phone);
			patId = patientServiceImpl.fetchPatientId(phone);
		} catch (SQLException e) {
			patId = -1;
		}
		if (patId == -1)
			message = "New Patient Details Saving Failed!!";
		else {
			message = "Your Details Saved Successfully!! Your Patient Id is: " + patId;
		}

		return new ModelAndView("output", "message", message);
	}
	/*
	 * PATIENTS SET/VIEW/DELETE APPOINTMENTS
	 ***************************************************************************************************/

	@RequestMapping("/verify")
	public ModelAndView patientsAppointmentsPage(/* #for object:patient or doctor */@ModelAttribute("pat") Patient p,
			/* @RequestParam("patId") int patId, */ HttpSession session) {
		int patId = p.getPatId();
		session.setAttribute("patId", patId);
		try {
			if (patientServiceImpl.existingPatient(patId)) {
				return new ModelAndView("getSetAppointments");
			} else
				return new ModelAndView("output", "message", "You entered an invalid id!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * <spring:form action="./verify" modelAttribute="pat"> Enter Patient Id :
	 * <spring:input path="patId"/><br><br> <input type="submit" value="Login">
	 * </spring:form> 
	 * ****************************************************************************
	 * <form action="./verify"> Enter Patient Id : <input type="text"
	 * name="patId"/><br><br> <input type="submit" value="Login"> </form>
	 **/
	
	@RequestMapping("/viewAppointments")
	public ModelAndView displayAppointments(HttpSession session) {
		int patId = (int) session.getAttribute("patId");
		Collection<Appointments> appointments = null;
		try {
			appointments = appointmentsServiceImpl.getAppointmentsForPatient(patId);
			if (appointments.isEmpty() || appointments == null)
				return new ModelAndView("output", "message", "No Appointments as of now!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("viewAppointments", "appointments", appointments);
	}

	@RequestMapping("/getSetAppointments")
	public ModelAndView appointmentsInfo() {
		return new ModelAndView("getSetAppointments");
	}

	@ModelAttribute("doctors")
	public List<Doctor> getDoctors(){
	List<Doctor> doctors=null;
		try {
		doctors = doctorServiceImpl.fetchDoctors();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return doctors;
	}
	
	@RequestMapping("/bookAppointment")
	public ModelAndView bookAppointment() {
		ModelAndView modelAndView=new ModelAndView();
		List<Doctor> doctors = null;
		doctors = getDoctors();
		modelAndView.addObject("doctors", doctors);
		modelAndView.addObject("doc", new Doctor());
		modelAndView.setViewName("bookAppointmentHomePage");
		return modelAndView;
	}

	@ModelAttribute("docIds")
	public List<Integer> getAllIds(){
		try {
			return doctorServiceImpl.fetchDoctors().stream()
					.map(Doctor::getDocId).collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/appointmentTimeSelector")
	public ModelAndView appointmentTimeSelector(@ModelAttribute("doc") Doctor doctor,HttpSession session) {
		int docId = doctor.getDocId();
		session.setAttribute("docId", docId);
		List<Doctor> doctors;
		try {
			doctors = getDoctors();
			LocalDateTime from = null, to = null;
			
			List<Doctor> doct=(doctors.parallelStream()
		    .filter(e -> e.getDocId() == docId)).collect(Collectors.toList());
			
			from=doct.get(0).getFrom();   to=doct.get(0).getTo();
			
			Map<LocalDateTime, Boolean> timeSlots = patientServiceImpl.getScheduleforPatient(docId,from,to);
			List<LocalDateTime> list=new ArrayList<LocalDateTime>();
			for(LocalDateTime i:timeSlots.keySet())
				if(timeSlots.get(i)==true) list.add(i);

			return new ModelAndView("appointmentSlotSelector","slots",list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ModelAndView();
	}
/*
 * <!--
<spring:form action="./finaliseSlot" method="post" modelAttribute="appoint">
	Select Suitable Appointment Slot : <spring:select path="timeScheduled">
		<spring:options items="${slots}"/>
		<br><br> <input type="submit" value="Submit">
	</spring:select><br><br>
</spring:form>  -->
 * */
	@RequestMapping("/finaliseSlot")/*@ModelAttribute("appoint") Appointments appoint*/
	public ModelAndView finaliseSlot(@RequestParam("item") String localDateTime,HttpSession session) {
//		LocalDateTime slot=appoint.getTimeScheduled();
		LocalDateTime timeScheduled=LocalDateTime.parse(localDateTime);
		int docId=(int)session.getAttribute("docId");
		int patId=(int)session.getAttribute("patId");
		try {
			appointmentsServiceImpl.bookAppointment(docId, patId, timeScheduled);
			return new ModelAndView("output","message","Appointment Successfully Booked!!");
		}catch (ClassNotFoundException|SQLException e) {
			return new ModelAndView("output","message","Internal Error!!");
		}
	}
	
	@RequestMapping("/deleteAppointment")
	public ModelAndView deleteAppointments(HttpSession session) {
		int patId = (int) session.getAttribute("patId");
		List<Appointments> appointments = null;
		try {
			appointments = appointmentsServiceImpl.getAppointmentsForPatient(patId).stream()
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("deleteAppointmentHomePage", "appointments", appointments);
	}

	@RequestMapping("/delete")
	public ModelAndView delete(HttpSession session, HttpServletRequest request) {
		int appointId = Integer.parseInt(request.getParameter("appointId"));
		String msg = "";
		int patId = (int) session.getAttribute("patId");
		List<Appointments> appointments;
		try {
			appointments = appointmentsServiceImpl.getAppointmentsForPatient(patId).stream()
					.collect(Collectors.toList());
			if (appointments.parallelStream().anyMatch(i -> (i.getAppointId() == appointId && i.getPatId() == patId))) {
				appointmentsServiceImpl.deleteAppointment(appointId);
				msg = "Appointment deleted successfully!";
			} else
				msg = "Appointment deletion failed!!Enter a valid appointment Id!";
		} catch (SQLException e) {
			msg = "Appointment deletion failed!Technical Error!";
		}
		return new ModelAndView("output", "message", msg);
	}
}