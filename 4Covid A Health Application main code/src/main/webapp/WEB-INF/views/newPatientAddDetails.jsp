<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome New User</h1>
<h3>Please fill in the further details to continue: </h3>
<form action="./savePatient" method="post">
Enter Your Name : <input type="text" name="name"><br><br>
Enter your Medical History : <input type="text" name="medicalHistory"><br><br>
Enter Your Phone Number : <input type="text" name="phone"><br><br>
<input type="submit" value="Submit">
</form>
</body>
</html>