package com.example.helloandroid;

import java.util.ArrayList;

import model.Feature;
import android.R.xml;
import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FormActivity extends Activity{

	private ArrayList<Parcelable> xmlFeatures;
	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		
		ArrayList<Feature> xmlInfo = this.getIntent().getParcelableArrayListExtra("features");
//		xmlFeatures =  xmlInfo.getParcelableArrayList("features");
		TextView text = (TextView) findViewById(R.id.textview);
		
		
		for (Feature feature : xmlInfo) {
			if(feature != null){
				Log.e("FormActivity", feature.getName());
			}
			else{
				Log.e("FormActivity", "FEATURE NULL !!!!");
			}
		}

	}

}
