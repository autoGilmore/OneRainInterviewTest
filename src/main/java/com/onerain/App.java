package com.onerain;

import com.onerain.server.WeatherStationServer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		System.out.println("Hello World! " + CustomMessage.daysUntilNewYearMessage(1, 2, 2018));

		// start weather station listening server
		WeatherStationServer server = new WeatherStationServer();
		server.start();

		// provide a hourly report
		System.out.println(CustomMessage.getHourlyRainReport());
	}
}
