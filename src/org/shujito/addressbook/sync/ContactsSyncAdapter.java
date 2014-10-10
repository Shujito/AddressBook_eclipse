package org.shujito.addressbook.sync;

import java.util.List;

import org.shujito.addressbook.controller.AddressBookApiController;
import org.shujito.addressbook.model.Contact;
import org.shujito.addressbook.model.Session;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

public class ContactsSyncAdapter extends AbstractThreadedSyncAdapter
{
	public static final String TAG = ContactsSyncAdapter.class.getSimpleName();
	private AccountManager mAccountManager = null;
	
	public ContactsSyncAdapter(Context context, boolean autoInitialize)
	{
		super(context, autoInitialize);
		this.mAccountManager = AccountManager.get(context);
	}
	
	@Override
	public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult)
	{
		Log.i(TAG, "onPerformSync");
		try
		{
			Session session = new Session();
			session.id = this.mAccountManager.blockingGetAuthToken(account, this.getContext().getPackageName(), true);
			AddressBookApiController controller = new AddressBookApiController(this.getContext());
			List<Contact> remoteContacts = controller.getContacts(session);
			//List<Contact> localContacts = new Select().from(Contact.class).execute();
			for (Contact contact : remoteContacts)
			{
				contact.save();
			}
		}
		catch (Exception ex)
		{
			Log.e(TAG, ex.toString());
		}
	}
}
