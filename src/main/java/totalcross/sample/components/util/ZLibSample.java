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

package totalcross.sample.components.util;

import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.sample.util.Colors;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.util.zip.ZLib;

public class ZLibSample extends ScrollContainer {
	private Button btn;
	private int gap = (int) (Settings.screenDensity * 10);
	@Override
	public void initUI() {
		super.initUI();
		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		add(btn = new Button("    Start    "), CENTER, TOP + 3);
	}

	@Override
	public void onEvent(Event e) {
		if (e.type == ControlEvent.PRESSED && e.target == btn) {
			add(new Label("proceed(BEST_COMPRESSION)", LEFT, Color.BLACK, true), LEFT + gap, AFTER + gap);
			proceed(ZLib.BEST_COMPRESSION);
			
			add(new Label("proceed(BEST_SPEED)", LEFT, Color.BLACK, true), LEFT + gap, AFTER + gap);
			proceed(ZLib.BEST_SPEED);
		}
	}

	private void proceed(int compression_level) {
		int sz = 0;
		StringBuffer sb = new StringBuffer(42 * 500);

		for (int i = 0; i < 500; i++) {
			sb = sb.append("This is the uncompressed original message.");
		}
		
		add(new Label("original size is " + sb.length()), LEFT + gap, AFTER + gap);

		ByteArrayStream is = new ByteArrayStream(Convert.getBytes(sb));
		ByteArrayStream cs = new ByteArrayStream(0);

		try {
			int ini = Vm.getTimeStamp();
			sz = ZLib.deflate(is, cs, compression_level);
			int end = Vm.getTimeStamp();
			add(new Label("compressed output size=" + sz), LEFT + gap, AFTER + gap);
			add(new Label("Elapsed: " + (end - ini) + "ms"), LEFT + gap, AFTER + gap);

		} catch (IOException e) {
			add(new Label("an exception occurred : " + e.getMessage(), LEFT, Colors.ERROR, true), LEFT + gap, AFTER + gap);
		}
		try {
			cs.setPos(0);
		} catch (IOException exc) {
			add(new Label("an exception occurred : " + exc.getMessage(), LEFT, Colors.ERROR, true), LEFT + gap, AFTER + gap);
		}
		add(new Label("compressed byte stream:"), LEFT + gap, AFTER + gap);

		byte buf[] = new byte[8];
		int read, ofs = 0, left = sz;
		while (left > 0) {
			read = left;
			if (read > buf.length) {
				read = buf.length;
			}

			read = cs.readBytes(buf, 0, read);
			left -= read;

			sb.setLength(0);
			for (int i = 0; i < read; i++) {
				sb = sb.append(' ').append(Convert.unsigned2hex(buf[i], 2));
			}
			add(new Label(ofs + " - " + sb.toString()), LEFT + gap, AFTER + gap);

			ofs += read;
		}
		ByteArrayStream os = new ByteArrayStream(0);

		try {
			cs.setPos(0);
		} catch (IOException exc) {
			add(new Label("an exception occurred : " + exc.getMessage(), LEFT, Colors.ERROR, true), LEFT + gap, AFTER + gap);
		}
		try {
			int ini = Vm.getTimeStamp();
			sz = ZLib.inflate(cs, os, sz);
			int end = Vm.getTimeStamp();
			
			add(new Label("uncompressed output size=" + sz), LEFT + gap, AFTER + gap);
			add(new Label("Elapsed: " + (end - ini) + "ms"), LEFT + gap, AFTER + gap);

		} catch (IOException e) {
			add(new Label("an exception occurred : " + e.getMessage(), LEFT, Colors.ERROR, true), LEFT + gap, AFTER + gap);
		}

		if (sz > 0) {
			String str = new String(os.getBuffer(), 0, sz);
			
			add(new Label("original content was '" + str.substring(0, 20) + "'"), LEFT + gap, AFTER + gap);
			add(new Label("original size was " + sz), LEFT + gap, AFTER + gap);
		}
	}
}
