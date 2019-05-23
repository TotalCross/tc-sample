package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.chart.Velocimeter;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.Event;
import totalcross.ui.event.TimerEvent;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class VelocimeterSample extends Container {
	private Velocimeter vel;
	private TimerEvent tt;
	private int gap = UnitsConverter.toPixels(DP + 20);
	
	@Override
	public void initUI() {
		try {
			setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
			Container adv = new Container();
			Label header = new Label("This is a velocimeter sample, it just shows how the Velocimeter class works on TotalCross.", CENTER);
			header.autoSplit = true;
			header.setForeColor(Colors.ON_SURFACE);

			tt = addTimer(50);
			vel = new Velocimeter();
			vel.value = -20;
			vel.max = 40;
			vel.pointerColor = Color.GREEN;
			add(vel, CENTER, CENTER, PARENTSIZE + 50, PARENTSIZE + 50);
			add(header, CENTER, TOP + gap, SCREENSIZE, PREFERRED);
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
