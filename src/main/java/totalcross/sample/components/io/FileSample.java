package totalcross.sample.components.io;

import totalcross.io.DataStream;
import totalcross.io.File;
import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.sys.Time;
import totalcross.sys.Vm;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.dialog.MessageBox;
import totalcross.util.Date;
import totalcross.util.InvalidDateException;
import totalcross.util.Vector;

public class FileSample extends Container implements Runnable {
	private Label logLabel;
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
		logLabel.setText("===== testFileList =====");
		Vector v = new Vector(50);
		if (!recursiveList(rootPath, v)) {
			logLabel.setText(logLabel.getText() + "\nrecursiveList threw an exception");
		} else {
			logLabel.setText(logLabel.getText() + "\nrecursiveList successful");
			int start;
			start = Vm.getTimeStamp();
			String[] files = (String[]) v.toObjectArray();
			if (files == null) {
				logLabel.setText(logLabel.getText() + "\nrecursiveList found no files");
				files = new String[] { "No files" };
			} else {
				logLabel.setText(logLabel.getText() + "\nrecursiveList found " + files.length);
				if (files[0].charAt(1) == '[') {
					files[0] = files[0].substring(1); // remove the preceding slash
				}
			}
			add(new ComboBox(files), LEFT, TOP, FILL, PREFERRED);
			logLabel.setText(logLabel.getText() + "\nrecursiveList took " + (Vm.getTimeStamp() - start) + "ms");
		}
	}

	private void testDirectory() {
		logLabel.setText(logLabel.getText() + "\n" + "===== testDirectory =====");
		try {
			File f = new File(rootPath + "TempDir");
			boolean exists = f.exists();
			logLabel.setText(logLabel.getText() + "\n" + rootPath + "TempDir.exists? " + exists);
			if (!exists) {
				logLabel.setText(logLabel.getText() + "\nCreating dir...");
				f.createDir();
			}
			logLabel.setText(logLabel.getText() + "\n" + rootPath + "TempDir.exists after create? " + f.exists());
			logLabel.setText(logLabel.getText() + "\n" + rootPath + "TempDir isDir?" + f.isDir());
			f.delete();
			f = new File(rootPath + "TempDir");
			logLabel.setText(logLabel.getText() + "\n" + rootPath + "TempDir.exists after delete? " + f.exists());

			logLabel.setText(logLabel.getText() + "\ntestDirectory successful");
		} catch (IOException ioe) {
			logLabel.setText(logLabel.getText() + "\ntestDirectory threw an exception " + ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	private void testFileRename() {
		logLabel.setText(logLabel.getText() + "\n" + "===== testFileRename =====");
		try {
			File f = new File(rootPath + "TempRename");
			if (!f.exists()) {
				logLabel.setText(logLabel.getText() + "\n" + rootPath + "TempRename does not exist. Creating...");
				f.createDir();
			}
			logLabel.setText(logLabel.getText() + "\n" + rootPath + "TempRename created? " + f.exists());
			logLabel.setText(logLabel.getText() + "\nrenaming tempRename to testRename...");
			f.rename(rootPath + "TestRename");
			// file object is now invalid. create a new one.
			f = new File(rootPath + "TestRename");
			logLabel.setText(logLabel.getText() + "\nTestRename.isDir? " + f.isDir());
			f = new File(rootPath + "TestRename/Teste.txt", File.CREATE);
			logLabel.setText(logLabel.getText() + "\nRenaming Teste.txt to Teste2.txt...");
			f.rename(rootPath + "TestRename/Teste2.txt");
			// file object is now invalid. create a new one
			f = new File(rootPath + "TestRename/Teste2.txt");
			logLabel.setText(logLabel.getText() + "\nTeste2.txt exists? " + f.exists());
			logLabel.setText(logLabel.getText() + "\nTeste2.txt isDir? " + f.isDir());
			f.delete();
			f = new File(rootPath + "TestRename");
			logLabel.setText(logLabel.getText() + "\n" + rootPath + "TestRename.isDir? " + f.isDir());
			logLabel.setText(logLabel.getText() + "\nDeleting " + rootPath + "TestRename...");
			f.delete();

			f = new File(rootPath + "TestRename");
			logLabel.setText(logLabel.getText() + "\n" + rootPath + "TestRename.exists? " + f.exists());

			logLabel.setText(logLabel.getText() + "\ntestFileRename successful");
		} catch (IOException ioe) {
			logLabel.setText(logLabel.getText() + "\ntestFileRename threw an exception:\n" + ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	private void testFileReadWrite() {
		logLabel.setText(logLabel.getText() + "\n" + "===== testFileReadWrite =====");
		try {
			File f = new File(rootPath + "Teste.txt", File.CREATE);
			logLabel.setText(logLabel.getText() + "\nwriting values to file...");
			DataStream ds = new DataStream(f);
			ds.writeString("Test");
			ds.writeInt(1234);
			f.setPos(0);
			logLabel.setText(logLabel.getText() + "\nFile size now is: " + f.getSize());
			String s = ds.readString();
			int i = ds.readInt();
			logLabel.setText(logLabel.getText() + "\nread: " + s + "," + i);
			logLabel.setText(logLabel.getText() + "\nchanging values...");
			f.setPos(0);
			ds.writeString("Abcd");
			f.setPos(0);
			logLabel.setText(logLabel.getText() + "\nFile size now is: " + f.getSize());
			s = ds.readString();
			i = ds.readInt();
			logLabel.setText(logLabel.getText() + "\nread: " + s + "," + i);
			f.delete();

			f = new File(rootPath + "Teste.txt");
			logLabel.setText(logLabel.getText() + "\nfile deleted? " + !f.exists());

			logLabel.setText(logLabel.getText() + "\ntestFileReadWrite successful");
		} catch (totalcross.io.IOException ioe) {
			logLabel.setText(logLabel.getText() + "\nTest failed");
			logLabel.setText(logLabel.getText() + "\ntestFileReadWrite threw an exception " + ioe.getMessage());
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
		logLabel.setText(logLabel.getText() + "\n" + "===== testAttrTime =====");
		logLabel.setText(logLabel.getText() + "\ncreating file " + rootPath + "TestAttr.txt");
		try {
			File f = new File(rootPath + "TestAttr.txt", File.CREATE);
			int attr = f.getAttributes();
			logLabel.setText(logLabel.getText() + "\nFile attributes: " + getAttrDescription(attr));
			logLabel.setText(logLabel.getText() + "\nSetting to hidden...");
			f.setAttributes(attr | File.ATTR_HIDDEN);
			attr = f.getAttributes();
			logLabel.setText(logLabel.getText() + "\nAttributes changed to " + getAttrDescription(attr));

			Time t;
			logLabel.setText(logLabel.getText() + "\nFile Created Time:");
			t = f.getTime(File.TIME_CREATED);
			try {
				logLabel.setText(logLabel.getText() + "\n" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				logLabel.setText(logLabel.getText() + "\n" + ide.getMessage());
			}

			logLabel.setText(logLabel.getText() + "\nFile Modified Time:");
			t = f.getTime(File.TIME_MODIFIED);
			try {
				logLabel.setText(logLabel.getText() + "\n" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				logLabel.setText(logLabel.getText() + "\n" + ide.getMessage());
			}

			logLabel.setText(logLabel.getText() + "\nFile Acessed Time:");
			t = f.getTime(File.TIME_ACCESSED);
			try {
				logLabel.setText(logLabel.getText() + "\n" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				logLabel.setText(logLabel.getText() + "\n" + ide.getMessage());
			}

			logLabel.setText(logLabel.getText() + "\nChanging Modified time to:");
			logLabel.setText(logLabel.getText() + "\n25/03/2000 13:30:15");
			f.setTime(File.TIME_MODIFIED, new Time(2000, 3, 25, 13, 30, 15, 0));
			logLabel.setText(logLabel.getText() + "\nFile Modified Time now is:");
			t = f.getTime(File.TIME_MODIFIED);
			try {
				logLabel.setText(logLabel.getText() + "\n" + new Date(t) + " " + t);
			} catch (InvalidDateException ide) {
				logLabel.setText(logLabel.getText() + "\n" + ide.getMessage());
			}

			logLabel.setText(logLabel.getText() + "\nDeleting file...");
			f.delete();

			f = new File(rootPath + "TestAttr.txt");
			logLabel.setText(logLabel.getText() + "\nFile deleted? " + !f.exists());

			logLabel.setText(logLabel.getText() + "\ntestAttrTime successful");
		} catch (totalcross.io.IOException ioe) {
			logLabel.setText(logLabel.getText() + "\nTest failed");
			logLabel.setText(logLabel.getText() + "\ntestAttrTime threw an exception " + ioe.getMessage());
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
			logLabel.setText(logLabel.getText() + "\nNot all files are shown");
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
						logLabel.setText(logLabel.getText() + "\n/sdcard" + i + " exists");
					}
				} catch (Exception e) {
				}
			}
		}
	}

	@Override
	public void initUI() {
		super.initUI();
		logLabel = new Label();
		add(logLabel, LEFT, TOP + 50 + fmH * 2, FILL, FILL);
		MainWindow.getMainWindow().runOnMainThread(this); // allow animation
	}
}
