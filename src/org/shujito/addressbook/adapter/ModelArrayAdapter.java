package org.shujito.addressbook.adapter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;

/**
 * Prepared {@link ArrayAdapter} that leverages the implementation of common methods
 * like add, remove, getCount, getItem ...
 * It also persists data!
 * @author shujito
 *
 * @param <T> Data type to be used for this {@link ArrayAdapter}
 */
public abstract class ModelArrayAdapter<T extends Model> extends ArrayAdapter<T>
{
	private List<T> objects = null;
	private Class<T> class_ = null;
	
	protected ModelArrayAdapter(Context context, int resource, Class<T> class_)
	{
		super(context, resource);
		this.class_ = class_;
		this.objects = this.select();
	}
	
	@Override
	public final void add(T object)
	{
		object.save();
	}
	
	@Override
	public final void addAll(Collection<? extends T> collection)
	{
		@SuppressWarnings("unchecked")
		T[] vt = (T[]) collection.toArray();
		ActiveAndroid.beginTransaction();
		for (T t : vt)
		{
			this.add(t);
			ActiveAndroid.setTransactionSuccessful();
		}
		ActiveAndroid.endTransaction();
	}
	
	@Override
	public final void addAll(@SuppressWarnings("unchecked") T... items)
	{
		this.addAll(Arrays.asList(items));
	}
	
	@Override
	public final void remove(T object)
	{
		object.delete();
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
	public final long getItemId(int position)
	{
		if (this.objects != null && this.objects.size() > position)
		{
			Model model = this.objects.get(position);
			if (model != null)
			{
				return model.getId();
			}
		}
		return position;
	}
	
	@Override
	public final boolean hasStableIds()
	{
		return this.objects != null;
	}
	
	@Override
	public final void notifyDataSetChanged()
	{
		this.objects = this.select();
		super.notifyDataSetChanged();
	}
	
	public List<T> select()
	{
		return new Select()
			.from(this.class_)
			.execute();
	}
}
