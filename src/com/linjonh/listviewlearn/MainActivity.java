package com.linjonh.listviewlearn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView mListView;
	private List<HashMap<String, String>> data;
	private String[] strArray;
	private Button btn1;
	private Button btn2;
	private EditText edt;
	int count = 0;
	static {
		// System.loadLibrary()
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) this.findViewById(R.id.listView1);
		strArray = getResources().getStringArray(R.array.lsitText);
		data = new ArrayList<HashMap<String, String>>();
		for (String str : strArray) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("key", str);
			data.add(map);
		}
		final SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item_layout,
				new String[]{"key"}, new int[]{R.id.text1});

		// mListView.setStackFromBottom(true);
		mListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		TextView tv = new TextView(this);
		tv.setText("footer fixed TextView");

		mListView.addFooterView(tv);// NOTE: Call this before calling setAdapter.
		mListView.addHeaderView(tv);// NOTE: Call this before calling setAdapter.

		mListView.setItemsCanFocus(true);

		mListView.setSelectionAfterHeaderView();
		mListView.setOverscrollFooter(getResources().getDrawable(R.drawable.ab_solid_green));
		mListView.setOverscrollHeader(getResources().getDrawable(R.drawable.hh));
		ImageView up = new ImageView(this);
		up.setBackgroundColor(Color.BLUE);
		LayoutParams params = new LayoutParams(50, 50);
		up.setLayoutParams(params);

		ImageView down = new ImageView(this);
		down.setLayoutParams(params);
		down.setBackgroundColor(Color.RED);
		mListView.setScrollIndicators(up, down);
		mListView.setAdapter(adapter);

		mListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Log.i("selectionItemPosition", String.valueOf(mListView.getSelectedItemPosition()));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				Log.i("selectionItemPosition,onNothingSelected",
						String.valueOf(mListView.getSelectedItemPosition()));
			}
		});
		btn1 = (Button) this.findViewById(R.id.button1);
		btn2 = (Button) this.findViewById(R.id.button2);
		edt = (EditText) this.findViewById(R.id.editText1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("key", String.valueOf(new Random().nextInt()));
				data.add(map);
				adapter.notifyDataSetChanged();
			}
		});

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// int position = Integer.valueOf(edt.getText().toString());
				// mListView.smoothScrollToPosition(position);
				// mListView.setSelection(position);
				count += 10;
				mListView.setScrollY(count);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
