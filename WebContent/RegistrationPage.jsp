<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<% String error = (String)request.getAttribute("error"); 
HttpSession h=request.getSession(); 
String str=(String)h.getAttribute("NETID");
if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}%>
<form action="RegistrationServlet" method="Post">
		<div align="center">
		<h3>Register Here</h3>
		<table>
		<tr>
		<td>NetID</td>
		<td><input type="text" name="NetID" /></td>
		</tr>
		<tr>
		<td>Password</td>
		<td><input type="password" name="pwd" /></td>
		</tr>
		<tr>
		<td>Firstname</td>
		<td><input type="text" name="firstname" /></td>
		</tr>
		<tr>
		<td>Lastname</td>
		<td><input type="text" name="Lastname" /></td>
		</tr>
		<tr>
		<td>Role</td>
		<td><select name="Role">
  <option value="Student">Student</option>
  <option value="Faculty">Faculty</option>


</select></td>
</tr>
<tr>
<td>Year of joining program</td>
<td><input type="text" name="yearofjoiningprogram" /></td>
</tr>
<tr>
<td>Program</td>
<td><select name="Program">
  <option value="BS">BS</option>
  <option value="BA">BA</option>
  <option value="MS">MS</option>
   <option value="PHD">PHD</option>
</select></td>
</tr>
</table>	
<tr>
<td><input type="submit" value="Submit" /></td>
<%if(error!=null){
	out.print(error);
}
	%>
</tr>
		</div>
	</form>
</body>
</html>