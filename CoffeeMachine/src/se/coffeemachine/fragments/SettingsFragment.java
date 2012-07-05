package se.coffeemachine.fragments;

import se.coffeemachine.R;
import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.utils.VolumeUtils;
import se.coffeemachine.vos.CoffeeVo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsFragment extends SwipeFragment implements Handler.Callback {
	public SettingsFragment(SwipeContext context, SwipeController controller) {
		super(context, controller);
		Log.i(TAG, "Trying to create");
	}

	public static final String TAG = SettingsFragment.class.getSimpleName();

	ImageView mImage;
	SeekBar mSeekBar;
	TextView mText1;
	TextView mText2;
	TextView mText3;
	TextView mText4;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "SettingsFragment created");
		View view = inflater.inflate(R.layout.settings, container, false);
		mSeekBar = (SeekBar) view.findViewById(R.id.seekBar1);
		mSeekBar.setMax(VolumeUtils.SEEKBAR_MAX);
		mImage = (ImageView) view.findViewById(R.id.imageView1);
		mText1 = (TextView) view.findViewById(R.id.textView1);
		mText2 = (TextView) view.findViewById(R.id.textView2);
		mText3 = (TextView) view.findViewById(R.id.textView3);
		mText4 = (TextView) view.findViewById(R.id.textView4);

		mImage.setClickable(true);
		mImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "SettingsFragment image clicked");
				context.handleMessage(SwipeController.MESSAGE_INCREMENT_COUNT,
						2);
			}
		});
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.i(TAG, "stop tracking");
				context.handleMessage(
						SwipeController.MESSAGE_SET_CURRENT_VALUE,
						(double) seekBar.getProgress());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.i(TAG, "start tracking");

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Log.i(TAG, "onProgressChanged");

			}
		});
		fetchData();
		Log.i(TAG, "Created");
		return view;
	}

	private void fetchData() {
		Log.i(TAG, "fetchData");
		context.handleMessage(SwipeController.MESSAGE_SETTINGS_SET_UP);

	}

	@Override
	public void updateFragment(CoffeeVo model) {
		mText1.setText("Statistics: "
				+ ((Integer) model.getCount(0)).toString());
		mText2.setText("Drinks: " + ((Integer) model.getCount(1)).toString());
		mText3.setText("Settings: " + ((Integer) model.getCount(2)).toString());
		mText4.setText("Manuals: " + ((Integer) model.getCount(3)).toString());
		mSeekBar.setProgress(VolumeUtils.weightVolume(model.getCurrentVolume(),
				model.getMaxVolume()));
	}

	@Override
	public boolean handleMessage(final Message msg) {
		Log.i(TAG, "handleMessage");
		switch (msg.what) {
		case SwipeController.MESSAGE_SETTINGS_SET_UP_ANSWER:
			// Look at StatisticsFragment.handleMessage
			return true;
		}
		return false;
	}

	public int weightSeekBar(Double volume, Double max) {
		return (int) ((volume / max) * mSeekBar.getMax());
	}
}
