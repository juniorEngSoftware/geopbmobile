package com.example.helloandroid;

import java.util.ArrayList;

import model.Option;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


public class MyExpandableListAdapter extends BaseExpandableListAdapter {

	 private Context context;
	 private ArrayList<String> groups;
	 private ArrayList<ArrayList<Option>> options;
	 private LayoutInflater inflater;
	 
	 public MyExpandableListAdapter(Context context, 
			 						ArrayList<String> groups,
			 						ArrayList<ArrayList<Option>> options ) { 
		 this.context = context;
		 this.groups = groups;
		 this.options = options;
		 inflater = LayoutInflater.from( context );
	 }
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return options.get( groupPosition ).get( childPosition );
//		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return (long)( groupPosition*1024+childPosition );  // Max 1024 children per group;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		View v = null;
        if( convertView != null )
            v = convertView;
        else
            v = inflater.inflate(R.layout.child_row, parent, false); 
        Option option = (Option)getChild( groupPosition, childPosition );
//		TextView color = (TextView)v.findViewById( R.id.childname );
//		if( color != null )
//			color.setText( option.getColor() );
//		TextView rgb = (TextView)v.findViewById( R.id.rgb );
//		if( rgb != null )
//			rgb.setText( option.getRgb() );
		CheckBox cb = (CheckBox)v.findViewById( R.id.check1 );
        cb.setChecked( option.getState() );
        return v;
//		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return options.get( groupPosition ).size();
//		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get( groupPosition ); 
//		return null;
	}

	@Override
	public int getGroupCount() {
		return groups.size();
//		return 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return (long)( groupPosition*1024 );  // To be consistent with getChildId
//		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		View v = null;
        if( convertView != null )
            v = convertView;
        else
            v = inflater.inflate(R.layout.group_row, parent, false); 
        return v;
//		return null;
	}

	@Override
	public boolean hasStableIds() {
		 return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		 return true;
	}
}