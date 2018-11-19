package totalcross.sample.util;

import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;

public class Palette extends ScrollContainer{
	private final int gap = 50;
	@Override
	public void initUI() {
		setBackColor(Colors.BACKGROUND);
		add(new Label("Primary color and variants: ", LEFT, Colors.ON_BACKGROUND, true), LEFT + gap, TOP + gap);
		for(int i = 0; i < 10; i++) {
			Container prim = new Container();
			String text;
			switch (i) {
				case 0:
					prim.setBackForeColors(Colors.P_050, Colors.ON_P_050);
					text = "50";
					break;
				case 1:
					prim.setBackForeColors(Colors.P_100, Colors.ON_P_100);
					text = "100";
					break;
				case 2:
					prim.setBackForeColors(Colors.P_200, Colors.ON_P_200);
					text = "200";
					break;
				case 3:
					prim.setBackForeColors(Colors.P_300, Colors.ON_P_300);
					text = "300";
					break;
				case 4:
					prim.setBackForeColors(Colors.P_400, Colors.ON_P_400);
					text = "400";
					break;
				case 5:
					prim.setBackForeColors(Colors.P_500, Colors.ON_P_500);
					text = "500P";
					break;
				case 6:
					prim.setBackForeColors(Colors.P_600, Colors.ON_P_600);
					text = "600";
					break;
				case 7:
					prim.setBackForeColors(Colors.P_700, Colors.ON_P_700);
					text = "700";
					break;
				case 8:
					prim.setBackForeColors(Colors.P_800, Colors.ON_P_800);
					text = "800";
					break;
				case 9:
					prim.setBackForeColors(Colors.P_900, Colors.ON_P_900);
					text = "900";
					break;	
				default:
					text = "null";
					break;
			}
			if(i == 0)
				add(prim, LEFT + gap, AFTER + gap, 50 + DP, 50 + DP);
			else if(i == 5)
				add(prim, LEFT + gap, AFTER, 50 + DP, 50 + DP);
			else
				add(prim, AFTER, SAME, 50 + DP, 50 + DP);
			prim.add(new Label(text, CENTER), CENTER, CENTER);
		}
		
		add(new Label("Secondary color and variants: ", LEFT, Colors.ON_BACKGROUND, true), LEFT + gap, AFTER + gap);
		
		for(int i = 0; i < 10; i++) {
			Container sec = new Container();
			String text;
			switch (i) {
				case 0:
					sec.setBackForeColors(Colors.S_050, Colors.ON_S_050);
					text = "50";
					break;
				case 1:
					sec.setBackForeColors(Colors.S_100, Colors.ON_S_100);
					text = "100";
					break;
				case 2:
					sec.setBackForeColors(Colors.S_200, Colors.ON_S_200);
					text = "200";
					break;
				case 3:
					sec.setBackForeColors(Colors.S_300, Colors.ON_S_300);
					text = "300";
					break;
				case 4:
					sec.setBackForeColors(Colors.S_400, Colors.ON_S_400);
					text = "400";
					break;
				case 5:
					sec.setBackForeColors(Colors.S_500, Colors.ON_S_500);
					text = "500";
					break;
				case 6:
					sec.setBackForeColors(Colors.S_600, Colors.ON_S_600);
					text = "600";
					break;
				case 7:
					sec.setBackForeColors(Colors.S_700, Colors.ON_S_700);
					text = "700";
					break;
				case 8:
					sec.setBackForeColors(Colors.S_800, Colors.ON_S_800);
					text = "800S";
					break;
				case 9:
					sec.setBackForeColors(Colors.S_900, Colors.ON_S_900);
					text = "900";
					break;	
				default:
					text = "null";
					break;
			}
			if(i == 0)
				add(sec, LEFT + gap, AFTER + gap, 50 + DP, 50 + DP);
			else if(i == 5)
				add(sec, LEFT + gap, AFTER, 50 + DP, 50 + DP);
			else
				add(sec, AFTER, SAME, 50 + DP, 50 + DP);
			
			sec.add(new Label(text, CENTER), CENTER, CENTER);
		}
		
		add(new Label("Background color: ", LEFT, Colors.ON_BACKGROUND, true), LEFT + gap, AFTER + gap);
		Container backC = new Container();
		backC.setBackColor(Colors.BACKGROUND);
		backC.setBorderStyle(BORDER_SIMPLE);
		add(backC, LEFT + gap, AFTER + gap, 50 + DP, 50 + DP);
		backC.add(new Label("Fore", CENTER, Colors.ON_BACKGROUND, false), CENTER, CENTER);
		
		add(new Label("Surface color: ", LEFT, Colors.ON_BACKGROUND, true), LEFT + gap, AFTER + gap);
		Container surfC = new Container();
		surfC.setBackColor(Colors.SURFACE);
		surfC.setBorderStyle(BORDER_SIMPLE);
		add(surfC, LEFT + gap, AFTER + gap, 50 + DP, 50 + DP);
		surfC.add(new Label("Fore", CENTER, Colors.ON_SURFACE, false), CENTER, CENTER);
		
		add(new Label("Error color: ", LEFT, Colors.ON_BACKGROUND, true), LEFT + gap, AFTER + gap);
		Container errorC = new Container();
		errorC.setBackColor(Colors.ERROR);
		errorC.setBorderStyle(BORDER_SIMPLE);
		add(errorC, LEFT + gap, AFTER + gap, 50 + DP, 50 + DP);
		errorC.add(new Label("Fore", CENTER, Colors.ON_ERROR, false), CENTER, CENTER);
		
		add(new Spacer(), LEFT, AFTER, 0, gap/3);
	}
}
