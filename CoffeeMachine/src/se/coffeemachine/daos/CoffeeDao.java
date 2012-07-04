package se.coffeemachine.daos;

import se.coffeemachine.vos.CoffeeVo;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CoffeeDao {

	private static final String TAG = CoffeeDao.class.getSimpleName();

	protected static final String TABLE = "Coffee";
	protected static final String _ID = "_id";
	protected static final String CURRENT_VOLUME = "current_volume";
	protected static final String MIN_VOLUME = "min_volume";
	protected static final String MAX_VOLUME = "max_volume";
	protected static final String BIG_COUNT = "big_count";
	protected static final String SMALL_COUNT = "small_count";
	protected static final String CAPPUCCINO_COUNT = "cappuccino_count";
	protected static final String CAFE_LATTE_COUNT = "cafe_latte_count";
	protected static final String STAT_COUNT = "stat_count";
	protected static final String DRINK_COUNT = "drink_count";
	protected static final String SETT_COUNT = "sett_count";
	protected static final String MAN_COUNT = "man_count";
	protected static final String HEAT = "heat";

	public CoffeeDao() {

	}

	public CoffeeVo getFirst() {
		Log.i(TAG, "getFirst()");
		SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		CoffeeVo vo = null;
		if (cursor.moveToFirst()) {
			vo = new CoffeeVo();
			vo.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
			vo.setCurrentVolume(cursor.getDouble(cursor
					.getColumnIndex(CURRENT_VOLUME)));
			vo.setMinVolume(cursor.getDouble(cursor.getColumnIndex(MIN_VOLUME)));
			vo.setMaxVolume(cursor.getDouble(cursor.getColumnIndex(MAX_VOLUME)));
			vo.setBigCoffeeCount(cursor.getInt(cursor.getColumnIndex(BIG_COUNT)));
			vo.setSmallCoffeeCount(cursor.getInt(cursor
					.getColumnIndex(SMALL_COUNT)));
			vo.setCappuccinoMilkCount(cursor.getInt(cursor
					.getColumnIndex(CAPPUCCINO_COUNT)));
			vo.setCafeLatteMilkCount(cursor.getInt(cursor
					.getColumnIndex(CAFE_LATTE_COUNT)));
			vo.setCount(cursor.getInt(cursor.getColumnIndex(STAT_COUNT)), 0);
			vo.setCount(cursor.getInt(cursor.getColumnIndex(DRINK_COUNT)), 1);
			vo.setCount(cursor.getInt(cursor.getColumnIndex(SETT_COUNT)), 2);
			vo.setCount(cursor.getInt(cursor.getColumnIndex(MAN_COUNT)), 3);
			vo.setHeat(cursor.getInt(cursor.getColumnIndex(HEAT)) > 0);
		}

		cursor.close();
		db.close();
		return vo;
	}

	public CoffeeVo getById(int id) {
		Log.i(TAG, "getById()");
		SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
		Cursor cursor = db.query(TABLE, null, _ID + "=?",
				new String[] { Integer.toString(id) }, null, null, null);
		CoffeeVo vo = null;
		if (cursor.moveToFirst()) {
			vo = new CoffeeVo();
			vo.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
			vo.setCurrentVolume(cursor.getDouble(cursor
					.getColumnIndex(CURRENT_VOLUME)));
			vo.setMinVolume(cursor.getDouble(cursor.getColumnIndex(MIN_VOLUME)));
			vo.setMaxVolume(cursor.getDouble(cursor.getColumnIndex(MAX_VOLUME)));
			vo.setBigCoffeeCount(cursor.getInt(cursor.getColumnIndex(BIG_COUNT)));
			vo.setSmallCoffeeCount(cursor.getInt(cursor
					.getColumnIndex(SMALL_COUNT)));
			vo.setCappuccinoMilkCount(cursor.getInt(cursor
					.getColumnIndex(CAPPUCCINO_COUNT)));
			vo.setCafeLatteMilkCount(cursor.getInt(cursor
					.getColumnIndex(CAFE_LATTE_COUNT)));
			vo.setCount(cursor.getInt(cursor.getColumnIndex(STAT_COUNT)), 0);
			vo.setCount(cursor.getInt(cursor.getColumnIndex(DRINK_COUNT)), 1);
			vo.setCount(cursor.getInt(cursor.getColumnIndex(SETT_COUNT)), 2);
			vo.setCount(cursor.getInt(cursor.getColumnIndex(MAN_COUNT)), 3);
			vo.setHeat(cursor.getInt(cursor.getColumnIndex(HEAT)) > 0);
		}

		cursor.close();
		db.close();
		return vo;
	}

	public long insert(CoffeeVo coffeeVo) {
		Log.i(TAG, "insert()");
		SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
		ContentValues values = new ContentValues();
		if (coffeeVo.getId() > 0) {
			values.put(_ID, coffeeVo.getId());
		}
		values.put(CURRENT_VOLUME, coffeeVo.getCurrentVolume());
		values.put(MIN_VOLUME, coffeeVo.getMinVolume());
		values.put(MAX_VOLUME, coffeeVo.getMaxVolume());
		values.put(BIG_COUNT, coffeeVo.getBigCoffeeCount());
		values.put(SMALL_COUNT, coffeeVo.getSmallCoffeeCount());
		values.put(CAPPUCCINO_COUNT, coffeeVo.getCappuccinoMilkCount());
		values.put(CAFE_LATTE_COUNT, coffeeVo.getCafeLatteMilkCount());
		values.put(STAT_COUNT, coffeeVo.getCount(0));
		values.put(DRINK_COUNT, coffeeVo.getCount(1));
		values.put(SETT_COUNT, coffeeVo.getCount(2));
		values.put(MAN_COUNT, coffeeVo.getCount(3));
		if (coffeeVo.getHeat()) {
			values.put(HEAT, 1);
		} else {
			values.put(HEAT, -1);
		}

		long num = db.insert(TABLE, null, values);
		db.close();
		return num;
	}

	public int update(CoffeeVo coffeeVo) {
		Log.i(TAG, "update()");
		SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(_ID, coffeeVo.getId());
		values.put(CURRENT_VOLUME, coffeeVo.getCurrentVolume());
		values.put(MIN_VOLUME, coffeeVo.getMinVolume());
		values.put(MAX_VOLUME, coffeeVo.getMaxVolume());
		values.put(BIG_COUNT, coffeeVo.getBigCoffeeCount());
		values.put(SMALL_COUNT, coffeeVo.getSmallCoffeeCount());
		values.put(CAPPUCCINO_COUNT, coffeeVo.getCappuccinoMilkCount());
		values.put(CAFE_LATTE_COUNT, coffeeVo.getCafeLatteMilkCount());
		values.put(STAT_COUNT, coffeeVo.getCount(0));
		values.put(DRINK_COUNT, coffeeVo.getCount(1));
		values.put(SETT_COUNT, coffeeVo.getCount(2));
		values.put(MAN_COUNT, coffeeVo.getCount(3));
		if (coffeeVo.getHeat()) {
			values.put(HEAT, 1);
		} else {
			values.put(HEAT, -1);
		}

		int num = db.update(TABLE, values, _ID + "=?",
				new String[] { Integer.toString(coffeeVo.getId()) });
		db.close();
		return num;
	}

	public void delete(int id) {
		SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
		db.delete(TABLE, _ID + "=?", new String[] { Integer.toString(id) });
		db.close();
	}

	public void delete(CoffeeVo coffeeVo) {
		delete(coffeeVo.getId());
	}

	public void deleteAll() {
		SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
		db.delete(TABLE, null, null);
		db.close();
	}
}
