package totalcross.sample.components.ui;

import totalcross.io.IOException;
import totalcross.sys.Vm;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.html.Document;
import totalcross.ui.html.HtmlContainer;
import totalcross.xml.SyntaxException;
import totalcross.xml.XmlReadable;
import totalcross.xml.XmlReadableByteArray;
import totalcross.xml.XmlReadableString;

public class HTMLContainerSample extends Container{
	private static final String stringSample = "<html><head><title></title></head><body><h1>Home Screen</h1><p>This is the main screen of the application and will be displayed whenever you start up the application. You can get to this screen at any time by tapping the 'Home' Button. </p><p><b>Choosing a guideline:</b><br/><ol><li>Tap on the down arrow on the right side of the Guideline Selection box to open up a list of all the guidelines available.</li><li>Tap on the guideline. If it does not open immediately tap on the 'Go' button at the right side of the box.</li></ol></p><p><b>To filter the list of guidelines:</b><br/><ol><li>Tap on the down arrow on the right side of the Topic Selection box to open up a list of topics.</li><li>Tap on the topic to which the guideline you are interested belongs. This will filter the number of guidelines available in the Guideline Selection box.</li></ol><p>(To expand the list of guidelines again, choose 'All Guidelines' in the Topic Selection box)</p></p><p><b>Searching for keywords among all guidelines:</b><br/><ol><li>Enter the keyword(s) into the Search box.</li><li>Tap 'Go'.</li></ol><ul><li>To enter keyword(s) from previous searches tap the down arrow at the right side of the search box and choose the words for which you want to search again.</li><li>For more search options tap on the Advanced Search button.</li></ul></p><p><b>Toolbar:</b><br/><ul><li>Back button: Opens the last guideline you viewed at the last page you were reading.</li><li>Bookmark button: Opens the list of all bookmarks you have saved.</li><li>Help button: Opens this screen.</li></ul></p><p><b>Menu:</b><br/><ul><li>Tap on the top left-hand side of the screen or on the devices menu button to open the CliniPEARLS menu bar.</li><li>File:<ul><li>Exit - quit the program.</li></ul></li><li>Tools:<ul><li>Manage subscription - Edit the number of guidelines you want on your device.</li></ul></li><li>Options:<ul><li>++ Font - Increase the size of the font in the guideline pages only.</li><li>--Font - Decrease the size of the font in the guideline pages only.</li><li>Update user - Update your CliniPEARLS username and password.</li></ul></li></ul></p></body></html>";
	  private HtmlContainer htmlCnr;
	  private ComboBox cb;
	  private String[] samples = { "", "tc/samples/api/ui/data/Sample0.html", "tc/samples/api/ui/data/Sample1.html",
	      "tc/samples/api/ui/data/Sample2.html", "tc/samples/api/ui/data/Sample3.html",
	      "tc/samples/api/ui/data/Sample4.html", "tc/samples/api/ui/data/Sample5.html",
	      "tc/samples/api/ui/data/Sample6.html", };
	  String gif = "tc/samples/api/ui/data/totalcross.gif"; // in the html we didnt put the full path, so we put here so tc.Deploy can store the gif in the tcz

	  @Override
	  public void initUI() {
	    super.initUI();
	    String[] items = { "Sample str", "Sample 0", "Sample 1", "Sample 2", "Sample 3", "Sample 4", "Sample 5",
	        "Sample 6" };
	    add(cb = new ComboBox(items), LEFT, TOP, FILL, PREFERRED);
	    this.setBackColor(Color.getRGB(74,144,226));
	    cb.setBackColor(Color.getRGB(74,144,226));
	    cb.setForeColor(Color.WHITE);
	    add(htmlCnr = new HtmlContainer(), LEFT, AFTER, FILL, FILL);
	    htmlCnr.setBackForeColors(Color.WHITE, Color.BLACK);
	  }

	  @Override
	  public void onEvent(Event event) {
	    switch (event.type) {
	    case ControlEvent.PRESSED:
	      try {
	        if (event.target == htmlCnr) {
	        } else if (event.target == cb) {
	          int sel = cb.getSelectedIndex();
	          switch (sel) {
	          case -1:
	            break;
	          case 0:
	            render(new XmlReadableString(stringSample));
	            break;
	          default:
	            loadSample(sel);
	            break;
	          }
	        }
	      } catch (Exception e) {
	        MessageBox.showException(e, true);
	      }
	      break;
	    }
	  }

	  private void loadSample(int n) throws Exception {
	    render(new XmlReadableByteArray(Vm.getFile(samples[n])));
	  }

	  private XmlReadable lastSource;

	  void renderLast() throws IOException, SyntaxException {
	    render(lastSource);
	  }

	  private void render(XmlReadable source) throws IOException, SyntaxException {
	    lastSource = source;
	    if (source != null) {
	      Document doc = new Document(source);
	      htmlCnr.setDocument(doc);
	    }
	  }
}
