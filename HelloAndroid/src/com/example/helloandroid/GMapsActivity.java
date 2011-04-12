package com.example.helloandroid;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GMapsActivity extends MapActivity implements OnGestureListener,
		OnDoubleTapListener {

	private static final String GMAPS_ACTIVITY_LOG_TAG = "GMaps Activity CLASS";
	private static final int DEFAULT_ZOOM = 15;

	private MapView mapView;
	private MapController mapController;
	private MyLocationOverlay myLocationOverlay;

	private List<Overlay> mapOverlays;
	private CustomItemizedOverlay itemizedOverlay;
	private View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		// Creating and initializing Map
		initialiseMapView();
		// Setting the map location overlay
		initialiseMapLocationOverlay();

		//
		Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
		itemizedOverlay = new CustomItemizedOverlay(drawable, this);

	}

	private void initialiseMapView() {
		mapView = (MapView) findViewById(R.id.map_view);

		// Adding zoom controls to Map
		mapView.setBuiltInZoomControls(true);
		mapView.displayZoomControls(true);
		mapView.setSatellite(false);

		// Getting the MapController to fine tune settings
		mapController = mapView.getController();
		mapController.setZoom(DEFAULT_ZOOM);
	}

	private void initialiseMapLocationOverlay() {
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		mapOverlays = mapView.getOverlays();

		mapOverlays.add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapController.animateTo(myLocationOverlay.getMyLocation());
			}
		});
		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
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

	public void setReturnIntent() {
		Log.e(GMAPS_ACTIVITY_LOG_TAG, "entrou no click");
		if (itemizedOverlay.getMapOverlays().size() == 0) {
			setResult(RESULT_CANCELED);

		} else {
			OverlayItem overlay = itemizedOverlay.getItem(0);

			Intent returnIntent = new Intent();
			if (overlay != null) {
				returnIntent.putExtra("latitude", overlay.getPoint().getLatitudeE6() / 1E6);
				returnIntent.putExtra("longitude", overlay.getPoint().getLongitudeE6() / 1E6);

				Log.e(GMAPS_ACTIVITY_LOG_TAG, String.valueOf(returnIntent
						.getDoubleExtra("latitude", 0.0)));
				Log.e(GMAPS_ACTIVITY_LOG_TAG, String.valueOf(returnIntent
						.getDoubleExtra("longitude", 0.0)));

			} else {
				MyLocationOverlay locationOverlay = (MyLocationOverlay) mapOverlays.get(0);

				returnIntent.putExtra("latitude", locationOverlay.getLastFix()
						.getLatitude());
				returnIntent.putExtra("longitude", locationOverlay.getLastFix()
						.getLongitude());

				Log.e(GMAPS_ACTIVITY_LOG_TAG, "BLZ");
			}

			setResult(RESULT_OK, returnIntent);

		}
	}

	@Override
	public void finish() {
		Log.e(GMAPS_ACTIVITY_LOG_TAG, "entrou no FINISH");
		setReturnIntent();
		super.finish();
	}

	@Override
	protected void onPause() {
		myLocationOverlay.disableMyLocation();
		super.onPause();
	};

	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}

	/**
	 * Methods required by OnDoubleTapListener
	 **/
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// get the point clicked and setup a overlay for that point
		if (this.myLocationOverlay.getLastFix() != null) {
			mapOverlays.clear();

			GeoPoint point = mapView.getProjection().fromPixels((int) e.getX(),
					(int) e.getY());
			OverlayItem overlayitem = new OverlayItem(point, "Nova Posição",
					"lat: " + point.getLatitudeE6() / 1E6 + " \n" + "long: "
							+ point.getLongitudeE6() / 1E6);

			itemizedOverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlay);
		} else {
			Toast.makeText(this, "Procurando localização atual..",
					Toast.LENGTH_LONG).show();
		}

		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		return false;
	}

	/**
	 * Methods required by OnGestureListener
	 **/
	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}
