package com.seterm.web.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class UserRegistration {
	private String NetID;
	private String firstname;
	private String Lastname;
	private String Role;
	private String Program;
	private String yearofjoining;
	private String pwd;
	
	
	public String getNetID() {
		return NetID;
	}
	public void setNetID(String netID) {
		NetID = netID;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getProgram() {
		return Program;
	}
	public void setProgram(String program) {
		Program = program;
	}
	public String getYearofjoining() {
		return yearofjoining;
	}
	public void setYearofjoining(String yearofjoining) {
		this.yearofjoining = yearofjoining;
	}
	
	public String getpwd() {
		return pwd;
	}
	public void setpwd(String pwd) {
		this.pwd = pwd;
	}
	

}
