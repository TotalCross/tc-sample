package totalcross.sample.components.ui;

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
import totalcross.ui.ScrollContainer;


public class SliderSample extends Container {
  private Label l;
  private ScrollContainer sc;
  
  @Override
  public void initUI() {
    try {
      super.initUI();
      
      sc = new ScrollContainer(false, true);
      add(sc,LEFT,TOP,FILL,FILL);
      
      add(l = new Label("", CENTER), LEFT, TOP);

      Slider sl;
      sl = new Slider(ScrollBar.HORIZONTAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE));
      sl.appId = 1;
      sl.setLiveScrolling(true);
      sl.setBackColor(Color.getRGB(158, 197, 244));
      sl.sliderColor = Color.getRGB(12, 98, 200);
      sl.setValue(10);
      sc.add(sl,CENTER, TOP + fmH * 2 + 150, (Settings.screenWidth - ((Settings.screenWidth)/10)*2), PREFERRED);
      
      
      
      sl = new Slider(ScrollBar.HORIZONTAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 3));
      sl.appId = 2;
      sl.setUnitIncrement(5);
      sl.drawTicks = false;
      sl.setBackColor(Color.getRGB(255, 234, 157));
      sl.sliderColor = Color.getRGB(255, 199, 0);
      
      sl.setValue(30);
      sc.add(sl, CENTER, AFTER + fmH + 100, (Settings.screenWidth - ((Settings.screenWidth)/10)*2), PREFERRED);

      sl = new Slider(ScrollBar.HORIZONTAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 4));
      sl.appId = 3;
      sl.invertDirection = true;
      sl.setBackColor(Color.getRGB(255, 192, 157));
      sl.sliderColor = Color.getRGB(255, 92, 0);
      sl.setValue(50);
      sc.add(sl, CENTER, AFTER + fmH + 50, (Settings.screenWidth - ((Settings.screenWidth)/10)*2), PREFERRED);

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE));
      sl.appId = 4;
      sl.setLiveScrolling(true);
      sl.setBackColor(Color.getRGB(149, 243, 230));
      sl.sliderColor = Color.getRGB(0, 195, 168);
      sl.setValue(70);
      sc.add(sl, BEFORE + 200 , AFTER + fmH + 200, PREFERRED, Settings.screenHeight/3);

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 3));
      sl.appId = 5;
      sl.setLiveScrolling(true);
      sl.setBackColor(Color.getRGB(255, 220, 157));
      sl.sliderColor = Color.getRGB(255, 164, 0);
      sl.setValue(90);
      sc.add(sl, CENTER , SAME, PREFERRED, Settings.screenHeight/3);

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 4));
      sl.appId = 6;
      sl.invertDirection = true;
      sl.setBackColor(Color.getRGB(254, 156, 165));
      sl.sliderColor = Color.getRGB(255, 0, 24);
      sl.setValue(50);
      sc.add(sl,AFTER + 200, SAME, PREFERRED, Settings.screenHeight/3);
    } catch (Exception ee) {
      MessageBox.showException(ee, true);
    }
  }

  @Override
  public void onEvent(Event e) {
    if (e.type == ControlEvent.PRESSED && e.target instanceof Slider) {
      Slider s = (Slider) e.target;
    }
  }
}
