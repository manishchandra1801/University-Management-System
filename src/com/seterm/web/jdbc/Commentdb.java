package com.seterm.web.jdbc;

import java.util.List;

import com.seterm.web.model.Comment;
import com.seterm.web.model.Reply;

public interface Commentdb {
	public List<Comment> getComments(String coursename) throws Exception;
	public List<Reply> getReplys(String coursename) throws Exception;
	public int replyadd(Reply reply,String Role) throws Exception;
	public int commentadd(Comment comment,String Role)throws Exception;
}
