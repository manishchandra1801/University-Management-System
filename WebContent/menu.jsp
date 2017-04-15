<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>menu</title>
<link href="Design.css" rel="stylesheet">
</head>
<body>
<% HttpSession h=request.getSession(); 
String str=(String)h.getAttribute("NETID");
if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}%>
<center>
<ul id='menu'>
<li><a href='menu.jsp'>Home</a></li> 
<% String ch=session.getAttribute("Role")==null ? "" :session.getAttribute("Role").toString(); 
System.out.print("hi "+ch);
if( ch.equals("Faculty"))
{%>
<li><a href="CoursesServlet">Courses</a></li>
<%}else{ %>
<li><a href="CoursesServletStudent">Courses</a></li>
<%} %>
<!-- <li><a href="phdstudentServlet?viewphdstudent">PHD Students</a></li> -->
<!-- <li><a href="Resource.jsp">Resources</a></li> -->
<% String ch1=session.getAttribute("Role")==null ? "" :session.getAttribute("Role").toString();
if( ch1.equals("Faculty"))
{%>
<li><a href="GradcenterServlet">Grade Center</a></li>
<%}else{ %>
<li><a href="GradcenterServlet">Grade Center</a></li>
<%} %>
<!-- <li><a href="Discussionboard.jsp">Discussion Board</a></li> -->
<!-- <li><a href="AlumniHome.jsp">Alumni info</a></li> -->

<% String ch2=session.getAttribute("Role")==null ? "" :session.getAttribute("Role").toString();
if( ch2.equals("Faculty"))
{%>

<li><a href="CommentsServlet">Discussion Board</a></li>
<%}else{ %>
<li><a href="CommentsServlet">Discussion Board</a></li>
<%} %>

<li><a href="LogoutServlet">LogOut</a></li>

<% if(str!=null){
out.print(str);
}%>
</ul>
<nav id="nav01"></nav>

<div id="main">
  
<footer id="foot01"></footer>
</div>
</center>

</body>
</html>