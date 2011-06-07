package com.example.helloandroid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Feature;
import model.GPSFeature;
import model.MultimediaFeature;
import model.MultipleCheckBoxFeature;
import model.Option;
import model.SingleCheckBoxFeature;
import model.TextFeature;

import org.xmlpull.v1.XmlSerializer;

import Utils.GeoPBMobileUtil;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FormManager {

	private static final String FORM_MANAGER_LOG_TAG = "Form Manager CLASS";
	
	private String link;
	
	private FormActivity formActivity;
	private LayoutInflater inflater;
	private LinearLayout formLayout;
	ArrayList<Feature> xmlInfo;
	private Uri imageUri;
	

	public FormManager(FormActivity formActivity, LinearLayout linearLayout, LayoutInflater inflater, ArrayList<Feature> xmlInfo) {
		this.formActivity = formActivity;
		this.inflater = inflater;
		this.formLayout = linearLayout;
		this.xmlInfo = xmlInfo;
		
	}
	
	public Uri getImageUri() {
		return this.imageUri;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setConfiguration(Feature feature) {
		setLink(feature.getName());
		Log.e(FORM_MANAGER_LOG_TAG, "GET LINKKKKKKKKKKK " + getLink());
	}

	public void addFeatures(ArrayList<Feature> xmlInfo) {
		for (int i = 1; i < xmlInfo.size(); i++) {
			Feature feature = xmlInfo.get(i);
			addOneFeature(feature);
			
			if(feature.getType() == R.layout.multiplecheckbox) {
				setMultipleChoiceButtonEvent(((MultipleCheckBoxFeature) feature).getOptionList());
			}
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
		Button button = (Button) inflater.inflate(R.layout.button, null);
		button.setText("ENVIAR");
		setSendFormButtonEvent(button);
		formLayout.addView(button);
	}
	
	
	private void setSendFormButtonEvent(Button sendFormButton) {
		sendFormButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < formLayout.getChildCount(); i++) {
					Log.e(FORM_MANAGER_LOG_TAG, formLayout.getChildAt(i).getClass().toString());
				}
				
				collectFormResults();
				String data = writeXmlFromForm();
				Log.e(FORM_MANAGER_LOG_TAG, "???????????  " + data);
				Log.e(FORM_MANAGER_LOG_TAG, "???????????  " + GeoPBMobileUtil.FILE_DIR);
				GeoPBMobileUtil.createXmlFile(data);
				if (!GeoPBMobileUtil.sendFile(data, getLink()).equals("") ){
					Toast.makeText(formActivity, "Formulario enviado com sucesso", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(formActivity, "Erro ao enviar Formulario", Toast.LENGTH_LONG).show();
				}
			}

		});
	}
	
	private String writeXmlFromForm() {
		XmlSerializer serializer = Xml.newSerializer();
	    StringWriter writer = new StringWriter();
	    try {
	        serializer.setOutput(writer);
	        serializer.startDocument("UTF-8", true);
	        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
	        serializer.startTag("", "form");
	        serializer.attribute("", "numberOfFeatures", String.valueOf(xmlInfo.size() - 1));
	        for (int i = 1; i < xmlInfo.size(); i++) {
				Feature feature = xmlInfo.get(i);
				serializer.startTag("", "feature");
	            serializer.attribute("", "type", feature.getClass().toString());
	            serializer.startTag("", "enunciation");
	            serializer.text(feature.getName());
	            serializer.endTag("", "enunciation");
	            //create another tag to save file's name and type
	            if(feature instanceof MultimediaFeature) {
	            	serializer.startTag("", "filename");
		            serializer.text( ((MultimediaFeature) feature).getFileName() );
		            serializer.endTag("", "filename");
	            }
	            serializer.startTag("", "content");
	            serializer.text(feature.getContent());
	            serializer.endTag("", "content");
	            serializer.endTag("", "feature");
	        }
	        serializer.endTag("", "form");
	        serializer.endDocument();
	        return writer.toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    } 
		
	}
	
	protected void collectFormResults() {
		int j = 0;
		for (int i = 1; i < xmlInfo.size(); i++) {
			Log.e(FORM_MANAGER_LOG_TAG, "i: " + i);
			j = getViewContent(xmlInfo.get(i), j);
		}
	}
	
	//*************************************************
	// methods to get formfeatures contents
	//*************************************************
	private int getViewContent(Feature feature,  int j)  {
		Log.e(FORM_MANAGER_LOG_TAG, "j: " + j);
		
		if (feature instanceof TextFeature) {
			View childAt2 = formLayout.getChildAt(j+1);
			Log.e(FORM_MANAGER_LOG_TAG, "j: " + childAt2.getClass().toString());
			EditText editText = (EditText) formLayout.getChildAt(j+1);
			CharSequence text = editText.getText();
			((TextFeature)feature).setText( text.toString());
			
			return j+=2;
		}	
		if ( feature instanceof SingleCheckBoxFeature) {
			Spinner spinner = (Spinner) formLayout.getChildAt(j+1);
			String selectedItem = (String) spinner.getSelectedItem();
			((SingleCheckBoxFeature)feature).setSelectedItem(selectedItem);
			return j++;
		}
		j++;
		
		Log.e(FORM_MANAGER_LOG_TAG, "DEPOIS j: " + j);
		return j;
		
	}
	
	public void setMultipleChoicesResults(ArrayList<String> stringArrayList) {
		for (int i = 0; i < xmlInfo.size(); i++) {
			if (xmlInfo.get(i) instanceof MultipleCheckBoxFeature)
				((MultipleCheckBoxFeature) xmlInfo.get(i)).setCheckedOptions(stringArrayList);
		}
	}
	public void setCoordinates(String latitude, String longitude) {
		for (int i = 0; i < xmlInfo.size(); i++) {
			if (xmlInfo.get(i) instanceof GPSFeature) {
				((GPSFeature) xmlInfo.get(i)).setLatitude(latitude);
				((GPSFeature) xmlInfo.get(i)).setLongitude(longitude);
			}
		}
	}
	public void setFilePath(String selectedFilePath) {
		for (int i = 0; i < xmlInfo.size(); i++) {
			if (xmlInfo.get(i) instanceof MultimediaFeature) {
				((MultimediaFeature) xmlInfo.get(i)).setFilePath(selectedFilePath);
				
			}
		}
		
	}


	//*************************************************
	//set especific button event methods
	//*************************************************
	private void setMultipleChoiceButtonEvent(final List<Option> optionList) {
		Button mutilpleChoiceButton = (Button) formActivity.findViewById(R.id.multipleChoice_button);
		mutilpleChoiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent multipleChoiceIntent = new Intent(formActivity, MultipleChoiceActivity.class);
				multipleChoiceIntent.putStringArrayListExtra("optionsName",	GeoPBMobileUtil.arrayListToString(optionList));
				formActivity.startActivityForResult(multipleChoiceIntent, GeoPBMobileUtil.MULTIPLE_CHOICE_CODE);
			}
		});
		
	}
	private void setGpsButtonsEvent() {
		Button gpsButton = (Button) formActivity.findViewById(R.id.gps_button);
		gpsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent gpsIntent = new Intent(formActivity, GPSActivity.class);
				formActivity.startActivityForResult(gpsIntent, GeoPBMobileUtil.MAP_CODE);
			}
		});
	}

	private void setMapButtonEvent() {
		Button mapButton = (Button) formActivity.findViewById(R.id.map_button);
		mapButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mapIntent = new Intent(formActivity, GMapsActivity.class);
				formActivity.startActivityForResult(mapIntent, GeoPBMobileUtil.MAP_CODE);
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
				formActivity.startActivityForResult(Intent.createChooser(intent,"Escolha um Arquivo"), GeoPBMobileUtil.FILE_EXPLORER_CODE);
			}
		});
	}
	
	//FIXME criar classe CameraMAnager
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
		
		formActivity.startActivityForResult(cameraIntent, GeoPBMobileUtil.CAMERA_CODE);
		
    }
}