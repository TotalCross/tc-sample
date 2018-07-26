package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
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
	private final int GAP = 50;

	@Override
	public void initUI() {

		try {
			super.initUI();
			setScrollBars(false, true);

			c1 = new Container();
			c1.setBackColor(Colors.GRAY);
			c1.setFont(font.asBold());

			Label lbCheck = new Label("SELECT YOUR SUBJECTS", CENTER);
			lbCheck.setBackColor(Colors.RED);

			subject1 = new Check("Biology");
			subject1.checkColor = Colors.RED;
			subject1.setChecked(true);

			subject2 = new Check("Physics");
			subject2.textColor = Colors.BLUE;
			subject2.checkColor = uiMaterial ? Color.BLUE : Color.YELLOW;

			subject3 = new Check("Chemistry");
			subject3.setForeColor(Color.darker(Colors.GREEN));
			subject3.checkColor = Colors.GREEN;

			subject4 = new Check("Math");
			subject4.textColor = Color.darker(Colors.YELLOW, 5);
			subject4.checkColor = Colors.YELLOW;
			subject4.setChecked(true);

			subject5 = new Check("History");
			subject5.textColor = Color.DARK;
			subject5.checkColor = uiMaterial ? Color.DARK : Color.ORANGE;

			subject6 = new Check("Geography");
			subject6.textColor = Colors.PURPLE;
			subject6.checkColor = Colors.PURPLE;

			cancelButtonC1 = new Button("CANCEL", (byte) 0);
			cancelButtonC1.transparentBackground = true;
			cancelButtonC1.setBackForeColors(Color.WHITE, Colors.P_DARK);

			confirmButtonC1 = new Button("CONFIRM");
			confirmButtonC1.setBackForeColors(Colors.P_DARK, Color.WHITE);

			add(c1, LEFT + GAP, TOP + GAP, FILL - GAP, WILL_RESIZE);
			int w = c1.getWidth() / 2 - GAP;
			c1.add(lbCheck, LEFT, TOP, FILL, PREFERRED + fmH * 8);
			c1.add(subject1, LEFT + GAP, AFTER + GAP, w, PREFERRED + H);
			c1.add(subject2, LEFT + GAP, AFTER + GAP * 2, w, PREFERRED + H);
			c1.add(subject3, LEFT + GAP, AFTER + GAP * 2, w, PREFERRED + H);
			c1.add(subject4, RIGHT - GAP, SAME, w, PREFERRED + H, subject1);
			c1.add(subject5, RIGHT - GAP, SAME, w, PREFERRED + H, subject2);
			c1.add(subject6, RIGHT - GAP, SAME, w, PREFERRED + H, subject3);
			c1.add(confirmButtonC1, RIGHT - GAP, AFTER + GAP * 2, w, PREFERRED + fmH * 6);
			c1.add(cancelButtonC1, LEFT + GAP, AFTER + GAP * 2 + (confirmButtonC1.getHeight() / 2), PREFERRED + fmH * 7,
					PREFERRED + fmH * 5, subject6);
			c1.add(new Spacer(), CENTER, AFTER, 10, GAP / 2, confirmButtonC1);
			c1.resizeHeight();

			c2 = new Container();
			c2.setBackColor(Colors.GRAY);
			c2.setFont(font.asBold());

			RadioGroupController radioGroup = new RadioGroupController();
			Label lbRadio = new Label("CHOOSE YOUR AREA OF SCIENCE", CENTER);
			lbRadio.setBackColor(Colors.RED);

			area1 = new Radio("STEM", radioGroup);
			area1.checkColor = Color.DARK;

			area2 = new Radio("Human Sciences", radioGroup);
			area2.textColor = Colors.RED;
			area2.checkColor = uiMaterial ? Color.RED : Color.YELLOW;

			area3 = new Radio("Health Care", radioGroup);
			area3.setForeColor(Color.darker(Colors.ORANGE));
			area3.checkColor = Colors.ORANGE;

			cancelButtonC2 = new Button("CANCEL", (byte) 0);
			cancelButtonC2.transparentBackground = true;
			cancelButtonC2.setBackForeColors(Color.WHITE, Colors.P_DARK);

			confirmButtonC2 = new Button("CONFIRM");
			confirmButtonC2.setBackForeColors(Colors.P_DARK, Color.WHITE);

			add(c2, LEFT + GAP, AFTER + GAP, FILL - GAP, WILL_RESIZE);
			c2.add(lbRadio, LEFT, TOP, FILL, PREFERRED + fmH * 8);
			c2.add(area1, LEFT + GAP * 3, AFTER + GAP, PREFERRED + GAP, PREFERRED + H);
			c2.add(area2, LEFT + GAP * 3, AFTER + GAP, PREFERRED + GAP, PREFERRED + H);
			c2.add(area3, LEFT + GAP * 3, AFTER + GAP, PREFERRED + GAP, PREFERRED + H);
			c2.add(confirmButtonC2, RIGHT - GAP, AFTER + GAP * 2, w, PREFERRED + fmH * 6);
			c2.add(cancelButtonC2, LEFT + GAP, AFTER + GAP * 2 + (confirmButtonC2.getHeight() / 2), PREFERRED + fmH * 7,
					PREFERRED + fmH * 5, area3);
			c2.add(new Spacer(), CENTER, AFTER, 10, GAP / 2, confirmButtonC2);
			c2.resizeHeight();

			add(new Spacer(), CENTER, AFTER, 10, GAP / 2, c2);

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
