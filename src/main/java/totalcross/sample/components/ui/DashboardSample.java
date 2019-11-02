package totalcross.sample.components.ui;

import totalcross.sample.components.ui.charts.donut.DonutChart;
import totalcross.sample.components.ui.charts.donut.Section;
import totalcross.ui.Container;
import totalcross.ui.gfx.Color;

public class DashboardSample extends Container {
    
    
    @Override
    public void initUI() {
        this.setBackColor(Color.getRGB(24, 28, 40));
        
        add(new Container() {
            @Override
            public void initUI() {
                this.setBackColor(Color.getRGB(41, 45, 57));
            }
            
        }, LEFT, TOP, PARENTSIZE, DP + 56);
        
        add(new Container() {
            @Override
            public void initUI() {
                
            }
            
        }, LEFT, AFTER, PARENTSIZE, DP + 56);
        
        add(new Container() {
            @Override
            public void initUI() {
                add(new Section("Donut", new DonutChart()), LEFT, AFTER, PARENTSIZE + 40, PARENTSIZE + 50);
                add(new Section("Values", null), AFTER, SAME, PARENTSIZE + 30, SAME);
                add(new Section("Gauge", null), AFTER, SAME, PARENTSIZE + 30, SAME);
                
                add(new Section("Columns", null), LEFT, AFTER, PARENTSIZE + 70, PARENTSIZE + 50);
                add(new Section("WTE", null), AFTER, SAME, PARENTSIZE + 30, SAME);
            }
        }, LEFT, AFTER, FILL, FILL);
    }

}