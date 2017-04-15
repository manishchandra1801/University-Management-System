package com.seterm.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentAssignmentGradedbimpl implements StudentAssignmentGradedb {
	private DataSource dataSource;
	
	

	public StudentAssignmentGradedbimpl(DataSource theDataSource) throws Exception{
		dataSource = theDataSource;
	
	}
	
	
	public List<String> addWholeGrade(String assignmentidpass, String netid, String coursepage_coursename) throws Exception{
		int i=0;
		List<String> gardelist = new ArrayList<>();
				Connection c;
				//DBconnect db=new DBconnect();
				c = dataSource.getConnection();
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				Connection myConn1 = null;
				Statement myStmt1 = null;
				ResultSet myRs1 = null;
				String courseid="";

				
				//System.out.println(coursedb.getcourseid(course));
				try {	
					myConn = dataSource.getConnection();		
					String sql = "select courseid from course where coursename='"+coursepage_coursename+"'";			
					myStmt = myConn.createStatement();						
					myRs = myStmt.executeQuery(sql);			
						while (myRs.next()) {
						courseid = (String)myRs.getString("courseid");						
								}
						 
						System.out.println("COURSEID"+courseid);
					// get a connection
					myConn1 = dataSource.getConnection();
					
					// create sql statement
					String sql1 = "select studentgrade from studentassignmentgrade where studentid='"+netid+"' and courseid='"+courseid+"'";			
					myStmt1 = myConn1.createStatement();		
					// execute query
					myRs1 = myStmt1.executeQuery(sql1);
					while (myRs1.next()) {				
						String tempgrade = myRs1.getString("studentgrade");
						// retrieve data from result set row
						//int id = myRs.getInt("id");			
						gardelist.add(tempgrade);			
					}
					
				   
					
					
					
					
					//PreparedStatement ps=c.prepareStatement("Update courseregistration set grade='"+grade+"' where studentid='"+netid+"' and courseid='"+courseid+"'");
					//i=ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					try {
						c.close();
						close(myConn, myStmt, myRs);
						close(myConn1, myStmt1, myRs1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return gardelist;
			
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

