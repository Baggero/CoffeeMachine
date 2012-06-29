package se.coffeemachine.fragments;

import se.coffeemachine.R;
import se.coffeemachine.adapters.StatisticsListAdapter;
import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.vos.CoffeeVo;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class StatisticFragment extends SwipeFragment implements
		Handler.Callback {
	public StatisticFragment(SwipeContext context, SwipeController controller) {
		super(context, controller);
		Log.i(TAG, "Trying to create");
		counters = new Integer[] { 0, 0, 0, 0 };
		controller.addOutboxHandler(new Handler(this));
	}

	public static final String TAG = StatisticFragment.class.getSimpleName();

	ImageView mImage;
	ListView mList;
	TextView mText1;
	TextView mText2;
	TextView mText3;
	TextView mText4;

	private StatisticsListAdapter adapter;
	private Integer[] counters;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.statistics, container, false);

		adapter = new StatisticsListAdapter((Context) context, counters);
		mList = (ListView) view.findViewById(R.id.listView);
		mList.setAdapter(adapter);

		mImage = (ImageView) view.findViewById(R.id.imageView1);
		mText1 = (TextView) view.findViewById(R.id.textView1);
		mText2 = (TextView) view.findViewById(R.id.textView2);
		mText3 = (TextView) view.findViewById(R.id.textView3);
		mText4 = (TextView) view.findViewById(R.id.textView4);

		mImage.setClickable(true);
		mImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "StatisticFragment image clicked");
				context.handleMessage(SwipeController.MESSAGE_INCREMENT_COUNT,
						0);
			}
		});
		fetchData();
		Log.i(TAG, "StatisticFragment view created");
		return view;
	}

	private void fetchData() {
		Log.i(TAG, "fetchData");
		context.handleMessage(SwipeController.MESSAGE_GET_COUNTERS);

	}

	@Override
	public void updateFragment(CoffeeVo model) {
		Log.i(TAG, "updateFragment");
		mText1.setText("Statistics: "
				+ ((Integer) model.getCount(0)).toString());
		mText2.setText("Drinks: " + ((Integer) model.getCount(1)).toString());
		mText3.setText("Settings: " + ((Integer) model.getCount(2)).toString());
		mText4.setText("Manuals: " + ((Integer) model.getCount(3)).toString());
		counters = model.getCountArray();
		adapter.notifyDataSetChanged();

	}

	@Override
	public boolean handleMessage(final Message msg) {
		Log.i(TAG, "handleMessage");
		switch (msg.what) {
		case SwipeController.MESSAGE_STATISTICS_UPDATED:
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					counters = ((CoffeeVo) msg.obj).getCountArray();
					adapter.notifyDataSetChanged();
				}
			});
			return true;
		}
		return false;
	}
}
