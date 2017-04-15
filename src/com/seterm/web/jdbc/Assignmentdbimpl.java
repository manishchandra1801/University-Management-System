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


public class Assignmentdbimpl implements Assignmentdb{
	private DataSource dataSource;
	private Coursedb coursedb;
	

	public Assignmentdbimpl(DataSource theDataSource) {
		dataSource = theDataSource;
		coursedb = new Coursedbimpl(dataSource);
	}
	
	public int assignmentadd(Assignment assignment,Course course) throws Exception{
		int i=0;
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		
		System.out.println(coursedb.getcourseid(course));
		try {
			PreparedStatement ps=c.prepareStatement("insert into assignment(question,option1,option2,option3,answer,courseid,option4) values(?,?,?,?,?,?,?)");
			ps.setString(1,assignment.getQuestion());
			ps.setString(2, assignment.getOption1());
			ps.setString(3,assignment.getOption2());
			ps.setString(4, assignment.getOption3());			
			ps.setString(5, assignment.getAnswer());
			ps.setString(6,coursedb.getcourseid(course));//coursedb.getcourseid(course)
			ps.setString(7,assignment.getOption4());
			
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
	public List<Assignment> getCourseAssignment(String coursename)throws Exception{
		List<Assignment> asslist = new ArrayList<>();
		Assignment assignment=null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Connection myConn1 = null;
		Statement myStmt1 = null;
		ResultSet myRs1 = null;
		String courseid="";
		try {
			myConn = dataSource.getConnection();		
			String sql = "select courseid from course where coursename='"+coursename+"'";			
			myStmt = myConn.createStatement();						
			myRs = myStmt.executeQuery(sql);			
				while (myRs.next()) {
				courseid = (String)myRs.getString("courseid");						
						}
				 
				
			// get a connection
			myConn1 = dataSource.getConnection();
			
			// create sql statement
			String sql1 = "select * from assignment where Courseid='"+courseid+"'";
			
			myStmt1 = myConn1.createStatement();
			
			// execute query
			myRs1 = myStmt1.executeQuery(sql1);
			
			// process result set
			while (myRs1.next()) {
				String assignmentid=myRs1.getString("assignmentid");
				String question = myRs1.getString("question");
				// retrieve data from result set row
				//int id = myRs.getInt("id");
				String option1 = myRs1.getString("option1");
				String option2 = myRs1.getString("option2");
				String option3 = myRs1.getString("option3");
				String option4 = myRs1.getString("option4");
				String answer = myRs1.getString("answer");
				//String grade = myRs1.getString("grade");
				assignment=new Assignment();
				assignment.setQuestion(question);
				assignment.setOption1(option1);
				assignment.setOption2(option2);
				assignment.setOption3(option3);
				assignment.setOption4(option4);
				assignment.setAnswer(answer);
				assignment.setAssignmentid(assignmentid);
				//assignment.setGrade(grade);
				asslist.add(assignment);
				System.out.print(assignment.getAnswer()+"+++++++++++++++=======================3");
			}
			
			return asslist;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
			close(myConn1, myStmt1, myRs1);
		}		
	}
	
	
	public Assignment getCourseAssignmentAnswers(String assignmentidpass)throws Exception{
		List<Assignment> asslist = new ArrayList<>();
		Assignment assignment=null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Connection myConn1 = null;
		Statement myStmt1 = null;
		ResultSet myRs1 = null;
		String courseid="";
		try {
			
				 
				
			// get a connection
			myConn1 = dataSource.getConnection();
			
			// create sql statement
			String sql1 = "select * from assignment where assignmentid='"+assignmentidpass+"'";
			
			myStmt1 = myConn1.createStatement();
			
			// execute query
			myRs1 = myStmt1.executeQuery(sql1);
			
			// process result set
			while (myRs1.next()) {
				String assignmentid=myRs1.getString("assignmentid");
				String question = myRs1.getString("question");
				// retrieve data from result set row
				//int id = myRs.getInt("id");
				String option1 = myRs1.getString("option1");
				String option2 = myRs1.getString("option2");
				String option3 = myRs1.getString("option3");
				String option4 = myRs1.getString("option4");
				String answer = myRs1.getString("answer");
				//String grade = myRs1.getString("grade");
				assignment=new Assignment();
				assignment.setQuestion(question);
				assignment.setOption1(option1);
				assignment.setOption2(option2);
				assignment.setOption3(option3);
				assignment.setOption4(option4);
				assignment.setAnswer(answer);
				assignment.setAssignmentid(assignmentid);
				//assignment.setGrade(grade);
				System.out.print(assignment.getAnswer()+"+++++++++++++++");
				
			}
			
			return assignment;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
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
}
