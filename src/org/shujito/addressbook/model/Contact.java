package org.shujito.addressbook.model;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * A simple {@link Contact} model.
 * @author shujito
 *
 */
@Table(name = Contact.TABLE_CONTACT)
public class Contact extends Model implements Serializable
{
	/* statics */
	public static final TypeToken<List<Contact>> listType = new TypeToken<List<Contact>>()
	{
	};
	private static final long serialVersionUID = 1L;
	public static final String TABLE_CONTACT = "contact";
	public static final String CONTACT_ID = "_id";
	public static final String CONTACT_NAME = "name";
	public static final String CONTACT_LASTNAME = "lastname";
	public static final String CONTACT_ADDRESS = "address";
	public static final String CONTACT_PHONE = "phone";
	public static final String CONTACT_NOTES = "notes";
	/* fields */
	@SerializedName(value = CONTACT_ID)
	@Column(name = CONTACT_ID, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	public String id = null;
	@SerializedName(value = CONTACT_NAME)
	@Column(name = CONTACT_NAME, notNull = true, onNullConflict = Column.ConflictAction.IGNORE)
	public String name = null;
	@SerializedName(value = CONTACT_LASTNAME)
	@Column(name = CONTACT_LASTNAME)
	public String lastname = null;
	@SerializedName(value = CONTACT_ADDRESS)
	@Column(name = CONTACT_ADDRESS)
	public String address = null;
	@SerializedName(value = CONTACT_PHONE)
	@Column(name = CONTACT_PHONE, notNull = true, onNullConflict = Column.ConflictAction.IGNORE)
	public String phone = null;
	@SerializedName(value = CONTACT_NOTES)
	@Column(name = CONTACT_NOTES)
	public String notes = null;
}
