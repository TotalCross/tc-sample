/*********************************************************************************
 *  TotalCross Software Development Kit                                          *
 *  Copyright (C) 2000-2014 SuperWaba Ltda.                                      *
 *  All Rights Reserved                                                          *
 *                                                                               *
 *  This library and virtual machine is distributed in the hope that it will     *
 *  be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                         *
 *                                                                               *
 *  This file is covered by the GNU LESSER GENERAL PUBLIC LICENSE VERSION 3.0    *
 *  A copy of this license is located in file license.txt at the root of this    *
 *  SDK or can be downloaded here:                                               *
 *  http://www.gnu.org/licenses/lgpl-3.0.txt                                     *
 *                                                                               *
 *********************************************************************************/

package totalcross.sample.components.ui;

import totalcross.io.File;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Label;
import totalcross.ui.MultiListBox;
import totalcross.ui.Ruler;
import totalcross.ui.ScrollBar;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Slider;
import totalcross.ui.Spacer;
import totalcross.ui.SpinList;
import totalcross.ui.dialog.ColorChooserBox;
import totalcross.ui.dialog.FileChooserBox;
import totalcross.ui.dialog.InputBox;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.dialog.TimeBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Graphics;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.unit.UIRobotEvent;
import totalcross.util.UnitsConverter;

public class OtherControlsSample extends ScrollContainer {
	private SpinList sl;
	private Label lName, lStatus, lTimeBox, lInputBox, lColorChooserBox, lSpinList, lRuler, lFileChooser;
	private Container timeBoxC, inputBoxC, colorChooserC, spinListC, fileChooserC;
	private int gap = UnitsConverter.toPixels(Control.DP + 20);

	@Override
	public void initUI() {
		try {
			super.initUI();
			
			setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
			int mar = Settings.screenWidth / 10;

			add(timeBoxC = new Container(), LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			timeBoxC.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);
			lTimeBox = new Label("TimeBox");
			lTimeBox.transparentBackground = true;
			timeBoxC.add(lTimeBox, CENTER, AFTER + gap);
			addClock();
			timeBoxC.add(new Spacer(), CENTER, AFTER + gap);
			timeBoxC.resizeHeight();
			add(inputBoxC = new Container(), LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			inputBoxC.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);

			final Button btnInput;
			lInputBox = new Label("InputBox");
			lInputBox.transparentBackground = true;
			inputBoxC.add(lInputBox, CENTER, AFTER + gap);
			inputBoxC.add(lName = new Label("Your name", CENTER), LEFT + gap, AFTER + gap, FILL - gap, PREFERRED);
			inputBoxC.add(btnInput = new Button("Click to input your name"), CENTER, AFTER, PREFERRED, PREFERRED);
			lName.setForeColor(Colors.S_400);
			btnInput.setBackForeColors(Colors.P_600, Colors.ON_P_600);
			inputBoxC.add(new Spacer(), CENTER, AFTER + gap);
			inputBoxC.resizeHeight();
			
			btnInput.addPressListener(new PressListener() {
				@Override
				public void controlPressed(ControlEvent e) {
					InputBox ib = new InputBox("InputBox", "Please enter your name:", "");
					ib.popup();
					String s = ib.getValue();
					if (s != null)
						lName.setText(s);
				}
			});

			add(colorChooserC = new Container(), LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			colorChooserC.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);

			final Button btnChooseColor;
			lColorChooserBox = new Label("ColorChooserBox");
			lColorChooserBox.transparentBackground = true;
			colorChooserC.add(lColorChooserBox, CENTER, AFTER + gap);
			colorChooserC.add(btnChooseColor = new Button("Choose new background color"), CENTER, AFTER, PREFERRED, PREFERRED);
			btnChooseColor.setBackForeColors(Colors.P_600, Colors.ON_P_600);
			colorChooserC.add(new Spacer(), CENTER, AFTER + gap);
			colorChooserC.resizeHeight();
			btnChooseColor.addPressListener(new PressListener() {
				@Override
				public void controlPressed(ControlEvent e) {
					ColorChooserBox ccb = new ColorChooserBox(getBackColor());
					ccb.popup();
					if (ccb.choosenColor != -1) {
						colorChooserC.setBackColor(ccb.choosenColor);
						repaint();
					}
				}
			});

			add(spinListC = new Container(), LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			spinListC.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);

			lSpinList = new Label("SpinList");
			lSpinList.transparentBackground = true;
			spinListC.add(lSpinList, CENTER, AFTER + gap);
			spinListC.add(sl = new SpinList(new String[] { "Today", "Day [1,31]" }, !Settings.fingerTouch), LEFT,
					AFTER, Settings.fingerTouch ? FILL : PREFERRED, PREFERRED);
			sl.hAlign = CENTER;
			spinListC.add(new Spacer(), CENTER, AFTER + gap);
			spinListC.resizeHeight();

			add(fileChooserC = new Container(), LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			fileChooserC.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);

			lFileChooser = new Label("FileChooser");
			lFileChooser.transparentBackground = true;
			fileChooserC.add(lFileChooser, CENTER, AFTER + gap);
			fileChooserC.add(lStatus = new Label("No file", CENTER, Colors.S_600, false), LEFT + gap, AFTER, FILL - gap, PREFERRED);
			lStatus.autoSplit = true;
			lStatus.setHighlighted(true);
			addFileChooser();
			fileChooserC.resizeHeight();
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}

	Button btnClock;
	Slider sb1;
	ScrollBar sb2;

	private void addClock() throws ImageException {
		Image clock = new Image(UnitsConverter.toPixels(Control.DP + 15), UnitsConverter.toPixels(Control.DP + 15));
		int xx = clock.getWidth() - 1;
		int yy = clock.getHeight() - 1;
		Graphics g = clock.getGraphics();
		// g.foreColor = Color.BLACK;
		g.drawCircle(xx / 2, yy / 2, xx / 2);
		g.drawLine(xx / 2, yy / 2, xx, yy / 2);
		g.drawLine(xx / 2, yy / 2, xx / 2, yy / 3);
		btnClock = new Button(clock);
		btnClock.setBorder(Button.BORDER_NONE);
		btnClock.transparentBackground = true;
		timeBoxC.add(btnClock, CENTER, AFTER + gap);
	}

	public void addFileChooser() {
		final Button btn, btn2;
		final Check ch;
		fileChooserC.add(ch = new Check("Multiple selection"), CENTER, AFTER + gap);
		Spacer spcr = new Spacer();
		fileChooserC.add(spcr, CENTER, AFTER + gap, gap, gap);
		fileChooserC.add(btn = new Button("Choose file"), BEFORE, SAME, spcr);
		btn.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		fileChooserC.add(btn2 = new Button("Delete file"), AFTER, SAME, spcr);
		btn2.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		btn2.setEnabled(false);
		btn.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				try {
					FileChooserBox fcb = new FileChooserBox(null);
					fcb.showPreview = true;
					fcb.multipleSelection = ch.isChecked(); // guich@tc115_4
					fcb.mountTree("device/");
					fcb.popup();
					String s = fcb.getAnswer();
					if (s != null) {
						lStatus.setText(s);
					}
					btn2.setEnabled(s != null);
				} catch (Exception ee) {
					MessageBox.showException(ee, true);
				}
			}
		});

		btn2.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				try {
					String s = lStatus.getText();
					File selectedFile = new File(s, File.DONT_OPEN);
					if(selectedFile.exists()) {
						selectedFile.delete();
						lStatus.setText("File deleted");
					} else
						lStatus.setText("Couldn't delete the file");
					btn2.setEnabled(false);
				} catch (Exception ee) {
					MessageBox.showException(ee, false);
				}
			}
		});
	}

	@Override
	public void onEvent(Event event) {
		if (event instanceof UIRobotEvent) {
			lStatus.setMarqueeText(event.type == UIRobotEvent.ROBOT_SUCCEED ? "Robot succeed"
					: "Robot failed: " + ((UIRobotEvent) event).failureReason, 100, 1, -5);
		} else if (event.type == ControlEvent.PRESSED) {
			if (event.target == btnClock) {
				TimeBox tb = new TimeBox();
				tb.popup();
				lStatus.setText(tb.getTime().toString());
			} else if (event.target == sb1 || event.target == sb2) {
				int value = ((ScrollBar) event.target).getValue();
				sb1.setValue(value);
				sb2.setValue(value);
			} else if (event.target instanceof MultiListBox) {
				lStatus.setText("Last selected: " + ((MultiListBox) event.target).getLastSelectedItem());
			}
		}
	}
}
