package model;

import android.os.Parcel;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.helloandroid.R;

public class MultimediaFeature extends Feature{

	private static final String MULTIMEDIA_LOG_TAG = "Multimedia CLASS";
	private String content;
	private String filePath;
	
	public MultimediaFeature() {
	}
	
	public MultimediaFeature(Parcel in) {
		super.readFromParcel(in);
	}
	
	
	public void setFilePath(String selectedFilePath) {
		filePath = selectedFilePath;
		
	}
	public String getFilePath() {
		return filePath;
		
	}
	@Override
    public String getContent() {
		if (getFilePath() == null)
			return "";
		return getFilePath();
    }
	
	@Override
	public int getType() {
		return R.layout.multimedia;
	}
	
	public View setUpView(View rawFeatureView, LayoutInflater inflater){
		RelativeLayout multimediaRelativeLayout = (RelativeLayout) rawFeatureView;

		TextView pathText = (TextView) multimediaRelativeLayout.getChildAt(2);
		pathText.setText("Nenhum arquivo adicionado");
		
		return multimediaRelativeLayout;
	}
	
	@Override
	public int describeContents() {
		Log.e(MULTIMEDIA_LOG_TAG, "SETOU CONTENTS p 6");
		return 6;
	}

	
}
