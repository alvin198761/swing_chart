package org.alvin.swing.chart;

public class ColumnGraphPointBean extends BaseGraphPointBean {

	private static final long serialVersionUID = 1L;
	public static final String PROPERTY_VALUE = "value";

	@Override
	public void setValue(double value) {
		if (this.value < value) {
			this.value = value;
		}
		firePropertyChange(PROPERTY_VALUE, true, false);
	}

}
