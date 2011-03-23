package com.example.helloandroid;

import java.util.ArrayList;

import model.Feature;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FormActivity extends Activity{

	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		Log.e("FormActivity", "ENTROU NO FormActivity");
		
		ArrayList<Feature> xmlInfo = this.getIntent().getParcelableArrayListExtra("features");
		Log.e("FormActivity", "size: " + String.valueOf(xmlInfo.size()) );
		
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.form_layout);

		setFormContent(linearLayout, inflater, xmlInfo);
		
		
		Button button = (Button) inflater.inflate(R.layout.button, null);
		button.setText("ENVIAR");
		linearLayout.addView(button);
		
	}

	private void setFormContent(LinearLayout linearLayout, LayoutInflater inflater, ArrayList<Feature> xmlInfo) {
		FormManager formManager = new FormManager(linearLayout);
		
		for (Feature feature : xmlInfo) {
			formManager.addFeature(feature, inflater);	
			
			Log.e("FormActivity", feature.getName());
			
		}
	}

}
