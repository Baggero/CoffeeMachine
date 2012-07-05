package se.coffeemachine.dialogs;

import se.coffeemachine.R;
import se.coffeemachine.controllers.SwipeController;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public final class CoffeeDialogs {
	private static final String TAG = CoffeeDialogs.class.getSimpleName();

	// Resources for Dialogs (151-200)
	public static final int MAKE_COFFEE_DIALOG = 151;
	public static final int STATE_1_DIALOG = 152;
	public static final int STATE_2_DIALOG = 153;

	public static MakeCoffeeDialog getMakeCoffeeDialog(Context context,
			SwipeController controller, String title, String message, int size,
			double currentVolume, double maxVolume) {
		return new MakeCoffeeDialog(context, controller, title, message, size,
				currentVolume, maxVolume);
	}

	public static Dialog stateOneDialog() {
		return null;
	}

	public static Dialog stateTwoDialog() {
		return null;
	}

	public static Dialog getCoffeeDialog(Context context,
			final SwipeController controller, String title, String message,
			int size, double currentVolume) {

		final Dialog dialog = new Dialog(context,
				android.R.style.Theme_Translucent);

		dialog.setContentView(R.layout.make_coffee_dialog);
		dialog.setTitle(R.string.big_coffee);

		LayoutParams lp = dialog.getWindow().getAttributes();
		lp.x = 60;
		lp.y = 20;
		lp.width = 700;
		lp.height = 450;
		lp.gravity = Gravity.TOP | Gravity.LEFT;
		lp.dimAmount = (float) 0.5;
		lp.flags = LayoutParams.FLAG_LAYOUT_NO_LIMITS
				| LayoutParams.FLAG_DIM_BEHIND;

		// TextView mTitle = (TextView) dialog
		// .findViewById(R.id.make_coffee_dialog_title);
		ImageView mCoffeeCup = (ImageView) dialog
				.findViewById(R.id.make_coffee_dialog_image);
		Button mMakeButton = (Button) dialog
				.findViewById(R.id.make_coffee_dialog_make_button);
		Button mResetButton = (Button) dialog
				.findViewById(R.id.make_coffee_dialog_reset_button);
		SeekBar mVolumeSeekbar = (SeekBar) dialog
				.findViewById(R.id.make_coffee_dialog_seekbar);
		EditText mVolumeEdittext = (EditText) dialog
				.findViewById(R.id.make_coffee_dialog_edittext);

		// mTitle.setText(title);
		mVolumeSeekbar.setProgress((int) currentVolume);
		mVolumeEdittext.setText(Integer.toString((int) currentVolume));
		switch (size) {
		case R.string.big_coffee:
			mCoffeeCup.setId(R.drawable.coffee_brown);
			break;
		case R.string.small_coffee:
			mCoffeeCup.setId(R.drawable.coffee_yellow);
			// mTitle.setText(R.string.small_coffee);
			break;
		default:
			break;
		}

		mMakeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
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
						if (event.getAction() == KeyEvent.ACTION_UP) {
							Log.i(TAG, "VolumeEditText changed");
							v.clearFocus();
						}

						// double volume = Double.parseDouble((String)
						// v.getText());
						// volume = (volume > 870) ? 870 : volume;
						// volume = (volume < 0) ? 0 : volume;
						// controller.handleMessage(
						// SwipeController.MESSAGE_SET_CURRENT_VALUE,
						// volume);
						return true;
					}
				});

		mCoffeeCup.requestFocus();

		dialog.setCanceledOnTouchOutside(true);
		return dialog;
	}
}
