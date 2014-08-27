package org.shujito.addressbook;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

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
		Configuration configuration = new Configuration.Builder(this)
			.setDatabaseName(this.getPackageName())
			.setDatabaseVersion(1)
			.create();
		ActiveAndroid.initialize(configuration);
	}
	
	@Override
	public void onTerminate()
	{
		super.onTerminate();
		ActiveAndroid.dispose();
	}
}
