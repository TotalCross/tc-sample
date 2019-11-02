package totalcross.sample.components.ui.charts.gauge;

import totalcross.ui.gfx.Graphics;

public class StorageGauge extends Gauge {
    StorageGauge(int min, int max, int angleRange) {
        super(min, max, angleRange);
        valueSuffix = "GB";
    }

    @Override
    public int getPreferredHeight() {
        int remainderAngle = (angleRange > 180? (angleRange - 180)/2: 0);
        return (int)(width/2 + width*Math.sin(remainderAngle*Math.PI/180));
    }

    @Override
    protected void onDrawSections(Graphics g, int radius, int startAngle, int endAngle) {
        g.backColor = g.foreColor = 0;
        g.fillPie(centerX, centerY, radius, endAngle, startAngle);
        g.foreColor = g.backColor = foreColor;
        g.fillPie(centerX, centerY, radius, getAngle(),
                startAngle);
        //draw inner circle
        g.backColor = g.foreColor = backColor;
        g.fillPie(centerX, centerY, (int)(radius*0.75), 0, 360);
    }

    @Override
    protected void onDrawTicks(Graphics g, int majorTicksInterval, int minorTicksInterval, int radius) {
        
    }

    @Override
    protected void drawArrow(Graphics g, int radius, int startAngle, int endAngle) {

    }

    @Override
    protected void onDisplayValue(Graphics g, String value, String preffix, String suffix, int radius, int startAngle, int endAngle) {


        g.setFont(font);
        g.backColor = g.foreColor = foreColor;
        String resultingValue = preffix+value+suffix;
        g.drawText(resultingValue,
                centerX - font.fm.stringWidth(resultingValue)/2, centerY - font.fm.height/2);
    }
}
