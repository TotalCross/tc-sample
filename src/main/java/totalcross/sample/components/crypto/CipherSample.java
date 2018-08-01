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

import totalcross.crypto.CryptoException;
import totalcross.crypto.cipher.AESCipher;
import totalcross.crypto.cipher.AESKey;
import totalcross.crypto.cipher.Cipher;
import totalcross.crypto.cipher.Key;
import totalcross.crypto.cipher.RSACipher;
import totalcross.crypto.cipher.RSAPrivateKey;
import totalcross.crypto.cipher.RSAPublicKey;
import totalcross.sample.util.Colors;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

/*
 * Comment about PKCS#5 padding: 
 * 
 * PKCS#5 padding is a padding scheme that always adds padding bytes even if the input 
 * size is a multiple of the blocksize (ie. 16 bytes). The value of the padding bytes is the number
 * of bytes added to get a multiple of the block size. Thus, once deciphered, you can immediately
 * find out how many padding bytes have been added by looking the value of the last byte of 
 * the output buffer. This is always true since there must always be at least one padding byte.
 *   
 * See http://tools.ietf.org/html/rfc3852#section-6.3 for more details.
 * 
 * For instance, if you use PKCS#5 with a 16 bytes input buffer, 16 values of 0x10 will be added resulting in 2
 * input blocks of 16 bytes each (32 bytes). Once deciphered, the 16 bytes padding block will be removed 
 * to resume the initial 16 bytes input buffer.
 */

public class CipherSample extends ScrollContainer {
	private Object[] ciphers;
	private Key[] encKeys;
	private Key[] decKeys;

	private int gap = 50;
	private Container options, adv;
	private Edit edtInput;
	private ComboBox cboCiphers;
	private ComboBox cboChaining;
	private ComboBox cboPadding;
	private Button btnGo;

	private byte[] AES_KEY = new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF };

	private static final byte[] RSA_N = new byte[] { (byte) 0, (byte) -60, (byte) -106, (byte) -118, (byte) -19,
			(byte) 57, (byte) -63, (byte) -18, (byte) 102, (byte) 111, (byte) -56, (byte) 1, (byte) 50, (byte) -101,
			(byte) -90, (byte) -85, (byte) -96, (byte) -66, (byte) -70, (byte) -49, (byte) -52, (byte) -3, (byte) 70,
			(byte) -120, (byte) 63, (byte) -76, (byte) -34, (byte) -114, (byte) 13, (byte) 8, (byte) 45, (byte) -124,
			(byte) -12, (byte) -6, (byte) 87, (byte) 90, (byte) 61, (byte) -124, (byte) -42, (byte) 34, (byte) 21,
			(byte) 14, (byte) -73, (byte) 21, (byte) -104, (byte) 70, (byte) 11, (byte) -59, (byte) 58, (byte) -72,
			(byte) -55, (byte) -98, (byte) 68, (byte) 123, (byte) -63, (byte) -11, (byte) -7, (byte) -115, (byte) 32,
			(byte) 57, (byte) -38, (byte) -41, (byte) -9, (byte) -108, (byte) 79 };

	private static final byte[] RSA_D = new byte[] { (byte) 122, (byte) -69, (byte) 13, (byte) -94, (byte) -54,
			(byte) -61, (byte) 67, (byte) 37, (byte) -38, (byte) -75, (byte) 127, (byte) -31, (byte) -21, (byte) -128,
			(byte) -29, (byte) 119, (byte) 104, (byte) 123, (byte) -46, (byte) -115, (byte) -60, (byte) -75, (byte) -53,
			(byte) 12, (byte) 18, (byte) -52, (byte) 58, (byte) -36, (byte) -15, (byte) -11, (byte) 17, (byte) 34,
			(byte) -109, (byte) -121, (byte) 5, (byte) 117, (byte) 109, (byte) -72, (byte) -27, (byte) -103, (byte) -85,
			(byte) -1, (byte) 37, (byte) -30, (byte) 38, (byte) -86, (byte) 88, (byte) -28, (byte) -26, (byte) -102,
			(byte) -10, (byte) 124, (byte) -97, (byte) -18, (byte) -118, (byte) 2, (byte) 36, (byte) 40, (byte) -47,
			(byte) -75, (byte) -44, (byte) 69, (byte) 10, (byte) 1 };

	private static final byte[] RSA_E = new byte[] { (byte) 1, (byte) 0, (byte) 1 };

	@Override
	public void initUI() {
		super.initUI();
		this.backColor = Color.WHITE;
		options = new Container();
		options.setBackForeColors(Colors.P_300, Colors.ON_P_300);

		ciphers = new Object[2];
		ciphers[0] = new AESCipher();
		ciphers[1] = new RSACipher();

		encKeys = new Key[2];
		encKeys[0] = new AESKey(AES_KEY);
		encKeys[1] = new RSAPublicKey(RSA_E, RSA_N);

		decKeys = new Key[2];
		decKeys[0] = encKeys[0];
		decKeys[1] = new RSAPrivateKey(RSA_E, RSA_D, RSA_N);

		edtInput = new Edit();
		edtInput.setText("0123456789ABCD");
		cboCiphers = new ComboBox(ciphers);
		cboCiphers.setSelectedIndex(0);

		cboChaining = new ComboBox(new Object[] { "NONE", "ECB", "CBC" });
		cboChaining.setSelectedIndex(0);

		cboPadding = new ComboBox(new Object[] { "NONE", "PKCS#1", "PKCS#5" });
		cboPadding.setSelectedIndex(0);

		btnGo = new Button("Go");
		btnGo.setBackForeColors(Colors.S_600, Colors.ON_S_600);

		add(options, LEFT + gap, TOP + gap, SCREENSIZE + 80, (int) (Settings.screenHeight * 0.15));
		options.add(new Label("Message:"), LEFT + gap / 2, TOP + gap / 2);
		options.add(edtInput, AFTER + gap, SAME, FILL, PREFERRED);
		options.add(cboCiphers, LEFT + gap / 2, AFTER + gap, SCREENSIZE + 22, PREFERRED);
		options.add(cboChaining, AFTER + gap, SAME, SCREENSIZE + 22, PREFERRED);
		options.add(cboPadding, AFTER + gap, SAME, SCREENSIZE + 29, PREFERRED);
		add(btnGo, AFTER + gap, SAME, FILL - gap, SAME, options);

		adv = new Container();
		adv.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);

		add(adv, LEFT + gap, AFTER + gap, FILL - gap, (int) (Settings.screenHeight * 0.22), options);
		Label titleAdv, valid1, valid2, valid3;

		adv.add(titleAdv = new Label("Valid options:"), CENTER, TOP + gap / 2);
		titleAdv.setFont(Font.getFont(true, 14));
		adv.add(valid1 = new Label("AES / CBC / PKCS#5"), LEFT + gap, AFTER + gap / 2);
		valid1.setFont(Font.getFont(14));
		adv.add(valid2 = new Label("AES / ECB / PKCS#5"), LEFT + gap, AFTER + gap / 2);
		valid2.setFont(Font.getFont(14));
		adv.add(valid3 = new Label("RSA / ECB / PKCS#1"), LEFT + gap, AFTER + gap / 2);
		valid3.setFont(Font.getFont(14));
	}

	@Override
	public void onEvent(Event e) {
		switch (e.type) {
		case ControlEvent.PRESSED:
			if (e.target == btnGo) {
				int index = cboCiphers.getSelectedIndex();
				int chaining = cboChaining.getSelectedIndex();
				int padding = cboPadding.getSelectedIndex();
				boolean validComb = true;
				String message = edtInput.getText();
				Cipher cipher = (Cipher) ciphers[index];
				ScrollContainer sc = new ScrollContainer(true, false);
				sc.setBackForeColors(Colors.P_600, Colors.ON_P_600);
				try {
					validComb = true;
					sc.add(new Label("Message: '" + message + "'"), LEFT + gap, TOP, PREFERRED, PREFERRED);

					byte[] iv = null; // no initialization vector => let the cipher generate a random one
					cipher.reset(Cipher.OPERATION_ENCRYPT, encKeys[index], chaining, iv, padding);
					iv = cipher.getIV(); // store the generated iv
					if (iv != null) {
						sc.add(new Label("Generated iv: " + Convert.bytesToHexString(iv)), LEFT + gap, AFTER, PREFERRED,
								PREFERRED);
					}

					cipher.update(message.getBytes());
					byte[] encrypted = cipher.getOutput();

					sc.add(new Label(
							"Encrypted: " + Convert.bytesToHexString(encrypted) + " (" + encrypted.length + " bytes)"),
							LEFT + gap, AFTER, PREFERRED, PREFERRED);

					cipher.reset(Cipher.OPERATION_DECRYPT, decKeys[index], chaining, iv, padding);
					cipher.update(encrypted);
					byte[] decrypted = cipher.getOutput();

					sc.add(new Label("Decrypted: '" + new String(decrypted) + "'"), LEFT + gap, AFTER, PREFERRED,
							PREFERRED);
				} catch (CryptoException ex) {
					validComb = false;
					sc.add(new Label("Invalid combination, please try the valid ones"), LEFT, AFTER, PREFERRED,
							PREFERRED);
					ex.printStackTrace();
				}
				if (validComb) {
					add(sc, CENTER, AFTER + gap, SCREENSIZE + 80, (int) (Settings.screenHeight * 0.2));
				} else {
					Label adv = new Label("Invalid combination");
					adv.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
					adv.setFont(Font.getFont(true, 12));
					adv.align = CENTER;
					add(adv, CENTER, AFTER + gap, PREFERRED + gap * 2, PREFERRED + gap * 2);
				}
			}
			break;
		}
	}
}