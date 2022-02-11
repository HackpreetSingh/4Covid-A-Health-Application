package com.hempreet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hempreet.bean.Patient;
@Repository
public class PatientsDaoImpl implements PatientsDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public void addRecordPatient(Patient p) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("Insert into Patients(name,MedicalHistory,phone) values(?,?,?);");
		preparedStatement.setString(1, p.getName());
		preparedStatement.setString(2, p.getMedicalHistory());
		preparedStatement.setString(3, p.getPhone());
		preparedStatement.execute();
		connection.close();
	}

	@Override
	public List<Patient> fetchRecordsPatient() throws SQLException {
		List<Patient> patients = new ArrayList<Patient>();
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Patients;");
		while (resultSet.next()) {
			int patId = resultSet.getInt("patId");
			String name = resultSet.getString("name");
			String MedicalHistory = resultSet.getString("MedicalHistory");
			String phone = resultSet.getString("phone");

			patients.add(new Patient(patId, name, MedicalHistory, phone));
		}
		connection.close();
		return patients;
	}

		}
