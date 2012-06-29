package se.coffeemachine.vos;

import android.util.Log;

public class CoffeeVo extends SimpleObservable<CoffeeVo> {

	private final String TAG = CoffeeVo.class.getSimpleName();

	private final int[] count = new int[] { 0, 0, 0, 0 };
	private boolean milk_heat = false;
	private int big_coffee_count = 0;
	private int small_coffee_count = 0;
	private int cappuccino_milk_count = 0;
	private int cafe_latte_milk_count = 0;

	public int getBigCoffeeCount() {
		return big_coffee_count;
	}

	public void setBigCoffeeCount(int count) {
		big_coffee_count = count;
		Log.i(TAG, "Big coffee event handled count=" + getBigCoffeeCount());
		notifyObservers(this);
	}

	public int getSmallCoffeeCount() {
		return small_coffee_count;
	}

	public void setSmallCoffeeCount(int count) {
		small_coffee_count = count;
		Log.i(TAG, "Small coffee event handled count=" + getSmallCoffeeCount());
		notifyObservers(this);
	}

	public int getCappuccinoMilkCount() {
		return cappuccino_milk_count;
	}

	public void setCappuccinoMilkCount(int count) {
		cappuccino_milk_count = count;
		Log.i(TAG, "Cappuccino milk event handled count="
				+ getCappuccinoMilkCount());
		notifyObservers(this);
	}

	public int getCafeLatteMilkCount() {
		return cafe_latte_milk_count;
	}

	public void setCafeLatteMilkCount(int count) {
		cafe_latte_milk_count = count;
		Log.i(TAG, "Cafe latte event handled count=" + getCafeLatteMilkCount());
		notifyObservers(this);
	}

	public int getCount(int index) {
		return count[index];
	}

	public void setCount(int count, int index) {
		this.count[index] = count;
		notifyObservers(this);
	}

	public boolean getHeat() {
		return milk_heat;
	}

	public void setHeat(boolean heat) {
		milk_heat = heat;
		Log.i(TAG, "Change heat event handled. Heat=" + heat);
		notifyObservers(this);
	}
}
