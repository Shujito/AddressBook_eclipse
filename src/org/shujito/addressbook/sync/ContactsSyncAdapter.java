package org.shujito.addressbook.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

public class ContactsSyncAdapter extends AbstractThreadedSyncAdapter
{
	public static final String TAG = ContactsSyncAdapter.class.getSimpleName();
	
	public ContactsSyncAdapter(Context context, boolean autoInitialize)
	{
		super(context, autoInitialize);
	}
	
	@Override
	public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult)
	{
		Log.i(TAG, "onPerformSync");
	}
}
