package org.shujito.addressbook.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ContactsSyncService extends Service
{
	public static final String TAG = ContactsSyncService.class.getSimpleName();
	private static final Object syncLock = new Object();
	private ContactsSyncAdapter mContactsSyncAdapter;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		synchronized (syncLock)
		{
			if (this.mContactsSyncAdapter == null)
			{
				this.mContactsSyncAdapter = new ContactsSyncAdapter(this, true);
			}
		}
	}
	
	@Override
	public IBinder onBind(Intent intent)
	{
		Log.i(TAG, "onBind");
		return this.mContactsSyncAdapter.getSyncAdapterBinder();
	}
}
