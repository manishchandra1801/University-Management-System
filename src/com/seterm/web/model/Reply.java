package com.seterm.web.model;

public class Reply {
	private int commentid;
	private int replyid;
	private String reply;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public String getReplierid_faculty() {
		return replierid_faculty;
	}
	public void setReplierid_faculty(String replierid_faculty) {
		this.replierid_faculty = replierid_faculty;
	}
	public String getReplierid_student() {
		return replierid_student;
	}
	public void setReplierid_student(String replierid_student) {
		this.replierid_student = replierid_student;
	}
	private String courseid;
	private String replierid_faculty;
	private String replierid_student;
}
