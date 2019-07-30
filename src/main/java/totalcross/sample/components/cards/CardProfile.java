package totalcross.sample.components.cards;

import totalcross.io.IOException;
import totalcross.sample.util.Images;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.util.UnitsConverter;

public class CardProfile extends Container {

  private Container cont;
  private ImageControl imgProfile;
  private Label lbHour;
  private Label lbDate;
  private Label lbDay;
  private Container cont2;
  private Label lbInbox;
  private Label lbDraft;
  private Label lbSpam;
  private Label lbInboxValue;
  private Label lbDraftValue;
  private Label lbSpamValue;
  private Button btSend;
  private Button btAttach;
  private Button btOthers;

  public void initUI() {
    int gap = UnitsConverter.toPixels(DP + 4);
    final int margin = UnitsConverter.toPixels(DP + 8);
    try {

      setBackColor(0xFFFFFF);
      setBorderStyle(BORDER_SIMPLE);
      borderColor = 0xFFFFFF;

      cont =
          new Container() {
            @Override
            public void initUI() {
              setBackColor(0x215968);
              setInsets(margin, margin, margin, margin);
              int imageSize = UnitsConverter.toPixels(DP + 64);
              try {
                imgProfile =
                    new ImageControl(
                        new Image("images/profile.png")
                            .hwScaledFixedAspectRatio(imageSize, true));
                imgProfile.centerImage = true;
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (ImageException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }

              lbHour = new Label("12:00");
              lbHour.setFont(Font.getFont("Lato Medium", false, lbHour.getFont().size + 20));
              lbHour.setForeColor(Color.WHITE);

              lbDate = new Label("13 April 2018");
              lbDate.setFont(Font.getFont("Lato Medium", false, lbDate.getFont().size + 8));
              lbDate.setForeColor(Color.WHITE);

              lbDay = new Label("Saturday");
              lbDay.setFont(Font.getFont("Lato Medium", false, lbDay.getFont().size + 8));
              lbDay.setForeColor(Color.WHITE);

              add(lbHour, RIGHT, TOP);
              add(lbDay, RIGHT, AFTER + UnitsConverter.toPixels(DP + 4));
              add(lbDate, RIGHT, AFTER + UnitsConverter.toPixels(DP + 4 ));
              add(imgProfile, LEFT, TOP);
              resizeHeight();

            }
          };
      add(cont, LEFT, TOP, FILL, PREFERRED);
      cont2 = new Container();
      cont2.setBackColor(0xD3D3D3);

      add(cont2, LEFT, AFTER, PARENTSIZE, PREFERRED);

      lbInboxValue = new Label("8");
      lbInboxValue.setFont(Font.getFont("Lato Medium", false, lbInboxValue.getFont().size + 5));
      lbInboxValue.setForeColor(0x215968);

      lbInbox = new Label("Inbox");
      lbInbox.setFont(Font.getFont("Lato Medium", false, lbInbox.getFont().size));
      lbInbox.setForeColor(0x215968);

      lbDraftValue = new Label("0");
      lbDraftValue.setFont(Font.getFont("Lato Medium", false, lbDraftValue.getFont().size + 5));
      lbDraftValue.setForeColor(0x215968);

      lbDraft = new Label("Draft");
      lbDraft.setFont(Font.getFont("Lato Medium", false, lbDraft.getFont().size));
      lbDraft.setForeColor(0x215968);

      lbSpamValue = new Label("1");
      lbSpamValue.setFont(Font.getFont("Lato Medium", false, lbSpamValue.getFont().size + 5));
      lbSpamValue.setForeColor(0x215968);

      lbSpam = new Label("Spam");
      lbSpam.setFont(Font.getFont("Lato Medium", false, lbSpam.getFont().size));
      lbSpam.setForeColor(0x215968);

      cont2.add(lbDraftValue, CENTER, TOP);
      cont2.add(lbInboxValue, BEFORE - lbInbox.getPreferredWidth() - gap, TOP, PREFERRED, PREFERRED, lbDraftValue);
      cont2.add(lbSpamValue, AFTER + lbSpam.getPreferredWidth() + gap, TOP, PREFERRED, PREFERRED, lbDraftValue);
      cont2.add(lbInbox, CENTER_OF, AFTER + gap, PREFERRED, PREFERRED, lbInboxValue);
      cont2.add(lbDraft, CENTER_OF, AFTER + gap, PREFERRED, PREFERRED, lbDraftValue);
      cont2.add(lbSpam, CENTER_OF, AFTER + gap, PREFERRED, PREFERRED, lbSpamValue);
      cont2.resizeHeight();
      btSend = new Button(Images.aplyColor(new Image("images/send.png"), 0xffffff));
      btSend.setBackColor(0xC10828);
      btSend.imageHeightFactor = 50;
      btAttach = new Button(Images.aplyColor(new Image("images/attach.png"), 0xFFFFFF));
      btAttach.setBackColor(0xC10828);
      btAttach.imageHeightFactor = 50;
      btOthers = new Button(Images.aplyColor(new Image("images/settings.png"), 0xFFFFFF));
      btOthers.setBackColor(0xC10828);
      btOthers.imageHeightFactor = 50;

      add(
          new Container() {
            @Override
            public void initUI() {
              setInsets(margin, margin, margin, margin);
              int btnWidth = UnitsConverter.toPixels(DP + 80);
              add(btAttach, CENTER, TOP, btnWidth, DP + 40);
              reposition();
              add(btSend, LEFT  + (btAttach.getX() - btnWidth)/2, TOP, btnWidth, DP + 40, btAttach);
              add(btOthers, RIGHT - (btAttach.getX() - btnWidth)/2, TOP,  btnWidth, DP + 40, btAttach);
              reposition();
              resizeHeight();
            }
          },
          LEFT,
          AFTER,
          FILL,
          PREFERRED);
      reposition();
      resizeHeight();
    } catch (ImageException | IOException e) {
      e.printStackTrace();
    }
  }
}
