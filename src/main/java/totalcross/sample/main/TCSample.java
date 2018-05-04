package totalcross.sample.main;

import totalcross.io.IOException;
import totalcross.sample.components.*;
import totalcross.sample.components.cards.CardsSample;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.*;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.icon.MaterialIcons;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class TCSample extends MainWindow {

  SideMenuContainer sideMenu;

  static {
    Settings.applicationId = "VKSS";
    Settings.appVersion = "1.0.4";
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
	SideMenuContainer.Item home = new SideMenuContainer.Item("Home", MaterialIcons._HOME, Color.BLACK, false, () -> { return new Home();});
	SideMenuContainer.Item login= new SideMenuContainer.Item("Login", MaterialIcons._PERSON, Color.BLACK,  () -> { return new Login(); });
	SideMenuContainer.Item buttons = new SideMenuContainer.Item("Button", MaterialIcons._TOUCH_APP, Color.BLACK,  () -> { return new ButtonSample(); });
	SideMenuContainer.Item notifications = new SideMenuContainer.Item("Notifications", MaterialIcons._NOTIFICATIONS_ACTIVE, Color.BLACK,  () -> { return new NotificationsSample(); });
	SideMenuContainer.Item edits = new SideMenuContainer.Item("Edit", MaterialIcons._TEXT_FORMAT, Color.BLACK,  () -> { return new EditSample(); });
	SideMenuContainer.Item checkRadio = new SideMenuContainer.Item("Check and Radio", MaterialIcons._CHECK_BOX, Color.BLACK,  () -> { return new CheckRadioSample(); });
	SideMenuContainer.Item comboList = new SideMenuContainer.Item("Combo and List", MaterialIcons._ARROW_DROP_DOWN_CIRCLE, Color.BLACK,  () -> { return new ComboListSample(); });
	SideMenuContainer.Item listContainer = new SideMenuContainer.Item("List Container", MaterialIcons._VIEW_LIST, Color.BLACK,  () -> { return new ListContainerSample(); });	
	SideMenuContainer.Item materialicons = new SideMenuContainer.Item("Material Icons", MaterialIcons._FONT_DOWNLOAD, Color.BLACK,  () -> { return new MaterialIconsSample(); });
	SideMenuContainer.Item camera = new SideMenuContainer.Item("Camera", MaterialIcons._PHOTO_CAMERA, Color.BLACK,  () -> { return new CameraSample(); });
	SideMenuContainer.Item messageBox = new SideMenuContainer.Item("MessageBox", MaterialIcons._QUESTION_ANSWER, Color.BLACK,  () -> { return new MessageBoxSample(); });
	SideMenuContainer.Item sqlite = new SideMenuContainer.Item("SQLite / Grid", MaterialIcons._STORAGE, Color.BLACK,  () -> { return new SQLiteFormGridTabbedContainer(); });
	SideMenuContainer.Item xml = new SideMenuContainer.Item("XML", MaterialIcons._CODE, Color.BLACK, () -> { return new XMLParseSample(); });
	SideMenuContainer.Item cards = new SideMenuContainer.Item("Cards",MaterialIcons._VIEW_AGENDA, Color.BLACK,  () -> { return new CardsSample(); });

    sideMenu =
        new SideMenuContainer(
            null,
            home,
            login,
            buttons,
            cards,
            notifications,
            edits,
            checkRadio,
            comboList,
            listContainer,
            materialicons,
            camera,
            messageBox,
            sqlite,
            xml);

    sideMenu.topMenu.header =
        new Container() {
          @Override
          public void initUI() {

            try {
              setBackColor(Colors.REDDESIGN);

              Label title = new Label("Components", CENTER, Color.WHITE, false);
              title.setFont(Font.getFont("Lato Bold", false, this.getFont().size + 5));
              title.setForeColor(Color.WHITE);
              add(title, LEFT + 45, BOTTOM - 30, PARENTSIZE + 38, DP + 56);

              ImageControl profile = new ImageControl(new Image("images/logoV.png"));
              profile.scaleToFit = true;
              profile.transparentBackground = true;
              add(profile, LEFT + 45, TOP + 150, PREFERRED, FIT);

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

    add(sideMenu, LEFT, TOP, PARENTSIZE, PARENTSIZE);
  }
}
