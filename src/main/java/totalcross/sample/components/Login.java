package totalcross.sample.components;

import totalcross.io.IOException;
import totalcross.res.Resources;
import totalcross.sample.util.Colors;
import totalcross.sample.util.Images;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Login extends Container{
	private Edit edPass, edLogin;
	private Check ch;
	private Button btLogin, btRegister;
	private ImageControl ic;
	
	public void initUI(){
		try {
			
			ic = new ImageControl(new Image("images/logo.png"));
			ic.scaleToFit = true;
			ic.centerImage = true;
			add(ic, LEFT, TOP+100, FILL, PARENTSIZE+30);
			
			edLogin = new Edit();
			edLogin.caption = "Login";
			edLogin.setBackColor(Color.RED);
			add(edLogin, CENTER, AFTER+60, PARENTSIZE+90, PREFERRED+30);
			
			edPass = new Edit();
			edPass.caption = "Password";
			edPass.setBackColor(Color.RED);
			add(edPass, SAME, AFTER+70, PARENTSIZE+90, PREFERRED+30);
			
			ch = new Check("Remember Me");
			add(ch, LEFT+86, AFTER+100, PARENTSIZE, PREFERRED+30);
			
			btLogin = new Button("Login");
			btLogin.setBackColor(Color.WHITE);
			add(btLogin, CENTER, AFTER+140, PARENTSIZE+80, PREFERRED+60);
			
			btRegister = new Button("Register Now");
			btRegister.transparentBackground = true;
			btRegister.setBorder(BORDER_NONE);
			add(btRegister, CENTER, AFTER, PARENTSIZE+30, PREFERRED+20);
			btRegister.addPressListener(e -> {Vm.exec("url", "http://www.totalcross.com", 0, true);});
			
		} catch (IOException | ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onEvent(Event e){
		try{
			switch(e.type){
				case ControlEvent.PRESSED:
					if(e.target == btLogin){
						if(edLogin.getLength() == 0 || edPass.getLength() == 0){
							MessageBox mb = new MessageBox("Message","Please fill all fields!",new String[]{"Close"});
							mb.setBackColor(Colors.BACKGROUND);
				        	mb.setForeColor(Colors.FOREGROUND);
							mb.headerColor = Colors.KHAKI;
							mb.footerColor = Colors.BACKGROUND;
							mb.setIcon(Images.getImageBlack(Resources.warning.getFrameInstance(0)));
					    	mb.popup();
							
						}
					}
			}
		}catch(Exception ee){
			MessageBox.showException(ee, true);
		}
	}
}
