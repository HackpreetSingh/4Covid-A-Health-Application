<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Collection"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Time Slots</h1>

<form action="./finaliseSlot"> 
<select name="item">
<c:forEach items="${slots}" var="id">
    <option value="${id}">${id}</option>
</c:forEach>
</select>
<br><br> <input type="submit" value="Submit">
 </form>
  <a href="./getSetAppointments">Go to Your Home Page</a>
</body>
</html>