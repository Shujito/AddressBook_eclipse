package org.shujito.addressbook.activity;

import org.shujito.addressbook.R;
import org.shujito.addressbook.controller.AddressBookApiController;
import org.shujito.addressbook.controller.AddressBookApiController.LoginCallback;
import org.shujito.addressbook.model.Result;
import org.shujito.addressbook.model.Session;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddressBookLoginAuthenticatorActivity extends AccountAuthenticatorActivity
	implements OnClickListener, LoginCallback
{
	public static final String TAG = AddressBookLoginAuthenticatorActivity.class.getSimpleName();
	public static final String EXTRA_ACCOUNT_TYPE = "account type";
	public static final String EXTRA_AUTH_TOKEN_TYPE = "auth token type";
	public static final String PARAM_USER_PASS = "user password";
	private EditText mEtUsername = null;
	private EditText mEtPassword = null;
	private Button mBtnLogin = null;
	private ProgressDialog mPdLoggingIn = null;
	
	@Override
	protected void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		this.setContentView(R.layout.activity_address_book_login);
		this.mEtUsername = (EditText) this.findViewById(R.id.et_username);
		this.mEtPassword = (EditText) this.findViewById(R.id.et_password);
		this.mBtnLogin = (Button) this.findViewById(R.id.btn_login);
		this.mBtnLogin.setOnClickListener(this);
		this.mPdLoggingIn = new ProgressDialog(this);
		this.mPdLoggingIn.setTitle(R.string.app_name);
		this.mPdLoggingIn.setMessage(this.getString(R.string.logging_in));
		this.mPdLoggingIn.setCancelable(false);
		this.mPdLoggingIn.setCanceledOnTouchOutside(false);
		this.mPdLoggingIn.setIndeterminate(true);
	}
	
	@Override
	public void onClick(View v)
	{
		String username = this.mEtUsername.getText().toString();
		String password = this.mEtPassword.getText().toString();
		Result result = new AddressBookApiController(this).login(username, password, this);
		if (result != null && result.message != null)
		{
			if (result.status == AddressBookApiController.STATUS_NO_USERNAME)
			{
				this.mEtUsername.setError(result.message);
				this.mEtUsername.requestFocus();
			}
			else if (result.status == AddressBookApiController.STATUS_NO_PASSWORD)
			{
				this.mEtPassword.setError(result.message);
				this.mEtPassword.requestFocus();
			}
			else if (result.status == (AddressBookApiController.STATUS_NO_USERNAME | AddressBookApiController.STATUS_NO_PASSWORD))
			{
				this.mEtUsername.setError(result.message);
				this.mEtPassword.setError(result.message);
			}
			return;
		}
		this.mPdLoggingIn.show();
	}
	
	@Override
	public void onLoginFailure(AddressBookApiController controller, Result result)
	{
		this.mPdLoggingIn.dismiss();
		new AlertDialog.Builder(this)
			.setTitle(R.string.app_name)
			.setMessage(result.message)
			.setPositiveButton(android.R.string.ok, null)
			.show();
	}
	
	@Override
	public void onLoginSuccess(AddressBookApiController controller, Session login)
	{
		this.mPdLoggingIn.dismiss();
		String username = this.mEtUsername.getText().toString();
		String password = this.mEtPassword.getText().toString();
		String authToken = login.id;
		String accountType = this.getPackageName();
		Intent intent = new Intent();
		intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, username);
		intent.putExtra(PARAM_USER_PASS, password);
		intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
		intent.putExtra(AccountManager.KEY_AUTHTOKEN, authToken);
		Account account = new Account(username, accountType);
		AccountManager accountManager = AccountManager.get(this);
		accountManager.addAccountExplicitly(account, password, null);
		accountManager.setAuthToken(account, accountType, authToken);
		this.setAccountAuthenticatorResult(intent.getExtras());
		this.setResult(Activity.RESULT_OK);
		this.finish();
	}
}
