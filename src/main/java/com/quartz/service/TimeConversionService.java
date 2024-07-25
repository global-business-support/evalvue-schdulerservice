package com.quartz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class TimeConversionService {


	public Date convertUnixTimeToDate(Object unixTime) throws ParseException {
		if (unixTime.equals(null)) {
			return null;
		}
		System.out.println(unixTime);
		long timestamp = Long.parseLong(unixTime.toString()) * 1000L;
		Date date = new Date(timestamp);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getDefault());
		String localDateString = sdf.format(date);
		return sdf.parse(localDateString);

	}

}