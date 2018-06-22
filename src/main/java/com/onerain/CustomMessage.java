package com.onerain;

import com.onerain.util.DateUtil;

public class CustomMessage {

	public static String daysUntilNewYearMessage(int month, int dayOfMonth, int year) {

		int yearTotalDays =  DateUtil.dayOfYear(12, 31, year);
		int daysRemaining = yearTotalDays - DateUtil.dayOfYear(month, dayOfMonth, year);
		if(daysRemaining == 0)
			return "Tommorrow is " + (year + 1) + "!";
		if (daysRemaining > 1)
			return "You have " + daysRemaining + " days until " + (year + 1) + "!";

		return "You have " + daysRemaining + " day until " + (year + 1) + "!";
	}

}
