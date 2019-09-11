package totalcross.sample.components.ui;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.MultiTouchEvent;
import totalcross.ui.event.MultiTouchListener;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.image.Image;
import totalcross.util.pdf.Base;

public class MultiTouchSample extends BaseScreen {
	final int gap = (int) (Settings.screenDensity * 20);
	private ImageControl ic;
	public static Image screenShot;
	private Image imgLenna, imgBlack;
	private Label status;
	private boolean hasTimer;
	
	@Override
	public void onContent(ScrollContainer content) {

		content.setFont(getFont().adjustedBy(2));
		content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		
		if (!Settings.isOpenGL && !Settings.onJavaSE) {
			Label error = new Label("This sample works only on iOS, Android and Windows Phone.", CENTER);
			error.autoSplit = true;
			content.add(error, LEFT + gap, CENTER, FILL - gap, PREFERRED);
		} else {
			try {
				imgLenna = new Image("images/lenna_full.png").getHwScaledInstance(Settings.screenWidth*2/3, Settings.screenWidth*2/3);
				imgBlack = new Image("images/black_square.png").getHwScaledInstance(Settings.screenWidth*2/3, Settings.screenWidth*2/3);

				ic = new ImageControl(imgBlack);
				screenShot = null;
				ic.allowBeyondLimits = false;
				ic.centerImage = true;
				ic.setEventsEnabled(true);
				
				Label message = new Label("Use a pinch movement to reveal the image", CENTER);
				message.autoSplit = true;
				content.add(message, LEFT + gap, TOP + gap, FILL - gap, PREFERRED);

				content.add(status = new Label("Waiting for a MultiTouch event...", CENTER),
						LEFT + gap, BOTTOM - gap, FILL - gap, PREFERRED);
				status.autoSplit = true;
				
				status.addTimerListener((e) -> {
					status.setForeColor(Colors.ON_BACKGROUND);
					status.setText("Waiting for a MultiTouch event...");
					ic.setImage(imgBlack);
					status.removeTimer(e);
					hasTimer = false;
				});
				hasTimer = false;

				content.add(ic, LEFT, AFTER + gap, FILL, FIT, message);
				
				ic.addMultiTouchListener(new MultiTouchListener() {
					@Override
					public void scale(MultiTouchEvent e) {
						e.consumed = true;
						ic.setImage(imgLenna);
						updateStatus();
					}
				});

				ic.addPenListener(new PenListener() {

					@Override
					public void penUp(PenEvent e) {

					}

					@Override
					public void penDragStart(DragEvent e) {

					}

					@Override
					public void penDragEnd(DragEvent e) {

					}

					@Override
					public void penDrag(DragEvent e) {
						e.consumed = true;
					}

					@Override
					public void penDown(PenEvent e) {

					}
				});
			} catch (Exception e) {
				MessageBox.showException(e, false);
			}
		}
	}

	private void updateStatus() {
		status.setText("You used a MultiTouch event!");
		status.setForeColor(Colors.SUCESS_GREEN);
		if(!hasTimer) {
			status.addTimer(1000);
			hasTimer = true;
		}
	}
	
	@Override
	public void reposition() {
		super.reposition();
		ic.setImage(imgBlack);
		repaintNow();
	}
}
