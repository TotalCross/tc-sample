package totalcross.sample.components.ui;

import totalcross.ui.AccordionContainer;
import totalcross.ui.MultiEdit;
import totalcross.ui.ScrollContainer;
import totalcross.ui.gfx.Color;

public class AccordionSample extends ScrollContainer {
  @Override
  public void initUI() {
    super.initUI();
    setScrollBars(false, true);

    AccordionContainer.Group gr = new AccordionContainer.Group();
    AccordionContainer ac = new AccordionContainer(gr);
    ac.setForeColor(Color.WHITE);
    ac.setBackColor(Color.getRGB(60, 226, 180));
    
    int gap = fmH / 2;
    add(ac,CENTER, TOP + 200, SCREENSIZE+80 , PREFERRED);
    ac.add(ac.new Caption("Type text 1"), LEFT, TOP, FILL, PREFERRED);
    ac.add(new MultiEdit(), LEFT + gap, AFTER + gap, FILL - gap, FONTSIZE + 1400);
    
    ac = new AccordionContainer(gr);
    ac.setForeColor(Color.WHITE);
    ac.setBackColor(Color.getRGB(74, 144, 226));
    
    add(ac,CENTER, AFTER, SCREENSIZE+80, PREFERRED);
    ac.add(ac.new Caption("Type text 2"), LEFT, AFTER, FILL, PREFERRED );
    ac.add(new MultiEdit(), LEFT + gap, AFTER + gap, FILL - gap, FONTSIZE + 1400);

    ac = new AccordionContainer(gr);
    ac.setForeColor(Color.WHITE);
    ac.setBackColor(Color.getRGB(99, 84, 229));
   
    add(ac,CENTER, AFTER, SCREENSIZE+80, PREFERRED);
    ac.add(ac.new Caption("Type text 3"), LEFT, AFTER, FILL, PREFERRED);
    ac.add(new MultiEdit(), LEFT + gap, AFTER + gap, FILL - gap, FONTSIZE + 1400);
  }
}
