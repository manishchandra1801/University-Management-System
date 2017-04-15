<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="menu.jsp"></jsp:include>
<style>
body {
    margin: 0;
}

.monica {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 17%;
    background-color: #ebdef0 ;
    position: fixed;
    height: 100%;
    overflow: auto;
}


.monica li a {
background-color: #94618E;
    display: block;
    color: #000;
    padding: 8px 0 8px 16px;
    text-decoration: none;
}

.monica li a.active {
    background-color: #94618E;
    color: BLACK;
}



.monica li a:hover {
    color: #F6D8CE;
    background-color: #49274A;
}
body {
    font-family: "Lato", sans-serif;
}
 
.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}
 
.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s
}
 
.sidenav a:hover, .offcanvas a:focus{
    color: #f1f1f1;
}
 
.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}
 
#main {
    transition: margin-left .5s;
    padding: 16px;
}
 
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

.h_iframe1 iframe {position:absolute;top:0;left:0;width:100%; height:calc(100% - 200px);}
.h_iframe2 iframe {position:absolute;bottom:0;right:0;width:100%; height:200px;}

.navigation {
  width: 300px;
}

/* reset our lists to remove bullet points and padding */
.mainmenu, .submenu {
  list-style: none;
  padding: 0;
  margin: 0;
}

/* make ALL links (main and submenu) have padding and background color */
.mainmenu a {
  display: block;
  background-color: #CCC;
  text-decoration: none;
  padding: 10px;
  color: #000;
}

/* add hover behaviour */
.mainmenu a:hover {
    background-color: #C5C5C5;
}


/* when hovering over a .mainmenu item,
  display the submenu inside it.
  we're changing the submenu's max-height from 0 to 200px;
*/

.mainmenu li:hover .submenu {
  display: block;
  max-height: 200px;
}

/*
  we now overwrite the background-color for .submenu links only.
  CSS reads down the page, so code at the bottom will overwrite the code at the top.
*/

.submenu a {
  background-color: #999;
}

/* hover behaviour for links inside .submenu */
.submenu a:hover {
  background-color: #666;
}

/* this is the initial state of all submenus.
  we set it to max-height: 0, and hide the overflowed content.
*/
.submenu {
  overflow: hidden;
  max-height: 0;
  -webkit-transition: all 0.5s ease-out;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Discussion Board</title>
</head>
<body>
<% HttpSession h=request.getSession(); 
String str=(String)h.getAttribute("NETID");
if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}%>

<!--  <div style="float: left">
<ul class="monica" >

  <li><a class="active" href="CoursesServlet?action=viewallcourses">View ALLCourses</a></li>
  <li><a href="CoursesServlet?action=viewmycourses">View MyCourses</a></li>
  <li><a href="Addtopic.jsp">Add Course</a></li>
  <li><a href="PostOfficehrs.jsp">Post Office Hours</a></li>
  
   </ul>
   </div>-->
   
<div id="mySidenav" class="sidenav">
  <a href="#" class="closebtn" onclick="closeNav() ">&times;</a>
  <!-- <a href= "AddManager.jsp"  id="Chemisrty" target="frame1" onclick="Chemisrty()" >Add Course</a>
  <a href="ViewQuads.jsp" target="frame1" id="Json" onclick="Json()">View Course</a>
  <a href="SetConfigurations.jsp" target="frame1" id="Physics" onclick="Physics()" >Configure Time</a> -->
  <nav class="navigation">
  <ul class="mainmenu">
   <li><a href="" target="frame1" id="Json" onclick="Json()" >View Course Comments</a>
	    <ul class="submenu">
	    <c:forEach var="CourseList" items="${Course_List}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="ViewCommentsServlet" >						
						<c:param name="coursename" value="${CourseList}" />
					</c:url>		
					
					<li><a href="${tempLink}" target="frame1" >${CourseList}</a></li>											
					
				
				</c:forEach>
	        <!--  <li><a href="" target="frame1" id="Physics" onclick="Physics()" >Course1</a></li>
	        <li><a href="" target="frame1" id="Physics" onclick="Physics()" >Course2</a></li>
	        <li><a href="" target="frame1" id="Physics" onclick="Physics()" >Course3</a></li>-->
	      </ul>
    </li>
    <!-- <li><a href="" target="frame1" id="Physics" onclick="Physics()" >Discussion Board</a>
      <ul class="submenu">
        <li><a href="" target="frame1" id="Physics" onclick="Physics()" >Course1</a></li>
        <li><a href="" target="frame1" id="Physics" onclick="Physics()" >Course2</a></li>
        <li><a href="" target="frame1" id="Physics" onclick="Physics()" >Course3</a></li>
      </ul>
    </li>
    <li><a href=""  target="frame1" id="Physics" onclick="Physics()" >GRADE CENTER</a></li> -->
  </ul>
</nav>    
</div>
 
<div id="main">
  <h2>Course Description</h2>
  <p>Click For Options</p>
  <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span> 
</div>
 
<div id="h_iframe1">
<iframe name="frame1" src="http://ellar.kissr.com/#" width="100%" style="height: 100vh;"></iframe>
</div>

 <!-- <div id="h_iframe2">  -->
 <!-- <iframe name="frame2" id="frame2" frameborder="0" width="100%" allowfullscreen></iframe> --> 
 <!-- </div> --> 
 
<script type="text/javascript">
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
document.getElementById("h_iframe1").style.marginLeft = "250px";
document.getElementById("frame2").style.marginLeft = "250px";
document.body.style.backgroundColor = "rgba(0,0,0,0.4)";    
}
 
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
document.getElementById("h_iframe1").style.marginLeft = "0";
document.getElementById("frame2").style.marginLeft = "0";
document.body.style.backgroundColor = "white"; 
}
 

</script>
</body>
</html>