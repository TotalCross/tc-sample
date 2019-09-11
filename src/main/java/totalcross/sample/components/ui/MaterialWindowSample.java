package totalcross.sample.components.ui;

import totalcross.io.IOException;
import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sample.util.MaterialConstants;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Check;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.MaterialWindow;
import totalcross.ui.OutlinedEdit;
import totalcross.ui.Presenter;
import totalcross.ui.ScrollContainer;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.ui.layout.HBox;
import totalcross.ui.layout.VBox;
import totalcross.util.UnitsConverter;

public class MaterialWindowSample extends BaseScreen {

	public MaterialWindowSample () {
		super("https://totalcross.gitbook.io/playbook/components/material-window");
	}

	@Override
	public void onContent(ScrollContainer content) {
		Button btn1 = new Button("Material Window (Instant)");
		btn1.setBackForeColors(Colors.P_500, Colors.ON_P_500);

		VBox vBox = new VBox();
		vBox.setSpacing(MaterialConstants.EIGHT_DP_SPACING);
		vBox.transparentBackground = true;

		vBox.add(btn1);
		btn1.addPressListener((e) -> {
			MaterialWindow mw = new MaterialWindow("Material Window (Instant)", false, getPresenter());
			mw.popup();
		});

		Button btn2 = new Button("Material Window (Delayed)");
		btn2.setBackForeColors(Colors.P_500, Colors.ON_P_500);

		vBox.add(btn2);
		btn2.addPressListener((e) -> {
			MaterialWindow mw = new MaterialWindow("Material Window (Delayed)", true, getPresenter());
			mw.popup();
		});
		content.add(vBox, CENTER, CENTER, DP + 240, PREFERRED);
	}

	private Presenter getPresenter() {
		return new Presenter() {
			@Override
			public Container getView() {
				return new Container() {
					@Override
					public void initUI() {
						Edit edPass, edLogin;
						Check ch;
						Button btLogin, btRegister;
						ImageControl ic = null;

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
						add(ic, LEFT, TOP + UnitsConverter.toPixels(DP + 40), FILL, PARENTSIZE + 30);

						edLogin = new Edit();
						edLogin.caption = "Login";
						add(edLogin, CENTER, AFTER + MaterialConstants.EIGHT_DP_SPACING, PARENTSIZE + 90, PREFERRED);

						edPass = new Edit();
						edPass.caption = "Password";
						edPass.setMode(Edit.PASSWORD_ALL);
						add(edPass, SAME, AFTER + MaterialConstants.FOUR_DP_SPACING, PARENTSIZE + 90, PREFERRED);

						ch = new Check("Remember Me");
						add(ch, LEFT + 86, AFTER + 100, PARENTSIZE, PREFERRED);

						btLogin = new Button("Login");
						btLogin.setBackColor(Color.WHITE);
						add(btLogin, CENTER, AFTER + MaterialConstants.FOUR_DP_SPACING, PARENTSIZE + 80, PREFERRED);

						btRegister = new Button("Register Now");
						btRegister.transparentBackground = true;
						btRegister.setBorder(BORDER_NONE);
						add(btRegister, CENTER, AFTER, PARENTSIZE + 30, PREFERRED + 20);
						btRegister.addPressListener(e -> {
							Vm.exec("webview", "http://www.totalcross.com", 0, true);
						});
					}
				};
			}
		};
	}
}