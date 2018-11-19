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

public class SliderSwitchSample extends ScrollContainer implements PressListener {
	
	Slider mv, sfx, bgm, voice;
	Switch subtitles;
	Label lmv, lsfx, lbgm, lvoice, lsubtitles;
	int hGap, vGap;
	Ruler line;
	Container c1;
	Spacer divider;
	
	@Override
	public void reposition() {
		
		if(Settings.isLandscape()) {
			hGap = Settings.screenHeight/6;
			vGap = Settings.screenWidth/10;
		}else {
			hGap = Settings.screenHeight/10;
			vGap = Settings.screenWidth/6;
		}
		super.reposition();
		
	}

	@Override
	public void initUI() {
		try {
			super.initUI();
			
			
			if(!Settings.isLandscape()) {
				hGap = Settings.screenWidth/6;
				vGap = Settings.screenHeight/10;
			}else {
				hGap = Settings.screenWidth/10;
				vGap = Settings.screenHeight/6;
			}
			
			add(new Spacer(), LEFT, TOP, 1, SCREENSIZE+10);
			add(divider = new Spacer(), CENTER, AFTER,SCREENSIZE+5 , SCREENSIZE+90);
			add(lmv =new Label("Master Volume:"), BEFORE, TOP + 200,PREFERRED,PREFERRED);
			System.out.println(Settings.screenHeight + " " + Settings.screenHeight/10);
			mv = new Slider();
			mv.sliderColor = Color.getRGB(63,81,181);
			mv.setBackColor(Color.getRGB(195, 195, 195));;
			mv.setValue(50);
			add(mv, RIGHT - hGap,  SAME+lmv.getHeight()/2, SCREENSIZE+40, PREFERRED);
			
			add(lsfx = new Label("SFX:", RIGHT),BEFORE, TOP + 400,PREFERRED,PREFERRED, divider);
			sfx = new Slider();
			sfx.sliderColor = Color.getRGB(63,81,181);
			sfx.setBackColor(Color.getRGB(195, 195, 195));
			sfx.setValue(50);
			add(sfx, RIGHT-hGap, SAME+lsfx.getHeight()/2, SCREENSIZE+40, PREFERRED);
			
			add(lbgm = new Label("BGM:"),BEFORE, TOP+600,PREFERRED,PREFERRED, divider);
			bgm = new Slider();
			bgm.sliderColor = Color.getRGB(63,81,181);
			bgm.setBackColor(Color.getRGB(195, 195, 195));
			bgm.setValue(50);
			add(bgm, RIGHT-hGap, SAME+lsfx.getHeight()/2, SCREENSIZE+40, PREFERRED);
			
			add(lvoice = new Label("Voice:"),BEFORE, TOP+800,PREFERRED,PREFERRED, divider);
			voice = new Slider();
			voice.sliderColor = Color.getRGB(63,81,181);
			voice.setBackColor(Color.getRGB(195, 195, 195));
			voice.setValue(50);
			add(voice, RIGHT-hGap, SAME+lsfx.getHeight()/2, SCREENSIZE+40, PREFERRED);
			
			add(lsubtitles = new Label("Subtitles:"),BEFORE, TOP+1000,PREFERRED,PREFERRED, divider);
			subtitles = new Switch();
			subtitles.colorBallOn = Color.getRGB(0,150,136);
			subtitles.colorBarOn = Colors.P_700;
			subtitles.colorBallOn = Color.getRGB(241,241,241);
			subtitles.colorBarOff = Colors.P_200;
			subtitles.transparentBackground=true;
			add(subtitles, RIGHT-hGap, SAME+lsfx.getHeight()/2, PREFERRED, PREFERRED);
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