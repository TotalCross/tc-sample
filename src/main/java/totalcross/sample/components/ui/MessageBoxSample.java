package totalcross.sample.components.ui;

import java.util.Random;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.ui.*;
import totalcross.ui.chart.ColumnChart;
import totalcross.ui.chart.Series;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;
import totalcross.util.pdf.Base;

public class MessageBoxSample extends BaseScreen {

	private ScrollContainer sc;
	private Button btnQuestion;
	private Button btnFact;
	private Button btnClear;
	private ColumnChart chart;
	private int H = 225;
	private int GAP = 50;
	private MessageBox mb;
	private int wrongAnswers, correctAnswers;

	public MessageBoxSample() {
		super("https://totalcross.gitbook.io/playbook/components/messagebox");
	}

	@Override
	public void onContent(ScrollContainer content) {
		wrongAnswers = 0;
		correctAnswers = 0;
		String[] facts = { "Scotland has 421 words for /“snow/”",
				"The /“Windy City/” name has nothing to do with Chicago weather",
				"The longest English word is 189,819 letters long", "Cats have fewer toes on their back paws",
				"Kleenex tissues were originally intended for gas masks",
				"Thanks to 3D printing, NASA can basically /“email/” tools to astronauts",
				"There were active volcanoes on the moon when dinosaurs were alive",
				"No number before 1,000 contains the letter A", "Movie trailers originally played after the movie",
				"Humans aren’t the only animals that dream",
				"The inventor of the microwave appliance only received $2 for his discovery",
				"Totalcross was founded in 2014" };
		String[] questions = { "Slug is a measurement unit?", "There are more grains of sand than stars in the sky?",
				"Totalcross > Ionic 3 ?", "Are penguins birds ?", "Are there just oranges oranges ?", };
		int[] questionAnswers = { 0, 0, 0, 0, 1 };
		int difficulty = 5;
		Random r = new Random();


		btnFact = new Button("Random fact");
		btnFact.setBackForeColors(Colors.P_700, Colors.ON_P_700);

		btnFact.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				mb = new MessageBox("Did you know?", facts[r.nextInt(facts.length)], new String[] { "Nice!" });
				mb.setRect(CENTER, CENTER, SCREENSIZE + 50, SCREENSIZE + 30);
				mb.setBackForeColors(Colors.P_300, Colors.ON_P_300);
				mb.popup();
			}
		});

		btnQuestion = new Button("Random question");
		btnQuestion.setBackForeColors(Colors.P_700, Colors.ON_P_700);

		btnQuestion.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				int QuestionIndex = r.nextInt(questions.length);
				MessageBox mb = new MessageBox("Question", questions[QuestionIndex], new String[] { "Yes", "No" });
				mb.setBackForeColors(Colors.P_300, Colors.ON_P_300);
				mb.popup();
				if (mb.getPressedButtonIndex() == questionAnswers[QuestionIndex]) {
					if (correctAnswers + 1 == difficulty) {
						MessageBox win = new MessageBox("Congratulations!", "You won!            ",
								new String[] { "Nice" });
						win.setBackForeColors(Colors.P_300, Colors.ON_P_300);
						win.popup();
						correctAnswers = 0;
						wrongAnswers = 0;
						chart.series.removeAllElements();
						String columnLabel = "Correct answers " + correctAnswers + "/" + difficulty;
						chart.series.addElement(new Series(columnLabel, new double[] { correctAnswers, 0 },
								Color.getRGB(32, 204, 104)));
						chart.series.addElement(new Series("Wrong answers", new double[] { 0, wrongAnswers },
								Color.getRGB(211, 68, 21)));
						repaintNow();
					} else {
						System.out.println("Correct");
						correctAnswers++;
						System.out.println("Correct answers: " + correctAnswers);
					}

				} else {
					if (wrongAnswers + 1 == difficulty) {
						MessageBox lose = new MessageBox("Too bad!", "You lost!", new String[] { "Okay" });
						lose.setBackForeColors(Colors.P_300, Colors.ON_P_300);
						lose.popup();
						correctAnswers = 0;
						wrongAnswers = 0;
						chart.series.removeAllElements();
						String columnLabel = "Correct answers " + correctAnswers + "/" + difficulty;
						chart.series.addElement(new Series(columnLabel, new double[] { correctAnswers, 0 },
								Color.getRGB(32, 204, 104)));
						chart.series.addElement(new Series("Wrong answers", new double[] { 0, wrongAnswers },
								Color.getRGB(211, 68, 21)));
						repaintNow();
					} else {
						System.out.println("Wrong");
						wrongAnswers++;
						System.out.println("Wrong answers: " + wrongAnswers);
					}
				}
				chart.series.removeAllElements();
				String columnLabel = "Correct answers " + correctAnswers + "/" + difficulty;
				chart.series.addElement(
						new Series(columnLabel, new double[] { correctAnswers, 0 }, Color.getRGB(32, 204, 104)));
				chart.series.addElement(
						new Series("Wrong answers", new double[] { 0, wrongAnswers }, Color.getRGB(211, 68, 21)));
				repaintNow();
			}
		});

		btnClear = new Button("Clear");
		btnClear.setBackForeColors(Colors.P_400, Colors.ON_P_400);

		btnClear.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				correctAnswers = 0;
				wrongAnswers = 0;
				Toast.show("Data cleared", 2000);
				System.out.println("Correct answers: " + correctAnswers);
				System.out.println("Wrong answers: " + wrongAnswers);
				chart.series.removeAllElements();
				chart.series.addElement(
						new Series("Correct answers", new double[] { correctAnswers, 0 }, Color.getRGB(32, 204, 104)));
				chart.series.addElement(
						new Series("Wrong answers", new double[] { 0, wrongAnswers }, Color.getRGB(211, 68, 21)));
				repaintNow();
			}
		});
		String[] chartParam = new String[2];
		chartParam[0] = "Correct Answers";
		chartParam[1] = "Wrong Answers";

		chart = new ColumnChart(chartParam);
		chart.series.addElement(
				new Series("Correct answers", new double[] { correctAnswers, 0 }, Color.getRGB(32, 204, 104)));
		chart.series
				.addElement(new Series("Wrong answers", new double[] { 0, wrongAnswers }, Color.getRGB(211, 68, 21)));

		chart.setYAxis(0, 15, 5);
		chart.showCategories = true;

		content.setFont(font.asBold());
		content.add(btnFact, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + H);
		content.add(btnQuestion, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + H);
		content.add(chart, LEFT, AFTER, FILL, SCREENSIZE + 40);
		content.add(btnClear, CENTER, AFTER + GAP, SCREENSIZE + 90, PREFERRED + H);
		content.add(new Spacer(), LEFT, AFTER + UnitsConverter.toPixels(DP + 24));
		chart.setBackColor(Color.WHITE);
	}
}
