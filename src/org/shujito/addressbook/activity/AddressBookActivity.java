package org.shujito.addressbook.activity;

import org.shujito.addressbook.R;
import org.shujito.addressbook.adapter.AddressBookAdapter;
import org.shujito.addressbook.model.Contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.From;

/**
 * This is the first activity that launches.
 * @author shujito
 */
public class AddressBookActivity extends ActionBarActivity
	implements OnItemClickListener, OnItemLongClickListener, ActionMode.Callback
{
	/* statics */
	static final String TAG = AddressBookActivity.class.getSimpleName();
	static final int REQUEST_CODE_CREATE = 0x1000;
	/* fields */
	private ListView mListView = null;
	private ActionMode mActionMode = null;
	
	/* lifecycle methods */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.mListView = new ListView(this);
		this.mListView.setOnItemClickListener(this);
		this.mListView.setOnItemLongClickListener(this);
		this.mListView.setAdapter(new AddressBookAdapter(this));
		this.mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
		this.setContentView(this.mListView);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		this.getMenuInflater().inflate(R.menu.address_book, menu);
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
	public void onItemClick(AdapterView<?> ada, View v, int pos, long id)
	{
		if (this.mListView.getChoiceMode() == ListView.CHOICE_MODE_NONE)
		{
			AddressBookAdapter adapter = (AddressBookAdapter) this.mListView.getAdapter();
			Contact contact = adapter.getItem(pos);
			Intent intent = new Intent(this, ViewContactActivity.class);
			intent.putExtra(ViewContactActivity.RESULT_NAME, contact.name);
			intent.putExtra(ViewContactActivity.RESULT_LAST_NAME, contact.lastname);
			intent.putExtra(ViewContactActivity.RESULT_ADDRESS, contact.address);
			intent.putExtra(ViewContactActivity.RESULT_PHONE, contact.phone);
			intent.putExtra(ViewContactActivity.RESULT_NOTES, contact.notes);
			this.startActivity(intent);
		}
		if (this.mListView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE)
		{
			long[] checkedIds = this.mListView.getCheckedItemIds();
			if (checkedIds.length == 0)
			{
				this.mActionMode.finish();
			}
		}
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> dad, View v, int pos, long id)
	{
		if (this.mListView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE)
			return false;
		this.mActionMode = this.startSupportActionMode(this);
		this.mListView.setItemChecked(pos, true);
		return true;
	}
	
	/* ActionMode.Callback */
	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu)
	{
		this.mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		MenuInflater menuInflater = mode.getMenuInflater();
		menuInflater.inflate(R.menu.address_book_context, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareActionMode(ActionMode action, Menu menu)
	{
		return false;
	}
	
	@Override
	public boolean onActionItemClicked(ActionMode action, MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_delete:
				AddressBookAdapter adapter = (AddressBookAdapter) this.mListView.getAdapter();
				long[] ids = this.mListView.getCheckedItemIds();
				From from = new Delete().from(Contact.class);
				for (long id : ids)
				{
					from.or("id=?", id);
				}
				from.execute();
				adapter.notifyDataSetChanged();
				action.finish();
				return true;
		}
		return false;
	}
	
	@Override
	public void onDestroyActionMode(ActionMode action)
	{
		AddressBookAdapter adapter = (AddressBookAdapter) this.mListView.getAdapter();
		adapter.notifyDataSetInvalidated();
		this.mListView.clearChoices();
		//this.mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		this.mListView.post(new Runnable()
		{
			@Override
			public void run()
			{
				mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
			}
		});
	}
}
