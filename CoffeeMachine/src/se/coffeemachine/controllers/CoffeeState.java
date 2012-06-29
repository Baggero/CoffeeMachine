package se.coffeemachine.controllers;

import se.coffeemachine.vos.CoffeeVo;
import android.os.Handler;
import android.os.HandlerThread;

public abstract class CoffeeState implements ControllerState {

	public final HandlerThread workerThread;

	protected SwipeController controller;
	protected CoffeeVo model;

	protected final Handler workerHandler;

	protected Handler getWorkerHandler() {
		return workerHandler;
	}

	public CoffeeState(SwipeController controller) {
		this.controller = controller;
		this.model = controller.getModel();
		workerThread = new HandlerThread("Unlocked Save Thread");
		workerThread.start();
		workerHandler = new Handler(workerThread.getLooper());
	}

	@Override
	public void dispose() {
		workerThread.getLooper().quit();
	}

	@Override
	public boolean handleMessage(int what) {
		return handleMessage(what, null);
	}

	@Override
	public boolean handleMessage(int what, Object data) {
		switch (what) {
		case SwipeController.MESSAGE_SAVE_MODEL: // Add SwipeController.MESSAGE
			saveModel();
			return true;
		case SwipeController.MESSAGE_POPULATE_MODE: // Add
													// SwipeController.MESSAGE
			populateModel();
			return true;
		case SwipeController.MESSAGE_CREATE_NEW_MODEL:
			createNewModel();
			return true;
		default:
			return false;
		}
	}

	private void saveModel() {
		workerHandler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (model) {
					// Save Model
				}
			}
		});
	}

	private void populateModel() {
		workerHandler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (model) {
					// Populate model
				}
			}
		});
	}

	private void createNewModel() {
		// Create new model
	}

}
