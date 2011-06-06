package model;

import Utils.GeoPBMobileStrings;
import android.os.Parcel;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.helloandroid.R;

public class ConfigurationFeature extends Feature{

	private static final String CONFIGURATION_LOG_TAG = "CONFIGURATION CLASS";
	
	public ConfigurationFeature() {
		super();
	}

	public ConfigurationFeature(String name) {
		super(name);
	}

	public ConfigurationFeature(Parcel in) {
		readFromParcel(in);
	}

	@Override
	protected void readFromParcel(Parcel in) {
		super.readFromParcel(in);
	}

	@Override
	public String toString() {
		return "Name: " + super.getName() + "\n" + "Content: ";
	}
	
	@Override
	public int describeContents() {
		Log.e(CONFIGURATION_LOG_TAG, "SETOU CONTENTS p 7");
		return 7;
	}

}