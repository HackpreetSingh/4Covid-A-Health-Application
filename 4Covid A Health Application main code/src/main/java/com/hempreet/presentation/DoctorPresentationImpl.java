package com.hempreet.presentation;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hempreet.bean.Doctor;
import com.hempreet.service.DoctorServiceImpl;

@Component
public class DoctorPresentationImpl implements DoctorPresentation {

	@Autowired
	private DoctorServiceImpl doctorSer = new DoctorServiceImpl();

	@Override
	public void displayMenu() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("\n1: Doctors DataBase\n2: Get Doctor Schedule\n3: Change Doctor Schedule\n4: Exit");
			int ch = s.nextInt();
			if (ch == 1)
				FetchDoctors();
			else if (ch == 2)
				doctorSchedule();
			else if (ch == 3)
				changeDoctorSchedule();
			else if (ch == 4)
				return;
			else
				System.out.println("Wrong Choice entered!!");
		}
	}

	@Override
	public void FetchDoctors() {
		List<Doctor> doctors = null;
		try {
			doctors = doctorSer.fetchDoctors();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!doctors.isEmpty()) {
			System.out.printf("%-20s%-20s%-20s\n", "Doctor Name", "Speciality", "Phone");

			for (Doctor d : doctors) {
				System.out.printf("%-20s%-20s%-20s\n", d.getName(), d.getSpeciality(), d.getPhone());
			}
		} else {
			System.out.println("No doctors!!");
		}

	}

	@Override
	public void doctorSchedule() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("Enter doctor Id: ");
		Doctor doctor;
		try {
			doctor = doctorSer.getDoctorById(s.nextInt());
			if (doctor == (null))
				System.out.println("No such Doctor!");
			else {
				System.out.printf("\n%-20s%-20s%-20s%-20s%-30s%-30s\n", "Doctor Id", "Doctor Name", "Speciality",
						"Phone", "Start Time", "Stop Time");
				System.out.printf("\n%-20s%-20s%-20s%-20s%-30s%-30s\n", doctor.getDocId(), doctor.getName(),
						doctor.getSpeciality(), doctor.getPhone(),
						Timestamp.valueOf(doctor.getFrom()),Timestamp.valueOf(doctor.getTo()));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void changeDoctorSchedule() {

		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		Doctor doctor = null;
		int docId = 0;
		System.out.println("Enter your Doctor Id: ");
		docId = s.nextInt();
		try {
			doctor = doctorSer.fetchDoctorById(docId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (doctor == (null)) {
			System.out.println("No such Doctor!");
			return;
		} 
		try {
			System.out.println("Present Schedule is from: " 
		+Timestamp.valueOf(doctor.getFrom()) + "   to   " +Timestamp.valueOf(doctor.getTo()));
			while (true) {
				System.out.println(
						"________________________________________________________________________________________________");

				System.err.println("On proceeding, All your Appointments will be cancelled!!\nEnter y/n to continue: ");
				System.out.println(
						"________________________________________________________________________________________________");
				char choice = s.next().charAt(0);
				if (choice == 'n')
					return;
				else if (choice == 'y')
					break;
				else
					System.out.println("Wrong Choice!");
			}

			s.nextLine();
			System.out.println("Enter your new Schedule day in the format: YYYY-MM-DD");
			String day = s.nextLine();
			System.out.println("Enter your new Schedule start time in the format: HH:MM");
			String time = s.nextLine();
			String from = day + "T" + time;
			System.out.println("Enter your new Schedule stop time in the format: HH:MM");
			time = s.nextLine();
			String to = day + "T" + time;
			doctorSer.changeDoctorAppointment(docId, LocalDateTime.parse(from), LocalDateTime.parse(to));
			doctorSer.deleteDoctorAppointments(docId);
			System.out.println("Appointment schedule updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
