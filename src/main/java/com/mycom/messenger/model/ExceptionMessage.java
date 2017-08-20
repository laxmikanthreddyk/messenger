package com.mycom.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExceptionMessage {

	private String exceptionMessage;
	private int exceptionCode;
	private String documentation;
	public ExceptionMessage(String exceptionMessage, int exceptionCode, String documentation) {
		this.exceptionMessage = exceptionMessage;
		this.exceptionCode = exceptionCode;
		this.documentation = documentation;
	}
	
	public ExceptionMessage()
	{
		
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	
	
	
}
