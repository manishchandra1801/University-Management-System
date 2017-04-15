package com.seterm.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
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
import com.seterm.web.jdbc.CourseRegistrationdb;
import com.seterm.web.jdbc.CourseRegistrationdbimpl;
import com.seterm.web.jdbc.Coursedb;
import com.seterm.web.jdbc.Coursedbimpl;
import com.seterm.web.jdbc.Filetouploaddb;
import com.seterm.web.jdbc.Filetouploaddbimpl;
import com.seterm.web.model.Assignment;
import com.seterm.web.model.Course;
import com.seterm.web.model.Filetoupload;
import com.seterm.web.model.Student;

/**
 * Servlet implementation class ViewCoursesServlet
 */
@WebServlet("/ViewCoursesServlet")
public class ViewCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	private HttpSession session;
	private Coursedb coursedb;
	private Assignmentdb assignmentdb;
	private Filetouploaddb filetouploaddb;
	private CourseRegistrationdb courseregistrationdb;
	InputStream  inputstream;
	OutputStream output;
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
			assignmentdb=new Assignmentdbimpl(dataSource);
			filetouploaddb=new Filetouploaddbimpl(dataSource);
			courseregistrationdb=new CourseRegistrationdbimpl(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("gotopost").equals("POST")){
			doPost(request,response);
		}
		if(session.getAttribute("NETID")==null){    		
    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
    	}  
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			// read the "command" parameter
			session = request.getSession();
			if(session.getAttribute("NETID")==null){
			doGet(request,response);}
		String netid=(String) session.getAttribute("NETID");	
		String coursename = request.getParameter("coursename");
		Course course =coursedb.getCourseNamesDescription(coursename);
		List<Assignment> assignment =assignmentdb.getCourseAssignment(coursename);		
		List<Filetoupload> filetoupload =filetouploaddb.getCourseBlob(coursename);
		Assignment studentgrtade0 =courseregistrationdb.getStudentAssignment(netid, assignment.get(0).getAssignmentid());
		Assignment studentgrtade1 =courseregistrationdb.getStudentAssignment(netid, assignment.get(1).getAssignmentid());
		List<Assignment> arr = new ArrayList<Assignment>();
		arr.add(studentgrtade0);
		arr.add(studentgrtade1);
		
		List<String> ass1opts = new ArrayList<String>();
		List<String> ass2opts = new ArrayList<String>();
		
		ass1opts.add(assignment.get(0).getOption1());
		ass1opts.add(assignment.get(0).getOption2());
		ass1opts.add(assignment.get(0).getOption3());
		ass1opts.add(assignment.get(0).getOption4());
		
		ass2opts.add(assignment.get(1).getOption1());
		ass2opts.add(assignment.get(1).getOption2());
		ass2opts.add(assignment.get(1).getOption3());
		ass2opts.add(assignment.get(1).getOption4());
		
		Collections.shuffle(ass1opts);
		Collections.shuffle(ass2opts);
		
		assignment.get(0).setOption1(ass1opts.get(0));
		assignment.get(0).setOption2(ass1opts.get(1));
		assignment.get(0).setOption3(ass1opts.get(2));
		assignment.get(0).setOption4(ass1opts.get(3));
		
		assignment.get(1).setOption1(ass2opts.get(0));
		assignment.get(1).setOption2(ass2opts.get(1));
		assignment.get(1).setOption3(ass2opts.get(2));
		assignment.get(1).setOption4(ass2opts.get(3));
		
		



		//System.out.println(assignment.get(0).getAssignmentid()+"kav");
		//System.out.println(assignment.get(1).getAssignmentid()+"kav");
		//System.out.println(arr.get(0).getAssignmentid());
		//System.out.println(arr.get(1).getAssignmentid());
		request.setAttribute("student_assignment", arr);
		session.setAttribute("coursepage_coursename", coursename);
		request.setAttribute("Course_Name", course.getCoursename());
		request.setAttribute("Course_Desc", course.getDescription());
		request.setAttribute("Assignment_List", assignment);
		request.setAttribute("File_Upload", filetoupload);
		//System.out.println(assignment.get(0).getGrade()+"-------++++++++----------------");
		//System.out.println(assignment.get(1).getGrade()+"-----------+++++++------------");
		if(session.getAttribute("Role").equals("Faculty")){
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View_Course.jsp");
		dispatcher.forward(request, response);}else if(session.getAttribute("Role").equals("Student")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View_Course_Student.jsp");
		dispatcher.forward(request, response);}
		
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	

}
