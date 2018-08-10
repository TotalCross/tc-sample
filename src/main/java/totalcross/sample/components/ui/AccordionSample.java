package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.AccordionContainer;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.MultiEdit;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Toast;
import totalcross.ui.font.Font;

public class AccordionSample extends ScrollContainer {
	private int gap = (int) (Settings.screenDensity * 20);
	
	@Override
	public void initUI() {
		setScrollBars(false, true);
		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		
		Label order = new Label("This is a quiz about Brazil! Good luck!", CENTER);
		order.autoSplit = true;
		order.setFont(order.getFont().adjustedBy(2, true));
		
		add(order, LEFT + gap, TOP + gap*3, FILL - gap, PREFERRED);
		
		Label question = new Label("Which is...");
		question.setFont(question.getFont().adjustedBy(3, true));
		add(question, CENTER, AFTER + gap*3);
		
		AccordionContainer.Group gr = new AccordionContainer.Group();
		AccordionContainer ac[] = new AccordionContainer[5];
		MultiEdit me[] = new MultiEdit[5];
		
		for(int i = 0; i < ac.length; i ++) {
			ac[i] = new AccordionContainer(gr);
			ac[i].setFont(font.asBold());
			me[i] = new MultiEdit(50, gap/4);
			me[i].setText("Type here!");
		}
		
		add(ac[0], CENTER, AFTER + gap*2, SCREENSIZE + 85, PREFERRED);
		ac[0].setBackForeColors(Colors.P_100, Colors.ON_P_100);
		ac[0].add(ac[0].new Caption("...the biggest state?"), LEFT, TOP, FILL, PREFERRED);
		ac[0].add(me[0], LEFT + gap*4, AFTER + gap, FILL, FONTSIZE + 600);
		me[0].setFont(Font.getFont(false, this.getFont().size));
		
		for(int i = 1; i < ac.length; i++) {
			add(ac[i], CENTER, AFTER + gap/2, SCREENSIZE + 85, PREFERRED);
			ac[i].setBackForeColors(Colors.P_100, Colors.ON_P_100);
			switch (i) {
			case 1:
				ac[i].add(ac[i].new Caption("...the most famous forest?"), LEFT, TOP, FILL, PREFERRED);
				break;
			case 2:
				ac[i].add(ac[i].new Caption("...the current president?"), LEFT, TOP, FILL, PREFERRED);
				break;
			case 3:
				ac[i].add(ac[i].new Caption("...the most famous river?"), LEFT, TOP, FILL, PREFERRED);
				break;
			case 4:
				ac[i].add(ac[i].new Caption("...the most famous statue?"), LEFT, TOP, FILL, PREFERRED);
				break;
			}
			ac[i].add(me[i], LEFT + gap*4, AFTER + gap, FILL, FONTSIZE + 600);
			me[i].setFont(Font.getFont(false, ac[i].getFont().size));
		}
		
		
		Button confirm = new Button("SUBMIT");
		confirm.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		confirm.setEnabled(false);
		add(confirm, CENTER, BOTTOM - gap*3, PREFERRED + 120, PREFERRED + 60);
		
		Label warning = new Label("Please, answer all the questions to enable the button!", CENTER);
		warning.autoSplit = true;
		warning.setForeColor(Colors.S_600);
		add(warning, LEFT + gap, BEFORE - gap*2, FILL - gap, PREFERRED, confirm);
		warning.reposition();

		confirm.addPressListener((e) -> {
			Toast.show("The form was sent!\nAnswers erased.", 2000);
			for (MultiEdit m : me)
				m.setText("Type here!");

			confirm.setEnabled(false);
			gr.collapseAll();
		});
		
		addTimer(3000);
		
		addTimerListener((e) -> {
			boolean changed = true;
			for (MultiEdit m : me) {
				if(m.getText().equals("Type here!"))
					changed = false;
			}
			if(changed)
				confirm.setEnabled(true);
		});
	}
}
