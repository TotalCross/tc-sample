package totalcross.sample.components.ui;

import totalcross.res.Resources;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Radio;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Toast;
import totalcross.ui.TopMenu;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class TopMenuSample extends Container {
	final int gap = fmH;
	ScrollContainer sc;
	TopMenu topMenu;

	private class FilterContainer extends Container implements PressListener {
		@Override
		public void initUI() {
			super.initUI();
			Label l = new Label("FILTERS", CENTER, Color.WHITE, true);
			l.transparentBackground = true;
			add(l, LEFT, TOP, FILL, PREFERRED);
			add(new ComboBox(new String[] { "Name", "Name 1", "Name 2", "Name 3" }), LEFT, AFTER + fmH / 4, FILL,
					PREFERRED);
			add(new Edit(), LEFT, AFTER + fmH / 4);
			Button b;
			add(b = new Button("Search"), LEFT, AFTER + fmH / 4, FILL, PREFERRED);
			b.setBackColor(Color.CYAN);
			add(b = new Button("Close"), LEFT, AFTER + fmH, FILL, PREFERRED);
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
		add(new Label("Direction: "), LEFT + gap, TOP + gap, PREFERRED, PREFERRED);

		ComboBox cbSide = new ComboBox(new String[] { "LEFT", "TOP", "RIGHT", "BOTTOM" });
		cbSide.setBackColor(Color.getRGB("fcfcfc"));
		add(cbSide, AFTER + gap, SAME, PREFERRED + gap, PREFERRED);

		Radio filter = new Radio("FilterContainer");
		add(filter, LEFT + gap, AFTER + gap, PREFERRED, PREFERRED);

		Button confirm = new Button("CONFIRM");
		confirm.setBackColor(Color.getRGB("5a81ed"));
		add(confirm, CENTER, AFTER + gap*4, PREFERRED + gap * 4, PREFERRED + gap * 4);

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
						topMenu = new TopMenu(new Control[] { new FilterContainer() }, TOP);
					else
						topMenu = new TopMenu(items, TOP);
					break;
				case 2:
					if (filter.isChecked())
						topMenu = new TopMenu(new Control[] { new FilterContainer() }, RIGHT);
					else
						topMenu = new TopMenu(items, RIGHT);
					break;
				case 3:
					if (filter.isChecked())
						topMenu = new TopMenu(new Control[] { new FilterContainer() }, BOTTOM);
					else
						topMenu = new TopMenu(items, BOTTOM);
					break;
				}
				if (filter.isChecked()) {
					topMenu.totalTime = 500;
					topMenu.autoClose = false;
					topMenu.backImage = new Image("images/back1.jpg");
					topMenu.backImageAlpha = 96;
				}
				if(topMenu != null)
					topMenu.popup();
			} catch (Exception ee) {
				MessageBox.showException(ee, true);
			}
		});
	}

	private void show(final TopMenu t, String dir) {
		t.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
			}
		});
		t.popup();
	}
}
