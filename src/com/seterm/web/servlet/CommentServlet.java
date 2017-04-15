package com.seterm.web.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seterm.web.jdbc.Assignmentdb;
import com.seterm.web.jdbc.Commentdb;
import com.seterm.web.jdbc.Commentdbimpl;
import com.seterm.web.jdbc.Coursedb;
import com.seterm.web.jdbc.Coursedbimpl;
import com.seterm.web.jdbc.Filetouploaddb;
import com.seterm.web.model.Comment;
import com.seterm.web.model.Reply;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Coursedb coursedb;
	private Assignmentdb assignmentdb;
	private Filetouploaddb filetouploaddb;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Commentdb commentdb;
    /**
     * @see HttpServlet#HttpServlet()
     */
	 
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	public void init() throws ServletException{
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			commentdb=new Commentdbimpl(dataSource);
			coursedb=new Coursedbimpl(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String commenterid_faculty=null;
			String commenterid_student=null;
	
			session = request.getSession();
			if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}
			
			
			String comment=request.getParameter("comment");
			String session_coursename=(String)session.getAttribute("session_coursename");
			String courseid=coursedb.getcourseid(session_coursename);
			if(session.getAttribute("Role").equals("Faculty")){
				commenterid_faculty=(String) session.getAttribute("NETID");}
				else if(session.getAttribute("Role").equals("Student")){
				commenterid_student=(String) session.getAttribute("NETID");
				}
			
			String Role=(String) session.getAttribute("Role");
		
		      
		     Comment tempcomment=new Comment();
		     tempcomment.setComment(comment);
		     tempcomment.setCourseid(courseid);
		     tempcomment.setCommenterid_faculty(commenterid_faculty);
		     tempcomment.setCommenterid_student(commenterid_student);
		     
		    
		      
		
		     commentdb.commentadd(tempcomment,Role);
		     System.out.println( session.getAttribute("session_coursename")+"fromCommentsServlet");
		    
		     request.setAttribute("coursename",session.getAttribute("session_coursename") );
		     RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewCommentsServlet");
				dispatcher.forward(request, response);
		      
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
