package com.example.helloandroid;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.Bundle;
import android.util.Log;

public class GeoPBMobileUtil {

	/*
	 * public static List<Placemark> getPlacemarks(Kml ourKml) { List<Placemark>
	 * resultList = new LinkedList<Placemark>();
	 * 
	 * Document document = (Document) ourKml.getFeature();
	 * 
	 * List<Feature> list = document.getFeature();
	 * 
	 * for (Feature feature : list) { Placemark placemark = (Placemark) feature;
	 * resultList.add(placemark); }
	 * 
	 * return resultList; }
	 */

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
	
	
}
