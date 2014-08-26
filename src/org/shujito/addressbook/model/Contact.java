package org.shujito.addressbook.model;

import java.io.Serializable;

/**
 * A simple {@link Contact} model.
 * @author shujito
 *
 */
public class Contact implements Serializable
{
	private static final long serialVersionUID = 1L;
	public String name = null;
	public String lastname = null;
	public String address = null;
	public String phone = null;
	public String notes = null;
}
