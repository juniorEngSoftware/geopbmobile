package com.example.helloandroid;

import java.io.File;
import java.util.ArrayList;

import model.Feature;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends Activity{

	private static final String FORM_ACTIVITY_LOG_TAG = "Form Activity CLASS";
	protected static final int MAP_CODE = 10;
	protected static final int CAMERA_CODE = 11;
	protected static final int FILE_EXPLORER_CODE = 12;
	
	private FormManager formManager;
	private ArrayList<Feature> xmlInfo;
	private LinearLayout linearLayout;
	private Uri imageUri;
	
	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		
		xmlInfo = this.getIntent().getParcelableArrayListExtra("features");
		Log.e(FORM_ACTIVITY_LOG_TAG, "size: " + String.valueOf(xmlInfo.size()) );
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		linearLayout = (LinearLayout) findViewById(R.id.form_layout);
		
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
//		linearLayout.onFinishTemporaryDetach();
		
	}

	private void callNewActivity(Feature feature) {
		switch (feature.getType()) {
		case R.layout.location:
//			set gpsButtonEvent and call activity for Result
			Button gpsButton = (Button) findViewById(R.id.gps_button);
			gpsButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent gpsIntent = new Intent(FormActivity.this, GPSActivity.class);
					startActivityForResult(gpsIntent, MAP_CODE);
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
			
		case R.layout.multimedia:
//			set cameraButtonEvent and call activity for Result
			Button cameraButton = (Button) findViewById(R.id.camera_button);
			cameraButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startCameraActivity();
				}
			});
			
//			set ButtonEventcall activity for Result
			Button fileButton = (Button) findViewById(R.id.file_button);
			fileButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.e(FORM_ACTIVITY_LOG_TAG, "CLICOU NO BOTAO ARQUIVO");
					
//					ALTERNATIVA 0 = so eh possivel upar fotos
//					Intent i = new Intent(Intent.ACTION_PICK, Media.INTERNAL_CONTENT_URI);
//					startActivityForResult(i, FILE_EXPLORER_CODE); 
					
//					ALTERNATIVA 1
					Intent intent = new Intent(); 
					intent.setType("*/*"); 
					intent.setAction(Intent.ACTION_GET_CONTENT); 
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
					startActivityForResult(Intent.createChooser(intent,"Escolha um Arquivo"), FILE_EXPLORER_CODE); 
					
//					ALTERNATIVA 2
//					Intent fileExplorerIntent = new Intent(FormActivity.this, FileExplorerActivity.class);
//					startActivityForResult(fileExplorerIntent, FILE_EXPLORER_CODE);
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
	
	private void startCameraActivity() {
		String fileName = "testphoto.jpg";
		
		//create parameters for cameraIntent
		ContentValues values = new ContentValues();
		values.put(Media.TITLE, fileName);	
		values.put(Media.DESCRIPTION,"Image capture by camera");
		values.put(Images.Media.MIME_TYPE, "image/jpeg");

		//imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
		imageUri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
		
		//create new Intent
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		
		startActivityForResult(cameraIntent, CAMERA_CODE);
		
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
	      case CAMERA_CODE:
	    	  if (resultCode == RESULT_OK) {
	    		  //use imageUri here to access the image
	    		  String selectedFilePath = getPath(imageUri);
	    		  
	    		  TextView filePathText = (TextView) findViewById(R.id.file_text);
	    		  filePathText.setText(selectedFilePath);
   		      } else if (resultCode == RESULT_CANCELED) {
   		    	  Log.e(FORM_ACTIVITY_LOG_TAG, "cancelou foto");
   		    	  Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
   		      } else {
   		    	  Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
   		      }

	    	  break;	
	      case FILE_EXPLORER_CODE:
	    	  Log.e(FORM_ACTIVITY_LOG_TAG, "SELECIONOU ARQUIVO");
	    	  if (resultCode == RESULT_OK) { 
	    	    	Uri selectedFile = data.getData();
	    	    	String selectedFilePath = getPath(selectedFile);
	    	    	
	    	    	TextView filePathText = (TextView) findViewById(R.id.file_text);
	    	        filePathText.setText(selectedFilePath);
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
	
	private File getTempFile(Context context){
		//it will return /sdcard/image.tmp	
		final File path = new File( Environment.getExternalStorageDirectory(), context.getPackageName() );
		if(!path.exists()){
			path.mkdir();
		 }
		return new File(path, "image.jpg");
		
	}
}
