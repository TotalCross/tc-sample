package totalcross.sample.components.ui;

import totalcross.io.File;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.ColorList;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Whiteboard;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class HWSignatureSample extends Container {
	private int k = fmH * 6;
	private int mar = Settings.screenHeight/8;
	Button save;
	Button clear;
	Button load;
	Whiteboard paint;
	ComboBox cbColors;
	String fileName;

	@Override
	public void initUI() {
		super.initUI();
		fileName = Settings.platform.equals(Settings.ANDROID) ? "/sdcard/handwrite.png" : "device/handwrite.png";

		save = new Button("Save");
		save.setBackForeColors(Colors.P_DARK, Color.WHITE);
		clear = new Button("Clear");
		clear.setBackForeColors(Colors.P_DARK, Color.WHITE);
		load = new Button("Load");
		load.setBackForeColors(Colors.P_DARK, Color.WHITE);
		paint = new Whiteboard();

		// center the save/load/clear buttons on screen
		
		load.setBackColor(Color.getRGB(69, 131, 212));
		add(load, CENTER, BOTTOM - mar, PARENTSIZE + 30, PREFERRED + k);
		save.setBackColor(Color.getRGB(69, 131, 212));
		add(save, LEFT + mar, SAME, SAME, SAME); // before the load button
		clear.setBackColor(Color.getRGB(69, 131, 212));
		add(clear, RIGHT - mar, SAME, SAME, SAME, load); // after the load button add the paint control with a specified
														// width
		add(paint);
		paint.borderColor = Color.BLACK;
		paint.setRect(LEFT + mar, TOP + Settings.screenHeight/6, FILL - mar, SCREENSIZE+40); // add the status
		add(cbColors = new ComboBox(new ColorList()), CENTER, AFTER + mar, paint);
		cbColors.setSelectedIndex(cbColors.indexOf(new ColorList.Item(Color.BLACK))); // select color 0
	}
	
	@Override
	public void reposition() {
		super.reposition();
		paint.clear();
	}
	
	@Override
	public void onEvent(Event event) {
		if (event.type == ControlEvent.PRESSED) {
			if (event.target == cbColors) {
				paint.setPenColor(((ColorList.Item) cbColors.getSelectedItem()).value);
			} else if (event.target == clear) {
				paint.clear();
			} else if (event.target == save) {
				// note: we could use the saveTo method of totalcross.ui.image.Image, but for
				// example purposes, we will show how to use the other method, createPng
				// create a buffer to store the image
				try {
					// create
					File f = new File(fileName, File.CREATE_EMPTY);
					paint.getImage().createPng(f);
					f.close();
				} catch (Exception e) {
					MessageBox.showException(e, false);
				}
			} else if (event.target == load) {
				try {
					try {
						File f = new File(fileName, File.READ_ONLY);
						int size = f.getSize();
						byte test[] = new byte[size];
						f.readBytes(test, 0, size);
						f.close();
						Image img = new Image(test);
						paint.setImage(img);
					} catch (totalcross.io.FileNotFoundException e) {
						return;
					}
				} catch (Exception e) {
					MessageBox.showException(e, false);
				}
			}
		}
	}
}
