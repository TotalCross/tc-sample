package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.ButtonMenu;
import totalcross.ui.Container;
import totalcross.ui.ProgressBar;
import totalcross.ui.Spinner;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.dialog.ProgressBox;
import totalcross.ui.gfx.Color;

public class ProgressBoxSample extends Container {
  private ButtonMenu menu;
  private ProgressBox pb;
  private int count;

  @Override
  public void initUI() {
    try {
      super.initUI();
      String[] items = {"ProgressBox (Android Spinner)", "ProgressBox (iOS Spinner)"};
      pb = new ProgressBox("Alert!", "null", items, true);
      

      Button bAndroid = new Button("Android Style");
      bAndroid.setBackForeColors(Colors.P_600, Colors.ON_P_600);
      bAndroid.addPressListener((e) -> {
          Spinner.spinnerType = Spinner.ANDROID;
          
          ProgressBoxSample.this.addTimer(1000);
          count = 4;
          
          pb = new ProgressBox("Alert!", "Please wait " + count + " seconds.");
          pb.setBackForeColors(Colors.P_700, Colors.ON_P_700);
          pb.popup();          
      });
      add(bAndroid, CENTER, CENTER - 60, 200 + DP, 40 + DP);
      
      Button bIOS = new Button("IOS Style");
      bIOS.setBackForeColors(Colors.P_600, Colors.ON_P_600);
      bIOS.addPressListener((e) -> {
          Spinner.spinnerType = Spinner.IPHONE;
          
          ProgressBoxSample.this.addTimer(1000);
          count = 4;
          
          pb = new ProgressBox("Alert!", "Please wait " + count + " seconds.");
          pb.setBackForeColors(Colors.P_700, Colors.ON_P_700);
          pb.popup();          
      });
      add(bIOS, CENTER, AFTER + 20, 200 + DP, 40 + DP);
            
      
      ProgressBar bar1 = new ProgressBar(1, 100);
      bar1.setEndlessAutomatic();
      add(bar1, LEFT + 40, AFTER + 40, 100 + DP, PREFERRED);
      
      ProgressBar bar2 = new ProgressBar(1, 100);
      bar2.setEndlessAutomatic();
      add(bar2, LEFT + 40, AFTER + 40, 200 + DP, PREFERRED);
      
      ProgressBar bar3 = new ProgressBar(1, 100);
      bar3.setEndlessAutomatic();
      add(bar3, LEFT + 40, AFTER + 40, 300 + DP, PREFERRED);

      
      this.addTimerListener((e) -> {
    	  if(count >= 0) {
    		  pb.setText("Please wait " + count + " seconds.");
    		  count--;
    	  } else {
    		  pb.unpop();
    		  this.removeTimer(e);
    	  }
      });
      
    } catch (Exception ee) {
      MessageBox.showException(ee, true);
    }
  }
}
