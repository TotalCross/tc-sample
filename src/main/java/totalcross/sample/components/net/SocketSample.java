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

package totalcross.sample.components.net;

import totalcross.io.IOException;
import totalcross.io.LineReader;
import totalcross.net.Socket;
import totalcross.net.SocketTimeoutException;
import totalcross.net.UnknownHostException;
import totalcross.sample.util.Colors;
import totalcross.sys.Convert;
import totalcross.sys.InvalidNumberException;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;

public class SocketSample extends ScrollContainer implements Runnable{
	private Button btnOpen;
	private Edit edA, edP;
	private Socket socket;
	private int gap = (int) (Settings.screenDensity * 20);
	private Thread loadData;

	@Override
	public void initUI() {
		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		add(new Label("Address: "), LEFT + gap, TOP + gap/2);
		add(edA = new Edit(""), AFTER + gap/3, SAME);
		edA.setText("www.google.com");
		add(new Label("Port: "), LEFT + gap, AFTER + gap/2);
		add(edP = new Edit("8080"), SAME , AFTER + gap/2, edA);
		edP.setText("80");

		add(btnOpen = new Button(" Open connection "), CENTER, AFTER + gap, PREFERRED + (int)(Settings.screenDensity * 32), (int)(Settings.screenDensity * 36));
		btnOpen.setBackForeColors(Colors.P_600, Colors.ON_P_600);
	}

	@Override
	public void onEvent(Event e) {
		if (e.type == ControlEvent.PRESSED) {
			if (e.target == btnOpen) {
				loadData = new Thread(this);
				loadData.start();
			}
		}
	}

	private void openSocket() throws totalcross.net.UnknownHostException, totalcross.io.IOException {
		repaintNow(); // release the button
		 log("opening connection...");
		int port = 80;
		if (edP.getLength() > 0) {
			try {
				port = Convert.toInt(edP.getText());
			} catch (InvalidNumberException ine) {
			}
		}

		socket = new Socket(edA.getText(), port, 25000);
		socket.readTimeout = 30000;

		 log("Socket opened");
		 log("Sending HttpGet");
		byte[] bytes = "GET / HTTP/1.0\n\n".getBytes();
		socket.writeBytes(bytes);
		 log("===== RESPONSE =====");

		// some sites can take a longer delay to start sending things,
		// so we loop until we find something to read
		LineReader lr = new LineReader(socket); // note: using socket.readLine is VERY slow.
		String line;
		try {
			while ((line = lr.readLine()) != null) {
				 log(line);
			}
		} catch (SocketTimeoutException ex) {
			 log("Read: Timeout!");
		}
		 log("===================");
		 log("Closing socket");
		socket.close();
		 log("Socket closed");
	}
	
	private void log(String message)  {
		Label l = new Label(message);
		l.autoSplit = true;
		add(l, LEFT + gap*2, AFTER + gap, FILL - gap*2, PREFERRED);
		repaintNow();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			openSocket();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}