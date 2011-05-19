package com.example.helloandroid;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import model.Feature;

import org.xmlpull.v1.XmlSerializer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FormManager {

	private static final String FORM_MANAGER_LOG_TAG = "Form Manager CLASS";
	
	protected static final int MAP_CODE = 10;
	protected static final int CAMERA_CODE = 11;
	protected static final int FILE_EXPLORER_CODE = 12;
	
	private FormActivity formActivity;
	private LayoutInflater inflater;
	private LinearLayout formLayout;
	private Uri imageUri;

	public FormManager(FormActivity formActivity, LinearLayout linearLayout, LayoutInflater inflater) {
		this.formActivity = formActivity;
		this.inflater = inflater;
		this.formLayout = linearLayout;
	}
	
	public Uri getImageUri() {
		return this.imageUri;
	}
	
	public void addFeatures(ArrayList<Feature> xmlInfo) {
		for (Feature feature : xmlInfo) {
			addOneFeature(feature);
			
			if(feature.getType() == R.layout.location) {
				setGpsButtonsEvent();
				setMapButtonEvent();
			}
			if(feature.getType() == R.layout.multimedia) {
				setCameraButtonsEvent();
				setFileBrowserButtonEvent();
			}
		}
	}

	private void addOneFeature(Feature feature) {
		setTextView(feature);//set the feature title
		
		View rawFeatureView = inflater.inflate(feature.getType(), null);
		View setFeatureView = feature.setUpView(rawFeatureView, inflater);
		
		if(setFeatureView != null) 
			formLayout.addView(setFeatureView);
		
	}
	
	private void setTextView(Feature feature) {
		TextView textView = (TextView) inflater.inflate(R.layout.textview, null);
		textView.setText(feature.getName());
		
		formLayout.addView(textView);
	}
	
	public void addSendFormButton(ArrayList<Feature> xmlInfo){
//		XmlSerializer serializer = Xml.newSerializer();
//		StringWriter writer = new StringWriter();
//		try {
//			serializer.setOutput(writer);
//			serializer.startDocument("UTF-8", true);
//			
//			for (int i = 0; i < xmlInfo.size(); i++) {
//				serializer = getViewContent(xmlInfo.get(i), i, serializer);
//				Log.e(FORM_MANAGER_LOG_TAG, "ENTROU NO FOR DO SENDBUTTON");
//				break;
//			}
//			serializer.endDocument();
//			
//		} catch (Exception e) {
//			Log.e(FORM_MANAGER_LOG_TAG, "exceçao miseraaaaaaaaa");
//			Log.e(FORM_MANAGER_LOG_TAG, e.getMessage());
//		}
        
		Button button = (Button) inflater.inflate(R.layout.button, null);
		button.setText("ENVIAR");
//		setSendFormButtonEvent(button, writer.toString());
		formLayout.addView(button);
	}
	
	private XmlSerializer getViewContent(Feature feature, int i, XmlSerializer serializer)  {
		switch (feature.getType()) {
		case R.layout.edittext:
			Log.e(FORM_MANAGER_LOG_TAG, "i: " +i);
			EditText editText = (EditText) formLayout.getChildAt(i);
			Log.e(FORM_MANAGER_LOG_TAG, "2222222222");
			CharSequence text = editText.getText();
			Log.e(FORM_MANAGER_LOG_TAG, "3333333333");
//			serializer.startTag("", "Campo de Texto");
//			Log.e(FORM_MANAGER_LOG_TAG, "444444444444");
//			serializer.attribute("", "Valor", (String) text);
//			return serializer.endTag("", "Campo de Texto");
		default:
			break;
		}
		return null;
		
	}

	//*************************************************
	//set especific button event methods
	//*************************************************
	private void setGpsButtonsEvent() {
		Button gpsButton = (Button) formActivity.findViewById(R.id.gps_button);
		gpsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent gpsIntent = new Intent(formActivity, GPSActivity.class);
				formActivity.startActivityForResult(gpsIntent, MAP_CODE);
			}
		});
	}

	private void setMapButtonEvent() {
		Button mapButton = (Button) formActivity.findViewById(R.id.map_button);
		mapButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mapIntent = new Intent(formActivity, GMapsActivity.class);
				formActivity.startActivityForResult(mapIntent, MAP_CODE);
			}
		});
	}


	private void setCameraButtonsEvent() {
		Button cameraButton = (Button) formActivity.findViewById(R.id.camera_button);
		cameraButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startCameraActivity();
			}
		});
	}

	private void setFileBrowserButtonEvent() {
		Button fileButton = (Button) formActivity.findViewById(R.id.file_button);
		fileButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(); 
				intent.setType("*/*"); 
				intent.setAction(Intent.ACTION_GET_CONTENT); 
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
				formActivity.startActivityForResult(Intent.createChooser(intent,"Escolha um Arquivo"), FILE_EXPLORER_CODE);
			}
		});
	}
	
	private void setSendFormButtonEvent(Button sendFormButton, final String string) {
		sendFormButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				creatResultXmlForm();
				Toast.makeText(formActivity, string, Toast.LENGTH_LONG).show();
			}

			private void creatResultXmlForm() {
				for (int i = 0; i < formLayout.getChildCount(); i++) {
				}
			}
		});
	}
	
	private void startCameraActivity() {
		String fileName = "testphoto.jpg";
		
		//create parameters for cameraIntent
		ContentValues values = new ContentValues();
		values.put(Media.TITLE, fileName);	
		values.put(Media.DESCRIPTION,"Image capture by camera");
		values.put(Images.Media.MIME_TYPE, "image/jpeg");

		//imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
		imageUri = formActivity.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
		
		//create new Intent
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		
		formActivity.startActivityForResult(cameraIntent, CAMERA_CODE);
		
    }

}
