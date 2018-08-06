package totalcross.sample.components;

import totalcross.io.IOException;
import totalcross.sample.util.Colors;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Home extends Container {
	@Override
	public void initUI() {
		try {
			setBackColor(Colors.PRIMARY);

			ImageControl ic = new ImageControl(new Image("images/logoV.png"));
			ic.scaleToFit = true;
			ic.centerImage = true;
			add(ic, CENTER, AFTER + 250, PARENTSIZE, PARENTSIZE + 20);

			Label lbWelcome = new Label("Welcome!");
			lbWelcome.setFont(Font.getFont("Lato Light", false, lbWelcome.getFont().size + 30));
			lbWelcome.setForeColor(Color.WHITE);
			add(lbWelcome, CENTER, AFTER + 40);

			Label lbWelcome3 = new Label("Use the menu on the top left\n     to see the components");
			lbWelcome3.setFont(Font.getFont("Lato Bold", false, lbWelcome3.getFont().size));
			lbWelcome3.setForeColor(Color.WHITE);
			add(lbWelcome3, CENTER, AFTER + 40);

		} catch (IOException | ImageException e) {
			e.printStackTrace();
		}
	}
}
