package totalcross.sample.main;

import totalcross.io.IOException;
import totalcross.sample.components.*;
import totalcross.sample.components.cards.CardsSample;
import totalcross.sample.components.ui.AccordionSample;
import totalcross.sample.components.ui.AlignedLabelsSample;
import totalcross.sample.components.ui.ButtonSample;
import totalcross.sample.components.ui.CameraSample;
import totalcross.sample.components.ui.ChartSample;
import totalcross.sample.components.ui.CheckRadioSample;
import totalcross.sample.components.ui.ColorPickerSample;
import totalcross.sample.components.ui.ComboListSample;
import totalcross.sample.components.ui.ControlAnimationSample;
import totalcross.sample.components.ui.DynScrollContainerSample;
import totalcross.sample.components.ui.EditSample;
import totalcross.sample.components.ui.FontSample;
import totalcross.sample.components.ui.GridSample;
import totalcross.sample.components.ui.HTMLContainerSample;
import totalcross.sample.components.ui.ImageAnimationSample;
import totalcross.sample.components.ui.ImageBookSample;
import totalcross.sample.components.ui.ImageControlSample;
import totalcross.sample.components.ui.ImageModifiersSample;
import totalcross.sample.components.ui.ListContainerSample;
import totalcross.sample.components.ui.Login;
import totalcross.sample.components.ui.MaterialIconsSample;
import totalcross.sample.components.ui.MessageBoxSample;
import totalcross.sample.components.ui.MultiTouchSample;
import totalcross.sample.components.ui.NotificationsSample;
import totalcross.sample.components.ui.ProgressBoxSample;
import totalcross.sample.components.ui.SliderSample;
import totalcross.sample.components.ui.SpinnerSample;
import totalcross.sample.components.ui.SwitchSample;
import totalcross.sample.components.crypto.CipherSample;
import totalcross.sample.components.crypto.DigestSample;
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
    Settings.appVersion = "2.0.1";
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
	//General
	SideMenuContainer.Item home = new SideMenuContainer.Item("Home", MaterialIcons._HOME, Color.BLACK, false, () -> { return new Home();});
	SideMenuContainer.Item login= new SideMenuContainer.Item("Login", MaterialIcons._PERSON, Color.BLACK,  () -> { return new Login(); });
	//UI
	SideMenuContainer.Item accordion = new SideMenuContainer.Item("Accordion Container", MaterialIcons._FORMAT_ALIGN_LEFT, Color.BLACK,  () -> { return new AccordionSample(); });
	SideMenuContainer.Item alignedLabels = new SideMenuContainer.Item("Aligned Labels", MaterialIcons._MENU, Color.BLACK,  () -> { return new AlignedLabelsSample(); });
	SideMenuContainer.Item buttons = new SideMenuContainer.Item("Button", MaterialIcons._TOUCH_APP, Color.BLACK,  () -> { return new ButtonSample(); });
	SideMenuContainer.Item camera = new SideMenuContainer.Item("Camera", MaterialIcons._PHOTO_CAMERA, Color.BLACK,  () -> { return new CameraSample(); });
	SideMenuContainer.Item cards = new SideMenuContainer.Item("Cards", MaterialIcons._VIEW_AGENDA, Color.BLACK,  () -> { return new CardsSample(); });
	SideMenuContainer.Item chart = new SideMenuContainer.Item("Charts", MaterialIcons. _GRAPHIC_EQ, Color.BLACK,  () -> { return new ChartSample(); });
	SideMenuContainer.Item checkRadio = new SideMenuContainer.Item("Check and Radio", MaterialIcons._CHECK_BOX, Color.BLACK,  () -> { return new CheckRadioSample(); });
	SideMenuContainer.Item colorPicker = new SideMenuContainer.Item("Color Picker", MaterialIcons._COLORIZE, Color.BLACK,  () -> { return new ColorPickerSample(); });
	SideMenuContainer.Item comboList = new SideMenuContainer.Item("Combo and List", MaterialIcons._ARROW_DROP_DOWN_CIRCLE, Color.BLACK,  () -> { return new ComboListSample(); });
	SideMenuContainer.Item controlAnimation = new SideMenuContainer.Item("Plataform Animations", MaterialIcons._MOOD, Color.BLACK,  () -> { return new ControlAnimationSample(); });
	SideMenuContainer.Item dynScrollContainer = new SideMenuContainer.Item("Dynamic ScrollContainer", MaterialIcons._MOOD, Color.BLACK,  () -> { return new DynScrollContainerSample(); });
	SideMenuContainer.Item edits = new SideMenuContainer.Item("Edit", MaterialIcons._TEXT_FORMAT, Color.BLACK,  () -> { return new EditSample(); });
	SideMenuContainer.Item fontSize = new SideMenuContainer.Item("Font sizes", MaterialIcons._PALETTE, Color.BLACK,  () -> { return new FontSample(); });
	SideMenuContainer.Item grid = new SideMenuContainer.Item("Grid", MaterialIcons._GRID_ON, Color.BLACK,  () -> { return new GridSample(); });
	SideMenuContainer.Item html = new SideMenuContainer.Item("HTMLContainer", MaterialIcons._WEB, Color.BLACK,  () -> { return new HTMLContainerSample(); });
	SideMenuContainer.Item imgAnimation = new SideMenuContainer.Item("Image Animation", MaterialIcons._GIF, Color.BLACK,  () -> { return new ImageAnimationSample(); });
	SideMenuContainer.Item imgBook = new SideMenuContainer.Item("Image Book", MaterialIcons._BOOK, Color.BLACK,  () -> { return new ImageBookSample(); });
	SideMenuContainer.Item imgControl = new SideMenuContainer.Item("Image Control", MaterialIcons._IMAGE_ASPECT_RATIO, Color.BLACK,  () -> { return new ImageControlSample(); });
	SideMenuContainer.Item imgModifier = new SideMenuContainer.Item("Image Modifiers", MaterialIcons._IMAGE, Color.BLACK,  () -> { return new ImageModifiersSample(); });
	SideMenuContainer.Item listContainer = new SideMenuContainer.Item("List Container", MaterialIcons._VIEW_LIST, Color.BLACK,  () -> { return new ListContainerSample(); });	
	SideMenuContainer.Item materialicons = new SideMenuContainer.Item("Material Icons", MaterialIcons._FONT_DOWNLOAD, Color.BLACK,  () -> { return new MaterialIconsSample(); });
	SideMenuContainer.Item messageBox = new SideMenuContainer.Item("MessageBox", MaterialIcons._QUESTION_ANSWER, Color.BLACK,  () -> { return new MessageBoxSample(); });
	SideMenuContainer.Item multitouch = new SideMenuContainer.Item("Multi touch", MaterialIcons._TOUCH_APP, Color.BLACK,  () -> { return new MultiTouchSample(); });
	SideMenuContainer.Item notifications = new SideMenuContainer.Item("Notifications", MaterialIcons._NOTIFICATIONS_ACTIVE, Color.BLACK,  () -> { return new NotificationsSample(); });
	SideMenuContainer.Item progressBox = new SideMenuContainer.Item("ProgressBox", MaterialIcons._INDETERMINATE_CHECK_BOX, Color.BLACK,  () -> { return new ProgressBoxSample(); });
	SideMenuContainer.Item slider = new SideMenuContainer.Item("Slider", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new SliderSample(); });	
	SideMenuContainer.Item spinnerInsideLoop = new SideMenuContainer.Item("Spinner Inside Loop", MaterialIcons._3D_ROTATION , Color.BLACK,  () -> { return new SpinnerSample(); });	
	SideMenuContainer.Item switchSample = new SideMenuContainer.Item("Switch", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new SwitchSample(); });	
	
	//Crypto
	SideMenuContainer.Item cipher = new SideMenuContainer.Item("Cipher", MaterialIcons._INDETERMINATE_CHECK_BOX, Color.BLACK,  () -> { return new CipherSample(); });
	SideMenuContainer.Item digest = new SideMenuContainer.Item("Digest", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new DigestSample(); });	
	//SQL
	SideMenuContainer.Item sqlite = new SideMenuContainer.Item("SQLite / Grid", MaterialIcons._STORAGE, Color.BLACK,  () -> { return new SQLiteFormGridTabbedContainer(); });
	//XML
	SideMenuContainer.Item xml = new SideMenuContainer.Item("XML", MaterialIcons._CODE, Color.BLACK, () -> { return new XMLParseSample(); });
	
    SideMenuContainer.Sub uiGroup = new SideMenuContainer.Sub("User Interface", accordion, alignedLabels, buttons, camera, cards, chart, checkRadio, colorPicker, comboList,
    		controlAnimation, dynScrollContainer, edits, fontSize, grid, html, imgAnimation, imgBook, imgControl, imgModifier, listContainer, login, materialicons, messageBox, 
    		multitouch, notifications, progressBox,  slider, spinnerInsideLoop, switchSample);
    
    SideMenuContainer.Sub sqlGroup = new SideMenuContainer.Sub("SQL", sqlite);
    SideMenuContainer.Sub cryptoGroup = new SideMenuContainer.Sub("Crypto", cipher, digest);
    SideMenuContainer.Sub xmlGroup = new SideMenuContainer.Sub("XML", xml);
	
    sideMenu =
        new SideMenuContainer(
            null,
            home,
            uiGroup,
            sqlGroup,
            xmlGroup,
            cryptoGroup);

    sideMenu.topMenu.header =
        new Container() {
          @Override
          public void initUI() {

            try {
              setBackColor(Colors.REDDESIGN);

              Label title = new Label("Showcase", LEFT, Color.WHITE, false);
              title.setFont(Font.getFont("Lato Bold", false, this.getFont().size + 5));
              title.setForeColor(Color.WHITE);
              add(title, LEFT + 45, BOTTOM - 30, FILL, DP + 56);

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