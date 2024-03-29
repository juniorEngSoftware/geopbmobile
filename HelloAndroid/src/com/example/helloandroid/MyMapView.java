package com.example.helloandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

import com.google.android.maps.MapView;

public class MyMapView extends MapView {
	private Context context;
	private GestureDetector gestureDetector;

	public MyMapView(Context aContext, AttributeSet attrs) {
		super(aContext, attrs);
		context = aContext;

		gestureDetector = new GestureDetector((OnGestureListener) context);
		gestureDetector.setOnDoubleTapListener((OnDoubleTapListener) context);
	}

	// Override the onTouchEvent() method to intercept events and pass them
	// to the GestureDetector. If the GestureDetector doesn't handle the event,
	// propagate it up to the MapView.
	public boolean onTouchEvent(MotionEvent ev) {
		if (this.gestureDetector.onTouchEvent(ev))
			return true;
		else
			return super.onTouchEvent(ev);
	}
}
