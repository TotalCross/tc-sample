package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.AlignedLabelsContainer;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ListBox;
import totalcross.ui.ScrollContainer;

public class AlignedLabelsSample extends ScrollContainer{
	private int gap = (int) (Settings.screenDensity * 20);
	private boolean canInsert = true;
	private ListBox lb;
	private Label output;
	@Override
	  public void initUI() {
	    setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
	    setScrollBars(false, true);
	    
	    String[] labels = { "Name", "Born date", "Telephone", "Address", "City", "Country", ""};
	    Edit edits[] = new Edit[5];
	    Edit.useNativeNumericPad = true;
	    for (int i = 0; i < edits.length; i++) {
	    	switch(i) {
	    	case 1:
	    		edits[i] = new Edit("99/99/9999");
	    		edits[i].setMode(Edit.NORMAL,true);
	    		edits[i].setValidChars(Edit.numbersSet);
	    		edits[i].setKeyboard(Edit.KBD_NUMERIC);
	    		break;
	    	case 2:
	    		edits[i] = new Edit("(99)9999-99999");
	    		edits[i].setMode(Edit.NORMAL,true);
	    		edits[i].setValidChars(Edit.numbersSet);
	    		edits[i].setKeyboard(Edit.KBD_NUMERIC);
	    		break;
	    		default:
	    			edits[i] = new Edit();
	    	}
	    }

	    AlignedLabelsContainer c = new AlignedLabelsContainer(labels, gap*2);
	    
	    Label title = new Label("This is an AlignedLabelsContainer.\nAll the content will be automatically aligned.", CENTER, 0, true);
	    title.autoSplit = true;
	    
	    add(title, LEFT + gap, TOP + gap, FILL - gap, PREFERRED);
	    
	    c.labelAlign = RIGHT;
	   
	    c.setInsets(gap/3, gap/3, gap/3, gap/3);
	    add(c, LEFT + gap, AFTER + gap, FILL - gap, PREFERRED);
	    int i;
	    for (i = 0; i < edits.length - 1; i++) {
    		c.add(edits[i], LEFT + gap, c.getLineY(i), FILL, PREFERRED);
	    }
	    
	    Button btnInsert = new Button("Insert data", (byte) 0);
	    btnInsert.setBackForeColors(Colors.P_600, Colors.ON_P_600);
	    c.add(btnInsert, RIGHT, AFTER + gap, PREFERRED + gap*6, PREFERRED + gap*2, edits[3]);
	    c.add(edits[edits.length-1], LEFT + gap, c.getLineY(i), FIT - gap, PREFERRED);
	    
	    
	    Button btnClear = new Button("CLEAR DATA", (byte)0);
	    c.add(btnClear, SAME, c.getLineY(++i), SAME, SAME, btnInsert);
	    
	    ComboBox cbCountry = new ComboBox(new String[] { "Brazil", "USA" });
	    c.add(cbCountry, LEFT + gap, c.getLineY(i), FIT - gap, PREFERRED);
	    
	    btnInsert.addPressListener(e -> {
	    	if(canInsert) {
		    	lb = new ListBox();
		    	for (int j = 0; j < edits.length; j++)
					lb.add(labels[j] + ": " + edits[j].getText());
		    	if(cbCountry.getSelectedIndex() != -1)
		    		lb.add("Country: " + cbCountry.getSelectedItem());
		    	else
		    		lb.add("Country: ");
		    	
		    	output = new Label("OUTPUT:");
		    	output.setFont(font.asBold());
		    	add(output, CENTER, AFTER);
		    	add(lb, CENTER, AFTER + gap, SCREENSIZE + 80, PREFERRED);
		    	canInsert = false;
		    	
		    	scrollToControl(lb);
	    	} else {
	    		lb.removeAll();
	    		for (int j = 0; j < edits.length; j++)
					lb.add(labels[j] + ": " + edits[j].getText());
		    	if(cbCountry.getSelectedIndex() != -1)
		    		lb.add("Country: " + cbCountry.getSelectedItem());
		    	else
		    		lb.add("Country: ");
	    	}
	    	reposition();
	    });
	    
	    btnClear.addPressListener(e -> {
	    	if(!canInsert) {
		    	//Cleaning the labels' content
		    	for (Edit edit : edits)
					edit.clear();
		    	cbCountry.setSelectedIndex(-1);
		    	
		    	//Cleaning the output
		    	remove(lb);
		    	remove(output);
		    	canInsert = true;
	    	}
	    });
	  }
}
