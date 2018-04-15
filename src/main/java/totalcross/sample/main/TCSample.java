package totalcross.sample.main;

import totalcross.io.IOException;
import totalcross.sample.components.AwesomeFontSample;
import totalcross.sample.components.ButtonSample;
import totalcross.sample.components.CameraSample;
import totalcross.sample.components.CheckRadioSample;
import totalcross.sample.components.ComboListSample;
import totalcross.sample.components.EditSample;
import totalcross.sample.components.Home;
import totalcross.sample.components.ListContainerSample;
import totalcross.sample.components.Login;
import totalcross.sample.components.MessageBoxSample;
import totalcross.sample.components.cards.CardsSample;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.SideMenuContainer;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.icon.Icon;
import totalcross.ui.icon.MaterialIcons;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;


public class TCSample extends MainWindow {

	SideMenuContainer sideMenu;
		
	static {
		Settings.applicationId = "VKSS";
		Settings.appVersion = "1.0.2";
		Settings.iosCFBundleIdentifier = "com.totalcross.showcase";
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
		
			SideMenuContainer.Item home = new SideMenuContainer.Item("Home", new Image("images/home_icon.png").hwScaledFixedAspectRatio(fmH*2, true),  () -> { return new Home();});
			SideMenuContainer.Item login= new SideMenuContainer.Item("Login", new Image("images/bt_login.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new Login(); });
			SideMenuContainer.Item buttons = new SideMenuContainer.Item("Button", new Image("images/button_icon.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new ButtonSample(); });
			SideMenuContainer.Item edits = new SideMenuContainer.Item("Edit", new Image("images/edit.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new EditSample(); });
			SideMenuContainer.Item checkRadio = new SideMenuContainer.Item("Check and Radio", new Image("images/checkbox-marked.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new CheckRadioSample(); });
			SideMenuContainer.Item comboList = new SideMenuContainer.Item("Combo and List", new Image("images/arrow-down-drop-circle.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new ComboListSample(); });
			SideMenuContainer.Item listContainer = new SideMenuContainer.Item("List Container", new Image("images/list_container.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new ListContainerSample(); });	
			SideMenuContainer.Item awesomeFont = new SideMenuContainer.Item("Awesome Font", new Image("images/awesomefont-icon.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new AwesomeFontSample(); });
			SideMenuContainer.Item camera = new SideMenuContainer.Item("Camera", new Image("images/bt_camera.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new CameraSample(); });
			SideMenuContainer.Item messageBox = new SideMenuContainer.Item("MessageBox", new Image("images/message.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new MessageBoxSample(); });
			SideMenuContainer.Item cards = new SideMenuContainer.Item("Cards", new Image("images/cards.png").hwScaledFixedAspectRatio(fmH*2,true),  () -> { return new CardsSample(); });
			
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
	        		messageBox,
	        		cards
	        ); 
			
			sideMenu.topMenu.header = new Container() {
				@Override
				public void initUI() {
					
					try {
						setBackColor(Colors.REDDESIGN);
						
						ImageControl profile = new ImageControl(new Image("images/logoV.png"));
						profile.scaleToFit = true;
						profile.transparentBackground = true;
						add(profile, CENTER+65, BOTTOM-250, PARENTSIZE, PARENTSIZE+40);
						
						Label title = new Label("Components", CENTER, Color.WHITE, false);
						title.setFont(Font.getFont("Lato Bold", false, this.getFont().size + 5));
						title.setForeColor(Color.WHITE);
						add(title, LEFT+45, BOTTOM-45, PARENTSIZE+38, PREFERRED);
						
					} catch (IOException | ImageException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				@Override
				public int getPreferredHeight() {
					return 350;
				}
			};
			
			
			sideMenu.setBarFont(Font.getFont(Font.getDefaultFontSize() + 5));
	        sideMenu.setBackColor(Colors.PRIMARY);
	        sideMenu.setForeColor(Color.WHITE);
	        sideMenu.setItemForeColor(Color.BLACK);
	        sideMenu.topMenu.drawSeparators = false;
	        sideMenu.topMenu.itemHeightFactor = 3;
	        
	        Icon icon = new Icon(MaterialIcons._MENU);
	        icon.setBackColor(Color.WHITE);
	        add(icon, LEFT, TOP);
			add(sideMenu, LEFT, TOP, PARENTSIZE, PARENTSIZE);
		
		} catch (ImageException | IOException e) {
			e.printStackTrace();
		} 
	}
}
