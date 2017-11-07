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
import totalcross.sample.components.MessageBoxSample;
import totalcross.sample.util.Colors;
import totalcross.sample.util.Images;
import totalcross.sys.Settings;
import totalcross.ui.MainWindow;
import totalcross.ui.SideMenuContainer;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class TCSample extends MainWindow {

	SideMenuContainer sideMenu;
	
	public TCSample() {
		
		super("TotalCross Sample", NO_BORDER);
		
        setUIStyle(Settings.Material);
        Settings.uiAdjustmentsBasedOnFontHeight = true;
        
        setBackForeColors(Colors.BACKGROUND, Colors.FOREGROUND);
		
	}
	
	@Override
	public void initUI() {
		
		
		try{
			
			SideMenuContainer.Item home = new SideMenuContainer.Item("Home", Images.getImageRed(new Image("images/bt_home.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new Home(); });
			SideMenuContainer.Item buttons = new SideMenuContainer.Item("Button", Images.getImageRed(new Image("images/square-inc.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new ButtonSample(); });
			SideMenuContainer.Item edits = new SideMenuContainer.Item("Edit", Images.getImageRed(new Image("images/format-text.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new EditSample(); });
			SideMenuContainer.Item checkRadio = new SideMenuContainer.Item("Check and Radio", Images.getImageRed(new Image("images/checkbox-marked.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new CheckRadioSample(); });
			SideMenuContainer.Item comboList = new SideMenuContainer.Item("Combo and List", Images.getImageRed(new Image("images/arrow-down-drop-circle.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new ComboListSample(); });
			SideMenuContainer.Item listContainer = new SideMenuContainer.Item("List Container", Images.getImageRed(new Image("images/view-agenda.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new ListContainerSample(); });	
			SideMenuContainer.Item awesomeFont = new SideMenuContainer.Item("Awesome Font", Images.getImageRed(new Image("images/format-font.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new AwesomeFontSample(); });
			SideMenuContainer.Item camera = new SideMenuContainer.Item("Camera", Images.getImageRed(new Image("images/camera.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new CameraSample(); });
			SideMenuContainer.Item messageBox = new SideMenuContainer.Item("MessageBox", Images.getImageRed(new Image("images/message.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new MessageBoxSample(); });
			
			//SideMenuContainer.Item imageBook = new SideMenuContainer.Item("Image Book", Images.getImageRed(new Image("images/file-image.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new ImageBookSample(); });
			//SideMenuContainer.Item chart = new SideMenuContainer.Item("Chart", Images.getImageRed(new Image("images/chart-arc.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new ChartSample(); });
			//SideMenuContainer.Item grid = new SideMenuContainer.Item("Grid", Images.getImageRed(new Image("images/view-grid.png").hwScaledFixedAspectRatio(fmH*2,true)),  () -> { return new GridSample(); });
			
			sideMenu = new SideMenuContainer("Menu",
	        		home,
	        		buttons,
	        		edits,
	        		checkRadio,
	        		comboList,
	        		listContainer,
	        		awesomeFont,
	        		camera,
	        		messageBox
	        );
	        
	        sideMenu.setBackColor(Colors.PRIMARY);
	        sideMenu.setForeColor(Color.BLACK);
	        
			add(sideMenu, LEFT, TOP, FILL, FILL);
		
		} catch (ImageException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
