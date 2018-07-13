package totalcross.sample.components.io;

import totalcross.io.DataStream;
import totalcross.io.File;
import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.sys.Time;
import totalcross.sys.Vm;
import totalcross.ui.ComboBox;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.util.Date;
import totalcross.util.InvalidDateException;
import totalcross.util.Vector;

public class FileSample extends ScrollContainer implements Runnable {

	private String rootPath = Settings.appPath + "/";

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
		addLabel("===== testFileList =====");
		Vector v = new Vector(50);
		if (!recursiveList(rootPath, v)) {
			addLabel("recursiveList threw an exception");
		} else {
			addLabel("recursiveList successful");
			int start;
			start = Vm.getTimeStamp();
			String[] files = (String[]) v.toObjectArray();
			if (files == null) {
				addLabel("recursiveList found no files");
				files = new String[] { "No files" };
			} else {
				addLabel("recursiveList found " + files.length);
				if (files[0].charAt(1) == '[') {
					files[0] = files[0].substring(1); // remove the preceding slash
				}
			}
			add(new ComboBox(files), LEFT, TOP, FILL, PREFERRED);
			addLabel("recursiveList took " + (Vm.getTimeStamp() - start) + "ms");
		}
	}

	private void testDirectory() {
		addLabel("" + "===== testDirectory =====");
		try {
			File f = new File(rootPath + "TempDir");
			boolean exists = f.exists();
			addLabel("" + rootPath + "TempDir.exists? " + exists);
			if (!exists) {
				addLabel("Creating dir...");
				f.createDir();
			}
			addLabel("" + rootPath + "TempDir.exists after create? " + f.exists());
			addLabel("" + rootPath + "TempDir isDir?" + f.isDir());
			f.delete();
			f = new File(rootPath + "TempDir");
			addLabel("" + rootPath + "TempDir.exists after delete? " + f.exists());

			addLabel("testDirectory successful");
		} catch (IOException ioe) {
			addLabel("testDirectory threw an exception " + ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	private void testFileRename() {
		addLabel("" + "===== testFileRename =====");
		try {
			File f = new File(rootPath + "TempRename");
			if (!f.exists()) {
				addLabel("" + rootPath + "TempRename does not exist. Creating...");
				f.createDir();
			}
			addLabel("" + rootPath + "TempRename created? " + f.exists());
			addLabel("renaming tempRename to testRename...");
			f.rename(rootPath + "TestRename");
			// file object is now invalid. create a new one.
			f = new File(rootPath + "TestRename");
			addLabel("TestRename.isDir? " + f.isDir());
			f = new File(rootPath + "TestRename/Teste.txt", File.CREATE);
			addLabel("Renaming Teste.txt to Teste2.txt...");
			f.rename(rootPath + "TestRename/Teste2.txt");
			// file object is now invalid. create a new one
			f = new File(rootPath + "TestRename/Teste2.txt");
			addLabel("Teste2.txt exists? " + f.exists());
			addLabel("Teste2.txt isDir? " + f.isDir());
			f.delete();
			f = new File(rootPath + "TestRename");
			addLabel("" + rootPath + "TestRename.isDir? " + f.isDir());
			addLabel("Deleting " + rootPath + "TestRename...");
			f.delete();

			f = new File(rootPath + "TestRename");
			addLabel("" + rootPath + "TestRename.exists? " + f.exists());

			addLabel("testFileRename successful");
		} catch (IOException ioe) {
			addLabel("testFileRename threw an exception:\n" + ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	private void testFileReadWrite() {
		addLabel("" + "===== testFileReadWrite =====");
		try {
			File f = new File(rootPath + "Teste.txt", File.CREATE);
			addLabel("writing values to file...");
			DataStream ds = new DataStream(f);
			ds.writeString("Test");
			ds.writeInt(1234);
			f.setPos(0);
			addLabel("File size now is: " + f.getSize());
			String s = ds.readString();
			int i = ds.readInt();
			addLabel("read: " + s + "," + i);
			addLabel("changing values...");
			f.setPos(0);
			ds.writeString("Abcd");
			f.setPos(0);
			addLabel("File size now is: " + f.getSize());
			s = ds.readString();
			i = ds.readInt();
			addLabel("read: " + s + "," + i);
			f.delete();

			f = new File(rootPath + "Teste.txt");
			addLabel("file deleted? " + !f.exists());

			addLabel("testFileReadWrite successful");
		} catch (totalcross.io.IOException ioe) {
			addLabel("Test failed");
			addLabel("testFileReadWrite threw an exception " + ioe.getMessage());
			ioe.printStackTrace();
		}
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
		addLabel("" + "===== testAttrTime =====");
		addLabel("creating file " + rootPath + "TestAttr.txt");
		try {
			File f = new File(rootPath + "TestAttr.txt", File.CREATE);
			int attr = f.getAttributes();
			addLabel("File attributes: " + getAttrDescription(attr));
			addLabel("Setting to hidden...");
			f.setAttributes(attr | File.ATTR_HIDDEN);
			attr = f.getAttributes();
			addLabel("Attributes changed to " + getAttrDescription(attr));

			Time t;
			addLabel("File Created Time:");
			t = f.getTime(File.TIME_CREATED);
			try {
				addLabel("" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				addLabel("" + ide.getMessage());
			}

			addLabel("File Modified Time:");
			t = f.getTime(File.TIME_MODIFIED);
			try {
				addLabel("" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				addLabel("" + ide.getMessage());
			}

			addLabel("File Acessed Time:");
			t = f.getTime(File.TIME_ACCESSED);
			try {
				addLabel("" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				addLabel("" + ide.getMessage());
			}

			addLabel("Changing Modified time to:");
			addLabel("25/03/2000 13:30:15");
			f.setTime(File.TIME_MODIFIED, new Time(2000, 3, 25, 13, 30, 15, 0));
			addLabel("File Modified Time now is:");
			t = f.getTime(File.TIME_MODIFIED);
			try {
				addLabel("" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				addLabel("" + ide.getMessage());
			}

			addLabel("Deleting file...");
			f.delete();

			f = new File(rootPath + "TestAttr.txt");
			addLabel("File deleted? " + !f.exists());

			addLabel("testAttrTime successful");
		} catch (totalcross.io.IOException ioe) {
			addLabel("Test failed");
			addLabel("testAttrTime threw an exception " + ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	@Override
	public void run() {
		MessageBox mb = new MessageBox("Attention", "Please wait,\nrunning tests...", null);
		mb.popupNonBlocking();
		testSDCards();
		try {
			testFileList();
		} catch (OutOfMemoryError oome) {
			addLabel("Not all files are shown");
		}
		testAttrTime();
		testDirectory();
		testFileRename();
		testFileReadWrite();

		mb.unpop();
	}

	private void testSDCards() {
		if (Settings.platform.equals(Settings.ANDROID)) {
			for (int i = 0; i <= 9; i++) {
				try {
					if (File.isCardInserted(i)) {
						addLabel("/sdcard" + i + " exists");
					}
				} catch (Exception e) {
				}
			}
		}
	}

	@Override
	public void initUI() {
		super.initUI();
		MainWindow.getMainWindow().runOnMainThread(this); // allow animation
	}
	
	private void addLabel(String s)
	{
		Label lbl = new Label(s);
		add(lbl, LEFT, AFTER + 2, SCREENSIZE, PREFERRED);
	}
}
