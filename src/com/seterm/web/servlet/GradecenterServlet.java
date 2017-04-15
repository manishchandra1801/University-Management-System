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
import com.seterm.web.jdbc.Gradecenterdb;
import com.seterm.web.jdbc.Gradecenterdbimpl;
import com.seterm.web.model.GradeCenter;

/**
 * Servlet implementation class GradcenterServlet
 */
@WebServlet("/GradcenterServlet")
public class GradecenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	private HttpSession session;
	private Coursedb coursedb;
	private Gradecenterdb gradecenterdb;
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
			
			gradecenterdb=new Gradecenterdbimpl(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradecenterServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			String theCommand = request.getParameter("command");
			System.out.println(theCommand+"jjjjjjjj");
			column=(String)request.getParameter("column");
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "load";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "load":
				loadStudents(request, response);
				break;
				
			case "firstname":
				firstnameSort(request, response);
				break;
				
			case "lastname":
				lastnameSort(request, response);
				break;
				
			case "courseid":
				courseidSort(request, response);
				break;
			
			case "grade":
				gradeSort(request, response);
				break;
				
			case "CourseNamestudent":
				courseidSortstudent(request, response);
				break;
			
			case "gradestudent":
				gradeSortstudent(request, response);
				break;
				
			default:
				loadStudents(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		/*try {
			// read the "command" parameter
			session = request.getSession();
	    	//session.setAttribute("studentId", "as132736");
	    
	    	if(session.getAttribute("NETID")==null){    		
	    		request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
	    	}
	    	GradeCenter tempGradeCenter = new GradeCenter();
	    	List<GradeCenter> gradecenterlist = new ArrayList<>();			
			gradecenterlist=gradecenterdb.getGradecenter();
			System.out.println(tempGradeCenter.getFirstname()+"jjjjjjjj");
			System.out.println(tempGradeCenter.getLastname()+"jjjjjjjj");
			System.out.println(tempGradeCenter.getCourseid()+"jjjjjjjj");
			System.out.println(tempGradeCenter.getGrade()+"jjjjjjjj");
			request.setAttribute("Gradecenter_List", gradecenterlist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter.jsp");
			dispatcher.forward(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}*/
	}
	
	
	private void gradeSortstudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		String netid=(String) session.getAttribute("NETID");
		List<GradeCenter> gradecenterlist = new ArrayList<>();			
		gradecenterlist=gradecenterdb.getGradecentersortstudent(column,netid);
		
		request.setAttribute("Gradecenter_List", gradecenterlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter_student.jsp");
		dispatcher.forward(request, response);
	}
	
	private void courseidSortstudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		String netid=(String) session.getAttribute("NETID");
		List<GradeCenter> gradecenterlist = new ArrayList<>();			
		gradecenterlist=gradecenterdb.getGradecentersortstudentCoursename(column,netid);
		
		request.setAttribute("Gradecenter_List", gradecenterlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter_student.jsp");
		dispatcher.forward(request, response);
	}
	
	private void gradeSort(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		String netid=(String) session.getAttribute("NETID");
		List<GradeCenter> gradecenterlist = new ArrayList<>();			
		gradecenterlist=gradecenterdb.getGradecenter(column,netid);
		
		request.setAttribute("Gradecenter_List", gradecenterlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter.jsp");
		dispatcher.forward(request, response);
	}
	private void courseidSort(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		String netid=(String) session.getAttribute("NETID");
		List<GradeCenter> gradecenterlist = new ArrayList<>();			
		gradecenterlist=gradecenterdb.getGradecenter(column,netid);
		
		request.setAttribute("Gradecenter_List", gradecenterlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter.jsp");
		dispatcher.forward(request, response);
	}
	private void lastnameSort(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		String netid=(String) session.getAttribute("NETID");
		List<GradeCenter> gradecenterlist = new ArrayList<>();			
		gradecenterlist=gradecenterdb.getGradecenter(column,netid);
		
		request.setAttribute("Gradecenter_List", gradecenterlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter.jsp");
		dispatcher.forward(request, response);
	}
	private void firstnameSort(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		String netid=(String) session.getAttribute("NETID");
		List<GradeCenter> gradecenterlist = new ArrayList<>();			
		gradecenterlist=gradecenterdb.getGradecenter(column,netid);
		
		request.setAttribute("Gradecenter_List", gradecenterlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter.jsp");
		dispatcher.forward(request, response);
	}
	private void loadStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String netid=(String) session.getAttribute("NETID");
		if(session.getAttribute("Role").equals("Faculty")){
		List<GradeCenter> gradecenterlist = new ArrayList<>();		
		gradecenterlist=gradecenterdb.getGradecenterinstructor(netid);		
		request.setAttribute("Gradecenter_List", gradecenterlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter.jsp");
		dispatcher.forward(request, response);}
		else if(session.getAttribute("Role").equals("Student")){
		List<GradeCenter> gradecenterlist_student = new ArrayList<>();	
		gradecenterlist_student=gradecenterdb.getGradecenterstudent(netid);
		request.setAttribute("Gradecenter_List", gradecenterlist_student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gradecenter_student.jsp");
		dispatcher.forward(request, response);
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
