package totalcross.sample.components.ui;

import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MultiButton;
import totalcross.ui.ScrollContainer;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.sample.util.Colors;
import totalcross.ui.Button;

public class ButtonSample extends Container{

	private ScrollContainer sc;
	private Button simpleButton;
	private Button multiLineButton;
	private Button imageButton;
	private Button onlyImageButton;
	private MultiButton simpleMultiButton;
	private MultiButton multiButton;
	
	private final int H = 225;
	private final int B = 50;
	
	@Override
	public void initUI()
	{
		super.initUI();

		try {
			setBackColor(Color.WHITE);
			sc = new ScrollContainer(false, true);
			add(new Label("All the following buttons are\nonly for demonstration purposes.", CENTER), CENTER, TOP, PREFERRED, PREFERRED);
		    add(sc, LEFT, AFTER + 5, FILL, FILL);
			
			simpleButton = new Button("Simple button");
			simpleButton.setBackColor(Colors.P_DARK);
			simpleButton.setForeColor(Color.WHITE);
			
			multiLineButton = new Button("This is a multi-line button, you can add \nas much lines as ou want, as long as it's\nwithin the screen range.");
			multiLineButton.setBackColor(Colors.GREEN);
			multiLineButton.setForeColor(Color.WHITE);
			
			Image img = new Image("images/logoV.png").hwScaledFixedAspectRatio(fmH*2, true);			
		    
		    imageButton = new Button("  This is a text button with an image", img, RIGHT, 0);
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
		    
			
			sc.add(simpleButton, LEFT+B, AFTER+B, FILL-B, PREFERRED+H);		
			sc.add(multiLineButton, CENTER, AFTER+B, SAME, PREFERRED+H);
			sc.add(imageButton, LEFT+B, AFTER+B, FILL-B, PREFERRED+H);
			sc.add(simpleMultiButton, LEFT+B , AFTER+B , FILL-B , PREFERRED+H);
			sc.add(multiButton, LEFT+B , AFTER+B, FILL-B, PREFERRED+H);
			sc.add(new Label("The magnifier\nis an Image\nButton", CENTER), LEFT + 150, AFTER + B, PREFERRED, PREFERRED);
			sc.add(onlyImageButton, CENTER, SAME, PREFERRED, PREFERRED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
