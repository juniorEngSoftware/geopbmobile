package com.example.helloandroid;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import model.Feature;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

import com.thoughtworks.xstream.XStream;

public class HelloAndroid extends Activity {
	
	private static final CharSequence PROGRESS_MESSAGE = "Enviando requisição...";
	private static final String MAIN_LOG_TAG = "HELO ANDROID CLASS";
	private ProgressDialog progressDialog;
	
	private String xmlURL;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Log.e(MAIN_LOG_TAG, "on create ========================");
		
		setXMLRequestButtonEvent();
	}

	@Override
	public void finish(){
		super.finish();
	}
	private void setXMLRequestButtonEvent() {
		final Button startButton = (Button) findViewById(R.id.sendRequest_button);
		final EditText editText =  (EditText) findViewById(R.id.edit_url_text);
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				xmlURL = "http://dl.dropbox.com/u/8510487/features2.xml";
//				xmlURL = editText.getText().toString();
				downloadFile();
			}
		});	
	}

	
	private void downloadFile(){
		progressDialog = ProgressDialog.show(this, "", PROGRESS_MESSAGE);
		
		new FetchThread(this.messageHandler, this.xmlURL){}.start();
	}
		
	
	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			progressDialog.dismiss();

			if(msg.getData().getString("erro").equals(FetchThread.OK_MESSAGE)) {
				Intent intent = new Intent(HelloAndroid.this, FormActivity.class);
				
				ArrayList<Feature> parcelableArrayList = msg.getData().getParcelableArrayList("features");
				
				intent.putParcelableArrayListExtra("features",  msg.getData().getParcelableArrayList("features"));
				startActivity(intent);				
			}
			else{
				showAlertDialog("ERRO", (String) msg.getData().get("erro"));
			}
		}
	};
	
	private void showAlertDialog(String title, String message) {
		new AlertDialog.Builder(this)
			.setMessage(message)
			.setTitle(title)
			.setCancelable(true)
			.setNeutralButton(R.string.cancel_button,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton){}
					}
			)
			.show();
	}
}