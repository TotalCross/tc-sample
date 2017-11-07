package totalcross.sample.components;

import totalcross.ui.Container;
import totalcross.ui.MultiButton;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.res.Resources;
import totalcross.sample.util.Colors;
import totalcross.ui.Button;

public class ButtonSample extends Container{

	private Button simpleButton;
	private Button multiLineButton;
	private Button imageButton;
	private MultiButton simpleMultiButton;
	private MultiButton multiButton;
	
	private int GAP = fmH*4;
	private int HEIGHT = fmH*8;
	
	@Override
	public void initUI()
	{
		super.initUI();

		try {
			
			simpleButton = new Button("Simple button");
			simpleButton.setBackColor(Colors.BLUE);
			simpleButton.setForeColor(Color.WHITE);
			
			multiLineButton = new Button("This is\na multi-line\nButton");
			multiLineButton.setBackColor(Colors.GREEN);
			multiLineButton.setForeColor(Color.WHITE);
			
			Image img = Resources.warning.getSmoothScaledInstance(fmH, fmH);			
		    img.applyColor2(Color.WHITE);
		    
		    imageButton = new Button("This is an image Button", img, RIGHT, GAP);
		    imageButton.setBackColor(Colors.RED);
		    imageButton.setForeColor(Color.WHITE);
		    
		    simpleMultiButton = new MultiButton(new String[]{"Option One","Option Two"});
		    simpleMultiButton.setBackColor(Colors.ORANGE);
		    simpleMultiButton.setForeColor(Color.WHITE);
		    

		    multiButton = new MultiButton(new String[]{"Left","Center","Right"});
		    multiButton.setBackColor(Colors.PURPLE);
		    multiButton.setForeColor(Color.WHITE);
		    multiButton.isSticky = multiButton.is3dText = true;
		    multiButton.setEnabled(1,false);
			
			add(simpleButton, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + HEIGHT);		
			add(multiLineButton, LEFT + GAP, AFTER + GAP, PREFERRED + GAP, PREFERRED+GAP);
			add(imageButton, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + HEIGHT);
			add(simpleMultiButton, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + HEIGHT);
			add(multiButton, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + HEIGHT);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
