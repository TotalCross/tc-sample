package totalcross.sample.components.ui;

import java.util.ArrayList;

import totalcross.sample.util.Colors;
import totalcross.sample.util.SQLiteManager;
import totalcross.sample.util.User;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Grid;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class GridSample extends Container {
	
	private Grid grid;
	private Button loadButton;
	private int GAP = 50;
	private final int H = 225;
	
	@Override
    public void initUI()
    {
	
      String []gridCaptions = {"Name", "Phone", "Email" };
      int gridWidths[] = {-35, -35, -30};
      int gridAligns[] = {LEFT,LEFT,LEFT};

      grid = new Grid(gridCaptions, gridWidths, gridAligns, false);
      grid.verticalLineStyle = Grid.VERT_LINE;
      grid.secondStripeColor = Color.getRGB(245, 245, 245);
      
      loadButton = new Button("Load");
      loadButton.setBackColor(Colors.P_DARK);
      loadButton.setForeColor(Color.WHITE);

      add(grid, LEFT + GAP, TOP + GAP, FILL - GAP, FILL - GAP*9);
      add(loadButton, LEFT + GAP, BOTTOM - GAP, FILL - GAP,PREFERRED+H);
      
      
      loadButton.addPressListener(new PressListener() {
	        @Override
	        public void controlPressed(ControlEvent e)
	        {
	        	
	        	ArrayList<User> users = SQLiteManager.getInstance().getUsers();
	        	
	        	if (users.size() > 0) {
		        	
		        	String items[][] = new String[users.size()][3];
		        	
		        	for (int i = 0; i < users.size(); i++) {
		        		
		        		User user = users.get(i);
		        		
		        		items[i] =  new String[] {user.getName(), user.getPhone(), user.getMail()};
		        		
		        	}
	
		            grid.setItems(items);
		            
	            } else {
	            	
	            	MessageBox mb = new MessageBox("Message" , "No registered users.", new String[]{"Close"});
		        	mb.setBackColor(Colors.BACKGROUND);
		        	mb.setForeColor(Colors.FOREGROUND);
		        	mb.popup();
	            }
	        }
	    });
    }
}
