package org.alvin.swing.chart.bean;

import org.alvin.swing.chart.BaseObject;

public final class PropertiesBean extends BaseObject {
	private static final long serialVersionUID = 1L;
	/** ����ֵ */
	private String value;
	/** �Ƿ���Ա༭ */
	private boolean editable;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
