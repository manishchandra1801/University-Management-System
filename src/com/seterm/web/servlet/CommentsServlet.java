package com.seterm.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seterm.web.jdbc.Commentdb;
import com.seterm.web.jdbc.Commentdbimpl;
import com.seterm.web.jdbc.Coursedb;
import com.seterm.web.jdbc.Coursedbimpl;
import com.seterm.web.jdbc.Gradecenterdb;
import com.seterm.web.jdbc.Gradecenterdbimpl;
import com.seterm.web.model.Comment;
import com.seterm.web.model.Reply;

/**
 * Servlet implementation class CommentsServlet
 */
@WebServlet("/CommentsServlet")
public class CommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	private HttpSession session;
	private Coursedb coursedb;
	private Gradecenterdb gradecenterdb;
	private Commentdb commentdb;
	private HttpServletRequest request;
	private String column="";
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			coursedb=new Coursedbimpl(dataSource);
			//commentdb=new Commentdbimpl(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			session = request.getSession();
	    	//session.setAttribute("studentId", "as132736");
	    
	    	if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}
	    	//List<Comment> commentlist = new ArrayList<>();
	    	//List<Reply> replylist = new ArrayList<>();	    	
	    	List<String> coursenames = new ArrayList<>();
			String coursename=request.getParameter("addcoursename");
			
			
			if(session.getAttribute("Role").equals("Faculty")){
			coursenames=coursedb.getFacultyCoursenames((String)session.getAttribute("NETID"));}
			else if(session.getAttribute("Role").equals("Student")){
			coursenames=coursedb.getStudentCoursenames((String)session.getAttribute("NETID"));}
			//commentlist=commentdb.getComments("11");
			//replylist=commentdb.getReplys("11");
			//request.setAttribute("Comment_List", commentlist);
			//request.setAttribute("Reply_List", replylist);
			request.setAttribute("Course_List", coursenames);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Comments.jsp");
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
