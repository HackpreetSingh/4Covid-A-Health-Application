package com.hempreet.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.hempreet.bean.Doctor;

public interface DoctorService {
	public List<Doctor> fetchDoctors() throws ClassNotFoundException, SQLException;

	public Doctor getDoctorById(int docId) throws ClassNotFoundException, SQLException;

	public Doctor fetchDoctorById(int docId) throws ClassNotFoundException, SQLException;

	public boolean changeDoctorAppointment(int docId, LocalDateTime from, LocalDateTime to)
			throws ClassNotFoundException, SQLException;

	public boolean deleteDoctorAppointments(int docId) throws ClassNotFoundException, SQLException;

}
