package totalcross.sample.components.ui;

import totalcross.res.Resources;
import totalcross.sample.util.Colors;
import totalcross.sample.util.Images;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Toast;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class MessageBoxSample extends Container {
	
	private ScrollContainer sc;
	private Button btnTitleOnly;
	private Button btnYesNoTitle;
	private Button btnNoTitle;
	private Button btnTitleIconTopSeparatorOnly;
	private Button btnTitleIconTopBottonSeparatorOnly;
	private Button btnToast;
	
	private int H = 225;
	private int GAP = 50;
	
	@Override
	public void initUI() {
		
		super.initUI();
		
	    try {
	    	
	    	sc = new ScrollContainer(false, true);
			add(sc,LEFT,TOP,FILL,FILL);

	    	btnTitleOnly = new Button("Title only");
	    	btnTitleOnly.setBackForeColors(Colors.P_800, Colors.ON_P_800);
	      
	    	btnTitleOnly.addPressListener(new PressListener() {
	    		@Override
		        public void controlPressed(ControlEvent e)
		        {
		        	MessageBox mb = new MessageBox("Message" , "This is a MessageBox with title.", new String[]{"Close"});
		        	mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
		        	mb.popup();
		        }
	    	});

	    	btnYesNoTitle = new Button("Yes/No Title only");
	    	btnYesNoTitle.setBackForeColors(Colors.P_800, Colors.ON_P_800);
	      
	    	btnYesNoTitle.addPressListener(new PressListener() {
		        @Override
		        public void controlPressed(ControlEvent e)
		        {
		        	MessageBox mb = new MessageBox("Message", "Do you prefer this one?", new String[]{"Yes","No"});
		        	mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
		        	mb.popup();
		        }
		    });
	    	
	    	btnNoTitle = new Button("No title");
	    	btnNoTitle.setBackForeColors(Colors.P_800, Colors.ON_P_800);
	      
	    	btnNoTitle.addPressListener(new PressListener() {
	    		@Override
	    		public void controlPressed(ControlEvent e)
	    		{
	    			MessageBox mb = new MessageBox("", "This is a MessageBox without title.", new String[]{"Close"});
	    			mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
	    			mb.popup();
	    		}
	    	});
	    	
	    	btnTitleIconTopSeparatorOnly = new Button("Title and Icon");
	    	btnTitleIconTopSeparatorOnly.setBackForeColors(Colors.P_800, Colors.ON_P_800);
	      
	    	btnTitleIconTopSeparatorOnly.addPressListener(new PressListener() {
	    		@Override
	    		public void controlPressed(ControlEvent e)
	    		{
	    			MessageBox mb = new MessageBox("Message","This is a MessageBox with title and icon with top separator.",new String[]{"Close"});
	    			mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
	    			mb.headerColor = Colors.KHAKI;
	    			mb.footerColor = Colors.BACKGROUND;
	    			try {
	    				mb.setIcon(Images.getImageBlack(Resources.warning.getFrameInstance(0)));
	    			} catch (Exception e1) {
	    				e1.printStackTrace();
	    			}
	    			mb.popup();
	    		}
	    	});
	    	
	    	btnTitleIconTopBottonSeparatorOnly = new Button("Title and Icon\nTop/bottom separators");
	    	btnTitleIconTopBottonSeparatorOnly.setBackForeColors(Colors.P_800, Colors.ON_P_800);
	      
	    	btnTitleIconTopBottonSeparatorOnly.addPressListener(new PressListener() {
		    	@Override
		    	public void controlPressed(ControlEvent e){
		    		MessageBox mb = new MessageBox("Message","This is a MessageBox with title and icon with top and bottom separators.",new String[]{"Close"});
		    		mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
		    		mb.headerColor = Colors.KHAKI;
		    		mb.footerColor = Colors.KHAKI;
		    		try {
		    			mb.setIcon(Images.getImageBlack(Resources.warning.getFrameInstance(0)));
		    		} catch (Exception e1) {
		    			e1.printStackTrace();
		    		}
		    		mb.popup();
		    	}
	    	});
	      
	    	btnToast = new Button("Toast");
	    	btnToast.setBackForeColors(Colors.P_800, Colors.ON_P_800);
	    	
	    	btnToast.addPressListener(new PressListener() {
		    	@Override
		    	public void controlPressed(ControlEvent e){
		            Toast.show("I'm a Toast", 2000);
		    	}
	    	});
	    	
	      sc.add(btnTitleOnly, LEFT + GAP, TOP + GAP, FILL - GAP, PREFERRED + H);
	      sc.add(btnYesNoTitle, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + H);
	      sc.add(btnNoTitle, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + H);
	      sc.add(btnTitleIconTopSeparatorOnly, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + H);
	      sc.add(btnToast, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + H);

	    }
	    catch (Exception ee)
	    {
	      MessageBox.showException(ee,true);
	    }
	  }

}
