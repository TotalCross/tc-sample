package totalcross.sample.components.ui.charts;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Check;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Slider;
import totalcross.ui.chart.Chart;
import totalcross.ui.chart.PieChart;
import totalcross.ui.chart.Series;
import totalcross.ui.gfx.Color;

public class PieChartSample extends ScrollContainer {

	private PieChart pie;
	private Container menu, options;
	private final int gap = 25;

	private Slider h3DSlider, v3DSlider;
	private Label hor, ver;
	private Check is3D, hasLegend, showTitle;
	private ComboBox legendPosition;

	@Override
	public void initUI() {
		// TODO Auto-generated method stub
		super.initUI();

		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);

		menu = new Container();
		menu.setBackForeColors(Colors.SURFACE, Colors.ON_SURFACE);
		menu.setFont(font.asBold());

		add(menu, LEFT + gap, TOP + gap, FILL - gap, 125 + DP);
		menu.add(options = new Container(), CENTER, TOP + gap, Settings.screenWidth, WILL_RESIZE);
		int color1 = Chart.COLOR2;
		int color2 = Chart.COLOR3;
		int color3 = Chart.COLOR4;
		int bg = Color.darker(backColor, 16);

		pie = new PieChart();
		pie.series.addElement(new Series("Rice", new double[] { 100 }, color1));
		pie.series.addElement(new Series("Beans", new double[] { 200 }, color2));
		pie.series.addElement(new Series("Oil", new double[] { 80 }, color3));
		pie.selectedSeries = 2;
		pie.yDecimalPlaces = 1; // 1 decimal place
		pie.setTitle("Profit Share");
		pie.legendValueSuffix = "%"; // show % instead of the value in the tooltip
		pie.setBackColor(bg);
		pie.type = Chart.IS_3D;

		hasLegend = new Check("Legend");
		legendPosition = new ComboBox(new String[] { "Right", "Left", "Top", "Bottom" });
		legendPosition.setSelectedIndex(0);
		legendPosition.setEnabled(false);
		legendPosition.setForeColor(Colors.P_700);

		is3D = new Check("3D");
		is3D.setChecked(true);
		showTitle = new Check("Title");

		options.add(showTitle, LEFT + gap, TOP);

		options.add(hasLegend, AFTER + gap, SAME);

		options.add(is3D, LEFT + gap, AFTER + gap * 2);

		hor = new Label("H:");
		ver = new Label("V:");
		options.add(hor, AFTER + gap, SAME - gap * 2);
		options.add(ver, SAME, AFTER);

		options.add(legendPosition, AFTER + gap, SAME, FILL - gap, PREFERRED, hasLegend);
		options.add(h3DSlider = new Slider(), AFTER, CENTER_OF, FILL - gap, PREFERRED, hor);
		options.add(v3DSlider = new Slider(), AFTER, CENTER_OF, FILL - gap, PREFERRED, ver);
		options.setInsets(gap / 4, gap / 4, gap / 4, gap / 4);

		options.resizeHeight();
		menu.resizeHeight();

		h3DSlider.setMinimum(-Settings.screenWidth / 45);
		h3DSlider.setMaximum(Settings.screenWidth / 55);
		v3DSlider.setMaximum(Settings.screenHeight / 50);
		h3DSlider.drawTicks = v3DSlider.drawTicks = true;
		h3DSlider.drawFilledArea = v3DSlider.drawFilledArea = false;
		v3DSlider.setValue(pie.perspectiveV);
		h3DSlider.setValue(0);
		pie.perspectiveH = 0;
		h3DSlider.setLiveScrolling(true);
		v3DSlider.setLiveScrolling(true);
		h3DSlider.sliderColor = Colors.ON_SURFACE;
		v3DSlider.sliderColor = Colors.ON_SURFACE;

		add(pie, LEFT + gap * 3 / 2, AFTER, FILL - gap * 3 / 2, FILL - gap * 3 / 2);
		hasLegend.addPressListener(e -> {
			if (hasLegend.isChecked())
				legendPosition.setForeColor(Colors.ON_SURFACE);
			else
				legendPosition.setForeColor(Colors.P_700);
			legendPosition.setEnabled(hasLegend.isChecked());
			repaint();
		});

		showTitle.addPressListener(e -> {
			pie.showTitle = showTitle.isChecked();
			repaint();
		});

		hasLegend.addPressListener(e -> {
			pie.showLegend = hasLegend.isChecked();
			repaint();
		});

		legendPosition.addPressListener(e -> {
			pie.legendPosition = getLegendPosition();
			repaint();
		});

		is3D.addPressListener(e -> {
			pie.type = (is3D.isChecked() ? Chart.IS_3D : 0);
			v3DSlider.setEnabled(is3D.isChecked());
			h3DSlider.setEnabled(is3D.isChecked());
			repaint();
		});

		v3DSlider.addPressListener(e -> {
			pie.perspectiveV = Math.max(v3DSlider.getValue(), 0);
			repaint();
		});

		h3DSlider.addPressListener(e -> {
			pie.legendPerspective = pie.perspectiveH = h3DSlider.getValue();
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
