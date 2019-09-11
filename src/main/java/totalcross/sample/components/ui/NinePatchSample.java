package totalcross.sample.components.ui;

import totalcross.res.Resources;
import totalcross.sample.components.BaseScreen;
import totalcross.ui.*;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;
import totalcross.util.pdf.Base;

public class NinePatchSample extends BaseScreen {
	private static final int width = 320 + DP;
	private static final int gap = AFTER + UnitsConverter.toPixels(DP + 12);
	private static final int extraGap = UnitsConverter.toPixels(DP + 60);

	public NinePatchSample () {
		super("https://totalcross.gitbook.io/playbook/apis/ninepath");
	}

	@Override
	public void onContent(ScrollContainer content) {
		Edit edit = new Edit();
		edit.caption = "Edit";
		edit.setForeColor(Color.BLACK);
		edit.captionColor = Color.BLACK;
		content.add(edit, CENTER, TOP + extraGap, width, PREFERRED);
		
		Edit buttonEdit = new Edit();
		buttonEdit.caption = "Edit with Button Ninepatch";
		buttonEdit.setForeColor(Color.BLACK);
		buttonEdit.captionColor = Color.BLACK;
		buttonEdit.setNinePatch(Resources.button);
		content.add(buttonEdit, CENTER, gap, width, PREFERRED);
		
		
		
		Button button = new Button("Button");
		button.setForeColor(Color.BLACK);
		content.add(button, CENTER, gap + extraGap, width, PREFERRED);
		
		Button outlinedButton = new Button("Button with Outlined Ninepatch");
		outlinedButton.setNinePatch(Resources.outlinededit);
		outlinedButton.setForeColor(Color.BLACK);
		content.add(outlinedButton, CENTER, gap, width, PREFERRED);
		
		
		
		OutlinedEdit outlinedEdit = new OutlinedEdit();
		outlinedEdit.caption = "Outlined Edit";
		outlinedEdit.setForeColor(Color.BLACK);
		outlinedEdit.captionColor = Color.BLACK;
		content.add(outlinedEdit, CENTER, gap + extraGap, width, PREFERRED);
		
		OutlinedEdit buttonOutlinedEdit = new OutlinedEdit();
		buttonOutlinedEdit.caption = "Outlined Edit with Button Ninepatch";
		buttonOutlinedEdit.setNinePatch(Resources.button);
		buttonOutlinedEdit.setForeColor(Color.BLACK);
		buttonOutlinedEdit.captionColor = Color.BLACK;
		buttonOutlinedEdit.setBorderHeight(3); //This should be the size of border in pixels, shadow pixels should be also counted.
		content.add(buttonOutlinedEdit, CENTER, gap, width, PREFERRED);
	}
	
}
