package totalcross.sample.components.cards;

import totalcross.io.IOException;
import totalcross.sample.util.Images;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

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

    try {

      setBackColor(0xFFFFFF);
      setBorderStyle(BORDER_SIMPLE);
      borderColor = 0xFFFFFF;

      cont =
          new Container() {
            @Override
            public void initUI() {
              setBackColor(0x215968);

              try {
                imgProfile =
                    new ImageControl(
                        new Image("images/profile.png")
                            .hwScaledFixedAspectRatio((int) (Settings.screenDensity * 56), true));
                imgProfile.centerImage = true;
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (ImageException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }

              lbHour = new Label("12:00");
              lbHour.setFont(Font.getFont("Lato Medium", false, lbHour.getFont().getSize() + 20));
              lbHour.setForeColor(Color.WHITE);

              lbDate = new Label("13 April 2018");
              lbDate.setFont(Font.getFont("Lato Medium", false, lbDate.getFont().getSize() + 8));
              lbDate.setForeColor(Color.WHITE);

              lbDay = new Label("Saturday");
              lbDay.setFont(Font.getFont("Lato Medium", false, lbDay.getFont().getSize() + 8));
              lbDay.setForeColor(Color.WHITE);

              add(imgProfile, LEFT + 50, CENTER, (int) (Settings.screenDensity * 64), DP + 64);
              add(lbHour, AFTER + 50, CENTER, PREFERRED, PREFERRED);
              add(lbDay, AFTER + 50, TOP + 10, PREFERRED, PREFERRED);
              add(lbDate, AFTER + 50, BOTTOM - 10, PREFERRED, PREFERRED, lbHour);
            }
          };
      add(cont, LEFT, TOP, PARENTSIZE, PARENTSIZE + 40);

      cont2 = new Container();
      cont2.setBackColor(0xD3D3D3);

      add(cont2, LEFT, AFTER, PARENTSIZE, PARENTSIZE + 30);

      lbInboxValue = new Label("8");
      lbInboxValue.setFont(Font.getFont("Lato Medium", false, lbInboxValue.getFont().getSize() + 5));
      lbInboxValue.setForeColor(0x215968);

      lbInbox = new Label("Inbox");
      lbInbox.setFont(Font.getFont("Lato Medium", false, lbInbox.getFont().getSize()));
      lbInbox.setForeColor(0x215968);

      lbDraftValue = new Label("0");
      lbDraftValue.setFont(Font.getFont("Lato Medium", false, lbDraftValue.getFont().getSize() + 5));
      lbDraftValue.setForeColor(0x215968);

      lbDraft = new Label("Draft");
      lbDraft.setFont(Font.getFont("Lato Medium", false, lbDraft.getFont().getSize()));
      lbDraft.setForeColor(0x215968);

      lbSpamValue = new Label("1");
      lbSpamValue.setFont(Font.getFont("Lato Medium", false, lbSpamValue.getFont().getSize() + 5));
      lbSpamValue.setForeColor(0x215968);

      lbSpam = new Label("Spam");
      lbSpam.setFont(Font.getFont("Lato Medium", false, lbSpam.getFont().getSize()));
      lbSpam.setForeColor(0x215968);

      cont2.add(lbDraftValue, CENTER, TOP, PREFERRED, PREFERRED);
      cont2.add(lbInboxValue, BEFORE - fmH * 18, TOP, PREFERRED, PREFERRED);
      cont2.add(lbSpamValue, AFTER + fmH * 18, TOP, PREFERRED, PREFERRED, lbDraftValue);
      cont2.add(lbInbox, CENTER_OF, AFTER, PREFERRED, PREFERRED, lbInboxValue);
      cont2.add(lbDraft, CENTER_OF, AFTER, PREFERRED, PREFERRED, lbDraftValue);
      cont2.add(lbSpam, CENTER_OF, AFTER, PREFERRED, PREFERRED, lbSpamValue);

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
              add(btAttach, CENTER, CENTER, DP + 80, DP + 40);
              add(
                  new Container() {
                    @Override
                    public void initUI() {
                      add(btSend, CENTER, CENTER, DP + 80, DP + 40);
                    }
                  },
                  LEFT,
                  TOP,
                  FIT,
                  FILL);
              add(
                  new Container() {
                    @Override
                    public void initUI() {
                      add(btOthers, CENTER, CENTER, DP + 80, DP + 40);
                    }
                  },
                  AFTER,
                  TOP,
                  FILL,
                  FILL,
                  btAttach);
            }
          },
          LEFT,
          AFTER,
          FILL,
          FILL);
    } catch (ImageException | IOException e) {
      e.printStackTrace();
    }
  }
}
