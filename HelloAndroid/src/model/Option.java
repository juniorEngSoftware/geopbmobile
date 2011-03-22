package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Option implements Parcelable{

	private String name;
	
	
	public Option() {
		
	}
	public Option(Parcel in) {
		readFromParcel(in);
	}

	public Option(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Name: " + name;
	}

	/**
	 *
	 * Called from the constructor to create this
	 * object from a parcel.
	 *
	 * @param in parcel from which to re-create object
	 */
	private void readFromParcel(Parcel in) {
		this.name = in.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		
	}
	
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public Option createFromParcel(Parcel in) {
                return new Option(in);
            }
 
            public Option[] newArray(int size) {
                return new Option[size];
            }
        };
	
}
