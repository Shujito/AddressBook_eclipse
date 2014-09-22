package org.shujito.addressbook.controller.exception;

public class ServerException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public ServerException(String message)
	{
		super(message);
	}
	
	public int getStatusCode()
	{
		return 401;
	}
	
	public String getError(String error)
	{
		return null;
	}
}