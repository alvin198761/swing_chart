package org.alvin.swing.chart.bean;

import java.text.DecimalFormat;
import org.alvin.swing.chart.BaseGraphPointBean;

public class PieGraphPointBean extends BaseGraphPointBean {

	private static final long serialVersionUID = 1L;
	public static final String PROPERTY_VALUE = "value";
	/** ���ֵİٷֱ���ʵ��ʽ */
	protected static final DecimalFormat format = new DecimalFormat("#%");
	/** ��ʽ���ɰٷֱ� */
	protected String valueFormat;
	/** ���� */
	protected double scale = 0;
	/** �յ� */
	protected double end = 0;

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	@Override
	public void setValue(double value) {
		super.setValue(value);
		firePropertyChange(PROPERTY_VALUE, true, false);
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getScale() {
		return scale;
	}

	public String getValueFormat() {
		return format.format(scale);
	}

}
