package se.coffeemachine.fragments;

import se.coffeemachine.R;
import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.dialogs.CoffeeDialogs;
import se.coffeemachine.vos.CoffeeVo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public final class DrinkFragment extends SwipeFragment implements
		Handler.Callback {
	public DrinkFragment(SwipeContext context, SwipeController controller) {
		super(context, controller);
		Log.i(TAG, "Trying to create");
	}

	public static final String TAG = DrinkFragment.class.getSimpleName();

	ImageView mBigCoffeeImage;
	ImageView mSmallCoffeeImage;
	ImageView mCappuccinoMilkImage;
	ImageView mCafeLatteMilkImage;
	ToggleButton mChangeHeatToggleButton;
	TextView mText1;
	TextView mText2;
	TextView mText3;
	TextView mText4;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.drinks, container, false);
		initComponents(view);
		fetchData();
		Log.i(TAG, "Created");
		return view;
	}

	/**
	 * Initializes components in the specified view.
	 * 
	 * @param view
	 */
	private void initComponents(View view) {
		mBigCoffeeImage = (ImageView) view.findViewById(R.id.big_coffee_btn);
		mSmallCoffeeImage = (ImageView) view
				.findViewById(R.id.small_coffee_btn);
		mCappuccinoMilkImage = (ImageView) view
				.findViewById(R.id.cappuccino_milk_btn);
		mCafeLatteMilkImage = (ImageView) view
				.findViewById(R.id.cafe_latte_btn);
		mChangeHeatToggleButton = (ToggleButton) view
				.findViewById(R.id.heat_choose_btn);
		mText1 = (TextView) view.findViewById(R.id.textView1);
		mText2 = (TextView) view.findViewById(R.id.textView2);
		mText3 = (TextView) view.findViewById(R.id.textView3);
		mText4 = (TextView) view.findViewById(R.id.textView4);

		mBigCoffeeImage.setClickable(true);
		mBigCoffeeImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Later change to another handleMessage. Won«t be a count
				Log.i(TAG, "Big Coffee Button Clicked");
				// context.handleMessage(SwipeController.MESSAGE_MAKE_BIG_COFFEE,
				// 1);

				context.handleMessage(CoffeeDialogs.MAKE_COFFEE_DIALOG);

			}
		});

		mSmallCoffeeImage.setClickable(true);
		mSmallCoffeeImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Later change to another handleMessage. Won«t be a count
				Log.i(TAG, "Small Coffe Button Clicked");
				context.handleMessage(
						SwipeController.MESSAGE_MAKE_SMALL_COFFEE, 1);
			}
		});
		mCappuccinoMilkImage.setClickable(true);
		mCappuccinoMilkImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Later change to another handleMessage. Won«t be a count
				Log.i(TAG, "Cappuccino Milk Button Clicked");
				context.handleMessage(
						SwipeController.MESSAGE_MAKE_CAPPUCCINO_MILK, 1);
			}
		});

		mCafeLatteMilkImage.setClickable(true);
		mCafeLatteMilkImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Cafe Latte Milk Button Clicked");
				context.handleMessage(
						SwipeController.MESSAGE_MAKE_CAFE_LATTE_MILK, 1);
			}
		});

		mChangeHeatToggleButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Change Heat Button Clicked");
				if (mChangeHeatToggleButton.isChecked()) {
					context.handleMessage(SwipeController.MESSAGE_SET_WARM_FROTHED);
				} else {
					context.handleMessage(SwipeController.MESSAGE_SET_COLD_FROTHED);
				}
			}
		});
	}

	private void fetchData() {
		Log.i(TAG, "fetchData");
		context.handleMessage(SwipeController.MESSAGE_DRINKS_SET_UP);

	}

	@Override
	public void updateFragment(CoffeeVo model) {
		mText1.setText("Statistics: "
				+ ((Integer) model.getCount(0)).toString());
		mText2.setText("Drinks: " + ((Integer) model.getCount(1)).toString());
		mText3.setText("Settings: " + ((Integer) model.getCount(2)).toString());
		mText4.setText("Manuals: " + ((Integer) model.getCount(3)).toString());
		// TODO

	}

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		return false;
	}
}