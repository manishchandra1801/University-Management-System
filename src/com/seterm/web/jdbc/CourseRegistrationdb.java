package com.seterm.web.jdbc;

import java.util.List;

import javax.sql.DataSource;

import com.seterm.web.model.Assignment;

public interface CourseRegistrationdb {
	public int courseregistrationadd(String netid,String coursename) throws Exception;

	

	

	public int addgrade(String netid, String assignmentid, String string, String coursepage_coursename) throws Exception;
	public Assignment getStudentAssignment(String netid,String assignmentidpass)throws Exception;



	public int updateWholeGrade(String netid, String coursepage_coursename, int ans)  throws Exception;
}
