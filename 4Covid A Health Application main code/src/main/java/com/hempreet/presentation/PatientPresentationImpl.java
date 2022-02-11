package com.hempreet.presentation;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hempreet.bean.Appointments;
import com.hempreet.bean.Doctor;
import com.hempreet.service.AppointmentsServiceImpl;
import com.hempreet.service.DoctorServiceImpl;
import com.hempreet.service.PatientsServiceImpl;

import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class PatientPresentationImpl implements PatientPresentation {

	@Autowired
	private PatientsServiceImpl patientSer = new PatientsServiceImpl();
	@Autowired
	private AppointmentsServiceImpl appointmentsSer = new AppointmentsServiceImpl();
	@Autowired
	private DoctorServiceImpl doctorSer = new DoctorServiceImpl();

	@Override
	public void displayMenu() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		while (true) {
			System.out.println("\n1: Add new Patient\n2: Already a Patient.\n3: Exit\n");
			int ch = s.nextInt();
			if (ch == 1)
				addPatient();
			else if (ch == 2) {
				existingPatientCheck();
			} else if (ch == 3) {

				{

					return;
				}
			} else
				System.out.println("Wrong Choice entered!!");
		}

	}

	@Override
	public void bookAppointments(int patId) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		try {
			List<Doctor> doctors = doctorSer.fetchDoctors();
			System.out.printf("%-20s%-20s%-20s%-20s%-30s%-30s\n", "Index", "Doctor Id", "Doctor Name", "Speciality",
					"From", "To");
			for (Doctor d : doctors)
				System.out.printf("%-20s%-20s%-20s%-20s%-30s%-30s\n", doctors.indexOf(d) + 1, d.getDocId(), d.getName(),
						d.getSpeciality(), Timestamp.valueOf(d.getFrom()), Timestamp.valueOf(d.getTo()));
			System.out.println("Enter Doctor id for which time slot for you is convenient");
			int docId = s.nextInt();
			LocalDateTime from = null, to = null, timeScheduled = null;
			for (Doctor d : doctors)
				if (d.getDocId() == docId) {
					from = d.getFrom();
					to = d.getTo();
				}
			if (from == null) {
				System.out.println("Incorrect Doctor Id entered");
				return;
			}
			Map<LocalDateTime, Boolean> timeSchedules = patientSer.getScheduleforPatient(docId, from, to);

			System.out.printf("%-20s%-30s%-30s\n", "Index", "Time Slots", "Avalability");
			int k = 1;
			String bool;
			for (LocalDateTime i : timeSchedules.keySet()) {
				bool = (timeSchedules.get(i) == true) ? "Available" : "Not Available";
				System.out.printf("%-20s%-30s%-30s\n", k, Timestamp.valueOf(i), bool);
				k++;
			}
			k = 0;
			int index = s.nextInt();
			if (index > timeSchedules.size()) {
				System.out.println("Wrong index entered!!");
				return;
			}
			for (LocalDateTime i : timeSchedules.keySet()) {
				k++;
				if (k == index) {
					timeScheduled = i;
					break;
				}
			}
			if (timeSchedules.get(timeScheduled)) {
				appointmentsSer.bookAppointment(docId, patId, timeScheduled);
				System.out.println("Appointment booked!!");

			} else
				System.out.println("Appointment already booked for that time!!\nSelect appropriate Time Slot");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addPatient() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		System.out.println("Enter name: ");
		String nm = s.nextLine();
		System.out.println("Enter any medical history: ");
		String medicalHistory = s.nextLine();
		System.out.println("Enter your phone number: ");
		String phone = s.nextLine();
		try {
			patientSer.addPatient(nm, medicalHistory, phone);
			int patId = patientSer.fetchPatientId(phone);
			System.out.println("Your Patient Id is: " + patId);

			System.out.println("\n1: Book Appointment\n2:Main Menu\n");
			if (s.nextInt() == 1)
				bookAppointments(patId);
			else
				return;

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public void existingPatientCheck() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		System.out.println("Enter your patient id: ");
		try {
			int pat = s.nextInt();
			if (patientSer.existingPatient(pat) == true) {

				existingPatientMenu(pat);
			} else
				System.out.println("No such patient Exists!!");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void existingPatientMenu(int patId) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("\n1: Book Appointment\n2: View Appointments\n3: Cancel Apointments\n4: Exit\n");
			int ch = s.nextInt();
			if (ch == 1)
				bookAppointments(patId);
			else if (ch == 2) {
				viewAppointments(patId);
			} else if (ch == 3)
				cancelAppointment(patId);
			else if (ch == 4) {

				return;
			}

		}
	}

	@Override
	public void viewAppointments(int patId) {
		try {
			List<Appointments> appointments = appointmentsSer.getAppointmentsForPatient(patId);
			if (appointments.isEmpty()) {
				System.out.println("No Appointments as of now!!");
				return;
			} else {
				System.out.printf("%-20s%-20s%-20s%-20s\n", "Doctor Id", "Patient Id", "Appointment Id",
						"Time Scheduled");
				for (Appointments a : appointments)
					System.out.printf("%-20s%-20s%-20s%-20s\n", a.getDocId(), a.getPatId(), a.getAppointId(),
							Timestamp.valueOf(a.getTimeScheduled()));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void cancelAppointment(int patId) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.err.println("Note: The deleted appointment could not be undone");
		try {
			List<Appointments> appointments = appointmentsSer.getAppointmentsForPatient(patId);
			if (!appointments.isEmpty()) {
				System.out.printf("%-20s%-20s%-20s%-20s\n", "Doctor Id", "Patient Id", "Appointment Id",
						"Time Scheduled");
				for (Appointments a : appointments)
					System.out.printf("%-20s%-20s%-20s%-20s\n", a.getDocId(), a.getPatId(), a.getAppointId(),
							Timestamp.valueOf(a.getTimeScheduled()));
				System.out.println("Enter the apointment Id for which time slot you want to delete the appointment: ");
				int appointId = s.nextInt();
				if (appointments.parallelStream()
						.anyMatch(i -> (i.getAppointId() == appointId && i.getPatId() == patId))) {
					appointmentsSer.deleteAppointment(appointId);
					System.out.println("Appointment Successfully Deleted!!");
				} else {
					System.out.println("Wrong appointment id entered!!");
					return;
				}
			} else
				System.out.println("No appointmentd Booked!!");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
