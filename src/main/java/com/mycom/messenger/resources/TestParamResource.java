package com.mycom.messenger.resources;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.message.internal.HttpHeaderReader;

@Path("/testparams")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class TestParamResource {
	
	@GET
	public String getParams(@MatrixParam("matrixParam") String matrixParam, 
					@HeaderParam("headerCustomParam") String headerCustomParam,
					@CookieParam("JSESSIONID") String cookie)
	{
		return "Matrix Param value :" + matrixParam + " Header Param value: "+ headerCustomParam +" Cookie param" + cookie;
	}
	
	@GET
	@Path("/context")
	public String getContextParams(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders)
	{
		String path = uriInfo.getPath();
		return "Path: " + path+ " Cookies: " + httpHeaders.getCookies();
	}

}
