package com.example.helloandroid;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class GMapsActivity extends MapActivity {

	private static final String GMAPS_ACTIVITY_LOG_TAG = "GMaps Activity CLASS";
	
	private MapView mapView;
	private GeoPoint geoPoint;
	private MapController mapController;
	private MyLocationOverlay myLocationOverlay;
	
	private double defaultLatitude = -7.225977;
	private double defaultLongitude = -35.894669;
	private long lastTouchTime = -1;

	private List<Overlay> overlays;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		// Creating and initializing Map
		mapView = (MapView) findViewById(R.id.map_view);
		
		initMap();
		
		mapView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(!overlays.isEmpty()) { 
			     overlays.clear(); 
			     mapView.invalidate();
				}
				myLocationOverlay.disableCompass();
				myLocationOverlay.disableMyLocation();

				Log.e(GMAPS_ACTIVITY_LOG_TAG, "CHAMOU EVENTO onTouch !!!!!!!!!!!!!!");
				
				return true;
			}
		});
		
		// Getting the MapController to fine tune settings
		mapController = mapView.getController();
		mapController.setZoom(15);

		// Adding zoom controls to Map
		mapView.setBuiltInZoomControls(true);
		mapView.displayZoomControls(true);
		
	}

	private void initMap() {
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		overlays = mapView.getOverlays();
		
	    overlays.add(myLocationOverlay);
	    myLocationOverlay.enableCompass();
	    myLocationOverlay.enableMyLocation();
	    myLocationOverlay.runOnFirstFix(new Runnable() {
	    	public void run() {
	    		mapController.animateTo(myLocationOverlay.getMyLocation());
	    	}
	    });
	    mapView.invalidate();
	    mapView.setSatellite(false);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	// @Override
	// public boolean onTouchEvent(MotionEvent ev) {
	// if (ev.getAction() == MotionEvent.ACTION_DOWN) {
	// long thisTime = System.currentTimeMillis();
	//
	// if (thisTime - lastTouchTime < 250) {
	// // Double tap
	// mapController.zoomInFixing((int) ev.getX(), (int) ev.getY());
	// lastTouchTime = -1;
	// } else {
	// // Too slow
	// lastTouchTime = thisTime;
	// }
	// }
	// return super.onTouchEvent(ev);
	// }

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			long thisTime = System.currentTimeMillis();
//
//			if (thisTime - lastTouchTime < 250) {
//				GeoPoint p = mapView.getProjection().fromPixels((int) event.getX(), (int) event.getY());
//				Toast.makeText(getBaseContext(), p.getLatitudeE6() / 1E6 + "," + p.getLongitudeE6()/ 1E6, Toast.LENGTH_SHORT).show();
//				lastTouchTime = -1;
//			} else {
//				// Too slow
//				lastTouchTime = thisTime;
//			}
			Log.e(GMAPS_ACTIVITY_LOG_TAG, "CHAMOU EVENTO onTouch");
			
			
			return true;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.map_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.satellite_option:
			mapView.setSatellite(true);
			return true;
		case R.id.map_option:
			mapView.setSatellite(false);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onClick(View view) {
		finish();
	}

	@Override
	public void finish() {
		super.finish();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableMyLocation();
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}

	class MapOverlay extends com.google.android.maps.Overlay {

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,	long when) {
			super.draw(canvas, mapView, shadow);
			// ---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			mapView.getProjection().toPixels(geoPoint, screenPts);
			// ---add the marker---
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);
			return true;
		}
	}

}
