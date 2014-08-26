package org.shujito.addressbook;

import android.app.Application;

public class AddressBookApplication extends Application
{
	private static AddressBookApplication instance = null;
	
	public static AddressBookApplication getInstance()
	{
		return instance;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
	}
}
