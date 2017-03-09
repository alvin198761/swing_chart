package org.alvin.swing.chart.bean;

import org.alvin.swing.chart.BaseObject;


/**
 * ���������Ϣ
 * 
 * @author ��ֲ��
 * 
 */
public class PointInfoBean extends BaseObject {

	private static final long serialVersionUID = 1L;
	// ΨһId
	private int pointId;
	// ִ�в�����ʱ��
	private long timeId;
	// ֵ
	private double value;
	// ���ֵ
	private double max;
	// ��Сֵ
	private double min;
	// ƽ��ֵ
	private double avg;
	// ��׼��
	private double std;

	public PointInfoBean(String rowData) {
		String[] dataArr = rowData.split("\\s");
		pointId = Integer.parseInt(dataArr[0]);
		timeId = Long.parseLong(dataArr[1]);
		value = Double.parseDouble(dataArr[2]);
		max = Double.parseDouble(dataArr[3]);
		min = Double.parseDouble(dataArr[4]);
		avg = Double.parseDouble(dataArr[5]);
		std = Double.parseDouble(dataArr[6]);
	}

	/**
	 * ��������ƽ��ֵ ���һ��point
	 * 
	 * @param pointInfoBean1
	 * @param pointInfoBean2
	 */
	public PointInfoBean(PointInfoBean pointInfoBean1, PointInfoBean pointInfoBean2) {
		pointId = pointInfoBean1.pointId;
		timeId = pointInfoBean1.timeId;
		value = (pointInfoBean1.value + pointInfoBean2.value) / 2;
		max = pointInfoBean1.max > pointInfoBean2.max ? pointInfoBean1.max : pointInfoBean2.max;
		min = pointInfoBean1.min < pointInfoBean2.min ? pointInfoBean1.min : pointInfoBean2.min;
		avg = (pointInfoBean1.avg + pointInfoBean2.avg) / 2;
		std = (pointInfoBean1.std + pointInfoBean2.std) / 2;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public long getTimeId() {
		return timeId;
	}

	public void setTimeId(long timeId) {
		this.timeId = timeId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}
}
