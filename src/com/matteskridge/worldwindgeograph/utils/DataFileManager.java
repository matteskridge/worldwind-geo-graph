package com.matteskridge.worldwindgeograph.utils;

import java.io.*;

/**
 * @author Matt Eskridge
 * @created 10/20/14
 */
public class DataFileManager {
	public static String getFile(String path) {
		InputStream stream = getStream(path);

		if (stream == null) {
			return "";
		}

		String text = "", line;

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));

			while ((line = in.readLine()) != null) {
				text += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}

	public static InputStream getStream(String path) {
		String prefix = "com/matteskridge/worldwindgeograph/resources/";
		try {
			InputStream stream = DataFileManager.class.getResourceAsStream(prefix+path);
			if (stream != null)
				return stream;
		} catch (Exception e) {

		}

		File file = new File("src/"+prefix+path);

		try {
			return new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
