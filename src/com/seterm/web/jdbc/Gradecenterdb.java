package com.seterm.web.jdbc;

import java.util.List;

import com.seterm.web.model.GradeCenter;
import com.seterm.web.model.Student;

public interface Gradecenterdb {
	public List<GradeCenter> getGradecenterinstructor(String netid) throws Exception;
	public List<GradeCenter> getGradecenter(String column,String netid) throws Exception;
	public List<GradeCenter> getGradecenterstudent(String netid) throws Exception;
	public List<GradeCenter> getGradecentersortstudent(String column,String netid) throws Exception;
	public List<GradeCenter> getGradecentersortstudentCoursename(String column, String netid)throws Exception;
}
