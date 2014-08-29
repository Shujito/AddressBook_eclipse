package org.shujito.addressbook.adapter;

import org.shujito.addressbook.R;
import org.shujito.addressbook.model.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter for the addressbook listing.
 * @author shujito
 *
 */
public class AddressBookAdapter extends ModelArrayAdapter<Contact>
{
	class ViewHolder extends FrameLayout
	{
		ImageView avatar = null;
		TextView nameLastName = null;
		TextView address = null;
		TextView phone = null;
		
		public ViewHolder(Context context)
		{
			super(context);
			LayoutInflater.from(context).inflate(R.layout.item_address_book, this);
			this.avatar = (ImageView) this.findViewById(R.id.iv_avatar);
			this.nameLastName = (TextView) this.findViewById(R.id.tv_name_lastname);
			this.address = (TextView) this.findViewById(R.id.tv_address);
			this.phone = (TextView) this.findViewById(R.id.tv_phone);
		}
	}
	
	public AddressBookAdapter(Context context)
	{
		super(context, R.layout.item_address_book, Contact.class);
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
		if (contact.address == null || contact.address != null && contact.address.length() == 0)
			vh.address.setVisibility(View.GONE);
		else
			vh.address.setVisibility(View.VISIBLE);
		if (contact.phone == null || contact.phone != null && contact.phone.length() == 0)
			vh.phone.setVisibility(View.GONE);
		else
			vh.phone.setVisibility(View.VISIBLE);
		vh.nameLastName.setText(fullName);
		vh.address.setText(contact.address);
		vh.phone.setText(contact.phone);
		return vh;
	}
}
