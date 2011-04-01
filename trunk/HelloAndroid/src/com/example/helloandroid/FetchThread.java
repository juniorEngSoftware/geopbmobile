package com.example.helloandroid;
 

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import model.Feature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.thoughtworks.xstream.XStream;

public class FetchThread extends Thread{

	public static final String HTTP_ERROR_MESSAGE = "URL não é uma Http URL";
	public static final String URL_ERROR_MESSAGE = "URL errada ou mal formatada";
	public static final String OK_MESSAGE = "OK";

	public Handler messageHandler;
	private String xmlURL;
	
	public FetchThread(Handler handler, String xmlURL){
		this.messageHandler = handler;
		this.xmlURL = xmlURL;
	}
	
	@Override
	public void run(){
		InputStream inputStream = null;
        Message msg = Message.obtain();
        XStream xstream = new XStream();
        Bundle bundle = new Bundle();
        try {
        	inputStream = GeoPBMobileUtil.openHttpConnection(xmlURL);
          
			ArrayList<Feature> featureList = (ArrayList<Feature>) xstream.fromXML(inputStream);
            for (int i = 0; i < featureList.size(); i++) {
            	Log.e("===FETCH===", featureList.get(i).toString());
			}
				
            bundle.putParcelableArrayList("features", featureList);
//            bundle.putParcelable("features", (Parcelable) outraLista);
            bundle.putString("erro", OK_MESSAGE);
            
			inputStream.close();		  
        }catch (MalformedURLException e) {
        	bundle.putString("erro", URL_ERROR_MESSAGE);
		}
        catch (IOException e) {
        	bundle.putString("erro", HTTP_ERROR_MESSAGE);
        }finally{
        	msg.setData(bundle);        	
        }
        
		messageHandler.sendMessage(msg);

	}
}
