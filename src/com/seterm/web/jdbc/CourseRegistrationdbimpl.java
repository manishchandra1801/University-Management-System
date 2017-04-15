package com.seterm.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.seterm.web.model.Assignment;
import com.seterm.web.model.Course;

public class CourseRegistrationdbimpl  implements CourseRegistrationdb{
	private DataSource dataSource;
	
	

	public CourseRegistrationdbimpl(DataSource theDataSource) throws Exception{
		dataSource = theDataSource;
	
	}
	
	public int courseregistrationadd(String netid,String coursename) throws Exception{
		int i=0,j=0;
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		Connection myConn1 = null;
		Statement myStmt1 = null;
		ResultSet myRs1 = null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String courseidpass="";
		List<String> assidlist=new ArrayList<>();
		
		//System.out.println(coursedb.getcourseid(course));
		try {
			
			myConn1 = dataSource.getConnection();		
			String sql1 = "select courseid from course where coursename='"+coursename+"'";			
			myStmt1 = myConn1.createStatement();						
			myRs1 = myStmt1.executeQuery(sql1);			
				while (myRs1.next()) {
					courseidpass = (String)myRs1.getString("courseid");						
						}
			
			PreparedStatement ps=c.prepareStatement("insert into courseregistration(Studentid,Courseid) values(?,?)");
			ps.setString(1,netid);
			ps.setString(2,courseidpass);		
			i=ps.executeUpdate();
			
			
			myConn = dataSource.getConnection();		
			String sql = "select assignmentid from assignment where courseid='"+courseidpass+"'";			
			myStmt = myConn.createStatement();						
			myRs = myStmt.executeQuery(sql);			
				while (myRs.next()) {
				String	assignmentid = myRs.getString("assignmentid");		
					assidlist.add(assignmentid);
					PreparedStatement ps1=c.prepareStatement("insert into studentassignmentgrade(Studentid,assignmentid,courseid) values(?,?,?)");
					ps1.setString(1,netid);
					ps1.setString(2,assignmentid);	
					ps1.setString(3,courseidpass);
					j=ps1.executeUpdate();
						}
			
			
			
			
			
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
		return i;
		
	}
	
	
	
	public int updateWholeGrade(String netid, String coursepage_coursename, int ans)  throws Exception{
		int i=0;
		List<String> gardelist = new ArrayList<>();
				Connection c;
				//DBconnect db=new DBconnect();
				c = dataSource.getConnection();
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
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
						 
						
					// get a connection
					
					
					// create sql statement
					
				   
					
					
					
					
					PreparedStatement ps=c.prepareStatement("Update courseregistration set grade='"+ans+"' where studentid='"+netid+"' and courseid='"+courseid+"'");
					i=ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					try {
						c.close();
						close(myConn, myStmt, myRs);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return i;
	}
	
	
	
	
	
	public Assignment getStudentAssignment(String netid,String assignmentidpass)throws Exception{
		
		Assignment assignment=null;
	
		Connection myConn1 = null;
		Statement myStmt1 = null;
		ResultSet myRs1 = null;
		//String courseid="";
		try {
	myConn1 = dataSource.getConnection();
			
			// create sql statement
			String sql1 = "select * from studentassignmentgrade where studentid='"+netid+"' and assignmentid='"+assignmentidpass+"'";
			
			myStmt1 = myConn1.createStatement();
			//System.out.println("-------++++++++-----ppppp-----------");
			// execute query
			myRs1 = myStmt1.executeQuery(sql1);
			while (myRs1.next()) {
				
				String tempgrade = myRs1.getString("studentgrade");
				String assignmentid = myRs1.getString("assignmentid");
				// retrieve data from result set row
				//int id = myRs.getInt("id");
				
			
				assignment=new Assignment();
				assignment.setGrade(tempgrade);
				assignment.setAssignmentid(assignmentid);
				System.out.println("-------++++++++-----ppppp-----------");
			}
			
			return assignment;		
		}
		finally {
			// close JDBC objects
			
			close(myConn1, myStmt1, myRs1);
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
	
	
	
	public int addgrade(String netid,String assignmentid, String string,String coursename)  throws Exception{
int i=0;
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String courseid="";
		//System.out.println(coursedb.getcourseid(course));
		try {
			myConn = dataSource.getConnection();		
			String sql = "select courseid from course where coursename='"+coursename+"'";			
			myStmt = myConn.createStatement();						
			myRs = myStmt.executeQuery(sql);			
				while (myRs.next()) {
				courseid = (String)myRs.getString("courseid");						
						}
			
			
			PreparedStatement ps=c.prepareStatement("update studentassignmentgrade set studentgrade="+string+" where assignmentid="+assignmentid+" and studentid='"+netid+"' and courseid='"+courseid+"'");
			                                
			
			
			
			
			
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				c.close();
				close(myConn, myStmt, myRs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}
}
