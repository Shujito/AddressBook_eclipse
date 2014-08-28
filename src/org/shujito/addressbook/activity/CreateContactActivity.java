package org.shujito.addressbook.activity;

import org.shujito.addressbook.R;
import org.shujito.addressbook.model.Contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Just UI and intents, no models used here. Used to create {@link Contact} data.
 * @author shujito
 *
 */
public class CreateContactActivity extends ActionBarActivity
{
	/* statics */
	public static final String RESULT_AVATAR = "avatar";
	public static final String RESULT_NAME = "name";
	public static final String RESULT_LAST_NAME = "lastname";
	public static final String RESULT_ADDRESS = "address";
	public static final String RESULT_PHONE = "phone";
	public static final String RESULT_NOTES = "notes";
	/* fields */
	private EditText mEtName = null;
	private ImageView mIvAvatar = null;
	private EditText mEtLastName = null;
	private EditText mEtAddress = null;
	private EditText mEtPhone = null;
	private EditText mEtNotes = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_create_contact);
		this.mEtName = (EditText) this.findViewById(R.id.et_name);
		this.mIvAvatar = (ImageView) this.findViewById(R.id.iv_avatar);
		this.mEtLastName = (EditText) this.findViewById(R.id.et_last_name);
		this.mEtAddress = (EditText) this.findViewById(R.id.et_address);
		this.mEtPhone = (EditText) this.findViewById(R.id.et_phone);
		this.mEtNotes = (EditText) this.findViewById(R.id.et_notes);
		this.getSupportActionBar().setHomeButtonEnabled(true);
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		this.getMenuInflater().inflate(R.menu.create_contact, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_done:
				boolean pass = true;
				pass = pass && this.mEtName.getText().length() > 0;
				pass = pass && this.mEtPhone.getText().length() > 0;
				if (!pass)
				{
					if (this.mEtName.getText().length() == 0)
						this.mEtName.setError("Required field");
					if (this.mEtPhone.getText().length() == 0)
						this.mEtPhone.setError("Required field");
					return true;
				}
				Intent intent = new Intent();
				intent.putExtra(RESULT_NAME, this.mEtName.getText().toString());
				intent.putExtra(RESULT_LAST_NAME, this.mEtLastName.getText().toString());
				intent.putExtra(RESULT_ADDRESS, this.mEtAddress.getText().toString());
				intent.putExtra(RESULT_PHONE, this.mEtPhone.getText().toString());
				intent.putExtra(RESULT_NOTES, this.mEtNotes.getText().toString());
				this.setResult(RESULT_OK, intent);
				this.finish();
				return true;
			case android.R.id.home:
				this.finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
