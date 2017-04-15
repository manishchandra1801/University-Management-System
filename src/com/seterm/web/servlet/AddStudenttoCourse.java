package com.seterm.web.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seterm.web.jdbc.CourseRegistrationdb;
import com.seterm.web.jdbc.CourseRegistrationdbimpl;
import com.seterm.web.jdbc.Coursedb;
import com.seterm.web.jdbc.Gradecenterdb;
import com.seterm.web.jdbc.Gradecenterdbimpl;

/**
 * Servlet implementation class AddStudenttoCourse
 */
@WebServlet("/AddStudenttoCourse")
public class AddStudenttoCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	private HttpSession session;
	private Coursedb coursedb;
	private CourseRegistrationdb courseregistrationdb;
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
			
			courseregistrationdb=new CourseRegistrationdbimpl(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		session = request.getSession();
    	//session.setAttribute("studentId", "as132736");
    
    	if(session.getAttribute("NETID")==null){    		
    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
    	}
    	String coursename=request.getParameter("coursename");
    	String netid=(String) session.getAttribute("NETID");
    	System.out.println(coursename);
    	int i=courseregistrationdb.courseregistrationadd(netid, coursename);
    	if(i!=0){
    	request.setAttribute("courseadd", "Course Added to Catalouge");
		request.getRequestDispatcher("ViewCourseCat_Student.jsp").forward(request, response);}

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
