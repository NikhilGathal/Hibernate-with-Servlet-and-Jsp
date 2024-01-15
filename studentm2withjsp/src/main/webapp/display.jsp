<%@page import="studentm2withjsp.dto.Student"%>
<%@page import="java.util.List"%>
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
	String name = (String) request.getAttribute("name");
	%>
	<%
	if (name != null) {
	%>
	<h1>Changed By<%=name%> </h1>
	<%
	}
	%>

	<%
	List<Student> list = (List) request.getAttribute("list");
	%>
	<table border="10px" align="center" cellspacing="15px"
		cellpadding="15px">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Address</th>
			<th>Email</th>
			<th>Password</th>
			<th>Phone</th>
			<th>Fees</th>
			<th>Delete</th>
			<th>Update</th>
		</tr>


		<%
		for (Student student : list) {
		%>
		<tr>
			<td><%=student.getId()%></td>
			<td><%=student.getName()%></td>
			<td><%=student.getAddress()%></td>
			<td><%=student.getEmail()%></td>
			<td><%=student.getPassword()%></td>
			<td><%=student.getPhone()%></td>
			<td><%=student.getFees()%></td>
			<td><a href="delete?id=<%=student.getId()%>">Delete</a></td>
			<td><a href="update?id=<%=student.getId()%>">Update</a></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>