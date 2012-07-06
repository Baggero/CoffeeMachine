package se.coffeemachine.controllers;

public class StatisticsState extends SwipeState {

	private static final String TAG = StatisticsState.class.getSimpleName();

	public StatisticsState(SwipeController controller) {
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
		// Add case: for every unique message for StatisticsFragment
		default:
			return super.handleMessage(what, data);
		}
	}

}
