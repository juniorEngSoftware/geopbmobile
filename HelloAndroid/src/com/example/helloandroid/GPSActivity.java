package com.example.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GPSActivity extends Activity {

	private static final String GPS_ACTIVITY_LOG_TAG = "GPS Activity CLASS";
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 100; /* in Meters */
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000 * 60 * 2; /* two minutes in Milliseconds */

	private LocationManager locationManager;

	private Button retrieveLocationButton;
	private TextView retrieveLocationText;
	private String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps);

		retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button);
		retrieveLocationText = (TextView) findViewById(R.id.retrieve_location_text);

		retrieveLocationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				locationManager = (LocationManager) GPSActivity.this.getSystemService(Context.LOCATION_SERVICE);
				Criteria locationCritera = new Criteria();
				setLocationCriteria(locationCritera);

				String providerName = locationManager.getBestProvider(locationCritera, true);

				if(providerName == null){
					Log.e(GPS_ACTIVITY_LOG_TAG, "providerName IGUAL A NULL");
				}
				
				if (providerName != null && locationManager.isProviderEnabled(providerName)) {
					// Provider is enabled
					locationManager.requestLocationUpdates(providerName,
							MINIMUM_TIME_BETWEEN_UPDATES,
							MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
							GPSActivity.this.locationListener);
				} else {
					// Provider not enabled, prompt user to enable it
					Toast.makeText(GPSActivity.this, "Ligue o seu GPS", Toast.LENGTH_LONG).show();
					Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					GPSActivity.this.startActivity(myIntent);
				}
			}

			private void setLocationCriteria(Criteria locationCritera) {
				locationCritera.setAccuracy(Criteria.ACCURACY_FINE);
				locationCritera.setAltitudeRequired(false);
				locationCritera.setBearingRequired(false);
				locationCritera.setCostAllowed(true);
				locationCritera.setPowerRequirement(Criteria.POWER_LOW);

			}
		});

	}

	public void onClick(View view) {
		finish();
	}

	@Override
	public void finish() {
		super.finish();
		Intent data = new Intent();
		data.putExtra("message", "LOCALIZACAO SETADO COM SUCESSO");
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this.locationListener);
	};
	
	@Override
	protected void onStop() {
		locationManager.removeUpdates(this.locationListener);
		super.onStop();
	};
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	private final LocationListener locationListener = new LocationListener() {

	    @Override
	    public void onLocationChanged(Location location) {
	        GPSActivity.this.gpsLocationReceived(location);
	    }
	    @Override
	    public void onProviderDisabled(String provider) {}

	    @Override
	    public void onProviderEnabled(String provider) {}

	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {}

	};
	
	public void gpsLocationReceived(Location location) {
		String message = String.format(
				"Localizacao atual eh: \n Longitude: %1$s \n Latitude: %2$s",
				location.getLongitude(), location.getLatitude());
		retrieveLocationText.setText(message);
		
	}
	
//	
//	protected void showCurrentLocation() {
//
//		retrieveLocationButton.setEnabled(false);
//		Location location = locationManager
//				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//		if (location != null) {
//			message = String.format(
//					"Localizacao atual \n Latitude: %1$s \n Longitude: %2$s",
//					location.getLatitude(), location.getLongitude());
//
//			retrieveLocationText.setText(message);
//		}
//	}
//	private class MyLocationListener implements LocationListener {
//
//		public void onLocationChanged(Location location) {
//			GPSActivity.this.gpsLocationReceived(location);
//			Toast.makeText(GPSActivity.this, "NOVA LOCALIZACAO",Toast.LENGTH_LONG).show();
//			retrieveLocationButton.setEnabled(true);
//		}
//
//		public void onStatusChanged(String s, int i, Bundle b) {
//		}
//
//		public void onProviderDisabled(String s) {
//		}
//
//		public void onProviderEnabled(String s) {
//		}
//
//	}



}
