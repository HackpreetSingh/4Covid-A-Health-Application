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
import org.springframework.stereotype.Repository;

import com.hempreet.bean.Appointments;
@Repository
public class AppointmentsDaoImpl implements AppointmentsDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Appointments> fetchAppointments(int docId) throws SQLException {
		List<Appointments> appointments = new ArrayList<Appointments>();
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		String str = "SELECT patId,appointId,timescheduled FROM Appointments where docId = " + docId + ";";
		ResultSet resultSet = statement.executeQuery(str);
		while (resultSet.next()) {
			int patId = resultSet.getInt("patId");
			int appointId = resultSet.getInt("appointId");
			LocalDateTime t = (resultSet.getTimestamp("timescheduled")).toLocalDateTime();
			appointments.add(new Appointments(docId, patId, appointId, t));
		}
		connection.close();
		return appointments;
	}

	@Override
	public void setAppointments(int docId, int patId, int appointId, LocalDateTime t)
			throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("Insert into Appointments(docId,patId,timescheduled) values(?,?,?);");
		preparedStatement.setInt(1, docId);
		preparedStatement.setInt(2, patId);
		preparedStatement.setTimestamp(3, Timestamp.valueOf(t));
		preparedStatement.execute();
		connection.close();
	}

	@Override
	public void deleteAppointment(int appointId) throws SQLException{
		Connection connection = dataSource.getConnection();
		String str = "Delete from Appointments where appointId = " + appointId + ";";
		PreparedStatement preparedStatement = connection.prepareStatement(str);
		preparedStatement.execute();
		connection.close();
	}

	@Override
	public List<Appointments> getAppointmentByPatId(int patId) throws SQLException {
		List<Appointments> appointments = new ArrayList<Appointments>();
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		String str = "SELECT docId,appointId,timescheduled FROM Appointments where patId = " + patId + ";";
		ResultSet resultSet = statement.executeQuery(str);
		while (resultSet.next()) {

			int docId = resultSet.getInt("docId");
			int appointId = resultSet.getInt("appointId");
			LocalDateTime t = (resultSet.getTimestamp("timescheduled")).toLocalDateTime();
			appointments.add(new Appointments(docId, patId, appointId, t));
		}
		connection.close();
		return appointments;
	}


}
