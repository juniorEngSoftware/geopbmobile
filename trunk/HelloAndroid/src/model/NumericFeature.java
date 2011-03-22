package model;

import android.app.Activity;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class NumericFeature extends Feature{

	private String content;
	private double minValue;
	private double maxValue;

	public NumericFeature(){
		super();
	}
	
	public NumericFeature(String name) {
		super(name);
	}

	public NumericFeature(Parcel in) {
		super(in);
		this.minValue = in.readDouble();
		this.maxValue = in.readDouble();
	}
	

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	
	public double getMinValue() {
		return minValue;
	}
	
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	
	public double getMaxValue() {
		return maxValue;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	//FIXME
	public int getType() {
		return -1;
	}
	
	//FIXME
	@Override
	public View setUpView(View inflate, LayoutInflater inflater){
		return null;
		
	}
	
	@Override
	public String toString() {
		return "Name: " + super.getName() + "\n" + "Content: " + this.content;
	}
	
	@Override
	public int describeContents() {
		Log.e("NUMERIC_FEATURE", "SETOU CONTENTS p 2");
		return 2;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeDouble(minValue);
		dest.writeDouble(maxValue);
	}

}
