package com.seterm.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.seterm.web.model.Comment;
import com.seterm.web.model.Course;
import com.seterm.web.model.GradeCenter;
import com.seterm.web.model.Reply;

public class Commentdbimpl implements Commentdb{
	private DataSource dataSource;
	private HttpSession session;
	private HttpServletRequest request;
	public Commentdbimpl(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Comment> getComments(String coursename) throws Exception{
		List<Comment> commentlist = new ArrayList<>();
		Comment tempcomment=null;
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				Connection myConn1 = null;
				Statement myStmt1 = null;
				ResultSet myRs1 = null;
				String courseidpass="";
				try {
					myConn = dataSource.getConnection();		
					String sql = "select courseid from course where coursename='"+coursename+"'";			
					myStmt = myConn.createStatement();						
					myRs = myStmt.executeQuery(sql);			
						while (myRs.next()) {
							courseidpass = (String)myRs.getString("courseid");						
								}
					
					// get a connection
					myConn1 = dataSource.getConnection();
					
					// create sql statement
					String sql1 = "select * from comments where courseid="+courseidpass+"";
					
					myStmt1 = myConn1.createStatement();
					
					// execute query
					myRs1 = myStmt1.executeQuery(sql1);
					
					// process result set
					while (myRs1.next()) {
						
						// retrieve data from result set row
						tempcomment = new Comment();
						int commentid = myRs1.getInt("Commentid");
						String comment = myRs1.getString("comment");
						String courseid = myRs1.getString("courseid");
						String commenterid_faculty = myRs1.getString("commenterid_faculty");
						String commenterid_student = myRs1.getString("commenterid_student");
					
						// create new student object
						
						
						tempcomment.setComment(comment);
						tempcomment.setCommentid(commentid);
						tempcomment.setCourseid(courseid);
						tempcomment.setCommenterid_faculty(commenterid_faculty);
						tempcomment.setCommenterid_student(commenterid_student);
						
						
						System.out.println("bhos-d-k");
						// add it to the list of students
						commentlist.add(tempcomment);				
					}
					
					return commentlist;		
				}
				finally {
					// close JDBC objects
					close(myConn, myStmt, myRs);
					close(myConn1, myStmt1, myRs1);
				}		
			}
	
	public List<Reply> getReplys(String coursename) throws Exception{
		List<Reply> replylist = new ArrayList<>();
		Reply tempreply=null;
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				Connection myConn1 = null;
				Statement myStmt1 = null;
				ResultSet myRs1 = null;
				String courseidpass="";
				try {
					myConn1 = dataSource.getConnection();		
					String sql1 = "select courseid from course where coursename='"+coursename+"'";			
					myStmt1 = myConn1.createStatement();						
					myRs1 = myStmt1.executeQuery(sql1);			
						while (myRs1.next()) {
							courseidpass = (String)myRs1.getString("courseid");						
								}
					// get a connection
					myConn = dataSource.getConnection();
					
					// create sql statement
					String sql = "select reply.commentid,replyid,reply,courseid,replierid_faculty,replierid_student from reply inner join comments on reply.commentid=comments.commentid where courseid="+courseidpass+"";
					
					myStmt = myConn.createStatement();
					
					// execute query
					myRs = myStmt.executeQuery(sql);
					
					// process result set
					while (myRs.next()) {
						
						// retrieve data from result set row
						tempreply = new Reply();
						int commentid = myRs.getInt("Commentid");
						int replyid = myRs.getInt("replyid");
						String reply = myRs.getString("reply");
						String courseid = myRs.getString("courseid");
						String replierid_faculty = myRs.getString("replierid_faculty");
						String replierid_student = myRs.getString("replierid_student");
					
						// create new student object
						tempreply.setCommentid(commentid);
						tempreply.setCourseid(courseid);
						tempreply.setReplierid_faculty(replierid_faculty);
						tempreply.setReplierid_student(replierid_student);
						tempreply.setReply(reply);
						tempreply.setReplyid(replyid);
						
						
						
						System.out.println("bhos-d-k");
						// add it to the list of students
						replylist.add(tempreply);				
					}
					
					return replylist;		
				}
				finally {
					// close JDBC objects
					close(myConn, myStmt, myRs);
					close(myConn1, myStmt1, myRs1);
				}		
			}
	
	public int replyadd(Reply reply,String Role) throws Exception{
		int i=0;
		
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		
		try {
			if(Role.equals("Faculty")){
			PreparedStatement ps=c.prepareStatement("insert into reply(reply,commentid,replierid_faculty) values(?,?,?)");
			ps.setString(1,reply.getReply());
			ps.setInt(2, reply.getCommentid());
			ps.setString(3, reply.getReplierid_faculty());
			i=ps.executeUpdate();
			}else if(Role.equals("Student")){
				PreparedStatement ps=c.prepareStatement("insert into reply(reply,commentid,replierid_student) values(?,?,?)");
				ps.setString(1,reply.getReply());
				ps.setInt(2, reply.getCommentid());
				ps.setString(3, reply.getReplierid_student());
				i=ps.executeUpdate();
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
	
	public int commentadd(Comment comment,String Role)throws Exception{
		int i=0;
				Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		
		try {
			if(Role.equals("Faculty")){
			PreparedStatement ps=c.prepareStatement("insert into comments(comment,courseid,commenterid_faculty) values(?,?,?)");
			ps.setString(1,comment.getComment());
			ps.setString(2, comment.getCourseid());
			ps.setString(3, comment.getCommenterid_faculty());
			i=ps.executeUpdate();
			}else if(Role.equals("Student")){
				PreparedStatement ps=c.prepareStatement("insert into comments(comment,courseid,commenterid_student) values(?,?,?)");
				ps.setString(1,comment.getComment());
				ps.setString(2, comment.getCourseid());
				ps.setString(3, comment.getCommenterid_student());
				i=ps.executeUpdate();
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
