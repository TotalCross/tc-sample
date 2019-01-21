package totalcross.sample.components.ui;

import totalcross.io.IOException;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.OutlinedEdit;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.util.UnitsConverter;

public class NinePatchSample extends Container{
	int ninepatchNumber = 0;

	Edit originalEdit;
	Edit ninePatchChangedEdit;
	Button originalButton;
	Button ninePatchChangedButton;
	OutlinedEdit originalOutlinedEdit;
	OutlinedEdit ninePatchChangedOutlinedEdit;
	
	private static final int gap = AFTER + UnitsConverter.toPixels(DP + 12);
	
	@Override
	public void initUI() {
		Image differentNinePatch = null;
		try {
			differentNinePatch = new Image("images/ninepatch/different-ninepatch.png");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
		
		originalEdit = new Edit();
		originalEdit.caption = "Original Nine Patch";
		originalEdit.setForeColor(Color.BLACK);
		originalEdit.captionColor = Color.BLACK;
		ninePatchChangedEdit = new Edit();
		ninePatchChangedEdit.caption = "Nine Patch Changed";
		ninePatchChangedEdit.setForeColor(Color.BLACK);
		ninePatchChangedEdit.captionColor = Color.BLACK;
		ninePatchChangedEdit.setNinePatch(differentNinePatch);
		
		originalButton = new Button("Original Nine Patch");
		ninePatchChangedButton = new Button("Nine Patch Changed");
		ninePatchChangedButton.setNinePatch(differentNinePatch);
		
		originalOutlinedEdit = new OutlinedEdit();
		originalOutlinedEdit.caption = "Original Nine Patch";
		originalOutlinedEdit.setForeColor(Color.BLACK);
		originalOutlinedEdit.captionColor = Color.BLACK;
		ninePatchChangedOutlinedEdit = new OutlinedEdit();
		ninePatchChangedOutlinedEdit.caption = "Nine Patch Changed";
		ninePatchChangedOutlinedEdit.setForeColor(Color.BLACK);
		ninePatchChangedOutlinedEdit.setNinePatch(differentNinePatch);
		ninePatchChangedOutlinedEdit.setBorderHeight(3); //This should be the size of border in pixels, shadow pixels should be also counted.
		
		add(originalEdit, CENTER, AFTER + UnitsConverter.toPixels(DP + 32));
		add(ninePatchChangedEdit, CENTER, gap);
		add(originalButton, CENTER, gap);
		add(ninePatchChangedButton, CENTER, gap);
		add(originalOutlinedEdit, CENTER, gap);
		add(ninePatchChangedOutlinedEdit, CENTER, gap);
	}
	
}
