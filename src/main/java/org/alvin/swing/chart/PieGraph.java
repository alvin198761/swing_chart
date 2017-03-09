package org.alvin.swing.chart;

import org.alvin.swing.chart.bean.PieGraphPointBean;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

public class PieGraph extends Base2DGraph {

	private static final long serialVersionUID = 1L;
	/** ԲȦ��ֱ�������ı��� */
	protected static final float R_SCALE = .95f;
	private PieGraphBeanPropertyChangeAction propertyChangeAction = new PieGraphBeanPropertyChangeAction();
	/** ͼ�����Ķ��� */
	protected LinkedList<PieGraphPointBean> pieList = new LinkedList<PieGraphPointBean>();
	/** ֱ�� */
	protected double r = 0;
	/** ��� x */
	double startX = 0;
	/** ��� y */
	double startY = 0;

	public PieGraph() {
		createDefault();
	}

	private void createDefault() {
		PieGraphPointBean bean = this.addPie();
		bean.setValue(10);

		bean = this.addPie();
		bean.setValue(20);

		bean = this.addPie();
		bean.setValue(30);

		bean = this.addPie();
		bean.setValue(40);
	}

	@Override
	protected void drawH_mark(Graphics2D g2d, int fw, int fh) {
		// �����̶�
	}

	@Override
	protected void drawV_mark(Graphics2D g2d, int fw, int fh) {
		// �����̶�
	}

	public PieGraphPointBean addPie() {
		PieGraphPointBean bean = new PieGraphPointBean();
		pieList.add(bean);
		bean.addPropertyChangeListener(propertyChangeAction);
		bean.setColor(colorManager.getRGB());
		return bean;
	}

	public void removePie(PieGraphPointBean bean) {
		colorManager.setRGB(bean.getColor());
		pieList.remove(bean);
		bean.removePropertyChangeListener(propertyChangeAction);
	}

	@Override
	protected void calculateX() {

	}

	@Override
	protected void calculateY() {
		// �������
		double sum = 0;
		for (PieGraphPointBean bean : pieList) {
			sum += bean.getValue();
		}
		// ���������
		PieGraphPointBean bean;
		for (int i = 0; i < pieList.size(); i++) {
			bean = pieList.get(i);
			bean.setScale(bean.getValue() / sum);
			if (i < pieList.size() - 1) {
				pieList.get(i + 1).setEnd(bean.getScale() * 360 + bean.getEnd());
			}
		}
	}

	@Override
	protected void componentPropertyChanger() {
		double num = Math.min(w, h);
		double value = num * R_SCALE;
		r = value;
		startX = x + (w - r) / 2;
		startY = (h - r) / 2;
	}

	@Override
	protected void drawGraph(Graphics2D g2d) {
		Shape shape;
		for (PieGraphPointBean bean : pieList) {
			g2d.setColor(bean.getColor());
			shape = new Arc2D.Double(startX, startY, r, r, bean.getEnd(), bean.getScale() * 360, Arc2D.PIE);
			g2d.fill(shape);
			g2d.setColor(Color.white);
			g2d.draw(shape);
			g2d.setColor(Color.black);
			//g2d.drawString("aa", (float) startX, (float) startY);
		}
	}

	class PieGraphBeanPropertyChangeAction implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String name = evt.getPropertyName();
			if (name.equals(PieGraphPointBean.PROPERTY_VALUE)) {
				calculateY();
			}
		}
	}

}
