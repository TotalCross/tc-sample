package totalcross.sample.components.ui;

import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Control;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.TimerEvent;
import totalcross.ui.event.TimerListener;
import totalcross.ui.event.UpdateListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.gfx.Graphics;

public class GraphicsSample extends ScrollContainer {
	@Override
	public void onPaint(Graphics g) {
		int solidWhite = 0xFFFFFFFF;
		int solidBlack = 0xFF000000;
		int solidRed = 0xFFFF0000;
		int solidGreen = 0xFF00FF00;
		int solidBlue = 0xFF0000FF;
		int d = (int)Settings.screenDensity;
		
        // lines and N-line
		g.foreColor = Color.BLACK;
		g.drawLine((int)(20 * d), (int)(100 * d), (int)(60 * d), (int)(20 * d));
		
		g.foreColor = Color.RED;
		g.drawLines((int)(40 * d), (int)(100 * d), (int)(80 * d), (int)(20 * d), (int)(60 * d), (int)(100 * d), (int)(100 * d), (int)(20 * d));
		
		g.drawThickLine((int)(80 * d), (int)(100 * d), (int)(120 * d), (int)(20 * d), (int)(5 * d));
		
		// Outer rects
		g.foreColor = Color.BLACK;
		g.drawRect((int)(10 * d), (int)(10 * d), (int)(130 * d), (int)(110 * d));
		g.drawDottedRect((int)(5 * d), (int)(5 * d), (int)(140 * d), (int)(120 * d));
		
		// Three thin rects
		int cornerRadius = (int)(10 * d);
		g.drawRoundGradient((int)(160 * d), (int)(20 * d), (int)(180 * d), (int)(120 * d), cornerRadius, cornerRadius, cornerRadius, cornerRadius, solidBlue, solidGreen, true);
		g.drawRoundGradient((int)(190 * d), (int)(20 * d), (int)(240 * d), (int)(55 * d), cornerRadius, cornerRadius, cornerRadius, cornerRadius, solidBlue, solidGreen, false);
		g.drawRoundGradient((int)(190 * d), (int)(65 * d), (int)(240 * d), (int)(120 * d), cornerRadius, cornerRadius, cornerRadius * 2, cornerRadius * 2, solidBlue, solidGreen, true);
		
		g.backColor = Color.RED;
		g.fillRect((int)(250 * d), (int)(20 * d), (int)(20 * d), (int)(100 * d));
		
		g.backColor = Color.GREEN;
		g.foreColor = Color.BLUE;
		g.fillRoundRect((int)(280 * d), (int)(20 * d), (int)(20 * d), (int)(100 * d), (int)(10 * d));
		
		
		// Dots
		g.drawDots((int)(20 * d), (int)(135 * d), (int)(250 * d), (int)(130 * d));
		g.drawDots((int)(20 * d), (int)(130 * d), (int)(250 * d), (int)(135 * d));
		
		// Shaded rects
		g.fillShadedRect((int)(20 * d), (int)(140 * d), (int)(20 * d), (int)(20 * d), false, false, solidGreen, solidBlue, 100);
		g.fillShadedRect((int)(50 * d), (int)(140 * d), (int)(20 * d), (int)(20 * d), false, true, solidGreen, solidBlue, 100);
		g.fillShadedRect((int)(80 * d), (int)(140 * d), (int)(20 * d), (int)(20 * d), true, false, solidGreen, solidBlue, 100);
		g.fillShadedRect((int)(110 * d), (int)(140 * d), (int)(20 * d), (int)(20 * d), true, true, solidGreen, solidBlue, 100);

		g.fillShadedRect((int)(140 * d), (int)(140 * d), (int)(20 * d), (int)(20 * d), true, true, solidBlack, solidWhite, 100);
		g.fillShadedRect((int)(170 * d), (int)(140 * d), (int)(20 * d), (int)(20 * d), true, true, solidBlack, solidWhite, 80);
		g.fillShadedRect((int)(200 * d), (int)(140 * d), (int)(20 * d), (int)(20 * d), true, true, solidBlack, solidWhite, 60);
		g.fillShadedRect((int)(230 * d), (int)(140 * d), (int)(20 * d), (int)(20 * d), true, true, solidBlack, solidWhite, 40);
		
		// Manual Dots
		for (int i = (int)(20 * d); i < (int)(250 * d); i += (int)(4 * d)) {
			g.setPixel(i, (int)(165 * d));
		}

		Points c;
		c = generateStar((int)(50 * d), (int)(200 * d), (int)(30 * d), 5);
		g.drawPolygon(c.x, c.y, c.length());
		
		c = generateStar((int)(80 * d), (int)(200 * d), (int)(30 * d), 4);
		g.drawPolyline(c.x, c.y, c.length());
		
		c = generateStar((int)(130 * d), (int)(200 * d), (int)(30 * d), 6);
		g.backColor = Color.RED;
		g.fillPolygon(c.x, c.y, c.length());
		
		c = generateStar((int)(190 * d), (int)(200 * d), (int)(30 * d), 7);
		g.backColor = Color.GREEN;
		g.foreColor = Color.BLUE;
		g.fillPolygonGradient(c.x, c.y, c.length());
		
		c = generateStar((int)(240 * d), (int)(200 * d), (int)(30 * d), 15);
		g.fillPolygonGradient(c.x, c.y, c.length());
		
		
		// Elipses
		g.backColor = Color.RED;
		g.foreColor = Color.BLACK;
		g.drawEllipse((int)(80 * d), (int)(280 * d), (int)(40 * d), (int)(20 * d));
		
		g.foreColor = Color.RED;
		g.drawEllipticalArc((int)(80 * d), (int)(280 * d), (int)(55 * d), (int)(30 * d), -60, 60);
		g.drawEllipticalArc((int)(80 * d), (int)(280 * d), (int)(55 * d), (int)(30 * d), 120, 240);
		g.drawEllipticalPie((int)(80 * d), (int)(280 * d), (int)(45 * d), (int)(27 * d), 190, 210);
		g.drawCircle((int)(80 * d), (int)(280 * d), (int)(15 * d));
		
		g.backColor = Color.RED;
		g.foreColor = Color.WHITE;
		g.fillEllipse((int)(150 * d), (int)(280 * d), (int)(40 * d), (int)(20 * d));
		g.fillEllipseGradient((int)(200 * d), (int)(280 * d), (int)(40 * d), (int)(20 * d));
		g.fillEllipticalPie((int)(200 * d), (int)(280 * d), (int)(20 * d), (int)(40 * d), 250, 290);
		g.fillEllipticalPieGradient((int)(200 * d), (int)(280 * d), (int)(40 * d), (int)(30 * d), 70, 110);
		g.fillEllipticalPieGradient((int)(200 * d), (int)(280 * d), (int)(40 * d), (int)(30 * d), 120, 210);
		g.fillEllipticalPieGradient((int)(220 * d), (int)(290 * d), (int)(40 * d), (int)(30 * d), 280, 350);

		
		g.backColor = Color.BLACK;
		g.fillCircle((int)(200 * d), (int)(280 * d), (int)(15 * d));
		g.fillCircleGradient((int)(250 * d), (int)(280 * d), (int)(15 * d));
		
		g.foreColor = Color.BLACK;
		g.drawText("Texto", (int)(50 * d), (int)(320 * d));
		g.drawText("Texto", (int)(120 * d), (int)(320 * d), (int)(80 * d));
		g.drawText("Sombra", (int)(220 * d), (int)(320 * d), true, Color.RED);
		g.drawVerticalText("Áâó~ç`´^x", (int)(280 * d), (int)(130 * d));
		
		g.foreColor = Color.BLACK;
		g.backColor = Color.BLUE;
	}
	
	private class Points {
		public int[] x;
		public int[] y;
		public Points(int n) {
			x = new int[n];
			y = new int[n];
		}
		public int length() {
			return x.length;
		}
	}
	private Points generateStar(int cx, int cy, int r, int steps) {
		steps *= 2;
		
		Points p = new Points(steps);
		int step = 360 / steps;
		for (int i = 0; i < steps; i++) {
			double angle = Math.toRadians(i * step);
			double radius = i % 2 == 0 ? r : r / 2;
			
			p.x[i] = cx + (int)(Math.cos(angle) * radius);
			p.y[i] = cy + (int)(Math.sin(angle) * radius);
		}

		return p;
	}
}
