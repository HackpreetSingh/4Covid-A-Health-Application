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
<h1>Doctor Search</h1>
<form action="searchDocById"> 
<select name="item">
<c:forEach items="${docIds}" var="id">
    <option value="${id}">${id}</option>
</c:forEach>
</select>
<br><br> <input type="submit" value="Submit">
 </form>
<br><br>
<a href="./doctorHomePage">Go To Doctor Home Page</a>
</body>
</html>