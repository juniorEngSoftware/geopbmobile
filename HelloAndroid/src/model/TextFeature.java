package model;

import android.os.Parcel;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.helloandroid.R;

public class TextFeature extends Feature{

	private static final String TEXT_FEATURE_LOG_TAG = "TEXT FEATURE CLASS";
	private String content;
	private int constraint;
	

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
		readFromParcel(in);
	}
	
	@Override
	protected void readFromParcel(Parcel in) {
		super.readFromParcel(in);
		this.constraint = in.readInt();	
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private int getConstraint() {
		return constraint;
	}
	@Override
	public int getType() {
		return R.layout.edittext;
	}
	
	@Override
	public View setUpView(View rawFeatureView, LayoutInflater inflater){
		EditText editText = (EditText) rawFeatureView;
		
		Log.e(TEXT_FEATURE_LOG_TAG, "limite de texto: " + getConstraint());
		
		//set constraint
		if(getConstraint() != 0){
			InputFilter[] filterArray = new InputFilter[1];
			editText.setFilters(filterArray);
			filterArray[0] = new InputFilter.LengthFilter(getConstraint());
		}

		return editText;
	}
	

	@Override
	public String toString() {
		return "Name: " + super.getName() + "\n" + "Content: " + this.content + "\n" + "Constraint: " + this.constraint;
	}
	
	@Override
	public int describeContents() {
		Log.e(TEXT_FEATURE_LOG_TAG, "SETOU CONTENTS p 1");
		return 1;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		Log.e(TEXT_FEATURE_LOG_TAG, "writeToParcel na subclasse TEXT FEATURE");
		dest.writeInt(constraint);
	}
}
