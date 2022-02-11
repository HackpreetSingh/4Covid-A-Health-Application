package com.hempreet.presentation;


public interface PatientPresentation {
	
	public void displayMenu();
	
	public void bookAppointments(int patId);

	public void addPatient();	
	
	public void existingPatientCheck();
	
	public void existingPatientMenu(int patId);

	public void cancelAppointment(int patId);

	void viewAppointments(int patId);

	
}