package org.alvin.swing.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;

public class Column3D extends BaseObject {

	private static final long serialVersionUID = 1L;

	private GeneralPath topRect;
	private Rectangle centerRect;
	private GeneralPath rightRect;

	private Color color;

	public static Column3D createColumn3D(int x, int y, int w, int h, int z, Color color) {
		Column3D col = new Column3D();
		col.color = color;
		Rectangle rect = new Rectangle();
		rect.x = x;
		rect.y = y;
		rect.width = w;
		rect.height = h;
		// ��ͼ��
		col.centerRect = rect;
		// �ϱߵ�ͼ��
		GeneralPath path = new GeneralPath();
		path.moveTo(x, y);
		path.lineTo(x + z, y - z);
		path.lineTo(x + w + z, y - z);
		path.lineTo(rect.x + rect.width, rect.y);
		path.lineTo(x, y);
		path.closePath();
		col.topRect = path;
		// �ұߵ�ͼ��
		path = new GeneralPath();
		path.moveTo(rect.x + rect.width, rect.y);
		path.lineTo(x + w + z, y - z);
		path.lineTo(x + w + z, rect.y + rect.height - z);
		path.lineTo(x + w, rect.y + rect.height);
		path.closePath();
		col.rightRect = path;
		return col;

	}

	private Column3D() {

	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fill(centerRect);
		g2d.setColor(color.darker());
		g2d.fill(topRect);
		g2d.fill(rightRect);
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(0.5f));
		g2d.draw(centerRect);
		g2d.draw(topRect);
	}

}
