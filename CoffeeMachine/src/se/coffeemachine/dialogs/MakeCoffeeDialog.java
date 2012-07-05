package se.coffeemachine.dialogs;

import se.coffeemachine.R;
import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.utils.VolumeUtils;
import se.coffeemachine.vos.CoffeeVo;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MakeCoffeeDialog extends CoffeeInteractionDialog {

	private static final String TAG = MakeCoffeeDialog.class.getSimpleName();

	private TextView mTitle;
	private ImageView mCoffeeCup;
	private Button mMakeButton;
	private Button mResetButton;
	private SeekBar mVolumeSeekbar;
	private EditText mVolumeEdittext;

	private final int size;
	private final double currentVolume;
	private final double maxVolume;

	public MakeCoffeeDialog(Context context, SwipeController controller,
			String title, String message, int size, double currentVolume,
			double maxVolume) {
		super(context, controller, title, message,
				android.R.style.Theme_Translucent);
		setContentView(R.layout.make_coffee_dialog);
		setTitle(title);

		this.size = size;
		this.currentVolume = currentVolume;
		this.maxVolume = maxVolume;
	}

	@Override
	public void onCreate(Bundle onSavedInstance) {
		mTitle = (TextView) findViewById(R.id.make_coffee_dialog_title);
		mCoffeeCup = (ImageView) findViewById(R.id.make_coffee_dialog_image);
		mMakeButton = (Button) findViewById(R.id.make_coffee_dialog_make_button);
		mResetButton = (Button) findViewById(R.id.make_coffee_dialog_reset_button);
		mVolumeSeekbar = (SeekBar) findViewById(R.id.make_coffee_dialog_seekbar);
		mVolumeEdittext = (EditText) findViewById(R.id.make_coffee_dialog_edittext);

		mVolumeSeekbar.setProgress(VolumeUtils.weightVolume(currentVolume,
				maxVolume));
		mVolumeEdittext.setText(Integer.toString((int) currentVolume));
		switch (size) {
		case R.string.big_coffee:
			mCoffeeCup.setId(R.drawable.coffee_brown);
			break;
		case R.string.small_coffee:
			mCoffeeCup.setId(R.drawable.coffee_yellow);
			mTitle.setText(R.string.small_coffee);
			break;
		default:
			break;
		}

		initActions();
		mCoffeeCup.requestFocus();
		setCanceledOnTouchOutside(true);
	}

	private void initActions() {
		mMakeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				controller.handleMessage(
						SwipeController.MESSAGE_MAKE_BIG_COFFEE, 1);
				dismiss();
				Log.i(TAG, "make button clicked");
			}
		});

		mResetButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "Reset button clicked");

			}
		});

		mVolumeSeekbar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						Log.i(TAG, "onStopTrackingTouch changed");
						controller.handleMessage(
								SwipeController.MESSAGE_SET_CURRENT_VALUE,
								(double) seekBar.getProgress());

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						// TODO Auto-generated method stub

					}
				});

		mVolumeEdittext
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {

					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						Log.i(TAG, "VolumeEditText changed");
						double volume = Double.parseDouble((String) v.getText());
						volume = (volume > 870) ? 870 : volume;
						volume = (volume < 0) ? 0 : volume;
						controller.handleMessage(
								SwipeController.MESSAGE_SET_CURRENT_VALUE,
								volume);
						return true;
					}
				});
	}

	@Override
	public void updateDialog(CoffeeVo model) {
		mVolumeSeekbar.setProgress(VolumeUtils.weightVolume(
				model.getCurrentVolume(), model.getMaxVolume()));
		mVolumeEdittext.setText(Double.toString(model.getCurrentVolume()));

	}
}
