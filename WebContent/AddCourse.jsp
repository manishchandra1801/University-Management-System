<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<%String addcoursename=(String) request.getAttribute("availablecoursename");
String coursecount=(String) request.getAttribute("coursecount");
 HttpSession h=request.getSession(); 
String str=(String)h.getAttribute("NETID");
if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}%>
<form action="AddCourse" method="POST" enctype="multipart/form-data">
	<table style="width: 100%">
	<col width="170">
	<col width="160">
	<col width="150">
	
		<tr>
			<td>Course Name:</td>
			<td><input type="text" name="addcoursename"><input type="submit" name="button" value="Check Availability"></td>			
			
			<td><%if(addcoursename!=null){out.println(addcoursename);} %> </td>
		</tr>

		<tr>
			<td>Course Description:</td>
			<td><textarea name='Description' id='Description' ></textarea></td>
		</tr>
		<tr>		
			<td>Upload Course Material:</td>
			<td><input type="file" name="material" enctype="multipart/form-data"></td>
			<td>Name the Material</td>
			<td><input type="text" name="materialname"></td>
			
		</tr>
		<tr></tr>
		
		<tr>
			<td>Add Question1:</td>
			<td><textarea name='Question1' id='Question1'></textarea></td>			
		</tr>
		<tr>
		<td>Add Answers:</td>		
		</tr>
		<tr>
		<td>option1</td><td><input type="text" name="oneoption1"></td>
		</tr>
		<tr>
		<td>option2</td><td><input type="text" name="oneoption2"></td>
		</tr>
		<tr>
		<td>option3</td><td><input type="text" name="oneoption3"></td>
		</tr>
		<tr>
		<td>option4</td><td><input type="text" name="oneoption4"></td>
		</tr>
		<tr>
		<td>Enter answers seperated by ';'</td><td><input type="text" name="oneanswer"></td>
		</tr>
		<tr>
			<td>Add Question2:</td>
			<td><textarea name='Question2' id='Question1'></textarea></td>			
		</tr>		
		<tr>
		<td>Add Answers:</td>	
		</tr>
		<tr>
		<td>option1<td><input type="text" name="twooption1"></td>
		</tr>
		<tr>
		<td>option2<td><input type="text" name="twooption2"></td>
		</tr>
		<tr>
		<td>option3</td><td><input type="text" name="twooption3"></td>
		</tr>
		<tr>
		<td>option4</td><td><input type="text" name="twooption4"></td>
		</tr>
		<tr>
		<td>Enter answers seperated by ';'</td><td><input type="text" name="twoanswer"></td>
		</tr>
		<tr>			
			<td></td><td><input type="submit" name="button" value="Add Course"></td><td><%if(coursecount!=null){out.println(coursecount);} %> </td>
		</tr>
	</table>
</form>
<body>

</body>
</html>