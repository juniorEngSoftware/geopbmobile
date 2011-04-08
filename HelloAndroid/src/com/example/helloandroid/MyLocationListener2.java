package com.example.helloandroid;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocationListener2 implements LocationListener {

	
	private double latitude = 0.0;
	private double longitude = 0.0;

	private Button retrieveLocationButton;

	public MyLocationListener2() {
	}

	public MyLocationListener2(Button retrieveLocationButton) {
		this.retrieveLocationButton = retrieveLocationButton;
	}

	@Override
	public void onLocationChanged(Location location) {
		String message = String.format(
				"New Location \n Latitude: %1$s \n Longitude: %2$s",
				location.getLatitude(), location.getLongitude()
		);
//		latitude = location.getLatitude();
//		longitude = location.getLongitude();
//		locationText.setText(message);
		retrieveLocationButton.setEnabled(true);
	}

	@Override
	public void onStatusChanged(String s, int i, Bundle b) {
	}

	@Override
	public void onProviderDisabled(String s) {
	}
	
	@Override
	public void onProviderEnabled(String s) {
	}
}