package se.coffeemachine.dialogs;

import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.vos.CoffeeVo;
import android.content.Context;

public class CoffeeInteractionDialog extends CoffeeDialog {

	public CoffeeInteractionDialog(Context context, SwipeController controller,
			String title, String message, int theme) {
		super(context, controller, theme);
	}

	@Override
	public void updateDialog(CoffeeVo model) {
		// TODO
	}

}
