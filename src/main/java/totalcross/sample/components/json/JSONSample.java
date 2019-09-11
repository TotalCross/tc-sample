package totalcross.sample.components.json;

import java.util.List;

import totalcross.json.JSONFactory;
import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Convert;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class JSONSample extends BaseScreen {

	private int gap = UnitsConverter.toPixels(DP + 8);

	public JSONSample () {
		super("https://totalcross.gitbook.io/playbook/apis/json");
	}

	@Override
	public void onContent(ScrollContainer content) {
		content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		try {
			Container inputBox = new Container();
			inputBox.setBackForeColors(Colors.P_200, Colors.ON_P_200);
			content.add(inputBox, LEFT + gap, TOP + gap, FILL - gap, WILL_RESIZE);
			
			Label title1 = new Label("Input line", CENTER);
			title1.setFont(font.asBold());
			title1.setBackForeColors(Colors.P_700, Colors.ON_P_700);
			inputBox.add(title1, LEFT, TOP, FILL, 50 + DP);
			
			String line = "[{\n\t\"name\":\"Mary\",\n\t\"date\":61395803160000,\n\t\"text\":\"My first post\",\n\t\"likes\":1\n},\n{\n\t\"name\":\"John\",\n\t\"date\":61395803820000,\n\t\"text\":\"I like TotalCross\",\n\t\"likes\":200\n}]";
			Label l = new Label(line);
			l.autoSplit = true;
			inputBox.add(l, LEFT + gap, AFTER, FILL - gap, SCREENSIZE + 35);
			
			inputBox.setInsets(0, 0, 0, gap/3);
			inputBox.resizeHeight();
			
			Container arrayBox = new Container();
			arrayBox.setBackForeColors(Colors.S_300, Colors.ON_S_300);
			content.add(arrayBox, LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			
			Label title2 = new Label("As array (The return function is an array)", CENTER);
			title2.autoSplit = true;
			title2.setFont(font.asBold());
			title2.setBackForeColors(Colors.S_700, Colors.ON_S_700);
			arrayBox.add(title2, LEFT, TOP, FILL, 50 + DP);
			
			ScrollContainer results = new ScrollContainer(true, false);
			results.setBackForeColors(Colors.S_300, Colors.ON_S_300);
			arrayBox.add(results, LEFT, AFTER + gap, FILL, 120 + DP);
			FacebookPost[] posts = (FacebookPost[]) JSONFactory.parse(line, FacebookPost[].class);
			for (FacebookPost f : posts) {
				Label c1 = new Label(f.getName());
				Label c2 = new Label(Convert.toString(f.getDate()));
				Label c3 = new Label(f.getText());
				Label c4 = new Label(Convert.toString(f.getLikes()));
				c1.setBackColor(Colors.SURFACE);
				c2.setBackColor(Colors.SURFACE);
				c3.setBackColor(Colors.SURFACE);
				c4.setBackColor(Colors.SURFACE);
				Label size = new Label("Dado");
				size.setRect(LEFT, TOP, PREFERRED, PREFERRED);
				results.add(c1, LEFT + gap, AFTER + gap);	
				results.add(c2, AFTER + gap, SAME);	
				results.add(c3, AFTER + gap, SAME);
				results.add(c4, AFTER + gap, SAME);	
			}
			arrayBox.setInsets(0, 0, 0, gap/3);
			arrayBox.resizeHeight();
			
			Container listBox = new Container();
			listBox.setBackForeColors(Colors.S_300, Colors.ON_S_300);
			content.add(listBox, LEFT + gap, AFTER + gap, FILL - gap, WILL_RESIZE);
			
			Label title3 = new Label("As list (The return function is a list)", CENTER);
			title3.setFont(font.asBold());
			title3.setBackForeColors(Colors.S_700, Colors.ON_S_700);
			listBox.add(title3, LEFT, TOP, FILL, 50 + DP);
			
			ScrollContainer results2 = new ScrollContainer(true, false);
			results2.setBackForeColors(Colors.S_300, Colors.ON_S_300);
			listBox.add(results2, LEFT, AFTER + gap, FILL, 120 + DP);

			List<FacebookPost> posts2 = JSONFactory.asList(line, FacebookPost.class);
			for (FacebookPost f : posts2) {
				Label c1 = new Label(f.getName());
				Label c2 = new Label(Convert.toString(f.getDate()));
				Label c3 = new Label(f.getText());
				Label c4 = new Label(Convert.toString(f.getLikes()));
				c1.setBackColor(Colors.SURFACE);
				c2.setBackColor(Colors.SURFACE);
				c3.setBackColor(Colors.SURFACE);
				c4.setBackColor(Colors.SURFACE);
				Label size = new Label("Dado");
				size.setRect(LEFT, TOP, PREFERRED, PREFERRED);
				results2.add(c1, LEFT + gap, AFTER + gap);	
				results2.add(c2, AFTER + gap, SAME);	
				results2.add(c3, AFTER + gap, SAME);	
				results2.add(c4, AFTER + gap, SAME);	
			}
			listBox.setInsets(0, 0, 0, gap/3);
			listBox.resizeHeight();
			
			content.add(new Spacer(), LEFT, AFTER, 1, gap/3);
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}
	
}