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
import totalcross.sample.components.BaseScreen;
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
import totalcross.util.UnitsConverter;
import totalcross.util.pdf.Base;

public class DigestSample extends BaseScreen {
	private int gap = UnitsConverter.toPixels(DP + 8);
	private Container menu;
	private Edit edtInput;
	private ComboBox cboDigests;
	private Button btnGo;
	private Object[] comboItems;

	public DigestSample() {
		super("https://totalcross.gitbook.io/playbook/apis/visao-geral-da-api#totalcross-crypto");
		try {
			comboItems = new Object[] { new MD5Digest(), new SHA1Digest(), new SHA256Digest() };
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void onContent(ScrollContainer content) {
		menu = new Container();
		menu.setBackForeColors(Colors.P_300, Colors.ON_P_300);
		edtInput = new Edit();
		edtInput.setText("0123456789ABCDEF");
		cboDigests = new ComboBox(comboItems);
		cboDigests.setSelectedIndex(0);
		btnGo = new Button(" Go! ");
		btnGo.setBackForeColors(Colors.S_600, Colors.ON_S_600);
		
		content.add(menu, LEFT + gap, TOP + gap, SCREENSIZE + 80, WILL_RESIZE);
		menu.setInsets(gap/4, gap/4, gap/4, gap/4);
		Label lbl = new Label("Message:");
		menu.add(lbl, LEFT + gap, TOP + gap/2);
		menu.add(edtInput, AFTER + gap, SAME, FILL - gap, PREFERRED);
		menu.add(cboDigests, SAME, AFTER + gap);
		menu.resizeHeight();
		content.add(btnGo, AFTER + gap, SAME, FILL - gap, SAME, menu);
		
		btnGo.addPressListener((e) -> {
			Digest alg = (Digest) cboDigests.getSelectedItem();
			String message = edtInput.getText();

			alg.reset();
			alg.update(message.getBytes());
			byte[] digest = alg.getDigest();
			
			ScrollContainer sc = new ScrollContainer();
			sc.setBackForeColors(Colors.P_600, Colors.ON_P_600);
			content.add(sc, LEFT + gap*3, AFTER + gap, FILL - gap*3, (int)(Settings.screenHeight * 0.18));
			Label title = new Label(alg.toString(), CENTER);
			
			title.setFont(Font.getFont(true, 16));
			sc.add(title, CENTER, TOP + gap/2);
			sc.add(new Label("Message: " + message), LEFT + gap, AFTER + gap/2);
			sc.add(new Label("Digest: " + Convert.bytesToHexString(digest) + " (" + digest.length + " bytes)"), LEFT + gap, AFTER + gap/2);
		});
	}
}
