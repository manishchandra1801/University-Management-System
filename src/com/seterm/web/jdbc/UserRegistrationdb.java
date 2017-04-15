package com.seterm.web.jdbc;

import java.util.List;

import com.seterm.web.model.UserRegistration;

public interface UserRegistrationdb {
	public int userRegistration(UserRegistration userregistrarion) throws Exception;

	public List<String> getNetidnames()  throws Exception; 
		
		
	
}
