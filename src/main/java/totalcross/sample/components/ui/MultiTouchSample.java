package totalcross.sample.components.ui;

import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.MultiTouchEvent;
import totalcross.ui.event.MultiTouchListener;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class MultiTouchSample extends Container {
	final int gap = (int) (Settings.screenDensity * 20);
	ImageControl ic;
	public static Image screenShot;
	private static Image img;

	@Override
	public void initUI() {
		if (!Settings.isOpenGL && !Settings.onJavaSE) {
			add(new Label("This sample works only on iOS, Android and Windows Phone."), CENTER, CENTER);
		} else {
			try {
				if (img == null) {
					img = new Image("images/lenna.png");
				}
				ic = new ImageControl(screenShot != null ? screenShot : img);
				screenShot = null;
				ic.allowBeyondLimits = false;
				ic.setEventsEnabled(true);
				
				add(ic, LEFT + gap, TOP + gap, this);
				ic.addMultiTouchListener(new MultiTouchListener() {
					@Override
					public void scale(MultiTouchEvent e) {
						e.consumed = true;
						System.out.println("multitouch");
						updateStatus();
					}
				});
			} catch (Exception e) {
				MessageBox.showException(e, false);
			}
		}
	}

	private void updateStatus() {
		try {
			ic.setImage(img.scaledBy(1.2, 1.2));
		} catch (ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(ic, LEFT + gap, TOP + gap, this);
	}
}
