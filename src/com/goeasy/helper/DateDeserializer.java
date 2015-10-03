package com.goeasy.helper;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class DateDeserializer implements JsonDeserializer<Date> {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	   public Date deserialize(JsonElement dateStr, Type typeOfSrc, JsonDeserializationContext context)
	   {
	      try
	      {
	    	  System.out.println(" ### Befor Deserializing date : "+dateStr.getAsString());
	         return dateFormat.parse(dateStr.getAsString());
	      }
	      catch (ParseException e)
	      {
	         e.printStackTrace();
	      }
	      return null;
	   }

}
