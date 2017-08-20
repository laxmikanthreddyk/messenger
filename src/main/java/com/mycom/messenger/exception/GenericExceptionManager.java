package com.mycom.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.mycom.messenger.model.ExceptionMessage;

//@Provider
public class GenericExceptionManager implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), 500, "http://localhost:8080/messenger/webapi/documenation");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exceptionMessage).build();
	}

	
}
