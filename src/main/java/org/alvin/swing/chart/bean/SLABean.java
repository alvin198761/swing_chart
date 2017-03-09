package org.alvin.swing.chart.bean;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import org.alvin.swing.chart.BaseObject;
import org.alvin.swing.chart.StringManager;


/**
 * SLAʵ����
 * 
 * @author ��ֲ��
 * 
 */
public class SLABean extends BaseObject {

	private static final long serialVersionUID = 1L;

	public static final int TYPE_TRANSACTION_RESPONSE_TIME = 1;
	public static final int TYPE_ERROR_PER_SECOND = 2;
	public static final int TYPE_TOTAL_HITS = 3;
	public static final int TYPE_AVG_HITS = 4;
	public static final int TYPE_TOTAL_THROUGHPUT = 5;
	public static final int TYPE_AVG_THROUGHPUT = 6;
	private int type;
	private String measurement;
	private double thresholdsValue;
	/********** Transaction������ **************/
	// ״̬Ϊ�ٷֱ� ��ƽ����
	private Integer status = 0;
	// [tran,����ֵ]
	private LinkedHashMap<String, Double> transactinMap;
	private int percentile;
	private double value;
	// ���ɱ�׼���� ����ֻ�����ж��ǲ���none���ͣ�������Ͷ�һ����
	private int loadCriteriaType;
	// ״̬Ϊƽ�� == �������������
	private String criteriaType;// ��������
	private LinkedList<CriteriaBean> criteriaList = new LinkedList<CriteriaBean>();// ����

	public int getLoadCriteriaType() {
		return loadCriteriaType;
	}

	public void setLoadCriteriaType(int loadCriteriaType) {
		this.loadCriteriaType = loadCriteriaType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public double getThresholdsValue() {
		return thresholdsValue;
	}

	public void setThresholdsValue(double thresholdsValue) {
		this.thresholdsValue = thresholdsValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LinkedHashMap<String, Double> getTransactinMap() {
		return transactinMap;
	}

	public void setTransactinMap(LinkedHashMap<String, Double> transactinMap) {
		this.transactinMap = transactinMap;
	}

	public int getPercentile() {
		return percentile;
	}

	public void setPercentile(int percentile) {
		this.percentile = percentile;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCriteriaType() {
		return criteriaType;
	}

	public void setCriteriaType(String criteriaType) {
		this.criteriaType = criteriaType;
	}

	public LinkedList<CriteriaBean> getCriteriaList() {
		return criteriaList;
	}

	public void setCriteriaList(LinkedList<CriteriaBean> criteriaList) {
		this.criteriaList = criteriaList;
	}

	public static String getTypeNameByType(int type) {
		String value = "";
		switch (type) {
		case TYPE_TRANSACTION_RESPONSE_TIME:
			value = StringManager.getSringById("ActionEdit.Dialog.Rdo_Tran_response_time");
			break;
		case TYPE_ERROR_PER_SECOND:
			value = StringManager.getSringById("ActionEdit.Dialog.Rdo_Error_per_second");
			break;
		case TYPE_TOTAL_HITS:
			value = StringManager.getSringById("ActionEdit.Dialog.Rdo_Total_hits");
			break;
		case TYPE_AVG_HITS:
			value = StringManager.getSringById("ActionEdit.Dialog.Rdo_Avg_hits");
			break;
		case TYPE_TOTAL_THROUGHPUT:
			value = StringManager.getSringById("ActionEdit.Dialog.Rdo_Total_throughput");
			break;
		case TYPE_AVG_THROUGHPUT:
			value = StringManager.getSringById("ActionEdit.Dialog.Rdo_Avg_throughput");
			break;
		}
		return value;
	}
}
