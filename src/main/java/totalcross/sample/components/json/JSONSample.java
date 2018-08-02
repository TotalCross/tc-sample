package totalcross.sample.components.json;

import java.util.List;

import totalcross.json.JSONFactory;
import totalcross.sample.util.Colors;
import totalcross.sys.Convert;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;

public class JSONSample extends ScrollContainer {
	private int gap = 50;
	@Override
	public void initUI() {
		super.initUI();
		setScrollBars(false, true);
		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		try {
			Container inputBox = new Container();
			inputBox.setBackForeColors(Colors.P_200, Colors.ON_P_200);
			add(inputBox, LEFT + gap, TOP + gap, FILL - gap, WILL_RESIZE);
			
			Label title1 = new Label("Input line", CENTER);
			title1.setFont(font.asBold());
			title1.setBackForeColors(Colors.P_700, Colors.ON_P_700);
			inputBox.add(title1, LEFT, TOP, FILL, 50 + DP);
			
			String line = "[{\n\t\"name\":\"Mary\",\n\t\"date\":61395803160000,\n\t\"text\":\"My first post\",\n\t\"likes\":1\n},\n{\n\t\"name\":\"John\",\n\t\"date\":61395803820000,\n\t\"text\":\"I like TotalCross\",\n\t\"likes\":200\n}]";
			Label l = new Label(line);
			l.autoSplit = true;
			inputBox.add(l, LEFT + gap, AFTER, FILL - gap, PREFERRED);
			
			inputBox.setInsets(0, 0, 0, gap/3);
			inputBox.resizeHeight();
			
			Container arrayBox = new Container();
			arrayBox.setBackForeColors(Colors.S_300, Colors.ON_S_300);
			add(arrayBox, LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			
			Label title2 = new Label("As array (The return function\n is an array)", CENTER);
			title2.setFont(font.asBold());
			title2.setBackForeColors(Colors.S_700, Colors.ON_S_700);
			arrayBox.add(title2, LEFT, TOP, FILL, 50 + DP);
			
			ScrollContainer results = new ScrollContainer(true, false);
			results.setBackForeColors(Colors.S_300, Colors.ON_S_300);
			arrayBox.add(results, LEFT, AFTER + gap, FILL, 120 + DP);
			FacebookPost[] posts = (FacebookPost[]) JSONFactory.parse(line, FacebookPost[].class);
			for (FacebookPost f : posts) {
				Container c1 = createLabelContainer(f.getName());
				Container c2 = createLabelContainer(Convert.toString(f.getDate()));
				Container c3 = createLabelContainer(f.getText());
				Container c4 = createLabelContainer(Convert.toString(f.getLikes()));

				results.add(c1, LEFT + gap, AFTER + gap, PREFERRED, PREFERRED + fmH*8);	
				c1.resizeWidth();
				results.add(c2, AFTER + gap, SAME, PREFERRED, PREFERRED + fmH*8);	
				c2.resizeWidth();
				results.add(c3, AFTER + gap, SAME, PREFERRED, PREFERRED + fmH*8);	
				c3.resizeWidth();
				results.add(c4, AFTER + gap, SAME, PREFERRED, PREFERRED + fmH*8);	
				c4.resizeWidth();
			}
			arrayBox.setInsets(0, 0, 0, gap/3);
			arrayBox.resizeHeight();
			
			Container listBox = new Container();
			listBox.setBackForeColors(Colors.S_300, Colors.ON_S_300);
			add(listBox, LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			
			Label title3 = new Label("As list (The return function is a list)", CENTER);
			title3.setFont(font.asBold());
			title3.setBackForeColors(Colors.S_700, Colors.ON_S_700);
			listBox.add(title3, LEFT, TOP, FILL, 50 + DP);
			
			ScrollContainer results2 = new ScrollContainer(true, false);
			results2.setBackForeColors(Colors.S_300, Colors.ON_S_300);
			listBox.add(results2, LEFT, AFTER + gap, FILL, 120 + DP);

			List<FacebookPost> posts2 = JSONFactory.asList(line, FacebookPost.class);
			for (FacebookPost f : posts2) {
				Container c1 = createLabelContainer(f.getName());
				Container c2 = createLabelContainer(Convert.toString(f.getDate()));
				Container c3 = createLabelContainer(f.getText());
				Container c4 = createLabelContainer(Convert.toString(f.getLikes()));

				results2.add(c1, LEFT + gap, AFTER + gap, PREFERRED, PREFERRED + fmH*8);	
				c1.resizeWidth();
				results2.add(c2, AFTER + gap, SAME, PREFERRED, PREFERRED + fmH*8);	
				c2.resizeWidth();
				results2.add(c3, AFTER + gap, SAME, PREFERRED, PREFERRED + fmH*8);	
				c3.resizeWidth();
				results2.add(c4, AFTER + gap, SAME, PREFERRED, PREFERRED + fmH*8);	
				c4.resizeWidth();
			}
			listBox.setInsets(0, 0, 0, gap/3);
			listBox.resizeHeight();
			
			add(new Spacer(), LEFT, AFTER, 1, gap/3);
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}
	
	private Container createLabelContainer(String data) {
		Container c = new Container();
		c.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);
		c.setBorderStyle(BORDER_SIMPLE);			
		c.add(new Label(data), LEFT + gap, TOP + gap, PREFERRED + gap, PREFERRED);
		return c;
	}
}