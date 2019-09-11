package totalcross.sample.components.ui;

import java.util.ArrayList;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sample.util.SQLiteManager;
import totalcross.sample.util.User;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Grid;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;
import totalcross.util.pdf.Base;

public class GridSample extends BaseScreen {
	private ArrayList<User> users;
	private Grid grid;
	private Button loadButton;
	private int GAP = UnitsConverter.toPixels(DP + 8);
	private final int H = 225;
	
	public GridSample() {
		super("https://totalcross.gitbook.io/playbook/components/gridcontainer");
		users = SQLiteManager.getInstance().getUsers();
	}

	@Override
	public void onContent(ScrollContainer content) {

		String[] gridCaptions = { "Name", "Phone", "Email" };
		int gridWidths[] = { -35, -35, -30 };
		int gridAligns[] = { LEFT, LEFT, LEFT };

		grid = new Grid(gridCaptions, gridWidths, gridAligns, false);
		grid.verticalLineStyle = Grid.VERT_LINE;
		grid.setForeColor(Colors.ON_SURFACE);
		grid.firstStripeColor = Colors.P_050;
		grid.secondStripeColor = Colors.P_100;
		grid.highlightColor = Colors.S_300;
		
		loadButton = new Button("Load");
		loadButton.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		loadButton.setForeColor(Color.WHITE);

		content.add(grid, LEFT + GAP, TOP + GAP, FILL - GAP, FILL - GAP * 9);
		content.add(loadButton, LEFT + GAP, BOTTOM - GAP, FILL - GAP, PREFERRED);

		loadButton.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {

				users = SQLiteManager.getInstance().getUsers();

				if (users.size() > 0) {

					String items[][] = new String[users.size()][3];

					for (int i = 0; i < users.size(); i++) {

						User user = users.get(i);

						items[i] = new String[] { user.getName(), user.getPhone(), user.getMail() };

					}

					grid.setItems(items);

				} else {

					MessageBox mb = new MessageBox("Message", "No registered users.", new String[] { "Close" });
					mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
					mb.popup();
				}
			}
		});
	}
	
	public int getNumUsers() {
		return users.size();
	}
}
