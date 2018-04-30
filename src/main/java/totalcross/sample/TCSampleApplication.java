package totalcross.sample;

import totalcross.TotalCrossApplication;
import totalcross.sample.main.TCSample;

public class TCSampleApplication {
	public static void main(String[] args) {
	    TotalCrossApplication.run(
	        TCSample.class,
	        "/r",
	        "put yout key here");
	  }
}
