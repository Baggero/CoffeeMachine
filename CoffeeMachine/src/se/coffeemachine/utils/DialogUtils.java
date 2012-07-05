package se.coffeemachine.utils;

import android.app.Dialog;
import android.view.Gravity;
import android.view.WindowManager.LayoutParams;

public class DialogUtils {

	public static void setDialogParams(Dialog dialog) {
		LayoutParams lp = dialog.getWindow().getAttributes();
		lp.x = 100;
		lp.y = 50;
		lp.width = 600;
		lp.height = 350;
		lp.gravity = Gravity.TOP | Gravity.LEFT;
		lp.dimAmount = (float) 0.5;
		lp.flags = LayoutParams.FLAG_LAYOUT_NO_LIMITS
				| LayoutParams.FLAG_DIM_BEHIND;
	}

}
