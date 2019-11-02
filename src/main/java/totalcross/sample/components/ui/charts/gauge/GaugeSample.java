package totalcross.sample.components.ui.charts.gauge;


import totalcross.ui.Container;
import totalcross.ui.font.Font;
import totalcross.util.UnitsConverter;

public class GaugeSample extends Container {
    public GaugeSample() {

    }

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
        
        add(energyGauge, CENTER, TOP, PARENTSIZE + 50, PARENTSIZE + 50);

        Gauge stylishSectionsGauge = new StylishSectionsGauge(0, 180, 270);
        stylishSectionsGauge.section(80, Color1);
        stylishSectionsGauge.section(80, Color2);
        stylishSectionsGauge.section(80, Color3);
        stylishSectionsGauge.setValueLabelFont(Font.getFont(25));
        stylishSectionsGauge.setValueSuffix("RPM");
        stylishSectionsGauge.setForeColor(0x0);
        stylishSectionsGauge.setValue(80);

        add(stylishSectionsGauge, LEFT, AFTER, PARENTSIZE + 50, PARENTSIZE + 50);

        Gauge storageGauge = new StorageGauge(0, 180, 270);

        storageGauge.setForeColor(0xF35C00);
        storageGauge.showTickValues = true;
        storageGauge.showTicks = true;
        storageGauge.showTickValues(false);
        storageGauge.setValue(80);

        add(storageGauge, RIGHT, SAME, PARENTSIZE + 50, PARENTSIZE + 50);
    }
}
