package totalcross.sample.components.cards;

import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.ui.*;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.util.UnitsConverter;

public class CardSearch extends Container {

  private Edit edtSearch;
  private Button btSearch;

  public void initUI() {
    try {
      setBackColor(0xFFFFFF);
      setBorderStyle(BORDER_SIMPLE);
      borderColor = 0xFFFFFF;

      edtSearch = new Edit();
      edtSearch.drawLine = false;
      edtSearch.transparentBackground = true;
      edtSearch.caption = "Search";

      btSearch =
          new Button(
              new Image("images/microphone.png")
                  .hwScaledFixedAspectRatio(UnitsConverter.toPixels(DP + 24), true));
      btSearch.setBorder(Button.BORDER_NONE);

      add(btSearch, RIGHT - UnitsConverter.toPixels(DP + 8), CENTER, DP + 48, DP + 48);
      add(edtSearch, LEFT + UnitsConverter.toPixels(DP + 8), CENTER,
              FIT - UnitsConverter.toPixels(DP + 8), DP + 48);
      resizeHeight();
    } catch (ImageException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getPreferredHeight() {
    return UnitsConverter.toPixels(DP + 48);
  }
}
