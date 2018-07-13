package totalcross.sample.components.io;

import totalcross.io.Connector;
import totalcross.io.Stream;
import totalcross.io.device.RadioDevice;
import totalcross.io.device.bluetooth.DiscoveryAgent;
import totalcross.io.device.bluetooth.LocalDevice;
import totalcross.io.device.bluetooth.RemoteDevice;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ListBox;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.TimerEvent;
import totalcross.ui.gfx.Color;
import totalcross.util.IntHashtable;

public abstract class PrinterSampleBase extends Container{
	static ListBox lblog;
	Button btnRadioOn, btnDiscOn, btnRadioOff, btnListPaired, btnListUnpaired, btnConnect;
	Label lstatus;
	TimerEvent timer;
	Check chUnsec;
	static PrinterSampleBase instance;

	private void listDevices(String tit, RemoteDevice[] rd) {
		log(tit);
		if (rd == null) {
			log("No device found");
		} else {
			for (int i = 0; i < rd.length; i++) {
				log(rd[i]);
				lblog.ihtBackColors.put(lblog.size() - 1, Color.GREEN);
			}
		}
	}

	@Override
	public void initUI() {
		try {
			super.initUI();
			lblog = new ListBox();
			ScrollContainer sc = new ScrollContainer(false, true);
			add(sc, LEFT, TOP, FILL, FILL);
			lblog.add(sc, LEFT, TOP, FILL, FILL);
			instance = this;
			int gap = fmH / 2;
			Button.commonGap = fmH / 2;
			sc.add(new Label("This sample assumes you know which printer is near by!"), LEFT, TOP + 2);
			sc.add(btnRadioOn = new Button("set radio on"), LEFT, AFTER + gap);
			sc.add(btnRadioOff = new Button("set radio off"), AFTER + gap, SAME);
			sc.add(btnDiscOn = new Button("temporarily discoverable"), LEFT, AFTER + gap);
			sc.add(btnListPaired = new Button("list paired"), LEFT, AFTER + gap);
			sc.add(btnListUnpaired = new Button("list unpaired"), AFTER + gap, SAME);
			sc.add(btnConnect = new Button("connect to selected device"), LEFT, AFTER + gap);
			sc.add(chUnsec = new Check("Unsecure connection"), LEFT, AFTER + gap, FILL, SAME);
			Button.commonGap = 0;
			btnConnect.setEnabled(false);
			sc.add(lblog, LEFT, AFTER + gap, FILL, FILL);
			sc.add(lstatus = new Label("", CENTER), LEFT, AFTER);
			lblog.ihtBackColors = new IntHashtable(30);
			timer = addTimer(200);
			updateButtonState(false, false);
		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}

	public static void log(Object s) {
		lblog.add(s);
		instance.loglistSelected();
	}

	@Override
	public void onEvent(Event e) {
		try {
			switch (e.type) {
			case TimerEvent.TRIGGERED:
				if (timer != null && timer.triggered) {
					switch (RadioDevice.getState(RadioDevice.BLUETOOTH)) {
					case RadioDevice.RADIO_STATE_ENABLED:
						lstatus.setText("ON BUT NOT DISCOVERABLE");
						updateButtonState(true, false);
						break;
					case RadioDevice.BLUETOOTH_STATE_DISCOVERABLE:
						lstatus.setText("ON AND DISCOVERABLE");
						updateButtonState(true, true);
						break;
					default:
						lstatus.setText("BLUETOOTH IS OFF");
						updateButtonState(false, false);
						break;
					}
				}
				break;
			case ControlEvent.PRESSED:
				if (e.target == lblog) {
					loglistSelected();
				} else if (e.target == btnRadioOff) {
					RadioDevice.setState(RadioDevice.BLUETOOTH, RadioDevice.RADIO_STATE_DISABLED);
				} else if (e.target == btnRadioOn) {
					RadioDevice.setState(RadioDevice.BLUETOOTH, RadioDevice.RADIO_STATE_ENABLED);
				} else if (e.target == btnDiscOn) {
					RadioDevice.setState(RadioDevice.BLUETOOTH, RadioDevice.BLUETOOTH_STATE_DISCOVERABLE);
				} else if (e.target == btnListPaired) {
					RemoteDevice[] l = LocalDevice.getLocalDevice().getDiscoveryAgent()
							.retrieveDevices(DiscoveryAgent.PREKNOWN);
					if (l == null || l.length == 0) {
						Vm.sleep(250);
						l = LocalDevice.getLocalDevice().getDiscoveryAgent().retrieveDevices(DiscoveryAgent.PREKNOWN);
					}
					listDevices("Listing paired devices", l);
				} else if (e.target == btnListUnpaired) {
					listDevices("Listing unpaired devices",
							LocalDevice.getLocalDevice().getDiscoveryAgent().retrieveDevices(DiscoveryAgent.CACHED));
				} else if (e.target == btnConnect) {
					Object sel = lblog.getSelectedItem();
					if (sel != null && sel instanceof RemoteDevice) {
						boolean printIt = true;// ask("This test currently only works with a CPCL / CPL compatible
												// bluetooth printer (like Zebra MZ 320). Do you have such printer
												// attached and want to print a test page?");
						RemoteDevice rd = (RemoteDevice) sel;
						log("Connecting to " + rd.getBluetoothAddress());
						String addr = rd.getBluetoothAddress();
						if (chUnsec.isChecked()) {
							addr = "*" + addr;
						}
						Stream s = (Stream) Connector.open("btspp://" + addr + ":0");
						log("Connected!");
						if (s != null && printIt) {
							printSample(s);
						}
						try {
							s.close();
						} catch (Throwable t) {
						} // ignore any errors on close
						log("Finished.");
					}
				}
			}
		} catch (Exception ee) {
			log("Error - exception thrown " + ee);
			MessageBox.showException(ee, true);
		}
	}

	abstract protected void printSample(Stream s) throws Exception;

	private void loglistSelected() {
		Object sel = lblog.getSelectedItem();
		boolean ok = sel != null && sel instanceof RemoteDevice;
		btnConnect.setEnabled(ok);
		btnConnect.setBackColor(ok ? Color.GREEN : backColor);
	}

	private void updateButtonState(boolean on, boolean disc) {
		btnRadioOn.setEnabled(!on);
		btnRadioOff.setEnabled(on);
		btnDiscOn.setEnabled(!disc && on);
		btnListPaired.setEnabled(on);
		btnListUnpaired.setEnabled(on);
	}
}
