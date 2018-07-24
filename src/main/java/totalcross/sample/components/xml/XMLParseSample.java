package totalcross.sample.components.xml;

import java.util.ArrayList;
import totalcross.sample.util.Colors;
import totalcross.sample.util.User;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Grid;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.xml.AttributeList;
import totalcross.xml.ContentHandler;
import totalcross.xml.SyntaxException;
import totalcross.xml.XmlReader;

public class XMLParseSample extends ScrollContainer{
	
	private Grid grid;
	private Label status;
	private Button readXmlButton;
	private int gap = 50;
	
	@Override
	public void initUI()
	{
		super.initUI();

		try {
			
			String []gridCaptions = {"Name", "Phone", "Email" };
			int gridWidths[] = {-35, -35, -30};
		    int gridAligns[] = {LEFT,LEFT,LEFT};

		    grid = new Grid(gridCaptions, gridWidths, gridAligns, false);
		    grid.verticalLineStyle = Grid.VERT_LINE;
		    grid.secondStripeColor = Color.getRGB(245, 245, 245);
		    readXmlButton = new Button("Read XML");
		    readXmlButton.setBackColor(Colors.P_DARK);
		    readXmlButton.setForeColor(Color.WHITE);
		    status = new Label("a", CENTER);
		    status.setForeColor(Colors.RED);
		    status.setFont(Font.getFont(16));
		    
		    add(grid, LEFT + gap, TOP + gap, FILL - gap, SCREENSIZE + 65);
		    add(status, LEFT + gap, AFTER, FILL - gap, PREFERRED, grid);
		    add(readXmlButton, LEFT + gap, AFTER, FILL - gap, FILL - gap, status);
		    
			readXmlButton.addPressListener(new PressListener() {
		        @Override
		        public void controlPressed(ControlEvent e)
		        {
		        	readXml();
		        }
		    });
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void readXml() {
		try {
			UserHandler userHandler = new UserHandler();
			XmlReader rdr = new XmlReader();
			rdr.setContentHandler(userHandler);
			
			byte[] xml = Vm.getFile("user.xml");
			
			if (xml != null) {
				
				rdr.parse(xml, 0, xml.length);
				
				ArrayList<User> users = userHandler.getUsers();
				
				if (users.size() > 0) {
		        	
		        	String items[][] = new String[users.size()][3];
		        	
		        	for (int i = 0; i < users.size(); i++) {
		        		
		        		User user = users.get(i);
		        		
		        		items[i] =  new String[] {user.getName(), user.getPhone(), user.getMail()};
		        		
		        	}

		            grid.setItems(items);
		            status.setText("Great! It was found " + users.size() + " users on user.xml file!");
	            } else {
	            	
	            	MessageBox mb = new MessageBox("Message" , "No registered users.", new String[]{"Close"});
		        	mb.setBackColor(Colors.BACKGROUND);
		        	mb.setForeColor(Colors.FOREGROUND);
		        	mb.popup();
	            }
			} else {
            	
            	MessageBox mb = new MessageBox("Message" , "XML not found.", new String[]{"Close"});
	        	mb.setBackColor(Colors.BACKGROUND);
	        	mb.setForeColor(Colors.FOREGROUND);
	        	mb.popup();
            }
			
		} catch (SyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private 
	
	class UserHandler extends ContentHandler {

		private ArrayList<User> users = new ArrayList<>();
		private int count = 0;
		private User user = null;
		
		@Override
		public void characters(String arg0) {
			if(!" ".equals(arg0)) {
				addUser(arg0);
			}
		}

		@Override
		public void endElement(int arg0) {
		}

		@Override
		public void startElement(int arg0, AttributeList arg1) {
		}
		
		public ArrayList<User> getUsers(){
			return users;
		}
		
		private void addUser(String field) {
			if (count == 0) {
				user = new User();
				user.setName(field);
				count++;
			} else if (count == 1){
				user.setPhone(field);
				count++;
			} else if (count == 2){
				user.setMail(field);
				count++;
			} else if (count == 3){
				user.setPassword(field);
				count = 0;
				users.add(user);
			}
		}
    }

}
