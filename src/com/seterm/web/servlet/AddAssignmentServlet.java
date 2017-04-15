package com.seterm.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
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
import com.seterm.web.jdbc.StudentAssignmentGradedb;
import com.seterm.web.jdbc.StudentAssignmentGradedbimpl;
import com.seterm.web.model.Assignment;
import com.seterm.web.model.GradeCenter;


/**
 * Servlet implementation class AddAssignmentServlet
 */
@WebServlet("/AddAssignmentServlet")
public class AddAssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	private HttpSession session;
	private Coursedb coursedb;
	private CourseRegistrationdb courseregistrationdb;
	private HttpServletRequest request;
	private Assignmentdb assignmentdb;
	private String column="";
	private StudentAssignmentGradedb studentAssignmentGradedb;
	int i=0;
	int j=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			
			courseregistrationdb=new CourseRegistrationdbimpl(dataSource);
			assignmentdb=new Assignmentdbimpl(dataSource);
			studentAssignmentGradedb=new StudentAssignmentGradedbimpl(dataSource);
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
	    	int Ans1=0;
	    	int Ans2=0;
	    	int Ans=0;
	    	String assignmentid=request.getParameter("assignmentid");
	    	String netid=(String) session.getAttribute("NETID");
	    	//String coursepage_coursename=(String) session.getAttribute("NETID");
	    	List<String> gradeslist = new ArrayList<>();	
	    	
	    	System.out.println("abuvgyhgfygvctygusdfh----"+assignmentid+"-------RSDfcrwDFC");
	    	String coursepage_coursename=(String) session.getAttribute("coursepage_coursename");
	    	System.out.println("abuvgyhgfygvctygusdfh----"+coursepage_coursename+"-------RSDfcrwDFC");
	    	System.out.println("abuvgyhgfygvctygusdfh----"+netid+"-------RSDfcrwDFC");
			Assignment assignment= assignmentdb.getCourseAssignmentAnswers(assignmentid);
	    	String answer=assignment.getAnswer();
	    	String[] studentanswers=request.getParameterValues("favoriteLanguage");
	    	String[] answers = answer.split(";");
	    	System.out.println(Arrays.toString(studentanswers)+"-----lady");
	    	System.out.println(Arrays.toString(answers)+"---gaga");
	    	if(Arrays.asList(studentanswers).containsAll(Arrays.asList(answers))){
	    		System.out.println("**lady---gaga**");
	    	 i=courseregistrationdb.addgrade(netid,assignmentid,"10",coursepage_coursename);
	    	 //TimeUnit.SECONDS.sleep(10);
	    	 gradeslist=studentAssignmentGradedb.addWholeGrade(assignmentid,netid,coursepage_coursename);
	    	 System.out.println("Monica is mad");
	    	 String ans1=gradeslist.get(0);
	    	 String ans2=gradeslist.get(1);
	    	 if(ans1!=null){
	    		 Ans1=Integer.parseInt(ans1);}
	    	 if(ans2!=null){
	    	 Ans2=Integer.parseInt(ans2);}
	    	 Ans=Ans1+Ans2;
	    	 System.out.println("Ans1"+Ans1);
		    	System.out.println("Ans2"+Ans2);
		    	System.out.println("Ans"+Ans);
		    
	    	 j=courseregistrationdb.updateWholeGrade(netid,coursepage_coursename,Ans);
	    	 request.getRequestDispatcher("ViewCoursesServlet?gotopost="+"POST"+"&coursename="+coursepage_coursename+"").forward(request, response);
	    	}else{
	    		System.out.println("puss**lady---gaga**puss");
	    		 i=courseregistrationdb.addgrade(netid,assignmentid,"0",coursepage_coursename);
	    		 TimeUnit.SECONDS.sleep(10);
	    		 gradeslist=studentAssignmentGradedb.addWholeGrade(assignmentid,netid,coursepage_coursename);
	    		 String ans1=gradeslist.get(0);
		    	 String ans2=gradeslist.get(1);
		    	 if(ans1!=null){
		    		 Ans1=Integer.parseInt(ans1);}
		    	 if(ans2!=null){
		    	 Ans2=Integer.parseInt(ans2);}
		    	 Ans=Ans1+Ans2;
		    	 j=courseregistrationdb.updateWholeGrade(netid,coursepage_coursename,Ans);
		    	 request.getRequestDispatcher("ViewCoursesServlet?gotopost="+"POST"+"&coursename="+coursepage_coursename+"").forward(request, response);
	    	}
	    		
	    	
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
