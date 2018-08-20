package totalcross.sample.components.ui.charts;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Check;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.chart.Chart;
import totalcross.ui.chart.LineChart;
import totalcross.ui.chart.Series;
import totalcross.ui.gfx.Color;

public class LineChartSample extends ScrollContainer {
	private Container menu, options;
	private final int gap = 25;
	private LineChart line;

	Check hasLegend, showTitle, showCategories, showHGrids, showVGrids, showYValues;
	ComboBox legendPosition;

	@Override
	public void initUI() {
		super.initUI();
		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);

		menu = new Container();
		menu.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);
		menu.setFont(font.asBold());

		options = new Container();
		add(menu, LEFT + gap, TOP + gap, FILL - gap, 125 + DP);
		menu.add(options, CENTER, TOP + gap, Settings.screenWidth, WILL_RESIZE);
		int color1 = Chart.COLOR2;
		int color2 = Chart.COLOR3;
		int color3 = Chart.COLOR4;
		// setup the column chart
		String[] names = { "Jan", "Feb", "Mar", "Apr" };

		hasLegend = new Check("Legend");
		legendPosition = new ComboBox(new String[] { "Right", "Left", "Top", "Bottom" });
		legendPosition.setSelectedIndex(0);
		legendPosition.setEnabled(false);
		legendPosition.setForeColor(Colors.P_700);

		showTitle = new Check("Shade");

		showCategories = new Check("Category");
		showHGrids = new Check("HGrids");
		showVGrids = new Check("VGrids");
		showYValues = new Check("YValues");

		options.add(showTitle, LEFT + gap, TOP);

		options.add(hasLegend, AFTER + gap, SAME);

		showTitle.setText("Title");

		options.add(showCategories, RIGHT - gap, TOP, options);
		options.add(showYValues, SAME, AFTER + gap, SAME, SAME);
		options.add(showHGrids, SAME, AFTER + gap, SAME, SAME);
		options.add(showVGrids, SAME, AFTER, SAME, SAME);

		options.add(legendPosition, AFTER + gap, SAME, FIT - gap, PREFERRED, hasLegend);

		options.setInsets(gap / 4, gap / 4, gap / 4, gap / 4);
		options.resizeHeight();
		menu.resizeHeight();

		int bg = Color.darker(backColor, 16);

		// setup the line chart
		line = new LineChart(names);
		line.series.addElement(new Series("Rice", new double[] { 100, 102, 104, 106 }, color1));
		line.series.addElement(new Series("Beans", new double[] { 150, 155, 159, 164 }, color2));
		line.series.addElement(new Series("Oil", new double[] { 130, 137, 143, 150 }, color3));
		line.lineThickness = 2;
		line.setTitle("Sales Projection");
		line.setYAxis(0, 200, 10);
		add(line, LEFT + gap * 3 / 2, AFTER, FILL - gap * 3 / 2, FILL - gap * 3 / 2);
		line.setBackColor(bg);

		hasLegend.addPressListener(e -> {
			if (hasLegend.isChecked())
				legendPosition.setForeColor(Colors.ON_SURFACE);
			else
				legendPosition.setForeColor(Colors.P_700);
			legendPosition.setEnabled(hasLegend.isChecked());
			repaint();
		});

		showTitle.addPressListener(e -> {
			line.showTitle = showTitle.isChecked();
			repaint();
		});

		hasLegend.addPressListener(e -> {
			line.showLegend = hasLegend.isChecked();
			repaint();
		});

		legendPosition.addPressListener(e -> {
			line.legendPosition = getLegendPosition();
			repaint();
		});

		showCategories.addPressListener(e -> {
			line.showCategories = showCategories.isChecked();
			repaint();
		});

		showHGrids.addPressListener(e -> {
			line.showHGrids = showHGrids.isChecked();
			repaint();
		});

		showVGrids.addPressListener(e -> {
			line.showVGrids = showVGrids.isChecked();
			repaint();
		});

		showYValues.addPressListener(e -> {
			line.showYValues = showYValues.isChecked();
			repaint();
		});
	}

	private int getLegendPosition() {
		switch (legendPosition.getSelectedIndex()) {
		case 1:
			return LEFT;
		case 2:
			return TOP;
		case 3:
			return BOTTOM;
		default:
			return RIGHT;
		}
	}
}
