package org.alvin.swing.chart.bean;

import org.alvin.swing.chart.BaseObject;
import org.alvin.swing.chart.StringManager;


public class ActionBean extends BaseObject {

	private static final long serialVersionUID = 1L;
	// ģʽ�ĳ���
	public static final int MODE_1 = 1;
	public static final int MODE_2 = 2;
	public static final int MODE_3 = 3;
	// ���͵ĳ���
	public static final int TYPE_START_GROUP = 1;
	public static final int TYPE_INIT = 2;
	public static final int TYPE_START_VUSER = 3;
	public static final int TYPE_DUR = 4;
	public static final int TYPE_STOPVUSER = 5;
	// һ������ ������
	private int type;// ����
	private long time = 0;// �¼�
	private int mode;// ģʽ
	private boolean select;// �Ƿ�ѡ��
	// ��ͬ�Ķ���һ��
	private int dur_days;// ��������
	private int vuserCount;// �����û�����
	private int user;// ��λ�����û���
	private String start_beginScript;// �ڱ��ű�����ǰ���еĽű�������

	public ActionBean() {
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public int getDur_days() {
		return dur_days;
	}

	public int getMode() {
		return mode;
	}

	public String toString() {
		switch (type) {
		case TYPE_START_GROUP:
			return this.getStartGroupPropertise();
		case TYPE_INIT:
			return this.getInitPropertise();
		case TYPE_START_VUSER:
			return this.getStartVUserPropertise();
		case TYPE_DUR:
			return this.getDuractionPropertise();
		case TYPE_STOPVUSER:
			return this.getStopVuserPropertise();
		}
		return "";
	}

	private String getStopVuserPropertise() {
		switch (this.mode) {
		case MODE_1:
			return StringManager.getSringById("ActionEdit.StopVUser.stop.label") + vuserCount + StringManager.getSringById("ActionEdit.StartVUser.vUser.label") + StringManager.getSringById("ActionEdit.StartVUser.mode1");
		case MODE_2:
			return StringManager.getSringById("ActionEdit.StopVUser.stop.label") + user + StringManager.getSringById("ActionEdit.StartVUser.mode2.vuser.every") + BeanUtil.timeToH(time) + StringManager.getSringById("ActionEdit.StartVUser.mode1");
		}
		return "";
	}

	private String getDuractionPropertise() {
		// 
		switch (this.mode) {
		case MODE_1:
			return StringManager.getSringById("ActionEdit.Duration.mode1");
		case MODE_2:
			return StringManager.getSringById("ActionEdit.Duration.mode2.run") + dur_days + StringManager.getSringById("ActionEdit.Duration.mode2.days") + BeanUtil.timeToH(time) + StringManager.getSringById("ActionEdit.StopVUser.stop.label");
		}
		return "";
	}

	private String getStartVUserPropertise() {
		switch (this.mode) {
		case MODE_1:
			return StringManager.getSringById("ActionEdit.StartVUser.start.label") + vuserCount + StringManager.getSringById("ActionEdit.StartVUser.vUser.label") + StringManager.getSringById("ActionEdit.StartVUser.mode1");
		case MODE_2:
			return StringManager.getSringById("ActionEdit.StartVUser.start.label") + user + StringManager.getSringById("ActionEdit.StartVUser.mode2.vuser.every") + BeanUtil.timeToH(time) + StringManager.getSringById("ActionEdit.StartVUser.mode1");
		}
		return "";
	}

	private String getInitPropertise() {
		switch (this.mode) {
		case MODE_1:
			return StringManager.getSringById("ActionEdit.Init.mode1");
		case MODE_2:
			return StringManager.getSringById("ActionEdit.Init.mode2") + user + StringManager.getSringById("ActionEdit.Init.mode2.vUser") + BeanUtil.timeToH(time) + StringManager.getSringById("ActionEdit.StartVUser.mode2.time");
		case MODE_3:
			return StringManager.getSringById("ActionEdit.Init.mode3");
		}
		return "";
	}

	private String getStartGroupPropertise() {
		switch (this.mode) {
		case MODE_1:
			return StringManager.getSringById("ActionEdit.StartGroup.mode1");
		case MODE_2:
			return StringManager.getSringById("ActionEdit.StartGroup.mode2") + BeanUtil.timeToH(time) + StringManager.getSringById("ActionEdit.StartGroup.mode2.lable");
		case MODE_3:
			return StringManager.getSringById("ActionEdit.StartGroup.mode3") + start_beginScript + StringManager.getSringById("ActionEdit.StartGroup.mode3.lable");
		}
		return "";
	}

	public String getStart_beginScript() {
		return start_beginScript;
	}

	public long getTime() {
		return time;
	}

	public int getType() {
		return type;
	}

	public int getUser() {
		return user;
	}

	public int getVuserCount() {
		return vuserCount;
	}

	public void setDur_days(int durDays) {
		dur_days = durDays;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void setStart_beginScript(String startBeginScript) {
		start_beginScript = startBeginScript;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public void setVuserCount(int vuserCount) {
		this.vuserCount = vuserCount;
	}

}
