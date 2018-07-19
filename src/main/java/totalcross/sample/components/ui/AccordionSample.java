package totalcross.sample.components.ui;

import totalcross.sys.Settings;
import totalcross.ui.AccordionContainer;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MultiEdit;
import totalcross.ui.ScrollContainer;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class AccordionSample extends ScrollContainer {
  @Override
  public void initUI() {
    super.initUI();
    ScrollContainer sc = new ScrollContainer(false, true);
    add(sc, LEFT, TOP, FILL, FILL);
    // using default arrows
    AccordionContainer.Group gr = new AccordionContainer.Group();
    AccordionContainer ac = new AccordionContainer(gr);
    ac.setForeColor(Color.WHITE);
    ac.setBackColor(Color.getRGB(60, 226, 180));
    
    int gap = fmH / 2;
    sc.add(ac,CENTER, TOP + 200, SCREENSIZE+80 , PREFERRED);
    ac.add(ac.new Caption("Type text 1"), LEFT, TOP, FILL, PREFERRED);
    ac.add(new MultiEdit(), LEFT + gap, AFTER + gap, FILL - gap, FONTSIZE + 1400);
    
    ac = new AccordionContainer(gr);
    ac.setForeColor(Color.WHITE);
    ac.setBackColor(Color.getRGB(74, 144, 226));
    
    
   
    sc.add(ac,CENTER, AFTER, SCREENSIZE+80, PREFERRED);
    ac.add(ac.new Caption("Type text 2"), LEFT, AFTER, FILL, PREFERRED );
    ac.add(new MultiEdit(), LEFT + gap, AFTER + gap, FILL - gap, FONTSIZE + 1400);


    ac = new AccordionContainer(gr);
    ac.setForeColor(Color.WHITE);
    ac.setBackColor(Color.getRGB(99, 84, 229));
   
    sc.add(ac,CENTER, AFTER, SCREENSIZE+80, PREFERRED);
    ac.add(ac.new Caption("Type text 3"), LEFT, AFTER, FILL, PREFERRED);
    ac.add(new MultiEdit(), LEFT + gap, AFTER + gap, FILL - gap, FONTSIZE + 1400);

    
    
    
  }
}
