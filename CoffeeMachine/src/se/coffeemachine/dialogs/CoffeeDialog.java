package se.coffeemachine.dialogs;

import se.coffeemachine.controllers.SwipeController;
import se.coffeemachine.vos.CoffeeVo;
import android.app.Dialog;
import android.content.Context;

public abstract class CoffeeDialog extends Dialog {

	protected SwipeController controller;

	public CoffeeDialog(Context context, SwipeController controller, int theme) {
		super(context, theme);
		this.controller = controller;
	}

	public abstract void updateDialog(CoffeeVo model);

}
