package totalcross.sample.util;

import totalcross.ui.font.Font;
import totalcross.ui.gfx.Graphics;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Util {
	

	public static Image getAwesomeImage(char c, int height, int color) throws ImageException
	{
	    Font f = Font.getFont("FontAwesome", false, height);
	    Image img = new Image(f.fm.charWidth(c),height);
	    Graphics g = img.getGraphics();
	    g.foreColor = color;
	    g.setFont(f);
	    g.drawText(String.valueOf(c), 0,0);
	    return img;
	  }

}
