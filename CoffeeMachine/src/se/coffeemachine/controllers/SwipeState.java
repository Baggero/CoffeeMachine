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
		default:
			return super.handleMessage(what, data);
		}
	}

	public void updateState(int position) {
		switch (position) {
		case SwipeActivity.STATISTICS_STATE:
			break;
		case SwipeActivity.DRINK_STATE:
			controller.setMessageState(new DrinkState(controller));
			break;
		case SwipeActivity.MANUALS_STATE:
			break;
		case SwipeActivity.SETTINGS_STATE:
			break;
		default:
			Log.i(TAG,
					"There is no state for position "
							+ Integer.toString(position));
		}
	}

}
