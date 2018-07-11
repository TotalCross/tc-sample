package totalcross.sample.components.ui;

import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.Toast;
import totalcross.ui.dialog.InputBox;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.Event;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.TimerEvent;
import totalcross.ui.image.Image;

public class ImageControlSample extends Container {
	Image img;
	TimerEvent timer;
	boolean grow = true;
	ImageControl ic;
	Label lab;
	boolean stopped;
	double minScale, maxScale;

	@Override
	public void initUI() {
		try {
			super.initUI();
			
			add(lab = new Label("To Mirian", CENTER), LEFT, TOP, FILL, PREFERRED);
			img = new Image("images/lenna.png");
			int nw = parent.getWidth() / 2, nh = parent.getHeight() / 2;
			if (img.getWidth() > nw || img.getHeight() > nh) {
				img = img.smoothScaledFixedAspectRatio(nw < nh ? nw : nh, nh < nw);
			}
			ic = new ImageControl(img);
			ic.centerImage = true;
			add(ic, LEFT, AFTER, FILL, FILL);
			timer = addTimer(25);
			maxScale = Settings.isWindowsCE() ? 2 : 5;
			minScale = Settings.isWindowsCE() ? 1 : 0.5;
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}

	@Override
	public void onEvent(Event e) {
		try {
			if (e.type == TimerEvent.TRIGGERED && timer != null && timer.triggered && !stopped) {
				if (img.hwScaleH > maxScale) {
					grow = false;
				}
				img.hwScaleH = img.hwScaleW = img.hwScaleH * (grow ? 1.05 : 0.95);
				if (!grow && img.hwScaleH <= minScale) {
					grow = true;
				}
				ic.setImage(img); // this actually just computes the center position
			} else if (e.type == PenEvent.PEN_UP && e.target == ic) {
				stopped = true;
				InputBox ib = new InputBox("Change name", "(don't type \"To\")", "");
				ib.popup();
				String s = ib.getValue();
				if (s != null) {
					lab.setText("To " + s);
				}
				stopped = false;
			}
		} catch (Throwable ee) {
			ee.printStackTrace();
			Toast.show("Exception: " + ee.getClass().getName(), 2000);
		}
	}

	@Override
	public void onRemove() {
		removeTimer(timer);
		timer = null;
	}
}
