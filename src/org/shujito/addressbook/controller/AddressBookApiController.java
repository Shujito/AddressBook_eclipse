package org.shujito.addressbook.controller;

import java.util.List;

import org.shujito.addressbook.model.Contact;
import org.shujito.addressbook.model.Result;
import org.shujito.addressbook.model.Session;
import org.shujito.addressbook.model.User;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.activeandroid.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

public class AddressBookApiController
{
	public static final String TAG = AddressBookApiController.class.getSimpleName();
	public static final int STATUS_NONE = 0;
	public static final int STATUS_NO_USERNAME = 0x10;
	public static final int STATUS_NO_PASSWORD = 0x20;
	
	public static class LoginException extends Exception
	{
		private static final long serialVersionUID = 1L;
		
		public LoginException(String message)
		{
			super(message);
		}
	}
	
	public static class ServerException extends Exception
	{
		private static final long serialVersionUID = 1L;
		
		public ServerException(String message)
		{
			super(message);
		}
	}
	
	public static class ControllerFragment extends Fragment
	{
		private AddressBookApiController controller = null;
		
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			this.controller = new AddressBookApiController(this.getActivity());
		}
		
		public AddressBookApiController getController()
		{
			return this.controller;
		}
	}
	
	public interface LoginCallback
	{
		public void onLoginFailure(AddressBookApiController controller, Result result);
		
		public void onLoginSuccess(AddressBookApiController controller, Session login);
	}
	
	private Context mContext = null;
	private Gson mGson = new Gson();
	private String mHost = "10.0.2.2:2403";
	private String mUsersPath = "users";
	private String mContactsPath = "contacts";
	
	public AddressBookApiController(Context context)
	{
		this.mContext = context;
	}
	
	/**
	 * Logs a user in
	 * @param username to be used to login
	 * @param password to be used to login
	 * @return 
	 * @throws LoginException
	 */
	public Session login(String username, String password) throws LoginException, ServerException
	{
		if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password))
			throw new LoginException("Username and password required");
		if (TextUtils.isEmpty(username))
			throw new LoginException("Username required");
		if (TextUtils.isEmpty(password))
			throw new LoginException("Password required");
		Response<JsonObject> response = null;
		try
		{
			response = this.buildLoginResponse(username, password).get();
		}
		catch (Exception e)
		{
			throw new ServerException(e.getMessage());
		}
		if (response != null && response.getResult() != null)
		{
			JsonObject jobj = response.getResult();
			if (response.getHeaders().getResponseCode() == 200)
			{
				return this.mGson.fromJson(jobj, Session.class);
			}
			else
			{
				Result result = this.mGson.fromJson(jobj, Result.class);
				throw new ServerException(result.message);
			}
		}
		// XXX: strange case
		throw new LoginException(null);
	}
	
	/**
	 * Logs a user in asynchronously, using a callback
	 * @param username to be used to login
	 * @param password to be used to login
	 * @param callback receiving server responses
	 * @return a result for user and/or password validation
	 * @see LoginCallback
	 */
	public Result login(String username, String password, final LoginCallback callback)
	{
		if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password))
			return new Result().setStatus(STATUS_NO_USERNAME | STATUS_NO_PASSWORD).setMessage("Username and password required");
		if (TextUtils.isEmpty(username))
			return new Result().setStatus(STATUS_NO_USERNAME).setMessage("Username required");
		if (TextUtils.isEmpty(password))
			return new Result().setStatus(STATUS_NO_PASSWORD).setMessage("Password required");
		if (callback != null)
		{
			this.buildLoginResponse(username, password).setCallback(new FutureCallback<Response<JsonObject>>()
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
						callback.onLoginFailure($this, result);
						return;
					}
					// catch second exception
					if (response.getException() != null)
					{
						Log.e(TAG, response.getException().toString());
						Result result = new Result();
						result.status = response.getHeaders().getResponseCode();
						result.message = response.getException().getLocalizedMessage();
						callback.onLoginFailure($this, result);
						return;
					}
					// all caught up
					if (response.getResult() != null)
					{
						JsonObject jobj = response.getResult();
						if (response.getHeaders().getResponseCode() == 200)
						{
							Session session = $this.mGson.fromJson(jobj, Session.class);
							callback.onLoginSuccess($this, session);
						}
						else
						{
							Result result = $this.mGson.fromJson(jobj, Result.class);
							callback.onLoginFailure($this, result);
						}
						// end here
						return;
					}
					// XXX: strange case
					callback.onLoginFailure($this, null);
				}
			});
		}
		return null;
	}
	
	private String buildLoginUrl()
	{
		String host = Uri.encode(this.mHost, ":");
		return new Uri.Builder()
			.scheme("http")
			.encodedAuthority(host)
			.appendPath(this.mUsersPath)
			.appendPath("login")
			.build()
			.toString();
	}
	
	private Future<Response<JsonObject>> buildLoginResponse(String username, String password)
	{
		return Ion.with(this.mContext)
			.load(this.buildLoginUrl())
			.setBodyParameter("username", username)
			.setBodyParameter("password", password)
			.asJsonObject()
			.withResponse();
	}
	
	public List<User> getUsers()
	{
		try
		{
			Response<List<User>> response = Ion.with(this.mContext)
				.load(this.buildUsersUrl())
				.as(User.listType)
				.withResponse()
				.get();
			return response.getResult();
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
	private String buildUsersUrl()
	{
		String host = Uri.encode(this.mHost, ":");
		return new Uri.Builder()
			.scheme("http")
			.encodedAuthority(host)
			.appendPath(this.mUsersPath)
			.build()
			.toString();
	}
	
	public List<Contact> getContacts(Session session)
	{
		Ion.getDefault(this.mContext).getCookieMiddleware().clear();
		try
		{
			Response<List<Contact>> response = Ion.with(this.mContext)
				.load(this.buildContactsUrl())
				.setHeader("cookie", session.id == null ? "" : "sid=" + session.id)
				.as(Contact.listType)
				.withResponse()
				.get();
			return response.getResult();
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
	private String buildContactsUrl()
	{
		String host = Uri.encode(this.mHost, ":");
		return new Uri.Builder()
			.scheme("http")
			.encodedAuthority(host)
			.appendPath(this.mContactsPath)
			.build()
			.toString();
	}
}
