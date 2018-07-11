package totalcross.sample.components.UI;

import totalcross.sample.util.Colors;
import totalcross.sample.util.SQLiteManager;
import totalcross.sample.util.User;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class FormSample extends Container {

	private ScrollContainer scForm;
	private Edit nameEdit;
	private Edit phoneEdit;
	private Edit emailEdit;
	private Edit passwordEdit;
	private Button saveButton;

	private final int H = 225;
	private int GAP = 50;
	private int focusColor = 0xF0F8FF;
	
	@Override
	public void initUI()
	{	
		super.initUI();
		
		try
	    {
	      Settings.is24Hour = true;
	      
	      scForm = new ScrollContainer(false, true);
	      add(scForm, LEFT, AFTER, FILL, FILL);
		  
		  nameEdit = new Edit();
		  nameEdit.caption = "Name";
		  nameEdit.focusColor = focusColor;
		  nameEdit.setBackColor(Colors.P_DARK);
		  
	      phoneEdit = new Edit("(99)99999-9999");
	      phoneEdit.setValidChars(Edit.numbersSet);
	      phoneEdit.caption = "Phone";
	      phoneEdit.setMode(Edit.NORMAL,true);
	      phoneEdit.setBackColor(Colors.P_DARK);
	      
	      emailEdit = new Edit();
		  emailEdit.caption = "Email";
		  emailEdit.focusColor = focusColor;
		  emailEdit.setBackColor(Colors.P_DARK);
	      
	      passwordEdit = new Edit("");
	      passwordEdit.caption = "Password";
	      passwordEdit.setMode(Edit.PASSWORD); 
	      passwordEdit.setBackColor(Colors.P_DARK);
	      
	      saveButton = new Button("Save");
	      saveButton.setBackColor(Colors.P_DARK);
	      saveButton.setForeColor(Color.WHITE);

	      scForm.add(nameEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED);
	      scForm.add(phoneEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED);
	      scForm.add(emailEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED);
	      scForm.add(passwordEdit, LEFT + GAP, AFTER + GAP, FILL - GAP,PREFERRED);
	      scForm.add(saveButton, LEFT + GAP, BOTTOM - GAP, FILL - GAP,PREFERRED+H);
	           
	      saveButton.addPressListener(new PressListener() {
		        @Override
		        public void controlPressed(ControlEvent e)
		        {
		        	if(!"".equals(nameEdit.getText()) && !"".equals(phoneEdit.getText()) && !"".equals(emailEdit.getText()) && !"".equals(passwordEdit.getText())) {
		        		
		        		User user = new User(nameEdit.getText(), phoneEdit.getText(), emailEdit.getText(), passwordEdit.getText());
		        		
		        		Boolean success = SQLiteManager.getInstance().insertUsers(user);
		        		
		        		if (success) {
		        			
		        			MessageBox mb = new MessageBox("Message" , "User successfully saved.", new String[]{"Close"});
				        	mb.setBackColor(Colors.BACKGROUND);
				        	mb.setForeColor(Colors.FOREGROUND);
				        	mb.popup();
		        			
		        		} else {
		        			
		        			MessageBox mb = new MessageBox("Message" , "Error saving user.", new String[]{"Close"});
				        	mb.setBackColor(Colors.BACKGROUND);
				        	mb.setForeColor(Colors.FOREGROUND);
				        	mb.popup();
		        			
		        		}
		        		
		        	}else {
		        		MessageBox mb = new MessageBox("Message" , "Complete all fields.", new String[]{"Close"});
			        	mb.setBackColor(Colors.BACKGROUND);
			        	mb.setForeColor(Colors.FOREGROUND);
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
	
	
}
