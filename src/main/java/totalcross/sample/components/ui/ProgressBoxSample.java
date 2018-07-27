package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Vm;
import totalcross.ui.ButtonMenu;
import totalcross.ui.Container;
import totalcross.ui.Spinner;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.dialog.ProgressBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class ProgressBoxSample extends Container {
  private ButtonMenu menu;
  private ProgressBox pb;
  private int count;

  @Override
  public void initUI() {
    try {
      super.initUI();
      pb = new ProgressBox("Alert!", "null", null);
      
      String[] items = {"ProgressBox (Android style)", "ProgressBox (iOS style)"};

      menu = new ButtonMenu(items, ButtonMenu.SINGLE_COLUMN);
      menu.borderGap = 30;
      menu.textGap = 40;
      
      menu.pressedColor = Color.GREEN;
      add(menu, LEFT, TOP, FILL, FILL);

      count = 2;
      
      for (int i = 0; i < items.length; i++)
    	  menu.getButton(i).setBackForeColors(Colors.P_DARK, Color.WHITE);
      
      
      menu.addPressListener((e) -> {
    	  int sel = menu.getSelectedIndex();
          Spinner.spinnerType = sel == 0 ? Spinner.ANDROID : Spinner.IPHONE;
          
          pb = new ProgressBox("Alert!", "Please wait\n" + count + "seconds", null);
          pb.setBackColor(Color.getRGB(12, 98, 200));
          menu.addTimer(1000);
          pb.popup();
      });
      
      menu.addTimerListener((e) -> {
    	  System.out.println("TESTE");
    	  if(count >= 0) {
    		  pb.setText("Please wait\n" + count + " seconds.");
    		  count--;
    	  } else {
    		  pb.unpop();
    		  count = 2;
    		  menu.removeTimer(e);
    	  }
      });
      
    } catch (Exception ee) {
      MessageBox.showException(ee, true);
    }
  }
}
