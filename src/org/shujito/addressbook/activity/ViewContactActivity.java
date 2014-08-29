package org.shujito.addressbook.activity;

import org.shujito.addressbook.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewContactActivity extends ActionBarActivity
{
	/* statics */
	public static final String RESULT_AVATAR = "avatar";
	public static final String RESULT_NAME = "name";
	public static final String RESULT_LAST_NAME = "lastname";
	public static final String RESULT_ADDRESS = "address";
	public static final String RESULT_PHONE = "phone";
	public static final String RESULT_NOTES = "notes";
	/* fields */
	private ImageView mIvAvatar = null;
	private TextView mTvNameLastname = null;
	private TextView mTvAddress = null;
	private TextView mTvPhone = null;
	private TextView mTvNotes = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_view_contact);
		this.getSupportActionBar().setHomeButtonEnabled(true);
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// ui's
		this.mIvAvatar = (ImageView) this.findViewById(R.id.iv_avatar);
		this.mTvNameLastname = (TextView) this.findViewById(R.id.tv_name_lastname);
		this.mTvAddress = (TextView) this.findViewById(R.id.tv_address);
		this.mTvPhone = (TextView) this.findViewById(R.id.tv_phone);
		this.mTvNotes = (TextView) this.findViewById(R.id.tv_notes);
		// put infos
		String fullName = this.getIntent().getStringExtra(RESULT_NAME);
		String lastName = this.getIntent().getStringExtra(RESULT_LAST_NAME);
		String address = this.getIntent().getStringExtra(RESULT_ADDRESS);
		String phone = this.getIntent().getStringExtra(RESULT_PHONE);
		String notes = this.getIntent().getStringExtra(RESULT_NOTES);
		if (lastName != null && lastName.length() > 0)
		{
			fullName = fullName + " " + lastName;
		}
		if (address == null || address != null && address.length() == 0)
		{
			this.mTvAddress.setVisibility(View.GONE);
		}
		if (notes == null || notes != null && notes.length() == 0)
		{
			this.mTvNotes.setVisibility(View.GONE);
		}
		this.mTvNameLastname.setText(fullName);
		this.mTvAddress.setText(address);
		this.mTvPhone.setText(phone);
		this.mTvNotes.setText(notes);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				this.finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
