/*********************************************************************************
 *  TotalCross Software Development Kit                                          *
 *  Copyright (C) 2000-2014 SuperWaba Ltda.                                      *
 *  All Rights Reserved                                                          *
 *                                                                               *
 *  This library and virtual machine is distributed in the hope that it will     *
 *  be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                         *
 *                                                                               *
 *  This file is covered by the GNU LESSER GENERAL PUBLIC LICENSE VERSION 3.0    *
 *  A copy of this license is located in file license.txt at the root of this    *
 *  SDK or can be downloaded here:                                               *
 *  http://www.gnu.org/licenses/lgpl-3.0.txt                                     *
 *                                                                               *
 *********************************************************************************/

package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.TabbedContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class TabbedContainerSample extends ScrollContainer {

	private int gap = (int) (Settings.screenDensity * 30);

	@Override
	public void initUI() {
		try {
			setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);

			String[] caps = { "Social 1", "Social 2", "Social 3" };
			Image[] icons = { new Image("images/fb_icon_40.png"), new Image("images/gmail_icon_40.png"),
					new Image("images/insta_icon_40.png") };

			Label explain1 = new Label("This is a icon and text Tabbed Container", CENTER);
			explain1.autoSplit = true;
			add(explain1, LEFT + gap, TOP + gap, FILL - gap, PREFERRED);
			
			Container c1 = new Container();
			
			add(c1, LEFT + gap*2, AFTER + gap/2, FILL - gap*2, (int) (Settings.screenHeight * 0.3));
			
			final TabbedContainer tc = new TabbedContainer(caps);
			tc.setBackColor(Color.DARK);
			tc.getContainer(0).setBackColor(Colors.P_300);
			tc.getContainer(1).setBackColor(Colors.P_400);
			tc.getContainer(2).setBackColor(Colors.P_500);
			tc.setIcons(icons);
			tc.pressedColor = Colors.P_800;
			tc.activeTabBackColor = Colors.P_800;
			tc.allSameWidth = true;
			tc.extraTabHeight = fmH * 2;
			c1.add(tc, LEFT, TOP, FILL, PARENTSIZE);
			for(int i = 0; i < 3; i++)
				tc.getContainer(i).add(new Label("Container " + (i+1)), CENTER, CENTER);

			Label explain2 = new Label("This is a text only Tabbed Container", CENTER);
			explain2.autoSplit = true;
			add(explain2, LEFT + gap, AFTER + gap*2, FILL - gap, PREFERRED);
			
			Container c2 = new Container();

			add(c2, LEFT + gap*2, AFTER + gap/2, FILL - gap*2, (int) (Settings.screenHeight * 0.3));
			
			caps[0] = "Home";
			caps[1] = "Photos";
			caps[2] = "Profile";
			
			final TabbedContainer tc2 = new TabbedContainer(caps);
			tc2.setType(TabbedContainer.TABS_BOTTOM);
			tc2.setBackColor(Color.DARK);
			tc2.getContainer(0).setBackColor(Colors.P_300);
			tc2.getContainer(1).setBackColor(Colors.P_400);
			tc2.getContainer(2).setBackColor(Colors.P_500);
			tc2.useOnTabTheContainerColor = true;
			tc2.allSameWidth = true;
			tc2.extraTabHeight = fmH / 2;
			c2.add(tc2, LEFT, TOP, FILL, PARENTSIZE);
			for(int i = 0; i < 3; i++)
				tc2.getContainer(i).add(new Label("Container " + (i+1)), CENTER, CENTER);
			
			Image[] images = new Image[3];
			Image empty = new Image("images/bullet_empty.png").getSmoothScaledInstance(fmH, fmH);
			Image filled = new Image("images/bullet_full.png").getSmoothScaledInstance(fmH, fmH);
			filled.applyColor2(Color.ORANGE);

			for (int i = images.length; --i >= 0;) {
				images[i] = empty;
			}
			
			Label explain3 = new Label("This is a only image Tabbed Container", CENTER);
			explain3.autoSplit = true;
			add(explain3, LEFT + gap, AFTER + gap*2, FILL - gap, PREFERRED);
			
			Container c3 = new Container();
			add(c3, LEFT + gap*2, AFTER + gap/2, FILL - gap*2, (int) (Settings.screenHeight * 0.3));

			final TabbedContainer tc3 = new TabbedContainer(images);
			tc3.setActiveIcon(filled);
			tc3.setType(TabbedContainer.TABS_BOTTOM);
			tc3.setBackColor(Color.DARK);
			tc3.getContainer(0).setBackColor(Colors.P_300);
			tc3.getContainer(1).setBackColor(Colors.P_400);
			tc3.getContainer(2).setBackColor(Colors.P_500);
			tc3.allSameWidth = true;
			tc3.extraTabHeight = fmH / 2;
			tc3.setBorderStyle(Container.BORDER_NONE);
			tc3.transparentBackground = true;
			c3.add(tc3, LEFT, TOP, FILL, PARENTSIZE);
			for(int i = 0; i < 3; i++)
				tc3.getContainer(i).add(new Label("Container " + (i+1)), CENTER, CENTER);

		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}
}