package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.anim.ControlAnimation.AnimationFinished;
import totalcross.ui.anim.ControlAnimation;
import totalcross.ui.anim.FadeAnimation;
import totalcross.ui.anim.PathAnimation;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;

public class ControlAnimationSample extends Container {
	private Button btnL, btnR, btnT, btnB, btnSwp, btnRot, btnSH;
	private int gap = 50;
	private Container c;
	private AnimationFinished animFinished;
	private boolean animHasEnded;

	@Override
	public void initUI() {
		super.initUI();
		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		animHasEnded = true;
		c = new Container();
		c.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);
		
		Button.commonGap = fmH / 2;
		add(btnSwp = new Button("SWAP"), LEFT + gap, TOP + fmH / 4);
		btnSwp.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		btnSwp.appId = 5;
		add(btnSH = new Button("SHOW/HIDE"), CENTER, TOP + fmH / 4);
		btnSH.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		btnSH.appId = 6;
		add(btnRot = new Button("ROTATE"), RIGHT - gap, TOP + fmH / 4);
		btnRot.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		btnRot.appId = 7;
		add(c, LEFT + gap, AFTER + gap, FILL - gap, FILL - gap);
		c.add(btnB = new Button("Bottom"), CENTER, BOTTOM);
		btnB.setBackForeColors(Colors.S_400, Colors.ON_S_400);
		btnB.appId = 4;
		c.add(btnR = new Button("Right"), RIGHT, CENTER, SAME, SAME);
		btnR.setBackForeColors(Colors.S_400, Colors.ON_S_400);
		btnR.appId = 2;
		c.add(btnL = new Button("Left"), LEFT, CENTER, SAME, SAME);
		btnL.setBackForeColors(Colors.S_400, Colors.ON_S_400);
		btnL.appId = 1;
		c.add(btnT = new Button("Top"), CENTER, TOP, SAME, SAME);
		btnT.setBackForeColors(Colors.S_400, Colors.ON_S_400);
		btnT.appId = 3;
		Button.commonGap = 0;

		animFinished = new AnimationFinished() {
			@Override
			public void onAnimationFinished(ControlAnimation anim) {
				animHasEnded = true;
			}
		};
	}

	@Override
	public void onEvent(Event e) {
		if (e.type == ControlEvent.PRESSED) {
			Button btnAux = null;
			switch (((Control) e.target).appId) {
			case 1:
				if (btnL.appId == 1)
					btnAux = btnL;
				if (btnR.appId == 1)
					btnAux = btnR;
				if (btnT.appId == 1)
					btnAux = btnT;
				if (btnB.appId == 1)
					btnAux = btnB;

				if (animHasEnded)
					PathAnimation.create(btnAux, -LEFT, null, -1).then(PathAnimation.create(btnAux, LEFT, animFinished, -1))
							.start();
				break;
			case 2:
				if (btnL.appId == 2)
					btnAux = btnL;
				if (btnR.appId == 2)
					btnAux = btnR;
				if (btnT.appId == 2)
					btnAux = btnT;
				if (btnB.appId == 2)
					btnAux = btnB;
				if (animHasEnded)
					PathAnimation.create(btnAux, -RIGHT, null, -1)
							.then(PathAnimation.create(btnAux, RIGHT, animFinished, -1)).start();
				break;
			case 3:
				if (btnL.appId == 3)
					btnAux = btnL;
				if (btnR.appId == 3)
					btnAux = btnR;
				if (btnT.appId == 3)
					btnAux = btnT;
				if (btnB.appId == 3)
					btnAux = btnB;
				if (animHasEnded)
					PathAnimation.create(btnAux, -TOP, null, -1).then(PathAnimation.create(btnAux, TOP, animFinished, -1))
							.start();
				break;
			case 4:
				if (btnL.appId == 4)
					btnAux = btnL;
				if (btnR.appId == 4)
					btnAux = btnR;
				if (btnT.appId == 4)
					btnAux = btnT;
				if (btnB.appId == 4)
					btnAux = btnB;
				if (animHasEnded)
					PathAnimation.create(btnAux, -BOTTOM, null, -1)
							.then(PathAnimation.create(btnAux, BOTTOM, animFinished, -1)).start();
				break;
			case 5: {
				int xr = btnR.getX(), yr = btnR.getY(), xl = btnL.getX(), yl = btnL.getY();
				if (animHasEnded) {
					PathAnimation.create(btnR, xr, yr, xl, yl, null, -1)
							.then(PathAnimation.create(btnR, xl, yl, xr, yr, animFinished, -1)).start();
					PathAnimation.create(btnL, xl, yl, xr, yr, null, -1)
							.then(PathAnimation.create(btnL, xr, yr, xl, yl, animFinished, -1)).start();
				}
				break;
			}
			case 6:
				if (animHasEnded) {
					(FadeAnimation.create(btnT, false, null, -1).then(FadeAnimation.create(c, true, null, -1))).start();
					(FadeAnimation.create(btnR, false, null, -1).then(FadeAnimation.create(c, true, null, -1))).start();
					(FadeAnimation.create(btnB, false, null, -1).then(FadeAnimation.create(c, true, null, -1))).start();
					(FadeAnimation.create(btnL, false, null, -1).then(FadeAnimation.create(c, true, animFinished, -1))).start();
				}
				break;
			case 7: {
				int xr = btnR.getX(), yr = btnR.getY(), xl = btnL.getX(), yl = btnL.getY();
				int xt = btnT.getX(), yt = btnT.getY(), xb = btnB.getX(), yb = btnB.getY();
				PathAnimation p1 = PathAnimation.create(btnL, xt, yt, null, 500);
				PathAnimation p2 = PathAnimation.create(btnT, xr, yr, null, 500);
				PathAnimation p3 = PathAnimation.create(btnR, xb, yb, null, 500);
				PathAnimation p4 = PathAnimation.create(btnB, xl, yl, animFinished, 500);
				if (animHasEnded) {
					p1.start();
					p2.start();
					p3.start();
					p4.start();
				}
				int aux = btnR.appId;
				btnR.appId = btnB.appId;
				btnB.appId = btnL.appId;
				btnL.appId = btnT.appId;
				btnT.appId = aux;
				break;
			}
			}
			animHasEnded = false;
		}
	}
}
