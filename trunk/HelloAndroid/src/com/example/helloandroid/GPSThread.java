package com.example.helloandroid;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class GPSThread extends Thread {

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 100; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000 * 60 * 2; // two minutes in Milliseconds

	public final static int STATE_DONE = 0;
	public final static int STATE_RUNNING = 1;
	private static final long SLEEP_TIME = 1000;

	private int mState;
	private Handler messageHandler;
	private LocationManager locationManager;
	private Context context;
	private Message msg;

	public GPSThread(Handler messageHandler, Context context) {
		this.messageHandler = messageHandler;
		this.context = context;
		locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
	}

	@Override
	public void run() {
		mState = STATE_RUNNING;
		Log.e("GPS THREAD", "entrou no RUN DA THREAD");

		while (mState == STATE_RUNNING) {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				Log.e("ERROR", "Thread Interrupted");
			}
			msg = messageHandler.obtainMessage();
			Criteria locationCritera = new Criteria();
			setLocationCriteria(locationCritera);
			String providerName = locationManager.getBestProvider(
					locationCritera, true);

			if (providerName == null) {
				Log.e("GPS THREAD", "providerName IGUAL A NULL");
			}

			if (providerName != null
					&& locationManager.isProviderEnabled(providerName)) {
				// Provider is enabled
				locationManager.requestLocationUpdates(providerName,
						MINIMUM_TIME_BETWEEN_UPDATES,
						MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
						this.locationListener);
			} else {
				// Provider not enabled, prompt user to enable it
				Toast.makeText(context, "Ligue o seu GPS", Toast.LENGTH_LONG).show();
				Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				context.startActivity(myIntent);
//				msg.setData(bundle);
//				messageHandler.sendMessage(msg);
			}
		}
	}

	/*
	 * sets the current state for the thread, used to stop the thread
	 */
	public void setState(int state) {
		mState = state;
	}

	private void setLocationCriteria(Criteria locationCritera) {
		locationCritera.setAccuracy(Criteria.ACCURACY_FINE);
		locationCritera.setAltitudeRequired(false);
		locationCritera.setBearingRequired(false);
		locationCritera.setCostAllowed(true);
		locationCritera.setPowerRequirement(Criteria.POWER_LOW);
	}

	private final LocationListener locationListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			GPSThread.this.gpsLocationReceived(location);
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

	};

	public void gpsLocationReceived(Location location) {
		Bundle bundle = new Bundle();
		
		Log.e("GPS THREAD", "entrou no gpsLOCACTION");
		
		bundle.putDouble("latitude", location.getLatitude());
		bundle.putDouble("longitude", location.getLongitude());
		msg.setData(bundle);
		messageHandler.sendMessage(msg);

		// String message = String.format(
		// "Localizacao atual eh: \n Longitude: %1$s \n Latitude: %2$s",
		// location.getLongitude(), location.getLatitude());

	}

}
