package se.coffeemachine.daos;

import se.coffeemachine.CoffeeInterfaceApp;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public final class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG = DatabaseHelper.class.getSimpleName();
	private static final String DATABASE_NAME = "CoffeeMachine";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper() {
		super(CoffeeInterfaceApp.getContext(), DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		final String coffee = "CREATE TABLE " + CoffeeDao.TABLE + "("
				+ CoffeeDao._ID + " integer primary key, "
				+ CoffeeDao.CURRENT_VOLUME + " double, " + CoffeeDao.MIN_VOLUME
				+ " double, " + CoffeeDao.MAX_VOLUME + " double, "
				+ CoffeeDao.BIG_COUNT + " int, " + CoffeeDao.SMALL_COUNT
				+ " int, " + CoffeeDao.CAPPUCCINO_COUNT + " int, "
				+ CoffeeDao.CAFE_LATTE_COUNT + " int, " + CoffeeDao.STAT_COUNT
				+ " int, " + CoffeeDao.DRINK_COUNT + " int, "
				+ CoffeeDao.SETT_COUNT + " int, " + CoffeeDao.MAN_COUNT
				+ " int, " + CoffeeDao.HEAT + " int" + ")";
		Log.i(TAG, coffee);
		database.execSQL(coffee);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
