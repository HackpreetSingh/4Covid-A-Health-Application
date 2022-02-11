<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="com.hempreet.bean.Doctor"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty doctor }">
<table border="1">
	<tr>
		<td>Doctor ID</td>
		<td>Doctor Name</td>
		<td>Speciality</td>
		<td>Phone</td>
		<td>Start Time</td>
		<td>Stop Time</td>

	</tr>
	
	<tr>
		<td>${doctor.docId }</td>
		<td>${doctor.name }</td>
		<td>${doctor.speciality }</td>
		<td>${doctor.phone }</td>
		<td>${doctor.from }</td>
		<td>${doctor.to }</td>
		
	</tr>		
</table>
</c:if>
<%-- 
<%} else %>
--%>
<c:if test="${empty doctor}">
		<h1>Doctor does not exist</h1>
</c:if>
<br><br>
<a href="./doctorHomePage">Go to doctor Home Page</a>
</body>
</html>