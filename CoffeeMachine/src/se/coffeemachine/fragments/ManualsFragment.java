package se.coffeemachine.fragments;

import se.coffeemachine.R;
import se.coffeemachine.controllers.SwipeController;
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

public class ManualsFragment extends SwipeFragment implements Handler.Callback {
	public ManualsFragment(SwipeContext context, SwipeController controller) {
		super(context, controller);
		Log.i(TAG, "Trying to create");
		// TODO Auto-generated constructor stub
	}

	public static final String TAG = ManualsFragment.class.getSimpleName();

	ImageView mImage;
	TextView mText1;
	TextView mText2;
	TextView mText3;
	TextView mText4;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "ManualsFragment created");
		View view = inflater.inflate(R.layout.manuals, container, false);
		mImage = (ImageView) view.findViewById(R.id.imageView1);
		mText1 = (TextView) view.findViewById(R.id.textView1);
		mText2 = (TextView) view.findViewById(R.id.textView2);
		mText3 = (TextView) view.findViewById(R.id.textView3);
		mText4 = (TextView) view.findViewById(R.id.textView4);

		mImage.setClickable(true);
		mImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "ManualsFragment image clicked");
				context.handleMessage(SwipeController.MESSAGE_INCREMENT_COUNT,
						3);
			}
		});
		fetchData();
		Log.i(TAG, "Created");
		return view;
	}

	private void fetchData() {
		Log.i(TAG, "fetchData");
		context.handleMessage(SwipeController.MESSAGE_MANUALS_SET_UP);

	}

	@Override
	public void updateFragment(CoffeeVo model) {
		mText1.setText("Statistics: "
				+ ((Integer) model.getCount(0)).toString());
		mText2.setText("Drinks: " + ((Integer) model.getCount(1)).toString());
		mText3.setText("Settings: " + ((Integer) model.getCount(2)).toString());
		mText4.setText("Manuals: " + ((Integer) model.getCount(3)).toString());

	}

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		return false;
	}

}
