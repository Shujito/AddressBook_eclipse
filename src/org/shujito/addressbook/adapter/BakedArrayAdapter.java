package org.shujito.addressbook.adapter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class BakedArrayAdapter<T> extends ArrayAdapter<T>
{
	protected List<T> objects = null;
	
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
