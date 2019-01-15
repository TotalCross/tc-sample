package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sample.util.Util;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.dialog.TimeBox;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class EditSample extends Container {

	private ScrollContainer sc;
	private Edit simpleEdit;
	private Edit numericEdit;
	private Edit calculatorEdit;
	private Edit calendarEdit;
	private Edit timerEdit;
	private Edit passwordShowEdit;
	private Edit passwordHidenEdit;
	private Edit maskedEdit;
	private MultiEdit multiEdit;
	private OutlinedEdit outlinedEdit;

	private final int H = 25;
	private int GAP = 100;
	private int focusColor = Color.GREEN;

	@Override
	public void initUI() {
		try {
			Edit.useNativeNumericPad = false;
			Settings.is24Hour = true;
			UIColors.calculatorFore = Colors.BACKGROUND;
			UIColors.numericboxBack = Colors.BACKGROUND;
			UIColors.calendarBack = Colors.BACKGROUND;
			UIColors.timeboxVisorBack = Colors.BACKGROUND;
			sc = new ScrollContainer(false, true);
			add(sc, LEFT, TOP, FILL, FILL);

			simpleEdit = new Edit();
			simpleEdit.caption = "Simple Edit";
			simpleEdit.setBackColor(Color.BRIGHT);
			simpleEdit.transparentBackground = true;
			
			outlinedEdit = new OutlinedEdit();
			outlinedEdit.caption = "OutlinedEdit";
			outlinedEdit.captionColor = Color.getRGB(176, 46, 247);

			multiEdit = new MultiEdit();
			multiEdit.caption = "MultiEdit";
			
			numericEdit = new Edit();
			numericEdit.caption = "NumericBox Edit";
			numericEdit.setMode(Edit.CURRENCY);
			numericEdit.setKeyboard(Edit.KBD_NUMERIC);

			calculatorEdit = new Edit();
			calculatorEdit.caption = "Calculator Edit";
			calculatorEdit.setMode(Edit.CURRENCY, true);

			calendarEdit = new Edit("99/99/99");
			calendarEdit.caption = "Calendar Edit";
			calendarEdit.setMode(Edit.DATE, true);

			timerEdit = new Edit("99" + Settings.timeSeparator + "99" + Settings.timeSeparator + "99");
			timerEdit.caption = "TimeBox Edit (24-hour format)";
			TimeBox.hideIfInvalid = false;
			timerEdit.setValidChars("0123456789AMP");
			timerEdit.setMode(Edit.NORMAL, true);
			timerEdit.setKeyboard(Edit.KBD_TIME);

			passwordShowEdit = new Edit("");
			passwordShowEdit.caption = "Password Edit (last character is shown)";
			passwordShowEdit.setMode(Edit.PASSWORD);
			passwordHidenEdit = new Edit("");
			passwordHidenEdit.caption = "Password Edit (all characters are hidden)";
			passwordHidenEdit.setMode(Edit.PASSWORD_ALL);

			maskedEdit = new Edit("999.999.999-99");
			maskedEdit.caption = "Masked Edit (999.999.999-99)";
			maskedEdit.setMode(Edit.NORMAL, true);
			maskedEdit.setValidChars(Edit.numbersSet);

			sc.add(simpleEdit, LEFT + GAP, AFTER + GAP);
			sc.add(outlinedEdit, LEFT + GAP, AFTER + GAP);
			sc.add(multiEdit, LEFT + GAP, AFTER + GAP, FILL - GAP, DP + 48);
			sc.add(numericEdit, LEFT + GAP, AFTER + GAP);
			sc.add(calculatorEdit, LEFT + GAP, AFTER + GAP);
			sc.add(calendarEdit, LEFT + GAP, AFTER + GAP);
			sc.add(timerEdit, LEFT + GAP, AFTER + GAP);
			sc.add(passwordShowEdit, LEFT + GAP, AFTER + GAP);
			sc.add(passwordHidenEdit, LEFT + GAP, AFTER + GAP);
			sc.add(maskedEdit, LEFT + GAP, AFTER + GAP);

		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}

}
