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

	// Messages that has nothing to do with the fragments (0 - 20)
	public static final int MESSAGE_GET_COFFEE_MODEL = 0;
	public static final int MESSAGE_INCREMENT_COUNT = 1;
	public static final int MESSAGE_SAVE_MODEL = 2;
	public static final int MESSAGE_RESET_MODEL = 3;
	public static final int MESSAGE_POPULATE_MODEL_BY_ID = 4;
	public static final int MESSAGE_CREATE_NEW_MODEL = 5;
	public static final int MESSAGE_SET_UP = 6;
	public static final int MESSAGE_ANSWER_SET_UP = 7;

	// Message for all fragments. Handled in SwipeState (21 - 40)
	public static final int MESSAGE_UPDATE_STATE = 21;

	// Messages for StatisticsFragment. Handled in StatisticsState (41-60)
	public static final int MESSAGE_STATISTICS_SET_UP = 41;
	public static final int MESSAGE_STATISTICS_SET_UP_ANSWER = 42;

	// Messages for DrinkFragment. Handled in DrinkState (61-80)
	public static final int MESSAGE_DRINKS_SET_UP = 61;
	public static final int MESSAGE_DRINKS_SET_UP_ANSWER = 62;
	public static final int MESSAGE_MAKE_BIG_COFFEE = 63;
	public static final int MESSAGE_MAKE_SMALL_COFFEE = 64;
	public static final int MESSAGE_MAKE_CAPPUCCINO_MILK = 65;
	public static final int MESSAGE_MAKE_CAFE_LATTE_MILK = 66;
	public static final int MESSAGE_SET_COLD_FROTHED = 67;
	public static final int MESSAGE_SET_WARM_FROTHED = 68;

	// Messages for SettingsFragment. Handled in SettingsState (81-100)
	public static final int MESSAGE_SETTINGS_SET_UP = 81;
	public static final int MESSAGE_SETTINGS_SET_UP_ANSWER = 82;
	public static final int MESSAGE_SET_CURRENT_VALUE = 83;

	// Messages for ManualsFragment. Handled in ManualsState (101-120)
	public static final int MESSAGE_MANUALS_SET_UP = 101;
	public static final int MESSAGE_MANUALS_SET_UP_ANSWER = 102;

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
