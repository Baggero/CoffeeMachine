package se.coffeemachine.fragments;

import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.vos.CoffeeVo;
import android.support.v4.app.Fragment;

public abstract class SwipeFragment extends Fragment {
	protected SwipeContext context;
	protected SwipeController controller;

	public SwipeFragment(SwipeContext context, SwipeController controller) {
		this.context = context;
		this.controller = controller;
	}

	public abstract void updateFragment(CoffeeVo model);

}
