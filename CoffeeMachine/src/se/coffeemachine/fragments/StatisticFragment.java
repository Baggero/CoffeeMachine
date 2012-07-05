package se.coffeemachine.fragments;

import se.coffeemachine.R;
import se.coffeemachine.adapters.StatisticsListAdapter;
import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.utils.CoffeeStateUtils;
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		counters = new Integer[] { 0, 0, 0, 0 };
		controller.addOutboxHandler(new Handler(this));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.statistics, container, false);
		mList = (ListView) view.findViewById(R.id.listView);

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
		Log.i(TAG, "Created");
		return view;
	}

	private void fetchData() {
		Log.i(TAG, "fetchData");
		context.handleMessage(SwipeController.MESSAGE_STATISTICS_SET_UP);

	}

	@Override
	public void onDestroyView() {
		super.onDestroy();
		Log.d(TAG, "onDestroyView");
	}

	@Override
	public void updateFragment(final CoffeeVo model) {
		Log.i(TAG, "updateFragment");
		((Activity) context).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				counters = model.getCountArray();
				mText1.setText("Statistics: " + (counters[0]).toString());
				mText2.setText("Drinks: " + (counters[1]).toString());
				mText3.setText("Settings: " + (counters[2]).toString());
				mText4.setText("Manuals: " + (counters[3]).toString());

				int state = model.getCurrentState();
				switch (state) {
				case CoffeeStateUtils.STATE_3:
					break;
				case CoffeeStateUtils.STATE_4:
					break;
				default:
					break;
				}

				adapter.notifyDataSetChanged();
			}

		});
	}

	private void fullDisplayMode() {
		// Enable all components
		mList.setEnabled(true);
		mText1.setEnabled(true);
		mText2.setEnabled(true);
		mText3.setEnabled(true);
		mText4.setEnabled(true);
	}

	private void restrictedDisplayMode1() {
		// Disable some of the components
	}

	private void restrictedDisplayMode2() {
		// Disable some of the components
	}

	@Override
	public boolean handleMessage(final Message msg) {
		Log.i(TAG, "handleMessage");
		switch (msg.what) {
		case SwipeController.MESSAGE_STATISTICS_SET_UP_ANSWER:
			Log.i(TAG, "handleMessage - case: MESSAGE_STATISTICS_SET_UP");
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Log.i(TAG, "handleMessage - inside run()");
					counters = ((CoffeeVo) msg.obj).getCountArray();
					adapter = new StatisticsListAdapter((Context) context,
							counters);
					mList.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
			});
			return true;
		}
		return false;
	}
}
