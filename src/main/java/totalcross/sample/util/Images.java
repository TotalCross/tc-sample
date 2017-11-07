package totalcross.sample.util;

import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Images {
	
	public static Image getImageBlack(Image img){
		try {
			Image image = img.getCopy();
			image.applyColor2(Color.BLACK);
			return image;
		} catch (ImageException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image getImageRed(Image img){
		try {
			Image image = img.getCopy();
			image.applyColor2(Colors.RED);
			return image;
		} catch (ImageException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image getImageGray(Image img){
		try {
			Image image = img.getCopy();
			image.applyColor2(Colors.GRAY);
			return image;
		} catch (ImageException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image getImageBlue(Image img){
		try {
			Image image = img.getCopy();
			image.applyColor2(Colors.BLUE);
			return image;
		} catch (ImageException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image aplyColor(Image img, int color){
		try {
			Image image = img.getCopy();
			image.applyColor2(color);
			return image;
		} catch (ImageException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
