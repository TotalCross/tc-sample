package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sample.util.SQLiteManager;
import totalcross.sample.util.User;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

public class FormSample extends ScrollContainer {

	private Edit nameEdit;
	private Edit phoneEdit;
	private Edit emailEdit;
	private Edit passwordEdit;
	private Label status = new Label();
	private Button saveButton;

	private final int H = 225;
	private final int GAP = 50;
	private int focusColor = 0xF0F8FF;
	private int numUsers = 0;
	
	@Override
	public void reposition() {
		super.reposition();
		if(Settings.isLandscape()) {
			remove(status);
			remove(saveButton);
			
			add(status, LEFT + GAP, AFTER + GAP*2, FILL - GAP, PREFERRED, passwordEdit);
			add(saveButton, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED+H, status);
		}else {
			remove(status);
			remove(saveButton);

			add(saveButton, LEFT + GAP, BOTTOM - GAP, FILL - GAP,PREFERRED+H, this);
		    add(status, LEFT + GAP, BEFORE, FILL - GAP, PREFERRED, saveButton);
		}
	}
	
	@Override
	public void initUI()
	{	
		super.initUI();
		setScrollBars(false, true);
		try
	    {
	      Settings.is24Hour = true;
		  
		  nameEdit = new Edit();
		  nameEdit.caption = "Name";
		  nameEdit.focusColor = focusColor;
		  nameEdit.setBackForeColors(Colors.P_600, Colors.ON_P_500);
		  
	      phoneEdit = new Edit("(99)99999-9999");
	      phoneEdit.setValidChars(Edit.numbersSet);
	      phoneEdit.caption = "Phone";
	      phoneEdit.setMode(Edit.NORMAL,true);
	      phoneEdit.setBackForeColors(Colors.P_600, Colors.ON_P_500);
	      
	      emailEdit = new Edit();
		  emailEdit.caption = "Email";
		  emailEdit.focusColor = focusColor;
		  emailEdit.setBackForeColors(Colors.P_600, Colors.ON_P_500);
	      
	      passwordEdit = new Edit("");
	      passwordEdit.caption = "Password";
	      passwordEdit.setMode(Edit.PASSWORD); 
	      passwordEdit.setBackForeColors(Colors.P_600, Colors.ON_P_500);
	      
	      status.setFont(Font.getFont(true, 18));
	      
	      saveButton = new Button("Save");
	      saveButton.setBackForeColors(Colors.P_600, Colors.ON_P_600);
	      saveButton.setForeColor(Color.WHITE);

	      add(nameEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED);
	      add(phoneEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED);
	      add(emailEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED);
	      add(passwordEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED);
	      add(saveButton, LEFT + GAP, BOTTOM - GAP, FILL - GAP,PREFERRED+120);
	      add(status, LEFT + GAP, BEFORE, FILL - GAP, PREFERRED, saveButton);
	      
	      saveButton.addPressListener(new PressListener() {
		        @Override
		        public void controlPressed(ControlEvent e)
		        {
		        	if(!"".equals(nameEdit.getText()) && !"".equals(phoneEdit.getText()) && !"".equals(emailEdit.getText()) && !"".equals(passwordEdit.getText())) {
		        		User user = new User(nameEdit.getText(), phoneEdit.getText(), emailEdit.getText(), passwordEdit.getText());
		        		Boolean success = SQLiteManager.getInstance().insertUsers(user);
		        		
		        		if (success) {
		        			++numUsers;
		        			updateStatus();
		        			MessageBox mb = new MessageBox("Message" , "User successfully saved.", new String[]{"Close"});
				        	mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
				        	mb.popup();
		        			
		        		} else {
		        			
		        			MessageBox mb = new MessageBox("Message" , "Error saving user.", new String[]{"Close"});
		        			mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
				        	mb.popup();
		        			
		        		}
		        		
		        	}else {
		        		MessageBox mb = new MessageBox("Message" , "Complete all fields.", new String[]{"Close"});
		        		mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
			        	mb.popup();
		        	}
		        }
		    });
	      
	    }
	    catch (Exception ee)
	    {
	      MessageBox.showException(ee,true);
	    }
	}
	
	public void updateStatus() {
		this.status.setText(numUsers + " user(s) registred");
	}
	
	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
	}
}
