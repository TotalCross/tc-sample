package totalcross.sample.components;

import totalcross.io.IOException;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Home extends Container {
	
	private int GAP = fmH*10;
	
	@Override
	public void initUI()
	{
		
		try {
			
			ImageControl ic = new ImageControl(new Image("images/logo.png"));
			ic.scaleToFit = true;
			ic.centerImage = true;
			
			Label lbWelcome = new Label("Welcome to TotalCross!");
            lbWelcome.setFont(lbWelcome.getFont().asBold());

	        add(ic, CENTER, AFTER+GAP, SCREENSIZE + 54, SCREENSIZE + 40);
	        add(lbWelcome, CENTER, AFTER);
	        
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
        
	}

}
