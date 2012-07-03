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
	public static final int MESSAGE_SET_UP = 4;
	public static final int MESSAGE_ANSWER_SET_UP = 5;

	// Message for all fragments. Handled in SwipeState
	public static final int MESSAGE_UPDATE_STATE = 6;

	// Messages for StatisticsFragment. Handled in StatisticsState
	public static final int MESSAGE_STATISTICS_SET_UP = 7;
	public static final int MESSAGE_STATISTICS_SET_UP_ANSWER = 8;

	// Messages for DrinkFragment. Handled in DrinkState
	public static final int MESSAGE_DRINKS_SET_UP = 9;
	public static final int MESSAGE_DRINKS_SET_UP_ANSWER = 10;
	public static final int MESSAGE_MAKE_BIG_COFFEE = 11;
	public static final int MESSAGE_MAKE_SMALL_COFFEE = 12;
	public static final int MESSAGE_MAKE_CAPPUCCINO_MILK = 13;
	public static final int MESSAGE_MAKE_CAFE_LATTE_MILK = 14;
	public static final int MESSAGE_SET_COLD_FROTHED = 15;
	public static final int MESSAGE_SET_WARM_FROTHED = 16;

	// Messages for SettingsFragment. Handled in SettingsState
	public static final int MESSAGE_SETTINGS_SET_UP = 17;
	public static final int MESSAGE_SETTINGS_SET_UP_ANSWER = 18;
	public static final int MESSAGE_SET_CURRENT_VALUE = 19;

	// Messages for ManualsFragment. Handled in ManualsState
	public static final int MESSAGE_MANUALS_SET_UP = 20;
	public static final int MESSAGE_MANUALS_SET_UP_ANSWER = 21;

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
