package org.alvin.swing.chart.bean;

import org.alvin.swing.chart.BaseObject;

/**
 * ��׼ֵ
 * 
 * @author ��ֲ��
 * 
 */
public class CriteriaBean extends BaseObject {

	private static final long serialVersionUID = 1L;
	// ���� ��С�ڵ���,���ڵ���,����
	private int type;
	// ����1���������;��� �� >
	private String mark_1;
	// ����2���������;��� �� >
	private String mark_2;
	// ����1��Ӧ��ֵ > 20
	private double value_1;
	// ����2��Ӧ��ֵ >20
	private double value_2;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMark_1() {
		return mark_1;
	}

	public void setMark_1(String mark_1) {
		this.mark_1 = mark_1;
	}

	public String getMark_2() {
		return mark_2;
	}

	public void setMark_2(String mark_2) {
		this.mark_2 = mark_2;
	}

	public double getValue_1() {
		return value_1;
	}

	public void setValue_1(double value_1) {
		this.value_1 = value_1;
	}

	public double getValue_2() {
		return value_2;
	}

	public void setValue_2(double value_2) {
		this.value_2 = value_2;
	}

	@Override
	public String toString() {
		String str = "";
		str = mark_1 + value_1;
		if (mark_2 != null) {
			str += mark_2 + value_2;
		}
		return str;
	}
}
