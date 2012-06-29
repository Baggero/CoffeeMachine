package se.coffeemachine;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class CoffeeInterfaceApp extends Application {

	private static final String TAG = CoffeeInterfaceApp.class.getSimpleName();
	private static CoffeeInterfaceApp instance;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "TapCounterApp.onCreate was called");
		instance = this;
	}

	public static Context getContext() {
		return instance.getApplicationContext();
	}

}
