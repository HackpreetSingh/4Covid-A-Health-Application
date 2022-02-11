package com.hempreet.service;

import java.sql.SQLException;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hempreet.bean.Appointments;
import com.hempreet.bean.Doctor;
import com.hempreet.bean.Patient;
import com.hempreet.dao.AppointmentsDaoImpl;
import com.hempreet.dao.DoctorDaoImpl;
import com.hempreet.dao.PatientsDaoImpl;
@Service
public class PatientsServiceImpl implements PatientsService {
	@Autowired
	private PatientsDaoImpl dao;
	@Autowired
	private AppointmentsDaoImpl 	appointmentsDao;
	@Autowired
	private DoctorDaoImpl 	doctorDao;

	@Override
	public Map<LocalDateTime, Boolean> getScheduleforPatient(int docId,LocalDateTime from,LocalDateTime to) throws SQLException {
		Map<LocalDateTime, Boolean> schedule = new TreeMap<LocalDateTime, Boolean>();

		List<Appointments> appointments = appointmentsDao.fetchAppointments(docId);
	
		for (LocalDateTime i = from; i.isBefore(to); i=i.plusMinutes(20)) {
			schedule.put(i, true);
			for (Appointments a : appointments)
			{	
				if (i.isEqual(a.getTimeScheduled())) {
					schedule.put(i, false);
					break;
				} 
				if(schedule.get(i)== null)	schedule.put(i, true);
			}	

		}
		return schedule;

	}

	@Override
	public Map<Integer,List<LocalDateTime>> getTimeSlotforPatient()throws SQLException  //int docId
	{
		Map<Integer,List<LocalDateTime>> timeSlots=new TreeMap<Integer,List<LocalDateTime>>();
		List<Doctor> doctor = doctorDao.fetchDoctor();
		List<LocalDateTime> times;
		for (Doctor d : doctor) {
			times=new ArrayList<LocalDateTime>();
			times.add(d.getFrom());
			times.add(d.getTo());
			
				timeSlots.put(d.getDocId(),times);
		}
			
		return timeSlots;
	}


	@Override
	public void addPatient(String name,String medicalHistory,String phone) throws SQLException {
		
		Patient p=new Patient(0,name,medicalHistory,phone);
		dao.addRecordPatient(p);
	}

	@Override
	public boolean existingPatient(int patId) throws SQLException {
		List<Patient> patients=dao.fetchRecordsPatient();
		for(Patient p:patients)
			if(p.getPatId()==patId)
				return true;
		return false;
	}

	@Override
	public int fetchPatientId(String phone) throws SQLException {
		List<Patient> patients=dao.fetchRecordsPatient();
		for(Patient p:patients)
			if(p.getPhone().equals(phone)) {
//				System.out.println("Success: "+p.getPatId());
				return p.getPatId();
			}
//		System.out.println("Fail is service layer");
		return 0;
	}

	
		
	
}