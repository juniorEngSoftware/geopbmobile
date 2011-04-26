package com.example.helloandroid;

import java.io.File;

import model.Feature;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FormManager {

	private static final String FORM_MANAGER_LOG_TAG = "Form Manager CLASS";
	
	private Context context;
	private LayoutInflater inflater;
	private LinearLayout layout;

	public FormManager(LayoutInflater inflater, LinearLayout linearLayout) {
//		this.inflater = LayoutInflater.from(context);;
		this.inflater = inflater;
		this.layout = linearLayout;
	}

	public void addFeature(Feature feature) {
		setTextView(feature);//set the feature title
		
		View rawFeatureView = inflater.inflate(feature.getType(), null);
		View setFeatureView = feature.setUpView(rawFeatureView, inflater);
		
		Log.e(FORM_MANAGER_LOG_TAG, "SETOU VIEW" );
		if(setFeatureView != null) 
			layout.addView(setFeatureView);
	}

	private void setTextView(Feature feature) {
		TextView textView = (TextView) inflater.inflate(R.layout.textview, null);
		textView.setText(feature.getName());
		
		layout.addView(textView);
	}

	public void setSendFormButton() {
		Button button = (Button) inflater.inflate(R.layout.button, null);
		button.setText("ENVIAR");
		layout.addView(button);
	}



}
