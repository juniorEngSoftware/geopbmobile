package model;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.helloandroid.R;

public class SingleCheckBoxFeature extends Feature{

	private static final String SINGLE_CHECKBOX_LOG_TAG = "SingleCheckBoxFeature CLASS";
	private List<Option> optionList;
	
	public SingleCheckBoxFeature() {
		optionList = new ArrayList<Option>();
	}
	
	public SingleCheckBoxFeature(List<Option> optionList) {
		this.optionList = optionList;
	}
	public SingleCheckBoxFeature(Parcel in) {
		this();
		readFromParcel(in);
	}
	
	@Override
	protected void readFromParcel(Parcel in) {
		super.readFromParcel(in);
		in.readList(optionList, Option.class.getClassLoader());	
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Option> getOptionList() {
		return this.optionList;
	}
	
	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}
	
	@Override
	public String toString() {
		return "Single Check Box: " + this.name + "\n" + "Options: " + this.optionList.toString() + "\n";
	}
	
	@Override
	public int getType() {
		return R.layout.singlecheckbox;
	}
	
	@Override
	public View setUpView(View inflate, LayoutInflater inflater){
		RadioGroup radioGroup = (RadioGroup) inflate;
		
		Log.e(SINGLE_CHECKBOX_LOG_TAG, "setuP OPTIONS COMO BUTTONS");
		
		for (Option option : optionList) {
			RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.radiobutton, null);
			radioButton.setText(option.getName());
			radioGroup.addView(radioButton);
		}
		
		return radioGroup;
	}
	
	
	@Override
	public int describeContents() {
		Log.e(SINGLE_CHECKBOX_LOG_TAG, "SETOU CONTENTS p 3");
		return 3;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		Log.e(SINGLE_CHECKBOX_LOG_TAG, "writeToParcel na subclasse");
		dest.writeList(optionList);
	}
}
