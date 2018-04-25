package totalcross.sample.main;

import totalcross.io.IOException;
import totalcross.sample.components.*;
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
		
		super("TotalCross Showcase", NO_BORDER);
		
        setUIStyle(Settings.Material);
        Settings.uiAdjustmentsBasedOnFontHeight = true;
        
        setBackForeColors(Colors.BACKGROUND, Colors.FOREGROUND);
		
	}
	
	@Override
	public void initUI() {
		SideMenuContainer.Item home = new SideMenuContainer.Item("Home", MaterialIcons._HOME, Colors.PRIMARY, false, () -> { return new Home();});
		SideMenuContainer.Item login= new SideMenuContainer.Item("Login", MaterialIcons._PERSON, Colors.PRIMARY,  () -> { return new Login(); });
		SideMenuContainer.Item buttons = new SideMenuContainer.Item("Button", MaterialIcons._TOUCH_APP, Colors.PRIMARY,  () -> { return new ButtonSample(); });
		SideMenuContainer.Item notifications = new SideMenuContainer.Item("Notifications", MaterialIcons._NOTIFICATIONS_ACTIVE, Colors.PRIMARY,  () -> { return new NotificationsSample(); });
		SideMenuContainer.Item edits = new SideMenuContainer.Item("Edit", MaterialIcons._TEXT_FORMAT, Colors.PRIMARY,  () -> { return new EditSample(); });
		SideMenuContainer.Item checkRadio = new SideMenuContainer.Item("Check and Radio", MaterialIcons._CHECK_BOX, Colors.PRIMARY,  () -> { return new CheckRadioSample(); });
		SideMenuContainer.Item comboList = new SideMenuContainer.Item("Combo and List", MaterialIcons._ARROW_DROP_DOWN_CIRCLE, Colors.PRIMARY,  () -> { return new ComboListSample(); });
		SideMenuContainer.Item listContainer = new SideMenuContainer.Item("List Container", MaterialIcons._VIEW_LIST, Colors.PRIMARY,  () -> { return new ListContainerSample(); });	
		SideMenuContainer.Item fontawesome = new SideMenuContainer.Item("Font Awesome", MaterialIcons._TEXT_FIELDS, Colors.PRIMARY,  () -> { return new AwesomeFontSample(); });
		SideMenuContainer.Item materialicons = new SideMenuContainer.Item("Material Icons", MaterialIcons._FONT_DOWNLOAD, Colors.PRIMARY,  () -> { return new MaterialIconsSample(); });
		SideMenuContainer.Item camera = new SideMenuContainer.Item("Camera", MaterialIcons._PHOTO_CAMERA, Colors.PRIMARY,  () -> { return new CameraSample(); });
		SideMenuContainer.Item messageBox = new SideMenuContainer.Item("MessageBox", MaterialIcons._QUESTION_ANSWER, Colors.PRIMARY,  () -> { return new MessageBoxSample(); });
		SideMenuContainer.Item sqlite = new SideMenuContainer.Item("SQLite / Grid", MaterialIcons._STORAGE, Colors.PRIMARY,  () -> { return new SQLiteFormGridTabbedContainer(); });
		SideMenuContainer.Item xml = new SideMenuContainer.Item("XML", MaterialIcons._CODE, Colors.PRIMARY, () -> { return new XMLParseSample(); });
		SideMenuContainer.Item cards = new SideMenuContainer.Item("Cards",MaterialIcons._PICTURE_IN_PICTURE_ALT, Colors.PRIMARY,  () -> { return new CardsSample(); });
		
		sideMenu = new SideMenuContainer(null,
        		home,
        		login,
        		buttons,
            	cards,
        		notifications,
        		edits,
        		checkRadio,
        		comboList,
        		listContainer,
        		fontawesome,
        		materialicons,
        		camera,
        		messageBox,
        		sqlite,
        		xml
        ); 
		
		sideMenu.topMenu.header = new Container() {
			@Override
			public void initUI() {
				
				try {
					setBackColor(Colors.REDDESIGN);
					
					Label title = new Label("Components", CENTER, Color.WHITE, false);
					title.setFont(Font.getFont("Lato Bold", false, this.getFont().size + 5));
					title.setForeColor(Color.WHITE);
					add(title, LEFT+45, BOTTOM-45, PARENTSIZE+38, DP + 56);
					
					ImageControl profile = new ImageControl(new Image("images/logoV.png"));
					profile.scaleToFit = true;
					profile.transparentBackground = true;
					add(profile, LEFT + 45, TOP + 45, PREFERRED, FIT);
					
				} catch (IOException | ImageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	}
}
