package com.seterm.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.seterm.web.model.Login;



public class Logindb {
	private DataSource dataSource;

	public Logindb(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	public boolean userLogin(Login l)  throws Exception{
		String pswd = "1";
		Connection c;
		
		c = dataSource.getConnection();
		Statement s = null;
		try {
			s = c.createStatement();
		ResultSet r = null;
		
			r = s.executeQuery("select pwd from registration where NetID='"+l.getNETID()+"' and Role='"+l.getRole()+"'");
				while(r.next())
				{
					pswd=r.getString("pwd");
				}
				
				if(pswd.equals(l.getPassword())){
					return true;
				}
				else
				{
					return false;
				}
			}catch(Exception e){
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
		return false;
		}
	public ArrayList<String[]> setchar()  throws Exception{
		Connection c;
		//DBconnect db=new DBconnect();
		c = dataSource.getConnection();
		ArrayList<String[]>a=new ArrayList<String[]>();
		try{
			Statement s=c.createStatement();
			ResultSet r =s.executeQuery("select char from registration");
			
			while(r.next())
			{
				String[] s1 = new String[0];
				s1[0]=r.getString("char");
				//s1[1]=r.getString("name");
//				s1[2]=r.getString("details");
				a.add(s1);
			}
				
		    }
		catch(Exception e){
			e.printStackTrace();
			
		                   }finally{
		                   try {
							c.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
	}
		return a;
	}
}
