package totalcross.sample.components.cards;

import totalcross.sys.Settings;
import totalcross.ui.ScrollContainer;

public class CardsSample extends ScrollContainer {

  public CardsSample() {
    super(false, true);
  }

  public void initUI() {
    setBackColor(0xDCDCDC);
    final int DP_10 = (int) (Settings.screenDensity * 10);
    setInsets(0, 0, DP_10, DP_10);

    add(new CardSearch(), LEFT + 30, TOP, FILL - 30, DP + 60);
    add(new CardProfile(), LEFT + 30, AFTER + 50, FILL - 30, DP + 240);
    add(new CardWeather(), LEFT + 30, AFTER + 50, FILL - 30, DP + 120);
    add(new CardSocial(), LEFT + 30, AFTER + 50, FILL - 30, DP + 60);
  }
}
