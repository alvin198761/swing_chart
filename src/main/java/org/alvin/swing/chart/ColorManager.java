package org.alvin.swing.chart;

import java.awt.Color;
import java.util.LinkedList;

public class ColorManager {

	private LinkedList<Color> colorList = new LinkedList<Color>();

	public ColorManager() {
		init();
	}

	private void init() {
		int i = 0;
		int num = 0;
		Color blank = null;
		Color white = null;
		for (int r = 255; r >= 0; r -= 55) {
			for (int b = 255; b >= 0; b -= 55) {
				for (int g = 255; g >= 0; g -= 55) {
					i++;
					if (i < 5) {
						if (r == 255 && g == 255 && b == 255) {
							blank = new Color(r, g, b);
							colorList.add(blank);
							continue;
						}
						colorList.add(new Color(r, g, b));
						continue;
					} else {
						if (r == 0 && g == 0 && b == 0) {
							white = new Color(r, g, b);
							colorList.add(i % 5 + num, white);
							num++;
							continue;
						}
						colorList.add(i % 5 + num, new Color(r, g, b));
						num++;
					}
				}
			}
		}
		colorList.remove(white);
		colorList.remove(blank);
		colorList.removeFirst();
		colorList.removeFirst();
	}

	public Color getRGB() {
		if (colorList.size() == 0) {
			init();
		}
		return colorList.removeFirst();
	}

	public void setRGB(Color rgb) {
		colorList.addLast(rgb);
	}
}
