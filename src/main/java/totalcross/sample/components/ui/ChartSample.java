package totalcross.sample.components.ui;

import totalcross.sample.util.Colors;
import totalcross.sys.Settings;
import totalcross.ui.Check;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Slider;
import totalcross.ui.TabbedContainer;
import totalcross.ui.chart.ArcChart;
import totalcross.ui.chart.Chart;
import totalcross.ui.chart.ColumnChart;
import totalcross.ui.chart.LineChart;
import totalcross.ui.chart.PieChart;
import totalcross.ui.chart.Series;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Rect;

public class ChartSample extends ScrollContainer {
	private Container menu, options;
	private final int gap = 25;
	private ColumnChart column;
	private LineChart line;
	private PieChart pie;
	private ArcChart arc;
	private Slider h3DSlider, v3DSlider;

	Label hor, ver;
	Check is3D, hasLegend, hasShade, showTitle, showCategories, showHGrids, showVGrids, showYValues;
	ComboBox legendPosition, shadeDirection, shadeType;
	TabbedContainer tp;

	@Override
	public void initUI() {
		super.initUI();
		setBackForeColors(Colors.BACKGROUND, Colors.ON_BACKGROUND);
		setFont(Font.getFont(fmH));
		
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
		column = new ColumnChart(names);
		column.series.addElement(new Series("Rice", new double[] { 1000, 1020, 1040, 1060 }, color1));
		column.series.addElement(new Series("Beans", new double[] { 850, 755, 859, 964 }, color2));
		column.series.addElement(new Series("Oil", new double[] { 930, 837, 943, 1000 }, color3));

		column.setTitle("Sales Projection");
		column.setYAxis(0, 1100, 11);
		column.type = Chart.IS_3D;

		hasLegend = new Check("Legend");
		legendPosition = new ComboBox(new String[] { "Right", "Left", "Top", "Bottom" });
		legendPosition.setSelectedIndex(0);
		legendPosition.setEnabled(false);

		hasShade = new Check("Shade");
		shadeDirection = new ComboBox(new String[] { "Direction", "Horizontal", "Vertical" });
		shadeDirection.setSelectedIndex(0);
		shadeDirection.setEnabled(false);

		shadeType = new ComboBox(new String[] { "Type", "Inverted", "Dark" });
		shadeType.setSelectedIndex(0);
		shadeType.setEnabled(false);

		is3D = new Check("3D");
		is3D.setChecked(true);
		showTitle = new Check("Shade");

		showCategories = new Check("Category");
		showHGrids = new Check("HGrids");
		showVGrids = new Check("VGrids");
		showYValues = new Check("YValues");

		options.add(showTitle, LEFT + gap, TOP);

		options.add(hasLegend, AFTER + gap, SAME);

		options.add(hasShade, SAME, AFTER + gap, showTitle);
		showTitle.setText("Title");

		options.add(is3D, SAME, AFTER + gap * 2, hasShade);

		hor = new Label("H:");
		ver = new Label("V:");
		options.add(hor, AFTER + gap, SAME - gap * 2, hasShade.getWidth() - is3D.getWidth(), PREFERRED);
		options.add(ver, SAME, AFTER, hasShade.getWidth() - is3D.getWidth(), PREFERRED);


		options.add(showCategories, RIGHT - gap, TOP, options);
		options.add(showYValues, SAME, AFTER + gap, SAME, SAME);
		options.add(showHGrids, SAME, AFTER + gap, SAME, SAME);
		options.add(showVGrids, SAME, AFTER, SAME, SAME);
		
		options.add(legendPosition, AFTER + gap, SAME, FIT - gap, PREFERRED, hasLegend);
		options.add(shadeDirection, AFTER + gap, SAME, legendPosition.getWidth(), PREFERRED, hasShade);
		options.add(shadeType, AFTER + gap, SAME, hasLegend.getWidth(), PREFERRED, shadeDirection);
		options.add(h3DSlider = new Slider(), AFTER, CENTER_OF, shadeDirection.getWidth() + shadeType.getWidth(), PREFERRED, hor);
		options.add(v3DSlider = new Slider(), AFTER, CENTER_OF, shadeDirection.getWidth() + shadeType.getWidth(), PREFERRED, ver);
		options.setInsets(gap/4, gap/4, gap/4, gap/4);
		options.resizeHeight();
		menu.resizeHeight();

		h3DSlider.setMinimum(-Settings.screenWidth/45);
		h3DSlider.setMaximum(Settings.screenWidth/45);
		v3DSlider.setMaximum(Settings.screenHeight/80);
		h3DSlider.drawTicks = v3DSlider.drawTicks = true;
		h3DSlider.drawFilledArea = v3DSlider.drawFilledArea = false;
		v3DSlider.setValue(column.perspectiveV);
		h3DSlider.setValue(column.perspectiveH);
		h3DSlider.setLiveScrolling(true);
		v3DSlider.setLiveScrolling(true);
		
		int bg = Color.darker(backColor, 16);
		tp = new TabbedContainer(new String[] { " Column ", " Line ", " Pie ", " Arc " });
		tp.extraTabHeight = fmH / 2;
		add(tp, LEFT + gap, AFTER + gap, FILL - gap, FILL - gap, options);

		tp.getContainer(0).add(column, LEFT, TOP, FILL, FILL);
		column.setBackColor(bg);

		// setup the line chart
		line = new LineChart(names);
		line.series.addElement(new Series("Rice", new double[] { 100, 102, 104, 106 }, color1));
		line.series.addElement(new Series("Beans", new double[] { 150, 155, 159, 164 }, color2));
		line.series.addElement(new Series("Oil", new double[] { 130, 137, 143, 150 }, color3));
		line.lineThickness = 2;
		line.setTitle("Sales Projection");
		line.setYAxis(0, 200, 10);
		tp.getContainer(1).add(line, LEFT, TOP, FILL, FILL);
		line.setBackColor(bg);

		// setup the pie chart
		pie = new PieChart();
		pie.series.addElement(new Series("Rice", new double[] { 100 }, color1));
		pie.series.addElement(new Series("Beans", new double[] { 200 }, color2));
		pie.series.addElement(new Series("Oil", new double[] { 80 }, color3));
		pie.selectedSeries = 2;
		pie.yDecimalPlaces = 1; // 1 decimal place
		pie.setTitle("Profit Share");
		pie.legendValueSuffix = "%"; // show % instead of the value in the tooltip
		tp.getContainer(2).add(pie, LEFT, TOP, FILL, FILL);
		pie.setBackColor(bg);
		pie.type = Chart.IS_3D;

		arc = new ArcChart();
		arc.series.addElement(new Series("Rice", new double[] { 100 }, color1));
		arc.series.addElement(new Series("Beans", new double[] { 200 }, color2));
		arc.series.addElement(new Series("Oil", new double[] { 80 }, color3));
		arc.selectedSeries = 2;
		arc.yDecimalPlaces = 1; // 1 decimal place
		arc.setTitle("Profit Share");
		arc.legendValueSuffix = "%"; // show % instead of the value in the tooltip
		tp.getContainer(3).add(arc, LEFT, TOP, FILL, FILL);
		arc.setBackColor(bg);

		column.xDecimalPlaces = column.yDecimalPlaces = line.yDecimalPlaces = 0;
		line.legendPerspective = pie.legendPerspective = column.legendPerspective = 6;
		tp.activeTabBackColor = Color.ORANGE;
		tp.pressedColor = Color.YELLOW;

		tp.setActiveTab(0);
	}

	@Override
	public void reposition() {
		Rect r = shadeType.getRect();
		showTitle.setText("Shade");
		h3DSlider.setMinimum(-Settings.screenWidth/45);
		h3DSlider.setMaximum(Settings.screenWidth/45);
		v3DSlider.setMaximum(Settings.screenHeight/80);
		h3DSlider.setValue(h3DSlider.getValue()/2);
		v3DSlider.setValue(v3DSlider.getValue()/2);
		super.reposition();
		shadeType.setRect(r);
		showTitle.setText("Title");
	}

	@Override
	public void onEvent(Event e) {
		if (e.type == ControlEvent.PRESSED) {
			if (e.target == tp) {
				int sel = tp.getActiveTab();
				boolean onColumn = sel == 0;
				boolean onLine = sel == 1;
				boolean onPie = sel == 2;
				boolean onArc = sel == 3;

				is3D.setVisible(!onLine && !onArc);
				ver.setVisible(!onLine && !onArc);
				hor.setVisible(!onLine && !onArc);
				h3DSlider.setVisible(!onLine && !onArc);
				v3DSlider.setVisible(!onLine && !onArc);

				showCategories.setVisible(!onPie && !onArc);
				showYValues.setVisible(!onPie && !onArc);
				showHGrids.setVisible(!onPie && !onArc);
				showVGrids.setVisible(!onPie && !onArc);

				hasShade.setVisible(onColumn);
				shadeDirection.setVisible(onColumn);
				shadeType.setVisible(onColumn);

				if (onColumn) {
					v3DSlider.setMinimum(0);
					h3DSlider.setValue(column.perspectiveH);
					v3DSlider.setValue(column.perspectiveV);
				} else if (onPie) {
					v3DSlider.setMinimum(-6);
					h3DSlider.setValue(pie.perspectiveH);
					v3DSlider.setValue(pie.perspectiveV);
				}
			} else if (e.target instanceof Check || e.target instanceof ComboBox) {
				// if you click to select this Check
				if (e.target == hasLegend)
					legendPosition.setEnabled(hasLegend.isChecked());
				if (e.target == hasShade) {
					shadeDirection.setEnabled(hasShade.isChecked());
					shadeType.setEnabled(hasShade.isChecked());
				}
				if (e.target == is3D) {
					h3DSlider.setEnabled(is3D.isChecked());
					v3DSlider.setEnabled(is3D.isChecked());
				}

				pie.showTitle = line.showTitle = column.showTitle = arc.showTitle = showTitle.isChecked();
				pie.showLegend = line.showLegend = column.showLegend = arc.showLegend = hasLegend.isChecked();
				pie.legendPosition = line.legendPosition = column.legendPosition = arc.legendPosition = getLegendPosition();
				pie.showCategories = line.showCategories = column.showCategories = showCategories.isChecked();
				line.showHGrids = column.showHGrids = showHGrids.isChecked();
				line.showVGrids = column.showVGrids = showVGrids.isChecked();
				line.showYValues = column.showYValues = showYValues.isChecked();
				column.type = pie.type = (is3D.isChecked() ? Chart.IS_3D : 0)
						| ((getShapeDirection() != -1 && hasShade.isChecked()) ? getShapeDirection() : 0)
						| ((getShapeType() != -1 && hasShade.isChecked()) ? getShapeType() : 0);
				repaint();
			} else if (e.target == v3DSlider) {
				column.perspectiveV = Math.max(v3DSlider.getValue(), 0);
				pie.perspectiveV = v3DSlider.getValue();
				repaint();
			} else if (e.target == h3DSlider) {
				line.legendPerspective = pie.legendPerspective = column.legendPerspective = pie.perspectiveH = column.perspectiveH = h3DSlider
						.getValue();
				repaint();
			}
		}
	}

	private int getShapeType() {
		switch (shadeType.getSelectedIndex()) {
		case 1:
			return Chart.GRADIENT_INVERT;
		case 2:
			return Chart.GRADIENT_DARK;
		default:
			return -1;
		}
	}

	private int getShapeDirection() {
		switch (shadeDirection.getSelectedIndex()) {
		case 1:
			return Chart.GRADIENT_HORIZONTAL;
		case 2:
			return Chart.GRADIENT_VERTICAL;
		default:
			return -1;
		}
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
