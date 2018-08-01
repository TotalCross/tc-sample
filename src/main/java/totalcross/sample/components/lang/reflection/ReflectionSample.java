package totalcross.sample.components.lang.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import totalcross.sample.util.Colors;
import totalcross.ui.AlignedLabelsContainer;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

public class ReflectionSample extends ScrollContainer {
	private int gap = 50;
	// ui
	private Button btAdd;
	private Edit edName, edAddr, edNumber, edAge;
	
	// reflection
	private Constructor<?> c;
	private Field fname, faddr, fnumb;
	private Method mage;

	@Override
	public void initUI() {
		try {
			super.initUI();
			AlignedLabelsContainer alc = new AlignedLabelsContainer(
					new String[] { "Name: ", "Address: ", "Addr.Number: ", "Age: " });
			alc.setFont(font.asBold());
			add(alc, LEFT + gap, TOP + gap, FILL - gap, PREFERRED);
			alc.add(edName = new Edit(), LEFT, alc.getLineY(0));
			alc.add(edAddr = new Edit(), LEFT, alc.getLineY(1));
			alc.add(edNumber = new Edit(), LEFT, alc.getLineY(2));
			edNumber.setKeyboard(Edit.KBD_NUMERIC);
			alc.add(edAge = new Edit(), LEFT, alc.getLineY(3));
			edAge.setKeyboard(Edit.KBD_NUMERIC);
			btAdd = new Button("ADD");
			btAdd.setBackForeColors(Colors.P_600, Colors.ON_P_600);
			add(btAdd, CENTER, AFTER + gap*2, SCREENSIZE + 50, PREFERRED +fmH*6);
			Label adv = new Label("Constructed and retrieved using reflection:", CENTER);
			adv.autoSplit = true;
			add(adv, LEFT + gap, AFTER + gap);
			// get access to Data's fields
			Class<?> data = Class.forName("totalcross.sample.components.lang.reflection.Data");
			c = data.getConstructor(new Class[] { String.class, String.class, int.class, byte.class });
			fname = data.getField("name");
			faddr = data.getField("address");
			fnumb = data.getField("number");
			mage = data.getMethod("getAge", new Class[0]);
		} catch (Throwable e) {
			MessageBox.showException(e, true);
		}
	}

	@Override
	public void onEvent(Event e) {
		if (e.type == ControlEvent.PRESSED && e.target == btAdd) {
			try {
				if (edName.getTrimmedLength() == 0 || edAddr.getTrimmedLength() == 0 || edNumber.getTrimmedLength() == 0
						|| edAge.getTrimmedLength() == 0) {
					MessageBox mb = new MessageBox("Error", "Please fill all fields");
					mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
					mb.popup();
				} else {
					// Data d = new Data(name, addr, number, age);
					Object o = c.newInstance(new Object[] { edName.getText(), edAddr.getText(),
							Integer.valueOf(edNumber.getText()), Byte.valueOf(edAge.getText()) });
					// sn = d.name;
					String sn = (String) fname.get(o);
					// sa = d.address;
					String sa = (String) faddr.get(o);
					// in = d.number;
					int in = fnumb.getInt(o);
					// ba = d.getAge();
					byte ba = ((Byte) mage.invoke(o, (Object[]) null)).byteValue();
					// show in list
					ScrollContainer box = new ScrollContainer();
					box.setFont(Font.getFont(true, 16));
					box.setBackForeColors(Colors.S_700, Colors.ON_S_700);
					add(box, CENTER, AFTER + gap, SCREENSIZE + 70, box.getFont().size*8);

					box.add(new Label("Name: " + sn), LEFT + gap, TOP + gap, PREFERRED, fmH);
					box.add(new Label("Address: " + sa), LEFT + gap, AFTER + gap, PREFERRED, fmH);
					box.add(new Label("Number: " + in), LEFT + gap, AFTER + gap, PREFERRED, fmH);
					box.add(new Label("Age: " + ba), LEFT + gap, AFTER + gap, PREFERRED, fmH);
				}
			} catch (Throwable ee) {
				MessageBox.showException(ee, true);
			}
		}
	}
}
