package model;

import android.util.Log;

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


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Name: " + super.getName() + "\n" + "Content: " + this.content;
	}
	
	@Override
	public int describeContents() {
		Log.e("TEXT_FEATURE", "SETOU CONTENTS p 1");
		return 1;
	}
}
