package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class ButtonSample extends ScrollContainer{

	
	private final static int dp = (int) Settings.screenDensity;
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
			setInsets(0, 0, 0, 8 * dp);
			setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
			setScrollBars(false, true);
			//Colors
			Label lColors = new Label("Colors", CENTER);
			Font bold = lColors.getFont().asBold();
			lColors.setFont(bold);
			btnDefaultColor = new Button("Default");
			btnRed = new Button("Red");
			btnRed.setBackForeColors(Color.getRGB("bb0000"), Color.BLACK);
			btnGreen = new Button("Green");
			btnGreen.setBackForeColors(Color.getRGB("00bb00"), Color.BLACK);
			btnBlue = new Button("Blue");
			btnBlue.setBackForeColors(Color.getRGB("0000bb"), Color.WHITE);
			//Shapes
			Label lShapes = new Label("Shapes", CENTER);
			lShapes.setFont(bold);
			btnFull = new Button("Full Button");
			btnFull.setBackForeColors(Colors.P_700, Color.WHITE);
			btnBlock = new Button("Block Button", Button.BORDER_ROUND);
			btnBlock.roundBorderFactor = 7;
			btnBlock.setBackForeColors(Colors.P_700, Color.WHITE);
			btnRound = new Button("Round Button", Button.BORDER_ROUND);
			btnRound.setBackForeColors(Colors.P_700, Color.WHITE);
			btnBorderless = new Button("Borderless Button", Button.BORDER_NONE);
			btnBorderless.setBackForeColors(Colors.P_700, Color.WHITE);
			//Images
			lImages.setFont(bold);
			Image img = new Image("images/bt_info.png").smoothScaledFixedAspectRatio(18 * dp, true);
			btnLeftImage = new Button("Left Image", img, RIGHT, 8 * dp);
			btnLeftImage.setBackForeColors(Colors.P_700, Color.WHITE);
			btnRightImage = new Button("Right Image", img, LEFT, 8 * dp);
			btnRightImage.setBackForeColors(Colors.P_700, Color.WHITE);
			btnImage = new Button(img);
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
			
			add(lColors, LEFT, TOP + 8 * dp, FILL, DP + 36);
			add(btnDefaultColor, CENTER, AFTER + 8 * dp, btnDefaultColor.getPreferredWidth() <= 32 ? DP + 64 : btnDefaultColor.getPreferredWidth() + 32 * dp, DP + 36);
			add(btnGreen, CENTER, AFTER + 8 * dp, btnGreen.getPreferredWidth() <= 32 ? DP + 64 : btnGreen.getPreferredWidth() + 32 * dp, DP + 36);
			add(btnRed, BEFORE - 8 * dp, SAME, btnRed.getPreferredWidth() <= 32 ? DP + 64 : btnRed.getPreferredWidth() + 32 * dp, DP + 36);
			add(btnBlue, AFTER + 8 * dp, SAME, btnBlue.getPreferredWidth() <= 32 ? DP + 64 : btnBlue.getPreferredWidth() + 32 * dp, DP + 36, btnGreen);
			add(lShapes, LEFT, AFTER + 8 * dp, FILL, DP + 36);
			add(btnFull, CENTER, AFTER + 8 * dp, PARENTSIZE + 95, DP + 36);
			add(btnBlock, CENTER, AFTER + 8 * dp, PARENTSIZE + 95, DP + 36);
			add(btnBorderless, CENTER, AFTER + 8 * dp, btnBorderless.getPreferredWidth() <  32 ? DP + 64 : btnBorderless.getPreferredWidth() + 32 * dp, DP + 36);
			add(btnRound, CENTER, AFTER + 8 * dp, btnRound.getPreferredWidth() <= 32 ? DP + 64 : btnRound.getPreferredWidth() + 32 * dp, DP + 36);
			add(lImages, LEFT, AFTER + 8 * dp, FILL, DP + 36);
			add(btnRightImage, center + 4 * dp, AFTER + 8 * dp, btnRightImage.getPreferredWidth() <= 32 ? DP + 64 : btnRightImage.getPreferredWidth() + 32 * dp, DP + 36);
			add(btnLeftImage, BEFORE - 4 * dp, SAME, btnLeftImage.getPreferredWidth() <= 32 ? DP + 64 : btnLeftImage.getPreferredWidth() + 32 * dp, DP + 36);
			add(btnImage, CENTER, AFTER + 8 * dp, DP + 36, DP + 36);
			add(lSizes, LEFT, AFTER + 8 * dp, FILL, DP + 36);
			add(btnDefaultSize, CENTER, AFTER + 8 * dp, btnDefaultSize.getPreferredWidth() <= 32 ? DP + 64 : btnDefaultSize.getPreferredWidth() + 32 * dp, DP + 36);
			add(btnLarge, BEFORE - 8 * dp, CENTER_OF, btnLarge.getPreferredWidth() <= 48 ? DP + 96 : btnLarge.getPreferredWidth() + 48 * dp, DP + 54);
			add(btnSmall, AFTER + 8 * dp, CENTER_OF, btnSmall.getPreferredWidth() <= 24 ? DP + 48 : btnSmall.getPreferredWidth() + 24 * dp, DP + 27, btnDefaultSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void reposition() {
		center = Settings.screenWidth/2;
		btnRightImage.setRect(center + 4 * dp, AFTER + 8 * dp, btnRightImage.getPreferredWidth() <= 32 ? DP + 64 : btnRightImage.getPreferredWidth() + 32 * dp, DP + 36, lImages);
		btnLeftImage.setRect(BEFORE - 4 * dp, SAME, btnLeftImage.getPreferredWidth() <= 32 ? DP + 64 : btnLeftImage.getPreferredWidth() + 32 * dp, DP + 36, btnRightImage);
	}
}
