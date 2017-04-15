package com.seterm.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.seterm.web.model.UserRegistration;


public class UserRegistrationdbimpl implements UserRegistrationdb{
	private DataSource dataSource;

	public UserRegistrationdbimpl(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	  
	public int userRegistration(UserRegistration userregistrarion) throws Exception{
		int i=0;
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		
		try {
			PreparedStatement ps=c.prepareStatement("insert into registration values(?,?,?,?,?,?,?)");
			ps.setString(1,userregistrarion.getNetID());
			ps.setString(2, userregistrarion.getpwd());
			ps.setString(3, userregistrarion.getFirstname());
			ps.setString(4, userregistrarion.getLastname());
			ps.setString(5, userregistrarion.getRole()); 
			ps.setString(6,userregistrarion.getYearofjoining());
			ps.setString(7, userregistrarion.getProgram());
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
	
public List<String> getNetidnames() throws Exception {
		
		List<String> coursenames = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from registration order by netid";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				//int id = myRs.getInt("id");
				String coursename = myRs.getString("netid");
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
}
