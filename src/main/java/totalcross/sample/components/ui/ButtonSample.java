package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.MultiButton;
import totalcross.ui.ScrollContainer;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class ButtonSample extends ScrollContainer{

	private Button simpleButton;
	private Button multiLineButton;
	private Button imageButton;
	private Button onlyImageButton;
	private MultiButton simpleMultiButton;
	private MultiButton multiButton;
	private int gap = 50;
	private final int H = 225;
	
	@Override
	public void initUI()
	{
		super.initUI();

		try {
			setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
			setScrollBars(false, true);
			Label adv = new Label("All the following buttons are only for demonstration purposes.", CENTER);
			adv.autoSplit = true;
		    add(adv, LEFT + gap, TOP + gap, FILL - gap, PREFERRED);
			
			simpleButton = new Button("Simple button");
			simpleButton.setBackForeColors(Colors.S_700, Colors.ON_S_700);
			//Colors.S_700, Colors.ON_S_700
			//Colors.P_800, Colors.ON_P_800
			multiLineButton = new Button("This is a multi-line button, you\ncan add as much lines as ou want,\nas long as it's within the screen\nrange.");
			multiLineButton.setBackForeColors(Colors.P_700, Colors.ON_P_700);
			
			Image img = new Image("images/logoV.png").hwScaledFixedAspectRatio(fmH*2, true);			
		    
		    imageButton = new Button("Text button with an image", img, RIGHT, 10);
		    imageButton.setBackForeColors(Colors.S_700, Colors.ON_S_700);
		    
		    simpleMultiButton = new MultiButton(new String[]{"Option One","Option Two"});
		    simpleMultiButton.setBackForeColors(Colors.P_700, Colors.ON_P_700);

		    multiButton = new MultiButton(new String[]{"Left","Center","Right"});
		    multiButton.setBackForeColors(Colors.S_700, Colors.ON_S_700);

		    multiButton.isSticky = multiButton.is3dText = true;
		    
		    Image image = new Image("images/find.png").smoothScaledBy((double)480/2048, (double)480/2048); // Searching and scaling the image.
		    onlyImageButton = new Button(image); // This creates a button with an image only, no text.
		    onlyImageButton.setBorder(Button.BORDER_NONE); //This sets the button's border to null
		    
			add(simpleButton, LEFT+gap, AFTER+gap, FILL-gap, 90 + DP);	
			add(multiLineButton, LEFT+gap, AFTER+gap, FILL-gap, 90 + DP);
			add(imageButton, LEFT+gap, AFTER+gap, FILL-gap, 90 + DP);
			add(simpleMultiButton, LEFT+gap , AFTER+gap , FILL-gap , 90 + DP);
			add(multiButton, LEFT+gap , AFTER+gap, FILL-gap, 90 + DP);
			Label label = new Label("The magnifier is an Image Button", CENTER);
			label.setFont(Font.getFont("Lato Medium", false, label.getFont().size ));
			add(label, LEFT + gap, AFTER+gap, FILL - gap, PREFERRED);
			//add(new Label("The magnifier\nis an Image\nButton", CENTER), LEFT + gap, AFTER + gap);
			add(onlyImageButton,CENTER_OF, AFTER + gap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
