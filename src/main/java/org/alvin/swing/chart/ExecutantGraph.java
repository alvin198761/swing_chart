package org.alvin.swing.chart;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Iterator;

public class ExecutantGraph extends LineGraph {

	private static final long serialVersionUID = 1L;

	public ExecutantGraph() {
		createDefault();
	}

	private void createDefault() {
		LineGraphPointBean bean = this.addLine();
		bean.addPoint(10);
		bean.addPoint(20);
		bean.addPoint(30);
		bean.addPoint(40);
		bean.addPoint(30);
		bean.addPoint(20);
		bean.addPoint(10);
		bean.setSelect(true);

	}

	@Override
	protected void drawGraph(Graphics2D g2d) {
		// ����
		int px = 0;
		int pr = 0;
		double x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		double pvalue = 0, value = 0, s = 0;
		LineGraphPointBean bean;
		Iterator<LineGraphPointBean> it = lineBeanList.iterator();
		float lineWidth;
		while (it.hasNext()) {
			bean = it.next();
			// ���û���
			if (bean.select) {
				pr = POINT_MAX_SIZE >> 1;
				g2d.setStroke(BOLD_STROKE);
			} else {
				pr = POINT_MINI_SIZE >> 1;
				g2d.setStroke(DEFAULT_STROKE);
			}
			// �����ߵĿ��
			lineWidth = ((BasicStroke) g2d.getStroke()).getLineWidth() / 2;
			// ��ɫ
			g2d.setColor(bean.color);
			// ��λֵ�ȵ�λ����
			s = (double) v_p / vnum;
			// ����
			for (int j = 0; j < bean.points.size(); j++) {
				value = bean.points.get(j);
				pvalue = h - s * value - pr;
				// ��������λ��
				px = x + h_p * (j) - pr;
				// ���û���
				if (bean.select) {
					g2d.fill(new Ellipse2D.Double(px, pvalue, POINT_MAX_SIZE, POINT_MAX_SIZE));
				} else {
					g2d.fill(new Ellipse2D.Double(px, pvalue, POINT_MINI_SIZE, POINT_MINI_SIZE));
				}
				// ����
				if (j == 0) {
					x1 = x;
					y1 = h;
					x2 = px + pr + lineWidth + h_p;
					y2 = pvalue + pr + lineWidth;
					// ������
					g2d.draw(new Line2D.Double(x1, y2, x2, y2));
					continue;
				}

				x1 = x2;
				y1 = y2;
				x2 = px + pr + lineWidth + h_p;
				y2 = pvalue + pr + lineWidth;
				// ������
				g2d.draw(new Line2D.Double(x1, y1, x1, y2));
				g2d.draw(new Line2D.Double(x1, y2, x2, y2));
				x1 = x2;
				y1 = y2;
			}
		}
	}

}
