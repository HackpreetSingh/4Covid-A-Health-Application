<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome back patient.</h1> <br>
<h3>Please Enter your Patient Id to continue: </h3>
<spring:form action="./verify" modelAttribute="pat">
Enter Patient Id : <spring:input path="patId"/><br><br>
<input type="submit" value="Login">
</spring:form>
<br> <br>
<a href="./PatientHomePage">Back to Home page</a>
</body> 
</html>