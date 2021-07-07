package totalcross.sample;

import totalcross.TotalCrossApplication;
import totalcross.sample.main.TCSample;

public class TCSampleApplication {
	public static void main(String[] args) {
	    TotalCrossApplication.run(
				TCSample.class,"/scr", "iphone"
		);
	    // Note: for a pre-6.1 launcher, the command line should be like:
		//	  TCSample.class, "/r", "<PLACE YOUR KEY>", "/scr", "iphone"

	  }
}
