package com.example.helloandroid;

import java.util.ArrayList;

import model.Option;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;


public class MyExpandableListAdapter extends BaseExpandableListAdapter {

	 private Context context;
	 private ArrayList<String> groups;
	 private ArrayList<Option> options;
	 private LayoutInflater inflater;
	 
	 public MyExpandableListAdapter(Context context, 
			 						ArrayList<String> groups,
			 						ArrayList<Option> options ) { 
		 this.context = context;
		 this.groups = groups;
		 this.options = options;
		 inflater = LayoutInflater.from( context );
	 }
	@Override
	public Object getChild(int groupPosition, int childPosition) {
//		return options.get( groupPosition ).get( childPosition );
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return (long)( groupPosition*1024+childPosition );  // Max 1024 children per group;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
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