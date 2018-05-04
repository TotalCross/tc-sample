package totalcross.sample.components.cards;

import totalcross.io.IOException;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class CardSocial extends Container {
	
	private Button btFacebook;
	private Button btInstagram;
	private Button btTwitter;
	
	public void initUI() {
		
		setBackColor(0xFFFFFF);
		setBorderStyle(BORDER_SIMPLE);
        borderColor = 0xFFFFFF;
		
		try {
      btFacebook = new Button(new Image("images/fb_icon_40.png"));
      btFacebook.setBorder(Button.BORDER_NONE);
      btFacebook.imageHeightFactor = 50;
      btInstagram = new Button(new Image("images/insta_icon_40.png"));
      btInstagram.setBorder(Button.BORDER_NONE);
      btInstagram.imageHeightFactor = 50;
      btTwitter = new Button(new Image("images/tt_icon_40.png"));
      btTwitter.setBorder(Button.BORDER_NONE);
      btTwitter.imageHeightFactor = 50;

      add(btInstagram, CENTER, CENTER, PREFERRED, PREFERRED);
      add(
          new Container() {
            @Override
            public void initUI() {
              add(btFacebook, CENTER, CENTER);
            }
          },
          LEFT,
          TOP,
          FIT,
          FILL);
      add(
          new Container() {
            @Override
            public void initUI() {
              add(btTwitter, CENTER, CENTER);
            }
          },
          AFTER,
          TOP,
          FILL,
          FILL,
          btInstagram);
    } catch (ImageException | IOException e) {
      e.printStackTrace();
    }
  }
}
