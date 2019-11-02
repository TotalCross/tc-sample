package totalcross.sample.components.ui.charts.gauge;

import totalcross.ui.gfx.Coord;
import totalcross.ui.gfx.Graphics;

public class StylishSectionsGauge extends Gauge {

    public StylishSectionsGauge(int minValue, int maxValue, int angleRange) {
        super (minValue, maxValue, angleRange);
    }

    @Override
    protected void onDrawSections(Graphics g, int radius, int startAngle, int endAngle) {
        markWidthScale = 0.30;
        int start = (180 - angleRange)/2;
        int lastStart = start;
        int delta = (int)(radius*0.3);
        Coord out = new Coord();
        // Marks are mounted backwards
        for (int i = 0; i < sectionCount; i++) {
            g.backColor = colors[i];
            g.foreColor = colors[i];
            final int sectionSize = (int) (((double) sections[i] / sum) * angleRange);
            g.fillPie(centerX, centerY, radius, lastStart, lastStart + sectionSize);
            g.backColor = g.foreColor = backColor;
            g.fillPie(centerX, centerY, radius - delta, lastStart - 5, lastStart + sectionSize);
            delta = (int)(delta*0.66
            );
            lastStart += sectionSize;
        }
    }
}
