package com.goeasy.helper;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializer implements JsonSerializer<Date>{
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	   public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context)
	   {
	      return new JsonPrimitive(dateFormat.format(date));
	   }
}
