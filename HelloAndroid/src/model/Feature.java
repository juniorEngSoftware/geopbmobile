package model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Feature implements Parcelable {

	private String name;

	public Feature(){
		this.name = "bucertatasdta";
	}
	
	public Feature(String name) {
		this.name = name;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Name: " + name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.e("FEATURE", "SETOU O NOME QND writeTOParcel");
		dest.writeString(this.name);
	}

	public static final Parcelable.Creator<Feature> CREATOR = new Parcelable.Creator<Feature>() {
		public Feature createFromParcel(Parcel in) {
			Log.e("FEATURE bbb", in.readString());
			return new Feature(in.readString());
		}

		@Override
		public Feature[] newArray(int size) {
			return new Feature[size];
		}
	};

}
