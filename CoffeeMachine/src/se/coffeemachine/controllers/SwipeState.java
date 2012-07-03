package se.coffeemachine.controllers;

import se.coffeemachine.activities.SwipeActivity;
import android.util.Log;

public abstract class SwipeState extends CoffeeState {

	private final static String TAG = SwipeState.class.getSimpleName();

	public SwipeState(SwipeController controller) {
		super(controller);
	}

	@Override
	public boolean handleMessage(int what) {
		return super.handleMessage(what);
	}

	@Override
	public boolean handleMessage(int what, Object data) {
		switch (what) {
		case SwipeController.MESSAGE_UPDATE_STATE:
			int position = (Integer) data;
			model.setCount(model.getCount(position) + 1, position);
			updateState((Integer) data);
			return true;
		case SwipeController.MESSAGE_INCREMENT_COUNT:
			int pos = (Integer) data;
			model.setCount(model.getCount(pos) + 1, pos);
			return true;
		case SwipeController.MESSAGE_STATISTICS_SET_UP:
			setUpStatistics();
			return true;
		case SwipeController.MESSAGE_DRINKS_SET_UP:
			setUpDrinks();
			return true;
		case SwipeController.MESSAGE_SETTINGS_SET_UP:
			setUpSettings();
			return true;
		case SwipeController.MESSAGE_MANUALS_SET_UP:
			setUpManuals();
			return true;
		default:
			return super.handleMessage(what, data);
		}
	}

	public void updateState(int position) {
		switch (position) {
		case SwipeActivity.STATISTICS_STATE:
			controller.setMessageState(new StatisticsState(controller));
			Log.i(TAG, "Settings statistics state");
			break;
		case SwipeActivity.DRINK_STATE:
			controller.setMessageState(new DrinkState(controller));
			Log.i(TAG, "Settings drink state");
			break;
		case SwipeActivity.MANUALS_STATE:
			break;
		case SwipeActivity.SETTINGS_STATE:
			controller.setMessageState(new SettingsState(controller));
			Log.i(TAG, "Settings settings state");
			break;
		default:
			Log.i(TAG,
					"There is no state for position "
							+ Integer.toString(position));
		}
	}

	private void setUpStatistics() {
		workerHandler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (model) {
					controller.notifyOutboxHandlers(
							SwipeController.MESSAGE_STATISTICS_SET_UP_ANSWER,
							0, 0, model);
				}
			}
		});
	}

	private void setUpDrinks() {
		// Similar to setUpStatistics()
	}

	private void setUpSettings() {
		// Similar to setUpStatistics()
	}

	private void setUpManuals() {
		// Similar to setUpStatistics()
	}
}
