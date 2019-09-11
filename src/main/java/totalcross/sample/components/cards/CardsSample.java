package totalcross.sample.components.cards;

import totalcross.sample.components.BaseScreen;
import totalcross.sys.Settings;
import totalcross.ui.ScrollContainer;
import totalcross.util.UnitsConverter;

public class CardsSample extends BaseScreen {
  private int gap = (int) (Settings.screenDensity * 20);

  @Override
  public void onContent(ScrollContainer content) {
    content.setBackColor(0xDCDCDC);
    final int DP_10 = (int) (Settings.screenDensity * 10);
    content.setInsets(0, 0, DP_10, DP_10);
    int space = UnitsConverter.toPixels(DP + 24);
    content.add(new CardSearch(), LEFT + gap, TOP, FILL - gap, PREFERRED);
    content.add(new CardProfile(), LEFT + gap, AFTER + space, FILL - gap, PREFERRED);
    content.add(new CardWeather(), LEFT + gap, AFTER + space, FILL - gap, 170 + DP);
  }
}
