package se.coffeemachine.controllers;

import android.util.Log;

public class SettingsState extends SwipeState {
	private final String TAG = SettingsState.class.getSimpleName();

	public SettingsState(SwipeController controller) {
		super(controller);
	}

	@Override
	public boolean handleMessage(int what) {
		switch (what) {
		default:
			return super.handleMessage(what);
		}
	}

	@Override
	public boolean handleMessage(int what, Object data) {
		switch (what) {
		case SwipeController.MESSAGE_SET_CURRENT_VALUE:
			double volume = (((Double) data) / 100) * model.getMaxVolume();
			Log.d(TAG, "Current Volume=" + Double.toString(volume) + " Max="
					+ Double.toString(model.getMaxVolume()) + " Value="
					+ Double.toString((Double) data));
			model.setCurrentVolume(volume);
			return true;
		default:
			return super.handleMessage(what, data);
		}
	}
}
