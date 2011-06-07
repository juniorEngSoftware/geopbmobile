package com.example.helloandroid;

import java.util.ArrayList;

import model.Feature;
import Utils.GeoPBMobileUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends Activity{

	private static final String FORM_ACTIVITY_LOG_TAG = "Form Activity CLASS";
	
	private FormManager formManager;
	private ArrayList<Feature> xmlInfo;
//	private LinearLayout linearLayout;
	
	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		
		xmlInfo = this.getIntent().getParcelableArrayListExtra("features");
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.form_layout);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//set form
		formManager = new FormManager(this, linearLayout, inflater, xmlInfo);
		formManager.setConfiguration(xmlInfo.get(0));
		formManager.addFeatures(xmlInfo);
		formManager.addSendFormButton(xmlInfo);
	}


//	@Override
//	protected void onSaveInstanceState(Bundle outState) {
//		saveState(outState);
//		super.onSaveInstanceState(outState);
//	}
	
//	@Override
//	protected void onRestoreInstanceState(Bundle savedState) {
//		super.onRestoreInstanceState(savedState);
//	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
//	private void saveState() {
//	}
//
//	private void saveState(Bundle outState) {
//		for (Feature feature : xmlInfo) {
//			outState.putString(feature.getName(), feature.toString());
//		}
//	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 switch(requestCode) {
		 
		 case GeoPBMobileUtil.MULTIPLE_CHOICE_CODE:
	    	  Log.e(FORM_ACTIVITY_LOG_TAG, "MULTIPLE CHECK BOX !!!!!");
	    	  if (resultCode == RESULT_OK) { 
	    		  ArrayList<String> selectedChoices = data.getExtras().getStringArrayList("choices");
	    		  int numberOfChoices = selectedChoices.size();
	    		  String choices = selectedChoices.toString();
	    		  formManager.setMultipleChoicesResults(selectedChoices);
	    		  
	    		  TextView optionsText = (TextView) findViewById(R.id.options_text);
	    		  if (numberOfChoices > 0) {
	    			  optionsText.setText(numberOfChoices + " foram escolhidas. " + choices);
	    			  Log.e(FORM_ACTIVITY_LOG_TAG, choices );
	    		  } else {
	    			  optionsText.setText("Nenhuma opção foi escolhida");
	    		  }
	    	  }
	    	  break;
	    	  
	      case GeoPBMobileUtil.MAP_CODE: 
	            if (resultCode == RESULT_OK) {
	            	String latitude = String.valueOf(data.getExtras().getDouble("latitude"));
	            	String longitude = String.valueOf(data.getExtras().getDouble("longitude"));

	            	EditText latEditText = (EditText) findViewById(R.id.latitude);
	                EditText longEditText = (EditText) findViewById(R.id.longitude);
	                latEditText.setText(latitude);
	                longEditText.setText(longitude);
	                
	                formManager.setCoordinates(latitude, longitude);
	            }
	            break;
	            
	      case GeoPBMobileUtil.CAMERA_CODE:
	    	  if (resultCode == RESULT_OK) {
	    		  //use imageUri here to access the image
	    		  String selectedFilePath = getPath(formManager.getImageUri());
	    		  
	    		  TextView filePathText = (TextView) findViewById(R.id.file_text);
	    		  filePathText.setText(selectedFilePath);
	    		  
	    		  formManager.setFilePath(selectedFilePath);
   		      } else if (resultCode == RESULT_CANCELED) {
   		    	  Toast.makeText(this, "Foto não foi tirada", Toast.LENGTH_SHORT).show();
   		      } else {
   		    	  Toast.makeText(this, "Foto não foi tirada", Toast.LENGTH_SHORT).show();
   		      }

	    	  break;	
	      case GeoPBMobileUtil.FILE_EXPLORER_CODE:
	    	  Log.e(FORM_ACTIVITY_LOG_TAG, "SELECIONOU ARQUIVO");
	    	  if (resultCode == RESULT_OK) { 
	    	    	Uri selectedFile = data.getData();
	    	    	String selectedFilePath = getPath(selectedFile);
	    	    	
	    	    	TextView filePathText = (TextView) findViewById(R.id.file_text);
	    	        filePathText.setText(selectedFilePath);
	    	        
	    	        formManager.setFilePath(selectedFilePath);
	    	  }
	    	  break;
	      default:
				break;
			}
	}
	
	public String getPath(Uri uri) {
	    String selectedFilePath;
	    //1:MEDIA GALLERY --- query from MediaStore.Images.Media.DATA
	    String[] projection = { MediaStore.Images.Media.DATA };
	    Cursor cursor = managedQuery(uri, projection, null, null, null);
	    if(cursor != null){
	        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();
	        selectedFilePath = cursor.getString(column_index);
	    }else{
	        selectedFilePath = null;
	    }

	    if(selectedFilePath == null){
	        //2:OI FILE Manager --- call method: uri.getPath()
	    	Log.e(FORM_ACTIVITY_LOG_TAG, "selectedFilePath == null");
	        selectedFilePath = uri.getPath();
	    }
	    return selectedFilePath;
	}
	
	
}
