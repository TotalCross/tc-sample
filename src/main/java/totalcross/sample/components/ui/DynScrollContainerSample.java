package totalcross.sample.components.ui;

import totalcross.sys.Convert;
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
	final int gap = 5;
	private Button goButton;
	private Edit evenHeightEdit;
	private Edit oddHeightEdit;
	private Edit rowCountEdit;
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
					text += "Felis turpis mauris per nullam donec lobortis maecenas metus orci viverra vulputate non nostra platea conubia, lectus proin mauris ligula aenean, adipiscing purus accumsan commodo laoreet facilisis tellus nisl litora vehicula.";

				} else if (id % 3 == 0) {
					text += "Venenatis consequat adipiscing ullamcorper etiam tellus nam mattis vehicula nostra leo rutrum lorem at suscipit, suspendisse aliquam aptent netus nisi rutrum fringilla arcu vehicula ut ante nam in.";

				} else if (id % 2 == 0) {
					text += "Hendrerit fermentum blandit conubia fringilla class duis non neque blandit condimentum, maecenas neque vel justo magna ornare tempor purus quis porttitor quisque, et nullam turpis ullamcorper a donec consequat auctor ornare.";

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
			ui.setBackColor((id % 2 == 0) ? Color.darker(Color.YELLOW, 32) : Color.darker(Color.GREEN, 32));
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
		add(new Label(text), LEFT, AFTER, PARENTSIZE + 80, PREFERRED);
		Edit ed = new Edit();
		ed.setKeyboard(Edit.KBD_NUMERIC);
		add(ed, RIGHT, SAME, PARENTSIZE + 20, PREFERRED);
		return ed;
	}

	@Override
	public void initUI() {
		super.initUI();
		add(new Spacer(0, 0), LEFT, TOP + gap); // reset after position
		rowCountEdit = add("Number of rows to create: ");
		oddHeightEdit = add("Odd view height:");
		evenHeightEdit = add("Even view height:");

		dynamicChk = new Check("Dynamic height");
		add(dynamicChk, LEFT, AFTER);

		goButton = new Button("Generate");
		goButton.setBackColor(Color.GREEN);
		add(goButton, RIGHT, SAME);

		vsc = new DynamicScrollContainer();
		vsc.setBackColor(Color.WHITE);
		vsc.setBorderStyle(BORDER_SIMPLE);
		add(vsc, LEFT, AFTER + gap, FILL, FILL - 1);

		rowCountEdit.setText(String.valueOf(rowCount0 = 2000));
		oddHeightEdit.setText(String.valueOf(oddHeight0 = fmH));
		evenHeightEdit.setText(String.valueOf(evenHeight0 = fmH * 3 / 2));
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
				oddHeightEdit.setText(oddHeight + "");
			}
			try {
				evenHeight = Convert.toInt(evenHeightEdit.getText());
			} catch (Exception e) {
				evenHeightEdit.setText(evenHeight + "");
			}

			ProgressBox pb = new ProgressBox("Generating", "Creating datasource, please wait...", null);
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

		}
	}
}
