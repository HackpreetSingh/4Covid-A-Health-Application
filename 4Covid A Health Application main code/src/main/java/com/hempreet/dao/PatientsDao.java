package com.hempreet.dao;

import java.sql.SQLException;
import java.util.List;

import com.hempreet.bean.Patient;

public interface PatientsDao {

	
	public void addRecordPatient(Patient p)throws SQLException,ClassNotFoundException;
	
	public List<Patient> fetchRecordsPatient()throws SQLException,ClassNotFoundException;
	
			
}
