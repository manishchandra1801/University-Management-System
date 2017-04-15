/**
 * 
 */
package com.seterm.web.jdbc;

import java.util.List;

import com.seterm.web.model.Student;

/**
 * @author manish chandra
 *
 */
public interface Studentdb {
	public List<Student> getStudents()throws Exception;
	public void addStudent(Student theStudent)throws Exception;
	public Student getStudent(String theStudentId)throws Exception;
	public void updateStudent(Student theStudent)throws Exception;
	public void deleteStudent(String theStudentId) throws Exception;
	public void addmyStudent(String netid, String firstname, String lastname) throws Exception;
}
