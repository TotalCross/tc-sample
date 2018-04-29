package totalcross.sample.components.cards;

import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.ui.*;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class CardSearch extends Container {

  private Edit edtSearch;
  private Button btSearch;

  public void initUI() {
    try {
      setBackColor(0xFFFFFF);
      setBorderStyle(BORDER_SIMPLE);
      borderColor = 0xFFFFFF;

      edtSearch = new Edit();
      edtSearch.caption = "Search";

      btSearch =
          new Button(
              new Image("images/microphone.png")
                  .hwScaledFixedAspectRatio((int) (Settings.screenDensity * 24), true));
      btSearch.setBorder(Button.BORDER_NONE);

      add(btSearch, RIGHT - 50, CENTER, (int) (Settings.screenDensity * 48), DP + 48);
      add(edtSearch, LEFT + 50, CENTER, FIT - 50, PREFERRED);

    } catch (ImageException | IOException e) {
      e.printStackTrace();
    }
  }
}
