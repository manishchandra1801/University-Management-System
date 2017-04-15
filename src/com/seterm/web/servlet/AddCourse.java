package com.seterm.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.seterm.web.jdbc.Assignmentdb;
import com.seterm.web.jdbc.Assignmentdbimpl;
import com.seterm.web.jdbc.Coursedb;
import com.seterm.web.jdbc.Coursedbimpl;
import com.seterm.web.jdbc.Filetouploaddb;
import com.seterm.web.jdbc.Filetouploaddbimpl;
import com.seterm.web.jdbc.Studentdbimpl;
import com.seterm.web.model.Assignment;
import com.seterm.web.model.Course;
import com.seterm.web.model.Filetoupload;
import com.seterm.web.model.Login;

/**
 * Servlet implementation class AddCourse
 */
 
@WebServlet("/AddCourse")
@MultipartConfig(maxFileSize = 16177215)
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Coursedb coursedb;
	private Assignmentdb assignmentdb;
	private Filetouploaddb filetouploaddb;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	 private static final String UPLOAD_DIRECTORY = "pdf";
	    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
	 
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	public void init() throws ServletException{
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			coursedb = new Coursedbimpl(dataSource);
			assignmentdb=new Assignmentdbimpl(dataSource);
			filetouploaddb=new Filetouploaddbimpl(dataSource);
			
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	//session.setAttribute("studentId", "as132736");
		session = request.getSession();
    	if(session.getAttribute("NETID")==null){    		
    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			session = request.getSession();
			if(session.getAttribute("NETID")==null){
			doGet(request,response);}
			String action=request.getParameter("button");
			String instructor=(String) session.getAttribute("NETID");
		int count=coursedb.getcoursecount(instructor);
			System.out.println(action);
			if((count<3)){
	if(action.equals("Add Course")){
		 String filename=request.getParameter("materialname");
	      
		
			String coursename=request.getParameter("addcoursename");
			String description=request.getParameter("Description");
			String instructorid=(String) session.getAttribute("NETID");
			
			InputStream inputStream = null; // input stream of the upload file
		         
		        // obtains the upload file part in this multipart request
		        Part filePart = request.getPart("material");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName()+"====FILENAME=====");
		            System.out.println(filePart.getSize()+"====FILEsize=====");
		            System.out.println(filePart.getContentType()+"====FILEType=====");
		            
		            // obtains input stream of the upload file
		            inputStream = filePart.getInputStream();
		        }
		     
		        System.out.println(filePart.getContentType()+"====FILETypeOUT=====");
		      String question1=request.getParameter("Question1");
		      String oneoption1=request.getParameter("oneoption1");
		      String oneoption2=request.getParameter("oneoption2");
		      String oneoption3=request.getParameter("oneoption3");
		      String oneoption4=request.getParameter("oneoption4");
		      String oneanswer=request.getParameter("oneanswer");
		      String question2=request.getParameter("Question2");
		      String twooption1=request.getParameter("twooption1");
		      String twooption2=request.getParameter("twooption2");
		      String twooption3=request.getParameter("twooption3");
		      String twooption4=request.getParameter("twooption4");
		      String twoanswer=request.getParameter("twoanswer");
		      Course course=new Course();	
		      Assignment assignment1=new Assignment();
		      Assignment assignment2=new Assignment();
		      Filetoupload filetoupload=new Filetoupload();
		      course.setCoursename(coursename);
		      course.setDescription(description);
		      course.setInstructorid(instructorid);
		      System.out.println(course.getCoursename());
		      System.out.println(course.getDescription());
		      assignment1.setQuestion(question1);
		      assignment1.setOption1(oneoption1);
		      assignment1.setOption2(oneoption2);
		      assignment1.setOption3(oneoption3);
		      assignment1.setOption4(oneoption4);
		      assignment1.setAnswer(oneanswer);
		      System.out.println(assignment1.getQuestion());
		      System.out.println(assignment1.getOption1());
		      System.out.println(assignment1.getOption2());
		      System.out.println(assignment1.getOption3());
		      System.out.println(assignment1.getOption4());
		      System.out.println(assignment1.getAnswer());
		      assignment2.setQuestion(question2);
		      assignment2.setOption1(twooption1);
		      assignment2.setOption2(twooption2);
		      assignment2.setOption3(twooption3);
		      assignment2.setOption4(twooption4);
		      assignment2.setAnswer(twoanswer);
		      filetoupload.setFilename(filename);
		      filetoupload.setInputStream(inputStream);
		      filetoupload.setContenttype(filePart.getContentType());
		      coursedb.courseadd(course);
		      System.out.println(coursedb.getcourseid(course)+"servlert");
		      assignmentdb.assignmentadd(assignment1,course);
		      assignmentdb.assignmentadd(assignment2,course);
		      filetouploaddb.filetouploadadd(filetoupload, course);
	}
	else if(action.equals("Check Availability")){
		session = request.getSession();
		System.out.println(action+"inside");
		System.out.println(session.getAttribute("NETID")+"inside");
		if(session.getAttribute("NETID")==null){
		doGet(request,response);}
				List<String> coursenames = new ArrayList<>();
				String coursename=request.getParameter("addcoursename");
				coursenames=coursedb.getCoursenames();
				if(coursenames.contains(coursename)){
					request.setAttribute("availablecoursename", "The Course is Already Available.Select a Different Course");
				}else
				{
					request.setAttribute("availablecoursename", "The Course is New.Go Ahead With the Process");
				}request.getRequestDispatcher("AddCourse.jsp").forward(request, response);
			}
			}else{
				request.setAttribute("coursecount", "Instructor cannot add more than 3 courses");
				request.getRequestDispatcher("AddCourse.jsp").forward(request, response);
			}    
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
			
	}

}
