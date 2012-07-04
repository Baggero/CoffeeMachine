package se.coffeemachine.activities;

import se.coffeemachine.R;
import se.coffeemachine.adapters.TestFragmentAdapter;
import se.coffeemachine.adapters.TestTitleFragmentAdapter;
import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.fragments.SwipeContext;
import se.coffeemachine.fragments.SwipeFragment;
import se.coffeemachine.vos.CoffeeVo;
import se.coffeemachine.vos.OnChangeListener;
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

	private SwipeController controller;
	private CoffeeVo counter;

	private TestFragmentAdapter mAdapter;
	private ViewPager pager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		counter = new CoffeeVo();
		counter.addListener(this);
		controller = new SwipeController(counter);

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
		return controller.handleMessage(what);
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
					SwipeController.MESSAGE_RESET_MODEL, controller.getModel()
							.getId());
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
				updateView(pager.getCurrentItem());
			}
		});
	}

	public void updateView(int position) {
		SwipeFragment fragment = (SwipeFragment) mAdapter.getItem(position);
		fragment.updateFragment(counter);
	}

	@Override
	public void onPageSelected(final int position) {
		controller
				.handleMessage(SwipeController.MESSAGE_UPDATE_STATE, position);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateView(position);
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
