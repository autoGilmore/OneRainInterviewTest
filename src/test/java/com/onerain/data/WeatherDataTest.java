package com.onerain.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.onerain.util.DateUtil;

public class WeatherDataTest {

	@Test
	public void test_parseData() {
		// Set up
		String data = "18/JUN/12,14:15,RAIN,12,DEPTH,89";

		// Test
		WeatherData weatherData = new WeatherData(data);

		// Verify
		assertNotNull(weatherData);
		assertTrue("was: " + weatherData.getDateString(),
				weatherData.getDateString().equals("18/JUN/12"));
		assertTrue("was: " + weatherData.getTimeString(),
				weatherData.getTimeString().equals("14:15"));
		assertTrue("was: " + weatherData.getRainAmountString(),
				weatherData.getRainAmountString().equals("12"));
		assertTrue("was: " + weatherData.getDepthAmountString(),
				weatherData.getDepthAmountString().equals("89"));
		String epochToDate = DateUtil.getDateStringFromEpochSeconds(weatherData.getEpochSeconds());
		assertTrue("Should be: " + weatherData.getDateString() + "was: " + epochToDate,
				weatherData.getDateString().equalsIgnoreCase(epochToDate));
	}

	@Test
	public void test_parseData_noDepth() {
		// Set up
		String data = "18/Jun/04,	5:22,	RAIN,	5,	STAGE,	5";

		// Test
		WeatherData weatherData = new WeatherData(data);

		// Verify
		assertNotNull(weatherData);
		assertTrue("was: " + weatherData.getDateString(),
				weatherData.getDateString().equals("18/Jun/04"));
		assertTrue("was: " + weatherData.getTimeString(),
				weatherData.getTimeString().equals("5:22"));
		assertTrue("was: " + weatherData.getRainAmountString(),
				weatherData.getRainAmountString().equals("5"));
		assertTrue("was: " + weatherData.getDepthAmountString(),
				weatherData.getDepthAmountString().equals("0"));
	}

	@Test
	public void test_parseData_zeroRain() {
		// Set up
		String data = "18/Jun/04,	4:27,	RAIN,	0,	STAGE,	43";

		// Test
		WeatherData weatherData = new WeatherData(data);

		// Verify
		assertNotNull(weatherData);
		assertTrue("was: " + weatherData.getDateString(),
				weatherData.getDateString().equals("18/Jun/04"));
		assertTrue("was: " + weatherData.getTimeString(),
				weatherData.getTimeString().equals("4:27"));
		assertTrue("was: " + weatherData.getRainAmountString(),
				weatherData.getRainAmountString().equals("0"));
		assertTrue("was: " + weatherData.getDepthAmountString(),
				weatherData.getDepthAmountString().equals("0"));
	}
}
