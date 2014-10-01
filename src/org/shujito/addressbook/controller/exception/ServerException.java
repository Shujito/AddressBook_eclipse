package org.shujito.addressbook.controller.exception;

import java.util.HashMap;
import java.util.Map;

public class ServerException extends Exception
{
	private static final long serialVersionUID = 1L;
	private Map<String, String> errorMap = null;
	private int statusCode = 400;
	
	public ServerException(String message)
	{
		super(message);
		this.errorMap = new HashMap<String, String>();
	}
	
	public int getStatusCode()
	{
		return this.statusCode;
	}
	
	public String getError(String error)
	{
		return this.errorMap.get(error);
	}
	
	public ServerException setStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
		return this;
	}
	
	public ServerException putError(String error, String description)
	{
		this.errorMap.put(error, description);
		return this;
	}
}