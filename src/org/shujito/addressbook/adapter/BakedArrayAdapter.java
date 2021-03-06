package org.shujito.addressbook.adapter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Prepared {@link ArrayAdapter} that leverages the implementation of common methods
 * like add, remove, getCount, getItem ...
 * @author shujito
 *
 * @param <T> Data type to be used for this {@link ArrayAdapter}
 */
public abstract class BakedArrayAdapter<T> extends ArrayAdapter<T>
{
	private List<T> objects = null;
	
	protected BakedArrayAdapter(Context context, int resource, List<T> objects)
	{
		super(context, resource, objects);
		this.objects = objects;
	}
	
	@Override
	public final void add(T object)
	{
		this.objects.add(object);
	}
	
	@Override
	public final void addAll(Collection<? extends T> collection)
	{
		this.objects.addAll(collection);
	}
	
	@Override
	public final void addAll(@SuppressWarnings("unchecked") T... items)
	{
		this.addAll(Arrays.asList(items));
	}
	
	@Override
	public final void remove(T object)
	{
		this.objects.remove(object);
	}
	
	@Override
	public final int getCount()
	{
		if (this.objects != null)
			return this.objects.size();
		return 0;
	}
	
	@Override
	public final T getItem(int position)
	{
		if (this.objects != null)
			return this.objects.get(position);
		return null;
	}
	
	@Override
	public long getItemId(int position)
	{
		return -1;
	}
}
