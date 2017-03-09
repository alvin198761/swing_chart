package org.alvin.swing.chart.bean;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import org.alvin.swing.chart.BaseObject;

public class LegendBean extends BaseObject {

	private static final long serialVersionUID = 1L;

	private Color color;
	private boolean selected;

	private int scela;
	// ����
	private String meascurement;
	private double min;
	private double max;
	private double avg;
	// ��ֵ
	private double median;
	// ...����϶����������
	private Map<String, Object> showPropertyMap = new HashMap<String, Object>();

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getScela() {
		return scela;
	}

	public void setScela(int scela) {
		this.scela = scela;
	}

	public String getMeascurement() {
		return meascurement;
	}

	public void setMeascurement(String meascurement) {
		this.meascurement = meascurement;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public Map<String, Object> getShowPropertyMap() {
		return showPropertyMap;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
