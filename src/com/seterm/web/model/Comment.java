package com.seterm.web.model;

public class Comment {
	private int commentid;
	private String comment;
	private String courseid;	
	private String commenterid_faculty;
	private String commenterid_student;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public String getCommenterid_faculty() {
		return commenterid_faculty;
	}
	public void setCommenterid_faculty(String commenterid_faculty) {
		this.commenterid_faculty = commenterid_faculty;
	}
	public String getCommenterid_student() {
		return commenterid_student;
	}
	public void setCommenterid_student(String commenterid_student) {
		this.commenterid_student = commenterid_student;
	}
	
}
