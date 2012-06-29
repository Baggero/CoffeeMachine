package se.coffeemachine.adapters;

import se.coffeemachine.R;
import se.coffeemachine.fragments.DrinkFragment;
import se.coffeemachine.fragments.ManualsFragment;
import se.coffeemachine.fragments.SettingsFragment;
import se.coffeemachine.fragments.StatisticFragment;
import se.coffeemachine.fragments.SwipeContext;
import se.coffeemachine.fragments.SwipeFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TestFragmentAdapter extends FragmentPagerAdapter {
	protected final String[] fragment_titles;
	protected final SwipeFragment[] fragments;

	private int mCount;

	public TestFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
		fragment_titles = context.getResources().getStringArray(
				R.array.fragment_titles);
		fragments = new SwipeFragment[] {
				new StatisticFragment((SwipeContext) context),
				new DrinkFragment((SwipeContext) context),
				new SettingsFragment((SwipeContext) context),
				new ManualsFragment((SwipeContext) context) };
		mCount = fragment_titles.length;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments[position];
	}

	@Override
	public int getCount() {
		return mCount;
	}

	public void setCount(int count) {
		if (count > 0 && count <= 10) {
			mCount = count;
			notifyDataSetChanged();
		}
	}
}
