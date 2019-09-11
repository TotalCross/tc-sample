package totalcross.sample.components.ui;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.dialog.ProgressBox;
import totalcross.ui.gfx.Color;

public class ProgressBoxSample extends BaseScreen {
  private ButtonMenu menu;
  private ProgressBox pb;
  private int count;

  public ProgressBoxSample () {
      super("https://totalcross.gitbook.io/playbook/components/progress-box");
  }

  @Override
  public void onContent(ScrollContainer content) {
    try {
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
      content.add(bAndroid, CENTER, CENTER - 60, 200 + DP, 40 + DP);
      
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
      content.add(bIOS, CENTER, AFTER + 20, 200 + DP, 40 + DP);
      
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
