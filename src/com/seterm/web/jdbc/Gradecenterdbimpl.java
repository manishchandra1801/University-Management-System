package com.seterm.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.seterm.web.model.GradeCenter;
import com.seterm.web.model.Student;

public class Gradecenterdbimpl implements Gradecenterdb{
	private DataSource dataSource;

	public Gradecenterdbimpl(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<GradeCenter> getGradecenterinstructor(String netid) throws Exception{
List<GradeCenter> gradecenter = new ArrayList<>();
GradeCenter tempGradeCenter=null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select First_name,last_name,course.courseid,grade from courseregistration inner join student on courseregistration.studentid=student.id inner join course on courseregistration.courseid=course.courseid where course.instructorid='"+netid+"'";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				tempGradeCenter = new GradeCenter();
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String courseid = myRs.getString("Courseid");
				String grade = myRs.getString("grade");
				if(myRs.wasNull())
					tempGradeCenter.setGrade("-");
					else
					tempGradeCenter.setGrade(grade);
				System.out.println(firstName);
				System.out.println(lastName);
				System.out.println(courseid);
				System.out.println(grade);
				// create new student object
				
				
				tempGradeCenter.setFirstname(firstName);
				tempGradeCenter.setCourseid(courseid);
				tempGradeCenter.setLastname(lastName);
				
				
				
				System.out.println(tempGradeCenter.getFirstname());
				System.out.println(tempGradeCenter.getLastname());
				System.out.println(tempGradeCenter.getCourseid());
				System.out.println(tempGradeCenter.getGrade());
				System.out.println("bhos-d-k");
				// add it to the list of students
				gradecenter.add(tempGradeCenter);				
			}
			
			return gradecenter;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	
	
	
	
	
	
	public List<GradeCenter> getGradecenter(String column, String netid) throws Exception{
		List<GradeCenter> gradecenter = new ArrayList<>();
		GradeCenter tempGradeCenter=null;
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				try {
					// get a connection
					myConn = dataSource.getConnection();
					
					// create sql statement
					String sql = "select First_name,last_name,course.courseid,grade from courseregistration inner join student on courseregistration.studentid=student.id inner join course on courseregistration.courseid=course.courseid where course.instructorid='"+netid+"' order by "+column+"";
					
					myStmt = myConn.createStatement();
					
					// execute query
					myRs = myStmt.executeQuery(sql);
					
					// process result set
					while (myRs.next()) {
						
						// retrieve data from result set row
						tempGradeCenter = new GradeCenter();
						String firstName = myRs.getString("first_name");
						String lastName = myRs.getString("last_name");
						String courseid = myRs.getString("Courseid");
						String grade = myRs.getString("grade");
						if(myRs.wasNull())
							tempGradeCenter.setGrade("-");
							else
							tempGradeCenter.setGrade(grade);
						// create new student object
						
						
						tempGradeCenter.setFirstname(firstName);
						tempGradeCenter.setCourseid(courseid);
						tempGradeCenter.setLastname(lastName);
						//tempGradeCenter.setGrade(grade);
						
						
						// add it to the list of students
						gradecenter.add(tempGradeCenter);				
					}
					
					return gradecenter;		
				}
				finally {
					// close JDBC objects
					close(myConn, myStmt, myRs);
				}		
			}
	
	
	
	public List<GradeCenter> getGradecenterstudent(String netid) throws Exception{
		List<GradeCenter> gradecenter = new ArrayList<>();
		GradeCenter tempGradeCenter=null;
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
						tempGradeCenter = new GradeCenter();
						String coursename = myRs.getString("coursename");
						String courseid = myRs.getString("Courseid");
						String grade = myRs.getString("grade");
						if(myRs.wasNull())
							tempGradeCenter.setGrade("-");
							else
							tempGradeCenter.setGrade(grade);
						
						System.out.println(courseid);
						System.out.println(grade);
						// create new student object
						
						
						
						tempGradeCenter.setCourseid(courseid);
						tempGradeCenter.setCoursename(coursename);
						
						
						
						
						System.out.println(tempGradeCenter.getFirstname());
						System.out.println(tempGradeCenter.getLastname());
						System.out.println(tempGradeCenter.getCourseid());
						System.out.println(tempGradeCenter.getGrade());
						System.out.println("bhos-d-k");
						// add it to the list of students
						gradecenter.add(tempGradeCenter);				
					}
					
					return gradecenter;		
				}
				finally {
					// close JDBC objects
					close(myConn, myStmt, myRs);
				}		
			}
	
	
	public List<GradeCenter> getGradecentersortstudent(String column ,String netid) throws Exception{
		List<GradeCenter> gradecenter = new ArrayList<>();
		GradeCenter tempGradeCenter=null;
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				try {
					// get a connection
					myConn = dataSource.getConnection();
					
					// create sql statement
					String sql = "select course.courseid,course.coursename,grade from courseregistration inner join student on courseregistration.studentid=student.id inner join course on courseregistration.courseid=course.courseid where courseregistration.studentid='"+netid+"' order by "+column+"";
					
                   myStmt = myConn.createStatement();
					
					// execute query
					myRs = myStmt.executeQuery(sql);
					
					// process result set
					while (myRs.next()) {
						
						// retrieve data from result set row
						tempGradeCenter = new GradeCenter();
						String coursename = myRs.getString("coursename");
						String courseid = myRs.getString("Courseid");
						String grade = myRs.getString("grade");
						if(myRs.wasNull())
							tempGradeCenter.setGrade("-");
							else
							tempGradeCenter.setGrade(grade);
						
						System.out.println(courseid);
						System.out.println(grade);
						// create new student object
						
						
						
						tempGradeCenter.setCourseid(courseid);
						tempGradeCenter.setCoursename(coursename);
						
						
						
						
						System.out.println(tempGradeCenter.getFirstname());
						System.out.println(tempGradeCenter.getLastname());
						System.out.println(tempGradeCenter.getCourseid());
						System.out.println(tempGradeCenter.getGrade());
						System.out.println("bhos-d-k");
						// add it to the list of students
						gradecenter.add(tempGradeCenter);				
					}
					
					return gradecenter;		
				}
				finally {
					// close JDBC objects
					close(myConn, myStmt, myRs);
				}
	}
	
	
	public List<GradeCenter> getGradecentersortstudentCoursename(String column, String netid)throws Exception{
		List<GradeCenter> gradecenter = new ArrayList<>();
		GradeCenter tempGradeCenter=null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			
				
				myConn = dataSource.getConnection();
				
				// create sql statement
				String sql = "select course.courseid,course.coursename,grade from courseregistration inner join student on courseregistration.studentid=student.id inner join course on courseregistration.courseid=course.courseid where courseregistration.studentid='"+netid+"' order by "+column+"";
				
               myStmt = myConn.createStatement();
				
				// execute query
				myRs = myStmt.executeQuery(sql);
				
				// process result set
				while (myRs.next()) {
					
					// retrieve data from result set row
					tempGradeCenter = new GradeCenter();
					String coursename = myRs.getString("coursename");
					String courseid = myRs.getString("Courseid");
					String grade = myRs.getString("grade");
					if(myRs.wasNull())
						tempGradeCenter.setGrade("-");
						else
						tempGradeCenter.setGrade(grade);
					
					System.out.println(courseid);
					System.out.println(grade);
					// create new student object
					
					
					
					tempGradeCenter.setCourseid(courseid);
					tempGradeCenter.setCoursename(coursename);
					
					
					
					
					System.out.println(tempGradeCenter.getFirstname());
					System.out.println(tempGradeCenter.getLastname());
					System.out.println(tempGradeCenter.getCourseid());
					System.out.println(tempGradeCenter.getGrade());
					System.out.println("bhos-d-k");
					// add it to the list of students
					gradecenter.add(tempGradeCenter);				
				}
				
				return gradecenter;		
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
