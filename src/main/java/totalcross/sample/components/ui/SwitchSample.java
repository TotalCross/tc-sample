package totalcross.sample.components.ui;

import totalcross.ui.Container;
import totalcross.ui.Switch;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class SwitchSample extends Container implements PressListener {
	Switch s1, s2;

	@Override
	public void initUI() {
		try {
			super.initUI();
			s1 = new Switch(true);
			add(s1, CENTER, PARENTSIZE + 30, PARENTSIZE + 50, PREFERRED + fmH);
			s1 = new Switch(true);
			s1.setFont(font.adjustedBy(fmH / 2));
			add(s1, CENTER, AFTER + fmH, PARENTSIZE + 50, PREFERRED + fmH);
			s2 = new Switch(false);
			add(s2, CENTER, AFTER + fmH, PARENTSIZE + 60, PREFERRED + fmH);
			s1.addPressListener(this);
			s2.addPressListener(this);
			s1.textForeOn = "I";
			s1.textForeOff = "O";
			s2.textBackOn = "On";
			s2.textBackOff = "Off";
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}

	}

	@Override
	public void controlPressed(ControlEvent e) {
		e.consumed = true;
		if (e.target == s1) {
			s2.setOn(s1.isOn());
		} else {
			s1.setOn(s2.isOn());
		}
	}

}