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
	public static final String TABLE_CONTACT = "contact";
	public static final String CONTACT_ID = "_id";
	public static final String CONTACT_NAME = "name";
	public static final String CONTACT_LASTNAME = "lastname";
	public static final String CONTACT_ADDRESS = "address";
	public static final String CONTACT_PHONE = "phone";
	public static final String CONTACT_NOTES = "notes";
	/* fields */
	@Column(name = CONTACT_ID, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	public String id = null;
	@Column(name = CONTACT_NAME, notNull = true, onNullConflict = Column.ConflictAction.IGNORE)
	public String name = null;
	@Column(name = CONTACT_LASTNAME)
	public String lastname = null;
	@Column(name = CONTACT_ADDRESS)
	public String address = null;
	@Column(name = CONTACT_PHONE, notNull = true, onNullConflict = Column.ConflictAction.IGNORE)
	public String phone = null;
	@Column(name = CONTACT_NOTES)
	public String notes = null;
}
