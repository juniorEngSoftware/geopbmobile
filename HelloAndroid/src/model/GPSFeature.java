package model;


import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Parcel;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.example.helloandroid.FormActivity;
import com.example.helloandroid.GPSActivity;
import com.example.helloandroid.HelloAndroid;
import com.example.helloandroid.R;

public class GPSFeature extends Feature{

	private static final String GPS_LOG_TAG = "GPSFeature CLASS";
	
	private Button gpsButton;
	private Button mapButton;
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

		EditText latEditText = (EditText) gpsRelativeLayout.getChildAt(0);
		EditText longEditText = (EditText) gpsRelativeLayout.getChildAt(1);
		
		latEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		longEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL); 
		
		return gpsRelativeLayout;
	}
	
	@Override
	public int describeContents() {
		Log.e(GPS_LOG_TAG, "SETOU CONTENTS p 5");
		return 5;
	}
}
