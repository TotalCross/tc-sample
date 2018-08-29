package totalcross.sample.components;

import totalcross.io.IOException;
import totalcross.sample.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Home extends Container {

  @Override
  public void initUI() {
    try {
      setBackColor(Colors.PRIMARY);

      ImageControl ic = new ImageControl(new Image("images/logoV.png"));
      ic.scaleToFit = true;
      ic.centerImage = true;
      add(ic, CENTER, AFTER + 250, PARENTSIZE, PARENTSIZE + 20);

      Label lbWelcome = new Label("Welcome!");
      lbWelcome.setFont(Font.getFont("Lato Light", false, lbWelcome.getFont().getSize() + 30));
      lbWelcome.setForeColor(Color.WHITE);
      add(lbWelcome, CENTER, AFTER + 40);

      Label lbWelcome3 = new Label("Use the menu on the top left\n     to see the components");
      lbWelcome3.setFont(Font.getFont("Lato Bold", false, lbWelcome3.getFont().getSize()));
      lbWelcome3.setForeColor(Color.WHITE);
      add(lbWelcome3, CENTER, AFTER + 40);

      Button btSystemInfo = new Button("System information");
      btSystemInfo.setFont(Font.getFont("Lato Bold", false, lbWelcome3.getFont().getSize()));
      btSystemInfo.setForeColor(Color.WHITE);
      add(btSystemInfo, CENTER, BOTTOM - 100, PREFERRED + 100, PREFERRED);
    } catch (IOException | ImageException e) {
      e.printStackTrace();
    }
  }
}
