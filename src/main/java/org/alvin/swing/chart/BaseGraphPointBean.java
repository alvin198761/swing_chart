package org.alvin.swing.chart;

import java.awt.Color;

public abstract class BaseGraphPointBean extends BaseObject {

	private static final long serialVersionUID = 1L;
	public static final String PROPERTY_COLOR = "color";
	public static final String PROPERTY_SELECT = "select";
	/** ����ߵ���ɫ */
	protected Color color;
	/** �Ƿ�ѡ�� */
	protected boolean select = false;
	/** tooltip */
	protected String tooltip;
	/** ͼ��Ӧ������ֵ */
	protected double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setSelect(boolean select) {
		this.select = select;
		firePropertyChange(PROPERTY_SELECT, !select, select);
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isSelect() {
		return select;
	}
}
