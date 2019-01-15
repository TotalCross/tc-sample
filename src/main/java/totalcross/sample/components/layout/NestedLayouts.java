package totalcross.sample.components.layout;

import totalcross.ui.Button;
import totalcross.ui.layout.HBox;
import totalcross.ui.ScrollContainer;
import totalcross.ui.layout.VBox;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class NestedLayouts extends ScrollContainer {
	@Override
	public void initUI() {
		super.initUI();
		
		ScrollContainer scroll = new ScrollContainer();
		this.add(scroll, LEFT, TOP + 40, PARENTSIZE, PARENTSIZE);
		
		VBox main = new VBox();
		main.add(new PrettyButton("A", Color.BLUE), DP + 60, DP + 30);
		
		HBox twoBoxes = new HBox();
		twoBoxes.add(new PrettyButton("B", Color.GREEN));
		twoBoxes.add(new PrettyButton("B", Color.GREEN));
		main.add(twoBoxes);
		
		HBox oneBox = new HBox();
		oneBox.add(new PrettyButton("C", Color.brighter(Color.RED)), DP + 80, DP + 30);
		main.add(oneBox);
		
		HBox threeBoxes = new HBox();
		threeBoxes.add(new PrettyButton("D", Color.GREEN));
		threeBoxes.add(new PrettyButton("D", Color.GREEN));
		threeBoxes.add(new PrettyButton("D", Color.GREEN));
		main.add(threeBoxes);
		
		HBox twoVboxes = new HBox();
		VBox first = new VBox();
		first.add(new PrettyButton("E", Color.BLUE));
		first.add(new PrettyButton("E", Color.BLUE), DP + 40, DP + 40);
		first.add(new PrettyButton("E", Color.BLUE));
		twoVboxes.add(first);
		
		VBox second = new VBox();
		second.add(new PrettyButton("E", Color.BLUE));
		second.add(new PrettyButton("E", Color.BLUE), DP + 40, DP + 40);
		second.add(new PrettyButton("E", Color.BLUE));
		twoVboxes.add(second);
		
		main.add(twoVboxes);
		
		main.add(new PrettyButton("F", Color.GREEN));
		main.setLayout(VBox.LAYOUT_STACK_TOP, VBox.ALIGNMENT_CENTER);
		this.add(main, LEFT, TOP, PARENTSIZE, PARENTSIZE);
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
}
