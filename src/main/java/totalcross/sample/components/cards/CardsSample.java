package totalcross.sample.components.cards;

import totalcross.ui.Container;

public class CardsSample extends Container {
	
	public void initUI() {
		setBackColor(0xDCDCDC);
		
		add(new CardSearch(),CENTER,TOP+50,PARENTSIZE+96,PARENTSIZE+10);
		add(new CardProfile(),CENTER,AFTER+50,PARENTSIZE+96,PARENTSIZE+40);
		add(new CardWeather(),CENTER,AFTER+50,PARENTSIZE+96,PARENTSIZE+30);	
		add(new CardSocial(),CENTER,AFTER+50,PARENTSIZE+96,PARENTSIZE+10);
	}

}
