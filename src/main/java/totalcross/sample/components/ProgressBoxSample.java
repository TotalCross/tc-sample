package totalcross.sample.components;

import totalcross.sys.Vm;
import totalcross.ui.ButtonMenu;
import totalcross.ui.Container;
import totalcross.ui.Spinner;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.dialog.ProgressBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;

public class ProgressBoxSample extends Container {
  ButtonMenu menu;

  @Override
  public void initUI() {
    try {
      super.initUI();

      String[] items = { "Small ProgressBox (style 1)", "Big ProgressBox (style 1)", "Small ProgressBox (style 2)",
          "Big ProgressBox (style 2)" };

      menu = new ButtonMenu(items, ButtonMenu.SINGLE_COLUMN);
      menu.pressedColor = Color.GREEN;
      add(menu, LEFT, TOP, FILL, FILL);

      //setInfo("Each test takes 5 seconds");
    } catch (Exception ee) {
      MessageBox.showException(ee, true);
    }
  }

  @Override
  public void onEvent(Event e) {
    if (e.type == ControlEvent.PRESSED && e.target == menu) {
      int sel = menu.getSelectedIndex();
      Spinner.spinnerType = sel >= 2 ? Spinner.ANDROID : Spinner.IPHONE;
      String msg = sel == 0 || sel == 2 ? "Please wait\n 2 seconds."
          : "Please wait\n 4 seconds.";
      ProgressBox pb = new ProgressBox("Alert!", msg, null);
      pb.setBackColor(Color.getRGB(12, 98, 200));
      pb.popupNonBlocking();
      // we can't just block using Vm.sleep because it would also 
      // block a screen rotation from correctly paint the screen
      if(sel == 1 || sel == 3) {
    	for(int i=3; i>0; i--) {
    		Vm.safeSleep(1000);
    		if(i!=1)
    		pb.setText("Please wait\n " +i+ " seconds.");
    		else
    		pb.setText("Please wait\n " +i+ " seconds.");
    			
    	}
      }
      else
    	  Vm.safeSleep(1000);
          pb.setText("Please wait\n 1 second.");
          Vm.safeSleep(1000);
      pb.unpop();
      //setInfo(sel == 1 || sel == 3 ? "BUM!!!!" : "Loaded");
    }
  }
}
