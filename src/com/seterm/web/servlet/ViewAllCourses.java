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

import com.seterm.web.jdbc.Assignmentdb;
import com.seterm.web.jdbc.Assignmentdbimpl;
import com.seterm.web.jdbc.Coursedb;
import com.seterm.web.jdbc.Coursedbimpl;
import com.seterm.web.jdbc.Filetouploaddb;
import com.seterm.web.jdbc.Filetouploaddbimpl;
import com.seterm.web.model.Assignment;
import com.seterm.web.model.Course;
import com.seterm.web.model.Filetoupload;

/**
 * Servlet implementation class ViewAllCourses
 */
@WebServlet("/ViewAllCourses")
public class ViewAllCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	private HttpSession session;
	private Coursedb coursedb;
	private Assignmentdb assignmentdb;
	private Filetouploaddb filetouploaddb;
	String gotopost="";
	private String filePath = "D:/Photos/Tom.jpg";
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
			if(session.getAttribute("NETID")==null){
			doGet(request,response);}
			
			List<String> coursenames = new ArrayList<>();
			//String coursename=request.getParameter("addcoursename");
			coursenames=coursedb.getStudentCoursenames((String)session.getAttribute("NETID"));
		//String coursename = request.getParameter("coursename");
		List<Course> courselist =coursedb.getCourseNamesDescription();
		//System.out.println(coursenames.get(0)+"AaAaAAAAAaAaaaaaaaaa============");
		//System.out.println(courselist.get(0).getCoursename()+"AaAaAAAAAaAaaaaaaaaa============");
		
		request.setAttribute("Course_List",courselist);
		request.setAttribute("Course_List_student",coursenames);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewCourseCat_Student.jsp");
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
