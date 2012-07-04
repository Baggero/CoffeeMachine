package se.coffeemachine.controllers;

import se.coffeemachine.daos.CoffeeDao;
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
		case SwipeController.MESSAGE_SAVE_MODEL:
			saveModel();
			return true;
		case SwipeController.MESSAGE_RESET_MODEL:
			resetModel((Integer) data);
			return true;
		case SwipeController.MESSAGE_POPULATE_MODEL_BY_ID: // Add
			// SwipeController.MESSAGE
			populateModel((Integer) data);
			return true;
		case SwipeController.MESSAGE_CREATE_NEW_MODEL:
			createNewModel();
			return true;
		case SwipeController.MESSAGE_GET_COFFEE_MODEL:
			getFirstModel();
		default:
			return false;
		}
	}

	private void saveModel() {
		workerHandler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (model) {
					CoffeeDao dao = new CoffeeDao();
					if (model.getId() > 0) {
						int effected = dao.update(model);

						// this would be the case if
						// item is saved, item is deleted from list, user goes
						// history back,
						// old model still have id value.
						if (effected < 1) {
							long id = dao.insert(model);
							model.setId((int) id);
						}
					} else {
						long id = dao.insert(model);
						model.setId((int) id);
					}
					// Add statement below?
					// controller.notifyOutboxHandlers(TapController.MESSAGE_SAVE_COMPLETE,
					// 0, 0, null);
				}
			}
		});
	}

	private void getFirstModel() {
		workerHandler.post(new Runnable() {

			@Override
			public void run() {
				synchronized (model) {
					CoffeeDao dao = new CoffeeDao();
					CoffeeVo vo = dao.getFirst();
					if (vo == null)
						vo = new CoffeeVo();
					model.consume(vo);
				}

			}

		});

	}

	private void populateModel(final int id) {
		if (id < 0)
			return;
		workerHandler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (model) {
					CoffeeDao dao = new CoffeeDao();
					CoffeeVo vo = dao.getById(id);
					if (vo == null)
						vo = new CoffeeVo();
					model.consume(vo);
				}
			}
		});
	}

	private void resetModel(final int id) {
		workerHandler.post(new Runnable() {

			@Override
			public void run() {
				synchronized (model) {
					CoffeeVo vo = new CoffeeVo();
					vo.setId(id);
					model.consume(vo);
					saveModel();
				}

			}

		});
	}

	private void createNewModel() {
		CoffeeVo vo = new CoffeeVo();
		model.consume(vo);
	}

}
