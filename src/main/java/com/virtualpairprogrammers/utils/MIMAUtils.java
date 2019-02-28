package com.virtualpairprogrammers.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MIMAUtils {

	public static String timestampToDate(long time) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Timestamp ts=new Timestamp(time);  
        Date date=new Date(ts.getTime()); 
        return inputFormat.format(date); 
	}
	
	public static long dateToTimestamp(String date) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		try {
			date1 = inputFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Timestamp ts=new Timestamp(date1.getTime());  
        return ts.getTime();
	}
	
}
