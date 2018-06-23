package com.onerain.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.onerain.config.DataConsts;
import com.onerain.data.WeatherData;
import com.onerain.util.DataRecorderUtil;

public class WeatherStationServer extends Thread {

	public final int SERVER_PORT = DataConsts.WEATHER_DATA_SERVER_PORT;

	private ServerSocket serverSocket;

	public void start() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			while (true)
				new WeatherSocketHandler(serverSocket.accept()).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopSocket() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class WeatherSocketHandler extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;

		public WeatherSocketHandler(Socket socket) {
			this.clientSocket = socket;
		}

		public void run() {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine;
				WeatherData data = null;
				while ((inputLine = in.readLine()) != null) {
					// TODO: ACK ?
					out.println(inputLine);
					data = new WeatherData(inputLine);
				}

				if (data != null) {
					// store RAIN
					DataRecorderUtil.recordData(data.getEpochSeconds() + "", data.getRainAmountString(),
							DataConsts.WEATHER_DATA_RAIN_CSV_FILE);
					// store DEPTH
					DataRecorderUtil.recordData(data.getEpochSeconds() + "", data.getDepthAmountString(),
							DataConsts.WEATHER_DATA_DEPTH_CSV_FILE);
				}

				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
