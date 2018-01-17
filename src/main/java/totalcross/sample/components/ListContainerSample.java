package totalcross.sample.components;

import totalcross.sample.util.Colors;
import totalcross.sample.util.Images;
import totalcross.ui.Container;
import totalcross.ui.ListContainer;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class ListContainerSample extends Container {

	private ScrollContainer sc;
	private ListContainer lcSocialNetworks = new ListContainer();

	private int SC_GAP = fmH/2;
	private int GAP = fmH * 4;

	@Override
	public void initUI() {

		try {
			super.initUI();
			
			sc = new ScrollContainer(false, true);
			sc.setInsets(SC_GAP, SC_GAP, SC_GAP, SC_GAP);
			add(sc,LEFT,TOP,FILL,FILL);

			lcSocialNetworks.getFlick().longestFlick = 15;
			lcSocialNetworks.setBackColor(Colors.BACKGROUND);
			sc.add(lcSocialNetworks, LEFT + GAP, TOP, FILL - GAP, FILL);

			ListContainer.Item facebook = new ListContainer.Item(getLayout(Images.aplyColor(new Image("images/facebook.png"), Colors.BLUE)));
			facebook.items = new String[] { "- ", "Name", "   Facebook", "Like?  ", "" };

			ListContainer.Item twitter = new ListContainer.Item(getLayout(Images.aplyColor(new Image("images/twitter.png"), Colors.PRIMARY)));
			twitter.items = new String[] { "- ", "Name", "   Twitter", "Like?  ", "" };

			ListContainer.Item instagram = new ListContainer.Item(getLayout(Images.aplyColor(new Image("images/instagram.png"), Color.BLACK)));
			instagram.items = new String[] { "- ", "Name", "   Instagram", "Like?  ", "" };

			ListContainer.Item linkedin = new ListContainer.Item(getLayout(Images.aplyColor(new Image("images/linkedin.png"), Colors.BLUE)));
			linkedin.items = new String[] { "-", "Name", "   linkedin", "Like?  ", "" };

			ListContainer.Item googlePlus = new ListContainer.Item(getLayout(Images.aplyColor(new Image("images/google-plus.png"), Colors.RED)));
			googlePlus.items = new String[] { "- ", "Name", "   Google +", "Like?  ", "" };

			Container socialNetworks[] = new Container[5];
			socialNetworks[0] = facebook;
			socialNetworks[1] = twitter;
			socialNetworks[2] = instagram;
			socialNetworks[3] = linkedin;
			socialNetworks[4] = googlePlus;

			lcSocialNetworks.addContainers(socialNetworks);
			lcSocialNetworks.autoScroll = true;
			lcSocialNetworks.requestFocus();

		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}
	}

	private ListContainer.Layout getLayout(Image leftImage) {

		ListContainer.Layout layout = lcSocialNetworks.getLayout(5, 2);

		try {

			layout.insets.set(50, 10, 50, 10);
			layout.leftImageEnlargeIfSmaller = false;
			layout.defaultLeftImage = leftImage;
			layout.defaultRightImage = Images.aplyColor(new Image("images/thumb-up.png"), Colors.GRAY)
					.hwScaledFixedAspectRatio(fmH * 2, true);
			layout.defaultRightImage2 = Images.aplyColor(new Image("images/thumb-up.png"), Colors.GREEN)
					.hwScaledFixedAspectRatio(fmH * 2, true);
			layout.controlGap = 10;
			layout.lineGap = 25;
			layout.boldItems[2] = true;
			layout.defaultItemColors[2] = Colors.ORANGE;
			layout.relativeFontSizes[2] = +2;
			layout.relativeFontSizes[3] = +2;
			layout.positions[3] = RIGHT;
			layout.boldItems[3] = true;
			layout.setup();

		} catch (Exception ee) {
			MessageBox.showException(ee, true);
		}

		return layout;
	}

}
