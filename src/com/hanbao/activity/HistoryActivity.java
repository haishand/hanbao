package com.hanbao.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hanbao.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryActivity extends ListActivity {
	private List<Map<String, Object>> lsData;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_history);
		
		lsData = this.getLsData();
		MyAdapter adapter = new MyAdapter(this);
		setListAdapter(adapter);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.v("HistoryActivity", (String)lsData.get(position).get("word"));
	}

	private List<Map<String, Object>> getLsData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map; 
		
		for (int i=0; i<10; i++) {
			
			map = new HashMap<String, Object>();
			map.put("word", "test"+i);
			map.put("proun", "xian");
			map.put("mean", "this is a test"+i);
			list.add(map);
		}
		
		return list;
	}

	public final class ViewHolder {
		public TextView word;
		public TextView proun;
		public TextView mean;
		public Button button;
	}
	
	public class MyAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		
		public MyAdapter(Context context) {
			this.inflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return lsData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.activity_history, null);
				holder.word = (TextView)convertView.findViewById(R.id.word);
				holder.proun = (TextView)convertView.findViewById(R.id.proun);
				holder.mean = (TextView)convertView.findViewById(R.id.mean);
				holder.button = (Button)convertView.findViewById(R.id.btn_delete);
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder)convertView.getTag();
				
			}
			holder.word.setText((String)lsData.get(position).get("word"));
			holder.proun.setText((String)lsData.get(position).get("proun"));
			holder.mean.setText((String)lsData.get(position).get("mean"));
			holder.button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			return convertView;
		}
	}
}
