package org.alvin.swing.chart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ColumnGraph extends Base2DGraph {

	private static final long serialVersionUID = 1L;
	/** ���е���״ͼ */
	protected LinkedList<ColumnBeanManager> columnList = new LinkedList<ColumnBeanManager>();
	/** ��״ͼ֮��ļ�� */
	protected double column_padding;
	//
	private ColumnBeanManagerPropertyChangeAction propertyChangeAction = new ColumnBeanManagerPropertyChangeAction();

	public ColumnGraph() {
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
			g2d.setColor(bean.getColor());
			g2d.fill3DRect((int) endX, (int) (h - y), (int) column_padding, (int) y, true);
			g2d.setColor(Color.black);
			// ��ֵ
			g2d.drawString(bean.getValue() + "", (int) endX, (int) (h - y - 5));
			// ������ 
			g2d.drawString("aa", (int) endX, (int) (h + 15));
			endX += column_padding;
		}
		return endX;
	}

	@Override
	public void setGraphPath(String graphPath) {
		super.setGraphPath(graphPath);
		try {
			readColumnPoint(graphPath);
			repaintImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readColumnPoint(String path) throws IOException {
		String line;
		String[] splits;
		RandomAccessFile file = new RandomAccessFile(new File(path), "r");
		Map<String, ColumnGraphPointBean> map = new HashMap<String, ColumnGraphPointBean>();
		this.columnList.clear();
		ColumnBeanManager manager = new ColumnBeanManager(colorManager);
		while ((line = file.readLine()) != null) {
			// �����ַ���
			splits = line.trim().split("\\s");
			if (splits == null || splits.length == 0) {
				continue;
			}
			if (map.containsKey(splits[0])) {
				map.get(splits[0]).setValue(Double.parseDouble(splits[2]));
			} else {
				ColumnGraphPointBean bean = new ColumnGraphPointBean();
				manager.addColumn(bean);
				bean.setValue(Double.parseDouble(splits[2]));
			}
		}
		this.columnList.add(manager);
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
