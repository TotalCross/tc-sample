package totalcross.sample.main;

import totalcross.io.IOException;
import totalcross.sample.components.Home;
import totalcross.sample.components.XMLParseSample;
import totalcross.sample.components.cards.CardsSample;
import totalcross.sample.components.crypto.CipherSample;
import totalcross.sample.components.crypto.DigestSample;
import totalcross.sample.components.crypto.SignatureSample;
import totalcross.sample.components.io.FileSample;
import totalcross.sample.components.json.JSONSample;
import totalcross.sample.components.lang.reflection.ReflectionSample;
import totalcross.sample.components.lang.thread.ThreadSample;
import totalcross.sample.components.map.GoogleMapsSample;
import totalcross.sample.components.net.FTPSample;
import totalcross.sample.components.net.SecureSocketSample;
import totalcross.sample.components.net.ServerSocketSample;
import totalcross.sample.components.net.SocketSample;
import totalcross.sample.components.phone.PhoneDialerSample;
import totalcross.sample.components.phone.PhoneSmsSample;
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
import totalcross.sample.components.ui.HWSignatureSample;
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
import totalcross.sample.components.ui.OtherControlsSample;
import totalcross.sample.components.ui.ProgressBoxSample;
import totalcross.sample.components.ui.SliderSample;
import totalcross.sample.components.ui.SpinnerSample;
import totalcross.sample.components.ui.SwitchSample;
import totalcross.sample.components.ui.TabbedContainerSample;
import totalcross.sample.components.ui.TopMenuSample;
import totalcross.sample.components.ui.VelocimeterSample;
import totalcross.sample.components.util.PDFWriterSample;
import totalcross.sample.components.util.ZLibSample;
import totalcross.sample.components.util.ZipSample;
import totalcross.sample.components.xml.SoapSample;
import totalcross.sample.net.mail.MailSample;
import totalcross.sample.util.Colors;
import totalcross.sample.components.sql.SQLiteBenchSample;
import totalcross.sample.components.sql.SQLiteFormGridTabbedContainer;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.SideMenuContainer;
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
	SideMenuContainer.Item otherControls = new SideMenuContainer.Item("Other Controls", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new OtherControlsSample(); });	
	SideMenuContainer.Item progressBox = new SideMenuContainer.Item("ProgressBox", MaterialIcons._INDETERMINATE_CHECK_BOX, Color.BLACK,  () -> { return new ProgressBoxSample(); });
	SideMenuContainer.Item signatureHW = new SideMenuContainer.Item("HandWrite Signature", MaterialIcons._DASHBOARD, Color.BLACK,  () -> { return new HWSignatureSample(); });	
	SideMenuContainer.Item slider = new SideMenuContainer.Item("Slider", MaterialIcons._POWER_SETTINGS_NEW, Color.BLACK,  () -> { return new SliderSample(); });	
	SideMenuContainer.Item spinnerInsideLoop = new SideMenuContainer.Item("Spinner Inside Loop", MaterialIcons._LOOP , Color.BLACK,  () -> { return new SpinnerSample(); });	
	SideMenuContainer.Item switchSample = new SideMenuContainer.Item("Switch", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new SwitchSample(); });	
	SideMenuContainer.Item tabbedContainer = new SideMenuContainer.Item("Tabbed Container", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new TabbedContainerSample(); });	
	SideMenuContainer.Item topMenu = new SideMenuContainer.Item("TopMenu", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new TopMenuSample(); });
	SideMenuContainer.Item velocimeter = new SideMenuContainer.Item("Velocimeter", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new VelocimeterSample(); });
	//Crypto
	SideMenuContainer.Item cipher = new SideMenuContainer.Item("Cipher", MaterialIcons._INDETERMINATE_CHECK_BOX, Color.BLACK,  () -> { return new CipherSample(); });
	SideMenuContainer.Item digest = new SideMenuContainer.Item("Digest", MaterialIcons._SLIDESHOW, Color.BLACK,  () -> { return new DigestSample(); });
	SideMenuContainer.Item signatureCR = new SideMenuContainer.Item("Signature", MaterialIcons._SETTINGS_ETHERNET, Color.BLACK,  () -> { return new SignatureSample(); });
	//SQL
	SideMenuContainer.Item sqlite = new SideMenuContainer.Item("SQLite / Grid", MaterialIcons._STORAGE, Color.BLACK,  () -> { return new SQLiteFormGridTabbedContainer(); });
	SideMenuContainer.Item SQLiteBench = new SideMenuContainer.Item("SQLite Bench", MaterialIcons._INPUT, Color.BLACK, () -> { return new SQLiteBenchSample(); });
	//XML
	SideMenuContainer.Item xml = new SideMenuContainer.Item("XML", MaterialIcons._CODE, Color.BLACK, () -> { return new XMLParseSample(); });
	//IO
	SideMenuContainer.Item file = new SideMenuContainer.Item("File", MaterialIcons._ATTACH_FILE, Color.BLACK, () -> { return new FileSample(); });
	//JSON
	SideMenuContainer.Item json = new SideMenuContainer.Item("JSON Parser", MaterialIcons._HTTP, Color.BLACK, () ->{ return new JSONSample(); });
	//Lang
	SideMenuContainer.Item thread = new SideMenuContainer.Item("Thread", MaterialIcons._LINE_STYLE, Color.BLACK, () -> { return new ThreadSample(); });
	SideMenuContainer.Item reflection = new SideMenuContainer.Item("Reflection", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new ReflectionSample(); });
	//Map
	SideMenuContainer.Item googleMaps = new SideMenuContainer.Item("Google Maps", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new GoogleMapsSample(); });
	//net
	SideMenuContainer.Item ftpSample = new SideMenuContainer.Item("FTP ", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new FTPSample(); });
	SideMenuContainer.Item SecureSocketSample = new SideMenuContainer.Item("Secure Socket", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new SecureSocketSample(); });
	SideMenuContainer.Item ServerSocketSample = new SideMenuContainer.Item("Server Socket", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new ServerSocketSample(); });
	SideMenuContainer.Item SocketSample = new SideMenuContainer.Item("Socket", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new SocketSample(); });
	
	
	//Phone
	SideMenuContainer.Item phoneDialer = new SideMenuContainer.Item("Dialer", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new PhoneDialerSample(); });
	SideMenuContainer.Item phoneSms = new SideMenuContainer.Item("SMS", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new PhoneSmsSample(); });
	//util
	SideMenuContainer.Item pdfWriter = new SideMenuContainer.Item("PDF Writer", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new PDFWriterSample(); });
	SideMenuContainer.Item zipSample = new SideMenuContainer.Item("Zip", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new ZipSample(); });
	SideMenuContainer.Item zLibSample = new SideMenuContainer.Item("ZLib", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new ZLibSample(); });
	SideMenuContainer.Item mail = new SideMenuContainer.Item("Mail", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new MailSample(); });
	
	//xml
	SideMenuContainer.Item soap = new SideMenuContainer.Item("Soap", MaterialIcons._ART_TRACK, Color.BLACK, () -> { return new SoapSample(); });
	
	
	
	
	SideMenuContainer.Sub uiGroup = new SideMenuContainer.Sub("User Interface", accordion, alignedLabels, buttons, camera, cards, chart, checkRadio, colorPicker, comboList,
    		controlAnimation, dynScrollContainer, edits, fontSize, grid, html, imgAnimation, imgBook, imgControl, imgModifier, listContainer, login, materialicons, messageBox, 
    		multitouch, notifications, progressBox, signatureHW, slider, spinnerInsideLoop, switchSample, tabbedContainer, topMenu, velocimeter, otherControls);
    
    SideMenuContainer.Sub sqlGroup = new SideMenuContainer.Sub("SQL", sqlite, SQLiteBench);
    SideMenuContainer.Sub cryptoGroup = new SideMenuContainer.Sub("Crypto", cipher, digest, signatureCR);
    //SideMenuContainer.Sub xmlGroup = new SideMenuContainer.Sub("XML", xml);
    SideMenuContainer.Sub ioGroup = new SideMenuContainer.Sub("IO", file);
    SideMenuContainer.Sub jsonGroup = new SideMenuContainer.Sub("JSON", json);
    SideMenuContainer.Sub langGroup = new SideMenuContainer.Sub("Lang", thread, reflection);
	SideMenuContainer.Sub mapGroup = new SideMenuContainer.Sub("Map", googleMaps);
	SideMenuContainer.Sub netGroup = new SideMenuContainer.Sub("net", mail,ftpSample, SecureSocketSample, ServerSocketSample, SocketSample);
	SideMenuContainer.Sub phoneGroup = new SideMenuContainer.Sub("Phone", phoneDialer, phoneSms);
	SideMenuContainer.Sub utilGroup = new SideMenuContainer.Sub("Util", pdfWriter, zipSample, zLibSample);
	SideMenuContainer.Sub xmlGroup = new SideMenuContainer.Sub("XML", soap);
	
    sideMenu =
        new SideMenuContainer(
            null,
            home,
            uiGroup,
            sqlGroup,
           // xmlGroup,
            cryptoGroup,
            ioGroup,
            jsonGroup,
            langGroup,
            mapGroup,
            netGroup,
            phoneGroup,
            utilGroup,
            xmlGroup);

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