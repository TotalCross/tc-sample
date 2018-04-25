package totalcross.sample.components.cards;

import totalcross.io.IOException;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class CardSearch extends Container {

	private Edit edtSearch;
	private Button btSearch;
	
	public void initUI() {
		
		try {
			
			setBackColor(0xFFFFFF);
			setBorderStyle(BORDER_SIMPLE);
	        borderColor = 0xFFFFFF;
			
			edtSearch = new Edit();
			edtSearch.caption = "Search";
			
			btSearch = new Button(new Image("images/microphone.png").hwScaledFixedAspectRatio((int)(fmH*1.5), true));
			btSearch.setBorder(Button.BORDER_NONE);
			
			add(edtSearch, LEFT+100, TOP, PARENTSIZE+80, PARENTSIZE+80);
			add(btSearch, AFTER+50, TOP+50, PREFERRED, PREFERRED);
			
		} catch (ImageException | IOException e) {
			e.printStackTrace();
		} 
	}
}