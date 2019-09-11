package totalcross.sample.components.ui;

import totalcross.io.File;
import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Convert;
import totalcross.sys.InvalidNumberException;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.*;
import totalcross.ui.Container;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.layout.HBox;
import totalcross.ui.media.Camera;
import totalcross.util.UnitsConverter;

public class CameraSample extends BaseScreen {


	private int GAP = UnitsConverter.toPixels(Control.DP + 12);

	private Button btnFilm;
	private Button btnPhoto;
	private Button btnRotate;
	private Label l;
	private ComboBox cbRes;
	private ImageControl ic;
	private Camera camera;
	private RadioGroupController radioGroup;
	private Radio customRadio;
	private Radio nativeRadio;
	private Radio galleryRadio;

	public CameraSample() {
		super("https://totalcross.gitbook.io/playbook/apis/camera");
	}

	@Override
	public void onContent(ScrollContainer content) {
		content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		Settings.showMemoryMessagesAtExit = false;

		btnFilm = new Button("Film");
		btnFilm.setBackColor(Colors.BLUE);
		btnFilm.setForeColor(Color.WHITE);
		btnPhoto = new Button("Photo");
		btnPhoto.setBackColor(Colors.BLUE);
		btnPhoto.setForeColor(Color.WHITE);
		btnRotate = new Button("Rotate");
		btnRotate.setBackColor(Colors.BLUE);
		btnRotate.setForeColor(Color.WHITE);
		btnRotate.setEnabled(false);

		cbRes = new ComboBox(Camera.getSupportedResolutions());
		cbRes.setSelectedIndex(0);

		radioGroup = new RadioGroupController();
		radioGroup.setSelectedIndex(0, false);

		customRadio = new Radio("Custom", radioGroup);
		customRadio.leftJustify = true;
		nativeRadio = new Radio("Native", radioGroup);
		nativeRadio.leftJustify = true;
		galleryRadio = new Radio("Gallery", radioGroup);
		galleryRadio.leftJustify = true;

		Container subContent = new Container();
		content.add(subContent, LEFT, TOP, PARENTSIZE, PARENTSIZE);
		subContent.add(l = new Label(""), LEFT, BOTTOM);

		HBox box = new HBox();
		box.setLayout(HBox.LAYOUT_FILL, HBox.ALIGNMENT_CENTER);
		box.setSpacing(UnitsConverter.toPixels(DP + 8));
		subContent.add(box, LEFT, BEFORE, PARENTSIZE, DP + 48);

		box.add(new Control [] {
				btnFilm, btnPhoto, btnRotate, cbRes
				});

		subContent.add(galleryRadio, LEFT + GAP, BEFORE - GAP);
		subContent.add(nativeRadio, LEFT + GAP, BEFORE - GAP);
		subContent.add(customRadio, AFTER + GAP, SAME);

		subContent.add(ic = new ImageControl(), LEFT + GAP, GAP, FILL - GAP, FIT);
		ic.scaleToFit = true;
		ic.setEventsEnabled(true);
		camera = new Camera();
		camera.allowRotation = true;


		if (Settings.isIOS() || Settings.platform.equals(Settings.WINDOWSPHONE)) {
			btnFilm.setVisible(false);
		}
	}

	@Override
	public void onRemove() {
		releaseMemory();
	}

	private void releaseMemory() {
		ic.setImage(null);
		Vm.gc();
	}

	@Override
	public void onEvent(Event event) {
		try {
			if (event.type == ControlEvent.PRESSED) {
				if (event.target == btnPhoto) {
					btnPhoto.setEnabled(false);
					camera.captureMode = Camera.CAMERACAPTURE_MODE_STILL;
					camera.stillQuality = Camera.CAMERACAPTURE_STILLQUALITY_HIGH;
					// get resolution
					String res = (String) cbRes.getSelectedItem();
					if (res == null) {
						res = "640x480";
					}
					String[] p = Convert.tokenizeString(res, 'x');
					try {
						camera.resolutionWidth = Convert.toInt(p[0]);
						camera.resolutionHeight = Convert.toInt(p[1]);
					} catch (InvalidNumberException ine) {
						// keep original resolution
					}
					//
					l.setText("Starting camera...");
					l.repaintNow();
					camera.defaultFileName = "picture.jpg";
					int sel = radioGroup.getSelectedIndex();
					camera.cameraType = sel == 0 ? Camera.CAMERA_CUSTOM
							: sel == 1 ? Camera.CAMERA_NATIVE_NOCOPY : Camera.CAMERA_FROM_GALLERY;
					releaseMemory();
					String ret = camera.click();
					if (ret != null) {
						l.setText("Loading photo...");
						l.repaintNow();
						File f = new File(ret, File.READ_ONLY);
						int s = f.getSize();
						Image img = null;
						try {
							img = new Image(f);
							// img.transparentColor =
							// Image.NO_TRANSPARENT_COLOR; // doesn't make sense
							// on photos to have a transparent background
							ic.setImage(img);
							btnRotate.setEnabled(true);
							if (Settings.platform.equals(Settings.ANDROID)) {
								ret = copyToSD(f);
							}
							l.setMarqueeText(img.getWidth() + "x" + img.getHeight() + " (" + s + " bytes) " + ret, 100,
									3, -5);
						} catch (OutOfMemoryError oome) // guich@tc126_24
						{
							btnRotate.setEnabled(false);
							if (ret != null) {
								l.setText(ret);
							}
							ic.setImage(null);
							new MessageBox("Error",
									"Out of memory trying to load the image. The picture was taken but it cannot be shown here. If you selected a resolution that is not too big (under 1MP), then this error means that this resolution is not really supported on the camera, alghough it is listed, and a possible big default resolution was selected instead. Select another resolution.")
											.popup();
						}
						f.close();
						if (img != null && Settings.isOpenGL) // guich@tc307:
																// check if
																// there's
																// enough memory
																// to load the
																// image. If
																// there isn't,
																// show just a
																// smaller view
																// of the image
						{
							try {
								img.applyChanges();
							} catch (OutOfMemoryError oome) {
								img = img.smoothScaledFixedAspectRatio(ic.getHeight(), true);
								ic.setImage(img);
								new MessageBox("Error",
										"There's not enough memory to show the whole image, so we will decrease its size to show on screen. Note that the original image will remain untouched.")
												.popup();
							}
						}
						repaint();
					} else {
						btnRotate.setEnabled(false);
						l.setText("User canceled");
					}
					btnPhoto.setEnabled(true);
				} else if (event.target == btnFilm) {
					l.setText("Starting camcorder...");
					l.repaintNow();
					// guich@tc126_24: set the parameters again
					camera.stillQuality = Camera.CAMERACAPTURE_STILLQUALITY_HIGH;
					camera.resolutionWidth = 240;
					camera.resolutionHeight = 320;
					camera.captureMode = Camera.CAMERACAPTURE_MODE_VIDEOONLY;
					camera.defaultFileName = "movie.mpg";
					String ret = camera.click();
					if (ret == null) {
						l.setText("User canceled");
					} else {
						File f = new File(ret, File.READ_ONLY);
						int len = f.getSize();
						if (Settings.platform.equals(Settings.ANDROID)) {
							ret = copyToSD(f);
						}
						f.close();
						l.setMarqueeText(
								ret + " - " + len
										+ " bytes - videos cannot be displayed in TotalCross yet. Resolution set to 240x320",
								100, 3, -5);
					}
					ic.setImage(null);
					btnRotate.setEnabled(false);
				} else if (event.target == btnRotate) {
					ic.setImage(ic.getImage().getRotatedScaledInstance(100, 90, Color.BLACK));
					repaint();
				}
			}
		} catch (Exception e) {
			MessageBox.showException(e, false);
		}
	}

	@SuppressWarnings("resource")
	private String copyToSD(File f) {
		String ret = f.getPath();
		try {
			String dir = ret.endsWith(".3gp") ? "/sdcard/video/" : "/sdcard/photo/";
			try {
				new File(dir).createDir();
			} catch (Exception e) {
			}
			String ret2 = dir + (ret.substring(ret.lastIndexOf('/') + 1));
			File f2 = new File(ret2, File.CREATE_EMPTY);
			f.copyTo(f2);
			f2.close();
			ret += " (and also at " + ret2 + ")";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
