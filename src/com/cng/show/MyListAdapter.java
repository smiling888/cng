package com.cng.show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;



public class MyListAdapter extends SimpleAdapter {
	private Context context; 
	private ArrayList<HashMap<String, Object>> data; private int layoutResource; 
	 public MyListAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, null	,null);
	
	}


	private LayoutInflater mInflater;
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
//		LayoutInflater layoutInflater = (LayoutInflater) ShowInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
//		View layoutView = layoutInflater.inflate(layoutResource, null); 
//		ListViewItemHolder viewHolder = new ListViewItemHolder(); 
//		viewHolder.listitemkey = (TextView) layoutView.findViewById(R.id.imageView); viewHolder.number = (TextView) layoutView.findViewById(R.id.number); 
//		viewHolder.name = (TextView) layoutView.findViewById(R.id.name); 
//		viewHolder.picture.setImageResource(Integer.parseInt(data.get( position).get("imageView").toString()));
//		viewHolder.number.setText(data.get(position).get("id").toString()); Log.e("id", data.get(position).get("name").toString()); 
//		viewHolder.name.setText(data.get(position).get("name").toString()); return layoutView; 
	return null;
	}



	

}