package com.seterm.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seterm.web.model.Course;
import com.seterm.web.model.Student;



public class Coursedbimpl implements Coursedb {
	private DataSource dataSource;
	private HttpSession session;
	private HttpServletRequest request;
	public Coursedbimpl(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public int courseadd(Course course) throws Exception{
		int i=0;
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		
		try {
			PreparedStatement ps=c.prepareStatement("insert into course(coursename,description,instructorid) values(?,?,?)");
			ps.setString(1,course.getCoursename());
			ps.setString(2, course.getDescription());
			ps.setString(3, course.getInstructorid());
			
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
		
	}
	
	public String getcourseid(Course course) throws Exception{
		String i="";
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r=s.executeQuery("select courseid from course where coursename='"+course.getCoursename()+"'");
			//PreparedStatement ps=c.prepareStatement("select courseid from course where coursename='"+course.getCoursename()+"'");
			while(r.next())
			{
				i=r.getString("courseid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
		
	}
	
	
	public String getcourseid(String coursename) throws Exception{
		String i="";
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r=s.executeQuery("select courseid from course where coursename='"+coursename+"'");
			//PreparedStatement ps=c.prepareStatement("select courseid from course where coursename='"+course.getCoursename()+"'");
			while(r.next())
			{
				i=r.getString("courseid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
		
	}
	public int getcoursecount(String instructor) throws Exception{
int i=0;
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		Statement s = null;
		ResultSet r = null;
		try {
			s = c.createStatement();
			r=s.executeQuery("select count(courseid) as count from course where instructorid='"+instructor+"'");
			//PreparedStatement ps=c.prepareStatement("select courseid from course where coursename='"+course.getCoursename()+"'");
			while(r.next())
			{
				i=r.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}
	
	
public List<String> getCoursenames() throws Exception {
		
		List<String> coursenames = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from course order by coursename";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				//int id = myRs.getInt("id");
				String coursename = myRs.getString("coursename");
				//String lastName = myRs.getString("last_name");
				//String email = myRs.getString("email");
				
				// create new student object
				//Student tempStudent = new Student(id, firstName, lastName, email);
				
				// add it to the list of students
				coursenames.add(coursename);				
			}
			
			return coursenames;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

	try {
		if (myRs != null) {
			myRs.close();
		}
		
		if (myStmt != null) {
			myStmt.close();
		}
		
		if (myConn != null) {
			myConn.close();   // doesn't really close it ... just puts back in connection pool
		}
	}
	catch (Exception exc) {
		exc.printStackTrace();
	}
}
public List<String> getFacultyCoursenames(String netid) throws Exception{
	List<String> coursenames = new ArrayList<>();
	
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		// get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql = "select coursename from course where instructorid='"+netid+"' order by coursename";
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		while (myRs.next()) {
			
			// retrieve data from result set row
			//int id = myRs.getInt("id");
			String coursename = myRs.getString("coursename");
			System.out.println(coursename+"tingu");
			//String lastName = myRs.getString("last_name");
			//String email = myRs.getString("email");
			
			// create new student object
			//Student tempStudent = new Student(id, firstName, lastName, email);
			
			// add it to the list of students
			coursenames.add(coursename);				
		}
		
		return coursenames;		
	}
	finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);
	}		
	
}


public List<String> getStudentCoursenames(String netid) throws Exception{
List<String> coursenames = new ArrayList<>();
	
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		// get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql = "select course.courseid,course.coursename,grade from courseregistration inner join student on courseregistration.studentid=student.id inner join course on courseregistration.courseid=course.courseid where courseregistration.studentid='"+netid+"'";
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		while (myRs.next()) {
			
			// retrieve data from result set row
			//int id = myRs.getInt("id");
			String coursename = myRs.getString("coursename");
			System.out.println(coursename+"tingu");
			//String lastName = myRs.getString("last_name");
			//String email = myRs.getString("email");
			
			// create new student object
			//Student tempStudent = new Student(id, firstName, lastName, email);
			
			// add it to the list of students
			coursenames.add(coursename);				
		}
		
		return coursenames;		
	}
	finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);
	}		
}



public List<Course> getCourseNamesDescription()throws Exception{
	List<Course> coursenames = new ArrayList<>();
	Course course=null;
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		// get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql = "select * from course";
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		while (myRs.next()) {
			String description = myRs.getString("description");
			// retrieve data from result set row
			//int id = myRs.getInt("id");
			String tempcoursename = myRs.getString("coursename");
			//String lastName = myRs.getString("last_name");
			//String email = myRs.getString("email");
			course=new Course();
			course.setCoursename(tempcoursename);
			course.setDescription(description);
			// create new student object
			//Student tempStudent = new Student(id, firstName, lastName, email);
			coursenames.add(course);
			// add it to the list of students
							
		}
		
		return coursenames;		
	}
	finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);
	}		

}

public Course getCourseNamesDescription(String coursename) throws Exception{
	List<String> coursenames = new ArrayList<>();
	Course course=null;
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		// get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql = "select * from course where Coursename='"+coursename+"'";
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		while (myRs.next()) {
			String description = myRs.getString("description");
			// retrieve data from result set row
			//int id = myRs.getInt("id");
			String tempcoursename = myRs.getString("coursename");
			//String lastName = myRs.getString("last_name");
			//String email = myRs.getString("email");
			course=new Course();
			course.setCoursename(tempcoursename);
			course.setDescription(description);
			// create new student object
			//Student tempStudent = new Student(id, firstName, lastName, email);
			
			// add it to the list of students
			coursenames.add(coursename);				
		}
		
		return course;		
	}
	finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);
	}		
	
}
	
}
