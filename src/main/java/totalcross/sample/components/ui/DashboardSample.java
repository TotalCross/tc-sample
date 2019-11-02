package totalcross.sample.components.ui;

import totalcross.sample.components.ui.charts.donut.DonutChart;
import totalcross.sample.components.ui.charts.donut.Section;
import totalcross.sample.components.ui.charts.gauge.GaugeSample;
import totalcross.ui.Container;
import totalcross.ui.Spacer;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

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
                final int GAP = UnitsConverter.toPixels(DP + 8);

                Spacer center = new Spacer();
                add(center, CENTER, CENTER);

                add(new Section("Gauge", new GaugeSample()), RIGHT - GAP, TOP, PARENTSIZE + 30, FIT - GAP);
                add(new Section("Values", null), BEFORE - GAP, SAME, PARENTSIZE + 30, SAME);
                add(new Section("Donut", new DonutChart()), LEFT + GAP, SAME, FIT - GAP, SAME);

                add(new Section("WTE", null), RIGHT - GAP, AFTER, PARENTSIZE + 30, FILL - GAP, center);
                add(new Section("Columns", null), LEFT + GAP, SAME, FIT - GAP, SAME);

            }
        }, LEFT, AFTER, FILL, FILL);
    }

}