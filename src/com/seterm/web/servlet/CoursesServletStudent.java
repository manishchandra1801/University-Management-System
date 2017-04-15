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

import com.seterm.web.jdbc.Coursedb;
import com.seterm.web.jdbc.Coursedbimpl;

/**
 * Servlet implementation class CoursesServletStudent
 */
@WebServlet("/CoursesServletStudent")
public class CoursesServletStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	private HttpSession session;
	private Coursedb coursedb;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			coursedb = new Coursedbimpl(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			session = request.getSession();
	    	//session.setAttribute("studentId", "as132736");
	    
	    	if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}
			
	    	List<String> coursenames = new ArrayList<>();
			String coursename=request.getParameter("addcoursename");
			coursenames=coursedb.getStudentCoursenames((String)session.getAttribute("NETID"));
			request.setAttribute("Course_List", coursenames);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/student_courses.jsp");
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
