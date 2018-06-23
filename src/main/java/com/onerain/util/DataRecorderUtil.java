package com.onerain.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataRecorderUtil {

	private static BufferedWriter _bufferedWriter = null;

	public static void recordData(String key, String value, String fileName) {
		if (key != null && !key.isEmpty() && value != null && !value.isEmpty() && fileName != null
				&& !fileName.isEmpty()) {
			File file = new File(fileName);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				_bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));
				_bufferedWriter.write(key + "," + value);
				_bufferedWriter.newLine();
				_bufferedWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (_bufferedWriter != null) {
					try {
						if (_bufferedWriter != null)
							_bufferedWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			// TODO: handle error
		}
	}

	public static String _readDataRecord(String fileName) {
		if (fileName != null && !fileName.isEmpty()) {
			Charset encoding = Charset.defaultCharset();
			try {
				return readFile(fileName, encoding);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
