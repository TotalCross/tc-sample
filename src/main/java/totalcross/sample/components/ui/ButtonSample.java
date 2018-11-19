package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

public class ButtonSample extends ScrollContainer{

	
	private Button btnDefaultColor;
	private Button btnRed;
	private Button btnGreen;
	private Button btnBlue;
	private Button btnFull;
	private Button btnBlock;
	private Button btnRound;
	private Button btnLeftImage;
	private Button btnRightImage;
	private Button btnImage;
	private Button btnLarge;
	private Button btnBorderless;
	private Button btnDefaultSize;
	private Button btnSmall;
	private int center;
	private Label lImages = new Label("Images", CENTER);
	
	public ButtonSample() {
		center = Settings.screenWidth/2;
	}
	
	@Override
	public void initUI()
	{
		super.initUI();

		try {
			setInsets(0, 0, 0, UnitsConverter.toPixels(DP + 8));
			setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
			setScrollBars(false, true);
			//Colors
			Label lColors = new Label("Colors", CENTER);
			Font bold = lColors.getFont().asBold();
			lColors.setFont(bold);
			btnDefaultColor = new Button("Default");
			btnRed = new Button("Red");
			btnRed.setBackForeColors(Color.getRGB("d34419"), Color.WHITE);
			btnGreen = new Button("Green");
			btnGreen.setBackForeColors(Color.getRGB("18d25a"), Color.WHITE);
			btnBlue = new Button("Blue");
			btnBlue.setBackForeColors(Color.getRGB("1878d1"), Color.WHITE);
			//Shapes
			Label lShapes = new Label("Shapes", CENTER);
			lShapes.setFont(bold);
			btnFull = new Button("Full Button");
			btnFull.setBackForeColors(Colors.P_700, Color.WHITE);
			btnBlock = new Button("Rounded Corners Button", Button.BORDER_ROUND);
			btnBlock.roundBorderFactor = 7;
			btnBlock.setBackForeColors(Colors.P_700, Color.WHITE);
			btnRound = new Button("Borderless Button", Button.BORDER_NONE);
			btnRound.setForeColor(Colors.P_700);
			btnBorderless = new Button("Outlined Button", Button.BORDER_OUTLINED);
			btnBorderless.setBackForeColors(Colors.P_700, Color.WHITE);
			//Images
			lImages.setFont(bold);
			Image img = new Image("images/bt_info.png").getHwScaledInstance(UnitsConverter.toPixels(DP + 18), UnitsConverter.toPixels(DP + 18));
			btnLeftImage = new Button("Left Image", img, RIGHT, UnitsConverter.toPixels(DP + 8));
			btnLeftImage.setBackForeColors(Colors.P_700, Color.WHITE);
			btnRightImage = new Button("Right Image", img, LEFT, UnitsConverter.toPixels(DP + 8));
			btnRightImage.setBackForeColors(Colors.P_700, Color.WHITE);
			btnImage = new Button(img.getHwScaledInstance(UnitsConverter.toPixels(DP + 24), UnitsConverter.toPixels(DP + 24)));
			btnImage.setBackForeColors(Colors.P_700, Color.WHITE);
			//Sizes
			Label lSizes = new Label("Sizes", CENTER);
			lSizes.setFont(bold);
			btnLarge = new Button("Large");
			btnLarge.setBackForeColors(Colors.P_700, Color.WHITE);
			btnLarge.setFont(Font.getFont(btnLarge.getFont().size*3/2));
			btnDefaultSize = new Button("Default");
			btnDefaultSize.setBackForeColors(Colors.P_700, Color.WHITE);
			btnSmall = new Button("Small");
			btnSmall.setFont(Font.getFont(btnLarge.getFont().size*3/4));
			btnSmall.setBackForeColors(Colors.P_700, Color.WHITE);
			
			add(lColors, LEFT, TOP + UnitsConverter.toPixels(DP + 8), FILL, DP + 36);
			add(btnDefaultColor, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			add(btnGreen, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			add(btnRed, BEFORE - UnitsConverter.toPixels(DP + 8), SAME);
			add(btnBlue, AFTER + UnitsConverter.toPixels(DP + 8), SAME, btnGreen);
			add(lShapes, LEFT, AFTER + UnitsConverter.toPixels(DP + 8), FILL, DP + 36);
			add(btnFull, CENTER, AFTER + UnitsConverter.toPixels(DP + 8), PARENTSIZE + 95, PREFERRED);
			add(btnBlock, CENTER, AFTER + UnitsConverter.toPixels(DP + 8), PARENTSIZE + 95, PREFERRED);
			add(btnBorderless, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			add(btnRound, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			add(lImages, LEFT, AFTER + UnitsConverter.toPixels(DP + 8), FILL, DP + 36);
			add(btnRightImage, center + UnitsConverter.toPixels(DP + 4), AFTER + UnitsConverter.toPixels(DP + 8));
			add(btnLeftImage, BEFORE - UnitsConverter.toPixels(DP + 4), SAME);
			add(btnImage, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			add(lSizes, LEFT, AFTER + UnitsConverter.toPixels(DP + 8), FILL, DP + 36);
			add(btnLarge, LEFT + UnitsConverter.toPixels(DP + 8), AFTER + UnitsConverter.toPixels(DP + 8), btnLarge.getPreferredWidth() <= 48 ? DP + 96 : btnLarge.getPreferredWidth() + UnitsConverter.toPixels(DP + 48), DP + 54);
			add(btnDefaultSize, AFTER +UnitsConverter.toPixels(DP + 8) , CENTER_OF);
			add(btnSmall, AFTER + UnitsConverter.toPixels(DP + 8), CENTER_OF, btnSmall.getPreferredWidth() <= 24 ? DP + 48 : btnSmall.getPreferredWidth() + UnitsConverter.toPixels(DP + 24), DP + 27, btnDefaultSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void reposition() {
		center = Settings.screenWidth/2;
		btnRightImage.setRect(center + UnitsConverter.toPixels(DP + 4), AFTER + UnitsConverter.toPixels(DP + 8), btnRightImage.getPreferredWidth() <= 32 ? DP + 64 : btnRightImage.getPreferredWidth() + UnitsConverter.toPixels(DP + 32), DP + 36, lImages);
		btnLeftImage.setRect(BEFORE - UnitsConverter.toPixels(DP + 4), SAME, btnLeftImage.getPreferredWidth() <= 32 ? DP + 64 : btnLeftImage.getPreferredWidth() + UnitsConverter.toPixels(DP + 32), DP + 36, btnRightImage);
	}
}
