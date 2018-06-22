package com.onerain.util;

public class DateUtil {

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

	private static boolean isLeapYear(int year) {
		// copied from:
		// https://stackoverflow.com/questions/725098/leap-year-calculation
		if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))
			return true;
		return false;
	}
}
