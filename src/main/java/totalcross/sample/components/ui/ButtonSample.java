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
			setBackColor(Color.WHITE);
			setScrollBars(false, true);

		    add(new Label("All the following buttons are\nonly for demonstration purposes.", CENTER), CENTER, TOP + gap);
			
			simpleButton = new Button("Simple button");
			simpleButton.setBackColor(Colors.P_DARK);
			simpleButton.setForeColor(Color.WHITE);
			
			multiLineButton = new Button("This is a multi-line button, you\ncan add as much lines as ou want,\nas long as it's within the screen\nrange.");
			multiLineButton.setBackColor(Colors.GREEN);
			multiLineButton.setForeColor(Color.WHITE);
			
			Image img = new Image("images/logoV.png").hwScaledFixedAspectRatio(fmH*2, true);			
		    
		    imageButton = new Button("Text button with an image", img, RIGHT, 10);
		    imageButton.setBackColor(Colors.RED);
		    imageButton.setForeColor(Color.WHITE);
		    
		    simpleMultiButton = new MultiButton(new String[]{"Option One","Option Two"});
		    simpleMultiButton.setBackColor(Colors.ORANGE);
		    simpleMultiButton.setForeColor(Color.WHITE);
		    

		    multiButton = new MultiButton(new String[]{"Left","Center","Right"});
		    multiButton.setBackColor(Colors.PURPLE);
		    multiButton.setForeColor(Color.WHITE);
		    multiButton.isSticky = multiButton.is3dText = true;
		    
		    Image image = new Image("images/find.png").smoothScaledBy((double)480/2048, (double)480/2048); // Searching and scaling the image.
		    onlyImageButton = new Button(image); // This creates a button with an image only, no text.
		    onlyImageButton.setBorder(Button.BORDER_NONE); //This sets the button's border to null
		    
			
			add(simpleButton, LEFT+gap, AFTER+gap, FILL-gap, PREFERRED+H);		
			add(multiLineButton, LEFT+gap, AFTER+gap, FILL-gap, PREFERRED+H);
			add(imageButton, LEFT+gap, AFTER+gap, FILL-gap, PREFERRED+H);
			add(simpleMultiButton, LEFT+gap , AFTER+gap , FILL-gap , PREFERRED+H);
			add(multiButton, LEFT+gap , AFTER+gap, FILL-gap, PREFERRED+H);
			Label label = new Label("The magnifier\nis an Image\nButton", CENTER);
			label.setFont(Font.getFont("Lato Medium", false, label.getFont().size ));
			add(label, LEFT+gap, AFTER+gap);
			//add(new Label("The magnifier\nis an Image\nButton", CENTER), LEFT + gap, AFTER + gap);
			add(onlyImageButton,CENTER, SAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
