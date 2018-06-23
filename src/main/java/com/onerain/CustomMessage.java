package com.onerain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.onerain.config.DataConsts;
import com.onerain.data.WeatherData;
import com.onerain.util.DataRecorderUtil;
import com.onerain.util.DateUtil;
import com.onerain.util.TableBuilderUtil;

public class CustomMessage {

	public static String daysUntilNewYearMessage(int month, int dayOfMonth, int year) {

		int yearTotalDays = DateUtil.dayOfYear(12, 31, year);
		int daysRemaining = yearTotalDays - DateUtil.dayOfYear(month, dayOfMonth, year);
		if (daysRemaining == 0)
			return "Tommorrow is " + (year + 1) + "!";
		if (daysRemaining > 1)
			return "You have " + daysRemaining + " days until " + (year + 1) + "!";

		return "You have " + daysRemaining + " day until " + (year + 1) + "!";
	}

	public static String getHourlyRainReport() {
		// get the data
		String fileName = DataConsts.WEATHER_DATA_RAIN_CSV_FILE;
		String data = DataRecorderUtil._readDataRecord(fileName);
		String[] dataLines = data.split(System.getProperty("line.separator"));

		// convert data to WeaterData
		List<WeatherData> weatherDataList = new ArrayList<WeatherData>();
		for (String line : dataLines) {
			WeatherData weatherData = new WeatherData();
			weatherData.setRainDataFromCSVKeyValueString(line);
			weatherDataList.add(weatherData);
		}

		// sort weather data old -> new
		weatherDataList.sort(Comparator.comparingLong(WeatherData::getEpochSeconds));

		// prepare data
		TableBuilderUtil tb = new TableBuilderUtil();
		tb.addRow("Date", "Time", "Accumulation");

		// TODO: find oldest time
		// TODO: read weatherDataList by each hour and store accumulation value
		// TODO: add to row
		// TODO: if no data add time and zero accumulation
		// TODO: move out methods as needed

		tb.addRow("2018-06-12", "14:00", "17");

		return tb.toString();
	}

}
