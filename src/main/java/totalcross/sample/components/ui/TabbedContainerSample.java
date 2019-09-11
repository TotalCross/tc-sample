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

import totalcross.io.IOException;
import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.TabbedContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class TabbedContainerSample extends BaseScreen {
	private final int gap = (int)(Settings.screenDensity * 30);

	public TabbedContainerSample () {
		super("https://totalcross.gitbook.io/playbook/components/tabbed-container");
	}

	@Override
	public void onContent(ScrollContainer content) {
		try {
			content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);

			CreateImageAndTextTabbedContainer(content);
			CreateTextOnlyTabbedContainer(content);
			CreateBulletsTabbedContainer(content);
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}

	private void CreateImageAndTextTabbedContainer(ScrollContainer content) throws ImageException, IOException {
		String[] caps = { 
			"Social 1", 
			"Social 2", 
			"Social 3" 
		};
		Image[] icons = { 
			new Image("images/fb_icon_40.png"), 
			new Image("images/gmail_icon_40.png"),
			new Image("images/insta_icon_40.png") 
		};

		Label sampleTitle = new Label("This is a icon and text Tabbed Container", CENTER);
		sampleTitle.autoSplit = true;
		content.add(sampleTitle, LEFT + gap, TOP + gap, FILL - gap, PREFERRED);
		
		Container spacing = new Container();
		content.add(spacing, LEFT + gap*2, AFTER + gap/2, FILL - gap*2, (int) (Settings.screenHeight * 0.3));
		
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
		spacing.add(tc, LEFT, TOP, FILL, PARENTSIZE);
		for(int i = 0; i < 3; i++)
			tc.getContainer(i).add(new Label("Container " + (i+1)), CENTER, CENTER);
	}

	private void CreateTextOnlyTabbedContainer(ScrollContainer content) throws ImageException, IOException {
		String[] caps = new String[3];
		caps[0] = "Home";
		caps[1] = "Photos";
		caps[2] = "Profile";
		
		
		Label sampleTitle = new Label("This is a text only Tabbed Container", CENTER);
		sampleTitle.autoSplit = true;
		content.add(sampleTitle, LEFT + gap, AFTER + gap*2, FILL - gap, PREFERRED);
		
		Container spacing = new Container();
		add(spacing, LEFT + gap*2, AFTER + gap/2, FILL - gap*2, (int) (Settings.screenHeight * 0.3));
		
		final TabbedContainer tc = new TabbedContainer(caps);
		tc.setType(TabbedContainer.TABS_BOTTOM);
		tc.setBackColor(Color.DARK);
		tc.getContainer(0).setBackColor(Colors.P_300);
		tc.getContainer(1).setBackColor(Colors.P_400);
		tc.getContainer(2).setBackColor(Colors.P_500);
		tc.useOnTabTheContainerColor = true;
		tc.allSameWidth = true;
		tc.extraTabHeight = fmH / 2;
		spacing.add(tc, LEFT, TOP, FILL, PARENTSIZE);
		for(int i = 0; i < 3; i++)
			tc.getContainer(i).add(new Label("Container " + (i+1)), CENTER, CENTER);
	}

	private void CreateBulletsTabbedContainer(ScrollContainer content) throws ImageException, IOException {
		Image[] images = new Image[3];
		Image empty = new Image("images/bullet_empty.png").getSmoothScaledInstance(fmH, fmH);
		Image filled = new Image("images/bullet_full.png").getSmoothScaledInstance(fmH, fmH);
		filled.applyColor2(Color.ORANGE);

		for (int i = images.length; --i >= 0;) {
			images[i] = empty;
		}
		
		Label sampleTitle = new Label("This is a image-only Tabbed Container", CENTER);
		sampleTitle.autoSplit = true;
		content.add(sampleTitle, LEFT + gap, AFTER + gap*2, FILL - gap, PREFERRED);
		
		Container spacing = new Container();
		content.add(spacing, LEFT + gap*2, AFTER + gap/2, FILL - gap*2, (int) (Settings.screenHeight * 0.3));

		final TabbedContainer tc = new TabbedContainer(images);
		tc.setActiveIcon(filled);
		tc.setType(TabbedContainer.TABS_BOTTOM);
		tc.setBackColor(Color.DARK);
		tc.getContainer(0).setBackColor(Colors.P_300);
		tc.getContainer(1).setBackColor(Colors.P_400);
		tc.getContainer(2).setBackColor(Colors.P_500);
		tc.allSameWidth = true;
		tc.extraTabHeight = fmH / 2;
		tc.setBorderStyle(Container.BORDER_NONE);
		tc.transparentBackground = true;
		spacing.add(tc, LEFT, TOP, FILL, PARENTSIZE);
		for(int i = 0; i < 3; i++)
			tc.getContainer(i).add(new Label("Container " + (i+1)), CENTER, CENTER);
	}
}