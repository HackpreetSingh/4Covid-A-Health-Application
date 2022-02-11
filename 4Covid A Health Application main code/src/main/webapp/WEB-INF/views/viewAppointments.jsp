<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Appointment's List is below: </h1>
<table border="1">
<thead>
<tr>
<td>Doctor's Id</td>
<td>Patient Id</td>
<td>Appointment Id</td>
<td>Time Scheduled</td>
</tr>
</thead>
 <tbody>
<c:forEach items="${appointments}" var="appoint">
	<tr>
		<td>${appoint.docId }</td>
		<td>${appoint.patId }</td>
		<td>${appoint.appointId}</td>
		<td>${appoint.timeScheduled}</td>
	</tr>
	</c:forEach>
<tr></tr>
</tbody>
</table>
<br>

<a href="./getSetAppointments">Go to home Page</a>
</body>
</html>