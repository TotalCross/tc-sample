package totalcross.sample.components.ui;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class SpinnerSample extends BaseScreen {
	private int gap = (int) (Settings.screenDensity * 20);
	private Container menu;
	private Spinner sp;
	private Button bt;
	private Label status;
	private RadioGroupController rg;
	private Image triplex;

	public SpinnerSample () {
		super("https://totalcross.gitbook.io/playbook/components/spinner");
	}

	@Override
	public void onContent(ScrollContainer content) {
		try {
			content.add(menu = new Container(), LEFT, TOP , FILL, WILL_RESIZE);
			menu.setBackForeColors(Colors.PRIMARY, Colors.ON_PRIMARY);

			rg = new RadioGroupController();

			status = new Label("Downloading...\n(Note that everything is blocked until the end of the process)",
					CENTER);
			status.autoSplit = true;
			status.setVisible(false);

			Radio r;
			menu.setInsets(0, 0, gap/3, gap/3);
			menu.add(r = new Radio(" Android", rg), CENTER, TOP);
			menu.add(r = new Radio(" iPhone", rg), LEFT + gap, SAME);
			r.leftJustify = true;
			r.leftJustify = true;
			menu.add(r = new Radio(" Sync", rg), RIGHT - gap, SAME);
			r.leftJustify = true;
			menu.add(r = new Radio(" Custom (triplex.gif)", rg), LEFT + gap, AFTER + gap);
			r.leftJustify = true;
			rg.setSelectedIndex(0);

			menu.resizeHeight();

			triplex = new Image("images/triplex.gif");
			sp = new Spinner(Spinner.IPHONE);
			content.add(sp, CENTER, CENTER, FONTSIZE + 200, FONTSIZE + 200);
			content.add(status, LEFT + gap, AFTER + gap, FILL - gap, PREFERRED);

			content.add(bt = new Button("Start"), CENTER, BOTTOM - gap, SCREENSIZE + 50, PREFERRED + (int)(Settings.screenDensity * 80));
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
					sp.start();
					new Thread() {
						@Override
						public void run() {
							while (Vm.getTimeStamp() < end) {
								try {
									Thread.sleep(200);
								} catch (InterruptedException ex) {
									ex.printStackTrace();
								}
							}
							MainWindow.getMainWindow().runOnMainThread(() -> onRemove());
						}
					}.start();
				}
			}
		}
	}

	@Override
	public void onRemove() // stop spinners at end
	{
		status.setVisible(false);
		bt.setVisible(true);
		sp.stop();
	}
}
