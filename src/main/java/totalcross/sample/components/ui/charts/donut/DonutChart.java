package totalcross.sample.components.ui.charts.donut;

import java.util.ArrayList;
import java.util.List;

import totalcross.ui.Container;
import totalcross.ui.event.Event;
import totalcross.ui.event.EventHandler;
import totalcross.ui.event.MouseEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Graphics;

public class DonutChart extends Container {
    
    int startAngle = 265;
    
    List<Slice> slices = new ArrayList<>();
    
    int centerX;
    int centerY;
    
    int r;
    
    Slice selectedSlice = null;
    
    @Override
    public void initUI() {
        centerX = this.x + this.width / 2;
        centerY = this.y + this.height / 2;
        r = Math.min(this.width, this.height) / 4;
        
        Slice s1 = new Slice();
//        s1.centerX = centerX;
//        s1.centerY = centerY;
//        s1.startAngle = 0;
        s1.sweepAngle = 240;
        s1.fillColor = Color.getRGB(139, 231, 54);

        Slice s2 = new Slice();
//      s1.centerX = centerX;
//      s1.centerY = centerY;
//      s1.startAngle = 0;
      s2.sweepAngle = 60;
      s2.fillColor = Color.getRGB(248, 208, 61);

      Slice s3 = new Slice();
//    s1.centerX = centerX;
//    s1.centerY = centerY;
//    s1.startAngle = 0;
    s3.sweepAngle = 60;
    s3.fillColor = Color.getRGB(249, 54, 88);
      
      
      
        slices.add(s1);
        slices.add(s2);
        slices.add(s3);
        
        this.transparentBackground = true;
    }
    
    @Override
    public void onPaint(Graphics g) {
        // TODO Auto-generated method stub
        super.onPaint(g);
        int centerX = this.x + this.width / 2;
        int centerY = this.y + this.height / 2;
        g.backColor = Color.RED;
        
        int currentAngle = startAngle;
        g.foreColor = this.getBackColor();
        for (Slice slice : slices) {
            g.foreColor = slice.fillColor;
            g.backColor = slice.fillColor;
            slice.startAngle = currentAngle;
            int selectedGap = selectedSlice == slice ? 10 : 0;
            g.fillPie(centerX + selectedGap, centerY - selectedGap, r, currentAngle, currentAngle + slice.sweepAngle);

            if (slice == selectedSlice) {
               int x = (int) (centerX + selectedGap + (r * Math.cos(Math.toRadians(selectedSlice.startAngle + selectedSlice.sweepAngle / 2))));
               int y = (int) (centerY - selectedGap + (r * Math.cos(Math.toRadians(selectedSlice.startAngle + selectedSlice.sweepAngle / 2))));

               g.foreColor = Color.WHITE;
               g.drawLine(x, y, x + 10, y + 10);
               g.foreColor = g.backColor = selectedSlice.fillColor;
               g.fillPolygon(
                       new int[] { x + 10, x + 70, x + 60, x }, 
                       new int[] { y + 10, y + 10, y + 30, y + 30 }, 4);
               g.foreColor = this.getBackColor();
               g.drawText(String.valueOf(selectedSlice.sweepAngle), x + 20, y + 10);
            }
            
            g.foreColor = this.getBackColor();
            g.backColor = this.getBackColor();
            g.fillPie(centerX + selectedGap, centerY - selectedGap, r / 2, currentAngle - 1, currentAngle + slice.sweepAngle + 1);
            currentAngle += slice.sweepAngle;
        }

    }
    
    @Override
    public <H extends EventHandler> void onEvent(Event<H> event) {
        if (event.type == PenEvent.PEN_UP) {
            PenEvent pe = (PenEvent) event;

            if (isInside(pe.x, pe.y, r) && !isInside(pe.x, pe.y, r / 2)) {

                double theta = Math.toDegrees(Math.atan2(pe.y - centerY, pe.x - centerX));
                for (Slice slice : slices) {
                    if (slice.isInside(theta)) {
                        if (slice == selectedSlice) {
                            selectedSlice = null;
                        } else {
                            selectedSlice = slice;
                        }
//                        System.out.println("INSIDE " + slice.sweepAngle);
                        repaintNow();
                        break;
                    }
                }

            }
            double theta = Math.toDegrees(Math.atan2(pe.y - centerY, pe.x - centerX));
            System.out.println(String.valueOf(theta));
        } else if (event.type == MouseEvent.MOUSE_MOVE) {
            MouseEvent pe = (MouseEvent) event;
//            if (isInside(pe.x, pe.y, r) && !isInside(pe.x, pe.y, r / 2)) {
//                double theta = Math.toDegrees(Math.atan2(pe.y - centerY, pe.x - centerX));
//                System.out.println(String.valueOf(theta));
//            }
        }
        super.onEvent(event);
    }
    
    public boolean isInside(int x, int y, int radius) {
        int dx = Math.abs(x-centerX);
        int dy = Math.abs(y-centerY);
        
        if (dx>radius) 
        return false;
    if (dy>radius)  
        return false;
    
    if ( dx+dy <= radius ) return true;
        return ( dx*dx + dy*dy <= radius*radius );
    }
}