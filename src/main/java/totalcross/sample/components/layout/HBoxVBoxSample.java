package totalcross.sample.components.layout;

import totalcross.ui.Button;
import totalcross.ui.Control;
import totalcross.ui.ScrollContainer;
import totalcross.ui.gfx.Color;
import totalcross.ui.layout.LinearBox;
import totalcross.ui.layout.HBox;
import totalcross.ui.layout.VBox;
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
		int width = hbox ? DP + 240 : DP + 68;
		int height = hbox ? DP + 68 : DP + 180;
		for (int l : layouts) {
			ypos = l == layouts[0] ? TOP : AFTER + 20;
			for (int a : alignments) {
				xpos = a == alignments[0] ? LEFT : AFTER;
				ypos = a == alignments[0] ? ypos : SAME;
				
				LinearBox v = hbox ? new HBox() : new VBox();
				v.setInsets(2, 2, 2, 2);
				v.setLayout(l, a);
				v.setBackColor(0xCCCCCC);				
				v.add(generateButtons());
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
			return UnitsConverter.toPixels(DP + 40);
		}
		@Override
		public int getPreferredHeight() {
			return UnitsConverter.toPixels(DP + 30);
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
		HBox.LAYOUT_STACK_LEFT, // same as VBox.LAYOUT_STACK_TOP
		LinearBox.LAYOUT_STACK_CENTER, 
		HBox.LAYOUT_STACK_RIGHT, // same as VBox.LAYOUT_STACK_BOTTOM
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
