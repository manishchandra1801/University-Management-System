<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
html, body{
   height: 100%;
   min-height: 100%;
}
</style>
</head>
<body>
<% HttpSession h=request.getSession(); 
String str=(String)h.getAttribute("NETID");
if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}%>
<form action="AddMoreCourseMaterial" method="POST" enctype="multipart/form-data">
<div id="container">
	
		<div id="content">			
		<h2>Course Name:</h2><h2>${Course_Name}</h2>
		<hr>
		<h2>Course Description:</h2><br/>${Course_Desc}
		<hr>
		
		<h2>Assignment with two Questions:</h2><br/>
				<c:forEach var="tempassignment" items="${Assignment_List}">
				<table>
				<col width="170">
				<col width="170">
				<col width="170">
				<tr><td>${tempassignment.question}</td></tr>
				<tr>
				<td><input type="checkbox" name="favoriteLanguage" value="oneoption1">${tempassignment.option1}</td>
				<td><input type="checkbox" name="favoriteLanguage" value="oneoption2">${tempassignment.option2}</td>
				<td><input type="checkbox" name="favoriteLanguage" value="oneoption3">${tempassignment.option3}</td>
				<td><input type="checkbox" name="favoriteLanguage" value="oneoption4">${tempassignment.option4}</td>				
				</tr>
				<tr><td>Answers:${tempassignment.answer}</td></tr>
				</table>
				</c:forEach>
		<hr>
		<p>Add Course Material:</p><input type="file" name="material" enctype="multipart/form-data"><p> Name the Material</p><input type="text" name="materialname"><input type="submit" name="button" value="Add Material">
			
			
			
		<hr>
		<c:forEach var="tempfile" items="${File_Upload}">
		<object data="ViewPdf?coursename=${Course_Name}&fileid=${tempfile.fileid}" height="1000" width="100%"">
    		<embed src="ViewPdf?coursename=${Course_Name}&fileid=${tempfile.fileid}" />
		</object>
		</c:forEach>
		</div>
		
		 <!-- <embed src="ViewPdf?coursename=${Course_Name}&fileid=${tempfile.fileid}" type="application/pdf"/> -->
  
	
	</div>
	</form>
</body>
</html>