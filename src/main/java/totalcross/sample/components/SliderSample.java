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
      add(sl, LEFT + fmH + Settings.screenWidth, TOP + fmH * 2 + 150, PARENTSIZE + 50, PREFERRED);
      sl.appId = 1;
      sl.setLiveScrolling(true);
      sl.setBackColor(Color.BLACK);
      sl.sliderColor = Color.RED;

      sl = new Slider(ScrollBar.HORIZONTAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 3));
      add(sl, SAME, AFTER + fmH + 50, PARENTSIZE + 50, PREFERRED);
      sl.appId = 2;
      sl.setUnitIncrement(5);
      sl.drawTicks = false;
      sl.setBackColor(Color.BLUE);
      sl.sliderColor = Color.BLACK;
      

      sl = new Slider(ScrollBar.HORIZONTAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 4));
      add(sl, SAME, AFTER + fmH + 50, PARENTSIZE + 50, PREFERRED);
      sl.appId = 3;
      sl.invertDirection = true;
      sl.setBackColor(Color.GREEN);
      sl.sliderColor = Color.BLUE;

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE));
      add(sl, LEFT+ Settings.screenWidth + 100 , AFTER + fmH + 100, PREFERRED, FONTSIZE + 400);
      sl.appId = 4;
      sl.setLiveScrolling(true);
      sl.setBackColor(Color.PINK);
      sl.sliderColor = Color.MAGENTA;

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 3));
      add(sl, LEFT+ Settings.screenWidth + 150, SAME, PREFERRED, FONTSIZE + 400);
      sl.appId = 5;
      //sl.setUnitIncrement(10);
      //sl.drawTicks = true;
      sl.setLiveScrolling(true);
      //sl.setBackColor(Color.DARK);
      sl.sliderColor = Color.DARK;

      sl = new Slider(ScrollBar.VERTICAL);
      sl.setFont(Font.getFont(false, Font.NORMAL_SIZE / 2 * 4));
      add(sl, LEFT+ Settings.screenWidth + 200, SAME, PREFERRED, FONTSIZE + 400);
      sl.appId = 6;
      sl.invertDirection = true;
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
