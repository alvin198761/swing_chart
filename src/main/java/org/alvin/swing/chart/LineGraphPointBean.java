package org.alvin.swing.chart;

import java.util.LinkedList;

public class LineGraphPointBean extends BaseGraphPointBean {

	private static final long serialVersionUID = 1L;
	public static final String PROPERTY_POINTS = "points";
	/** ����ͼ����ĵ� */
	protected LinkedList<Double> points;

	public LineGraphPointBean() {
		points = new LinkedList<Double>();
	}

	public void addPoint(double value) {
		this.points.add(value);
		firePropertyChange(PROPERTY_POINTS, -1, value);
	}

	public LinkedList<Double> getPoints() {
		return points;
	}

	@Deprecated
	public double getValue() {
		return 0;
	}

	@Deprecated
	public void setValue(double value) {

	}

}
