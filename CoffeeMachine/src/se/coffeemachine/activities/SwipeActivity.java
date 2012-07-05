package se.coffeemachine.activities;

import se.coffeemachine.R;
import se.coffeemachine.adapters.TestFragmentAdapter;
import se.coffeemachine.adapters.TestTitleFragmentAdapter;
import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.dialogs.CoffeeDialog;
import se.coffeemachine.dialogs.CoffeeDialogs;
import se.coffeemachine.fragments.SwipeContext;
import se.coffeemachine.fragments.SwipeFragment;
import se.coffeemachine.utils.CoffeeStateUtils;
import se.coffeemachine.utils.DialogUtils;
import se.coffeemachine.vos.CoffeeVo;
import se.coffeemachine.vos.OnChangeListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.viewpagerindicator.TitlePageIndicator;

public class SwipeActivity extends FragmentActivity implements SwipeContext,
		OnChangeListener<CoffeeVo>, ViewPager.OnPageChangeListener {

	private static final String TAG = SwipeActivity.class.getSimpleName();

	public final static int STATISTICS_STATE = 0;
	public final static int DRINK_STATE = 1;
	public final static int SETTINGS_STATE = 2;
	public final static int MANUALS_STATE = 3;

	private static Context context;

	private SwipeController controller;
	private CoffeeVo coffeevo;

	private TestFragmentAdapter mAdapter;
	private ViewPager pager;

	private CoffeeDialog mDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = this;

		mDialog = null;
		coffeevo = new CoffeeVo();
		coffeevo.addListener(this);
		controller = new SwipeController(coffeevo);

		controller.handleMessage(SwipeController.MESSAGE_GET_COFFEE_MODEL);

		mAdapter = new TestTitleFragmentAdapter(getSupportFragmentManager(),
				this, controller);

		pager = (ViewPager) findViewById(R.id.viewpager);
		TitlePageIndicator title_indicator = (TitlePageIndicator) findViewById(R.id.title_indicator);
		pager.setAdapter(mAdapter);
		title_indicator.setViewPager(pager);
		pager.setOffscreenPageLimit(1);
		pager.setCurrentItem(1);
		title_indicator.setOnPageChangeListener(this);
	}

	@Override
	public boolean handleMessage(int what) {
		switch (what) {
		case CoffeeDialogs.MAKE_COFFEE_DIALOG:
			showNewDialog(CoffeeDialogs.MAKE_COFFEE_DIALOG);
			return true;
		default:
			return controller.handleMessage(what);
		}
	}

	@Override
	public boolean handleMessage(int what, Object data) {
		return controller.handleMessage(what, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.reset:
			return controller.handleMessage(
					SwipeController.MESSAGE_RESET_MODEL, coffeevo.getId());
		case R.id.save:
			return controller.handleMessage(SwipeController.MESSAGE_SAVE_MODEL);
		case R.id.about:
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://www.therealjoshua.com/blog/"));
			startActivity(intent);
			return true;
		default:
			return false;

		}
	}

	@Override
	public void onChange(CoffeeVo model) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				int state = coffeevo.getCurrentState();
				if (state != coffeevo.getPreviousState()) {
					switch (state) {
					case CoffeeStateUtils.STATE_1:
						// Display the appropriate dialog for STATE_1
						showNewDialog(CoffeeDialogs.STATE_1_DIALOG);
						break;
					case CoffeeStateUtils.STATE_2:
						// Display the appropriate dialog for STATE_2
						showNewDialog(CoffeeDialogs.STATE_2_DIALOG);
						break;
					// Add case for all new states that should show a dialog.
					default:
						// The new state does not require a dialog and the
						// child-view itself gets to decide how to display its
						// components.
						updateView();
						break;
					}
				} else {
					// If the state is not changed the same view as before
					// should
					// be changed and displayed (This can be a dialog or a view)
					updateView();
				}

			}
		});
	}

	private void showNewDialog(int dialog) {
		switch (dialog) {
		case CoffeeDialogs.MAKE_COFFEE_DIALOG:
			mDialog = CoffeeDialogs.getMakeCoffeeDialog(context, controller,
					context.getResources().getString(R.string.big_coffee),
					"message", R.string.big_coffee,
					coffeevo.getCurrentVolume(), coffeevo.getMaxVolume());
			DialogUtils.setDialogParams(mDialog);
			mDialog.show();
			mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					mDialog = null;

				}
			});
			break;
		}

	}

	public void updateView() {
		if (mDialog == null) {
			SwipeFragment fragment = (SwipeFragment) mAdapter.getItem(pager
					.getCurrentItem());
			fragment.updateFragment(coffeevo);
		} else {
			mDialog.updateDialog(coffeevo);
		}
	}

	@Override
	public void onPageSelected(final int position) {
		controller
				.handleMessage(SwipeController.MESSAGE_UPDATE_STATE, position);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateView();
			}
		});
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}
}
