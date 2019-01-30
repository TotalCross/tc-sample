package totalcross.sample.components.sys;

import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class CommandLine extends Container {

	@Override
	public void initUI() {
		Label label = new Label("Send terminal commands to the underlying system");
		label.setForeColor(Color.BLACK);
		add(label,CENTER,TOP);

		Edit editCommand = new Edit();
		editCommand.caption = "Command";
		editCommand.fillColor = Color.BRIGHT;
		add(editCommand,SAME,AFTER + UnitsConverter.toPixels(DP + 100));

		Edit editArgs = new Edit();
		editArgs.caption = "Args";
		editArgs.fillColor = Color.BRIGHT;
		add(editArgs,SAME,AFTER + UnitsConverter.toPixels(DP + 100));

		Edit editLaunch = new Edit();
		editLaunch.caption = "LaunchCode";
		editLaunch.fillColor = Color.BRIGHT;
		editLaunch.setMode(Edit.CURRENCY);
		editLaunch.setKeyboard(Edit.KBD_NUMERIC);
		add(editLaunch,SAME,AFTER + UnitsConverter.toPixels(DP + 100));

		Edit editWait = new Edit();
		editWait.caption = "Wait - true/false";
		editWait.fillColor = Color.BRIGHT;
		add(editWait,SAME,AFTER + UnitsConverter.toPixels(DP + 100));

		Button btnExe = new Button("Run");
		btnExe.setForeColor(Color.BLACK);
		add(btnExe,SAME,AFTER + UnitsConverter.toPixels(DP + 100));

		btnExe.addPressListener(e -> {
			Vm.exec(editCommand.getText(), editArgs.getText(), Integer.parseInt(editLaunch.getText()), Boolean.parseBoolean(editWait.getText()));
		});

	}
}