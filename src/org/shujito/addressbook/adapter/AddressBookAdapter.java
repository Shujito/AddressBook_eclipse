package org.shujito.addressbook.adapter;

import java.util.ArrayList;

import org.shujito.addressbook.R;
import org.shujito.addressbook.model.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class AddressBookAdapter extends BakedArrayAdapter<Contact>
{
	class ViewHolder extends FrameLayout
	{
		ImageView avatar = null;
		TextView nameLastName = null;
		TextView phone = null;
		TextView address = null;
		
		public ViewHolder(Context context)
		{
			super(context);
			LayoutInflater.from(context).inflate(R.layout.item_address_book, this);
			this.avatar = (ImageView) this.findViewById(R.id.iv_avatar);
			this.nameLastName = (TextView) this.findViewById(R.id.tv_name_lastname);
			this.phone = (TextView) this.findViewById(R.id.tv_phone);
			this.address = (TextView) this.findViewById(R.id.tv_address);
		}
	}
	
	public static AddressBookAdapter create(Context context)
	{
		return new AddressBookAdapter(context, new ArrayList<Contact>());
	}
	
	private AddressBookAdapter(Context context, ArrayList<Contact> objects)
	{
		super(context, R.layout.item_address_book, objects);
	}
	
	@Override
	public View getView(int position, View v, ViewGroup parent)
	{
		ViewHolder vh = null;
		if (v == null)
		{
			vh = new ViewHolder(this.getContext());
		}
		else
		{
			vh = (ViewHolder) v;
		}
		Contact contact = this.getItem(position);
		String fullName = contact.name;
		if (contact.lastname != null && contact.lastname.length() > 0)
			fullName = fullName + " " + contact.lastname;
		vh.nameLastName.setText(fullName);
		vh.phone.setText(contact.phone);
		vh.address.setText(contact.address);
		return vh;
	}
}
