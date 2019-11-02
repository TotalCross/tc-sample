package totalcross.sample.components.ui.charts.gauge;

import totalcross.sample.components.ui.charts.AnimationFunctions;
import totalcross.ui.Container;
import totalcross.ui.MainWindow;
import totalcross.ui.event.UpdateListener;
import totalcross.ui.font.Font;

public class GaugeSample extends Container {

    UpdateListener updateListener;

    @Override
    public void initUI() {
        int Color1 = 0x2ECC40;
        int Color2 = 0xFFDC00;
        int Color3 = 0xFF4136;

        Gauge energyGauge = new EnergyGauge(0, 80, 180);
        energyGauge.section(80, Color1);
        energyGauge.section(80, Color2);
        energyGauge.section(80, Color3);
        energyGauge.setMajorTickInterval(20);
        energyGauge.setMinorTickInterval(5);
        energyGauge.setValueLabelFont(Font.getFont(25));
        energyGauge.setValueSuffix(" °C");
        energyGauge.setForeColor(0x0);
        energyGauge.setValue(60);

        add(energyGauge, CENTER, TOP, PARENTSIZE + 33, PARENTSIZE);

        Gauge stylishSectionsGauge = new StylishSectionsGauge(0, 180, 270);
        stylishSectionsGauge.section(80, Color1);
        stylishSectionsGauge.section(80, Color2);
        stylishSectionsGauge.section(80, Color3);
        stylishSectionsGauge.setValueLabelFont(Font.getFont(25));
        stylishSectionsGauge.setValueSuffix(" RPM");
        stylishSectionsGauge.setForeColor(0x0);
        stylishSectionsGauge.setValue(80);

        add(stylishSectionsGauge, LEFT, SAME, FIT, SAME);

        Gauge storageGauge = new StorageGauge(0, 180, 270);

        storageGauge.setForeColor(0xF35C00);
        storageGauge.showTickValues = true;
        storageGauge.showTicks = true;
        storageGauge.showTickValues(false);
        storageGauge.setValue(80);

        add(storageGauge, AFTER, SAME, FILL, SAME, energyGauge);

        updateListener = new UpdateListener() {
            AnimationFunctions af = new AnimationFunctions();
            int rpmTotalTime = 3000;
            int currentime = 0;

            int count = -1;

            @Override
            public void updateListenerTriggered(int elapsedMilliseconds) {
                currentime += elapsedMilliseconds;

                if (++count % 3 == 0) {
                    double ratio = rpmAnimationFunction(currentime % rpmTotalTime, rpmTotalTime);
                    stylishSectionsGauge.setValue((int) ((stylishSectionsGauge.max - stylishSectionsGauge.min) * ratio)
                            - stylishSectionsGauge.min);
                    stylishSectionsGauge.repaintNow();

                    energyGauge.setValue((int) ((energyGauge.max - energyGauge.min) * ratio) - energyGauge.min);
                    energyGauge.repaintNow();
                }
            }

            public double rpmAnimationFunction(int time, int totalTime) {
                double ratio = time / (double) totalTime;
                if (ratio < .30) {
                    ratio = ratio / 0.30;
                    return af.easeOutQuad(ratio);
                } else {
                    ratio = (ratio - 0.30) / 0.70;
                    return 1.0 - af.easeOutSine(ratio);
                }
            }
        };
        MainWindow.getMainWindow().addUpdateListener(updateListener);
    }

    @Override
    protected void onRemove() {
        MainWindow.getMainWindow().removeUpdateListener(updateListener);
    }
}
