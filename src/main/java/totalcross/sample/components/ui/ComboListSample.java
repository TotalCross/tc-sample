package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sample.util.Util;
import totalcross.ui.CaptionPress;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ListBox;
import totalcross.ui.MultiListBox;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;
import totalcross.util.IntHashtable;

public class ComboListSample extends Container {
	
	private ScrollContainer sc;
	private ComboBox simpleComboBox;
	private ComboBox imageComboBox;
	private ComboBox popupComboBox;
	private ListBox simpleListBox;
	private MultiListBox multiColorListBox;
	
	private final int H = 0;
	private final int B = 100;
	
	@Override
	public void initUI() {
		try {
			
			sc = new ScrollContainer(false, true);
		    add(sc,LEFT,TOP,FILL,FILL);
			
			String[] items = {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
			String[] items2 = {"One","Two","Three","Four","Five"};
			
			Label lbCombos = new Label("Combos");
			lbCombos.setFont(lbCombos.getFont().asBold());
			sc.add(lbCombos, LEFT + B, AFTER + B, FILL - B, PREFERRED+H);
			
			ComboBox.usePopupMenu = false;
			simpleComboBox = new ComboBox(items);
			simpleComboBox.caption = "Numbers with Dropdown";
			simpleComboBox.fillColor = Colors.RED;
			simpleComboBox.setBackColor(Colors.RED);
			simpleComboBox.setForeColor(Color.WHITE);
			simpleComboBox.checkColor = Color.WHITE;
			
			
			sc.add(simpleComboBox, LEFT + B, AFTER + B, FILL - B, PREFERRED+H);
			ComboBox.usePopupMenu = true;
			
			imageComboBox = new ComboBox(items);
			imageComboBox.caption = "Numbers with Popup";
			imageComboBox.captionIcon = Util.getAwesomeImage('\uF12D',fmH,Color.BLACK);
			imageComboBox.popupTitle = "Select the item";
			imageComboBox.enableSearch = false;
			imageComboBox.setBackColor(Color.BRIGHT);
			imageComboBox.checkColor = Colors.BLUE;
			sc.add(imageComboBox, LEFT + B, AFTER + B , FILL - B, PREFERRED+H);
			imageComboBox.captionPress = new CaptionPress() {
		        @Override
		        public void onIconPress()
		        {
		        	imageComboBox.setSelectedIndex(-1);
		        }

		        @Override
		        public void onCaptionPress()
		        {
		        }
		    };
			
			popupComboBox = new ComboBox(items);
			popupComboBox.caption = "Numbers with Popup";
			popupComboBox.popupTitle = "Select the item";
			popupComboBox.setBackColor(Color.BRIGHT);
			popupComboBox.checkColor = Colors.ORANGE;
			sc.add(popupComboBox, LEFT + B, AFTER + B , FILL - B, PREFERRED+H);
			
			Label lbListBox = new Label("List Box");
			lbListBox.setFont(lbListBox.getFont().asBold());
			sc.add(lbListBox, LEFT + B, AFTER+ B, FILL - B, PREFERRED+H);
			
			simpleListBox = new ListBox(items);
			simpleListBox.setBackColor(Colors.BACKGROUND);
		    sc.add(simpleListBox, LEFT + B, AFTER + B, FILL - B, FONTSIZE+725);

		    multiColorListBox = new MultiListBox(items2); 
		    multiColorListBox.setOrderIsImportant(true);
		    IntHashtable colorsTable = new IntHashtable(1);
		    colorsTable.put(0,Colors.RED);
		    colorsTable.put(1,Colors.SUCESS_GREEN);
		    colorsTable.put(2,Colors.BLUE);
		    colorsTable.put(3,Colors.ORANGE);
		    colorsTable.put(4,Colors.PURPLE);
		    multiColorListBox.ihtForeColors = colorsTable;
		    sc.add(multiColorListBox,LEFT + B,AFTER + B, FILL - B, PREFERRED+H);
			
	    } catch (Exception e) {
	    	MessageBox.showException(e,true);
	    }
	}

}
