package com.hempreet.service;
import java.util.*;


import java.sql.SQLException;
import java.time.LocalDateTime;

public interface PatientsService {

	public Map<LocalDateTime,Boolean> getScheduleforPatient(int docId,LocalDateTime from,LocalDateTime to)throws ClassNotFoundException,SQLException;
	
	public Map<Integer,List<LocalDateTime>> getTimeSlotforPatient()throws ClassNotFoundException,SQLException; //int docId
	
	public void addPatient(String name,String medicalHistory,String phone)throws ClassNotFoundException,SQLException;
	
	public boolean existingPatient(int patId)throws ClassNotFoundException,SQLException;//called inside addPatient
	
	public int fetchPatientId(String phone) throws ClassNotFoundException, SQLException;
	
	
	
}
