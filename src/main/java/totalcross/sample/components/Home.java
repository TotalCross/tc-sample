package totalcross.sample.components;

import totalcross.io.IOException;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.*;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.util.UnitsConverter;

public class Home  extends Container{

  public void initUI() {
    try {
    	setBackColor(Colors.PRIMARY);
      ImageControl ic = new ImageControl(new Image("images/logoV.png"));

      ic.scaleToFit = true;
      ic.centerImage = true;
      add(ic, CENTER, AFTER + UnitsConverter.toPixels(Control.DP + 50), PARENTSIZE, PARENTSIZE + 20);
      Label lbWelcome = new Label("Welcome!");
      lbWelcome.setFont(Font.getFont("Lato Light", false, lbWelcome.getFont().size + 30));
      lbWelcome.setForeColor(Color.WHITE);
      add(lbWelcome, CENTER, AFTER + UnitsConverter.toPixels(Control.DP + 25));

      Label lbWelcome3 = new Label("Use the menu on the top left\n     to see the components");
      lbWelcome3.setFont(Font.getFont("Lato Bold", false, lbWelcome3.getFont().size));
      lbWelcome3.setForeColor(Color.WHITE);
      add(lbWelcome3, CENTER, AFTER + UnitsConverter.toPixels(Control.DP + 10));

      Button btSystemInfo = new Button("System information");
      btSystemInfo.setFont(Font.getFont("Lato Bold", false, lbWelcome3.getFont().size));
      btSystemInfo.setBackForeColors(Color.WHITE, Color.BLACK);
      add(btSystemInfo, CENTER, BOTTOM - UnitsConverter.toPixels(Control.DP + 20), PREFERRED + 20, PREFERRED);
      btSystemInfo.addPressListener(
    	         (e) -> {
    	           MaterialWindow info =
    	               new MaterialWindow("Information", false, new Presenter() {
    	                @Override
    	                public Container getView() {
    	                    return new Container() {
    	                     @Override
    	                     public void initUI() {
    	                       AlignedLabelsContainer alc =
    	                           new AlignedLabelsContainer(
    	                               new String[] {
    	                                 "Application version",
    	                                 "TotalCross version",
    	                                 "OS",
    	                                 "OS version",
    	                                 "Screen density",
    	                                 "Device font size",
    	                                 "Actual font size",
    	                                 "Width x Height"
    	                               });
    	                       add(alc, LEFT, TOP, FILL, FILL);
    	                       int padding = (int) (Settings.screenDensity * 8);
    	                       alc.setInsets(padding, padding, padding, padding);
    	                       alc.setForeColor(Color.BLACK);
    	                       int lineY = 0;
    	                       alc.add(new Label(Settings.appVersion), LEFT + UnitsConverter.toPixels(DP + 15), alc.getLineY(lineY++));
    	                       alc.add(new Label(Settings.versionStr), LEFT + UnitsConverter.toPixels(DP + 15), alc.getLineY(lineY++));
    	                       alc.add(new Label(Settings.platform), LEFT + UnitsConverter.toPixels(DP + 15), alc.getLineY(lineY++));
    	                       alc.add(
    	                           new Label(String.valueOf(Settings.romVersion)),
    	                           LEFT + UnitsConverter.toPixels(DP + 15),
    	                           alc.getLineY(lineY++));
    	                       alc.add(
    	                           new Label(String.valueOf(Settings.screenDensity)),
    	                           LEFT + UnitsConverter.toPixels(DP + 15),
    	                           alc.getLineY(lineY++));
    	                       alc.add(
    	                           new Label(String.valueOf(Settings.deviceFontHeight)),
    	                           LEFT + UnitsConverter.toPixels(DP + 15),
    	                           alc.getLineY(lineY++));
    	                       alc.add(
        	                           new Label(String.valueOf(Font.getDefaultFontSize())),
        	                           LEFT + UnitsConverter.toPixels(DP + 15),
        	                           alc.getLineY(lineY++));
    	                       alc.add(
        	                           new Label(String.valueOf(Settings.screenWidth)+"x"+String.valueOf(Settings.screenHeight)),
        	                           LEFT + UnitsConverter.toPixels(DP + 15),
        	                           alc.getLineY(lineY++));
    	                     }
    	                   };
    	                }    
    	            });
    	           info.popup();
    	         });
    } catch (IOException | ImageException e) {
      e.printStackTrace();
    }
  }
}
