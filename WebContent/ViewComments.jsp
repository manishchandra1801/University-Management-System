<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% HttpSession h=request.getSession(); 
String str=(String)h.getAttribute("NETID");
if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}%>
<div id="wrapper">
		<div id="header">
			<h2>Discussion Board</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->			
			<table>
			<col width="170">
	
				<c:forEach var="tempComment" items="${Comment_List}">
				
					<tr>
					<td> ${tempComment.commenterid_faculty}${tempComment.commenterid_student} SAYS....</td></tr>
					<tr>
					<td> <textarea disabled>${tempComment.comment}</textarea></td></tr>
					<tr>
					<td><form action="ReplyServlet" method="GET"><input type="text" name="reply"><input type="hidden" name="commentid" value="${tempComment.commentid}" /><input type="submit" value="reply"></form></td></tr>
				<c:forEach var="tempReply" items="${Reply_List}">
					<!-- set up a link for each student -->					    
					    <c:if test= "${tempReply.commentid == tempComment.commentid }">
						<tr>
						<td></td><td> ${tempReply.replierid_faculty}${tempReply.replierid_student} SAYS...</td></tr>
						<tr>
						<td></td><td><textarea disabled  cols="50"> ${tempReply.reply}</textarea></td></tr>
						</c:if>			
				</c:forEach>
				
				</c:forEach>
				<tr></tr>
				<tr></tr>
				<tr><td></td>
					<td><form action="CommentServlet" method="GET"><input type="text" name="comment"><input type="submit" value="Add Comment"></form> </td></tr>
			</table>
		
		</div>
	
	</div>
</body>
</html>