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
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.TabbedContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class TabbedContainerSample extends ScrollContainer {
  @Override
  public void initUI() {
    try {
      super.initUI();
      int gap = 50;
      
      Button btnEnable = new Button("Enable all tabs");
      btnEnable.setBackForeColors(Colors.P_600, Colors.ON_P_600);

      add(btnEnable, CENTER, TOP + gap, SCREENSIZE+95, PREFERRED + gap*2);

      String[] caps = { "Facebook", "Google+", "Instagram" };
      Image[] icons = { new Image("images/fb_icon_40.png"), new Image("images/gmail_icon_40.png"),
          new Image("images/insta_icon_40.png") };
    
      Container c1 = new Container();
      c1.setInsets(15, 15, 0, 0);
      add(c1, LEFT, AFTER + gap*3, FILL, (int)(Settings.screenHeight * 0.4), btnEnable);
      
      final TabbedContainer tc = new TabbedContainer(caps);
      tc.setBackColor(Color.DARK);
      tc.getContainer(0).setBackColor(0x4267b2);
      tc.getContainer(1).setBackColor(0xdd4b3e);
      tc.getContainer(2).setBackColor(0xb62e8e);
      tc.setIcons(icons);
      tc.pressedColor = Color.RED;
      tc.activeTabBackColor = 0xDDDDDD;
      tc.allSameWidth = true;
      tc.extraTabHeight = fmH * 2;
      c1.add(tc, LEFT, TOP, FILL, (int)(Settings.screenHeight * 0.3));
      Button btnDisable1 = new Button("Disable selected tab");
      btnDisable1.setBackForeColors(Colors.S_600, Colors.ON_S_600);
      c1.add(btnDisable1, CENTER, AFTER + gap, PREFERRED + gap, (int)(Settings.screenHeight * 0.08), tc);
      
      Container c2 = new Container();
      c2.setInsets(15, 15, 0, 0);
      add(c2, LEFT, AFTER + gap*4, FILL, (int)(Settings.screenHeight * 0.4), c1);
      
      final TabbedContainer tc2 = new TabbedContainer(caps);
      tc2.setType(TabbedContainer.TABS_BOTTOM);
      tc2.setBackColor(Color.DARK);
      tc2.getContainer(0).setBackColor(0x4267b2);
      tc2.getContainer(1).setBackColor(0xdd4b3e);
      tc2.getContainer(2).setBackColor(0xb62e8e);
      tc2.useOnTabTheContainerColor = true;
      tc2.allSameWidth = true;
      tc2.extraTabHeight = fmH / 2;
      c2.add(tc2, LEFT, TOP, FILL, (int)(Settings.screenHeight * 0.3));
      Button btnDisable2 = new Button("Disable selected tab");
      btnDisable2.setBackForeColors(Colors.S_600, Colors.ON_S_600);
      c2.add(btnDisable2, CENTER, AFTER, PREFERRED + gap, (int)(Settings.screenHeight * 0.08), tc2);
      
      
      Image[] images = new Image[3];
      Image empty = new Image("images/bullet_empty.png").getSmoothScaledInstance(fmH, fmH);
      Image filled = new Image("images/bullet_full.png").getSmoothScaledInstance(fmH, fmH);
      filled.applyColor2(Color.ORANGE);

      for (int i = images.length; --i >= 0;) {
        images[i] = empty;
      }
      
      Container c3 = new Container();
      c3.setInsets(15, 15, 0, 0);
      add(c3, LEFT, AFTER + gap*4, FILL, (int)(Settings.screenHeight * 0.4), c2);
      
      final TabbedContainer tc3 = new TabbedContainer(images);
      tc3.setActiveIcon(filled);
      tc3.setType(TabbedContainer.TABS_BOTTOM);
      tc3.setBackColor(Color.DARK);
      tc3.getContainer(0).setBackColor(0x4267b2);
      tc3.getContainer(1).setBackColor(0xdd4b3e);
      tc3.getContainer(2).setBackColor(0xb62e8e);
      tc3.allSameWidth = true;
      tc3.extraTabHeight = fmH / 2;
      tc3.setBorderStyle(Container.BORDER_NONE);
      tc3.transparentBackground = true;
      c3.add(tc3, LEFT, TOP, FILL, (int)(Settings.screenHeight * 0.3));
      Button btnDisable3 = new Button("Disable selected tab");
      btnDisable3.setBackForeColors(Colors.S_600, Colors.ON_S_600);
      c3.add(btnDisable3, CENTER, AFTER, PREFERRED + gap, (int)(Settings.screenHeight * 0.08), tc3);
      
      btnEnable.addPressListener(new PressListener() {
        @Override
        public void controlPressed(ControlEvent e) {
        	for(int i = 0; i < 3; i++)
        		tc.setEnabled(i, true);
        	for(int i = 0; i < 3; i++)
        		tc2.setEnabled(i, true);
        	for(int i = 0; i < 3; i++)
        		tc3.setEnabled(i, true);
        }
      });
      
      btnDisable1.addPressListener(new PressListener() {
          @Override
          public void controlPressed(ControlEvent e) {
          	tc.setEnabled(tc.getActiveTab(), false);
          }
        });
      btnDisable2.addPressListener(new PressListener() {
          @Override
          public void controlPressed(ControlEvent e) {
          	tc2.setEnabled(tc2.getActiveTab(), false);
          }
        });
      btnDisable3.addPressListener(new PressListener() {
          @Override
          public void controlPressed(ControlEvent e) {
          	tc3.setEnabled(tc3.getActiveTab(), false);
          }
        });
      
    } catch (Exception ee) {
      MessageBox.showException(ee, true);
    }
  }
}