package totalcross.sample.components.ui;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.AlignedLabelsContainer;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ListBox;
import totalcross.ui.ScrollContainer;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class AlignedLabelsSample extends BaseScreen {
	private int gap = UnitsConverter.toPixels(10 + DP);
	private boolean canInsert = true;
	private ListBox lb;
	private Label output;

	public AlignedLabelsSample() {
		super("https://totalcross.gitbook.io/playbook/components/aligned-labels-container");
	}

	@Override
	public void onContent(ScrollContainer content) {
		content.uiAdjustmentsBasedOnFontHeightIsSupported = false;
		content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);

		String[] labels = { "Name", "Born date", "Telephone", "Address", "City", "Country"};
		Edit edits[] = new Edit[5];
		Edit.useNativeNumericPad = true;
		for (int i = 0; i < edits.length; i++) {
			switch (i) {
				case 1:
					edits[i] = new Edit("99/99/9999");
					edits[i].setMode(Edit.NORMAL, true);
					edits[i].setValidChars(Edit.numbersSet);
					edits[i].setKeyboard(Edit.KBD_NUMERIC);
					break;
				case 2:
					edits[i] = new Edit("(99)9999-99999");
					edits[i].setMode(Edit.NORMAL, true);
					edits[i].setValidChars(Edit.numbersSet);
					edits[i].setKeyboard(Edit.KBD_NUMERIC);
					break;
				default:
					edits[i] = new Edit();
			}
		}


		Label title = new Label("This is an AlignedLabelsContainer.\nAll the content will be automatically aligned.",
				CENTER, 0, true);
		title.autoSplit = true;
		content.add(title, LEFT + gap, TOP + gap, FILL - gap, PREFERRED);

		AlignedLabelsContainer alc = new AlignedLabelsContainer();
		alc.uiAdjustmentsBasedOnFontHeightIsSupported = false;
		alc.labelAlign = RIGHT;

		alc.setInsets(gap, gap, 0, 0);
		alc.setLabels(labels, edits[0].getPreferredHeight());
		content.add(alc, LEFT, AFTER, FILL, PREFERRED);
		int i;
		for (i = 0; i < edits.length - 1; i++) {
			alc.add(edits[i], LEFT + gap, alc.getLineY(i), FILL - gap, PREFERRED);
		}

		Button btnInsert = new Button("Insert data", (byte) 0);
		btnInsert.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		alc.add(edits[edits.length - 1], LEFT + gap, alc.getLineY(i), edits[3].getWidth()/2 - gap/2, PREFERRED);
		alc.add(btnInsert, RIGHT - gap, CENTER_OF, SAME, PREFERRED, edits[edits.length - 1]);

		ComboBox cbCountry = new ComboBox(new String[] { "Brazil", "USA" });
		alc.add(cbCountry, LEFT + gap, alc.getLineY(++i), SAME, PREFERRED, edits[edits.length - 1]);

		Button btnClear = new Button("CLEAR DATA", (byte) 0);
		alc.add(btnClear, RIGHT - gap, CENTER_OF, SAME, PREFERRED);

		btnInsert.addPressListener(e -> {
			if (canInsert) {
				lb = new ListBox();
				for (int j = 0; j < edits.length; j++)
					lb.add(labels[j] + ": " + edits[j].getText());
				if (cbCountry.getSelectedIndex() != -1)
					lb.add("Country: " + cbCountry.getSelectedItem());
				else
					lb.add("Country: ");

				output = new Label("OUTPUT:");
				output.setFont(font.asBold());
				content.add(output, CENTER, AFTER);
				content.add(lb, CENTER, AFTER + gap, SCREENSIZE + 80, PREFERRED);
				canInsert = false;

				content.scrollToControl(lb);
			} else {
				lb.removeAll();
				for (int j = 0; j < edits.length; j++)
					lb.add(labels[j] + ": " + edits[j].getText());
				if (cbCountry.getSelectedIndex() != -1)
					lb.add("Country: " + cbCountry.getSelectedItem());
				else
					lb.add("Country: ");
			}
//			reposition(); reposition bugando o edit
		});

		btnClear.addPressListener(e -> {

			// Cleaning the labels' content
			for (Edit edit : edits)
				edit.clear();
			cbCountry.setSelectedIndex(-1);
			if (!canInsert) {
				// Cleaning the output
				content.remove(lb);
				content.remove(output);
				canInsert = true;
			}
		});
	}
}
