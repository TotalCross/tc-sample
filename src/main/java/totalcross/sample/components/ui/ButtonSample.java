package totalcross.sample.components.ui;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

public class ButtonSample extends BaseScreen {

	
	private Button btnDefaultColor;
	private Button btnRed;
	private Button btnGreen;
	private Button btnBlue;
	private Button btnFull;
	private Button btnRounded;
	private Button btnBorderless;
	private Button btnLeftImage;
	private Button btnRightImage;
	private Button btnImage;
	private Button btnLarge;
	private Button btnOutlined;
	private Button btnDefaultSize;
	private Button btnSmall;
	private int center;
	private Label lImages = new Label("Images", CENTER);
	
	public ButtonSample() {
		super("https://totalcross.gitbook.io/playbook/components/button");
		center = Settings.screenWidth/2;
	}


	@Override
	public void onContent(ScrollContainer content) {
		try {

			content.setInsets(0, 0, 0, UnitsConverter.toPixels(DP + 8));
			content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
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
			btnRounded = new Button("Rounded Corners Button", Button.BORDER_ROUND);
			btnRounded.roundBorderFactor = 7;
			btnRounded.setBackForeColors(Colors.P_700, Color.WHITE);
			btnBorderless = new Button("Borderless Button", Button.BORDER_NONE);
			btnBorderless.setForeColor(Colors.P_700);
			btnOutlined = new Button("Outlined Button", Button.BORDER_OUTLINED);

			btnOutlined.setBackForeColors(Colors.P_700, Color.WHITE);
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
			content.add(lColors, LEFT, AFTER + UnitsConverter.toPixels(DP + 8), FILL, DP + 36);
			content.add(btnDefaultColor, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			content.add(btnGreen, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			content.add(btnRed, BEFORE - UnitsConverter.toPixels(DP + 8), SAME);
			content.add(btnBlue, AFTER + UnitsConverter.toPixels(DP + 8), SAME, btnGreen);
			content.add(lShapes, LEFT, AFTER + UnitsConverter.toPixels(DP + 8), FILL, DP + 36);
			content.add(btnFull, CENTER, AFTER + UnitsConverter.toPixels(DP + 8), PARENTSIZE + 95, PREFERRED);
			content.add(btnRounded, CENTER, AFTER + UnitsConverter.toPixels(DP + 8), PARENTSIZE + 95, PREFERRED);
			content.add(btnOutlined, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			content.add(btnBorderless, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			content.add(lImages, LEFT, AFTER + UnitsConverter.toPixels(DP + 8), FILL, DP + 36);
			content.add(btnRightImage, center + UnitsConverter.toPixels(DP + 4), AFTER + UnitsConverter.toPixels(DP + 8));
			content.add(btnLeftImage, BEFORE - UnitsConverter.toPixels(DP + 4), SAME);
			content.add(btnImage, CENTER, AFTER + UnitsConverter.toPixels(DP + 8));
			content.add(lSizes, LEFT, AFTER + UnitsConverter.toPixels(DP + 8), FILL, DP + 36);
			content.add(btnLarge, LEFT + UnitsConverter.toPixels(DP + 2), AFTER + UnitsConverter.toPixels(DP + 8), btnLarge.getPreferredWidth() <= 48 ? DP + 96 : btnLarge.getPreferredWidth() + UnitsConverter.toPixels(DP + 48), DP + 54);
			content.add(btnDefaultSize, AFTER +UnitsConverter.toPixels(DP + 2) , CENTER_OF);
			content.add(btnSmall, AFTER + UnitsConverter.toPixels(DP + 2), CENTER_OF, btnSmall.getPreferredWidth() <= 24 ? DP + 48 : btnSmall.getPreferredWidth() + UnitsConverter.toPixels(DP + 24), DP + 27, btnDefaultSize);

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
