package totalcross.sample.main;

import totalcross.io.IOException;
import totalcross.sample.components.*;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Insets;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.SideMenuContainer;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;


public class TCSample extends MainWindow {

	SideMenuContainer sideMenu;
	private final int COMPONENT_H = fmH-16;
	//private final int FLAT_EDGE_MARGIN = (int) (Math.min(Settings.screenHeight, Settings.screenWidth) * 0.20);
	
	static {
		Settings.applicationId = "VKSS";
		Settings.appVersion = "1.0.1";
	}
	
	public TCSample() {
		
		super("TotalCross Sample", NO_BORDER);
		
        setUIStyle(Settings.Material);
        Settings.uiAdjustmentsBasedOnFontHeight = true;
        
        setBackForeColors(Colors.BACKGROUND, Colors.FOREGROUND);
		
	}
	
	@Override
	public void initUI() {
		try{
		
			SideMenuContainer.Item home = new SideMenuContainer.Item("Home", new Image("images/bt_home.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new Home(); });
			SideMenuContainer.Item login= new SideMenuContainer.Item("Login", new Image("images/bt_login.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new Login(); });
			SideMenuContainer.Item buttons = new SideMenuContainer.Item("Button", new Image("images/square-inc.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new ButtonSample(); });
			SideMenuContainer.Item edits = new SideMenuContainer.Item("Edit", new Image("images/format-text.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new EditSample(); });
			SideMenuContainer.Item checkRadio = new SideMenuContainer.Item("Check and Radio", new Image("images/checkbox-marked.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new CheckRadioSample(); });
			SideMenuContainer.Item comboList = new SideMenuContainer.Item("Combo and List", new Image("images/arrow-down-drop-circle.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new ComboListSample(); });
			SideMenuContainer.Item listContainer = new SideMenuContainer.Item("List Container", new Image("images/view-agenda.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new ListContainerSample(); });	
			SideMenuContainer.Item awesomeFont = new SideMenuContainer.Item("Awesome Font", new Image("images/format-font.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new AwesomeFontSample(); });
			SideMenuContainer.Item camera = new SideMenuContainer.Item("Camera", new Image("images/camera.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new CameraSample(); });
			SideMenuContainer.Item messageBox = new SideMenuContainer.Item("MessageBox", new Image("images/message.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new MessageBoxSample(); });
			
			sideMenu = new SideMenuContainer(null,
	        		home,
	        		login,
	        		buttons,
	        		edits,
	        		checkRadio,
	        		comboList,
	        		listContainer,
	        		awesomeFont,
	        		camera,
	        		messageBox
	        ); 
			sideMenu.topMenu.header = new Container() {
				@Override
				public void initUI() {
					
					try {
						ImageControl fundo = new ImageControl(new Image("images/fundo.jpg"));
						fundo.transparentBackground = true;
						fundo.scaleToFit = true;
						fundo.strechImage = true;
						fundo.hwScale = true;
						add(fundo,LEFT,TOP, FILL, FILL);
						
						ImageControl profile = new ImageControl(new Image("images/elon.png"));
						profile.scaleToFit = true;
						profile.transparentBackground = true;
						add(profile, CENTER+35, BOTTOM-250, PARENTSIZE, PARENTSIZE+30);
						
						Label nome = new Label("Elon Musk");
						nome.setForeColor(Colors.BACKGROUND);
						nome.transparentBackground = true;
						add(nome, LEFT+35, AFTER+15, PARENTSIZE+30, PARENTSIZE+10);
						
						Font mail = Font.getFont(true, Font.NORMAL_SIZE - 4);
						Label email = new Label("elonmusk@totalcross.com");
						email.setForeColor(Colors.P_GREY);
						email.transparentBackground = true;
						email.setFont(mail);
						add(email, SAME, BOTTOM-70, PARENTSIZE, PARENTSIZE+8);
						
						
						
					} catch (IOException | ImageException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				@Override
				public int getPreferredHeight() {
				return fmH * 11;
				}
			};
			
			sideMenu.topMenu.scInsets = new Insets(0, 0, 0, 0);
			sideMenu.setBarFont(Font.getFont(Font.getDefaultFontSize() + COMPONENT_H));
	        sideMenu.setBackColor(Colors.PRIMARY);
	        sideMenu.setForeColor(Color.BLACK);
	        sideMenu.setItemForeColor(Colors.P_DARK);
	        sideMenu.topMenu.drawSeparators = false;
	        sideMenu.topMenu.itemHeightFactor = 3;
	        
			add(sideMenu, LEFT, TOP, PARENTSIZE, PARENTSIZE);
		
		} catch (ImageException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
