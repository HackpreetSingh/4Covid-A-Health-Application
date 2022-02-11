package com.hempreet.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hempreet.bean.Doctor;
@Repository
public class DoctorDaoImpl implements DoctorDao {

	@Autowired
	private DataSource dataSource;
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Doctor> fetchDoctor() throws SQLException {
		List<Doctor> doctor = new ArrayList<Doctor>();
		Connection connection= dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Doctors;");
		while (resultSet.next()) {
			int docId = resultSet.getInt("docId");
			String name = resultSet.getString("name");
			String speciality = resultSet.getString("speciality");
			String phone = resultSet.getString("phone");
			LocalDateTime from = (resultSet.getTimestamp("fromtime")).toLocalDateTime();
			LocalDateTime to = (resultSet.getTimestamp("totime")).toLocalDateTime();
			doctor.add(new Doctor(docId, name, speciality, phone, from, to));
		}
//		JdbcTemplate.query(str);
		connection.close();
		return doctor;
	}

	@Override
	public boolean changeDoctorAppointment(int docId, LocalDateTime from, LocalDateTime to)
			throws SQLException {
		Connection connection = dataSource.getConnection();
		String str = "UPDATE Doctors\r\n" + "SET fromtime = '"+Timestamp.valueOf(from)+"' , totime = '"+Timestamp.valueOf(to)+ "' WHERE docId = " +docId+ ";";
		PreparedStatement preparedStatement = connection.prepareStatement(str);
		if(preparedStatement.executeUpdate()==1) return true;
		else return false;
	}

	@Override
	public boolean deleteDoctorAppointments(int docId) throws SQLException {
		Connection connection = dataSource.getConnection();
				String str = "Delete from Appointments where docId = " + docId + ";";
		PreparedStatement preparedStatement = connection.prepareStatement(str);
		connection.close();
		if(preparedStatement.executeUpdate()==1) return true;
		else return false;

	}



}
