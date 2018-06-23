package com.onerain.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {

	// Get the number of days elapsed since the start of the year.
	//
	public static int dayOfYear(int month, int dayOfMonth, int year) {

		// TODO: magic numbers for days in month. Create constants or get from
		// Calendar.
		if (month == 2) {
			dayOfMonth += 31;
		} else if (month == 3) {
			dayOfMonth += 59;
		} else if (month == 4) {
			dayOfMonth += 90;
		} else if (month == 5) {
			dayOfMonth += 31 + 28 + 31 + 30;
		} else if (month == 6) {
			dayOfMonth += 31 + 28 + 31 + 30 + 31;
		} else if (month == 7) {
			dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30;
		} else if (month == 8) {
			dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31;
		} else if (month == 9) {
			dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
		} else if (month == 10) {
			dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
		} else if (month == 11) {
			dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
		} else if (month == 12) {
			dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
		}

		// add leap day
		if (isLeapYear(year) && month > 2)
			dayOfMonth += 1;

		return dayOfMonth;
	}

	// Java version of elapsed days of the year.
	//
	public static int getDayOfYear(int month, int dayOfMonth, int year) {
		Calendar calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		return dayOfYear;
	}

	// Time in seconds since Midnight, Jan. 1st 1970 UTC
	//
	public static long secondsSinceEpoch(int month, int dayOfMonth, int year) {
		Calendar calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		return calendar.getTimeInMillis() / 1000L;
	}

	// Convert epoch seconds to date string.
	//
	public static String getDateStringFromEpochSeconds(long epochSeconds) {
		Date date = new Date(epochSeconds * 1000);
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MMM/dd");
		return sdf.format(calendar.getTime());
	}

	private static boolean isLeapYear(int year) {
		// TODO: test. was copied from:
		// https://stackoverflow.com/questions/725098/leap-year-calculation
		if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))
			return true;
		return false;
	}

}
