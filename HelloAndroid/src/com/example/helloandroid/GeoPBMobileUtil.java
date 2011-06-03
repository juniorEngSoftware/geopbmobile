package com.example.helloandroid;

import java.io.File;
import java.io.FileInputStream;
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

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class GeoPBMobileUtil {

	public static final int MAP_CODE = 10;
	public static final int CAMERA_CODE = 11;
	public static final int FILE_EXPLORER_CODE = 12;
	public static final int MULTIPLE_CHOICE_CODE = 13;

	public static final String FILE_SEPARATOR = System
			.getProperty("file.separator");
	public static final String FILE_DIR = System.getProperty("user.dir")
			+ FILE_SEPARATOR;

	public static InputStream openHttpConnection(String urlString)
			throws MalformedURLException, IOException {

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

		File file = new File("/sdcard/output.xml");
		try {
			file.createNewFile();
			FileWriter fileWriter;
			fileWriter = new FileWriter(file);
			fileWriter.write(data);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// FileUtils.writeStringToFile(file, data);
	}

	public static String convertFiletoBase64(String filePath) {
		try {
			File file = new File(filePath);
			byte[] encoded = Base64.encodeBase64(getBytesFromFile(file));
			String encodedString = new String(encoded);
			return encodedString;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	// Returns the contents of the file in a byte array.
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public static String sendFile(String xmlData, String link) {
		// Create a new HttpClient and Post Header  
        HttpClient httpclient = new DefaultHttpClient();  
        HttpPost httppost = new HttpPost(link);
        httppost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");

        try {  
            // Add your data  
        	StringEntity se = new StringEntity(xmlData, HTTP.UTF_8);
        	se.setContentType("text/xml");  
        	httppost.setEntity(se);  

        	HttpResponse httpResponse = httpclient.execute(httppost);
        	HttpEntity resEntity = httpResponse.getEntity();
            return EntityUtils.toString(resEntity);
        } catch (ClientProtocolException e) {  
            // TODO Auto-generated catch block  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
        }
		return "";  
		
	}

}
