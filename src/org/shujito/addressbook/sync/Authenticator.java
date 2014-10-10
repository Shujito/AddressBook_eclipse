package org.shujito.addressbook.sync;

import org.shujito.addressbook.R;
import org.shujito.addressbook.activity.AddressBookLoginAuthenticatorActivity;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class Authenticator extends AbstractAccountAuthenticator
{
	private Context mContext = null;
	private Handler mHandler = null;
	
	public Authenticator(Context context)
	{
		super(context);
		this.mContext = context;
		this.mHandler = new Handler();
	}
	
	public static final String TAG = Authenticator.class.getSimpleName();
	
	@Override
	public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException
	{
		Log.i(TAG, "addAccount");
		AccountManager accountManager = AccountManager.get(this.mContext);
		Account[] accounts = accountManager.getAccountsByType(accountType);
		if (accounts.length > 0)
		{
			this.mHandler.post(new Runnable()
			{
				@Override
				public void run()
				{
					Toast.makeText(mContext, R.string.only_one_account, Toast.LENGTH_SHORT).show();
				}
			});
			Bundle result = new Bundle();
			result.putInt(AccountManager.KEY_ERROR_CODE, 0);
			result.putString(AccountManager.KEY_ERROR_MESSAGE, this.mContext.getString(R.string.only_one_account));
			return result;
		}
		Intent intent = new Intent(this.mContext, AddressBookLoginAuthenticatorActivity.class);
		intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
		intent.putExtra(AddressBookLoginAuthenticatorActivity.EXTRA_ACCOUNT_TYPE, accountType);
		intent.putExtra(AddressBookLoginAuthenticatorActivity.EXTRA_AUTH_TOKEN_TYPE, authTokenType);
		Bundle bundle = new Bundle();
		bundle.putParcelable(AccountManager.KEY_INTENT, intent);
		return bundle;
	}
	
	@Override
	public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException
	{
		Log.i(TAG, "confirmCredentials");
		return null;
	}
	
	@Override
	public Bundle editProperties(AccountAuthenticatorResponse response, String accountType)
	{
		Log.i(TAG, "editProperties");
		return null;
	}
	
	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException
	{
		Log.i(TAG, "getAuthToken");
		return null;
	}
	
	@Override
	public String getAuthTokenLabel(String authTokenType)
	{
		Log.i(TAG, "getAuthTokenLabel");
		return authTokenType;
	}
	
	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException
	{
		Log.i(TAG, "hasFeatures");
		return null;
	}
	
	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException
	{
		Log.i(TAG, "updateCredentials");
		return null;
	}
}
