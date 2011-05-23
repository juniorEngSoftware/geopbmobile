package com.example.helloandroid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import model.Option;

import android.os.Bundle;
import android.util.Log;

public class GeoPBMobileUtil {
	
	public static final int MAP_CODE = 10;
	public static final int CAMERA_CODE = 11;
	public static final int FILE_EXPLORER_CODE = 12;
	public static final int MULTIPLE_CHOICE_CODE = 13;
	
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String FILE_DIR = System.getProperty("user.dir") + FILE_SEPARATOR;

	public static InputStream openHttpConnection(String urlString) throws MalformedURLException, IOException {

		InputStream input = null;
		int resCode = -1;

		URL url = new URL(urlString);
		URLConnection urlConn = url.openConnection();

		if (!(urlConn instanceof HttpURLConnection)) {
			throw new IOException("URL não é uma Http URL");
		}

		HttpURLConnection httpConn = (HttpURLConnection) urlConn;
		httpConn.setAllowUserInteraction(false);
		httpConn.setInstanceFollowRedirects(true);
		httpConn.setRequestMethod("GET");
		httpConn.connect();

		resCode = httpConn.getResponseCode();
		if (resCode != HttpURLConnection.HTTP_OK) {
			throw new MalformedURLException("URL errada ou mal formatada");
		}
		input = httpConn.getInputStream();
		
		
		return input;
	}

	public static String getData(InputStream inputStream) throws IOException {
		
		int BUFFER_SIZE = 2000;
		InputStreamReader isr = new InputStreamReader(inputStream);
		int charRead;
		String text = "";
		char[] inputBuffer = new char[BUFFER_SIZE];

		while ((charRead = isr.read(inputBuffer)) > 0) {
			// ---convert the chars to a String---
			String readString = String.copyValueOf(inputBuffer, 0, charRead);
			text += readString;
			inputBuffer = new char[BUFFER_SIZE];
		}
		isr.close();
		
		return text;

	}

	public static ArrayList<String> arrayListToString(List<Option> optionList) {
		ArrayList<String> optionsName = new ArrayList<String>();
		for (Option option : optionList) {
			optionsName.add(option.getName());
		}
		return optionsName;
		
		
	}
	
	public static void createXmlFile(String data) {
		
		File file = new File("C:\\output.xml");
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(data);	
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
//		FileUtils.writeStringToFile(file, data);
	}
	
	
}
