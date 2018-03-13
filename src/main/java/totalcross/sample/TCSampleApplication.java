package totalcross.sample;

import totalcross.TotalCrossApplication;
import totalcross.sample.main.TCSample;

public class TCSampleApplication {
	public static void main(String[] args) {
	    TotalCrossApplication.run(
	        TCSample.class,
	        "/scr",
	        "360x568",
//	        "720x1136",
//	        "768x1024",
//	        "/scale",
//	        "0.5",
	        "/r",
	        "544353541A8E8B2A28D5B8E0",
	        "/scr",
	        "360x640"
	        );
	  }
}
