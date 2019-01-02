package totalcross.sample.components.layout;

import totalcross.ui.Button;
import totalcross.ui.Control;
import totalcross.ui.HBox;
import totalcross.ui.LinearBox;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.VBox;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class HBoxVBoxSample extends ScrollContainer {
	boolean hbox = true;
	
	public HBoxVBoxSample(boolean hbox) {
		this.hbox = hbox;
	}
	
	@Override
	public void initUI() {
		super.initUI();
		
		ScrollContainer scroll = new ScrollContainer();
		this.add(scroll, LEFT, TOP + 40, PARENTSIZE, PARENTSIZE);
		
		int xpos;
		int ypos;
		int width = hbox ? DP + 180 : DP + 68;
		int height = hbox ? DP + 68 : DP + 180;
		for (int l : layouts) {
			ypos = l == layouts[0] ? TOP : AFTER + 10;
			for (int a : alignments) {
				xpos = a == alignments[0] ? LEFT : AFTER;
				ypos = a == alignments[0] ? ypos : SAME;
				
				LinearBox v = hbox ? new HBox() : new VBox();
				v.setLayout(l, a, false);
				v.setBackColor(0xCCCCCC);				
				v.addControls(generateButtons());
				scroll.add(v, xpos + 10, ypos, width, height);
			}
		}
	}
	
	private class PrettyButton extends Button {
		public PrettyButton(String name, int backColor) {
			super(name);
			setBackColor(backColor);
		}
		
		@Override
		public int getPreferredWidth() {
			return UnitsConverter.toPixels(40);
		}
		@Override
		public int getPreferredHeight() {
			return UnitsConverter.toPixels(30);
		}
	}
	public Control[] generateButtons() {
		return new Control[] {
			new PrettyButton("A", Color.BLUE), 
			new PrettyButton("B", Color.RED), 
			new PrettyButton("C", Color.MAGENTA), 
			new PrettyButton("D", Color.BLACK)
		};
	}
	
	private final int[] layouts = new int[] { 
		LinearBox.LAYOUT_STACK_LEFT, 
		LinearBox.LAYOUT_STACK_CENTER, 
		LinearBox.LAYOUT_STACK_RIGHT,
		LinearBox.LAYOUT_DISTRIBUTE,
		LinearBox.LAYOUT_FILL,
	};
		
	private final int[] alignments = new int[] { 
		LinearBox.ALIGNMENT_LEFT, 
		LinearBox.ALIGNMENT_CENTER,
		LinearBox.ALIGNMENT_RIGHT, 
		LinearBox.ALIGNMENT_STRETCH,
	};
}
