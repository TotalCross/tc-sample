package totalcross.sample.components;

import totalcross.sample.util.Colors;
import totalcross.sample.util.Util;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.CaptionPress;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ScrollContainer;
import totalcross.ui.UIColors;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.dialog.TimeBox;
import totalcross.ui.gfx.Color;

public class EditSample extends Container {
	
	private ScrollContainer sc;
	private Edit simpleEdit;
	private Edit imageEdit;
	private Edit numericEdit;
	private Edit calculatorEdit;
	private Edit calendarEdit;
	private Edit timerEdit;
	private Edit passwordShowEdit;
	private Edit passwordHidenEdit;
	private Edit maskedEdit;
	
	private final int H = 25;
	private int GAP = 100;
	private int focusColor = 0xF0F8FF;
	
	@Override
	public void initUI()
	{	
		super.initUI();
		
		try
	    {
	      Settings.is24Hour = true;
	      UIColors.calculatorFore = Colors.BACKGROUND;
	      UIColors.numericboxBack = Colors.BACKGROUND;
	      UIColors.calendarBack = Colors.BACKGROUND;
	      UIColors.timeboxVisorBack = Colors.BACKGROUND;
	      sc = new ScrollContainer(false, true);
		  add(sc,LEFT,TOP,FILL,FILL);

	      simpleEdit = new Edit();
	      simpleEdit.caption = "Simple Edit";
	      simpleEdit.focusColor = focusColor;
	      simpleEdit.setBackColor(Color.darker(Color.ORANGE));      

	      imageEdit = new Edit();
	      imageEdit.caption = "Icon Edit (Click the Icon)";
	      imageEdit.captionIcon = Util.getAwesomeImage('\uf12d', fmH, Color.BLACK);
	      imageEdit.setBackColor(Color.darker(Color.YELLOW));      
	      imageEdit.captionPress = new CaptionPress()
	      {
	        @Override
	        public void onIconPress()
	        {
	          Vm.debug("on icon press");
	          imageEdit.clear();
	        }

			@Override
			public void onCaptionPress() {
			}
	      };
	      
	      numericEdit = new Edit();
	      numericEdit.caption = "NumericBox Edit";
	      numericEdit.setMode(Edit.CURRENCY); 
	      numericEdit.setBackColor(Color.darker(Color.CYAN));
	      numericEdit.setKeyboard(Edit.KBD_NUMERIC);
	      

	      calculatorEdit = new Edit();
	      calculatorEdit.caption = "Calculator Edit";
	      calculatorEdit.setBackColor(Color.darker(Color.MAGENTA));
	      calculatorEdit.setMode(Edit.CURRENCY, true);

	      calendarEdit = new Edit("99/99/99");
	      calendarEdit.caption = "Calendar Edit";
	      calendarEdit.setBackColor(Color.darker(Color.BLUE));
	      calendarEdit.setMode(Edit.DATE, true);

	      timerEdit = new Edit("99"+Settings.timeSeparator+"99"+Settings.timeSeparator+"99");
	      timerEdit.caption = "TimeBox Edit (24-hour format)";
	      TimeBox.hideIfInvalid = false;
	      timerEdit.setValidChars("0123456789AMP");
	      timerEdit.setBackColor(Color.darker(Color.RED));
	      timerEdit.setMode(Edit.NORMAL, true);
	      timerEdit.setKeyboard(Edit.KBD_TIME);

	      passwordShowEdit = new Edit("");
	      passwordShowEdit.caption = "Password Edit (last character is shown)";
	      passwordShowEdit.setMode(Edit.PASSWORD); 
	      passwordShowEdit.setBackColor(Color.darker(Color.GREEN));

	      passwordHidenEdit = new Edit("");
	      passwordHidenEdit.caption = "Password Edit (all characters are hidden)";
	      passwordHidenEdit.setMode(Edit.PASSWORD_ALL); 
	      passwordHidenEdit.setBackColor(Color.darker(Color.BLUE));

	      maskedEdit = new Edit("999.999.999-99");
	      maskedEdit.setValidChars(Edit.numbersSet);
	      maskedEdit.caption = "Masked Edit (999.999.999-99)";
	      maskedEdit.setMode(Edit.NORMAL,true);

	      sc.add(simpleEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);
	      sc.add(imageEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);
	      sc.add(numericEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);
	      sc.add(calculatorEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);;
	      sc.add(calendarEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);
	      sc.add(timerEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);
	      sc.add(passwordShowEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);
	      sc.add(passwordHidenEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);
	      sc.add(maskedEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H);
	      
	    }
	    catch (Exception ee)
	    {
	      MessageBox.showException(ee,true);
	    }
	}

}
