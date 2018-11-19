package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.Radio;
import totalcross.ui.RadioGroupController;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;
import totalcross.ui.Spinner;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class SpinnerSample extends ScrollContainer {
	private int gap = 50;
	private Container menu;
	private Spinner sp;
	private Button bt;
	private Label status;
	private RadioGroupController rg;
	private Image triplex;

	@Override
	public void initUI() {
		try {
			super.initUI();

			add(menu = new Container(), LEFT, TOP , FILL, WILL_RESIZE);
			menu.setBackForeColors(Colors.PRIMARY, Colors.ON_PRIMARY);

			rg = new RadioGroupController();

			status = new Label("Downloading...\n(Note that everything is blocked until the end of the process)",
					CENTER);
			status.autoSplit = true;
			status.setVisible(false);

			Radio r;
			menu.add(new Spacer(), CENTER, TOP, 10, gap / 3);
			menu.add(r = new Radio(" iPhone", rg), LEFT + gap, AFTER, PARENTSIZE + 32, PREFERRED);
			r.leftJustify = true;
			menu.add(r = new Radio(" Android", rg), AFTER, SAME, SAME, PREFERRED);
			r.leftJustify = true;
			menu.add(r = new Radio(" Sync", rg), AFTER, SAME, SAME, PREFERRED);
			r.leftJustify = true;
			menu.add(r = new Radio(" Custom (triplex.gif)", rg), CENTER, AFTER, PREFERRED, PREFERRED);
			menu.add(new Spacer(), CENTER, AFTER, 10, gap / 3);
			r.leftJustify = true;
			rg.setSelectedIndex(0);

			menu.resizeHeight();

			triplex = new Image("images/triplex.gif");
			sp = new Spinner(Spinner.IPHONE);
			add(sp, CENTER, CENTER, FONTSIZE + 200, FONTSIZE + 200);
			add(status, LEFT + gap, AFTER + gap, FILL - gap, PREFERRED);

			sp.addTimerListener((e) -> {
				sp.stop();
				sp.removeTimer(e);
			});

			add(bt = new Button("Start"), CENTER, BOTTOM - gap, SCREENSIZE + 50, PREFERRED + fmH * 6);
			bt.setBackForeColors(Colors.P_600, Colors.ON_P_600);

		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}

	@Override
	public void onEvent(Event e) {
		if (e.type == ControlEvent.PRESSED) {
			if (e.target instanceof Radio) {
				int t = rg.getSelectedIndex();
				switch (t) {
				case 0:
				case 1:
				case 2:
					sp.setType(t + 1);
					break;
				case 3:
					sp.setImage(triplex);
					break;
				}
			} else if (e.target == bt) {
				if (!sp.isRunning()) {
					// sp.setForeColor(Color.getRandomColor(rr));
					bt.setVisible(false);
					status.setVisible(true);

					int end = Vm.getTimeStamp() + 5000;
					while (Vm.getTimeStamp() < end) {
						// Ygor: I deprecated the update() method because it is synchronous and
						// blocking.
						// The start() and stop() methods should be used instead.
						sp.update();
						repaintNow();
					}
					onRemove();
				}
			}
		}
	}

	@Override
	public void onRemove() // stop spinners at end
	{
		status.setVisible(false);
		bt.setVisible(true);
	}
}
