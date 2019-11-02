package totalcross.sample.components.ui.charts.donut;

import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Label;
import totalcross.ui.gfx.Color;

public class Section extends Container {
    
    String title;
    Control content;
    
    public Section(String title, Control content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public void initUI() {
        this.setBackColor(Color.getRGB(47, 47, 47));
        
        add(new Label(title), LEFT, TOP, FILL, DP + 36);
        
        if (this.content != null) {
            add(content, LEFT, AFTER, FILL, FILL);
        }
    }
}
