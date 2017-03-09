package org.alvin.swing.chart.bean;

import org.alvin.swing.chart.bean.PointInfoBean;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import org.alvin.swing.chart.BaseObject;
import org.alvin.swing.chart.ColorManager;

/**
 * ������Ϣ��ʵ����
 * 
 * @author ��ֲ��
 * 
 */
public class TransactionInfoBean extends BaseObject {

	public static final String PROPERTY_POINTS = "points";
	private static final long serialVersionUID = 1L;
	/** ��󳤶� */
	public static final int MAX_LNEGTH = 20;
	/** �������� */
	private String transactionName;
	/** ���ֵ */
	private double max;
	/** ��Сֵ */
	private double min;
	/** ƽ��ֵ */
	private double avg;
	/** ��׼�� */
	private double sta;
	/** ���һ������ */
	private double last;
	/** �߽�ֵ */
	private double thresgholdsValue = 0d;
	/** ������ */
	private LinkedList<PointInfoBean> points;
	/** ������ͼ������ */
	transient private PointInfoBean[] array = new PointInfoBean[MAX_LNEGTH];
	/** ����������� */
	transient private int arrayCount = 0;
	/** ���ϵĳ��� */
	transient private int listLen = MAX_LNEGTH;
	/** ��ɫ���� */
	transient private ColorManager colorManager = new ColorManager();
	/** �Ƿ�ѡ�� */
	transient private boolean selected;
	/** �ߵ���ɫ */
	transient private Color dataColor;

	public TransactionInfoBean() {
		points = new LinkedList<PointInfoBean>();
		dataColor = colorManager.getRGB();
	}

	/**
	 * ���һ����
	 * 
	 * @param bean
	 */
	public void addPoint(PointInfoBean bean) {
		do {
			// ����arrayLen��������item�����array
			if (arrayCount < MAX_LNEGTH) {
				array[arrayCount] = bean;
				++arrayCount;
				// ...��ͼarray
				break;
			}
			points.add(bean);
			if (points.size() == listLen) {
				// ��Ҫѹ��
				// ѹ������
				for (int i = 0; i < array.length; i += 2) {
					array[i >> 1] = new PointInfoBean(array[i], array[i + 1]);
				}
				// ѹ��list
				int index = array.length >> 1;
				int listCompress = listLen / (MAX_LNEGTH >> 1);
				Iterator<PointInfoBean> itr = points.iterator();
				float sum = 0;
				int listCount = 0;
				while (itr.hasNext()) {
					sum += itr.next().getValue();

					++listCount;
					if (listCount == listCompress) {
						array[index].setValue(sum / listCompress);
						++index;

						listCount = 0;
						sum = 0;
					}
				}
				// ��������
				listLen *= 2;
				// ...��ͼarray
				break;
			}
			// ��array��list������ȡarrayLen�����ݣ�����ͼ
			// float space = 1 + ((float) list.size()) / array.length;
			// int sample;
			// for (int i = 0; i < array.length; ++i) {
			// sample = (int) (space * i + 0.5);
			// if (sample < array.length) {
			// // ...��array[sample]
			// } else {
			// // ...��list.get(sample)
			// }
			// }
		} while (false);
		// ����õ�
		// ����Ϣ
		// firePropertyChange(PROPERTY_POINTS, true, false);
	}

	public LinkedList<PointInfoBean> getPoints() {
		return points;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getDataColor() {
		return dataColor;
	}

	public double getThresgholdsValue() {
		return thresgholdsValue;
	}

	public void setThresgholdsValue(double thresgholdsValue) {
		this.thresgholdsValue = thresgholdsValue;
	}

	public void setDataColor(Color dataColor) {
		this.dataColor = dataColor;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
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

	public double getSta() {
		return sta;
	}

	public void setSta(double sta) {
		this.sta = sta;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public PointInfoBean[] getArray() {
		return array;
	}

	public int getArrayCount() {
		return arrayCount;
	}
}
