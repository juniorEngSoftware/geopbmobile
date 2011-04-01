package model;


import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.helloandroid.FormActivity;
import com.example.helloandroid.GPSActivity;
import com.example.helloandroid.HelloAndroid;
import com.example.helloandroid.R;

public class GPSFeature extends Feature{

	private static final String GPS_LOG_TAG = "GPSFeature CLASS";
	
	private Button gpsButton;
	private Button mapButton;
	private TextView gpsText;
	private LocationManager locationManager;
	private String content;
	
	public GPSFeature() {
		
	}
	
	public GPSFeature(Parcel in) {
		super.readFromParcel(in);
	}

	@Override
	public int getType() {
		return R.layout.location;
	}
	
	@Override
	public View setUpView(View inflate, LayoutInflater inflater){
		RelativeLayout gpsRelativeLayout = (RelativeLayout) inflate;
		
		gpsButton = (Button) gpsRelativeLayout.getChildAt(0);
		mapButton = (Button) gpsRelativeLayout.getChildAt(1);
		
		setGPSButtonEvent(gpsRelativeLayout, inflater);
		
		
		return gpsRelativeLayout;
	}
	
	private void setGPSButtonEvent(final RelativeLayout gpsRelativeLayout, final LayoutInflater inflater) {
		gpsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View viewParam) {
//            	Intent intent = new Intent(inflater.getContext(), GPSActivity.class);
//            	inflater.startActivity(intent);
            	
            	
            	
//            	gpsText = (TextView) gpsRelativeLayout.getChildAt(2);
//            	gpsText.setText("Procurando Localizacao...");
//            	gpsButton.setEnabled(false);
//            	
//                locationManager = (LocationManager) inflater.getContext().getSystemService(Context.LOCATION_SERVICE);
//                try {
//                	List<String> providers = locationManager.getAllProviders();
//           	    } catch (Throwable e) {
//           	    	Log.e(GPS_LOG_TAG, "exceçao ao listar provedores");
//            	}
            	
            	
            	
            	
//                LocationListener locationListener = new MyLocationListener(locationManager, gpsButton);
//                
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
//                gpsText.setText(locationListener.toString());
                
            }


		
	});
	}

	@Override
	public int describeContents() {
		Log.e(GPS_LOG_TAG, "SETOU CONTENTS p 5");
		return 5;
	}
}
