package com.example.helloandroid;

import java.util.ArrayList;

import model.Feature;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FormActivity extends Activity{

	private static final String FORM_ACTIVITY_LOG_TAG = "Form Activity CLASS";
	protected static final int MAP_CODE = 10;
	protected static final int GPS_CODE = 11;
	
	private FormManager formManager;
	
	ArrayList<Feature> xmlInfo;
	
	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		
		xmlInfo = this.getIntent().getParcelableArrayListExtra("features");
		Log.e(FORM_ACTIVITY_LOG_TAG, "size: " + String.valueOf(xmlInfo.size()) );
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.form_layout);
		formManager = new FormManager(inflater, linearLayout);

		setFormContent();
			
		formManager.setSendFormButton();
		
	}


	private void setFormContent() {
		for (Feature feature : xmlInfo) {
			formManager.addFeature(feature);	
			
			Log.e(FORM_ACTIVITY_LOG_TAG, feature.getName());
			
			callNewActivity(feature);
		}
	}

	private void callNewActivity(Feature feature) {
		switch (feature.getType()) {
		case R.layout.location:
			
			Log.e(FORM_ACTIVITY_LOG_TAG, "CHAMOU GPSActivity");
			
//			set gpsButtonEvent and call activity for Result
			Button gpsButton = (Button) findViewById(R.id.gps_button);
			gpsButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent gpsIntent = new Intent(FormActivity.this, GPSActivity.class);
					startActivityForResult(gpsIntent, GPS_CODE);
				}
			});
			
//			set ButtonEventcall activity for Result
			Button mapButton = (Button) findViewById(R.id.map_button);
			mapButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent mapIntent = new Intent(FormActivity.this, GMapsActivity.class);
					startActivityForResult(mapIntent, MAP_CODE);
				}
			});
			break;

		default:
			break;
		}
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		saveState(outState);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
	}

	@Override
	protected void onPause() {
		saveState();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void saveState() {
		// TODO Auto-generated method stub
	}

	private void saveState(Bundle outState) {
//		for (Feature feature : xmlInfo) {
//			outState.putString(feature.getName(), feature.toString());
//		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 switch(requestCode) {
	      case MAP_CODE: 
	            if (resultCode == RESULT_OK) {
	                EditText latEditText = (EditText) findViewById(R.id.latitude);
	                EditText longEditText = (EditText) findViewById(R.id.longitude);
	                latEditText.setText(String.valueOf(data.getExtras().getDouble("latitude")));
	                longEditText.setText(String.valueOf(data.getExtras().getDouble("longitude")));
	                break;
	            }
	      default:
				break;
			}
	}
}
