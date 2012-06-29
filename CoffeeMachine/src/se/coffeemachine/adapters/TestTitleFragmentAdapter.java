package se.coffeemachine.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public class TestTitleFragmentAdapter extends TestFragmentAdapter {
	public TestTitleFragmentAdapter(FragmentManager fm, Context context) {
		super(fm, context);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return fragment_titles[position % fragment_titles.length];
	}
}
