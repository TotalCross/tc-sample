package totalcross.sample.components;

import totalcross.ui.Container;
import totalcross.ui.gfx.Graphics;

public class BMFontSample extends Container {
   @Override
   public void onPaint(Graphics g) {
      super.onPaint(g);
      String lorem = "Lorem ipsum dolor sit amet\n, consectetur adipiscing elit. Donec vestibulum quam vitae leo sodales, in auctor urna pulvinar. Morbi leo tellus, placerat at ullamcorper elementum, tincidunt eu massa. In finibus massa lacinia dui scelerisque, id vehicula velit interdum. Phasellus ut massa id erat cursus fermentum ut vitae nulla. Cras a turpis lobortis, luctus mi ut, malesuada mauris. Nunc scelerisque lacinia justo vel viverra. Nullam luctus ex eget interdum cursus. Proin eget diam a nulla tincidunt fermentum. Nunc at nunc sed lacus elementum molestie tempus et tortor.";
      g.drawText(lorem, 30, 150, 0);
   }
}
