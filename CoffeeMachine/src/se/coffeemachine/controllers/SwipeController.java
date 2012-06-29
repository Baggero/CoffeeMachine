package se.coffeemachine.controllers;

import se.coffeemachine.vos.CoffeeVo;
import android.util.Log;

public class SwipeController extends Controller {

	// TODO See if there is need to add another class between SwipeController
	// and Controller to handlemessages for all activites.

	private static final String TAG = SwipeController.class.getSimpleName();

	private ControllerState messageState;

	protected void setMessageState(ControllerState messageState) {
		if (this.messageState != null) {
			this.messageState.dispose();
		}
		this.messageState = messageState;
	}

	public static final int MESSAGE_INCREMENT_COUNT = 0;
	public static final int MESSAGE_SAVE_MODEL = 1;
	public static final int MESSAGE_POPULATE_MODE = 2;
	public static final int MESSAGE_CREATE_NEW_MODEL = 3;

	// Message for all fragments. Handled in SwipeState
	public static final int MESSAGE_UPDATE_STATE = 4;

	// Messages for StatisticsFragment. Handled in StatisticsState
	public static final int MESSAGE_GET_COUNTERS = 5;
	public static final int MESSAGE_STATISTICS_UPDATED = 6;

	// Messages for DrinkFragment. Handled in DrinkState
	public static final int MESSAGE_MAKE_BIG_COFFEE = 6;
	public static final int MESSAGE_MAKE_SMALL_COFFEE = 7;
	public static final int MESSAGE_MAKE_CAPPUCCINO_MILK = 8;
	public static final int MESSAGE_MAKE_CAFE_LATTE_MILK = 9;
	public static final int MESSAGE_SET_COLD_FROTHED = 10;
	public static final int MESSAGE_SET_WARM_FROTHED = 11;

	// Messages for SettingsFragment. Handled in SettingsState

	// Messages for ManualsFragment. Handled in ManualsState

	private final CoffeeVo model;

	public SwipeController(CoffeeVo model) {
		this.model = model;
		messageState = new DrinkState(this);
	}

	public CoffeeVo getModel() {
		return model;
	}

	@Override
	public boolean handleMessage(int what, Object data) {
		Log.i(TAG, "handling message code of " + what);
		return messageState.handleMessage(what, data);
	}

	@Override
	public boolean handleMessage(int what) {
		Log.i(TAG, "handling message code of " + what);
		return messageState.handleMessage(what);
	}

}
