package totalcross.sample.components;

import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollBar;
import totalcross.ui.Slider;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;


public class SliderSample extends Container {
  private Label l;

  @Override
  public void initUI() {
    try {
      super.initUI();

      add(l = new Label("", CENTER), LEFT, TOP);

      Slider sl;
      sl = new Slider(ScrollBar.HORIZONTAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE));
      add(sl, LEFT + fmH + Settings.screenWidth + 100, TOP + fmH * 2 + 150, PARENTSIZE + 50, PREFERRED);
      sl.appId = 1;
      sl.setLiveScrolling(true);
      sl.setBackColor(Color.getRGB(158, 197, 244));
      sl.sliderColor = Color.getRGB(12, 98, 200);
      sl.setValue(10);
      
      
      
      sl = new Slider(ScrollBar.HORIZONTAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 3));
      add(sl, SAME, AFTER + fmH + 100, PARENTSIZE + 50, PREFERRED);
      sl.appId = 2;
      sl.setUnitIncrement(5);
      sl.drawTicks = false;
      sl.setBackColor(Color.getRGB(255, 234, 157));
      sl.sliderColor = Color.getRGB(255, 199, 0);
      sl.setValue(30);
      

      sl = new Slider(ScrollBar.HORIZONTAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 4));
      add(sl, SAME, AFTER + fmH + 50, PARENTSIZE + 50, PREFERRED);
      sl.appId = 3;
      sl.invertDirection = true;
      sl.setBackColor(Color.getRGB(255, 192, 157));
      sl.sliderColor = Color.getRGB(255, 92, 0);
      sl.setValue(50);

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE));
      add(sl, LEFT+ fmH + Settings.screenWidth + 100 , AFTER + fmH + 200, PREFERRED, FONTSIZE + 400);
      sl.appId = 4;
      sl.setLiveScrolling(true);
      sl.setBackColor(Color.getRGB(149, 243, 230));
      sl.sliderColor = Color.getRGB(0, 195, 168);
      sl.setValue(70);

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 3));
      add(sl, LEFT+ Settings.screenWidth + 150, SAME, PREFERRED, FONTSIZE + 400);
      sl.appId = 5;
      //sl.setUnitIncrement(10);
      //sl.drawTicks = true;
      sl.setLiveScrolling(true);
      sl.setBackColor(Color.getRGB(255, 220, 157));
      sl.sliderColor = Color.getRGB(255, 164, 0);
      sl.setValue(90);

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 4));
      add(sl, LEFT+ Settings.screenWidth + 200, SAME, PREFERRED, FONTSIZE + 400);
      sl.appId = 6;
      sl.invertDirection = true;
      sl.setBackColor(Color.getRGB(254, 156, 165));
      sl.sliderColor = Color.getRGB(255, 0, 24);
      sl.setValue(50);
    } catch (Exception ee) {
      MessageBox.showException(ee, true);
    }
  }

  @Override
  public void onEvent(Event e) {
    if (e.type == ControlEvent.PRESSED && e.target instanceof Slider) {
      Slider s = (Slider) e.target;
      //l.setText(s.appId + ": " + s.getValue());
    }
  }
}
