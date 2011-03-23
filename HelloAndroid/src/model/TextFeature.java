package model;

import com.example.helloandroid.R;

import android.os.Parcel;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class TextFeature extends Feature{

	private static final String TEXT_FEATURE_LOG_TAG = "TEXT FEATURE CLASS";
	private String content;

	public TextFeature(){
		super();
	}

	public TextFeature(String name) {
		super(name);
	}
	
	public TextFeature(String name, String content) {
		super(name);
		this.content = content;
	}


	public TextFeature(Parcel in) {
		super(in);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int getType() {
		return R.layout.edittext;
	}
	
	@Override
	public View setUpView(View inflate, LayoutInflater inflater){
		EditText editText = (EditText) inflate;
		return editText;
	}
	
	@Override
	public String toString() {
		return "Name: " + super.getName() + "\n" + "Content: " + this.content;
	}
	
	@Override
	public int describeContents() {
		Log.e(TEXT_FEATURE_LOG_TAG, "SETOU CONTENTS p 1");
		return 1;
	}
}
