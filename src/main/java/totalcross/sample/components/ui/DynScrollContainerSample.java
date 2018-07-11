package totalcross.sample.components.ui;

import tc.samples.api.ui.DynSCTestView;
import totalcross.sys.Convert;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.DynamicScrollContainer;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.ProgressBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
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
