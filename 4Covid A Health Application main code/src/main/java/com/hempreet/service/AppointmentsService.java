package com.hempreet.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.hempreet.bean.Appointments;

public interface AppointmentsService {
	public void bookAppointment(int docId, int patId, LocalDateTime timeScheduled)
			throws ClassNotFoundException, SQLException;// called inside addPatient and existingPatient

	List<Appointments> getAppointmentsForPatient(int patId) throws ClassNotFoundException, SQLException;

	public void deleteAppointment(int appointId) throws ClassNotFoundException, SQLException;

}
