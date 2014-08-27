package org.shujito.addressbook.activity;

import org.shujito.addressbook.R;
import org.shujito.addressbook.adapter.AddressBookAdapter;
import org.shujito.addressbook.model.Contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * This is the first activity that launches.
 * @author shujito
 */
public class AddressBookActivity extends ActionBarActivity
	implements OnItemClickListener
{
	/* statics */
	static final String TAG = AddressBookActivity.class.getSimpleName();
	static final int REQUEST_CODE_CREATE = 0x1000;
	/* fields */
	private ListView mListView = null;
	
	/* lifecycle methods */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.mListView = new ListView(this);
		this.mListView.setOnItemClickListener(this);
		this.mListView.setAdapter(new AddressBookAdapter(this));
		this.setContentView(this.mListView);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_new:
				Intent ntn = new Intent(this, CreateContactActivity.class);
				this.startActivityForResult(ntn, REQUEST_CODE_CREATE);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == REQUEST_CODE_CREATE)
		{
			if (resultCode == RESULT_OK)
			{
				Contact contact = new Contact();
				contact.name = data.getStringExtra(CreateContactActivity.RESULT_NAME);
				contact.lastname = data.getStringExtra(CreateContactActivity.RESULT_LAST_NAME);
				contact.address = data.getStringExtra(CreateContactActivity.RESULT_ADDRESS);
				contact.phone = data.getStringExtra(CreateContactActivity.RESULT_PHONE);
				contact.notes = data.getStringExtra(CreateContactActivity.RESULT_NOTES);
				AddressBookAdapter adapter = (AddressBookAdapter) this.mListView.getAdapter();
				adapter.add(contact);
				adapter.notifyDataSetChanged();
			}
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> dad, View v, int pos, long id)
	{
	}
}
