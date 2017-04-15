<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="menu.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<body>
<% HttpSession h=request.getSession(); 
String str=(String)h.getAttribute("NETID");
if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}%>
	<div id="wrapper">
		<div id="header" style="width: 100%">
			<h2>Grade Center</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->
			
			
			
			<table style="width: 100%">
					<c:url var="tempLink" value="/GradcenterServlet">
						<c:param name="command" value="CourseNamestudent" />
						<c:param name="column" value="CourseName" />						
					</c:url>
					<c:url var="tempLink1" value="/GradcenterServlet">
						<c:param name="command" value="gradestudent" />
						<c:param name="column" value="grade" />
						
					</c:url>
					<c:url var="tempLink2" value="/GradcenterServlet">
						<c:param name="command" value="courseid" />
						<c:param name="column" value="courseid" />
						
					</c:url>
					<c:url var="tempLink3" value="/GradcenterServlet">
						<c:param name="command" value="grade" />
						<c:param name="column" value="grade" />
					</c:url>
				<tr>
					
					
					<th><a  href="${tempLink}">CourseName</a></th>
					<th><a href="${tempLink1}">Grade</a></th>
				</tr>
				
				<c:forEach var="tempStudent" items="${Gradecenter_List}">
					
					
					
																		
					<tr>
						<td> ${tempStudent.coursename} </td>
						<td> ${tempStudent.grade} </td>
					
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>
</body>
</html>