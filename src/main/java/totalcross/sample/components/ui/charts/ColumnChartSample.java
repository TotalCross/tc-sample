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
import totalcross.ui.chart.ColumnChart;
import totalcross.ui.chart.Series;
import totalcross.ui.gfx.Color;
import totalcross.util.UnitsConverter;

public class ColumnChartSample extends ScrollContainer {

	private ColumnChart column;
	private Container menu, options;
	private final int gap = UnitsConverter.toPixels(5 + DP);

	private Slider h3DSlider, v3DSlider;
	private Label hor, ver;
	private Check is3D, hasLegend, hasShade, showTitle, showCategories, showHGrids, showVGrids, showYValues;
	private ComboBox legendPosition, shadeDirection, shadeType;

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

		String[] names = { "Jan", "Feb", "Mar" };
		column = new ColumnChart(names);
		column.series.addElement(new Series("Rice", new double[] { 1000, 1020, 1040 }, color1));
		column.series.addElement(new Series("Beans", new double[] { 850, 755, 859 }, color2));
		column.series.addElement(new Series("Oil", new double[] { 930, 837, 943 }, color3));

		column.setTitle("Sales Projection");
		column.setYAxis(0, 1100, 11);
		column.type = Chart.IS_3D;

		hasLegend = new Check("Legend");
		legendPosition = new ComboBox(new String[] { "Right", "Left", "Top", "Bottom" });
		legendPosition.setSelectedIndex(0);
		legendPosition.setEnabled(false);
		legendPosition.setForeColor(Colors.P_700);

		hasShade = new Check("Shade");
		shadeDirection = new ComboBox(new String[] { "Direction", "Horizontal", "Vertical" });
		shadeDirection.setSelectedIndex(1);
		shadeDirection.setEnabled(false);
		shadeDirection.setForeColor(Colors.P_700);

		shadeType = new ComboBox(new String[] { "Type", "Inverted", "Dark" });
		shadeType.setSelectedIndex(2);
		shadeType.setEnabled(false);
		shadeType.setForeColor(Colors.P_700);

		is3D = new Check("3D");
		is3D.setChecked(true);
		showTitle = new Check("Shade");

		showCategories = new Check("Category");
		showHGrids = new Check("HGrids");
		showVGrids = new Check("VGrids");
		showYValues = new Check("YValues");

		options.add(showTitle, LEFT, TOP);
		options.add(hasLegend, SAME, AFTER);		
		options.add(hasShade, SAME, AFTER, hasLegend);

		
		showTitle.setText("Title");

		options.add(is3D, SAME, AFTER + gap * 2, hasShade);

		hor = new Label("H:");
		ver = new Label("V:");


		options.add(showCategories, AFTER + gap, TOP, hasLegend);
		
		options.add(showYValues, SAME, AFTER + gap, SAME, SAME,showCategories);
		
		
		
		options.add(showHGrids, SAME, AFTER + gap, SAME, SAME);
		options.add(showVGrids, SAME, AFTER, SAME, SAME);

		options.add(shadeDirection, RIGHT, SAME, PREFERRED, PREFERRED, showCategories);
		
		options.add(legendPosition, SAME, AFTER, FILL, SAME, shadeDirection);
		
		options.add(shadeType, SAME, AFTER, FILL, SAME, legendPosition);

		
		options.add(hor, LEFT + gap*4, AFTER, hasShade.getWidth() - is3D.getWidth(), PREFERRED, shadeType);
		options.add(ver, SAME, AFTER, hasShade.getWidth() - is3D.getWidth(), PREFERRED);

		
		options.add(h3DSlider = new Slider(), AFTER, CENTER_OF, FILL, PREFERRED, hor);
		options.add(v3DSlider = new Slider(), AFTER, CENTER_OF, FILL, PREFERRED, ver);
		options.setInsets(gap / 4, gap / 4, gap / 4, gap / 4);
		options.resizeHeight();
		menu.resizeHeight();

		h3DSlider.setMinimum(-Settings.screenWidth / 45);
		h3DSlider.setMaximum(Settings.screenWidth / 45);
		v3DSlider.setMaximum(Settings.screenHeight / 80);
		h3DSlider.drawTicks = v3DSlider.drawTicks = true;
		h3DSlider.drawFilledArea = v3DSlider.drawFilledArea = false;
		v3DSlider.setValue(column.perspectiveV);
		h3DSlider.setValue(column.perspectiveH);
		h3DSlider.setLiveScrolling(true);
		v3DSlider.setLiveScrolling(true);
		h3DSlider.sliderColor = Colors.ON_SURFACE;
		v3DSlider.sliderColor = Colors.ON_SURFACE;

		int bg = Color.darker(backColor, 16);
		column.setBackColor(bg);
		add(column, LEFT + gap * 3 / 2, AFTER, FILL - gap * 3 / 2, FILL - gap * 3 / 2);

		hasLegend.addPressListener(e -> {
			if (hasLegend.isChecked())
				legendPosition.setForeColor(Colors.ON_SURFACE);
			else
				legendPosition.setForeColor(Colors.P_700);
			legendPosition.setEnabled(hasLegend.isChecked());
			repaint();
		});

		showTitle.addPressListener(e -> {
			column.showTitle = showTitle.isChecked();
			repaint();
		});

		hasLegend.addPressListener(e -> {
			column.showLegend = hasLegend.isChecked();
			repaint();
		});

		legendPosition.addPressListener(e -> {
			column.legendPosition = getLegendPosition();
			repaint();
		});

		hasShade.addPressListener(e -> {
			int color = hasShade.isChecked() ? Colors.ON_SURFACE : Colors.P_700;
			shadeDirection.setForeColor(color);
			shadeType.setForeColor(color);
			column.type = (is3D.isChecked() ? Chart.IS_3D : 0)
					| ((getShapeDirection() != -1 && hasShade.isChecked()) ? getShapeDirection() : 0)
					| ((getShapeType() != -1 && hasShade.isChecked()) ? getShapeType() : 0);
			shadeDirection.setEnabled(hasShade.isChecked());
			shadeType.setEnabled(hasShade.isChecked());
			repaint();
		});

		shadeDirection.addPressListener(e -> {
			column.type = (is3D.isChecked() ? Chart.IS_3D : 0)
					| ((getShapeDirection() != -1 && hasShade.isChecked()) ? getShapeDirection() : 0)
					| ((getShapeType() != -1 && hasShade.isChecked()) ? getShapeType() : 0);
			repaint();
		});

		shadeType.addPressListener(e -> {
			column.type = (is3D.isChecked() ? Chart.IS_3D : 0)
					| ((getShapeDirection() != -1 && hasShade.isChecked()) ? getShapeDirection() : 0)
					| ((getShapeType() != -1 && hasShade.isChecked()) ? getShapeType() : 0);
			repaint();
		});

		showCategories.addPressListener(e -> {
			column.showCategories = showCategories.isChecked();
			repaint();
		});

		showHGrids.addPressListener(e -> {
			column.showHGrids = showHGrids.isChecked();
			repaint();
		});

		showVGrids.addPressListener(e -> {
			column.showVGrids = showVGrids.isChecked();
			repaint();
		});

		showYValues.addPressListener(e -> {
			column.showYValues = showYValues.isChecked();
			repaint();
		});

		is3D.addPressListener(e -> {
			column.type = (is3D.isChecked() ? Chart.IS_3D : 0)
					| ((getShapeDirection() != -1 && hasShade.isChecked()) ? getShapeDirection() : 0)
					| ((getShapeType() != -1 && hasShade.isChecked()) ? getShapeType() : 0);
			v3DSlider.setEnabled(is3D.isChecked());
			h3DSlider.setEnabled(is3D.isChecked());
			repaint();
		});

		v3DSlider.addPressListener(e -> {
			column.perspectiveV = Math.max(v3DSlider.getValue(), 0);
			repaint();
		});

		h3DSlider.addPressListener(e -> {
			column.legendPerspective = column.perspectiveH = h3DSlider.getValue();
			repaint();
		});
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
