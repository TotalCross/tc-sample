package totalcross.sample.components.sql;

import java.util.ArrayList;

import totalcross.sample.components.ui.FormSample;
import totalcross.sample.components.ui.GridSample;
import totalcross.sample.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.TabbedContainer;
import totalcross.ui.font.Font;

public class SQLiteFormGridTabbedContainer extends TabbedContainer{
	
	private static final String [] tabs = {"  Form  ", "  Grid  "};
	private final Container bottomBar;
	
	private ArrayList<ButtonContainerAction> btnContainerActionList = new ArrayList<>();
	
	private FormSample formSample = new FormSample();
	private GridSample gridSample = new GridSample();
	
	public static final int FORM = 0;
	public static final int GRID = 1;

	public SQLiteFormGridTabbedContainer() {
		super(tabs);
		started = false;
		setType(TABS_TOP);
		
		formSample.setNumUsers(gridSample.getNumUsers());
		formSample.updateStatus();
		
		addBottomContainer2List(createButton(FORM), formSample, () -> {});
		addBottomContainer2List(createButton(GRID), gridSample, () -> {});
		
		this.bottomBar = new Container() {
			
			@Override
			public void initUI() {
				super.initUI();
				
				setBorderStyle(BORDER_NONE);
				setBackColor(Colors.PRIMARY);
				
				boolean firstTime = true;
				for (ButtonContainerAction btnContainerAction: btnContainerActionList) {
					int xPos, yPos, width, height;
					if (firstTime) {
						firstTime = false;
						xPos = LEFT;
						yPos = CENTER;
						width = PARENTSIZE + 33;
						height = PREFERRED;
					} else {
						xPos = AFTER;
						yPos = SAME;
						width = SAME;
						height = SAME;
					}
					add(btnContainerAction.btn, xPos, yPos, width, height);
				}
			}
		};
	}
	
	public Button createButton(int type) {
		Button b;
		if (type == FORM) {
			b = new Button("Form");
		} else if (type == GRID) {
			b = new Button("Grid");
		} else {
			return null;
		}
		b.setFont(Font.getFont(true, fmH * 8 / 10));
		b.setForeColor(Colors.P_200);		
		b.setBorder(Button.BORDER_NONE);
		return b;
	}

	public Container getBottomBar() {
		return bottomBar;
	}
	
	private void addBottomContainer2List(Button btn, Container container, Runnable action) {
		int idx = btnContainerActionList.size();
		
		btn.addPressListener((e) -> {
			setActiveTab(idx);
		});
		btnContainerActionList.add(new ButtonContainerAction(btn, container, action));
	}

	@Override
	public void initUI() {
		super.initUI();
		
		int idx = 0;
		for (ButtonContainerAction btnContainer: btnContainerActionList) {
			if (btnContainer.container != null) {
				setContainer(idx, btnContainer.container);
			}
			
			idx++;
		}
		
		setActiveTab(0);
	}
	
	private static class ButtonContainerAction {
		final Button btn;
		final Container container;
		final Runnable action;
		ButtonContainerAction(Button btn, Container container, Runnable action) {
			this.btn = btn;
			this.container = container;
			this.action = action;
		}
	}

}
