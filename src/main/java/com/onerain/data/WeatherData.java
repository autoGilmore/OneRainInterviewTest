package com.onerain.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class WeatherData {

	public final String RAIN = "RAIN";
	public final String DEPTH = "DEPTH";

	private String dateString = "";
	private String timeString = "";
	private String rainAmount = "0";
	private String depthAmount = "0";
	private long epochSeconds = 0;

	public WeatherData(String data) {
		parseData(data);
	}

	private void parseData(String data) {
		if (data != null && !data.isEmpty()) {
			data = data.replaceAll("\\s+", "");
			setDateFromData(data);
			setTimeFromData(data);
			setRainAmountFromData(data);
			setDepthAmountFromData(data);
			setEpochSeconds();
		}
	}

	private int setDateFromData(String data) {
		int index = data.indexOf(",");
		String dateStr = data.substring(0, index);
		setDateString(dateStr);
		return index;
	}

	private void setTimeFromData(String data) {
		int indexSeparator = data.indexOf(":");
		String substring = data.substring(indexSeparator - 2, indexSeparator + 3);
		String str = substring.replace(",", "");
		setTimeString(str.trim());
	}

	private void setRainAmountFromData(String data) {
		if (data.contains(RAIN)) {
			int rainIndex = data.indexOf(RAIN);
			String substring = data.substring(rainIndex + 5);
			int index = substring.indexOf(",");
			String rainAmt;
			if (index > 0)
				rainAmt = substring.substring(0, index);
			else
				rainAmt = substring;
			setRainAmount(rainAmt);
		}

	}

	private void setDepthAmountFromData(String data) {
		if (data.contains(DEPTH)) {
			int depthIndex = data.indexOf(DEPTH);
			String substring = data.substring(depthIndex + 6);
			int index = substring.indexOf(",");
			String depth;
			if (index > 0)
				depth = substring.substring(0, index);
			else
				depth = substring;
			setDepthAmount(depth);
		}
	}

	private void setDateString(String dateStr) {
		if (dateStr != null)
			this.dateString = dateStr;
	}

	private void setTimeString(String timeString) {
		if (timeString != null && timeString.contains(":"))
			this.timeString = timeString;
	}

	private void setEpochSeconds() {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MMM-dd HH:mm", Locale.ENGLISH);
			String timeDateString = getDateString() + " " + getTimeString();
			timeDateString = timeDateString.replaceAll("/", "-");
			calendar.setTime(sdf.parse(timeDateString));
			calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
			this.epochSeconds = calendar.getTimeInMillis() / 1000L;
		} catch (ParseException e) {
			// TODO: handle error
			e.printStackTrace();
		}
	}

	private void setRainAmount(String rainAmt) {
		if (rainAmt != null && !rainAmt.isEmpty()) {
			int amount = Integer.parseInt(rainAmt);
			if (amount >= 0)
				this.rainAmount = rainAmt;
		}
	}

	private void setDepthAmount(String depth) {
		if (depth != null && !depth.isEmpty()) {
			int amount = Integer.parseInt(depth);
			if (amount >= 0)
				this.depthAmount = depth;
		}
	}

	public String getDateString() {
		return this.dateString;
	}

	public String getTimeString() {
		return this.timeString;
	}

	public String getRainAmountString() {
		return this.rainAmount;
	}

	public String getDepthAmountString() {
		return this.depthAmount;
	}

	public long getEpochSeconds() {
		return this.epochSeconds;
	}
}
