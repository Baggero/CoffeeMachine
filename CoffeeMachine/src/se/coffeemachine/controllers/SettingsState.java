package se.coffeemachine.controllers;

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
		// Add case: for every unique message for SettingsState
		default:
			return super.handleMessage(what, data);
		}
	}
}
