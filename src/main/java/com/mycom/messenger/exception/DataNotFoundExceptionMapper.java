package com.mycom.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.mycom.messenger.model.ExceptionMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>
{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), 404, "http://localhost:8080/messenger/webapi/documenation");
		return Response.status(Status.NOT_FOUND).entity(exceptionMessage).build();
		
	}
	

}
