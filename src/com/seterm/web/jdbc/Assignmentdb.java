package com.seterm.web.jdbc;

import java.util.List;

import com.seterm.web.model.Assignment;
import com.seterm.web.model.Course;

public interface Assignmentdb {
	public int assignmentadd(Assignment assignment,Course course) throws Exception;

	public List<Assignment> getCourseAssignment(String coursename)throws Exception;
	public Assignment getCourseAssignmentAnswers(String assignmentidpass)throws Exception;
}
