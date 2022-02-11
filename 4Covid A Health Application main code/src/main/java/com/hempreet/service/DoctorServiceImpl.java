package com.hempreet.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hempreet.bean.Doctor;
import com.hempreet.dao.DoctorDaoImpl;
@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorDaoImpl doctorDao;
	
	@Override
	public List<Doctor> fetchDoctors() throws SQLException {	
		return doctorDao.fetchDoctor();
	}

	@Override
	public Doctor getDoctorById(int docId) throws SQLException {
		List<Doctor> doctors=doctorDao.fetchDoctor();
		for(Doctor d:doctors)
			if(d.getDocId()==docId) return d;
		return null;
	}

	@Override
	public Doctor fetchDoctorById(int docId)throws SQLException {
		List<Doctor> doctors=doctorDao.fetchDoctor();
		for(Doctor d:doctors) if(d.getDocId()==docId) return d;
		return null;
	}

	@Override
	public boolean changeDoctorAppointment(int docId, LocalDateTime from, LocalDateTime to)
			throws  SQLException {
		if(doctorDao.changeDoctorAppointment(docId, from, to)) return true;
		else return false;
	}

	@Override
	public boolean deleteDoctorAppointments(int docId) throws SQLException {
		if(doctorDao.deleteDoctorAppointments(docId))
			return true;
		else return false;
	}
}
