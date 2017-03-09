package org.alvin.swing.chart.bean;


import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.alvin.swing.chart.BaseObject;


/**
 * ��Ŀ��Ϣ
 * 
 * @author ��ֲ��
 * 
 */
public class ProjectBean extends BaseObject {

	private static final long serialVersionUID = 1L;
	// Action����
	private LinkedList<Integer> actions;
	// �ű����Ƶļ���
	private LinkedList<String> allScriptList;
	// ���� [ScriptName,Tran]
	private LinkedHashMap<String, TransactionInfoBean> allTransactionMap;
	// Action Map
	private Map<Integer, ActionBean> actionMap = new HashMap<Integer, ActionBean>();
	/** Ŀǰ��Ϊ����Ҫ���л������� *************************/
	// �Ƿ�ѡ��
	transient private boolean select;
	// Ĭ����ɫ
	transient private Color color;
	// ��ʼֵ
	transient private Integer down = 0;
	// ����
	transient private Integer pending = 0;
	// ��ʼ��
	transient private Integer init = 0;
	// Ԥ��
	transient private Integer ready = 0;
	// ����
	transient private Integer run = 0;
	// ���
	private Integer rendez = 0;
	// ͨ��
	transient private Integer passed = 0;
	// ʧ��
	transient private Integer failed = 0;
	// ����
	transient private Integer error = 0;
	// ���˳�
	transient private Integer gradualExiting = 0;
	// �˳�
	transient private Integer exiting = 0;
	// ֹͣ
	transient private Integer stoped = 0;
	// δ֪
	transient private Double tps;
	// ��Ŀ·��
	transient private String projectPath;

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public Double getTps() {
		return tps;
	}

	public void setTps(Double tps) {
		this.tps = tps;
	}

	public LinkedList<String> getAllScriptList() {
		return allScriptList;
	}

	public void setAllScriptList(LinkedList<String> allScriptList) {
		this.allScriptList = allScriptList;
	}

	public LinkedHashMap<String, TransactionInfoBean> getAllTransactionMap() {
		return allTransactionMap;
	}

	public void setAllTransactionMap(LinkedHashMap<String, TransactionInfoBean> allTransactionMap) {
		this.allTransactionMap = allTransactionMap;
	}

	public ProjectBean() {
		actions = new LinkedList<Integer>();
		initDefaultAction();

		allScriptList = new LinkedList<String>();
		allTransactionMap = new LinkedHashMap<String, TransactionInfoBean>();
		initDefaultTransaction();
	}

	private void initDefaultTransaction() {
		TransactionInfoBean bean;
		allScriptList.add("Init");
		allScriptList.add("End");
		bean = new TransactionInfoBean();
		bean.setName("Init");
		this.allTransactionMap.put("Init", bean);
		bean = new TransactionInfoBean();
		bean.setName("End");
		this.allTransactionMap.put("End", bean);
	}

	private void initDefaultAction() {
		ActionBean bean;
		// ��ʼ������
		bean = new ActionBean();
		bean.setName("ActionBean.StartGroup");
		bean.setType(ActionBean.TYPE_START_GROUP);
		bean.setMode(ActionBean.MODE_1);
		bean.setTime(0);
		bean.setStart_beginScript("");
		actions.add(ActionBean.TYPE_START_GROUP);
		actionMap.put(ActionBean.TYPE_START_GROUP, bean);
		// ��ʼ��
		// bean = new ActionBean();
		// bean.setName("ActionBean.Initialize");
		// bean.setType(ActionBean.TYPE_INIT);
		// bean.setMode(ActionBean.MODE_1);
		// bean.setTime(0);
		// bean.setUser(0);
		// actions.add(ActionBean.TYPE_INIT);
		// actionMap.put(ActionBean.TYPE_INIT, bean);
		// ���������û�
		bean = new ActionBean();
		bean.setName("ActionBean.StartVuser");
		bean.setType(ActionBean.TYPE_START_VUSER);
		bean.setMode(ActionBean.MODE_1);
		bean.setVuserCount(5);
		bean.setUser(1);
		bean.setTime(15);
		actions.add(ActionBean.TYPE_START_VUSER);
		actionMap.put(ActionBean.TYPE_START_VUSER, bean);
		// ����ʱ��
		bean = new ActionBean();
		bean.setName("ActionBean.Duration");
		bean.setType(ActionBean.TYPE_DUR);
		bean.setMode(ActionBean.MODE_2);
		bean.setDur_days(0);
		bean.setTime(15);
		actions.add(ActionBean.TYPE_DUR);
		actionMap.put(ActionBean.TYPE_DUR, bean);
		// ֹͣ�����û�
		bean = new ActionBean();
		bean.setName("ActionBean.StopVuser");
		bean.setType(ActionBean.TYPE_STOPVUSER);
		bean.setMode(ActionBean.MODE_1);
		bean.setVuserCount(5);
		bean.setUser(1);
		bean.setTime(15);
		actions.add(ActionBean.TYPE_STOPVUSER);
		actionMap.put(ActionBean.TYPE_STOPVUSER, bean);
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public LinkedList<ActionBean> getActions() {
		LinkedList<ActionBean> list = new LinkedList<ActionBean>();
		for (Integer it : actions) {
			list.add(actionMap.get(it));
		}
		return list;
	}

	public ActionBean getActionBeanByType(Integer type) {
		return actionMap.get(type);
	}

	/**
	 * ���ݽű������õ��ű�������
	 * 
	 * @param name
	 * @return
	 */
	public String getScriptContentByName(String name) {
		return "";
	}

	public Integer getDown() {
		return down;
	}

	public void setDown(Integer down) {
		this.down = down;
	}

	public Integer getPending() {
		return pending;
	}

	public void setPending(Integer pending) {
		this.pending = pending;
	}

	public Integer getInit() {
		return init;
	}

	public void setInit(Integer init) {
		this.init = init;
	}

	public Integer getReady() {
		return ready;
	}

	public void setReady(Integer ready) {
		this.ready = ready;
	}

	public Integer getRun() {
		return run;
	}

	public void setRun(Integer run) {
		this.run = run;
	}

	public Integer getRendez() {
		return rendez;
	}

	public void setRendez(Integer rendez) {
		this.rendez = rendez;
	}

	public Integer getPassed() {
		return passed;
	}

	public void setPassed(Integer passed) {
		this.passed = passed;
	}

	public Integer getFailed() {
		return failed;
	}

	public void setFailed(Integer failed) {
		this.failed = failed;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public Integer getGradualExiting() {
		return gradualExiting;
	}

	public void setGradualExiting(Integer gradualExiting) {
		this.gradualExiting = gradualExiting;
	}

	public Integer getExiting() {
		return exiting;
	}

	public void setExiting(Integer exiting) {
		this.exiting = exiting;
	}

	public Integer getStoped() {
		return stoped;
	}

	public void setStoped(Integer stoped) {
		this.stoped = stoped;
	}

	public void setActions(LinkedList<Integer> actions) {
		this.actions = actions;
	}

	/**
	 * ��������������ȡ����
	 * 
	 * @param name
	 * @return
	 */
	public TransactionInfoBean getTransactionByName(String name) {
		return allTransactionMap.get(name);
	}
}
