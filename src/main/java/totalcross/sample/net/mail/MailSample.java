
package totalcross.sample.net.mail;

import totalcross.net.AuthenticationException;
import totalcross.net.mail.Folder;
import totalcross.net.mail.MailSession;
import totalcross.net.mail.Message;
import totalcross.net.mail.MessagingException;
import totalcross.net.mail.Store;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Grid;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;

/**
Although not implemented here, here's now to send email to a GMAIL account:
<pre>
   Message m = new Message();
   m.addFrom(new Address[] { new Address("me@gmail.com", "Me") });
   m.addRecipient(RecipientType.TO, new Address("you@gmail.com", "You"));
   m.subject = "me and you";
   m.setText("we're good friends");

   MailSession s = MailSession.getDefaultInstance();
   s.put(MailSession.SMTP_HOST, new Properties.Str("smtp.gmail.com"));
   s.put(MailSession.SMTP_USER, new Properties.Str("postmaster@gmail.com"));
   s.put(MailSession.SMTP_PASS, new Properties.Str("a good password!));
   s.put(MailSession.SMTP_PORT, new Properties.Int(587));
   s.put(MailSession.SMTP_AUTH, new Properties.Boolean(true));
   s.put(MailSession.SMTP_STARTTLS, new Properties.Boolean(true));
   SMTPSSLTransport.send(m, s);
</pre>
If you receive error 534, see:
https://accounts.google.com/b/0/DisplayUnlockCaptcha

 */

public class MailSample extends Container {
  MessageContainer messagePanel;

  Label lblInbox;
  Label lblPage;
  Button btDelete;
  Button btReset;
  Button btOpen;
  Button btConnect;
  Button btConfig;
  Grid inbox;

  Store store;
  Folder folder;

  MailboxDataSource mds;

  @Override
  public void initUI() {
    super.initUI();
    this.setBackColor(Color.getRGB(74, 144, 226));
    add(btConfig = new Button("Configuration"), LEFT + ((Settings.screenWidth/100)*8), TOP,(Settings.screenWidth/10)*4, 25);
    btConfig.setBackColor(Color.getRGB(69, 131, 212));
    btConfig.setForeColor(Color.WHITE);
    add(lblPage = new Label("Inbox", Control.CENTER), CENTER, TOP + ((Settings.screenWidth/100)*8));
    lblPage.setForeColor(Color.WHITE);
    add(btConnect = new Button("Synchronize"), RIGHT - ((Settings.screenWidth/100)*8), TOP, (Settings.screenWidth/10)*4, 25);
    btConnect.setBackColor(Color.getRGB(69, 131, 212));
    btConnect.setForeColor(Color.WHITE);
    add(btReset = new Button("Reset"), LEFT + ((Settings.screenWidth/100)*8) ,BOTTOM-(Settings.screenHeight/10), (Settings.screenWidth/10)*4, 25);
    btReset.setBackColor(Color.getRGB(69, 131, 212));
    btReset.setForeColor(Color.WHITE);
    add(btOpen = new Button("Open"), RIGHT -((Settings.screenWidth/100)*8), BOTTOM-(Settings.screenHeight/10), (Settings.screenWidth/10)*4, 25);
    btOpen.setBackColor(Color.getRGB(69, 131, 212));
    btOpen.setForeColor(Color.WHITE);
    Grid.useHorizontalScrollBar = true;
    inbox = new Grid(new String[] { "Email","Subject" }, new int[] { Settings.screenWidth/2, Settings.screenWidth/2 }, new int[] { LEFT, LEFT }, false);
    inbox.firstStripeColor = totalcross.ui.gfx.Color.getRGB(255, 255, 255);
    inbox.secondStripeColor = totalcross.ui.gfx.Color.getRGB(255, 255, 255);
    inbox.highlightColor = totalcross.ui.gfx.Color.getRGB(224, 224, 224);
    inbox.verticalLineStyle = Grid.VERT_LINE;
    add(inbox, LEFT, AFTER+Settings.screenHeight/10, FILL, FIT, lblPage);

  }

  Message[] msgs;

  @Override
  public void onEvent(Event event) {
    try {
      if (event.target == btReset && event.type == ControlEvent.PRESSED) {
        inbox.removeAllElements();
        folder.reset();
        inbox.setDataSource(mds, folder.getMessageCount());
      } else if (event.target == btOpen && event.type == ControlEvent.PRESSED) {
        int selectedIndex = inbox.getSelectedIndex();
        if (selectedIndex != -1) {
          String[] item = inbox.getSelectedItem();
          showMessage(folder.getMessage(item[1]));
        }
      } else if (event.target == btConnect && event.type == ControlEvent.PRESSED) {
        connect();
      } else if(event.target == btConfig && event.type == ControlEvent.PRESSED) {
    	new ConfigurationContainer().popup();
      } 
    } catch (MessagingException e) {
      MessageBox.showException(e, true);
    }
  }

  private void connect() {
    if (store != null) {
      return;
    }

    try {
      store = MailSession.getDefaultInstance().getStore("pop3");
      store.connect();
      folder = store.getFolder("INBOX");
      folder.open();
      inbox.liveScrolling = true;
      int messageCount = folder.getMessageCount();
      inbox.setDataSource(mds = new MailboxDataSource(folder), messageCount);
      inbox.scrollTo(messageCount);
    } catch (AuthenticationException e) {
      MessageBox.showException(e, true);
    } catch (MessagingException e) {
      MessageBox.showException(e, true);
    }
  }

  private void showMessage(Message message) {
    if (message != null) {
      new MessageContainer(message).popup();
    }
  }

  @Override
  public void onRemove() {
    try {
      if (folder != null) {
        folder.close(false);
      }
      if (store != null) {
        store.close();
      }
    } catch (MessagingException e) {
      MessageBox.showException(e, true);
    }
  }
}
