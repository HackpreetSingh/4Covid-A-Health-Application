package com.hempreet.service;

import java.sql.SQLException;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hempreet.bean.Appointments;
import com.hempreet.dao.AppointmentsDaoImpl;
@Service
public class AppointmentsServiceImpl implements AppointmentsService {

	@Autowired
	private AppointmentsDaoImpl appointmentsDao=new AppointmentsDaoImpl();
	
	@Override
	public void bookAppointment(int docId,int patId,LocalDateTime timeScheduled) throws ClassNotFoundException, SQLException {
		appointmentsDao.setAppointments(docId, patId, 0, timeScheduled);
	}


	@Override
	public List<Appointments> getAppointmentsForPatient(int patId) throws SQLException {
		List<Appointments> appointments=appointmentsDao.getAppointmentByPatId(patId);
		return appointments;
	}

	@Override
	public void deleteAppointment(int appointId)throws SQLException {
		appointmentsDao.deleteAppointment(appointId);
	}

}
