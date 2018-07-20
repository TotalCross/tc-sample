/*********************************************************************************
 *  TotalCross Software Development Kit                                          *
 *  Copyright (C) 2000-2012 SuperWaba Ltda.                                      *
 *  All Rights Reserved                                                          *
 *                                                                               *
 *  This library and virtual machine is distributed in the hope that it will     *
 *  be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                         *
 *                                                                               *
 *********************************************************************************/

package totalcross.sample.components.crypto;

import totalcross.crypto.NoSuchAlgorithmException;
import totalcross.crypto.digest.Digest;
import totalcross.crypto.digest.MD5Digest;
import totalcross.crypto.digest.SHA1Digest;
import totalcross.crypto.digest.SHA256Digest;
import totalcross.sample.util.Colors;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

public class DigestSample extends ScrollContainer {
	private int gap = 50;
	private Container menu;
	private Edit edtInput;
	private ComboBox cboDigests;
	private Button btnGo;
	private Object[] comboItems;

	public DigestSample() {
		try {
			comboItems = new Object[] { new MD5Digest(), new SHA1Digest(), new SHA256Digest() };
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initUI() {
		super.initUI();
		menu = new Container();
		menu.setBackColor(Colors.GRAY);
		edtInput = new Edit();
		edtInput.setText("0123456789ABCDEF");
		cboDigests = new ComboBox(comboItems);
		cboDigests.setSelectedIndex(0);
		btnGo = new Button(" Go! ");
		btnGo.setBackForeColors(Colors.P_DARK, Color.WHITE);
		
		add(menu, LEFT + gap, TOP + gap, FILL - gap, (int)(Settings.screenHeight * 0.15));
		menu.add(new Label("Message:"), LEFT + gap, TOP + gap/2);
		menu.add(edtInput, AFTER + gap, SAME, FILL - gap, PREFERRED);
		menu.add(cboDigests, LEFT + gap, BOTTOM - gap, menu);
		menu.add(btnGo, RIGHT - gap, BOTTOM - gap, SCREENSIZE + 30, PREFERRED + 30);
		
		btnGo.addPressListener((e) -> {
			Digest alg = (Digest) cboDigests.getSelectedItem();
			String message = edtInput.getText();

			alg.reset();
			alg.update(message.getBytes());
			byte[] digest = alg.getDigest();
			
			ScrollContainer sc = new ScrollContainer();
			sc.setBackColor(Color.darker(Colors.GRAY, 10));
			add(sc, LEFT + gap*3, AFTER + gap, FILL - gap*3, (int)(Settings.screenHeight * 0.18));
			Label title = new Label(alg.toString());
			title.align = CENTER;
			title.setFont(Font.getFont(true, 16));
			sc.add(title, CENTER, TOP + gap/2);
			sc.add(new Label("Message: " + message), LEFT + gap, AFTER + gap/2);
			sc.add(new Label("Digest: " + Convert.bytesToHexString(digest) + " (" + digest.length + " bytes)"), LEFT + gap, AFTER + gap/2);
		});
	}
}
