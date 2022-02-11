<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<h1>Doctor Schedule Update</h1>
<form action="./updateDocPage" method="post">
Enter Doctor ID : <input type="text" name="docId"><br><br>
<p style="color: red">Note: All your present Appointments will be cancelled</p>
Enter Doctor New Day in format(yyyy-mm-dd) : <p>Date: <input type="text"name="date"></p><br><br>
Enter Doctor New Start Time in format(HH:MM) : <p>Start Time: <input type="text" name="from"></p><br><br>
Enter Doctor New Stop Time in format(HH:MM) : <p>Stop Time: <input type="text" name="to"></p><br><br>
<input type="submit" value="Update Doctor Schedule">
</form>
<br><br>
<a href="./doctorHomePage">GO To Main Page</a>
</body>
</html>