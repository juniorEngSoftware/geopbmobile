package com.example.helloandroid;

import java.util.ArrayList;

import model.Option;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MultipleChoiceActivity extends Activity {
	
	private static final String MULTIPLE_CHOICE_LOG_TAG = "Multiple choice CLASS";

	private ArrayList<String> optionList = new ArrayList<String>();
	protected ArrayList<String> selectedOptions = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e(MULTIPLE_CHOICE_LOG_TAG, "MULTIPLE CHECK BOX  ==========================");
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.multiplecheckbox);

		showSelectColoursDialog();
	}

//	@Override
//	public void onClick(View view) {
//		switch(view.getId()) {
//			case R.id.select_colours:
//				showSelectColoursDialog();
//				break;
//
//			default:
//				break;
//		}
//	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	protected void onChangeSelectedColours() {
		StringBuilder stringBuilder = new StringBuilder();

		for(CharSequence colour : selectedOptions)
			stringBuilder.append(colour + ",");
		
//		selectColoursButton.setText(stringBuilder.toString());
	}

	protected void showSelectColoursDialog() {
		setOptionList(getIntent().getStringArrayListExtra("optionsName"));
		
		boolean[] checkedOptions = new boolean[getOptionList().size()];
		int count = getOptionList().size();

		for(int i = 0; i < count; i++)
			checkedOptions[i] = selectedOptions.contains(optionList.get(i));

		DialogInterface.OnMultiChoiceClickListener coloursDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedOptions.add(optionList.get(which));
				else
					selectedOptions.remove(optionList.get(which));

				onChangeSelectedColours();
			}
		};

		
		//FIXME refatorar
		CharSequence[] optionsArray = new CharSequence[getOptionList().size()];
		for (int i = 0; i < optionList.size(); i++) {
			optionsArray[i] = optionList.get(i);
		}
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Colours");
		builder.setPositiveButton("OK", new DialogButtonClickHandler() );
		builder.setMultiChoiceItems(optionsArray, checkedOptions, coloursDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public class DialogButtonClickHandler implements DialogInterface.OnClickListener{
		public void onClick( DialogInterface dialog, int clicked )
		{
			switch( clicked )
			{
				case DialogInterface.BUTTON_POSITIVE:
					finish();
					break;
			}
		}
	}
	
	@Override
	public void finish() {
		setReturnIntent();
		super.finish();
		
	}
	private void setReturnIntent() {
		Intent returnIntent= new Intent();
		returnIntent.putStringArrayListExtra("choices", selectedOptions);
		setResult(RESULT_OK, returnIntent);	
	}

	public void setOptionList(ArrayList<String> optionsNames) {
		this.optionList = optionsNames;
	}

	public ArrayList<String> getOptionList() {
		return optionList;
	}
}