package totalcross.sample.components.ui;

import totalcross.io.IOException;
//import totalcross.notification.Notification;
//import totalcross.notification.NotificationManager;
import totalcross.notification.Notification;
import totalcross.notification.NotificationManager;
import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.font.Font;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class NotificationsSample extends BaseScreen {
	private ImageControl ic;

	public void onContent(ScrollContainer content){
		try {
			
			Label lbl = new Label("Notify Me !");
            lbl.setFont(Font.getFont("Lato Light", false, lbl.getFont().size + 20));
            lbl.setForeColor(Colors.PRIMARY);
	        content.add(lbl, CENTER, CENTER-400);
			
			ic = new ImageControl(new Image("images/botao.png"));
			ic.scaleToFit = true;
			ic.centerImage = true;
			
			ic.addPenListener(
			new PenListener(){

				@Override
				public void penDown(PenEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void penDrag(DragEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void penDragEnd(DragEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void penDragStart(DragEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void penUp(PenEvent arg0) {
					// TODO Auto-generated method stub
					Notification.Builder builder = new Notification.Builder();
					Notification notification = builder
							.title("Click Me!")
							.text("Tanks for Click Me!! \n Twice")
							.build();
					NotificationManager.getInstance().notify(notification);
				}
				  
			  });

			content.add(ic, LEFT, CENTER-300, FILL, PARENTSIZE+20);
			
		} catch (IOException | ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
