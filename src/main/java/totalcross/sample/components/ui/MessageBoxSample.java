package totalcross.sample.components.ui;

import java.util.Random;

import totalcross.sample.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Toast;
import totalcross.ui.chart.ColumnChart;
import totalcross.ui.chart.Series;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class MessageBoxSample extends Container {

	private ScrollContainer sc;
	private Button btnQuestion;
	private Button btnFact;
	private Button btnClear;
	private ColumnChart chart;
	private int H = 225;
	private int GAP = 50;
	private MessageBox mb;
	private int wrongAnswers, correctAnswers;

	@Override
	public void initUI() {
		wrongAnswers = 0;
		correctAnswers = 0;
		String[] facts = new String[10];
		String[] questions = new String[10];
		int[] questionAnswers = new int[10];
		questions[1] = "Test (yes=correct)";
		for (int i = 1; i <= 10; i++) {
			questions[i - 1] = "Question " + i;
			facts[i - 1] = "fact                     " + i;
			if (i % 2 == 0) {
				questionAnswers[i - 1] = 0;
			} else {
				questionAnswers[i - 1] = 1;
			}
		}
		Random r = new Random();

		sc = new ScrollContainer(false, true);
		add(sc, LEFT, TOP, FILL, FILL);

		btnFact = new Button("Random fact");
		btnFact.setBackForeColors(Colors.P_700, Colors.ON_P_700);

		btnFact.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {

				mb = new MessageBox("Did you know?", facts[r.nextInt(10)], new String[] { "Nice!" });
				mb.setRect(CENTER, CENTER, SCREENSIZE + 50, SCREENSIZE + 30);
				mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
				mb.popup();

			}

		});

		btnQuestion = new Button("Random question");
		btnQuestion.setBackForeColors(Colors.P_700, Colors.ON_P_700);

		btnQuestion.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				int QuestionIndex = r.nextInt(10);
				MessageBox mb = new MessageBox("Question", questions[QuestionIndex], new String[] { "Yes", "No" });
				mb.setBackForeColors(Colors.WARNING, Colors.ON_WARNING);
				mb.popup();
				if (mb.getPressedButtonIndex() == questionAnswers[QuestionIndex]) {
					if(correctAnswers<10) {
						System.out.println("Correct");
						correctAnswers++;
						System.out.println("Correct answers: " + correctAnswers);
					}
					else {
						System.out.println("Congratulations, reset");
						
						MessageBox congratulations = new MessageBox("Congratulations!", "You answered 10 questions correctly! You won!", new String[] { "Nice!" });
						congratulations.setBackForeColors(Colors.P_400, Colors.ON_P_400);
						congratulations.popup();
						correctAnswers= 0;
						wrongAnswers=0;
					}

				} else {
					if(wrongAnswers<10) {
						System.out.println("Wrong");
						wrongAnswers++;
						System.out.println("Wrong answers: " + wrongAnswers);
					}else {
						System.out.println("Too bad, you lose");
						MessageBox lose = new MessageBox("You lose :(", "You lost the game, but don´t be afraid to try again! :)", new String[] { "Okay!" });
						lose.setBackForeColors(Colors.S_400, Colors.ON_S_400);
						lose.popup();
						correctAnswers= 0;
						wrongAnswers=0;
					}
				}
				chart.series.removeAllElements();
				chart.series.addElement(
						new Series("Correct answers", new double[] { correctAnswers, 0 }, Color.getRGB(32, 204, 104)));
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

		sc.setFont(font.asBold());
		sc.add(btnFact, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + H);
		sc.add(btnQuestion, LEFT + GAP, AFTER + GAP, FILL - GAP, PREFERRED + H);
		sc.add(chart, LEFT, AFTER, FILL, SCREENSIZE + 40);
		sc.add(btnClear, CENTER, AFTER + GAP, SCREENSIZE + 90, PREFERRED + H);

		chart.setBackColor(Color.WHITE);

	}

}
