package se.coffeemachine.adapters;

import se.coffeemachine.controllers.SwipeController;
import android.content.Context;
import android.support.v4.app.FragmentManager;

public class TestTitleFragmentAdapter extends TestFragmentAdapter {
	public TestTitleFragmentAdapter(FragmentManager fm, Context context,
			SwipeController controller) {
		super(fm, context, controller);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return fragment_titles[position % fragment_titles.length];
	}
}
