<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String message = (String) request.getAttribute("message");
	%>
	<%
	if (message != null) {
	%>
	<h1><%=message%></h1>
	<%
	} else {
	%>
	<h1><%="Welcome to Student Application please signup"%></h1>
	<%
	}
	%>
	
	<form action="signup" method="post">
	Name::<input type="text" name="name">
	<br>
	<br>
	Email::<input type="email" name="email">
	<br>
	<br>
	Password::<input type="password" name="password">
	<br>
	<br>
	Address::<input type="text" name="address">
	<br>
	<br>
	Phone::<input type="number" name="phone">
	<br>
	<br>
	<input type="submit" value="Signup">
	</form>
		
</body>
</html>