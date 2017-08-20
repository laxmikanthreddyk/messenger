package com.mycom.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.mycom.messenger.database.DatabaseClass;
import com.mycom.messenger.model.*;

public class MessageService {
	
	public Map<Long, Message> messages = DatabaseClass.getMessages();
	public Map<Long, Profile> profiles = DatabaseClass.getProfiles();
	
	public MessageService()
	{
		Message m1 = new Message(1L,"My First Message","Laxmikanth");
		Message m2 = new Message(2L, "My Second Message", "Laxmikanth");
		messages.put(m1.getId(), m1);
		messages.put(m2.getId(), m2);
	}
	
	public List<Message> getAllMessages()
	{
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getMessageByYear(int year)
	{
		List<Message> messagesByYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message: messages.values())
		{
			cal.setTime(message.getCreated());
			System.out.println("Passed year: "+ year);
			System.out.println("Cal year: "+ cal.get(Calendar.YEAR));
			
			if(cal.get(Calendar.YEAR) == year)
			{
				messagesByYear.add(message);
			}
		}
		
		return messagesByYear;
	}
	
	public List<Message> getMessageByPagination(int start, int size)
	{
		ArrayList<Message> messagesByPagination = new ArrayList<>(messages.values());
		if(start > messages.size())
		{
			return (List<Message>) messages;
		}
		if(start+size > messages.size())
		{
		  	return messagesByPagination.subList(start, messages.size());
		}
		else
		{
		  return messagesByPagination.subList(start, start+size);
		}
	}
	
	public Message getMessage(long id)
	{
		return messages.get(id);
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message)
	{
		if(message.getId() <= 0)
		{
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public void removeMessage(long messageId)
	{
		messages.remove(messageId);
		//return message;
	}

}
