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
<div id="container">
	
		<div id="content">			
		<h2>Course Name:</h2><h2>${Course_Name}</h2>
		<hr>
		<h2>Course Description:</h2><br/><h2>${Course_Desc}</h2>
		<hr>
		
		<h2>Assignment with two Questions:</h2><br/>
				<c:forEach var="tempassignment" items="${Assignment_List}">
				<c:forEach var="studentassignment" items="${student_assignment}">
				<c:if test="${tempassignment.assignmentid==studentassignment.assignmentid }">
				<c:if test="${studentassignment.grade ==  null}">
				<form  action="AddAssignmentServlet" method="GET">
				
				<h3>${tempassignment.question}</h3>
				
			
				<label><input type="checkbox" name="favoriteLanguage" value="${tempassignment.option1}">${tempassignment.option1}</label>
				<label><input type="checkbox" name="favoriteLanguage" value="${tempassignment.option2}">${tempassignment.option2}</label>
				<label><input type="checkbox" name="favoriteLanguage" value="${tempassignment.option3}">${tempassignment.option3}</label>
				<label><input type="checkbox" name="favoriteLanguage" value="${tempassignment.option4}">${tempassignment.option4}</label>
					
				
				<input type="submit" name="answer" value="Answer"><input type="hidden" name="assignmentid" value="${tempassignment.assignmentid}" />
				
				</form>    
				</c:if>
				</c:if>
				
				
				</c:forEach>
			
				</c:forEach>
		<hr>
		<c:forEach var="tempfile" items="${File_Upload}">
		<object data="ViewPdf?coursename=${Course_Name}&fileid=${tempfile.fileid}"  height="1000" width="100%">
    		<embed src="ViewPdf?coursename=${Course_Name}&fileid=${tempfile.fileid}" />
		</object>
		</c:forEach>
		</div>
		
		
  <!-- <embed src="${tempfile.outputstream}" type="application/pdf"/> -->
	
	</div>

</body>
</html>