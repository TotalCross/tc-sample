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
			
			btFacebook = new Button(new Image("images/fb_icon_40.png").hwScaledFixedAspectRatio((int)(fmH*1.5), true));
			btFacebook.setBorder(Button.BORDER_NONE);
			btInstagram = new Button(new Image("images/insta_icon_40.png").hwScaledFixedAspectRatio((int)(fmH*1.5), true));
			btInstagram.setBorder(Button.BORDER_NONE);
			btTwitter = new Button(new Image("images/tt_icon_40.png").hwScaledFixedAspectRatio((int)(fmH*1.5), true));
			btTwitter.setBorder(Button.BORDER_NONE);
			
			add(btInstagram, CENTER, CENTER, PREFERRED, PREFERRED);
			add(btFacebook, BEFORE-fmH*18, CENTER, PREFERRED, PREFERRED);
			add(btTwitter, AFTER+fmH*18, CENTER, PREFERRED, PREFERRED, btInstagram);
			
		} catch (ImageException | IOException e) {
			e.printStackTrace();
		} 
	}

}