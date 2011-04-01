package model;

import android.app.Activity;
import android.os.Parcel;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class NumericFeature extends TextFeature{

	private static final String NUMERIC_FEATURE_LOG_TAG = "NUMERIC FEATURE CLASS";
	
	private String content;
//	private double minValue;
//	private double maxValue;

	public NumericFeature(){
		super();
	}
	
	public NumericFeature(String name) {
		super(name);
	}

	public NumericFeature(Parcel in) {
		super(in);
//		this.minValue = in.readDouble();
//		this.maxValue = in.readDouble();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public View setUpView(View rawFeatureView, LayoutInflater inflater){
		EditText editText = (EditText) rawFeatureView;
		editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		
		return editText;
	}
	
	@Override
	public int describeContents() {
		Log.e(NUMERIC_FEATURE_LOG_TAG, "SETOU CONTENTS p 2");
		return 2;
	}
	
	

}
