package com.seterm.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.seterm.web.model.Course;
import com.seterm.web.model.Faculty;
import com.seterm.web.model.Filetoupload;
import com.seterm.web.model.Student;

public class Facultydbimpl implements Facultydb{

	private DataSource dataSource;

	public Facultydbimpl(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	public void addFaculty(Faculty theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into faculty "
					   + "(faculty_netid,faculty_firstname, faculty_lastname) "
					   + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, theStudent.getId());
			myStmt.setString(2, theStudent.getFirstName());
			myStmt.setString(3, theStudent.getLastName());
			
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
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
