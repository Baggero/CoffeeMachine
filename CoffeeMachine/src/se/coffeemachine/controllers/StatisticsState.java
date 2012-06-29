package se.coffeemachine.controllers;

public class StatisticsState extends SwipeState {

	private static final String TAG = StatisticsState.class.getSimpleName();

	public StatisticsState(SwipeController controller) {
		super(controller);
	}

	@Override
	public boolean handleMessage(int what) {
		switch (what) {
		case SwipeController.MESSAGE_GET_COUNTERS:
			getCounters();
			return true;
		default:
			return super.handleMessage(what);
		}
	}

	@Override
	public boolean handleMessage(int what, Object data) {
		switch (what) {
		default:
			return super.handleMessage(what, data);
		}
	}

	private void getCounters() {
		workerHandler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (model) {
					controller.notifyOutboxHandlers(
							SwipeController.MESSAGE_STATISTICS_UPDATED, 0, 0,
							model);
				}
			}
		});
	}

}
