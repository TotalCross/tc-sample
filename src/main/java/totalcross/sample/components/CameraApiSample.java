package totalcross.sample.components;

import totalcross.hardware.camera.Camera;
import totalcross.hardware.camera.CameraException;
import totalcross.hardware.camera.CameraProperties;
import totalcross.hardware.map.Map;
import totalcross.io.File;
import totalcross.io.IOException;
import totalcross.sample.util.Colors;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class CameraApiSample extends Container {
	@Override
	public void initUI() {
		final int w = 150;
		final int h = 50;

		Button b1 = new Button("Open\nCamera");
		b1.setBackColor(Colors.P_DARK);
		b1.setForeColor(Color.WHITE);
		b1.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				try {
					CameraProperties cp = new CameraProperties();
					cp.previewSize = 1370;
					
					Camera.open(cp);
				} catch (CameraException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(b1, LEFT + 120, TOP + 40, PREFERRED + w, PREFERRED + h);

		Button b2 = new Button("Close\nCamera");
		b2.setBackColor(Colors.P_DARK);
		b2.setForeColor(Color.WHITE);
		b2.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				Camera.close();
			}
		});
		add(b2, AFTER + 40, SAME, PREFERRED + w, PREFERRED + h);

//		Button b3 = new Button("Capture\nPhoto");
//		b3.setBackColor(Colors.P_DARK);
//		b3.setForeColor(Color.WHITE);
//		b3.addPressListener(new PressListener() {
//			@Override
//			public void controlPressed(ControlEvent e) {
//				String s = Camera.captureImage();
//
//				Vm.sleep(2000);
//
//				System.out.println("Received: " + s);
//				File f = null;
//				Image img = null;
//				try {
//					f = new File(s, File.READ_ONLY);
//					img = new Image(f);
//
//					ImageControl imc = new ImageControl(img);
//					CameraApiSample.this.add(imc, RIGHT, TOP + 250, 800, 450);
//
//					System.out.println("Return: " + s);
//				} catch (IOException | ImageException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//		add(b3, AFTER + 40, SAME, PREFERRED + w, PREFERRED + h);
	}
}
