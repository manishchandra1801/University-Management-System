package com.seterm.web.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
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
import com.seterm.web.model.Filetoupload;

public class Filetouploaddbimpl implements Filetouploaddb{
	private DataSource dataSource;
	private Coursedb coursedb;
	

	public Filetouploaddbimpl(DataSource theDataSource) {
		dataSource = theDataSource;
		coursedb = new Coursedbimpl(dataSource);
	}
	public int filetouploadadd(Filetoupload filetoupload,Course course) throws Exception{
		int i=0;
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		
		try {
			PreparedStatement ps=c.prepareStatement("insert into material(name,courseid,matter,contenttype) values(?,?,?,?)");
			ps.setString(1,filetoupload.getFilename());								
			ps.setString(2, coursedb.getcourseid(course));
			ps.setBlob(3,filetoupload.getInputStream());
			ps.setString(4,filetoupload.getContenttype());
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
	
	
	
	public int filetouploadadd(Filetoupload filetoupload,String coursename) throws Exception{
		int i=0;
		
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String courseid="";
		try {
			myConn = dataSource.getConnection();		
			String sql = "select courseid from course where coursename='"+coursename+"'";			
			myStmt = myConn.createStatement();						
			myRs = myStmt.executeQuery(sql);			
				while (myRs.next()) {
				courseid = (String)myRs.getString("courseid");						
						}
			
			PreparedStatement ps=c.prepareStatement("insert into material(name,courseid,matter,contenttype) values(?,?,?,?)");
			ps.setString(1,filetoupload.getFilename());								
			ps.setString(2, courseid);
			ps.setBlob(3,filetoupload.getInputStream());
			ps.setString(4, filetoupload.getContenttype());
			
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
	
	


	public List<Filetoupload> getCourseBlob(String coursename) throws Exception{
		
		List<Filetoupload> filelist = new ArrayList<>();
		Filetoupload filetoupload=null;
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
			String sql1 = "select * from material where Courseid='"+courseid+"'";
			
			myStmt1 = myConn1.createStatement();
			
			// execute query
			myRs1 = myStmt1.executeQuery(sql1);
			
			// process result set
			while (myRs1.next()) {
				String filename = myRs1.getString("name");
				// retrieve data from result set row
				//int id = myRs.getInt("id");
				String fileid =  myRs1.getString("materialid");
				 //InputStream inputStream = blob.getBinaryStream();	                
	                
			filetoupload=new Filetoupload();
			filetoupload.setFilename(filename);
			filetoupload.setFileid(fileid);
			filelist.add(filetoupload);
			System.out.println(filetoupload.getFileid()+"PIT----PINGU-----"+coursename);
			}
			
			return filelist;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
			close(myConn1, myStmt1, myRs1);
		}	
		
	}
	
	
public InputStream getCourseBlobone(String coursename,String fileid) throws Exception{
	InputStream inputStream = null;
		List<Filetoupload> filelist = new ArrayList<>();
		Filetoupload filetoupload=null;
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
			String sql1 = "select * from material where Courseid='"+courseid+"' and materialid="+fileid+"";
			
			myStmt1 = myConn1.createStatement();
			
			// execute query
			myRs1 = myStmt1.executeQuery(sql1);
			
			// process result set
			while (myRs1.next()) {
				String filename = myRs1.getString("name");
				// retrieve data from result set row
				//int id = myRs.getInt("id");
				Blob blob =  myRs1.getBlob("matter");
				  inputStream = blob.getBinaryStream();	          
	                
		
			}
			
			return inputStream;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
			close(myConn1, myStmt1, myRs1);
		}	
		
	}




public String getCourseBlobcontent(String coursename,String fileid) throws Exception{
	InputStream inputStream = null;
		List<Filetoupload> filelist = new ArrayList<>();
		Filetoupload filetoupload=null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Connection myConn1 = null;
		Statement myStmt1 = null;
		ResultSet myRs1 = null;
		String filecontent="";
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
			String sql1 = "select * from material where Courseid='"+courseid+"' and materialid="+fileid+"";
			
			myStmt1 = myConn1.createStatement();
			
			// execute query
			myRs1 = myStmt1.executeQuery(sql1);
			
			// process result set
			while (myRs1.next()) {
				filecontent = myRs1.getString("contenttype");
				// retrieve data from result set row
				//int id = myRs.getInt("id");
					          
	                
		
			}
			
			return filecontent;		
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
