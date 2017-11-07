package totalcross.sample;

import totalcross.TotalCrossApplication;
import totalcross.sample.main.TCSample;

public class TCSampleApplication {
	public static void main(String[] args) {
	    TotalCrossApplication.run(
	        TCSample.class,
	        "/r",
	        "",
	        "/scr",
	        "400x600",
	        "/fontsize",
	        "24",
	        "/fingertouch");
	  }
}
