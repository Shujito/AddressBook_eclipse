package org.shujito.addressbook;

import android.app.Application;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.google.gson.GsonBuilder;
import com.koushikdutta.ion.Ion;

public class AddressBookApplication extends Application
{
	static final String TAG = AddressBookApplication.class.getSimpleName();
	private static AddressBookApplication instance = null;
	
	public static AddressBookApplication getInstance()
	{
		return instance;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.e(TAG, "I command you to log my cat.");
		instance = this;
		Configuration configuration = new Configuration.Builder(this)
			.setDatabaseName(this.getPackageName())
			.setDatabaseVersion(1)
			.create();
		ActiveAndroid.initialize(configuration);
		ExcludeFieldsWithoutSerializedName sfwosn = new ExcludeFieldsWithoutSerializedName();
		Ion ion = Ion.getDefault(this);
		ion.configure().setGson(new GsonBuilder()
			.addSerializationExclusionStrategy(sfwosn)
			.addDeserializationExclusionStrategy(sfwosn)
			.serializeNulls()
			.create());
	}
	
	@Override
	public void onTerminate()
	{
		super.onTerminate();
		ActiveAndroid.dispose();
	}
}
