package com.seterm.web.model;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Login {
private String NETID;
private String Role;
private String charr;
private String Password;
public String getCharr() {
	return charr;
}
public void setCharr(String charr) {
	this.charr = charr;
}
public String getRole() {
	return Role;
}
public void setRole(String role) {
	Role = role;
}
public String getNETID() {
	return NETID;
}
public void setNETID(String nETID) {
	NETID = nETID;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}





}
