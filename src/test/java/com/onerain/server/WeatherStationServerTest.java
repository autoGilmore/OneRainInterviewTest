package com.onerain.server;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.onerain.config.DataConsts;
import com.onerain.util.DataRecorderUtil;

import static org.junit.Assert.*;

public class WeatherStationServerTest {

	private WeatherStationServer _server = new WeatherStationServer();
	private WeatherStationTestClient _testClient;

	@Before
	public void setup() throws UnknownHostException, IOException {
		if (_testClient == null) {
			_testClient = new WeatherStationTestClient();
			_testClient.startConnection("127.0.0.1", _server.SERVER_PORT);
		}
		assertNotNull(_testClient);
	}

	@After
	public void tearDown() throws IOException {
		if (_testClient != null)
			_testClient.stopConnection();
	}

	@Test
	public void test_clientCanSendData() throws IOException {
		// Set up
		String data = "18/JUN/12,14:15,RAIN,12,DEPTH,89";

		// Test
		String msgReceived = _testClient.sendMessage(data);

		// Verify
		assertNotNull(msgReceived);
		assertTrue("was: " + msgReceived, data.contains(msgReceived));
	}

	@Test
	public void test_clientDataStored() throws IOException {
		// Set up
		String data = "18/JUN/12,14:15,RAIN,12,DEPTH,89";
		String rainFile = DataConsts.WEATHER_DATA_RAIN_CSV_FILE;
		String expectedRain = "1528834500,12";
		String depthFile = DataConsts.WEATHER_DATA_DEPTH_CSV_FILE;
		String expectedDepth = "1528834500,89";

		// Test
		String msgReceived = _testClient.sendMessage(data);
		assertNotNull(msgReceived);

		// Verify
		String rainCSV = DataRecorderUtil._readDataRecord(rainFile);
		assertTrue("was: " + rainCSV, rainCSV.contains(expectedRain));
		String depthCSV = DataRecorderUtil._readDataRecord(depthFile);
		assertTrue("was: " + depthCSV, depthCSV.contains(expectedDepth));
	}

}
