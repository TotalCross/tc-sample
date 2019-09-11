package totalcross.sample.components.lang.thread;

import totalcross.sample.components.BaseScreen;
import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class ThreadSample extends BaseScreen implements Runnable {
	private int gap = UnitsConverter.toPixels(DP + 8);

	interface SetX {
		void incX(int x);
		void setX(int x);
	}

	Button directionButton;
	Button pauseButton, unpauseButton;
	static boolean paused, paused0;
	SetX[] containers;
	int direction = Settings.platform.equals(Settings.JAVA) || Settings.platform.equals(Settings.WIN32) ? 1 : 4; // moving
																													// right
	Thread slideThread;
	boolean running, finished;

	@Override
	public void onContent(ScrollContainer content) {
		Button.commonGap = fmH / 4;
		Vm.tweak(Vm.TWEAK_DUMP_MEM_STATS, true);

		directionButton = new Button("Switch Direction");
		directionButton.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		directionButton.addPressListener(e -> restartThread(true));

		pauseButton = new Button("Pause");
		pauseButton.setBackForeColors(Colors.P_600, Colors.ON_P_600);
		pauseButton.addPressListener(e -> pause());

		unpauseButton = new Button("Unpause");
		unpauseButton.setBackForeColors(Colors.S_600, Colors.ON_S_600);
		unpauseButton.addPressListener(e -> pause());

		containers = new SetX[3];
		containers[0] = new TypingContainer(true);
		containers[1] = new HTTPContainer();
		containers[2] = new TypingContainer(false);
		content.add(directionButton, LEFT + gap/2, TOP + gap/2);
		content.add(unpauseButton, RIGHT - gap/2, SAME);
		content.add(pauseButton, RIGHT - gap/2, SAME, unpauseButton.getWidth(), PREFERRED);
		unpauseButton.setVisible(false);
		Button.commonGap = 0;

		for (int i = 0; i < containers.length; i++) {
			content.add((Container) containers[i], i == 0 ? LEFT : AFTER, i == 0 ? AFTER + gap : SAME, SCREENSIZE, FILL,
					i == 0 ? pauseButton : null);
		}

		slideThread = new Thread(this);
		slideThread.start();
	}

	public void pause() {
		paused = !paused;
		pauseButton.setVisible(!paused);
		unpauseButton.setVisible(paused);
	}

	private void restartThread(boolean changeDir) {
		running = false;
		while (!finished) {
			Thread.yield();
		}
		if (changeDir) {
			direction = -direction;
		}
		slideThread = new Thread(this);
		slideThread.start();
	}

	@Override
	public void run() {
		running = true;
		finished = false;
		while (running) {
			SetX temp;
			if (direction > 0) {
				temp = containers[2];
				containers[2] = containers[1];
				containers[2].setX(0);

				containers[1] = containers[0];
				containers[1].setX(-width);

				containers[0] = temp;
				containers[0].setX(-width * 2);
			} else {
				temp = containers[0];
				containers[0] = containers[1];
				containers[0].setX(0);

				containers[1] = containers[2];
				containers[1].setX(width);

				containers[2] = temp;
				containers[2].setX(width * 2);
			}
			while (running && ((Container) containers[1]).getX() != 0) {
				if (!paused) {
					for (int i = 0; i < containers.length; i++) {
						containers[i].incX(direction);
						try {
							repaint();
						} catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
				Vm.sleep(5); // without this, scroll does not work
			}
			if (running) {
				paused0 = true;
				Vm.sleep(1000);
				paused0 = false;
			}
		}
		finished = true;
	}

	@Override
	public void reposition() {
		super.reposition();
		restartThread(false);
	}
}
