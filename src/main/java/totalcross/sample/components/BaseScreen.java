package totalcross.sample.components;

import totalcross.sample.util.Colors;
import totalcross.sample.util.MaterialConstants;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Bar;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.PressListener;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Graphics;
import totalcross.ui.icon.Icon;
import totalcross.ui.icon.IconType;
import totalcross.ui.icon.MaterialIcons;
import totalcross.util.UnitsConverter;
import totalcross.util.concurrent.Lock;
import totalcross.util.pdf.Base;

import java.awt.*;
import java.util.Set;

public abstract class BaseScreen extends Container {

    protected ScrollContainer content;
    protected boolean allowVerticalScroll = true;
    protected boolean allowHorizontalScroll = false;
    String docLink;
    Container bar;
    static final String branch = "master";
    static final String baseURL = "https://github.com/TotalCross/TCSample/tree/" + branch + "/src/main/java/";
    String codeUrl;
    boolean showBar;

    public  BaseScreen(String docLink, boolean showBar) {
        this.docLink = docLink;
        this.showBar = showBar;
        String classPath = BaseScreen.this.getClass().getName().replace(".", "/") + ".java";
        codeUrl = baseURL + classPath;
        System.out.println(codeUrl);
    }

    public BaseScreen() {
        this(null, true);
    }

    public BaseScreen(String docLink) {
        this(docLink, true);
    }

    public BaseScreen(boolean showBar) {
        this(null, showBar);
    }

    @Override
    public void initUI() {
        if(showBar) {
            bar = new Container();
            bar.setBackColor(Colors.PRIMARY);
            ExtraButton docBtn = new ExtraButton(MaterialIcons._BOOKMARK, "Documentation");
            String command =
                    Settings.platform.equals(Settings.ANDROID) || Settings.platform.equals(Settings.IPHONE) ?
                            "webview" : "url";
            System.out.println("command: " + command);

            if (docLink == null) {
                docBtn.setEnabled(false);
            } else {
                docBtn.addPressListener(c -> Vm.exec(command, docLink, 0, true));
            }

            ExtraButton codeBtn = new ExtraButton(MaterialIcons._CODE, "Code");
            codeBtn.addPressListener(c -> Vm.exec(command, codeUrl, 0, true));

            add(bar, LEFT, TOP, PARENTSIZE, DP + 52);
            bar.add(docBtn, LEFT, TOP, PARENTSIZE + 50, PREFERRED);
            bar.add(codeBtn, RIGHT, TOP, PARENTSIZE + 50, PREFERRED);
            bar.resizeHeight();
        }
        content = new ScrollContainer() {
            @Override
            public void initUI() {
                setScrollBars(allowHorizontalScroll, allowVerticalScroll);
                super.initUI();
            }
        };
        // Content
        add(content, LEFT, AFTER, PARENTSIZE, FILL);
        onContent(content);
    }

    public abstract void onContent(ScrollContainer content);

    public class ExtraButton extends Button {
        IconType iconType;
        final int UNDEFINED = -999999;
        int textX, iconX, iconY; // centralized text
        String originalText;
        final Lock lock = new Lock();
        public ExtraButton(IconType iconType, String text) {
            super(text);
            originalText = text;
            backColor = Colors.PRIMARY;
            transparentBackground = true;
            setNinePatch(null);
            this.iconType = iconType;
            textX = iconX = iconY = UNDEFINED;
        }

        @Override
        protected void onBoundsChanged(boolean screenChanged) {
            super.onBoundsChanged(screenChanged);
            if(screenChanged)
                textX = iconX = iconY = UNDEFINED;
        }

        @Override
        public void onPaint(Graphics g) {
            if(!isEnabled()) {
                foreColor = Color.getCursorColor(Color.WHITE);
            }
            else {
                foreColor = Color.WHITE;
            }
            g.foreColor = this.foreColor;

            Font bkpFont = this.font;
            Font iconFont = Font.getFont(iconType.fontName(), false, 18);

            if(textX == UNDEFINED) {
                int totalTextAndIconWidth = iconFont.fm.stringWidth(iconType.toString())
                        + UnitsConverter.toPixels(DP + 8) + bkpFont.fm.stringWidth(originalText);
                int desiredIconX = (getWidth() - totalTextAndIconWidth) / 2;
                setText(originalText);
                textX = (getWidth() - fm.stringWidth(text)) / 2;
                iconX = textX
                        - UnitsConverter.toPixels(DP + 8)
                        - iconFont.fm.stringWidth(iconType.toString());
                iconY = (getHeight() - iconFont.fm.height) / 2;
                int spaceSize = fm.stringWidth(" ");
                while (iconX < desiredIconX && textX > 0) { //shift until icon and text get on the middle
                    setText(" " + getText());
                    iconX += spaceSize;
                }
            }

            super.onPaint(g);

            g.setFont(iconFont);
            g.foreColor = foreColor;
            g.drawText(iconType.toString(), iconX, iconY);
            g.setFont(bkpFont); // restore font
        }
    }
}
