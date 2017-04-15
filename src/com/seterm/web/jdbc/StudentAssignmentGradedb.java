package com.seterm.web.jdbc;

import java.util.List;

public interface StudentAssignmentGradedb {
	public List<String> addWholeGrade(String assignmentidpass, String netid, String coursepage_coursename) throws Exception;
}
