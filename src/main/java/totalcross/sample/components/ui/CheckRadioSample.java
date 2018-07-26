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

public class CheckRadioSample extends Container {
	private Button confirmButton;
	private Button cancelButton;
	private ScrollContainer sc;
	private Check simpleCheck;
	private Check backgroundCheck;
	private Check foregroundCheck;
	private Check simpleCheck2;
	private Check backgroundCheck2;
	private Check foregroundCheck2;
	private Radio simpleRadio;
	private Radio backgroundRadio;
	private Radio foregroundRadio;
	private Spacer spacer;
	
	private final int H = 25;
	private final int GAP = 100;
	
	@Override
	public void initUI() {
		
		try {
			
			super.initUI();
			
			sc = new ScrollContainer(false, true);
		    add(sc,LEFT,TOP,FILL,FILL);
		    
		    


			Label lbCheck = new Label("Select your subjects");
			lbCheck.setFont(lbCheck.getFont().asBold());
			
			simpleCheck = new Check("Biology");
			simpleCheck.checkColor = Colors.RED;
			simpleCheck.setChecked(true);

		    backgroundCheck = new Check("Physics");
		    backgroundCheck.textColor = Colors.BLUE;
		    backgroundCheck.checkColor = uiMaterial ? Color.BLUE : Color.YELLOW;
	
		    foregroundCheck = new Check("Chemistry");
		    foregroundCheck.setForeColor(Color.darker(Colors.GREEN));
		    foregroundCheck.checkColor = Colors.GREEN;
		    
		    simpleCheck2 = new Check("Mathematics");
		    simpleCheck2.textColor = Colors.YELLOW;
			simpleCheck2.checkColor = Colors.YELLOW;
			simpleCheck2.setChecked(true);

		    backgroundCheck2 = new Check("History");
		    backgroundCheck2.textColor = Color.DARK;
		    backgroundCheck2.checkColor = uiMaterial ? Color.DARK : Color.ORANGE;
	
		    foregroundCheck2 = new Check("Geography");
		   		    foregroundCheck2.textColor = Colors.PURPLE;
		    foregroundCheck2.checkColor = Colors.PURPLE;
	
		    RadioGroupController radioGroup = new RadioGroupController();
		    Label lbRadio = new Label("Choose your area of ​​science");
		    lbRadio.setFont(lbRadio.getFont().asBold());

		    simpleRadio = new Radio("STEM", radioGroup);
		    simpleRadio.checkColor = Color.DARK;
		    simpleRadio.textColor = Color.DARK;
		    simpleRadio.setChecked(true);
	
		    backgroundRadio = new Radio("Human Sciences", radioGroup);
		    backgroundRadio.textColor = Colors.RED;
		    backgroundRadio.checkColor = uiMaterial ? Color.RED : Color.YELLOW;
	
		    foregroundRadio = new Radio("Health Care", radioGroup);
		    foregroundRadio.setForeColor(Color.darker(Colors.ORANGE));
		    foregroundRadio.checkColor = Colors.ORANGE;
		    
		    cancelButton = new Button("CANCEL", (byte)0);
		    cancelButton.transparentBackground = true;
		    cancelButton.setBackForeColors(Color.WHITE, Colors.P_DARK);
		    
		    confirmButton = new Button("Confirm");
			confirmButton.setBackForeColors(Colors.P_DARK, Color.WHITE);
			
			cancelButton.addPressListener(new PressListener()
		    {
		    	@Override
		        public void controlPressed(ControlEvent e)
		        {
		    		if(!simpleCheck.isEnabled()) {
		    			simpleCheck.setEnabled(true);
			    		backgroundCheck.setEnabled(true);
			    		foregroundCheck.setEnabled(true);
			    		simpleCheck2.setEnabled(true);
			    		backgroundCheck2.setEnabled(true);
			    		foregroundCheck2.setEnabled(true);
		    		}
		    		
		        }
		    });
		    confirmButton.addPressListener(new PressListener()
		    {
		    	@Override
		        public void controlPressed(ControlEvent e)
		        {
		    		simpleCheck.setEnabled(false);
		    		backgroundCheck.setEnabled(false);
		    		foregroundCheck.setEnabled(false);
		    		simpleCheck2.setEnabled(false);
		    		backgroundCheck2.setEnabled(false);
		    		foregroundCheck2.setEnabled(false);
		    		
		        }
		    });
		    
		    
		    sc.add(lbCheck, CENTER, AFTER + GAP,PREFERRED,PREFERRED + H);
		    sc.add(simpleCheck, LEFT + GAP, AFTER + GAP,PREFERRED + H,PREFERRED + H);
		    sc.add(backgroundCheck, LEFT + GAP, AFTER + GAP,PREFERRED + H,PREFERRED + H);
		    sc.add(foregroundCheck, LEFT + GAP, AFTER + GAP,PREFERRED + H,PREFERRED + H);
		    sc.add(simpleCheck2, Settings.screenWidth/2, SAME,PREFERRED + H ,PREFERRED + H, simpleCheck);
		    sc.add(backgroundCheck2, Settings.screenWidth/2, SAME,PREFERRED + H,PREFERRED + H, backgroundCheck);
		    sc.add(foregroundCheck2, Settings.screenWidth/2, SAME,PREFERRED + H,PREFERRED + H, foregroundCheck);
		    sc.add(spacer = new Spacer(),CENTER, AFTER+GAP, 10, SAME);
		    sc.add(confirmButton, BEFORE, SAME, PREFERRED + fmH*7, PREFERRED+fmH*5);
		    sc.add(cancelButton, AFTER, SAME+(confirmButton.getHeight()), PREFERRED + GAP, PREFERRED+H,spacer);
		    sc.add(lbRadio, CENTER, AFTER + GAP,PREFERRED,PREFERRED + H);
		    sc.add(simpleRadio, LEFT + GAP*3, AFTER + GAP,PREFERRED + GAP,PREFERRED + H);
		    sc.add(backgroundRadio,LEFT + GAP*3 , AFTER + GAP,PREFERRED + GAP,PREFERRED + H);
		    sc.add(foregroundRadio, LEFT + GAP*3, AFTER + GAP,PREFERRED + GAP,PREFERRED + H);
	      
	    } catch (Exception e) {
	      MessageBox.showException(e,true);
	    }
		
	}
}
