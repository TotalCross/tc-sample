package totalcross.sample.components;

import totalcross.sys.Convert;
import totalcross.sys.InvalidNumberException;
import totalcross.sys.Settings;
import totalcross.ui.AlignedLabelsContainer;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.SideMenuContainer;
import totalcross.ui.Toast;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.EnabledStateChangeEvent;
import totalcross.ui.event.EnabledStateChangeListener;
import totalcross.ui.event.FocusListener;
import totalcross.ui.event.KeyEvent;
import totalcross.ui.event.KeyListener;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.event.PressListener;
import totalcross.ui.event.ValueChangeEvent;
import totalcross.ui.event.ValueChangeHandler;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Graphics;
import totalcross.ui.icon.MaterialIcons;
import totalcross.ui.image.Image;
import totalcross.util.Random;

public class ColorPickerSample extends Container {
	private final int mar = 50;

	private Graphics gColors;
	private int gPixel;
	
	private AlignedLabelsContainer alc;
	private Label lblImage;
	private Label lblCursor;
	private Label lblColor;
	private ImageControl image;
	private Edit cr, cg, cb, ch;

	@Override
	public void initUI() {
		try {
			
			lblImage = new Label("Test the color picker here:", CENTER, Color.BLACK, true);
			add(lblImage, CENTER, TOP + mar, PREFERRED, PREFERRED);
			
			lblCursor = new Label();
			lblCursor.borderColor = Color.WHITE;
			lblCursor.transparentBackground = true;
			
			Image im = new Image("images/colorPickerExample.jpeg").scaledBy(0.55, 1);
			// add(c, CENTER, AFTER, im.getWidth()+mar, im.getHeight()+mar);
			image = new ImageControl(im);
			add(image, LEFT + mar, AFTER + mar, PREFERRED, PREFERRED);

			alc = new AlignedLabelsContainer(new String[] { "Color:", "R:", "G:", "B:"});
			alc.foreColors = new int[] { Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
			add(alc, RIGHT - mar, SAME, image.getWidth(), image.getHeight());

			lblColor = new Label();
			lblColor.setBackColor(Color.BLACK);
			cr = new Edit("999");
			cg = new Edit("999");
			cb = new Edit("999");
			cr.setText("0");
			cg.setText("0");
			cb.setText("0");
			
			alc.add(lblColor, LEFT + mar, alc.getLineY(0), FILL, fmH + Edit.prefH);
			alc.add(cr, LEFT + mar, alc.getLineY(1));
			alc.add(cg, LEFT + mar, alc.getLineY(2));
			alc.add(cb, LEFT + mar, alc.getLineY(3));
			
			// adding all the necessary event listeners
			image.addPenListener(new PenListener() {

				@Override
				public void penUp(PenEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void penDragStart(DragEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void penDragEnd(DragEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void penDrag(DragEvent e) {
					// TODO Auto-generated method stub
					add(lblCursor, e.absoluteX-5, e.absoluteY-60, 10, 10);
					changeEdit(e);
					e.consumed = true;
					repaintNow();
				}

				@Override
				public void penDown(PenEvent e) {
					// TODO Auto-generated method stub
					add(lblCursor, e.absoluteX-5, e.absoluteY-60, 10, 10);
					changeEdit(e);
					e.consumed = true;
					repaintNow();
				}
			});
		} catch (Exception e) {
			MessageBox.showException(e, true);
		}
		cr.addKeyListener(new KeyListener() {
			
			@Override
			public void specialkeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				try {
					if(cr.getText().length() == 0) cr.setText("0");
					if(cg.getText().length() == 0) cg.setText("0");
					if(cb.getText().length() == 0) cb.setText("0");
					gPixel = Color.getRGB(Convert.toInt(cr.getText()), Convert.toInt(cg.getText()), Convert.toInt(cb.getText()));
					lblColor.setBackColor(gPixel);
					repaintNow();
				} catch (InvalidNumberException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub		
				try {
					if(Convert.toInt(cr.getText()) > 255) cr.setText("255");
					if(Convert.toInt(cg.getText()) > 255) cg.setText("255");
					if(Convert.toInt(cb.getText()) > 255) cb.setText("255");

					gPixel = Color.getRGB(Convert.toInt(cr.getText()), Convert.toInt(cg.getText()), Convert.toInt(cb.getText()));
					lblColor.setBackColor(gPixel);
					repaintNow();
				} catch (InvalidNumberException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void actionkeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
}
	
	private void changeEdit(PenEvent e) {
		gColors = image.getGraphics();
		gPixel = gColors.getPixel(e.x, e.y);
		lblColor.setBackColor(gPixel);
		cr.setText(Convert.toString(Color.getRed(gPixel)));
		cg.setText(Convert.toString(Color.getGreen(gPixel)));
		cb.setText(Convert.toString(Color.getBlue(gPixel)));
	}
}
