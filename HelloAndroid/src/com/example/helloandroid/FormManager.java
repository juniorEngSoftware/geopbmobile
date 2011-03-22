package com.example.helloandroid;

import model.Feature;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FormManager {

	private static final String FORM_MANAGER_LOG_TAG = "Form Manager CLASS";
	
	private LinearLayout layout;

	public FormManager() {
	}

	public FormManager(LinearLayout layout) {
		this.layout = layout;
	}

	public void addFeature(Feature feature, LayoutInflater inflater) {
		setTextView(feature, inflater);//set the feature title
		
		View inflate = inflater.inflate(feature.getType(), null);
		View setView = feature.setUpView(inflate, inflater);
		
		Log.e(FORM_MANAGER_LOG_TAG, "SETOU VIEW" );
		if(setView != null) 
			layout.addView(setView);
	}

	private void setTextView(Feature feature, LayoutInflater inflater) {
		TextView textView = (TextView) inflater.inflate(R.layout.textview, null);
		textView.setText(feature.getName());
		
		layout.addView(textView);
	}

}
