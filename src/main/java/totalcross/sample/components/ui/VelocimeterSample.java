package totalcross.sample.components.ui;

import totalcross.ui.Container;
import totalcross.ui.chart.Velocimeter;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.Event;
import totalcross.ui.event.TimerEvent;
import totalcross.ui.gfx.Color;

public class VelocimeterSample extends Container {
	Velocimeter vel;
	TimerEvent tt;

	@Override
	public void initUI() {
		try {
			super.initUI();
			tt = addTimer(50);
			vel = new Velocimeter();
			vel.value = -20;
			vel.max = 40;
			vel.pointerColor = Color.GREEN;
			add(vel, CENTER, CENTER, PARENTSIZE + 50, PARENTSIZE + 50);
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}
	}

	@Override
	public void onEvent(Event e) {
		if (e.type == TimerEvent.TRIGGERED && tt.triggered) {
			vel.value++;
			if (vel.value > vel.max + 20) {
				vel.value = vel.min - 20;
			}
			repaint();
		}
	}
}
