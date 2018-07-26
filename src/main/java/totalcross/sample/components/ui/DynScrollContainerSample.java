package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.DynamicScrollContainer;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.ProgressBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

public class DynScrollContainerSample extends Container {
	final int gap = 50;
	private Button goButton;
	private Edit evenHeightEdit;
	private Edit oddHeightEdit;
	private Edit rowCountEdit;
	String oldOdd, oldEven;
	private DynamicScrollContainer vsc;
	private Check dynamicChk;
	private int rowCount0, oddHeight0, evenHeight0;

	public static class DynSCTestView extends DynamicScrollContainer.AbstractView {
		public static boolean dynamicHeight;

		private int id;

		public String text;

		private Font f;

		public DynSCTestView(int id, Font f) {
			super();
			this.f = f;
			this.id = id;
		}

		@Override
		public int getHeight() {
			text = "Row id: " + id + "\n\n";
			if (dynamicHeight) {
				/*
				 * if your views height can only be determined on rendering then you will need
				 * to work out the height dynamically based on font, line wrapping etc
				 */
				if (id % 5 == 0) {
					text += "Lorem ipsum pretium class porta gravida lobortis ipsum, etiam posuere elit torquent duis nostra elit, sagittis etiam dapibus libero tellus facilisis curabitur facilisis sed sagittis posuere imperdiet bibendum.";

				} else if (id % 4 == 0) {
					text += "Felis turpis mauris per nullam donec lobortis maecenas metus orci viverra vulputate non nostra platea conubia, lectus proin mauris ligula aenean, adipiscing purus accumsan commodo laoreet facilisis tellus nisl litora vehicula. Aliquam aptent netus nisi rutrum fringilla arcu vehicula ut ante nam in.";

				} else if (id % 3 == 0) {
					text += "Venenatis consequat adipiscing ullamcorper etiam tellus nam mattis vehicula nostra leo rutrum lorem at suscipit, suspendisse aliquam aptent netus nisi rutrum fringilla arcu vehicula ut ante nam in.";

				} else if (id % 2 == 0) {
					text += "Hendrerit fermentum blandit conubia fringilla class duis non neque blandit condimentum, maecenas neque vel justo magna ornare tempor purus quis porttitor quisque.";

				} else {
					text += "Lorem ipsum pretium class porta gravida lobortis ipsum, etiam posuere elit torquent.";
				}

				int lines = Convert.numberOf(Convert.insertLineBreak(parentWidth - 10, f.fm, text), '\n') + 1;
				height = (f.fm.height * lines) + Edit.prefH;
				return height;
			} else {
				return height;
			}
		}

		@Override
		public void initUI() {
			Container ui = new Container();
			ui.setBackColor((id % 2 == 0) ? Color.getRGB(245, 245, 245) : Color.WHITE);
			ui.setRect(0, yStart, parentWidth, height);
			try {
				Label l = new Label(Convert.insertLineBreak(parentWidth - 10, f.fm, text));
				ui.add(l, Control.LEFT, Control.TOP, Control.FILL, Control.FILL);
			} catch (Exception e) {
				e.printStackTrace();
				// MessageBox.showException(e, true);
			}
			this.c = ui;
		}
	}

	private Edit add(String text) {
		add(new Label(text), LEFT + gap, AFTER, PREFERRED, PREFERRED);
		Edit ed = new Edit();
		ed.setKeyboard(Edit.KBD_NUMERIC);
		add(ed, RIGHT - gap, SAME, SCREENSIZE + 25, PREFERRED);
		return ed;
	}

	@Override
	public void initUI() {
		super.initUI();
		add(new Spacer(0, 0), LEFT, TOP + gap); // reset after position
		rowCountEdit = add("Number of rows to create: ");
		add(new Spacer(0, 0), LEFT, AFTER + gap/2);
		oddHeightEdit = add("Odd view height:");
		add(new Spacer(0, 0), LEFT, AFTER + gap/2);
		evenHeightEdit = add("Even view height:");
		
		dynamicChk = new Check("Dynamic height");
		add(dynamicChk, LEFT + gap, AFTER + gap + gap/2);

		goButton = new Button("Generate");
		goButton.setBackForeColors(Colors.P_DARK, Color.WHITE);
		add(goButton, RIGHT - gap, AFTER + gap, SCREENSIZE + 25, PREFERRED + gap, evenHeightEdit);

		vsc = new DynamicScrollContainer();
		vsc.setBackColor(Color.WHITE);
		vsc.setBorderStyle(BORDER_SIMPLE);
		add(vsc, LEFT + gap, AFTER + gap*2, FILL - gap, FILL - gap*2);

		rowCountEdit.setText(String.valueOf(rowCount0 = 30));
		oddHeightEdit.setText(String.valueOf(oddHeight0 = fmH * 2));
		evenHeightEdit.setText(String.valueOf(evenHeight0 = fmH * 2));
	}

	@Override
	public void onEvent(Event event) {
		if (event.type == ControlEvent.PRESSED && event.target == goButton) {
			int rowCount = rowCount0;
			int oddHeight = oddHeight0;
			int evenHeight = evenHeight0;
			try {
				rowCount = Convert.toInt(rowCountEdit.getText());
			} catch (Exception e) {
				rowCountEdit.setText(rowCount + "");
			}
			try {
				oddHeight = Convert.toInt(oddHeightEdit.getText());
			} catch (Exception e) {
				if(!dynamicChk.isChecked())
					oddHeightEdit.setText(oddHeight + "");
			}
			try {
				evenHeight = Convert.toInt(evenHeightEdit.getText());
			} catch (Exception e) {
				if(!dynamicChk.isChecked())
					evenHeightEdit.setText(evenHeight + "");
			}

			ProgressBox pb = new ProgressBox("Generating", "Creating datasource, please wait...", null);
			pb.setBackColor(Color.getRGB(12, 98, 200));
			pb.popupNonBlocking();
			DynamicScrollContainer.DataSource datasource = new DynamicScrollContainer.DataSource(rowCount);

			for (int i = 0; i < rowCount; i++) {
				DynSCTestView view = new DynSCTestView(i, font);
				view.height = i % 2 == 0 ? evenHeight : oddHeight;
				datasource.addView(view);
			}

			pb.unpop();
			vsc.setDataSource(datasource);
			vsc.scrollToView(datasource.getView(0));
		}
		if (event.type == ControlEvent.PRESSED && event.target == dynamicChk) {
			DynSCTestView.dynamicHeight = dynamicChk.isChecked();
			if(dynamicChk.isChecked()) {
				oddHeightEdit.setEditable(false);
				oldOdd = oddHeightEdit.getText();
				oddHeightEdit.setText("AUTO");
				evenHeightEdit.setEditable(false);
				oldEven = evenHeightEdit.getText();
				evenHeightEdit.setText("AUTO");
			}else {
				oddHeightEdit.setEditable(true);
				oddHeightEdit.setText(oldOdd);
				evenHeightEdit.setEditable(true);
				evenHeightEdit.setText(oldEven);
			}
		}
	}
}
