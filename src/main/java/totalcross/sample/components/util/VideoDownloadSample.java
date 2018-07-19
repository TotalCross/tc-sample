package totalcross.sample.components.util;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Spacer;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

public class VideoDownloadSample extends ScrollContainer {
	private int gap = 50;
	private int count = 10;
	private String downloadPath;

	@Override
	public void initUI() {
		Label status = new Label();
		status.align = CENTER;
		add(status, CENTER, BOTTOM - gap, SCREENSIZE, fmH*2);
		
		Spacer spcr = new Spacer();
		add(spcr, CENTER, CENTER, SCREENSIZE, 30 + DP);
		Button downloadBtn = new Button("DOWNLOAD");
		downloadBtn.setBackForeColors(Colors.P_DARK, Color.WHITE);
		add(downloadBtn, CENTER, BEFORE, PREFERRED + 100, PREFERRED + 70, spcr);

		Button executeBtn = new Button("EXECUTE");
		executeBtn.setBackForeColors(Colors.P_DARK, Color.WHITE);
		executeBtn.setEnabled(false);
		add(executeBtn, CENTER, AFTER, downloadBtn.getWidth(), downloadBtn.getHeight(), spcr);
		
		Edit videoPath = new Edit();
		
		if (Settings.platform.equals(Settings.JAVA) || Settings.platform.equals(Settings.WIN32)
				|| Settings.isWindowsCE()) {
			downloadPath = "C:/Users/" + Settings.userName + "/Downloads/material-design.mp4";
		} else if (Settings.isIOS()) {
			downloadPath = "material-design.mp4";
		} else if (Settings.platform.equals(Settings.ANDROID)) {
			System.out.println("Hey, I'm on Android!");
			downloadPath = "file:///storage/emulated/0/Download/material-design.mp4";
		}
		videoPath.setText(downloadPath);
		
		downloadBtn.addPressListener((e) -> {
			Vm.exec("url",
					"https://doc-0g-9c-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/gj3bfe8sbtcki1kgtqh6dvhlvrbl7pfo/1532023200000/08527788165150735579/*/1qRNfrJp_78J__Nud_cZvvr9GEqxQOygZ?e=download",
					0, true);
			status.setText("Downloading, please wait. (" + count + ")");
			status.addTimer(1000);
		});
		
		status.addTimerListener((e) -> {
			status.setText("Downloading, please wait. (" + --count + ")");
			if(count <= 0) {
				status.setText("");
				executeBtn.setEnabled(true);
			}
		});

		executeBtn.addPressListener((e) -> {
			Vm.exec("viewer", downloadPath, 0, true);
			Label info = new Label("If it doesn't execute, put the right path here:");
			info.setFont(Font.getFont(true, 12));
			info.align = CENTER;
			add(info, LEFT + gap, AFTER + gap*2, FILL - gap, PREFERRED, executeBtn);
			add(videoPath, LEFT + gap, AFTER, FILL - gap, PREFERRED + gap, info);
		});
	}
}
