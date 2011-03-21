package model;

import com.example.helloandroid.R;

import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class TextFeature extends Feature{

	private String content;

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
		super(in);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	@Override
	public int getType() {
		return R.layout.edittext;
	}
	
	@Override
	public View setUpView(View inflate){
		EditText editText = (EditText) inflate;
//		editText.setText(this.getName());
		
		return editText;
	}
	
	@Override
	public String toString() {
		return "Name: " + super.getName() + "\n" + "Content: " + this.content;
	}
	
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		Log.e("TEXT_FEATURE", "SETOU O NOME subclasse");
//		super.writeToParcel(dest, flags);
//	}
	
	@Override
	public int describeContents() {
		Log.e("TEXT_FEATURE", "SETOU CONTENTS p 1");
		return 1;
	}
}
