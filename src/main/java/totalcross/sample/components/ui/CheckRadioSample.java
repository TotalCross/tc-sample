package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.Radio;
import totalcross.ui.RadioGroupController;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class CheckRadioSample extends Container {
	private Check disableCheck;
	private ScrollContainer sc;
	private Check simpleCheck;
	private Check backgroundCheck;
	private Check foregroundCheck;
	private Radio simpleRadio;
	private Radio backgroundRadio;
	private Radio foregroundRadio;
	
	private final int H = 25;
	private final int GAP = 100;
	
	@Override
	public void initUI() {
		
		try {
			
			super.initUI();
			
			sc = new ScrollContainer(false, true);
		    add(sc,LEFT,TOP,FILL,FILL);
		    
		    disableCheck = new Check("Disable all options");
			disableCheck.checkColor = Colors.GREEN;
			disableCheck.setChecked(true);


			Label lbCheck = new Label("Check completed tasks");
			lbCheck.setFont(lbCheck.getFont().asBold());
			
			simpleCheck = new Check("Task 1");
			simpleCheck.checkColor = Colors.RED;
			simpleCheck.setChecked(true);

		    backgroundCheck = new Check("Task 2");
		    backgroundCheck.setBackColor(Colors.YELLOW);
		    backgroundCheck.textColor = Colors.BLUE;
		    backgroundCheck.checkColor = uiMaterial ? Color.BLACK : Color.YELLOW;
	
		    foregroundCheck = new Check("Task 3");
		    foregroundCheck.setForeColor(Color.darker(Colors.GREEN));
		    foregroundCheck.checkColor = Colors.GREEN;
	
		    RadioGroupController radioGroup = new RadioGroupController();
		    Label lbRadio = new Label("Choose one option");
		    lbRadio.setFont(lbRadio.getFont().asBold());

		    simpleRadio = new Radio("Option 1", radioGroup);
		    simpleRadio.checkColor = Colors.RED;
		    simpleRadio.setChecked(true);
	
		    backgroundRadio = new Radio("Option 2", radioGroup);
		    backgroundRadio.setBackColor(Colors.YELLOW);
		    backgroundRadio.textColor = Colors.BLUE;
		    backgroundRadio.checkColor = uiMaterial ? Color.BLACK : Color.YELLOW;
	
		    foregroundRadio = new Radio("Option 3", radioGroup);
		    foregroundRadio.setForeColor(Color.darker(Colors.GREEN));
		    foregroundRadio.checkColor = Colors.GREEN;
		    
		    disableCheck.addPressListener(new PressListener()
		    {
		    	@Override
		        public void controlPressed(ControlEvent e)
		        {
		    		boolean b = ( (Check) disableCheck ).isChecked();
		    		simpleCheck.setEnabled(b);
		    		backgroundCheck.setEnabled(b);
		    		foregroundCheck.setEnabled(b);
		    		simpleRadio.setEnabled(b);
		    		backgroundRadio.setEnabled(b);
		    		foregroundRadio.setEnabled(b);
		        }
		    });
		    
		    sc.add(disableCheck, LEFT + GAP/2, TOP+GAP/2, PREFERRED + GAP, PREFERRED+H);
		    sc.add(lbCheck, CENTER, AFTER + GAP,PREFERRED,PREFERRED + H);
		    sc.add(simpleCheck, LEFT + GAP, AFTER + GAP,FILL - GAP,PREFERRED + H);
		    sc.add(backgroundCheck, LEFT + GAP, AFTER + GAP,FILL - GAP,PREFERRED + H);
		    sc.add(foregroundCheck, LEFT + GAP, AFTER + GAP,FILL - GAP,PREFERRED + H);
		    sc.add(lbRadio, CENTER, AFTER + GAP,PREFERRED,PREFERRED + H);
		    sc.add(simpleRadio, LEFT + GAP, AFTER + GAP,PREFERRED + GAP,PREFERRED + H);
		    sc.add(backgroundRadio, LEFT + GAP, AFTER + GAP,PREFERRED + GAP,PREFERRED + H);
		    sc.add(foregroundRadio, LEFT + GAP, AFTER + GAP,PREFERRED + GAP,PREFERRED + H);
	      
	    } catch (Exception e) {
	      MessageBox.showException(e,true);
	    }
		
	}
}
