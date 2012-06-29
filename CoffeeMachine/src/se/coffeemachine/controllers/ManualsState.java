package se.coffeemachine.controllers;

public class ManualsState extends SwipeState {

	public ManualsState(SwipeController controller) {
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
		default:
			return super.handleMessage(what, data);
		}
	}

}
