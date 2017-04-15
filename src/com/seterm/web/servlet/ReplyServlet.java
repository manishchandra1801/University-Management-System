package com.seterm.web.servlet;

import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.seterm.web.jdbc.Assignmentdb;
import com.seterm.web.jdbc.Assignmentdbimpl;
import com.seterm.web.jdbc.Commentdb;
import com.seterm.web.jdbc.Commentdbimpl;
import com.seterm.web.jdbc.Coursedb;
import com.seterm.web.jdbc.Coursedbimpl;
import com.seterm.web.jdbc.Filetouploaddb;
import com.seterm.web.jdbc.Filetouploaddbimpl;
import com.seterm.web.model.Assignment;
import com.seterm.web.model.Course;
import com.seterm.web.model.Filetoupload;
import com.seterm.web.model.Reply;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {
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
			
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String replierid_faculty=null;
			String replierid_student=null;
	
			session = request.getSession();
			if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}
			
			String commentid=request.getParameter("commentid");
			String reply=request.getParameter("reply");
			System.out.println(reply);
			System.out.println(session.getAttribute("Role")+"Asshole");
			System.out.println(session.getAttribute("Netid")+"Asshole");
			if(session.getAttribute("Role").equals("Faculty")){
			replierid_faculty=(String) session.getAttribute("NETID");}
			else if(session.getAttribute("Role").equals("Student")){
			replierid_student=(String) session.getAttribute("NETID");
			}
			String Role=(String) session.getAttribute("Role");
			
		
		      
		     Reply tempreply=new Reply();
		     tempreply.setCommentid(Integer.parseInt(commentid));
		     tempreply.setReply(reply);
		     tempreply.setReplierid_faculty(replierid_faculty);
		     tempreply.setReplierid_student(replierid_student);
		     
		     System.out.println(tempreply.getCommentid()+"pppppppppppppppppppppppppppppppppppp");
		      System.out.println(tempreply.getReply());
		      System.out.println(tempreply.getReplierid_faculty());
		      System.out.println(tempreply.getReplierid_student());
		     
		      
		
		     commentdb.replyadd(tempreply,Role);
		     System.out.println( session.getAttribute("session_coursename")+"fromReplyServlet");
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
