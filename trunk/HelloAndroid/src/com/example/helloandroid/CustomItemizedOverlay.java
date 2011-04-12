package com.example.helloandroid;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CustomItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	
	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	
	private Context context;
	
	public CustomItemizedOverlay(Drawable defaultMarker) {
		  super(boundCenterBottom(defaultMarker));
	}
	
	public CustomItemizedOverlay(Drawable defaultMarker, Context context) {
		  this(defaultMarker);
		  this.context = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return getMapOverlays().get(i);
	}

	@Override
	public int size() {
		return getMapOverlays().size();
	}
	
	@Override
	protected boolean onTap(int index) {
		OverlayItem item = getMapOverlays().get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}
	
	public void addOverlay(OverlayItem overlay) {
		getMapOverlays().clear();
		getMapOverlays().add(overlay);
	    this.populate();
	}

	public void setMapOverlays(ArrayList<OverlayItem> mapOverlays) {
		this.mapOverlays = mapOverlays;
	}

	public ArrayList<OverlayItem> getMapOverlays() {
		return mapOverlays;
	}

}