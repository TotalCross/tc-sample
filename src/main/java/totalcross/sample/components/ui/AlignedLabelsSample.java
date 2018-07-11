package totalcross.sample.components.ui;

import totalcross.ui.AlignedLabelsContainer;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Edit;
import totalcross.ui.ScrollContainer;
import totalcross.ui.gfx.Color;

public class AlignedLabelsSample extends ScrollContainer{
	@Override
	  public void initUI() {
	    super.initUI();
	    ScrollContainer sc = new ScrollContainer(false, true);
	    add(sc, LEFT, TOP, FILL, FILL);

	    String[] labels = { "Name", "Born date", "Telephone", "Address", "City", "Country", "", };
	    AlignedLabelsContainer c = new AlignedLabelsContainer(labels, 4);
	    c.setBorderStyle(BORDER_LOWERED);
	    c.labelAlign = RIGHT;
	    c.foreColors = new int[] { Color.RED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	        Color.BLACK, };
	    c.setInsets(2, 2, 2, 2);
	    sc.add(c, LEFT + 2, TOP + 2, FILL - 2, PREFERRED + 4);
	    int i;
	    for (i = 0; i < labels.length - 2; i++) {
	      c.add(new Edit(), LEFT + 2, c.getLineY(i));
	    }
	    c.add(new ComboBox(new String[] { "Brazil", "USA" }), LEFT + 2, c.getLineY(i));
	    c.add(new Button("Insert data"), RIGHT, SAME);
	    c.add(new Button("Clear data"), RIGHT, AFTER + 4, SAME, PREFERRED);
	  }
}
