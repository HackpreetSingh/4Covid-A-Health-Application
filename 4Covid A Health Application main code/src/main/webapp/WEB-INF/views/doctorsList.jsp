<%@page import="com.hempreet.bean.Doctor"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
<td>Doctor's Name</td>
<td>Doctor's Speciality</td>
<td>Doctor's Contact info</td>
</tr>
</thead>
 <tbody>
<c:forEach items="${DOC}" var="doc">
	<tr>
		<td>${doc.name }</td>
		<td>${doc.speciality }</td>
		<td>${doc.phone}</td>
	</tr>
	</c:forEach>
<tr></tr>
</tbody>
</table>
<br> <br>
<a href="./doctorHomePage">Go to doctor Home Page</a>
</body>
</html>