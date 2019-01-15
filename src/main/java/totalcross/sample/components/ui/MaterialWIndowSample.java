package totalcross.sample.components.ui;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.io.IOException;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.MaterialWindow;
import totalcross.ui.OutlinedEdit;
import totalcross.ui.Presenter;
import totalcross.ui.VBox;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class MaterialWIndowSample extends Container {
	@Override
	public void initUI() {
		VBox vbox = new VBox();

		add(vbox, CENTER, CENTER, DP + 200, DP + 300);
		vbox.setSpacing(200);

		Button btn1 = new Button("Sign Up");
		btn1.setBackForeColors(Colors.P_500, Colors.ON_P_500);

		vbox.add(btn1);
		btn1.addPressListener((e) -> {
			MaterialWindow mw = new MaterialWindow("Sign Up", true, new Presenter<Container>() {
				@Override
				public Container getView() {
					return new Container() {
						@Override
						public void initUI() {
							setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
							ImageControl ic = null;
							try {
								ic = new ImageControl(new Image("images/logo.png"));
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (ImageException e1) {
								e1.printStackTrace();
							}
							ic.scaleToFit = true;
							ic.centerImage = true;
							add(ic, LEFT, TOP + 100, FILL, PARENTSIZE + 30);

							VBox vboxTotal = new VBox();
							vboxTotal.setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);

							Button singUP = new Button("Sing Up");
							Button cancel = new Button("Cancel");

							OutlinedEdit outUsu = new OutlinedEdit();
							OutlinedEdit outPass = new OutlinedEdit();
							OutlinedEdit outPassConf = new OutlinedEdit();
							OutlinedEdit outEmail = new OutlinedEdit();

							add(vboxTotal, CENTER, AFTER, (Settings.screenWidth / 10) * 7, Settings.screenHeight / 2);
							vboxTotal.setSpacing(25);

							outUsu.caption = "User";
							outUsu.captionColor = Color.BRIGHT;

							outPass.caption = "Password";
							outPass.captionColor = Color.BRIGHT;
							outPass.setMode(Edit.PASSWORD_ALL);

							outPassConf.caption = "Confirm Password";
							outPassConf.captionColor = Color.BRIGHT;
							outPassConf.setMode(Edit.PASSWORD_ALL);

							outEmail.caption = "Email";
							outEmail.captionColor = Color.BRIGHT;

							vboxTotal.add(outUsu);
							vboxTotal.add(outPass);
							vboxTotal.add(outPassConf);
							vboxTotal.add(outEmail);

							vboxTotal.add(singUP);
							vboxTotal.add(cancel);

						}
					};
				}
			});
			mw.popup();
		});

		Button btn2 = new Button("Sign in");
		btn2.setBackForeColors(Colors.P_500, Colors.ON_P_500);

		vbox.add(btn2);
		btn2.addPressListener((e) -> {
			MaterialWindow mw = new MaterialWindow("Sign in", true, new Presenter<Container>() {
				@Override
				public Container getView() {
					return new Container() {
						@Override
						public void initUI() {
							Edit edPass, edLogin;
							Check ch;
							Button btLogin, btRegister;
							ImageControl ic = null;
							SQLiteUtil util;

							setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
							try {
								ic = new ImageControl(new Image("images/logo.png"));
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (ImageException e1) {
								e1.printStackTrace();
							}
							ic.scaleToFit = true;
							ic.centerImage = true;
							add(ic, LEFT, TOP + 100, FILL, PARENTSIZE + 30);

							edLogin = new Edit();
							edLogin.caption = "Login";
							add(edLogin, CENTER, AFTER + 60, PARENTSIZE + 90, PREFERRED + 30);

							edPass = new Edit();
							edPass.caption = "Password";
							edPass.setMode(Edit.PASSWORD_ALL);
							add(edPass, SAME, AFTER + 70, PARENTSIZE + 90, PREFERRED + 30);

							ch = new Check("Remember Me");
							add(ch, LEFT + 86, AFTER + 100, PARENTSIZE, PREFERRED + 30);

							btLogin = new Button("Login");
							btLogin.setBackColor(Color.WHITE);
							add(btLogin, CENTER, AFTER + 140, PARENTSIZE + 80, PREFERRED + 60);

							btRegister = new Button("Register Now");
							btRegister.transparentBackground = true;
							btRegister.setBorder(BORDER_NONE);
							add(btRegister, CENTER, AFTER, PARENTSIZE + 30, PREFERRED + 20);
							btRegister.addPressListener(e -> {
								Vm.exec("url", "http://www.totalcross.com", 0, true);
							});

						}
					};
				}
			});
			mw.popup();

		});

	}
}