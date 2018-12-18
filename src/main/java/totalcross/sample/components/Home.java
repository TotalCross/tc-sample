package totalcross.sample.components;

import totalcross.io.IOException;
import totalcross.sample.util.Colors;
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
      lbWelcome.setFont(Font.getFont("Lato Light", false, lbWelcome.getFont().size + 30));
      lbWelcome.setForeColor(Color.WHITE);
      add(lbWelcome, CENTER, AFTER + 40);

      Label lbWelcome3 = new Label("Use the menu on the top left\n     to see the components");
      lbWelcome3.setFont(Font.getFont("Lato Bold", false, lbWelcome3.getFont().size));
      lbWelcome3.setForeColor(Color.WHITE);
      add(lbWelcome3, CENTER, AFTER + 40);
      
      final int w = 80;
      final int h = 50;
      
      Button b1 = new Button("Camera\nOpen");
      b1.setBackColor(Colors.P_DARK);
      b1.setForeColor(Color.WHITE);
      b1.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				try {
					Camera.open();
				} catch (CameraException e1) {
					e1.printStackTrace();
				}
			}
		});
      add(b1, LEFT + 120, AFTER + 10, PREFERRED + w, PREFERRED + h);
      
      Button b2 = new Button("Camera\nClose");
      b2.setBackColor(Colors.P_DARK);
      b2.setForeColor(Color.WHITE);
      b2.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				Camera.close();
			}
		});
      add(b2, AFTER + 40, SAME, PREFERRED + w, PREFERRED + h);
      
      Button b3 = new Button("Set 'n\nCapture");
      b3.setBackColor(Colors.P_DARK);
      b3.setForeColor(Color.WHITE);
      b3.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				Camera.setProperties(new CameraProperties());
				String s = Camera.captureImage();
				
				Vm.sleep(2000);
				
				System.out.println("Received: " + s);
				File f = null;
				Image img = null;
				try {
					f = new File(s, File.READ_ONLY);
					img = new Image(f);
					
					ImageControl imc = new ImageControl(img);
					Home.this.add(imc, RIGHT, TOP);
					
					System.out.println("Return: " + s);
				} catch (IOException | ImageException e1) {
					e1.printStackTrace();
				}
			}
		});
      add(b3, AFTER + 40, SAME, PREFERRED + w, PREFERRED + h);
      
      Button b4 = new Button("Start\nRec");
      b4.setBackColor(Colors.P_DARK);
      b4.setForeColor(Color.WHITE);
      b4.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				Camera.startMovieRecording();
			}
		});
      add(b4, AFTER + 40, SAME, PREFERRED + w, PREFERRED + h);
      
      Button b5 = new Button("Stop\nRec");
      b5.setBackColor(Colors.P_DARK);
      b5.setForeColor(Color.WHITE);
      b5.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				String s = Camera.endMovieRecording();
				System.out.println("Return: " + s);
			}
		});
      add(b5, AFTER + 40, SAME, PREFERRED + w, PREFERRED + h);
		
      Button b6 = new Button("Abort\nRec");
      b6.setBackColor(Colors.P_DARK);
      b6.setForeColor(Color.WHITE);
      b6.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				Camera.abortMovieRecording();
			}
		});
      add(b6, AFTER + 40, SAME, PREFERRED + w, PREFERRED + h);

      Button btSystemInfo = new Button("System information");
      btSystemInfo.setFont(Font.getFont("Lato Bold", false, lbWelcome3.getFont().size));
      btSystemInfo.setForeColor(Color.WHITE);
      add(btSystemInfo, CENTER, BOTTOM - 100, PREFERRED + 100, PREFERRED);
      btSystemInfo.addPressListener(
    	         (e) -> {
    	           MaterialWindow info =
    	               new MaterialWindow("Information", new Presenter() {
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
    	                                 "Default font size"
    	                               });
    	                       add(alc, LEFT, TOP, FILL, FILL);
    	                       int padding = (int) (Settings.screenDensity * 8);
    	                       alc.setInsets(padding, padding, padding, padding);
    	                       alc.setForeColor(Color.BLACK);
    	                       int lineY = 0;
    	                       alc.add(new Label(Settings.appVersion), LEFT + 100, alc.getLineY(lineY++));
    	                       alc.add(new Label(Settings.versionStr), LEFT + 100, alc.getLineY(lineY++));
    	                       alc.add(new Label(Settings.platform), LEFT + 100, alc.getLineY(lineY++));
    	                       alc.add(
    	                           new Label(String.valueOf(Settings.romVersion)),
    	                           LEFT + 100,
    	                           alc.getLineY(lineY++));
    	                       alc.add(
    	                           new Label(String.valueOf(Settings.screenDensity)),
    	                           LEFT + 100,
    	                           alc.getLineY(lineY++));
    	                       alc.add(
    	                           new Label(String.valueOf(Settings.deviceFontHeight)),
    	                           LEFT + 100,
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
