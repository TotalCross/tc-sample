package totalcross.sample.components.ui;

import totalcross.res.Resources;
import totalcross.sample.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Toast;
import totalcross.ui.TopMenu;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class TopMenuSample extends ScrollContainer {
	final int gap = 50;
	private ComboBox cbSide;
	private Container menu;
	private TopMenu topMenu;

	private class FilterContainer extends Container implements PressListener {
		@Override
		public void initUI() {
			super.initUI();
			Label l = new Label("FILTERS", CENTER, Color.WHITE, true);
			l.transparentBackground = true;
			add(l, LEFT, TOP, FILL, PREFERRED);
			add(new ComboBox(new String[] { "Name", "Name 1", "Name 2", "Name 3" }), LEFT + gap, AFTER + gap, FILL - gap,
					PREFERRED);
			add(new Edit(), LEFT, CENTER, PARENTSIZE - gap*2, PREFERRED);
			Button b;
			this.add(b = new Button("Search"), CENTER, AFTER, PREFERRED + fmH * 4, PREFERRED + fmH * 6);
			b.setBackColor(Color.CYAN);
			add(b = new Button("Close"), AFTER + gap, AFTER + gap,  PREFERRED + fmH * 4, PREFERRED + fmH * 6);
			b.setBackColor(Color.RED);
			b.addPressListener(this);
		}

		@Override
		public void controlPressed(ControlEvent e) {
			TopMenu top = (TopMenu) getParentWindow();
			top.unpop(new TopMenu.AnimationListener() {
				@Override
				public void onAnimationFinished() {
					new MessageBox("Message", "The search was done.").popup();
				}
			});
		}
	}

	@Override
	public void initUI() {
		super.initUI();
		add(menu = new Container(), LEFT + gap, TOP + gap, SCREENSIZE + 78, WILL_RESIZE);
		menu.setBackColor(Colors.GRAY);
		menu.add(new Label("Direction: "), LEFT + gap, TOP + gap, PREFERRED, PREFERRED);

		cbSide = new ComboBox(new String[] { "LEFT", "RIGHT", "TOP", "BOTTOM" });
		cbSide.setBackColor(Color.getRGB("fcfcfc"));
		menu.add(cbSide, AFTER + gap, SAME, PREFERRED + gap, PREFERRED);

		Check filter = new Check("FilterContainer");
		menu.add(filter, LEFT + gap, AFTER + gap, PREFERRED + gap, PREFERRED + gap);
		
		menu.resizeHeight();
		
		Button confirm = new Button("GO");
		confirm.setBackForeColors(Colors.P_DARK, Color.WHITE);
		add(confirm, AFTER + gap, SAME, FILL - gap, SAME, menu);
		

		Control[] items = { new TopMenu.Item("Videocalls", Resources.warning),
				new TopMenu.Item("Insert emoticon", Resources.exit),
				new ComboBox(new String[] { "Smile", "Sad", "Laugh" }), new TopMenu.Item("Add text", Resources.back),
				new TopMenu.Item("See contact", Resources.menu), new TopMenu.Item("Add slide", Resources.warning),
				new TopMenu.Item("Add subject", Resources.exit), new TopMenu.Item("Add persons", Resources.back),
				new TopMenu.Item("Programmed messages", Resources.menu),
				new TopMenu.Item("Add to the phone book", Resources.warning), };
		
		confirm.addPressListener((e) -> {
			try {
				switch (cbSide.getSelectedIndex()) {
				case -1:
					Toast.show("You must select a direction first", 500);
					break;
				case 0:
					if (filter.isChecked())
						topMenu = new TopMenu(new Control[] { new FilterContainer() }, LEFT);
					else
						topMenu = new TopMenu(items, LEFT);
					break;
				case 1:
					if (filter.isChecked())
						topMenu = new TopMenu(new Control[] { new FilterContainer() }, RIGHT);
					else
						topMenu = new TopMenu(items, RIGHT);
					break;
				case 2:
					if (filter.isChecked()) 
						Toast.show("You shouldn't use FilterContainer with TOP", 500);
					else
						topMenu = new TopMenu(items, TOP);
					break;
				case 3:
					if (filter.isChecked())
						Toast.show("You shouldn't use FilterContainer with BOTTOM", 500);
					else
						topMenu = new TopMenu(items, BOTTOM);
					break;
				}
				if(topMenu != null) {
					if (filter.isChecked()) {
						topMenu.totalTime = 500;
						topMenu.autoClose = false;
						topMenu.backImage = new Image("images/back1.jpg");
						topMenu.backImageAlpha = 96;
					}
						topMenu.popup();
				}
			} catch (Exception ee) {
				MessageBox.showException(ee, true);
			}
		});
	}
}
