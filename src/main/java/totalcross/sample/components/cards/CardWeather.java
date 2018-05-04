package totalcross.sample.components.cards;

import totalcross.io.IOException;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.font.Font;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.ui.ImageControl;

public class CardWeather extends Container {

	private Container cont;
	private Container cont2;
	private ImageControl imgIcon;
	private Label lbValue;
	private Label lbText;
	
	public void initUI() {
		
		try {
			setBackColor(0xFFFFFF);
			setBorderStyle(BORDER_SIMPLE);
	        borderColor = 0xFFFFFF;
			
			cont = new Container();
			cont.setBackColor(0x215968);
			cont2 = new Container();
			
			add(cont, LEFT, TOP, PARENTSIZE+50, PARENTSIZE);
			add(cont2, RIGHT, TOP, PARENTSIZE+50, PARENTSIZE);
			
      imgIcon =
          new ImageControl(
              new Image("images/partly-cloudly.png")
                  .hwScaledFixedAspectRatio((int) (fmH * 5), true));
      lbValue = new Label("22°");
      lbValue.setFont(Font.getFont("Lato Medium", false, lbValue.getFont().size + 30));
      lbValue.setForeColor(0x215968);
      lbText = new Label("Partly Cloudly");
      lbText.setFont(Font.getFont("Lato Medium", false, lbText.getFont().size + 10));
      lbText.setForeColor(0x215968);

      cont.add(imgIcon, CENTER, CENTER, PREFERRED, PREFERRED);
      cont2.add(lbValue, CENTER, CENTER - 30, PREFERRED, PREFERRED);
      cont2.add(lbText, CENTER, AFTER, PREFERRED, PREFERRED);

    } catch (ImageException | IOException e) {
      e.printStackTrace();
    }
  }
}
