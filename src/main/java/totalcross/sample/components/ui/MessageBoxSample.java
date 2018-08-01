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
	    	btnTitleOnly.setBackColor(Colors.BLUE);
	    	btnTitleOnly.setForeColor(Color.WHITE);
	      
	    	btnTitleOnly.addPressListener(new PressListener() {
	    		@Override
		        public void controlPressed(ControlEvent e)
		        {
		        	MessageBox mb = new MessageBox("Message" , "This is a MessageBox with title.", new String[]{"Close"});
		        	mb.setBackColor(Colors.BACKGROUND);
		        	mb.setForeColor(Colors.SURFACE);
		        	mb.popup();
		        }
	    	});

	    	btnYesNoTitle = new Button("Yes/No Title only");
	    	btnYesNoTitle.setBackColor(Colors.RED);
	    	btnYesNoTitle.setForeColor(Color.WHITE);
	      
	    	btnYesNoTitle.addPressListener(new PressListener() {
		        @Override
		        public void controlPressed(ControlEvent e)
		        {
		        	MessageBox mb = new MessageBox("Message", "Do you prefer this one?", new String[]{"Yes","No"});
		        	mb.setBackColor(Colors.BACKGROUND);
		        	mb.setForeColor(Colors.SURFACE);
		        	mb.popup();
		        }
		    });
	    	
	    	btnNoTitle = new Button("No title");
	    	btnNoTitle.setBackColor(Colors.SUCESS_GREEN);
	    	btnNoTitle.setForeColor(Color.WHITE);
	      
	    	btnNoTitle.addPressListener(new PressListener() {
	    		@Override
	    		public void controlPressed(ControlEvent e)
	    		{
	    			MessageBox mb = new MessageBox("", "This is a MessageBox without title.", new String[]{"Close"});
	    			mb.setBackColor(Colors.BACKGROUND);
		        	mb.setForeColor(Colors.SURFACE);
	    			mb.popup();
	    		}
	    	});
	    	
	    	btnTitleIconTopSeparatorOnly = new Button("Title and Icon");
	    	btnTitleIconTopSeparatorOnly.setBackColor(Colors.ORANGE);
	    	btnTitleIconTopSeparatorOnly.setForeColor(Color.WHITE);
	      
	    	btnTitleIconTopSeparatorOnly.addPressListener(new PressListener() {
	    		@Override
	    		public void controlPressed(ControlEvent e)
	    		{
	    			MessageBox mb = new MessageBox("Message","This is a MessageBox with title and icon with top separator.",new String[]{"Close"});
	    			mb.setBackColor(Colors.BACKGROUND);
		        	mb.setForeColor(Colors.SURFACE);
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
	    	btnTitleIconTopBottonSeparatorOnly.setBackColor(Colors.YELLOW);
	    	btnTitleIconTopBottonSeparatorOnly.setForeColor(Color.WHITE);
	      
	    	btnTitleIconTopBottonSeparatorOnly.addPressListener(new PressListener() {
		    	@Override
		    	public void controlPressed(ControlEvent e){
		    		MessageBox mb = new MessageBox("Message","This is a MessageBox with title and icon with top and bottom separators.",new String[]{"Close"});
		    		mb.setBackColor(Colors.BACKGROUND);
			        mb.setForeColor(Colors.SURFACE);
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
	    	btnToast.setBackColor(Colors.PURPLE);
	    	btnToast.setForeColor(Color.WHITE);
	    	
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
