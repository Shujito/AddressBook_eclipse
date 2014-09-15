package org.shujito.addressbook.model;

import com.google.gson.annotations.SerializedName;

public class Result
{
	@SerializedName(value = "message")
	public String message;
	@SerializedName(value = "status")
	public int status;
}
