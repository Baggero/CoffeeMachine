package se.coffeemachine.fragments;

import se.coffeemachine.vos.CoffeeVo;
import android.support.v4.app.Fragment;

public abstract class SwipeFragment extends Fragment {
	protected SwipeContext context;

	public SwipeFragment(SwipeContext context) {
		this.context = context;
	}

	public abstract void updateFragment(CoffeeVo model);
}
