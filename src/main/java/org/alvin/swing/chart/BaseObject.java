package org.alvin.swing.chart;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * ����ʵ����Ļ���
 * 
 * @author Administrator
 * 
 */
public abstract class BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	// ������Ϣ���� ����һЩ���ݴ��ݺͽ���ˢ��
	protected PropertyChangeSupport support = new PropertyChangeSupport(this);
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ������Ϣ
	 */
	public void firePropertyChange(String property, Object oldValue, Object newValue) {
		support.firePropertyChange(property, oldValue, newValue);
	}

	public void firePropertyChange() {
		this.firePropertyChange("name", true, false);
	}

	/**
	 * ����¼����� �����ظ������ͬ����
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PropertyChangeListener[] listeners = support.getPropertyChangeListeners();
		for (PropertyChangeListener l : listeners) {
			if (l.equals(listeners)) {
				return;
			}
		}
		support.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public String toString() {
		return name;
	}

}
