package com.hempreet.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.hempreet.bean.Appointments;

public interface AppointmentsDao {
	public List<Appointments> fetchAppointments(int docId)throws SQLException,ClassNotFoundException;

	public void setAppointments(int docId, int patId,int appointId,LocalDateTime t)throws SQLException,ClassNotFoundException;
	
	public List<Appointments> getAppointmentByPatId(int patId) throws ClassNotFoundException,SQLException;
	
	public void deleteAppointment(int appointId)throws SQLException,ClassNotFoundException;
	

}
