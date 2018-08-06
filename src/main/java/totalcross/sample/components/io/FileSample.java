package totalcross.sample.components.io;

import totalcross.io.DataStream;
import totalcross.io.File;
import totalcross.io.IOException;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.sys.Time;
import totalcross.sys.Vm;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ListBox;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.font.Font;
import totalcross.util.Date;
import totalcross.util.InvalidDateException;
import totalcross.util.Vector;

public class FileSample extends ScrollContainer implements Runnable {

	private String rootPath = Settings.appPath + "/";
	private int gap = 50;

	private boolean recursiveList(String path, Vector v) {
		if (path == null) {
			return false;
		}
		try {
			File file = new File(path);
			String[] list = file.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					if (list[i] != null) {
						v.addElement(path + list[i]);
						if (list[i].endsWith("/")) {
							recursiveList(path + list[i], v);
						}
					}
				}
			}
			file.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			Vm.debug(ioe.getMessage());
			return false;
		}
		return true;
	}

	private void testFileList() {
		boolean success = true;
		ListBox lb = new ListBox();
		String[] files = null;
		Vector v = new Vector(50);
		if (!recursiveList(rootPath, v)) {
			lb.add("recursiveList threw an exception");
		} else {
			lb.add("recursiveList successful");
			int start;
			start = Vm.getTimeStamp();
			files = (String[]) v.toObjectArray();
			if (files == null) {
				success = false;
				lb.add("recursiveList found no files");
				files = new String[] { "No files" };
			} else {
				lb.add("recursiveList found " + files.length);
				if (files[0].charAt(1) == '[') {
					files[0] = files[0].substring(1); // remove the preceding slash
				}
			}
			lb.add("recursiveList took " + (Vm.getTimeStamp() - start) + "ms");
		}
		addStatusBox(success, new Label("Test File List", CENTER), lb);
		
		if(success) {
			Container box = new Container();
			box.setBackForeColors(Colors.P_200, Colors.ON_P_200);
			add(box, LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			Label title = new Label("List of All Files Found", CENTER);
			title.setBackForeColors(Colors.P_700, Colors.ON_P_700);
			title.setFont(font.asBold());
			box.add(title, LEFT, TOP, FILL, 50 + DP);
			ComboBox cbFiles = new ComboBox(files);
			cbFiles.fillColor = Colors.SURFACE;
			box.add(cbFiles, LEFT + gap, AFTER + gap, FILL - gap, PREFERRED);
			box.add(new Spacer(), LEFT, AFTER, 1, gap/3);
			box.resizeHeight();
		}
	}

	private void testDirectory() {
		boolean success = true;
		ListBox lb = new ListBox();
		try {
			File f = new File(rootPath + "TempDir");
			boolean exists = f.exists();
			lb.add("" + rootPath + "TempDir.exists? " + exists);
			if (!exists) {
				lb.add("Creating dir...");
				f.createDir();
			}
			lb.add("" + rootPath + "TempDir.exists after create? " + f.exists());
			lb.add("" + rootPath + "TempDir isDir?" + f.isDir());
			f.delete();
			f = new File(rootPath + "TempDir");
			lb.add("" + rootPath + "TempDir.exists after delete? " + f.exists());

			lb.add("testDirectory successful");
		} catch (IOException ioe) {
			success = false;
			lb.add("testDirectory threw an exception " + ioe.getMessage());
			ioe.printStackTrace();
		}
		
		addStatusBox(success, new Label("Test Directory", CENTER), lb);
	}

	private void testFileRename() {
		boolean success = true;
		ListBox lb = new ListBox();
		try {
			File f = new File(rootPath + "TempRename");
			if (!f.exists()) {
				lb.add("" + rootPath + "TempRename does not exist. Creating...");
				f.createDir();
			}
			lb.add("" + rootPath + "TempRename created? " + f.exists());
			lb.add("renaming tempRename to testRename...");
			f.rename(rootPath + "TestRename");
			// file object is now invalid. create a new one.
			f = new File(rootPath + "TestRename");
			lb.add("TestRename.isDir? " + f.isDir());
			f = new File(rootPath + "TestRename/Teste.txt", File.CREATE);
			lb.add("Renaming Teste.txt to Teste2.txt...");
			f.rename(rootPath + "TestRename/Teste2.txt");
			// file object is now invalid. create a new one
			f = new File(rootPath + "TestRename/Teste2.txt");
			lb.add("Teste2.txt exists? " + f.exists());
			lb.add("Teste2.txt isDir? " + f.isDir());
			f.delete();
			f = new File(rootPath + "TestRename");
			lb.add("" + rootPath + "TestRename.isDir? " + f.isDir());
			lb.add("Deleting " + rootPath + "TestRename...");
			f.delete();

			f = new File(rootPath + "TestRename");
			lb.add("" + rootPath + "TestRename.exists? " + f.exists());

			lb.add("testFileRename successful");
		} catch (IOException ioe) {
			success = false;
			lb.add("testFileRename threw an exception:\n" + ioe.getMessage());
			ioe.printStackTrace();
		}
		
		addStatusBox(success, new Label("Test File Rename", CENTER), lb);
	}

	private void testFileReadWrite() {
		boolean success = true;
		ListBox lb = new ListBox();

		try {
			File f = new File(rootPath + "Teste.txt", File.CREATE);
			lb.add("writing values to file...");
			DataStream ds = new DataStream(f);
			ds.writeString("Test");
			ds.writeInt(1234);
			f.setPos(0);
			lb.add("File size now is: " + f.getSize());
			String s = ds.readString();
			int i = ds.readInt();
			lb.add("read: " + s + "," + i);
			lb.add("changing values...");
			f.setPos(0);
			ds.writeString("Abcd");
			f.setPos(0);
			lb.add("File size now is: " + f.getSize());
			s = ds.readString();
			i = ds.readInt();
			lb.add("read: " + s + "," + i);
			f.delete();

			f = new File(rootPath + "Teste.txt");
			lb.add("file deleted? " + !f.exists());

			lb.add("testFileReadWrite successful");
		} catch (totalcross.io.IOException ioe) {
			success = false;
			lb.add("Test failed");
			lb.add("testFileReadWrite threw an exception " + ioe.getMessage());
			ioe.printStackTrace();
		}
		
		addStatusBox(success, new Label("Test File Read/Write", CENTER), lb);
	}

	private String getAttrDescription(int attr) {
		String s = "";
		if ((attr & File.ATTR_ARCHIVE) != 0) {
			s += "A";
		}
		if ((attr & File.ATTR_HIDDEN) != 0) {
			s += "H";
		}
		if ((attr & File.ATTR_READ_ONLY) != 0) {
			s += "R";
		}
		if ((attr & File.ATTR_SYSTEM) != 0) {
			s += "S";
		}
		return s;
	}

	private void testAttrTime() {
		boolean success = true;
		ListBox lb = new ListBox();
		lb.add("creating file " + rootPath + "TestAttr.txt");
		try {
			File f = new File(rootPath + "TestAttr.txt", File.CREATE);
			int attr = f.getAttributes();
			lb.add("File attributes: " + getAttrDescription(attr));
			lb.add("Setting to hidden...");
			f.setAttributes(attr | File.ATTR_HIDDEN);
			attr = f.getAttributes();
			lb.add("Attributes changed to " + getAttrDescription(attr));

			Time t;
			lb.add("File Created Time:");
			t = f.getTime(File.TIME_CREATED);
			try {
				lb.add("" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				lb.add("" + ide.getMessage());
			}

			lb.add("File Modified Time:");
			t = f.getTime(File.TIME_MODIFIED);
			try {
				lb.add("" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				lb.add("" + ide.getMessage());
			}

			lb.add("File Acessed Time:");
			t = f.getTime(File.TIME_ACCESSED);
			try {
				lb.add("" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				lb.add("" + ide.getMessage());
			}

			lb.add("Changing Modified time to:");
			lb.add("25/03/2000 13:30:15");
			f.setTime(File.TIME_MODIFIED, new Time(2000, 3, 25, 13, 30, 15, 0));
			lb.add("File Modified Time now is:");
			t = f.getTime(File.TIME_MODIFIED);
			try {
				lb.add("" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				lb.add("" + ide.getMessage());
			}

			lb.add("Deleting file...");
			f.delete();

			f = new File(rootPath + "TestAttr.txt");
			lb.add("File deleted? " + !f.exists());

			lb.add("testAttrTime successful");
		} catch (totalcross.io.IOException ioe) {
			success = false;
			lb.add("Test failed");
			lb.add("testAttrTime threw an exception " + ioe.getMessage());
			ioe.printStackTrace();
		}
		addStatusBox(success, new Label("Test Attribute Time", CENTER), lb);
	}

	@Override
	public void run() {
		MessageBox mb = new MessageBox("Attention", "Please wait,\nrunning tests...", null);
		mb.popupNonBlocking();

		testSDCards();
		try {
			testFileList();
		} catch (OutOfMemoryError oome) {
		}
		testAttrTime();
		testDirectory();
		testFileRename();
		testFileReadWrite();
		add(new Spacer(), LEFT, AFTER, 1, gap/3);
		mb.unpop();
	}

	private void testSDCards() {
		boolean success = true;
		ListBox lb = new ListBox();
		if (Settings.platform.equals(Settings.ANDROID)) {
			for (int i = 0; i <= 9; i++) {
				try {
					if (File.isCardInserted(i)) {
						lb.add("/sdcard" + i + " exists");
					}
				} catch (Exception e) {
					success = false;
				}
			}
		}
		if(lb.size() == 0)
			success = false;
		if(success) {
			addStatusBox(success, new Label("Test SD Cards", CENTER), lb);
		}
	}

	@Override
	public void initUI() {
		super.initUI();
		MainWindow.getMainWindow().runOnMainThread(this); // allow animation
	}
	
	public void addStatusBox(boolean successful, Label title, ListBox lb) {
		lb.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);
		Container box = new Container();
		box.setBackForeColors(Colors.P_200, Colors.ON_P_200);
		
		add(box, LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);

		title.setFont(font.asBold());
		title.setBackForeColors(Colors.P_700, Colors.ON_P_700);
		
		box.add(title, LEFT, TOP, FILL, 50 + DP);

		box.add(lb, LEFT + gap*3, AFTER + gap, FILL - gap*3, PREFERRED);
		
		Label status = new Label("SUCESS", CENTER);
		status.setFont(Font.getFont(true, 20));
		status.transparentBackground = true;
		status.setForeColor(Colors.SUCESS_GREEN);
		if(!successful) {
			status.setForeColor(Colors.RED);
			status.setText("FAIL");
			add(status, LEFT, AFTER + gap, FILL, PREFERRED);
		}
		
		box.add(status, LEFT, AFTER + gap, FILL, PREFERRED);
		box.add(new Spacer(), LEFT, AFTER, 1, gap/3);
		box.resizeHeight();
	}
}
