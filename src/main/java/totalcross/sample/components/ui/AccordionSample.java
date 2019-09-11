package totalcross.sample.components.ui;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.AccordionContainer;
import totalcross.ui.Button;
import totalcross.ui.Control;
import totalcross.ui.Label;
import totalcross.ui.MultiEdit;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Toast;
import totalcross.ui.font.Font;
import totalcross.util.UnitsConverter;

public class AccordionSample extends BaseScreen {
	private int gap = UnitsConverter.toPixels(Control.DP + 20);

	public AccordionSample() {
		super("https://totalcross.gitbook.io/playbook/components/accordion-container");
	}

	@Override
	public void onContent(ScrollContainer content) {
		content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		content.setInsets(0, 0, 0, UnitsConverter.toPixels(Control.DP + 8));

		Label order = new Label("This is a quiz about Brazil! Good luck!", CENTER);
		order.autoSplit = true;
		order.setFont(order.getFont().adjustedBy(2, true));

		content.add(order, LEFT + gap, TOP + gap, FILL - gap, PREFERRED);

		Label question = new Label("Which is...");
		question.setFont(question.getFont().adjustedBy(3, true));
		content.add(question, CENTER, AFTER + gap);

		AccordionContainer.Group gr = new AccordionContainer.Group();
		AccordionContainer ac[] = new AccordionContainer[5];
		MultiEdit me[] = new MultiEdit[5];

		for(int i = 0; i < ac.length; i ++) {
			ac[i] = new AccordionContainer(gr);
			ac[i].setFont(font.asBold());
			me[i] = new MultiEdit(50, gap/4);
			me[i].setText("Type here!");
		}

		content.add(ac[0], CENTER, AFTER + gap/2, SCREENSIZE + 85, PREFERRED);
		ac[0].setBackForeColors(Colors.P_100, Colors.ON_P_100);
		ac[0].add(ac[0].new Caption("...the biggest state?"), LEFT, TOP, FILL, PREFERRED);
		ac[0].add(me[0], LEFT, AFTER + gap, FILL, FONTSIZE + 600);
		me[0].setFont(Font.getFont(false, this.getFont().size));

		for(int i = 1; i < ac.length; i++) {
			content.add(ac[i], CENTER, AFTER + UnitsConverter.toPixels(Control.DP + 2), SCREENSIZE + 85, PREFERRED);
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
			ac[i].add(me[i], LEFT, AFTER + gap, FILL, FONTSIZE + 600);
			me[i].setFont(Font.getFont(false, ac[i].getFont().size));
		}

		Label warning = new Label("Please, answer all the questions to enable the button!", CENTER);
		warning.autoSplit = true;
		warning.setForeColor(Colors.S_600);
		content.add(warning, LEFT + gap, AFTER + gap/2, FILL - gap, PREFERRED);

		Button confirm = new Button("SUBMIT");
		confirm.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		confirm.setEnabled(false);
		content.add(confirm, CENTER, AFTER + gap/2, confirm.getPreferredWidth() <= 32 ? DP + 64 : confirm.getPreferredWidth() + (int) Settings.screenDensity*100*32/100, DP + 36);

		confirm.addPressListener((e) -> {
			Toast.show("The form was sent!\nAnswers erased.", 2000);
			for (MultiEdit m : me)
				m.setText("Type here!");

			confirm.setEnabled(false);
			gr.collapseAll();
		});

		content.addTimer(3000);

		content.addTimerListener((e) -> {
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
