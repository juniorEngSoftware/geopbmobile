package model;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.helloandroid.R;

public class MultipleCheckBoxFeature extends Feature {

	private static final String MULTIPLE_CHECKBOX_LOG_TAG = "MultipleCheckBoxFeature CLASS";
	private List<Option> optionList;
	
	
	public MultipleCheckBoxFeature() {
		optionList = new ArrayList<Option>();
	}
	
	public MultipleCheckBoxFeature(Parcel in) {
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
		return "Multiple Check Box: " + this.name + "\n" + "Options: " + this.optionList.toString() + "\n";
	}
	
	@Override
	public int getType() {
		return R.layout.multiplecheckbox;
	}
	
	
	@Override
	public View setUpView(View inflate, LayoutInflater inflater){
		LinearLayout linearLayout = (LinearLayout) inflate;
		
		Log.e(MULTIPLE_CHECKBOX_LOG_TAG, "setuP OPTIONS COMO CHECKBOXS");
		
		for (Option option : optionList) {
			CheckBox checkBox = (CheckBox) inflater.inflate(R.layout.checkbox, null);
			checkBox.setText(option.getName());
			linearLayout.addView(checkBox);
		}
		return linearLayout;
	}
	
	
	@Override
	public int describeContents() {
		Log.e(MULTIPLE_CHECKBOX_LOG_TAG, "SETOU CONTENTS p 4");
		return 4;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		Log.e(MULTIPLE_CHECKBOX_LOG_TAG, "writeToParcel na subclasse");
		dest.writeList(optionList);
	}
}
