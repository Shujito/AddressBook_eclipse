package org.shujito.addressbook.activity;

import android.content.Intent;
import android.os.Bundle;

public class EditContactActivity extends CreateContactActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent intent = this.getIntent();
		String name = intent.getStringExtra(CreateContactActivity.RESULT_NAME);
		String lastname = intent.getStringExtra(CreateContactActivity.RESULT_LAST_NAME);
		String address = intent.getStringExtra(CreateContactActivity.RESULT_ADDRESS);
		String phone = intent.getStringExtra(CreateContactActivity.RESULT_PHONE);
		String notes = intent.getStringExtra(CreateContactActivity.RESULT_NOTES);
		this.mEtName.setText(name);
		this.mEtLastName.setText(lastname);
		this.mEtAddress.setText(address);
		this.mEtPhone.setText(phone);
		this.mEtNotes.setText(notes);
	}
}
