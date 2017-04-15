<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% String error = (String)request.getAttribute("error");%>
<form action="LoginServlet" method=post>

		<div align="center">
		<h3>Login</h3>
<table>
<tr>
<td>NetID</td>
<td><input type="text" name="NETID" /></td>
</tr>
<tr>
<td>Role</td>
<td><select name="Role">
  <option value="Student">Student</option>
  <option value="Faculty">Faculty</option>
  

</select></td>
</tr>
<tr>
<td>Password</td>
<td><input type="password" name="Password" /></td>
</tr>
</table>
<tr>
<td><input type="submit" value="Submit" /></td>
</tr>
<%if(error!=null){
	out.print(error);
}
	%>
</div>
</form>

</body>
</html>