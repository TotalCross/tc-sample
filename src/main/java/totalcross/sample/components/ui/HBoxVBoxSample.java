package totalcross.sample.components.ui;

import totalcross.ui.Button;
import totalcross.ui.HBox;
import totalcross.ui.ScrollContainer;
import totalcross.ui.VBox;
import totalcross.ui.gfx.Color;

public class HBoxVBoxSample extends ScrollContainer {
	@Override
	public void initUI() {
		super.initUI();

		// Instanciando os btns
		Button btn1 = new Button("A");
		btn1.setBackColor(Color.BLUE);
		Button btn2 = new Button("B");
		btn2.setBackColor(Color.RED);
		Button btn3 = new Button("C");
		btn3.setBackColor(Color.MAGENTA);
		Button btn4 = new Button("D");
		btn4.setBackColor(Color.BLACK);
		Button btn5 = new Button("E");
		btn5.setBackColor(Color.DARK);

		//HBox hbox = new HBox();
		//add(hbox, LEFT, TOP, DP + 100, DP + 70);
        //
		//hbox.addControl(btn1);
		//hbox.addControl(btn2);

		VBox vbox = new VBox();
		add(vbox, LEFT, AFTER, DP + 120, DP + 200);
		
		vbox.suspendLayout();
		vbox.add(btn1);
		vbox.add(btn2);
		vbox.add(btn3);
		vbox.add(btn4);
		vbox.add(btn5);
		vbox.resumeLayout();
	}
}
