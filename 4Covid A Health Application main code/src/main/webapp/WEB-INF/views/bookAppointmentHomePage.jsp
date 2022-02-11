<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="com.hempreet.bean.Doctor"%>
<%@page import="java.util.Collection"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
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
<td>Doctor's Id</td>
<td>Doctor's Name</td>
<td>Doctor's Speciality</td>
<td>Doctor's Start Time</td>
<td>Doctor's Stop Time</td>
</tr>
</thead>
 <tbody>
<c:forEach items="${doctors}" var="doctors">
	<tr>
		<td>${doctors.docId }</td>
		<td>${doctors.name }</td>
		<td>${doctors.speciality }</td>
		<td>${doctors.from}</td>
		<td>${doctors.to}</td>
	</tr>
	</c:forEach>
<tr></tr>
</tbody>
</table>
<br><br>
<spring:form action="./appointmentTimeSelector" method="post" modelAttribute="doc">
	Select Doctor ID : <spring:select path="docId">
		<spring:options items="${docIds}"/>
		<br><br> <input type="submit" value="Submit">
	</spring:select><br><br>
</spring:form>
<br> <br> <a href="./getSetAppointments">Go to Your Home Page</a>
</body>
</html>