package totalcross.sample.components.ui.charts;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Check;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.chart.ArcChart;
import totalcross.ui.chart.Chart;
import totalcross.ui.chart.Series;
import totalcross.ui.gfx.Color;

public class ArcChartSample extends ScrollContainer {
	private Container menu, options;
	private final int gap =  100;
	private ArcChart arc;

	Check hasLegend, showTitle;
	ComboBox legendPosition;

	@Override
	public void initUI() {
		super.initUI();
		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);

		menu = new Container();
		menu.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);
		menu.setFont(font.asBold());

		options = new Container();
		add(menu, LEFT, TOP, FILL, 125 + DP);

		menu.add(options, CENTER, TOP, Settings.screenWidth, WILL_RESIZE);
		
		int color1 = Chart.COLOR2;
		int color2 = Chart.COLOR3;
		int color3 = Chart.COLOR4;

		hasLegend = new Check("Legend");
		legendPosition = new ComboBox(new String[] { "Right", "Left", "Top", "Bottom" });
		legendPosition.setSelectedIndex(0);
		legendPosition.setEnabled(false);
		legendPosition.setForeColor(Colors.P_700);

		showTitle = new Check("Shade");

		options.add(showTitle, LEFT + gap, TOP);

		options.add(hasLegend, SAME, AFTER);


		showTitle.setText("Title");

		options.add(legendPosition, RIGHT, TOP);

		options.setInsets(gap / 4, gap / 4, gap / 4, gap / 4);
		options.resizeHeight();
		menu.resizeHeight();

		int bg = Color.darker(backColor, 16);

		arc = new ArcChart();
		arc.series.addElement(new Series("Rice", new double[] { 100 }, color1));
		arc.series.addElement(new Series("Beans", new double[] { 200 }, color2));
		arc.series.addElement(new Series("Oil", new double[] { 80 }, color3));
		arc.selectedSeries = 2;
		arc.yDecimalPlaces = 1; // 1 decimal place
		arc.setTitle("Profit Share");
		arc.legendValueSuffix = "%"; // show % instead of the value in the tooltip
		add(arc, LEFT, AFTER, FILL, FILL);
		arc.setBackColor(bg);

		hasLegend.addPressListener(e -> {
			if (hasLegend.isChecked())
				legendPosition.setForeColor(Colors.ON_SURFACE);
			else
				legendPosition.setForeColor(Colors.P_700);
			legendPosition.setEnabled(hasLegend.isChecked());
			repaint();
		});

		showTitle.addPressListener(e -> {
			arc.showTitle = showTitle.isChecked();
			repaint();
		});

		hasLegend.addPressListener(e -> {
			arc.showLegend = hasLegend.isChecked();
			repaint();
		});

		legendPosition.addPressListener(e -> {
			arc.legendPosition = getLegendPosition();
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
