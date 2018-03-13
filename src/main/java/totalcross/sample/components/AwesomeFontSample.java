package totalcross.sample.components;

import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.icon.Icon;
import totalcross.ui.icon.MaterialIcons;

public class AwesomeFontSample extends ScrollContainer {

  public AwesomeFontSample() {
    super(false, true);
  }

  @Override
  public void initUI() {
    int cols =
        (int)
            (Math.min(Settings.screenWidth, Settings.screenHeight) / (48 * Settings.screenDensity));

    Container c =
        new Container() {
          @Override
          public void initUI() {
            for (int i = 0, j = 0; i < MaterialIcons.values().length; i++, j++) {
              Icon icon = new Icon(MaterialIcons.values()[i]);
              add(
                  icon,
                  ((j % cols) == 0 ? LEFT : AFTER) + 10,
                  (j % cols) == 0 ? AFTER : SAME,
                  (int) (48 * Settings.screenDensity),
                  DP + 48);
              this.resizeHeight();
            }
          }
        };
    add(c, CENTER, TOP + 100, (int) (cols * 48 * Settings.screenDensity), WILL_RESIZE);
  }
}
