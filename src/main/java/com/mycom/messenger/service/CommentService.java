package com.mycom.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mycom.messenger.database.DatabaseClass;
import com.mycom.messenger.model.Comment;
import com.mycom.messenger.model.Message;


public class CommentService {
	
	public Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId)
	{
		Message message = messages.get(messageId);
		Map<Long, Comment> comments = message.getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId)
	{
		return messages.get(messageId).getComments().get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <=0)
		{
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public void removeComment(long messageId, long comemntId)
	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comments.remove(comemntId);
	}

}
