package totalcross.sample.components.ui;

import totalcross.io.IOException;
import totalcross.sample.components.BaseScreen;
import totalcross.sys.Settings;
import totalcross.ui.FloatingActionButton;
import totalcross.ui.ScrollContainer;
import totalcross.ui.gfx.Color;
import totalcross.ui.icon.Icon;
import totalcross.ui.icon.MaterialIcons;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class MaterialIconsSample extends BaseScreen {
	static final int ICON_WIDTH = 64;


	public MaterialIconsSample() {
		super("https://totalcross.gitbook.io/playbook/components/material-icons");
	}

	public void onContent(ScrollContainer content) {
		int cols = (int) (Math.min(Settings.screenWidth, Settings.screenHeight)
				/ (ICON_WIDTH * Settings.screenDensity));

		for (int i = 0, j = 0; i < MaterialIcons.values().length; i++, j++) {
			Icon icon = new Icon(MaterialIcons.values()[i]);
			icon.setFont(icon.getFont().adjustedBy(10));
			content.add(icon, (j % cols) == 0 ? LEFT : AFTER, (j % cols) == 0 ? AFTER : SAME, PARENTSIZE + (100 / cols),
					DP + ICON_WIDTH);
		}

		Image ic = null;
		try {
			ic = new Image("images/floatbtn.png");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
		FloatingActionButton floatbutton = new FloatingActionButton(ic);
		floatbutton.setBackColor(Color.getRGB(109, 156, 232));
		floatbutton.setIconSize(30);
		floatbutton.addPressListener(e -> {
			content.scrollToOrigin();
		});
		content.add(floatbutton, RIGHT - 40, BOTTOM - 40);
	}
}
