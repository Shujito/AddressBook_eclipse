package org.shujito.addressbook.model;

import com.google.gson.annotations.SerializedName;

public class Result
{
	@SerializedName(value = "message")
	public String message;
	@SerializedName(value = "status")
	public int status;
	
	public Result setMessage(String message)
	{
		this.message = message;
		return this;
	}
	
	public Result setStatus(int status)
	{
		this.status = status;
		return this;
	}
}
