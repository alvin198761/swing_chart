package org.alvin.swing.chart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.LinkedList;

public class ColumnBeanManager extends BaseObject {

	private static final long serialVersionUID = 1L;
	public static final String PROPERTY_COLUMNLIST = "columnList";
	public static final String PROPERTY_MAX_VALUE = "max_value";
	protected ColorManager colorManager;
	/** һ��������Ҫ��������״ͼ **/
	protected LinkedList<ColumnGraphPointBean> columnList = new LinkedList<ColumnGraphPointBean>();

	public ColumnBeanManager(ColorManager colorManager) {
		this.colorManager = colorManager;
	}

	public LinkedList<ColumnGraphPointBean> getColumnList() {
		return columnList;
	}

	/** ���yֵ */
	protected double max_value = 0;
	/** ����ֵ�ı�����Ϣ */
	private ColumnPropertyChagerAction propertyChangerAction = new ColumnPropertyChagerAction();

	public void addColumn(ColumnGraphPointBean column) {
		columnList.add(column);
		column.addPropertyChangeListener(propertyChangerAction);
		// ��ɫ
		column.setColor(colorManager.getRGB());
		// �������ֵ
		if (column.getValue() > max_value) {
			max_value = column.getValue();
		}
		firePropertyChange(PROPERTY_COLUMNLIST, true, false);
	}

	public void removeColumn(ColumnGraphPointBean column) {
		columnList.remove(column);
		column.removePropertyChangeListener(propertyChangerAction);
		colorManager.setRGB(column.getColor());
		// �������ֵ
		max_value = 0;
		for (ColumnGraphPointBean c : columnList) {
			if (max_value < c.getValue()) {
				max_value = c.getValue();
			}
		}
		firePropertyChange(PROPERTY_COLUMNLIST, false, true);
	}

	public int getColumnSize() {
		return columnList.size();
	}

	public void removeAllColumn() {
		Iterator<ColumnGraphPointBean> it = columnList.iterator();
		while (it.hasNext()) {
			removeColumn(it.next());
		}
	}

	public double getMax_value() {
		return max_value;
	}

	public void setMax_value(double maxValue) {
		max_value = maxValue;
		firePropertyChange(PROPERTY_MAX_VALUE, false, true);
	}

	class ColumnPropertyChagerAction implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(ColumnGraphPointBean.PROPERTY_VALUE)) {
				// ��������ֵ
				ColumnGraphPointBean bean = (ColumnGraphPointBean) evt.getSource();
				if (bean.getValue() > max_value) {
					max_value = bean.getValue();
				}
				return;
			}
		}
	}
}
