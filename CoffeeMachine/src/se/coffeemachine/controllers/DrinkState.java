package se.coffeemachine.controllers;


public class DrinkState extends SwipeState {

	public DrinkState(SwipeController controller) {
		super(controller);
	}

	@Override
	public boolean handleMessage(int what) {
		switch (what) {
		case SwipeController.MESSAGE_SET_COLD_FROTHED:
			model.setHeat(false);
			return true;
		case SwipeController.MESSAGE_SET_WARM_FROTHED:
			model.setHeat(true);
			return true;
		default:
			return super.handleMessage(what);
		}
	}

	@Override
	public boolean handleMessage(int what, Object data) {
		switch (what) {
		case SwipeController.MESSAGE_MAKE_BIG_COFFEE:
			model.setBigCoffeeCount(model.getBigCoffeeCount() + (Integer) data);
			return true;
		case SwipeController.MESSAGE_MAKE_SMALL_COFFEE:
			model.setSmallCoffeeCount(model.getSmallCoffeeCount()
					+ (Integer) data);
			return true;
		case SwipeController.MESSAGE_MAKE_CAPPUCCINO_MILK:
			model.setCappuccinoMilkCount(model.getCappuccinoMilkCount()
					+ (Integer) data);
			return true;
		case SwipeController.MESSAGE_MAKE_CAFE_LATTE_MILK:
			model.setCafeLatteMilkCount(model.getCafeLatteMilkCount()
					+ (Integer) data);
			return true;
		default:
			return super.handleMessage(what, data);
		}
	}
}
