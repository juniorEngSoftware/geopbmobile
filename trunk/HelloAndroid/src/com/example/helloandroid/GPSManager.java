package com.example.helloandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class GPSManager {
	private static final int GPS_PROGRESS_DIALOG = 0;

	protected static final String OK_MESSAGE = null;

	private String GPS_PROGRESS_MESSAGE  = "Obtendo localização...";
	
	private String latitude;
	private String longitude;
	private ProgressDialog gpsProgressDialog;
	private LocationManager locationManager;
	private Context context;

	private GPSThread gpsThread;

//	private HandlerThread gpsThread;
	
	
	public GPSManager(Context context) {
		this.context = context;
		this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public void start() {
		
		gpsProgressDialog = ProgressDialog.show(context, "", GPS_PROGRESS_MESSAGE);
		
//		gpsThread = new HandlerThread("GPS Thread");
		gpsThread = new GPSThread(messageHandler, context);
		gpsThread.start();
//		
//		new Handler(gpsThread.getLooper()).post(
//				new Runnable() {
//					@Override
//					public void run() {
//						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, GPSManager.this.locationListener);
//					}
//				});
//		
//		new GPSThread(this.messageHandler, this.context){}.start();
	}	
	
	final Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
				gpsProgressDialog.dismiss();
				gpsThread.setState(GPSThread.STATE_DONE);

				setLatitude(String.valueOf(msg.getData().getDouble("latitude")));
				setLongitude(String.valueOf(msg.getData().getDouble("longitude")));

		}
	};
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLatitude() {
		return latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLongitude() {
		return longitude;
	}
}
