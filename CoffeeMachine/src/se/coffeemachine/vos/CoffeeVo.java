package se.coffeemachine.vos;

import android.util.Log;

public class CoffeeVo extends SimpleObservable<CoffeeVo> {

	private final String TAG = CoffeeVo.class.getSimpleName();

	private int id = -1;
	private final Integer[] count = new Integer[] { 0, 0, 0, 0 };
	private boolean milk_heat = false;
	private int big_coffee_count = 0;
	private int small_coffee_count = 0;
	private int cappuccino_milk_count = 0;
	private int cafe_latte_milk_count = 0;
	private final Double[] coffee_volume = new Double[] { 30.0, 0.0, 870.0 };

	// {current value, min value, max value}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		notifyObservers(this);
	}

	public double getCurrentVolume() {
		return coffee_volume[0];
	}

	public void setCurrentVolume(double volume) {
		coffee_volume[0] = volume;
		notifyObservers(this);
	}

	public Double getMaxVolume() {
		return coffee_volume[2];
	}

	public void setMaxVolume(double volume) {
		coffee_volume[2] = volume;
		notifyObservers(this);
	}

	public void setMinVolume(double volume) {
		coffee_volume[1] = volume;
		notifyObservers(this);
	}

	public Double getMinVolume() {
		return coffee_volume[1];
	}

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

	public Integer[] getCountArray() {
		return count;
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

	@Override
	synchronized public CoffeeVo clone() {
		CoffeeVo vo = new CoffeeVo();
		vo.setId(id);
		vo.setCurrentVolume(coffee_volume[0]);
		vo.setMinVolume(coffee_volume[1]);
		vo.setMaxVolume(coffee_volume[2]);
		vo.setBigCoffeeCount(big_coffee_count);
		vo.setSmallCoffeeCount(small_coffee_count);
		vo.setCappuccinoMilkCount(cappuccino_milk_count);
		vo.setCafeLatteMilkCount(cafe_latte_milk_count);
		vo.setCount(count[0], 0);
		vo.setCount(count[1], 1);
		vo.setCount(count[2], 2);
		vo.setCount(count[3], 3);
		vo.setHeat(milk_heat);
		return vo;
	}

	synchronized public void consume(CoffeeVo vo) {
		this.id = vo.getId();
		this.coffee_volume[0] = vo.getCurrentVolume();
		this.coffee_volume[1] = vo.getMinVolume();
		this.coffee_volume[2] = vo.getMaxVolume();
		this.big_coffee_count = vo.getBigCoffeeCount();
		this.small_coffee_count = vo.getSmallCoffeeCount();
		this.cafe_latte_milk_count = vo.getCafeLatteMilkCount();
		this.cappuccino_milk_count = vo.getCappuccinoMilkCount();
		this.count[0] = vo.getCount(0);
		this.count[1] = vo.getCount(1);
		this.count[2] = vo.getCount(2);
		this.count[3] = vo.getCount(3);
		this.milk_heat = vo.getHeat();
		notifyObservers(this);
	}
}
