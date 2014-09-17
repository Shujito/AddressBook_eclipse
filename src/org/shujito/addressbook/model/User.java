package org.shujito.addressbook.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class User
{
	public static final TypeToken<List<User>> listType = new TypeToken<List<User>>()
	{
	};
	@SerializedName(value = "username")
	public String username;
	@SerializedName(value = "created")
	public long created;
}
