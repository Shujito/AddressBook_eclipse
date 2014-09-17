package org.shujito.addressbook.model;

import com.google.gson.annotations.SerializedName;

public class Session
{
	@SerializedName(value = "id")
	public String id;
	@SerializedName(value = "uid")
	public String uid;
	@SerializedName(value = "path")
	public String path;
}
