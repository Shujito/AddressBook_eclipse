package org.shujito.addressbook.controller;

import org.shujito.addressbook.model.Result;
import org.shujito.addressbook.model.Session;

import android.content.Context;
import android.net.Uri;

import com.activeandroid.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

public class AddressBookApiController
{
	public static final String TAG = AddressBookApiController.class.getSimpleName();
	
	public interface LoginCallback
	{
		public void onLoginFailure(Result result);
		
		public void onLoginSuccess(Session login);
	}
	
	private Gson mGson = new Gson();
	private Context mContext = null;
	private String mHost = "10.0.2.2:2403";
	private String mUsersPath = "users";
	private String mContactsPath = "contacts";
	
	public AddressBookApiController(Context context)
	{
		this.mContext = context;
	}
	
	public String login(String username, String password)
	{
		return this.login(username, password, null);
	}
	
	public String login(String username, String password, final LoginCallback callback)
	{
		if (username == null && password == null)
			return "Username and password required";
		if (username == null)
			return "Username required";
		if (password == null)
			return "Password required";
		if (callback != null)
		{
			final Uri.Builder uriBuilder = new Uri.Builder()
				.scheme("http")
				.encodedAuthority(this.mHost)
				.appendPath(this.mUsersPath)
				.appendPath("login");
			String u = uriBuilder.build().toString();
			Ion.with(this.mContext)
				.load(u)
				.setBodyParameter("username", username)
				.setBodyParameter("password", password)
				.asJsonObject()
				.withResponse()
				.setCallback(new FutureCallback<Response<JsonObject>>()
				{
					AddressBookApiController $this = AddressBookApiController.this;
					
					@Override
					public void onCompleted(Exception ex, Response<JsonObject> response)
					{
						// catch library exception
						if (ex != null)
						{
							Log.e(TAG, ex.toString());
							Result result = new Result();
							result.status = -1;
							result.message = ex.getLocalizedMessage();
							callback.onLoginFailure(result);
							return;
						}
						// catch second exception
						if (response.getException() != null)
						{
							Log.e(TAG, response.getException().toString());
							Result result = new Result();
							result.status = response.getHeaders().getResponseCode();
							result.message = response.getException().getLocalizedMessage();
							callback.onLoginFailure(result);
							return;
						}
						// all caught up
						if (response.getResult() != null)
						{
							JsonObject jobj = response.getResult();
							if (response.getHeaders().getResponseCode() == 200)
							{
								Session session = $this.mGson.fromJson(jobj, Session.class);
								callback.onLoginSuccess(session);
							}
							else
							{
								Result result = $this.mGson.fromJson(jobj, Result.class);
								callback.onLoginFailure(result);
							}
							// end here
							return;
						}
						// HACK: strange case
						callback.onLoginFailure(null);
					}
				});
		}
		return null;
	}
}
