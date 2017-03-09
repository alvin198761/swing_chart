package org.alvin.swing.chart.bean;

import org.alvin.swing.chart.bean.ProjectBean;
import org.alvin.swing.chart.bean.SLABean;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.alvin.swing.chart.BaseObject;
import org.alvin.swing.chart.ColorManager;

public class ScenarioBean extends BaseObject {

	private static final long serialVersionUID = 1L;
	// �����ļ���
	public static final String PROJECT_FILE_NAME = "Executant.cfg";
	// ����ʱ��
	transient private long startTime;
	// ��ɫ����
	transient private ColorManager colorManager = new ColorManager();
	// ״̬
	transient private int status;
	// ��̬���� SLA
	private LinkedList<SLABean> slaList;
	// ��Ŀ
	private LinkedList<ProjectBean> projectList;

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public ColorManager getColorManager() {
		return colorManager;
	}

	public void setColorManager(ColorManager colorManager) {
		this.colorManager = colorManager;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LinkedList<SLABean> getSlaList() {
		return slaList;
	}

	public void setSlaList(LinkedList<SLABean> slaList) {
		this.slaList = slaList;
	}

	public void setProjectList(LinkedList<ProjectBean> projectList) {
		this.projectList = projectList;
	}

	public ScenarioBean() {
		slaList = new LinkedList<SLABean>();
		projectList = new LinkedList<ProjectBean>();
	}

	public LinkedList<ProjectBean> getProjectList() {
		return projectList;
	}

	public void addProject(ProjectBean bean) {
		projectList.add(bean);
		bean.setColor(colorManager.getRGB());
	}

	public void removeProject(int row) {
		ProjectBean bean = projectList.remove(row);
		if (bean != null) {
			colorManager.setRGB(bean.getColor());
		}
	}

	public LinkedList<TransactionInfoBean> getAllTransaction() {
		LinkedList<TransactionInfoBean> list = new LinkedList<TransactionInfoBean>();
		List<String> strList = new ArrayList<String>();
		for (ProjectBean pro : projectList) {
			for (String str : pro.getAllScriptList()) {
				if (strList.contains(str)) {
					continue;
				}
				strList.add(str);
				list.add(pro.getTransactionByName(str));
			}
		}
		return list;
	}

	public LinkedList<TransactionInfoBean> getTransactionsBySelected(boolean selected) {
		LinkedList<TransactionInfoBean> list = getAllTransaction();
		LinkedList<TransactionInfoBean> resultList = new LinkedList<TransactionInfoBean>();
		loop: for (TransactionInfoBean tr : list) {
			if (tr.isSelected() != selected) {
				continue;
			}
			for (TransactionInfoBean comTr : resultList) {
				if (comTr.getName().equals(tr.getName())) {
					continue loop;
				}
			}
			resultList.add(tr);
		}
		return resultList;
	}

}
