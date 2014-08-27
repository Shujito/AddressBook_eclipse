package org.shujito.addressbook.model;

import java.io.Serializable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * A simple {@link Contact} model.
 * @author shujito
 *
 */
@Table(name = Contact.TABLE_CONTACT)
public class Contact extends Model implements Serializable
{
	/* statics */
	private static final long serialVersionUID = 1L;
	static final String TABLE_CONTACT = "contact";
	static final String CONTACT_NAME = "name";
	static final String CONTACT_LASTNAME = "lastname";
	static final String CONTACT_ADDRESS = "address";
	static final String CONTACT_PHONE = "phone";
	static final String CONTACT_NOTES = "notes";
	/* fields */
	@Column(name = CONTACT_NAME)
	public String name = null;
	@Column(name = CONTACT_LASTNAME)
	public String lastname = null;
	@Column(name = CONTACT_ADDRESS)
	public String address = null;
	@Column(name = CONTACT_PHONE)
	public String phone = null;
	@Column(name = CONTACT_NOTES)
	public String notes = null;
}
