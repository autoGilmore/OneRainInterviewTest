package com.onerain.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.onerain.config.DataConsts;
import com.onerain.data.WeatherData;

public class DataRecorderUtilTest {

	@Test
	public void test_recordData() {
		// Set up
		WeatherData weatherData = new WeatherData("18/Jun/12,9:08,RAIN,8,STAGE,19");
		String fileName = DataConsts.WEATHER_DATA_RAIN_CSV_FILE;
		String expected = "1528816080,8";

		// Test
		DataRecorderUtil.recordData(weatherData.getEpochSeconds() + "",
				weatherData.getRainAmountString(), fileName);

		// Verify
		String dataString = DataRecorderUtil._readDataRecord(fileName);
		assertTrue("was: " + dataString, dataString.contains(expected));
	}

}
