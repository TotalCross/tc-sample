package totalcross.sample.components.ui.charts.donut;

class Slice {
    
    int centerX;
    int centerY;
    int startAngle;
    int sweepAngle;
    int fillColor;
    
    public boolean isInside(double theta) {
        int start = startAngle;
        int end = start - sweepAngle;
        if(start > 0 && end < 0) {
            return theta <= start || (theta - 360) >= end;
        }
        if(start < 0) start = 360 + start;
        if(end < 0) end = 360 + end;
//        System.out.println(theta + ", " + start + ", " + end);
        return theta <= start && theta >= end;
    }
}