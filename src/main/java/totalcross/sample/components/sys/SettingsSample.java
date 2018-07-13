package totalcross.sample.components.sys;

import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.ListBox;
import totalcross.ui.ScrollContainer;
import totalcross.ui.font.Font;

public class SettingsSample extends ScrollContainer{
	@Override
	  public void initUI() {
	    super.initUI();
	    ListBox lb = new ListBox();
	    add(lb, LEFT, TOP, FILL, FILL);
	    lb.add("Version is " + Settings.versionStr);
	    lb.add("Build number is " + Settings.buildNumber);
	    lb.add("Platform is " + Settings.platform);
	    lb.add("User is " + Settings.userName);
	    lb.add("Virtual keyboard is " + Settings.virtualKeyboard);
	    lb.add("Screen is " + Settings.screenWidth + "x" + Settings.screenHeight);
	    lb.add("Screen dpi is " + Settings.screenWidthInDPI + "x" + Settings.screenHeightInDPI);
	    lb.add("Default font size is " + Font.NORMAL_SIZE);
	    lb.add("Device font height is " + Settings.deviceFontHeight);
	    lb.add("Screen bpp is " + Settings.screenBPP);
	    lb.add("timeZoneMinutes is " + Settings.timeZoneMinutes);
	    lb.add("daylightSavingsMinutes is " + Settings.daylightSavingsMinutes);
	    lb.add("dateFormat is " + Settings.dateFormat);
	    lb.add("dateSeparator is " + Settings.dateSeparator);
	    lb.add("decimalSeparator is " + Settings.decimalSeparator);
	    lb.add("thousandsSeparator is " + Settings.thousandsSeparator + "  ");
	    lb.add("timeSeparator is " + Settings.timeSeparator);
	    lb.add("is24Hour is " + Settings.is24Hour);
	    lb.add("weekStart is " + Settings.weekStart);
	    lb.add("Battery is at " + Vm.getRemainingBattery() + "%");
	    lb.add("Free memory is at " + Vm.getFreeMemory());
	    lb.add("Line number is " + Settings.lineNumber);
	    lb.add("Rom serial number is " + Settings.romSerialNumber);
	    lb.add("Mac Address is " + Settings.macAddress);
	    
	    if (Settings.imeis == null) {
	      ;
	    } else if (Settings.imeis.length == 1) {
	    	lb.add("IMEI is " + Settings.imeis[0]);
	    } else {
	    	lb.add("IMEI 1 is " + Settings.imeis[0]);
	    	lb.add("IMEI 2 is " + Settings.imeis[1]);
	    }
	    lb.add("ICCID is " + Settings.iccid);
	    lb.add("Rom version is " + Settings.romVersion);
	    lb.add("Device id is " + Settings.deviceId);
	    lb.add("App path is " + Settings.appPath);
	    lb.add("iosCertDate is " + (Settings.iosCertDate != null ? Settings.iosCertDate.getSQLString() : "null"));
	  }
}
