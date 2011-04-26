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
	
	public MultimediaFeature() {
	}
	
	public MultimediaFeature(Parcel in) {
		super.readFromParcel(in);
	}
	
	@Override
	public int getType() {
		return R.layout.multimedia;
	}
	
	public View setUpView(View inflate, LayoutInflater inflater){
		RelativeLayout multimediaRelativeLayout = (RelativeLayout) inflate;

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
