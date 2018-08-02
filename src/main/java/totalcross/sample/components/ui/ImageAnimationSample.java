package totalcross.sample.components.ui;

import totalcross.game.Animation;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class ImageAnimationSample extends Container {
	final int gap = 5;
	private Button btnStartStop;
	private Animation anim;
	private ComboBox cbEffect;
	private int effect;

	@Override
	public void initUI() {
		super.initUI();
		add(btnStartStop = new Button(" Start/Stop "), CENTER, TOP + Settings.screenHeight/10, SCREENSIZE+50, SCREENSIZE+10);
		btnStartStop.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		
		add(new Label("Effect: ", LEFT, Colors.ON_BACKGROUND, false), LEFT + Settings.screenWidth/7, BOTTOM - Settings.screenHeight/10);
		
		String[] items = { "normal", "scaledBy", "smoothScaledBy", "getRotatedScaledInstance", "getTouchedUpInstance",
				"changeColors", "fadedInstance", "applyColor2/dither" };
		ComboBox.usePopupMenu = false;
		add(cbEffect = new ComboBox(items), AFTER, BOTTOM - Settings.screenHeight/10, FILL - Settings.screenHeight/10, PREFERRED);
		cbEffect.setSelectedIndex(0);
		cbEffect.setBackForeColors(Colors.P_800, Colors.ON_P_800);
		cbEffect.fillColor = Colors.P_600;
		next(false);
	}

	@Override
	public void onAddAgain() {
		next(false);
	}

	@Override
	public void onEvent(Event event) {
		switch (event.type) {
		case ControlEvent.PRESSED: {
			if (event.target == cbEffect && effect != cbEffect.getSelectedIndex()) {
				next(true);
			} else if (event.target == btnStartStop) {
				if (anim.isPaused) {
					anim.resume();
				} else {
					anim.pause();
				}
			}
			break;
		}
		}
	}

	/**
	 * shows next frame
	 */
	private void next(boolean changeEffect) {
		try {
			onRemove();
			Image img = new Image("images/alligator.gif");
			effect = cbEffect.getSelectedIndex();
			double scale = Settings.isIOS() ? 1.5 : 2; // ios has less opengl memory
			switch (effect) {
			case 1:
				img = img.scaledBy(scale, scale);
				break;
			case 2:
				img = img.smoothScaledBy(scale, scale);
				break;
			case 3:
				img = img.getRotatedScaledInstance(50, 90, -1);
				break;
			case 4:
				img = img.getTouchedUpInstance((byte) 50, (byte) 100);
				break;
			case 5:
				img.changeColors(0xFF31CE31, 0xFFFF00FF);
				break;
			case 6:
				img = img.getFadedInstance();
				break;
			case 7:
				img.applyColor2(Color.RED);
				img.getGraphics().dither(0, 0, img.getWidth(), img.getHeight());
				break;
			}
			if (Settings.isOpenGL) {
				img.applyChanges();
			}
			anim = new Animation(img, 120);
			anim.pauseIfNotVisible = true;
			add(anim, CENTER, CENTER, PREFERRED, PREFERRED);
			anim.start(Animation.LOOPS_UNLIMITED);
		} catch (Throwable e) {
			MessageBox.showException(e, true);
		}
	}

	@Override
	public void onRemove() {
		if (anim != null) {
			anim.stop();
			remove(anim);
			anim = null;
		}
	}
}
