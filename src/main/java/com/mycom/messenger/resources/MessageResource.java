package com.mycom.messenger.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.mycom.messenger.exception.DataNotFoundException;
import com.mycom.messenger.model.Message;
import com.mycom.messenger.resources.beans.MessageFilterBean;
import com.mycom.messenger.service.MessageService;

@Path("/messages")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {
	
	MessageService messageService = new MessageService();

	/*
	@GET
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start, @QueryParam("size") int size)
	{
		if(year > 0)
		{
			return messageService.getMessageByYear(year);
		}
		if(start > 0 && size > 0)
		{
			return messageService.getMessageByPagination(start, size);
		}
		return messageService.getAllMessages();
	}
	*/

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean)
	{
		if(filterBean.getYear() > 0)
		{
			return messageService.getMessageByYear(filterBean.getYear());
		}
		if(filterBean.getStart() > 0 && filterBean.getSize() > 0)
		{
			return messageService.getMessageByPagination(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo)
	{
		Message message = messageService.getMessage(messageId);
		message.addLink(getSelfLinkInfo(uriInfo, message),"self"); 
		message.addLink(getProfileLinkInfo(uriInfo, message),"profile"); 
		message.addLink(getCommentLinkInfo(uriInfo, message),"comments"); 
		
		
		if(message == null)
		{
			throw new DataNotFoundException("Data Not Found for given message ID" + messageId);
		}
		return message;
	}


	private String getSelfLinkInfo(UriInfo uriInfo, Message message) {
		String link = uriInfo.getBaseUriBuilder()
						.path(MessageResource.class)
						.path(Long.toString(message.getId())).toString();
		return link;
	}
	
	private String getProfileLinkInfo(UriInfo uriInfo, Message message) {
		String link = uriInfo.getBaseUriBuilder()
						.path(ProfileResource.class)
						.path(message.getAuthor()).toString();
		return link;
	}
	
	private String getCommentLinkInfo(UriInfo uriInfo, Message message) {
		String link = uriInfo.getBaseUriBuilder()
						.path(MessageResource.class)
						.path(MessageResource.class,"getCommentResource")
						.path(CommentResource.class)
						.resolveTemplate("messageId", message.getId()).toString();
		return link;
	}
	
	
	@POST
	@Path("/add")
	public Response addMessage(Message message, @Context UriInfo uriInfo)
	{
		Message newMessage =  messageService.addMessage(message);
		
		return Response
				.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build())
				.entity(newMessage).build();
	}
	
	@PUT
	@Path("/update/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message)
	{
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/delete/{messageId}")
	public void removeMessage(@PathParam("messageId") long messageId)
	{
	   messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
	

	
}
