package totalcross.sample.components.ui.charts.donut;

class Slice {
    
    int centerX;
    int centerY;
    int startAngle;
    int sweepAngle;
    int fillColor;
    
    public boolean isInside(double theta) {
        int start = 360 - startAngle > 360 ? startAngle - 360 : startAngle;
        int end = start + sweepAngle;
        if (end > 360) {
            end -= 360;
        }
        
        theta = theta >= 0 ? theta : 360 + theta;
        
        System.out.println(theta + ", " + start + ", " + end);
        return start < end ? (theta >= start && theta <= end) : (theta > start || theta < end);
    }
}