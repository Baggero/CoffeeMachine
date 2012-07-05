package se.coffeemachine.dialogs;

import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.vos.CoffeeVo;
import android.content.Context;

public class CoffeeMessageDialog extends CoffeeDialog {

	public CoffeeMessageDialog(Context context, SwipeController controller) {
		super(context, controller, -1);
	}

	@Override
	public void updateDialog(CoffeeVo model) {
		// TODO
	}

}
