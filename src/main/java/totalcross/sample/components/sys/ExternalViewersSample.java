package totalcross.sample.components.sys;

import totalcross.io.File;
import totalcross.io.FileNotFoundException;
import totalcross.io.IOException;
import totalcross.io.IllegalArgumentIOException;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.ButtonMenu;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;

public class ExternalViewersSample extends ScrollContainer{
	private ButtonMenu menu;
	String sdcardPath = "/sdcard/extviewers/";
	String jpg = "back1.jpg";
	String pdf = "TotalCrossCompanion.pdf";
	File file;
	private static boolean isAndroid = Settings.platform.equals(Settings.ANDROID);

	@Override
	public void initUI() {
		super.initUI();
		try {
			if (Settings.isIOS()) {
				sdcardPath = Settings.appPath + "/";
			}

			if (isAndroid || Settings.isIOS()) {
				copyFiles2Sdcard();
			}

			if(Settings.platform.equals(Settings.JAVA) || Settings.platform.equals(Settings.WIN32) 
					|| Settings.isWindowsCE()) {
		    	  sdcardPath = Settings.appPath.replace('\\', '/') + "src/main/resources/sys/";
		    }

			String[] items = { "Zoom image", "Read PDF *", "Open HTML page" };
			menu = new ButtonMenu(items, ButtonMenu.SINGLE_COLUMN);
			menu.pressedColor = getBackColor();
			menu.textGap = 400;
			menu.borderGap = 100;
			add(new Label("* Requires a third-party PDF reader"), CENTER, BOTTOM);
			add(menu, LEFT, TOP, FILL, FIT);
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}

	private void copyFiles2Sdcard() throws IllegalArgumentIOException, FileNotFoundException, IOException {
		if (isAndroid) {
			try {
				file = new File(sdcardPath);
				file.createDir();
			} catch (Exception e) {
			}
		}
		// extract the files from the tcz and copy them to the sdcard
		copyFile(jpg);
		copyFile(pdf);

	}

	private String getTargetName(String name) {
		return sdcardPath + name;
	}

	private void copyFile(String name) throws IllegalArgumentIOException, FileNotFoundException, IOException {
		String fullPath = getTargetName(name);
		file = new File(fullPath);
		if (file.exists()) {
			String dir = Convert.getFilePath(fullPath);
			try {
				file = new File(dir);
				file.createDir();
			} catch (Exception e) {
				e.printStackTrace();
			}
			file = new File(fullPath, File.CREATE_EMPTY);
			file.writeAndClose(Vm.getFile(name));
		}
	}

	@Override
	public void onEvent(Event e) {
		if (e.type == ControlEvent.PRESSED && e.target == menu) {
			try {
				int idx = menu.getSelectedIndex();
				int ret = 0;
				switch (idx) {
				case 0:
					ret = Vm.exec("viewer", getTargetName(jpg), 0, true);
					break;
				case 1:
					ret = Vm.exec("viewer", getTargetName(pdf), 0, true);
					break;
				case 2:
					if(isAndroid || Settings.isIOS()) {
						Vm.exec("url", "http://www.google.com", 0, true);
					}
					else new MessageBox("Attention", "Only works on Android or IOS devices").popup();
					break; // always returns 0
				}
				if (ret == -2) {
					new MessageBox("Attention", "Viewer returned: file not found").popup();
				}
			} catch (Exception ee) {
				MessageBox.showException(ee, true);
			}
		}
	}
}
