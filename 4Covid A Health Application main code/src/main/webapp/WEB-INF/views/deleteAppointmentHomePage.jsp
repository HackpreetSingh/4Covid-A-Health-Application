<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.hempreet.bean.Appointments"%>
<%@page import="java.util.Collection"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Doctor's List is below: </h1>
<table border="1">
<thead>
<tr>
<td>Doctor Id</td>
<td>Patient Id</td>
<td>Appointment Id</td>
<td>Time Scheduled</td>
</tr>
</thead>
 <tbody>
<c:forEach items="${appointments}" var="appointments">
	<tr>
		<td>${appointments.docId }</td>
		<td>${appointments.patId }</td>
		<td>${appointments.appointId}</td>
		<td>${appointments.timeScheduled}</td>
	</tr>
	</c:forEach>
<tr></tr>
</tbody>
</table>
<br>
<br>
<h3>Please Enter your Appointment Id to continue: </h3>
<form action="./delete">
Enter Appointment Id : <input type="text" name="appointId"/><br><br>
<input type="submit" value="Submit">
</form>

 <a href="./getSetAppointments">Go to Your Home Page</a>
</body>
</html>