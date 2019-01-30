package totalcross.sample.components.sys;

import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Radio;
import totalcross.ui.RadioGroupController;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class CommandLine extends Container {

	@Override
	public void initUI() {		
		Label label = new Label("Send terminal commands \nto the underlying system");
		label.autoSplit = true;
		label.setForeColor(Color.BLACK);
		add(label, CENTER, TOP + UnitsConverter.toPixels(DP + 60));

		Edit editCommand = new Edit();
		editCommand.caption = "Command";
		editCommand.fillColor = Color.BRIGHT;
		add(editCommand, CENTER, AFTER + UnitsConverter.toPixels(DP + 60));

		Edit editArgs = new Edit();
		editArgs.caption = "Args";
		editArgs.fillColor = Color.BRIGHT;
		add(editArgs, CENTER, AFTER + UnitsConverter.toPixels(DP + 60));

		Edit editLaunch = new Edit();
		editLaunch.caption = "LaunchCode";
		editLaunch.fillColor = Color.BRIGHT;
		editLaunch.setMode(Edit.CURRENCY);
		editLaunch.setKeyboard(Edit.KBD_NUMERIC);
		add(editLaunch, CENTER, AFTER + UnitsConverter.toPixels(DP + 60));
		
		Container radioContainer = new Container();
		add(radioContainer, CENTER, AFTER + UnitsConverter.toPixels(DP + 60), UnitsConverter.toPixels(DP + 200), UnitsConverter.toPixels(DP + 100));
		
		RadioGroupController radioGroup = new RadioGroupController();
		
		Label labelRadio = new Label("Wait");
		labelRadio.setForeColor(Color.BLACK);
		radioContainer.add(labelRadio, CENTER, TOP);
		
		Radio radioYes = new Radio("Yes", radioGroup);
		radioYes.setForeColor(Color.BLACK);
		radioContainer.add(radioYes, LEFT + UnitsConverter.toPixels(DP + 50), AFTER + UnitsConverter.toPixels(DP + 60));
		
		Radio radioNo = new Radio("No", radioGroup);
		radioNo.setChecked(true);
		radioNo.setForeColor(Color.BLACK);
		radioContainer.add(radioNo, AFTER + UnitsConverter.toPixels(DP + 200), SAME);
			
		Button btnExe = new Button("Run");
		btnExe.setForeColor(Color.BLACK);
		add(btnExe, CENTER, AFTER);
		
		btnExe.addPressListener(e -> {
			Vm.exec(editCommand.getText(), editArgs.getText(), Integer.parseInt(editLaunch.getText()), radioYes.isChecked() ? true : false);
		});

	}
}