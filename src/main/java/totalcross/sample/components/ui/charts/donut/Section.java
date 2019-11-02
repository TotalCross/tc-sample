package totalcross.sample.components.ui.charts.donut;

import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Label;
import totalcross.ui.Ruler;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.icon.MaterialIcons;
import totalcross.util.UnitsConverter;

public class Section extends Container {
    
    String title;
    Control content;
    
    public Section(String title, Control content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public void initUI() {
        this.borderColor = Color.getRGB(47, 47, 47);
        this.setBorderStyle(BORDER_ROUNDED);
        this.setBackColor(Color.getRGB(47, 47, 47));
        
        Button btMore = new Button(String.valueOf(MaterialIcons._MORE_VERT), Button.BORDER_NONE);
        btMore.setForeColor(Color.getRGB(127, 137, 153));
        btMore.transparentBackground = true;
        btMore.setFont(Font.getFont(MaterialIcons._3D_ROTATION.fontName(), false, 18));
        add(btMore, RIGHT, TOP, PREFERRED, PREFERRED);
        
        Button btConfig = new Button(String.valueOf(MaterialIcons._SETTINGS), Button.BORDER_NONE);
        btConfig.setForeColor(Color.getRGB(127, 137, 153));
        btConfig.transparentBackground = true;
        btConfig.setFont(Font.getFont(MaterialIcons._3D_ROTATION.fontName(), false, 18));
        add(btConfig, BEFORE, TOP, PREFERRED, PREFERRED);
        
        Label lblTitle = new Label(title);
        lblTitle.setForeColor(Color.getRGB(127, 137, 153));
        lblTitle.setFont(Font.getFont(18));
        add(lblTitle, LEFT + UnitsConverter.toPixels(DP + 10), TOP, FIT, SAME);
        
        Ruler r = new Ruler();
        r.setForeColor(Color.getRGB(127, 137, 153));
        add(r, LEFT, AFTER, FILL, PREFERRED);
        
        if (this.content != null) {
            add(content, LEFT, AFTER, FILL, FILL);
        }
    }
}
