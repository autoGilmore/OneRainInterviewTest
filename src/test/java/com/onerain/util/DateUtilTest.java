package com.onerain.util;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testDayOfYear() {
		// Set up
		int year = 2018;
		int month = 6;
		int dayOfMonth = 22;
		int expected = 173;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == javaGetDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void testDayOfYear_check4Years() {
		// Set up
		int year = 2018;
		int month = 1;
		int dayOfMonth = 1;
		int expected = 1;
		int daysToCheck = 365 * 4; // check 4 years

		Calendar calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

		for (int days = 0; days <= daysToCheck; days++) {
			month = calendar.get(Calendar.MONTH) + 1;
			dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
			year = calendar.get(Calendar.YEAR);

			// Test
			int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

			// Verify
			assertTrue("Java fails on: " + getDateString(month, dayOfMonth, year),
					expected == javaGetDayOfYear(month, dayOfMonth, year));
			assertTrue("Util fails on: " + getDateString(month, dayOfMonth, year),
					dayOfYear == expected);

			// advance one day
			calendar.add(Calendar.DATE, 1);
			expected++;

			// reset for new year
			if (month == 12 && dayOfMonth == 31)
				expected = 1;
		}
	}

	@Test
	public void testDayOfYear_leapYear() {
		// Set up
		int year = 2020;
		int month = 12;
		int dayOfMonth = 31;
		int expected = 366;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == javaGetDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void testDayOfYear_lastDayLeapYear() {
		// Set up
		int year = 2020;
		int month = 12;
		int dayOfMonth = 31;
		int expected = 366;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == javaGetDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void testDayOfYear_dayOne() {
		// Set up
		int year = 2018;
		int month = 1;
		int dayOfMonth = 1;
		int expected = 1;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == javaGetDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void testDayOfYear_lastDay() {
		// Set up
		int year = 2018;
		int month = 12;
		int dayOfMonth = 31;
		// December 31, 2018 (Mon) Day 365
		int expected = 365;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == javaGetDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	private int javaGetDayOfYear(int month, int dayOfMonth, int year) {
		Calendar calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		return dayOfYear;
	}

	private String getDateString(int month, int dayOfMonth, int year) {
		return month + "/" + dayOfMonth + "/" + year;
	}

}
