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

public class MapApiSample extends Container {

	@Override
	public void initUI() {
		final int w = 150;
		final int h = 50;

		Button b1 = new Button("Open\nMap");
		b1.setBackColor(Colors.P_DARK);
		b1.setForeColor(Color.WHITE);
		b1.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				Map.mapOpen();
			}
		});
		add(b1, LEFT + 120, TOP + 40, PREFERRED + w, PREFERRED + h);

		Button b2 = new Button("Close\nMap");
		b2.setBackColor(Colors.P_DARK);
		b2.setForeColor(Color.WHITE);
		b2.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				Map.mapClose();
			}
		});
		add(b2, AFTER + 40, SAME, PREFERRED + w, PREFERRED + h);
	}
}
