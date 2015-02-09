package com.cng.show;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gow.qrcodedemo.R;
import com.gow.qrcodedemo.R.id;
import com.gow.qrcodedemo.R.layout;

public class ShowInfoActivity extends Activity {
	private final static String TAG = "ShowInfo";
	private final String QRRESULT = "qrresult";
	ListView listView;
	String[] c1;
	String[] c2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.data_show2);
		setTitle("供气单基本信息");
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 String result = getIntent().getExtras().getString(QRRESULT);
//		String result = "date,2015/01/01 14:58:32;vehicle_id,京x;氮气,08;c02,1;h2,1";
		if (result != null) {
			String[] arrayResult = result.split(";");
			c1 = new String[arrayResult.length];
			c2 = new String[arrayResult.length];
			for (int i = 0; i < arrayResult.length; i++) {
				String[] item = arrayResult[i].split(",");
				c1[i] = item[0];
				c2[i] = item[1];
			}
			listView = (ListView) findViewById(R.id.datalist1);
			listView.setAdapter(new MyAdapter(c1, c2));
		}
	}

	public class MyAdapter extends BaseAdapter {
		String[] c1, c2;

		public MyAdapter(String[] c1, String[] c2) {
			this.c1 = c1;
			this.c2 = c2;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return c1.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return c1[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) ShowInfoActivity.this
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.list_item_data, null);
			}

			TextView key = (TextView) convertView
					.findViewById(R.id.list_item_value);
			key.setText(c1[position]);

			TextView value = (TextView) convertView
					.findViewById(R.id.list_item_key);
			value.setText(c2[position]);

			return convertView;
		}

	}
	// for (int i = 0; i < resultArray.length; i++) {
	// key = (TextView) findViewById(table.get(resultindex++));
	// key.setText(resultArray[1].split(",")[0]);
	// value = (TextView) findViewById(table.get(resultindex++));
	// value.setText(resultArray[1].split(",")[1]);
	// }
	// initTable();
	// ListView list = (ListView) findViewById(R.id.datalist1);
	//
	// // 生成动态数组，加入数据
	// ArrayList<HashMap<String, Object>> listItem = new
	// ArrayList<HashMap<String, Object>>();
	// for (int i = 0; i < 10; i++) {
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// // 图像资源的ID
	// map.put("ItemTitle", "Level " + i);
	// map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
	// listItem.add(map);
	// }
	// // 生成适配器的Item和动态数组对应的元素
	// SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
	// R.layout.list_item_data,// ListItem的XML实现
	// // 动态数组与ImageItem对应的子项
	// new String[] { "ItemTitle", "ItemText" },
	// // ImageItem的XML文件里面的一个ImageView,两个TextView ID
	// new int[] { R.id.list_item_key, R.id.list_item_value });
	//
	// // 添加并且显示
	// list.setAdapter(listItemAdapter);
	// 绑定Layout里面的ListView

	// private void initTable() {
	// table.add(R.id.t1r1c1);
	// table.add(R.id.t1r1c2);
	// table.add(R.id.t1r2c1);
	// table.add(R.id.t1r2c2);
	// table.add(R.id.t1r3c1);
	// table.add(R.id.t1r3c2);
	//
	// table.add(R.id.t2r1c1);
	// table.add(R.id.t2r1c2);
	// table.add(R.id.t2r2c1);
	// table.add(R.id.t2r2c2);
	// table.add(R.id.t2r3c1);
	// table.add(R.id.t2r3c2);
	//
	// }

}
