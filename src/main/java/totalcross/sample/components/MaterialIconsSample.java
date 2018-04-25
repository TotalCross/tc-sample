package totalcross.sample.components;

import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.icon.Icon;
import totalcross.ui.icon.MaterialIcons;

public class MaterialIconsSample extends ScrollContainer {
	static final int ICON_WIDTH = 64;

	  public MaterialIconsSample() {
	    super(false, true);
	  }
	  
	  public void initUI() {
		    int cols =
		        (int)
		            (Math.min(Settings.screenWidth, Settings.screenHeight)
		                / (ICON_WIDTH * Settings.screenDensity));

		    Container c =
		        new Container() {
		          @Override
		          public void initUI() {
		            for (int i = 0, j = 0; i < MaterialIcons.values().length; i++, j++) {
		              Icon icon = new Icon(MaterialIcons.values()[i]);
		              icon.setFont(icon.getFont().adjustedBy(10));
		              add(
		                  icon,
		                  (j % cols) == 0 ? LEFT : AFTER,
		                  (j % cols) == 0 ? AFTER : SAME,
		                  PARENTSIZE + (100 / cols),
		                  DP + ICON_WIDTH);
		            }
		            resizeHeight();
		          }
		        };
		    add(c, CENTER, TOP + 100, (int) (cols * ICON_WIDTH * Settings.screenDensity), WILL_RESIZE);
		  }
}
