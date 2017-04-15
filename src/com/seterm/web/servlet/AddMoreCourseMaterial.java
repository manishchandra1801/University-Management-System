package com.seterm.web.servlet;

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
 * Servlet implementation class AddMoreCourseMaterial
 */
@WebServlet("/AddMoreCourseMaterial")
@MultipartConfig(maxFileSize = 161777215)
public class AddMoreCourseMaterial extends HttpServlet {
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
			
			filetouploaddb=new Filetouploaddbimpl(dataSource);
			
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
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
			
			String coursename=(String) session.getAttribute("coursepage_coursename");
			String filename=request.getParameter("materialname");
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
		    
		      Filetoupload filetoupload=new Filetoupload();
		      
		      filetoupload.setFilename(filename);
		      filetoupload.setInputStream(inputStream);
		      filetoupload.setContenttype(filePart.getContentType());
		      
		      
		      filetouploaddb.filetouploadadd(filetoupload, coursename);
	
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
			
	
	}

}
