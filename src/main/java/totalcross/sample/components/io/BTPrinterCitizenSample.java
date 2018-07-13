package totalcross.sample.components.io;

import totalcross.io.Stream;
import totalcross.io.device.printer.CitizenPrinter;
import totalcross.io.device.printer.MonoImage;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Graphics;

public class BTPrinterCitizenSample extends PrinterSampleBase {
	@Override
	protected void printSample(Stream s) throws Exception {
		CitizenPrinter cp = null;
		try {
			cp = new CitizenPrinter(s);
			// change the font and print something
			cp.setFont(false, false, false, false, false);
			cp.print("Bárbara Hazan\n\nDaddy loves you!\n");
			cp.newLine();

			// create a dynamic image...
			int k = 100;
			MonoImage img = new MonoImage(k, k);
			Graphics g = img.getGraphics();
			g.backColor = Color.WHITE;
			g.fillRect(0, 0, k, k);
			g.foreColor = Color.BLACK;
			g.drawRect(0, 0, k - 1, k - 1);
			g.drawLine(0, 0, k, k);
			g.drawLine(k, 0, 0, k);

			// ...and print it
			cp.print(img);
			cp.newLine();

			// change the font to a big one
			cp.setFont(true, true, true, false, true);
			cp.print("*** Barbara ***\n");
			cp.setFont(true, false, false, false, false);

			// print a png file.
			try {
				cp.print(new MonoImage("tc/samples/api/io/device/barbara.png"));
				cp.newLine();
			} catch (OutOfMemoryError oome) {
				add(new Label("No memory to load image"), CENTER, AFTER);
			}
		} catch (Exception e) {
			MessageBox.showException(e, true);
		} finally {
			try {
				cp.close();
			} catch (Exception e) {
			}
		}
	}
}
