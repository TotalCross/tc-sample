package totalcross.sample.components.ui.charts.column;

import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Label;
import totalcross.ui.Spacer;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Graphics;
import totalcross.util.UnitsConverter;

public class DashboardColumnChart extends Container {
    
    int[] values = new int[] {
            16, 19, 24, 15, 24, 10, 18, 20, 25, 14, 18, 20, 24, 20, 15, 13, 10, 9, 11, 20, 19, 18, 19
            
    };
    @Override
    public void initUI() {
        // TODO Auto-generated method stub
        super.initUI();

 
        for (int i = 0 ; i < 7 ; i++) {
            add(new Label(Convert.zeroPad(Convert.toCurrencyString(i*5, 2), 5)), LEFT + UnitsConverter.toPixels(DP + 8), this.height - (this.height / 7 * (i + 1)) + 4);
        }
        
        add(new Spacer(), AFTER, TOP, DP + 16, FILL);
        
        ChartBar[] bars = new ChartBar[values.length];
        for (int i = 0 ; i < bars.length ; i++) {
            bars[i] = new ChartBar(values[i]);
            add(bars[i], AFTER, TOP, width / (values.length + 3), FILL - UnitsConverter.toPixels(DP + 4));
        }
    }

}

class ChartBar extends Control {
    
    final static int MAX = 30;
    
    int value;
    
    ChartBar(int value) {
        this.value = value;
    }
    
    @Override
    public void onPaint(Graphics g) {
        
//        g.fillRoundRect(xx, yy, width, height, r);
        
        int d = (int)Settings.screenDensity;
        g.backColor = Color.getRGB(42, 150, 228);
        int myHeight = (int) (height * ((double) value / MAX));
        g.fillRoundRect(0, height - myHeight, width / 3, myHeight, (int)(10 * d));

    }
}
