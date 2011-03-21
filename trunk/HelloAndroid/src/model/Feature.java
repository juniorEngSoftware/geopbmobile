package model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.helloandroid.R;

public class Feature implements Parcelable {

	private static final int FEATURE_DESCRIPTION = 0;
	private static final int TEXT_FEATURE_DESCRIPTION = 1;
	private static final int NUMERIC_FEATURE_DESCRIPTION = 2;
	private static final int SINGLE_CHECK_BOX_FEATURE_DESCRIPTION = 3;
	private static final int MULTIPLE_CHECK_BOX_FEATURE_DESCRIPTION = 4;
	
	private String name;

	public Feature(){
	}
	
	public Feature(String name) {
		this.name = name;
	}


	public Feature(Parcel in) {
		readFromParcel(in);
	}

	private void readFromParcel(Parcel in) {
		this.name = in.readString();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return -1;
	}

	public View setUpView(View inflate) {
		return null;
	}
	@Override
	public String toString() {
		return "Name: " + name;
	}

	@Override
	public int describeContents() {
		return this.FEATURE_DESCRIPTION;
	}
	
	//*************************************************
	//Parcelable methods
	//*************************************************

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.describeContents());
		dest.writeString(this.name);
	}

	public static final Parcelable.Creator<Feature> CREATOR = new Parcelable.Creator<Feature>() {
		public Feature createFromParcel(Parcel in) {
			int description = in.readInt();
			Log.e("FEATURE bbb", "descricao: " + description );
			switch (description) {
				case TEXT_FEATURE_DESCRIPTION:
					return new TextFeature(in);
				case NUMERIC_FEATURE_DESCRIPTION:
					return new NumericFeature(in);
				case SINGLE_CHECK_BOX_FEATURE_DESCRIPTION:
					return new SingleCheckBoxFeature();
				case MULTIPLE_CHECK_BOX_FEATURE_DESCRIPTION:
					return new MutipleCheckBoxFeature();
				default:
					return new Feature(in);
			}
			
	
		}

		@Override
		public Feature[] newArray(int size) {
			return new Feature[size];
		}
	};


}
