package com.seterm.web.jdbc;

import java.util.List;

import com.seterm.web.model.Course;

public interface Coursedb {
	public int courseadd(Course course) throws Exception;
	public String getcourseid(Course course) throws Exception;
	public List<String> getCoursenames() throws Exception;
	public List<String> getFacultyCoursenames(String netid) throws Exception;
	public Course getCourseNamesDescription(String coursename) throws Exception;
	public String getcourseid(String coursename) throws Exception;
	public int getcoursecount(String instructor) throws Exception;
	public List<Course> getCourseNamesDescription()throws Exception;
	public List<String> getStudentCoursenames(String attribute) throws Exception;
			
}
