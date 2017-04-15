<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
String courseadd=(String) request.getAttribute("courseadd");
HttpSession h=request.getSession(); 
String str=(String)h.getAttribute("NETID");
if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}%>

<body>
<div id="container">

		
		<c:forEach var="tempStudent" items="${Course_List}">
		<!-- ${fn:contains(commandMap.model['invalidTests'], test.testName)} -->
		
		<c:if test="${!fn:contains(Course_List_student,tempStudent.coursename)}">
	
		<form action="AddStudenttoCourse" method="GET">
		
		<div id="content">					
		<h2>Course Name:</h2><h2>${tempStudent.coursename}</h2>		
		<h2>Course Description:</h2><textarea disabled cols="100">${tempStudent.description}</textarea><br/>
		<input type="hidden" name="coursename" value="${tempStudent.coursename}" />
		
		<input type="submit" name="button" value="Enroll Course"><p><%if(courseadd!=null){out.println(courseadd);} %></p> 		
		</div>
		<hr>
		</form>
		</c:if>
		</c:forEach>
		
  
	
	</div>
	<script>
document.write(test);
</script>
</body>

</html>