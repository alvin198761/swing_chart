package org.alvin.swing.chart;

import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;


public class Column3DGraph extends Base3DGraph {

	private static final long serialVersionUID = 1L;

	/** ���е���״ͼ */
	protected LinkedList<ColumnBeanManager> columnList = new LinkedList<ColumnBeanManager>();
	/** ��״ͼ֮��ļ�� */
	protected double column_padding;
	private ColumnBeanManagerPropertyChangeAction propertyChangeAction = new ColumnBeanManagerPropertyChangeAction();

	public Column3DGraph() {
		createDefault();
	}

	private void createDefault() {
		ColumnBeanManager manager;
		ColumnGraphPointBean bean;

		// ////////////////
		manager = this.addColumnManager();
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(30d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(34d);
		bean.setSelect(true);
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(34d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(36d);
		bean.setSelect(true);

		// ////////////////
		manager = this.addColumnManager();

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(67d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(67d);
		bean.setSelect(true);
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(63d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(17d);
		bean.setSelect(true);

		// ////////////////
		manager = this.addColumnManager();
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(27d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(87d);
		bean.setSelect(true);
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(47d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(100d);
		bean.setSelect(true);
	}

	@Override
	protected void calculateX() {
		// ���ٸ�����
		int size = columnList.size();
		// �ܹ����ٸ���״
		for (ColumnBeanManager manager : columnList) {
			size += manager.getColumnSize();
		}
		column_padding = (double) w / size;
	}

	@Override
	protected void calculateY() {
		double max = 0;
		for (ColumnBeanManager manager : columnList) {
			if (max < manager.getMax_value()) {
				max = manager.getMax_value();
			}
		}
		setMax_Yvalue((int) max);
		// �������ֵ�������
		int num = h / v_p;
		if (num <= 0) {
			return;
		}
		vnum = 1;
		while (num * vnum < max_Yvalue) {
			vnum++;
		}
	}

	public ColumnBeanManager addColumnManager() {
		ColumnBeanManager manager = new ColumnBeanManager(colorManager);
		columnList.add(manager);
		manager.addPropertyChangeListener(propertyChangeAction);

		calculateX();
		calculateY();
		return manager;
	}

	public void removeColumnManager(ColumnBeanManager manager) {
		columnList.remove(manager);
		manager.removeAllColumn();
		manager.removePropertyChangeListener(propertyChangeAction);

		calculateX();
		calculateY();
	}

	@Override
	protected void drawGraph(Graphics2D g2d) {
		double endX = x;
		for (ColumnBeanManager manager : columnList) {
			endX += column_padding;
			endX = drawManager(g2d, manager, endX);
		}
	}

	private double drawManager(Graphics2D g2d, ColumnBeanManager manager, double endX) {
		for (ColumnGraphPointBean bean : manager.getColumnList()) {
			// ����yֵ
			double s = (double) vnum / v_p;
			double y = bean.getValue() / s;
			// g2d.setColor(bean.getColor());
			Column3D col = Column3D.createColumn3D((int) (endX - lw), (int) (by - y), (int) column_padding, (int) y, (int) lw, bean.getColor());
			col.draw(g2d);
			// g2d.fill3DRect((int) (endX - lw), (int) (by - y), (int) column_padding, (int) y, true);
			endX += column_padding;
		}
		return endX;
	}

	@Override
	protected void drawV_mark(Graphics2D g2d, int fw, int fh) {
		// ��������
	}

	class ColumnBeanManagerPropertyChangeAction implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			ColumnBeanManager manager = (ColumnBeanManager) evt.getSource();
			if (max_Yvalue < manager.getMax_value()) {
				max_Yvalue = (int) manager.getMax_value();
				calculateY();
			}
		}
	}

}
