package com.onerain;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomMessageTest {

	@Test
	public void test_daysUntilNewYearMessage() {
		// Set up
		int year = 2018;
		int month = 6;
		int dayOfMonth = 22;
		// 192 = 365 (days in year) - 173 (day of year)
		String expected = "You have 192 days until 2019!";

		// Test
		String daysUntilNewYear = CustomMessage.daysUntilNewYearMessage(month, dayOfMonth, year);

		// Verify
		assertTrue("was: " + daysUntilNewYear, daysUntilNewYear.equals(expected));
	}

	@Test
	public void test_daysUntilNewYearMessage_oneDayRemaining() {
		// Set up
		int year = 2018;
		int month = 12;
		int dayOfMonth = 30;
		String expected = "You have 1 day until 2019!";

		// Test
		String daysUntilNewYear = CustomMessage.daysUntilNewYearMessage(month, dayOfMonth, year);

		// Verify
		assertTrue("was: " + daysUntilNewYear, daysUntilNewYear.equals(expected));
	}

	@Test
	public void test_daysUntilNewYearMessage_newYearsEve() {
		// Set up
		int year = 2018;
		int month = 12;
		int dayOfMonth = 31;
		String expected = "Tommorrow is 2019!";

		// Test
		String daysUntilNewYear = CustomMessage.daysUntilNewYearMessage(month, dayOfMonth, year);

		// Verify
		assertTrue("was: " + daysUntilNewYear, daysUntilNewYear.equals(expected));
	}
}
