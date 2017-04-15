package com.seterm.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
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
 * Servlet implementation class ViewPdf
 */
@WebServlet("/ViewPdf")
public class ViewPdf extends HttpServlet {
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
			assignmentdb=new Assignmentdbimpl(dataSource);
			filetouploaddb=new Filetouploaddbimpl(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			session = request.getSession();
			if(session.getAttribute("NETID")==null){
			doGet(request,response);}
			String fileid= request.getParameter("fileid");
		//String coursename=session.getAttribute("coursepage_coursename");
		String coursename = request.getParameter("coursename");
		System.out.println(fileid+"----PINGU-----"+coursename);
		
		//response.setContentType("application/pdf");
		
		InputStream  inputstream=filetouploaddb.getCourseBlobone(coursename,fileid);
		String contenttype = filetouploaddb.getCourseBlobcontent(coursename,fileid);
		 if(contenttype.equals("application/pdf"))
		 {response.setContentType("application/pdf");
		 OutputStream output = response.getOutputStream();
			
			//response.setContentType("application/pdf");
			//response.setContentType("video/mp4");application/vnd.ms-excel
			//response.setContentType("application/vnd.ms-excel");
			int bytesRead = -1;
	          byte[] buffer = new byte[BUFFER_SIZE];
	          while ((bytesRead = inputstream.read(buffer)) != -1) {
	        	  output.write(buffer, 0, bytesRead);
	          }
	          
	          output.flush();}
 else if(contenttype.equals("video/mp4"))
		 {response.setContentType("video/mp4");
		 OutputStream output = response.getOutputStream();
			
			//response.setContentType("application/pdf");
			//response.setContentType("video/mp4");application/vnd.ms-excel
			//response.setContentType("application/vnd.ms-excel");
			int bytesRead = -1;
	          byte[] buffer = new byte[BUFFER_SIZE];
	          while ((bytesRead = inputstream.read(buffer)) != -1) {
	        	  output.write(buffer, 0, bytesRead);
	          }
	          
	          output.flush();}
		System.out.println("---------CONTENT TYPE--------"+contenttype+"--------CONTENT TYPE----------");
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/View_Pdf.jsp");
		//dispatcher.forward(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	

}
