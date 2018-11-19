package totalcross.sample.components.lang.thread;

import totalcross.sample.util.Colors;
import totalcross.sys.Vm;
import totalcross.ui.Container;
import totalcross.ui.MultiEdit;
import totalcross.ui.gfx.Color;

public class TypingContainer extends Container implements Runnable, ThreadSample.SetX {
  String typingText = "The new virtual machine, called TotalCross, has better performance due to a new instruction set that eliminates limitations in the existing SuperWaba virtual machine, with enhancements such as unlimited object size, preemptive threads and a new high-performance garbage collector that is 20X faster than the SuperWaba's. Additionally, deployed files are now compacted, to acheive a 30% reduction in size over SuperWaba applications.";

  int index = 0;
  MultiEdit me;
  boolean fill;

  public TypingContainer(boolean fill) {
    this.fill = fill;
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public void incX(int x) {
    this.x += x;
  }

  @Override
  public void initUI() {
    super.initUI();
    setBackForeColors(fill ? Colors.S_400 : Colors.S_300, fill ? Colors.ON_S_400 : Colors.ON_S_300);
    setBorderStyle(BORDER_RAISED);

    me = new MultiEdit(0, 0);
    add(me, LEFT, TOP, FILL, FILL);
    if (fill) {
      me.justify = fill;
    }
    me.setEditable(false);
    MultiEdit.hasCursorWhenNotEditable = false;
    me.setBackColor(fill ? Color.YELLOW : Color.GREEN);

    Thread t = new Thread(this);
    t.start();
  }

  @Override
  public void run() {
    int length = typingText.length();
    StringBuffer sb = new StringBuffer(length);
    while (true) {
      index = 0;
      while (index < length) {
        sb.append(typingText.charAt(index));
        me.setText(sb.toString());
        if (ThreadSample.paused || ThreadSample.paused0) {
          me.repaintNow();
        }
        index++;
        Vm.sleep(100);
      }
      Vm.sleep(1000);
      sb.setLength(0);
      me.setText("");
      index = 0;
    }
  }
}
