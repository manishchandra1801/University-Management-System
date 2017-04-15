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

import com.seterm.web.jdbc.Logindb;
import com.seterm.web.jdbc.Studentdbimpl;
import com.seterm.web.model.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Logindb logindb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
			logindb = new Logindb(dataSource);
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			
		String NETID=request.getParameter("NETID");
		String Password=request.getParameter("Password");
		String Role=request.getParameter("Role");
		
		System.out.println(NETID+"pkkkkkkk-------");
		
    	//session.setAttribute("studentId", "as132736");
    
    	
	
		Login l=new Login();	
		l.setNETID(NETID);
		l.setPassword(Password);
		l.setRole(Role);
		
		
		boolean c= logindb.userLogin(l);
		
		if(c)
		{
			HttpSession session =request.getSession();
			session.setAttribute("NETID", NETID);
			session.setAttribute("Role", Role);
			
		request.getRequestDispatcher("menu.jsp").forward(request, response);	
		}
		else{
			request.setAttribute("error", "Not Registered!!");
			request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
		}
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
		

}
