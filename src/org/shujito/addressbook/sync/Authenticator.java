package org.shujito.addressbook.sync;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class Authenticator extends AbstractAccountAuthenticator
{
	public Authenticator(Context context)
	{
		super(context);
	}
	
	public static final String TAG = Authenticator.class.getSimpleName();
	
	@Override
	public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException
	{
		Log.i(TAG, "addAccount");
		return null;
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
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException
	{
		Log.i(TAG, "getAuthToken");
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String getAuthTokenLabel(String authTokenType)
	{
		Log.i(TAG, "getAuthTokenLabel");
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException
	{
		Log.i(TAG, "hasFeatures");
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException
	{
		Log.i(TAG, "updateCredentials");
		throw new UnsupportedOperationException();
	}
}
