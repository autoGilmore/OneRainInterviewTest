package com.onerain.util;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void test_dayOfYear() {
		// Set up
		int year = 2018;
		int month = 6;
		int dayOfMonth = 22;
		int expected = 173;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == DateUtil.getDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void test_dayOfYear_check4Years() {
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
			assertTrue("Java fails on: " + DateUtil.getDayOfYear(month, dayOfMonth, year),
					expected == DateUtil.getDayOfYear(month, dayOfMonth, year));
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
	public void test_dayOfYear_leapYear() {
		// Set up
		int year = 2020;
		int month = 12;
		int dayOfMonth = 31;
		int expected = 366;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == DateUtil.getDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void test_dayOfYear_lastDayLeapYear() {
		// Set up
		int year = 2020;
		int month = 12;
		int dayOfMonth = 31;
		int expected = 366;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == DateUtil.getDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void test_dayOfYear_dayOne() {
		// Set up
		int year = 2018;
		int month = 1;
		int dayOfMonth = 1;
		int expected = 1;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == DateUtil.getDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void test_dayOfYear_lastDay() {
		// Set up
		int year = 2018;
		int month = 12;
		int dayOfMonth = 31;
		// December 31, 2018 (Mon) Day 365
		int expected = 365;

		// Test
		int dayOfYear = DateUtil.dayOfYear(month, dayOfMonth, year);

		// Verify
		assertTrue(expected == DateUtil.getDayOfYear(month, dayOfMonth, year));
		assertTrue(dayOfYear == expected);
	}

	@Test
	public void test_convertDateToTimeInSeconds() {
		// Set up
		int year = 2018;
		int month = 1;
		int dayOfMonth = 1;
		long expected = 1514764800;

		// Test
		long seconds = DateUtil.secondsSinceEpoch(month, dayOfMonth, year);

		// Verify
		assertTrue(seconds == expected);
		String dateString = DateUtil.getDateStringFromEpochSeconds(seconds);
		assertTrue("was: " + dateString, dateString.equals(month + "/" + dayOfMonth + "/" + year));
	}

	// Helper to format data to MM/dd/yy string
	//
	private String getDateString(int month, int dayOfMonth, int year) {
		return month + "/" + dayOfMonth + "/" + year;
	}

}
