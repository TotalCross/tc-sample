package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.Ruler;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Slider;
import totalcross.ui.Spacer;
import totalcross.ui.Switch;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class SliderSwitchSample extends ScrollContainer implements PressListener {
	
	Slider mv, sfx, bgm, voice;
	Switch subtitles;
	Label lmv, lsfx, lbgm, lvoice, lsubtitles;
	Ruler line;
	Container c1;
	Spacer divider;
	
	@Override
	public void initUI() {
		try {
			super.initUI();
			
			
			
			add(new Spacer(), LEFT, TOP, 1, SCREENSIZE+10);
			add(divider = new Spacer(), CENTER, AFTER,SCREENSIZE+5 , SCREENSIZE+90);
			lmv = new Label("Master Volume:");
			add(lmv, LEFT + UnitsConverter.toPixels(DP + 15), TOP + UnitsConverter.toPixels(DP + 30),PREFERRED,PREFERRED);
			System.out.println(Settings.screenHeight + " " + Settings.screenHeight/10);
			mv = new Slider();
			mv.sliderColor = Color.getRGB(63,81,181);
			mv.setBackColor(Color.getRGB(195, 195, 195));;
			mv.setValue(50);
			add(mv, AFTER + UnitsConverter.toPixels(DP + 5), CENTER_OF, SCREENSIZE+40, PREFERRED);
			
			lsfx = new Label("SFX:", RIGHT);
			add(lsfx, LEFT + UnitsConverter.toPixels(DP + 15), AFTER + UnitsConverter.toPixels(DP + 5), lmv);
			sfx = new Slider();
			sfx.sliderColor = Color.getRGB(63,81,181);
			sfx.setBackColor(Color.getRGB(195, 195, 195));
			sfx.setValue(50);
			add(sfx, AFTER + UnitsConverter.toPixels(DP + 15), CENTER_OF , SCREENSIZE+40, PREFERRED);
			
			lbgm = new Label("BGM:");
			add(lbgm, LEFT + UnitsConverter.toPixels(DP + 15), AFTER + UnitsConverter.toPixels(DP + 5), lsfx);
			bgm = new Slider();
			bgm.sliderColor = Color.getRGB(63,81,181);
			bgm.setBackColor(Color.getRGB(195, 195, 195));
			bgm.setValue(50);
			add(bgm, AFTER + UnitsConverter.toPixels(DP + 15), CENTER_OF, SCREENSIZE+40, PREFERRED);
			
			lvoice = new Label("Voice:");
			add(lvoice, LEFT + UnitsConverter.toPixels(DP + 15), AFTER + UnitsConverter.toPixels(DP + 5), lbgm);
			voice = new Slider();
			voice.sliderColor = Color.getRGB(63,81,181);
			voice.setBackColor(Color.getRGB(195, 195, 195));
			voice.setValue(50);
			add(voice, AFTER + UnitsConverter.toPixels(DP + 15), CENTER_OF, SCREENSIZE+40, PREFERRED);
			
			lsubtitles = new Label("Subtitles:");
			add(lsubtitles, LEFT + UnitsConverter.toPixels(DP + 15), AFTER + UnitsConverter.toPixels(DP + 5), lvoice);
			subtitles = new Switch();
			subtitles.colorBallOn = Color.getRGB(0,150,136);
			subtitles.colorBarOn = Colors.P_700;
			subtitles.colorBallOn = Color.getRGB(241,241,241);
			subtitles.colorBarOff = Colors.P_200;
			subtitles.transparentBackground=true;
			add(subtitles, AFTER + UnitsConverter.toPixels(DP + 15), CENTER_OF, PREFERRED, PREFERRED);
			add(new Spacer(), LEFT, AFTER, 1, SCREENSIZE+10);
			
			
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}

	}

	@Override
	public void controlPressed(ControlEvent e) {
		// TODO Auto-generated method stub
		
	}

}