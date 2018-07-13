package totalcross.sample.components.media;

import totalcross.io.File;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.media.Sound;

public class MediaSample extends Container {
	@Override
	public void initUI() {
		try {
			super.initUI();
			// you may write the file only once; here we write always because it is pretty
			// small, just 29k
			final boolean isWAV = Settings.isWindowsCE() || Settings.platform.equals(Settings.WIN32);
			final String ext = isWAV ? "wav" : "mp3";
			// note: we're using ext concatenation here to prevent the wav/mp3 files being
			// added twice to the TCZ
			new File("device/sample." + ext, File.CREATE_EMPTY)
					.writeAndClose(Vm.getFile(isWAV ? "tc/samples/api/sample.wav" : "tc/samples/api/sample.mp3"));

			final Button b = new Button(isWAV ? "Play WAV sample" : "Play MP3 sample");
			add(b, CENTER, CENTER);
			b.addPressListener(new PressListener() {
				@Override
				public void controlPressed(ControlEvent e) {
					try {
						Sound.play("device/sample." + ext);
					} catch (Exception ee) {
						MessageBox.showException(ee, true);
					}
				}
			});
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}
	}
}
