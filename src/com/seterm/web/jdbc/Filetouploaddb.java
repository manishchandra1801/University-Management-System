package com.seterm.web.jdbc;

import java.io.InputStream;
import java.util.List;

import com.seterm.web.model.Assignment;
import com.seterm.web.model.Course;
import com.seterm.web.model.Filetoupload;

public interface Filetouploaddb {
	public int filetouploadadd(Filetoupload filetoupload,Course course) throws Exception;

	public List<Filetoupload> getCourseBlob(String coursename) throws Exception;
	public InputStream getCourseBlobone(String coursename, String fileid) throws Exception;
	public int filetouploadadd(Filetoupload filetoupload,String coursename) throws Exception;
	public String getCourseBlobcontent(String coursename,String fileid) throws Exception;
	
}
