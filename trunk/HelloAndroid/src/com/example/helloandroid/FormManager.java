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

	private LinearLayout layout;

	public FormManager() {
	}

	public FormManager(LinearLayout layout) {
		this.layout = layout;
	}

	public void addFeature(Feature feature, LayoutInflater inflater) {
		setTextView(feature, inflater);
		
		View inflate = inflater.inflate(feature.getType(), null);
		View setView = feature.setUpView(inflate);
		
		if(setView != null) 
			layout.addView(setView);
	}

	private void setTextView(Feature feature, LayoutInflater inflater) {
		TextView textView = (TextView) inflater.inflate(R.layout.textview, null);
		textView.setText(feature.getName());
		
		layout.addView(textView);
	}

}
