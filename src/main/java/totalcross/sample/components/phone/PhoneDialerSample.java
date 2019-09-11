/*********************************************************************************
 *  TotalCross Software Development Kit                                          *
 *  Copyright (C) 2000-2012 SuperWaba Ltda.                                      *
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

package totalcross.sample.components.phone;

import totalcross.io.IOException;
import totalcross.phone.Dial;
import totalcross.sample.components.BaseScreen;
import totalcross.sample.components.layout.CalculatorHBoxVBoxSample;
import totalcross.sample.util.Colors;
import totalcross.sample.util.MaterialConstants;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.dialog.keyboard.Builder;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PressListener;
import totalcross.ui.font.Font;
import totalcross.ui.layout.HBox;
import totalcross.ui.layout.VBox;
import totalcross.util.UnitsConverter;

public class PhoneDialerSample extends BaseScreen {
  VBox vboxAll;
  private  Edit ed;
  private Button dial;

  @Override
  public void onContent(ScrollContainer content) {
    int margin = MaterialConstants.EIGHT_DP_SPACING;
    content.setInsets(margin, margin, margin, margin);

    content.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
    Font big = Font.getFont(true, Font.NORMAL_SIZE + 2);
    ed = new Edit();
    ed.setFont(Font.getFont(true, Font.NORMAL_SIZE * 2));
    ed.setEnabled(false);
    ed.alignment = CENTER;
    content.add(ed, LEFT, TOP, FILL, SCREENSIZE+8);
    
    content.add(new Spacer(),LEFT, AFTER, 1, SCREENSIZE+5);

//    pbg = new PushButtonGroup(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "P", "0", " << " }, 5, 4);
//    pbg.setFont(big);
//    add(pbg, CENTER, AFTER, SCREENSIZE + 80, SCREENSIZE+65);
//    pbg.setFocusLess(true);
    content.add(new Spacer(),LEFT, AFTER, 1, SCREENSIZE+2);
    dial = new Button("Dial");
    dial.setFont(big);
    dial.setBackForeColors(Colors.P_600, Colors.ON_P_600);

    vboxAll = new VBox(VBox.LAYOUT_FILL, VBox.ALIGNMENT_STRETCH);
    vboxAll.setSpacing(2);

    HBox[] rows = new HBox[4];
    for (int i = 0; i < rows.length; i++) {
      rows[i] = new HBox(HBox.LAYOUT_FILL, HBox.ALIGNMENT_STRETCH);
      rows[i].setSpacing(MaterialConstants.TWO_DP_SPACING);
      vboxAll.add(rows[i]);
    }

    for (int i = 0; i < rows.length-1; i++) { //Add numbers
      for (int j = 0; j < 3; j++) { // 1 2 3
        int number = i*3 + j + 1;
        rows[i].add(
                new ButtonBuilder("" + number)
                .addPressListener((c) -> ed.setText(ed.getText() + number))
                .build()
        );
      }
    }

    //Last rows
    rows[3].add(
            new ButtonBuilder("P")
                    .addPressListener((c) -> ed.setText(ed.getText() + "P"))
                    .build()
    );
    rows[3].add(
            new ButtonBuilder("0")
            .addPressListener((c) -> ed.setText(ed.getText() + "0"))
            .build()
    );
    rows[3].add(
            new ButtonBuilder("<<")
            .addPressListener((c) -> {
              if (ed.getLength() > 0) {
                ed.setText(ed.getText().substring(0, ed.getText().length() - 1));
              }
            })
            .build()
    );

    int dialButtonHeight = UnitsConverter.toPixels(DP + 64);
    content.add(vboxAll, CENTER, AFTER, PARENTSIZE,
            FILL - dialButtonHeight - MaterialConstants.EIGHT_DP_SPACING);

    dial.addPressListener((c) -> { // Call
      try {
        Dial.number(ed.getText());
      } catch (IOException ex) {
        MessageBox.showException(ex, true);
      }
    });
    content.add(dial, CENTER, AFTER + margin, PARENTSIZE, FILL);


  }

  @Override
  public void onEvent(Event e) {
    switch (e.type) {
    case ControlEvent.PRESSED:
      if (e.target == vboxAll && dial.isEnabled()) {
        dial.setEnabled(ed.getLength() > 0);
      }
    }
  }

  private class ButtonBuilder {
    Button instance;

    ButtonBuilder(String text) {
      instance = new Button(text);
    }

    ButtonBuilder addPressListener(PressListener p) {
      instance.addPressListener(p);
      return this;
    }

    public Button build() {return instance;}
  }
}
