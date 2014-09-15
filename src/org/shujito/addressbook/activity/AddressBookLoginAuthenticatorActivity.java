package org.shujito.addressbook.activity;

import org.shujito.addressbook.R;

import android.accounts.AccountAuthenticatorActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddressBookLoginAuthenticatorActivity extends AccountAuthenticatorActivity
	implements OnClickListener
{
	public static final String TAG = AddressBookLoginAuthenticatorActivity.class.getSimpleName();
	private EditText mEtUsername = null;
	private EditText mEtPassword = null;
	private Button mBtnLogin = null;
	
	@Override
	protected void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		this.setContentView(R.layout.activity_address_book_login);
		this.mEtUsername = (EditText) this.findViewById(R.id.et_username);
		this.mEtPassword = (EditText) this.findViewById(R.id.et_password);
		this.mBtnLogin = (Button) this.findViewById(R.id.btn_login);
		this.mBtnLogin.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v)
	{
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return true;
	}
}
