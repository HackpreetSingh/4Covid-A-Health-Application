package com.hempreet.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.hempreet.bean.Doctor;

public interface DoctorDao {
	public List<Doctor> fetchDoctor() throws SQLException, ClassNotFoundException;

	public boolean changeDoctorAppointment(int docId, LocalDateTime from, LocalDateTime to)
			throws ClassNotFoundException, SQLException;

	public boolean deleteDoctorAppointments(int docId) throws ClassNotFoundException, SQLException;

}
