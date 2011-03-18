package com.example.helloandroid;

import java.util.ArrayList;

import model.Feature;
import android.R.xml;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FormActivity extends Activity{

	private ArrayList<Parcelable> xmlFeatures;
	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		
		ArrayList<Feature> xmlInfo = this.getIntent().getParcelableArrayListExtra("features");
//		xmlFeatures =  xmlInfo.getParcelableArrayList("features");
		Log.e("FormActivity", "size: " + String.valueOf(xmlInfo.size()) );
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.form_layout);
		if(linearLayout == null){
			Log.e("FormActivity", "linearLayout igual a null");
		}else{
			Log.e("FormActivity", linearLayout.toString());
		}
		
		for (Feature feature : xmlInfo) {
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			EditText editText = (EditText) inflater.inflate(R.layout.edittext, null);
//			EditText editText = (EditText) findViewById(R.layout.edittext);
			if(editText == null){
				Log.e("FormActivity", "editText igual a null");
				break;
			}
			if(feature == null){
				Log.e("FormActivity", "feature igual a null");
				break;
			}				
			
			editText.setText(feature.getName());
			Log.e("FormActivity", feature.getName());
			
			linearLayout.addView(editText);
		}
		
		

	}

}
