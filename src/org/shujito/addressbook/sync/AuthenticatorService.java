package org.shujito.addressbook.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticatorService extends Service
{
	private Authenticator mAuthenticator = null;
	public static final String TAG = AuthenticatorService.class.getSimpleName();
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		this.mAuthenticator = new Authenticator(this);
	}
	
	@Override
	public IBinder onBind(Intent intent)
	{
		return this.mAuthenticator.getIBinder();
	}
}
