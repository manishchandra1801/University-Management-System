package com.seterm.web.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.seterm.web.jdbc.Facultydb;
import com.seterm.web.jdbc.Facultydbimpl;
import com.seterm.web.jdbc.Studentdb;
import com.seterm.web.jdbc.Studentdbimpl;
import com.seterm.web.jdbc.UserRegistrationdb;
import com.seterm.web.jdbc.UserRegistrationdbimpl;
import com.seterm.web.model.Faculty;
import com.seterm.web.model.UserRegistration;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRegistrationdb userregistrationdb ;
	private Facultydb facultydb;
	private Studentdb studentdb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
    private HttpSession session;
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			userregistrationdb = new UserRegistrationdbimpl(dataSource);
			facultydb=new Facultydbimpl(dataSource);
			studentdb=new Studentdbimpl(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		String Netid = request.getParameter("NetID");
		String firstname = request.getParameter("firstname");
		
		String lastname = request.getParameter("Lastname");
		String password = request.getParameter("pwd");
		String role = request.getParameter("Role");
		String year = request.getParameter("yearofjoiningprogram");
		String program = request.getParameter("Program");
		String button = request.getParameter("button");
		
		/////////////////////////session = request.getSession();
    	//session.setAttribute("studentId", "as132736");
    
    //	if(session.getAttribute("NETID")==null){    		
    	//	request.getRequestDispatcher("RegistrationPage.jsp").forward(request, response);
    	//}
		if(button.equals("Register")){
		if(role.equals("Faculty")){
		Faculty faculty=new Faculty();
		faculty.setFirstName(firstname);
		faculty.setLastName(lastname);
		faculty.setId(Netid);
		facultydb.addFaculty(faculty);
		}
		
		
		if(role.equals("Student")){
			
			studentdb.addmyStudent(Netid,firstname,lastname);
			}
		
		UserRegistration r = new UserRegistration();
	    r.setNetID(Netid);
		r.setFirstname(firstname);
		r.setLastname(lastname);
		r.setpwd(password);
		r.setRole(role);
	    r.setYearofjoining(year);
	    r.setProgram(program);
		
		
		int u= userregistrationdb.userRegistration(r);
		
		if(u!=0)
		{
			
			
			request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
		}
		else{
			request.setAttribute("error", "Not Registered!!");
			request.getRequestDispatcher("RegistrationPage.jsp").forward(request, response);
			
		}
		
		System.out.println(Netid);
		//System.out.println(lastname);
		//System.out.println(firstname);
		//System.out.println(role);
		//System.out.println(year);
		//System.out.println(program);
		doGet(request, response);}else if(button.equals("check availability")){
			
			List<String> usernames = new ArrayList<>();
			String netid=request.getParameter("NetID");
			usernames=userregistrationdb.getNetidnames();
			if(usernames.contains(netid)){
				request.setAttribute("availablecoursename", "The Netid is Already Available.Select a Different Course");
			}else
			{
				request.setAttribute("availablecoursename", "The Netid is New.Go Ahead With the Process");
			}request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
	
		
	}
	catch (Exception exc) {
		throw new ServletException(exc);
	}
	}
	
	
}
