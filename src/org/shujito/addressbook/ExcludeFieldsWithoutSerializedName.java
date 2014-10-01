package org.shujito.addressbook;

import java.lang.annotation.Annotation;
import java.util.Collection;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.SerializedName;

public class ExcludeFieldsWithoutSerializedName implements ExclusionStrategy
{
	@Override
	public boolean shouldSkipClass(Class<?> clss)
	{
		return false;
	}
	
	@Override
	public boolean shouldSkipField(FieldAttributes fieldAttributes)
	{
		// get all annotations, skip these without @SerializedName
		Collection<Annotation> annotations = fieldAttributes.getAnnotations();
		for (Annotation a : annotations)
		{
			if (a instanceof SerializedName)
			{
				return false;
			}
		}
		return true;
	}
}