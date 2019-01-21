package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.Radio;
import totalcross.ui.RadioGroupController;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class CheckRadioSample extends ScrollContainer {
	private Container c1, c2;
	private Button confirmButtonC1, confirmButtonC2;
	private Button cancelButtonC1, cancelButtonC2;
	private Check subject1, subject2, subject3, subject4, subject5, subject6;
	private Radio area1, area2, area3;

	private final int H = 25;
	private int gap = 50;

	@Override
	public void initUI() {
		try {
			super.initUI();
			setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
			setScrollBars(false, true);

			c1 = new Container();
			c1.setBackForeColors(Color.WHITE, Colors.ON_P_200);

			Label lbCheck = new Label("SELECT YOUR SUBJECTS", CENTER);
			lbCheck.setBackForeColors(Colors.P_200, Colors.ON_P_200);

			subject1 = new Check("Biology");
			subject1.setChecked(true);
			subject2 = new Check("Physics");
			subject3 = new Check("Chemistry");
			subject4 = new Check("Math");
			subject4.setChecked(true);
			subject5 = new Check("History");
			subject6 = new Check("Geography");

			subject1.setForeColor(Colors.P_700);
			subject2.setForeColor(Colors.P_700);
			subject3.setForeColor(Colors.P_700);
			subject4.setForeColor(Colors.P_700);
			subject5.setForeColor(Colors.P_700);
			subject6.setForeColor(Colors.P_700);
			
			cancelButtonC1 = new Button("CANCEL", (byte) 0);
			cancelButtonC1.transparentBackground = true;
			cancelButtonC1.setBackForeColors(Colors.BACKGROUND, Colors.P_700);

			confirmButtonC1 = new Button("CONFIRM");
			confirmButtonC1.setBackForeColors(Colors.P_700, Colors.ON_P_700);
			confirmButtonC1.borderColor = Colors.P_700;

			add(c1, LEFT + gap, TOP + gap, FILL - gap, WILL_RESIZE);
			int w = c1.getWidth() / 2 - gap;
			int c1m = Settings.screenWidth/2;
			c1.add(lbCheck, LEFT, TOP, FILL, PREFERRED + fmH * 8);
			c1.add(subject1, LEFT + gap, AFTER + gap, PREFERRED , PREFERRED + H);
			c1.add(subject2, LEFT + gap, AFTER + gap * 2, PREFERRED, PREFERRED + H);
			c1.add(subject3, LEFT + gap, AFTER + gap * 2, PREFERRED, PREFERRED + H);
			c1.add(subject4, c1m, SAME, PREFERRED, PREFERRED + H, subject1);
			c1.add(subject5, c1m, SAME, PREFERRED, PREFERRED + H, subject2);
			c1.add(subject6, c1m, SAME, PREFERRED, PREFERRED + H, subject3);
			c1.add(confirmButtonC1, RIGHT - gap, AFTER + gap * 2, w, PREFERRED, subject6);
			c1.add(cancelButtonC1, LEFT + gap, CENTER_OF, confirmButtonC1);
			c1.add(new Spacer(), CENTER, AFTER, 10, gap / 2, confirmButtonC1);
			c1.resizeHeight();

			c2 = new Container();
			c2.setBackForeColors(Color.WHITE, Colors.ON_P_200);

			RadioGroupController radioGroup = new RadioGroupController();
			Label lbRadio = new Label("CHOOSE YOUR AREA OF SCIENCE", CENTER);
			lbRadio.setBackForeColors(Colors.P_200, Colors.ON_P_200);

			area1 = new Radio("STEM", radioGroup);
			area2 = new Radio("Human Sciences", radioGroup);
			area3 = new Radio("Health Care", radioGroup);
			
			area1.setForeColor(Colors.P_700);
			area2.setForeColor(Colors.P_700);
			area3.setForeColor(Colors.P_700);
			
			cancelButtonC2 = new Button("CANCEL", (byte) 0);
			cancelButtonC2.transparentBackground = true;
			cancelButtonC2.setBackForeColors(Colors.BACKGROUND, Colors.P_700);
			
			confirmButtonC2 = new Button("CONFIRM");
			confirmButtonC2.setBackForeColors(Colors.P_700, Colors.ON_P_700);
			confirmButtonC2.borderColor = Colors.P_700;
			add(c2, LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			c2.add(lbRadio, LEFT, TOP, FILL, PREFERRED + fmH * 8);
			c2.add(area1, LEFT + gap * 3, AFTER + gap, PREFERRED + gap, PREFERRED + H);
			c2.add(area2, LEFT + gap * 3, AFTER + gap, PREFERRED + gap, PREFERRED + H);
			c2.add(area3, LEFT + gap * 3, AFTER + gap, PREFERRED + gap, PREFERRED + H);
			
			c2.add(confirmButtonC2, RIGHT - gap, AFTER + gap * 2, w, PREFERRED, area3);
			c2.add(cancelButtonC2, LEFT + gap, CENTER_OF, confirmButtonC2);
			
//			c2.add(confirmButtonC2, RIGHT - gap, AFTER + gap * 2, w, PREFERRED + fmH * 6);
//			c2.add(cancelButtonC2, LEFT + gap, AFTER + gap * 2 + (confirmButtonC2.getHeight() / 2), PREFERRED + fmH * 7,
//					PREFERRED + fmH * 5, area3);
			c2.add(new Spacer(), CENTER, AFTER, 10, gap / 2, confirmButtonC2);
			c2.resizeHeight();

			add(new Spacer(), CENTER, AFTER, 10, gap / 2, c2);

			cancelButtonC1.addPressListener(new PressListener() {
				@Override
				public void controlPressed(ControlEvent e) {
					subject1.setEnabled(true); 
					subject2.setEnabled(true); 
					subject3.setEnabled(true); 
					subject4.setEnabled(true); 
					subject5.setEnabled(true); 
					subject6.setEnabled(true);
					subject1.setChecked(false);
					subject2.setChecked(false);
					subject3.setChecked(false);
					subject4.setChecked(false);
					subject5.setChecked(false);
					subject6.setChecked(false);
				}
			});
			confirmButtonC1.addPressListener(new PressListener() {
				@Override
				public void controlPressed(ControlEvent e) {
					subject1.setEnabled(false);
					subject2.setEnabled(false);
					subject3.setEnabled(false);
					subject4.setEnabled(false);
					subject5.setEnabled(false);
					subject6.setEnabled(false);

				}
			});

			cancelButtonC2.addPressListener(new PressListener() {
				@Override
				public void controlPressed(ControlEvent e) {
					area1.setEnabled(true);
					area2.setEnabled(true);
					area3.setEnabled(true);
					area1.setChecked(false);
					area2.setChecked(false);
					area3.setChecked(false);
				}
			});
			confirmButtonC2.addPressListener(new PressListener() {
				@Override
				public void controlPressed(ControlEvent e) {
					area1.setEnabled(false);
					area2.setEnabled(false);
					area3.setEnabled(false);
				}
			});

		} catch (Exception e) {
			MessageBox.showException(e, true);
		}

	}
}
