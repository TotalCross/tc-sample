package totalcross.sample.components.ui.charts.donut;

import java.util.ArrayList;
import java.util.List;

import totalcross.ui.Container;
import totalcross.ui.event.Event;
import totalcross.ui.event.EventHandler;
import totalcross.ui.event.MouseEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Coord;
import totalcross.ui.gfx.Graphics;
import totalcross.util.UnitsConverter;

public class DonutChart extends Container {
    
    int startAngle = 145;
    
    List<Slice> slices = new ArrayList<>();
    
    int centerX;
    int centerY;
    
    int r;
    
    Slice selectedSlice = null;
    
    @Override
    public void initUI() {
        r = Math.min(this.width, this.height) / 3;
        
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
        
        centerX = this.width / 2;
        centerY = this.height / 2;
        
        int currentAngle = startAngle;
        g.foreColor = this.getBackColor();
        for (Slice slice : slices) {
            g.foreColor = this.getBackColor();
            g.backColor = slice.fillColor;
            slice.startAngle = currentAngle;
            int selectedGap = selectedSlice == slice ? 10 : 0;
            Coord sliceCenter = getSliceCenter(slice, selectedGap);
            g.fillPie(sliceCenter.x, sliceCenter.y, r + selectedGap, currentAngle - slice.sweepAngle, currentAngle);

            if (slice == selectedSlice) {
                int tipAngle = currentAngle - slice.sweepAngle/2;
                Coord point = getCoordByAngle(sliceCenter, tipAngle, r + selectedGap);
                Coord point2 = getCoordByAngle(sliceCenter, tipAngle, r + selectedGap + 10);
               int x = point.x;
               int y = point.y;

               g.foreColor = Color.WHITE;
               g.drawLine(x, y, point2.x, point2.y);
               if(tipAngle < 0) tipAngle = 360 + tipAngle;
               if(tipAngle >= 0 && tipAngle < 90) {
                   point2.x += 10;
                   point2.y -= 30;
               }
                if(tipAngle >= 90 && tipAngle < 180) {
                    point2.x -= 60;
                    point2.y -= 30;
                }
                if(tipAngle >= 180 && tipAngle < 270) {
                    point2.x -= 70;
                }
               g.foreColor = this.getBackColor();
               g.backColor = selectedSlice.fillColor;
//               g.fillPolygon(
//                       new int[] { point2.x, point2.x + 70, point2.x + 60, point2.x - 10 },
//                       new int[] { point2.y, point2.y, point2.y + 30, point2.y + 30 }, 4);
               g.foreColor = Color.WHITE; //this.getBackColor();
               String valueStr = String.valueOf(selectedSlice.sweepAngle);
               g.drawText(valueStr,
                       point2.x + 35 - fm.stringWidth(valueStr)/2,
                       point2.y + 15 - fm.height/2);
            }
            
            g.foreColor = this.getBackColor();
            g.backColor = this.getBackColor();
            g.fillPie(sliceCenter.x, sliceCenter.y, r / 2, currentAngle - slice.sweepAngle,
                    currentAngle);
            currentAngle -= slice.sweepAngle;
        }
    }
    
    @Override
    public <H extends EventHandler> void onEvent(Event<H> event) {
        if (event.type == PenEvent.PEN_UP) {
            PenEvent pe = (PenEvent) event;

            if (isInside(pe.x, pe.y, r) && !isInside(pe.x, pe.y, r / 2)) {

                double theta = Math.toDegrees(Math.atan2(centerY - pe.y, pe.x - centerX));
                theta = (theta + 360) % 360;
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

    protected void drawTip(Graphics g, Slice slice) {

    }

    protected Coord getCoordByAngle(Coord center, int angle, int radius) {
        Coord result  = new Coord();
        result.x = center.x + (int) (Math.cos(Math.toRadians(angle))*radius);
        result.y = center.y - (int) (Math.sin(Math.toRadians(angle))*radius);
        return  result;
    }

    protected Coord getSliceCenter(Slice slice, int gap) {
        int angle = slice.startAngle - slice.sweepAngle/2; // Angle Center of slice
        if(angle < 0) angle = 360 + angle;
        Coord center = new Coord();
        center.x = centerX + (int)(gap*Math.cos(Math.toRadians(angle)));
        center.y = centerY - (int)(gap*Math.sin(Math.toRadians(angle)));
        System.out.println(angle + ", " +  center.x + ", " + center.y);
        return center;
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