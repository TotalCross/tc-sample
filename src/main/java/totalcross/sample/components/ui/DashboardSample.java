package totalcross.sample.components.ui;

import totalcross.sample.components.ui.charts.column.DashboardColumnChart;
import totalcross.sample.components.ui.charts.donut.DonutChart;
import totalcross.sample.components.ui.charts.donut.Section;
import totalcross.sample.components.ui.charts.gauge.GaugeSample;
import totalcross.ui.*;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Rect;
import totalcross.ui.layout.HBox;
import totalcross.ui.layout.LinearBox;
import totalcross.ui.layout.VBox;
import totalcross.util.UnitsConverter;

public class DashboardSample extends Container {

    @Override
    public void initUI() {
        this.setBackColor(Color.getRGB(24, 28, 40));

        add(new Container() {
            @Override
            public void initUI() {

            }

        }, LEFT, TOP, PARENTSIZE, DP + 8);

        add(new Container() {
            @Override
            public void initUI() {
                final int GAP = UnitsConverter.toPixels(DP + 8);

                Spacer center = new Spacer();
                add(center, CENTER, CENTER - GAP * 2);

                add(new Section("Gauge", new GaugeSample()), RIGHT - GAP, TOP, PARENTSIZE + 60, FIT - GAP);
                add(new Section("Donut", new DonutChart()), LEFT + GAP, SAME, FIT - GAP, SAME);

                add(new Section("Quality Metrics", new Container() {
                    @Override
                    public void initUI() {
                        setBackColor(getParent().getBackColor());
                        HBox allBox = new HBox();
                        allBox.transparentBackground = true;
                        allBox.setSpacing(UnitsConverter.toPixels(DP + 12));
                        allBox.setLayout(LinearBox.LAYOUT_STACK_CENTER, LinearBox.LAYOUT_STACK_CENTER);

                        LinearBox lbBox1 = createBox(false);
                        lbBox1.add(new Control[]{
                                createLb("MAF"),
                                createLb("MHF"),
                                createLb("MIF"),
                                createLb("AIF"),
                                createLb("CF"),
                        });

                        LinearBox valueBox1 = createBox(false);
                        valueBox1.add(new Control[]{
                                createValLb("13.3%"),
                                createValLb("84.1%"),
                                createValLb("63.1%"),
                                createValLb("62.6%"),
                                createValLb("2.8%"),
                        });

                        LinearBox lbBox2 = createBox(false);
                        lbBox2.add(new Control[]{
                                createLb("CBO"),
                                createLb("LCOM"),
                                createLb("DOIH"),
                                createLb("LOC"),
                                createLb("ESPQ"),

                        });

                        LinearBox valueBox2 = createBox(false);
                        valueBox2.add(new Control[]{
                                createValLb("33%"),
                                createValLb("20%"),
                                createValLb("67.07%"),
                                createValLb("40.13%"),
                                createValLb("8%"),
                        });
                        Spacer s = new Spacer();
                        s.setRect(new Rect(0, 0, UnitsConverter.toPixels(DP + 24), 1));
                        allBox.add(new Control[]{lbBox1, valueBox1,  s,lbBox2, valueBox2});
                        add(allBox, LEFT, TOP, PARENTSIZE, PARENTSIZE);

                    }

                    public LinearBox createBox(boolean isHBox) {
                        LinearBox lbox = isHBox? new HBox(): new VBox();
                        lbox.setLayout(VBox.LAYOUT_DISTRIBUTE, VBox.ALIGNMENT_LEFT);
                        lbox.setSpacing(UnitsConverter.toPixels(DP + 8));
                        return lbox;
                    }

                    public Label createLb(String text) {
                        Font lbFont = Font.getFont(12);
                        Label l = new Label(text);
                        l.transparentBackground = true;
                        l.setFont(lbFont);
                        l.setForeColor(Color.BRIGHT);
                        return  l;
                    }

                    public Label createValLb(String text) {
                        Font valueFont = Font.getFont("Lato Bold", false,13);
                        Label l = new Label(text);
                        l.transparentBackground = true;
                        l.setForeColor(Color.GREEN);
                        l.setFont(valueFont);
                        return  l;
                    }


                }), RIGHT - GAP, AFTER, PARENTSIZE + 30, FILL - GAP, center);
                add(new Section("Columns", new DashboardColumnChart()), LEFT + GAP, SAME, FIT - GAP, SAME);

            }
        }, LEFT, AFTER, FILL, FILL);
    }

}