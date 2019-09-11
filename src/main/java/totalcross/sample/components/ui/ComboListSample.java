package totalcross.sample.components.ui;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.ComboBox;
import totalcross.ui.Label;
import totalcross.ui.ListBox;
import totalcross.ui.MultiListBox;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;

public class ComboListSample extends BaseScreen {

    private ComboBox simpleComboBox;
    private ComboBox popupComboBox;
    private ListBox simpleListBox;
    private MultiListBox multiSelListBox;

    private int gap = (int) (Settings.screenDensity * 20);

    public ComboListSample () {
        super("https://totalcross.gitbook.io/playbook/components/combobox");
    }

    @Override
    public void onContent(ScrollContainer content) {
        try {
            setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);

            String[] items = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
            String[] items2 = {"One", "Two", "Three", "Four", "Five"};

            Label lbCombos = new Label("Combos", CENTER);
            lbCombos.setFont(lbCombos.getFont().asBold());
            content.add(lbCombos, LEFT + gap, AFTER + gap * 2, FILL - gap, PREFERRED);

            ComboBox.usePopupMenu = false;
            simpleComboBox = new ComboBox(items);
            simpleComboBox.caption = "Numbers with Dropdown";
            simpleComboBox.setForeColor(Colors.ON_BACKGROUND);

            content.add(simpleComboBox, LEFT + gap, AFTER + gap / 2, FILL - gap, PREFERRED);

            ComboBox.usePopupMenu = true;
            popupComboBox = new ComboBox(items);
            popupComboBox.caption = "Numbers with Popup";
            popupComboBox.setForeColor(Colors.ON_BACKGROUND);

            content.add(popupComboBox, LEFT + gap, AFTER + gap / 2, FILL - gap, PREFERRED);

            Label lbListBox = new Label("List Boxes", CENTER);
            lbListBox.setFont(lbListBox.getFont().asBold());
            content.add(lbListBox, LEFT + gap, AFTER + gap * 2, FILL - gap, PREFERRED);

            Label singleBox = new Label("This is a single selection list box!");
            singleBox.autoSplit = true;
            singleBox.setFont(lbListBox.getFont().asBold());
            content.add(singleBox, LEFT + gap, AFTER + gap, FILL - gap, PREFERRED);

            simpleListBox = new ListBox(items);
            simpleListBox.setBackColor(Colors.BACKGROUND);
            content.add(simpleListBox, CENTER, AFTER + gap / 2, SCREENSIZE + 75, 200 + DP);

            multiSelListBox = new MultiListBox(items2);
            multiSelListBox.setOrderIsImportant(true);

            Label multiBox = new Label("This is a multiple selection list box!");
            multiBox.autoSplit = true;
            multiBox.setFont(lbListBox.getFont().asBold());
            content.add(multiBox, LEFT + gap, AFTER + gap, FILL - gap, PREFERRED);

            content.add(multiSelListBox, CENTER, AFTER + gap / 2, SCREENSIZE + 75, 200 + DP);

            content.add(new Spacer(), CENTER, AFTER + gap / 2, gap / 2, gap / 2);
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

}
