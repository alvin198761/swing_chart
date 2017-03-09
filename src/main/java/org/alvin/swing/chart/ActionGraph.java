package org.alvin.swing.chart;



import org.alvin.swing.chart.bean.ActionBean;
import org.alvin.swing.chart.bean.BeanUtil;
import org.alvin.swing.chart.bean.ProjectBean;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class ActionGraph extends Base2DGraph {

	private static final long serialVersionUID = 1L;
	public static final int ROW_NUM = 10;
	public static final int COL_NUM = 10;

	public static final String STATUS_DESIGNE = "designe";
	public static final String STATUS_RUN = "run";
	public static final AlphaComposite ALPHA_COMPOSITE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
	/** Ĭ�����ߵĻ��� */
	protected static final BasicStroke DEFAULT_LINE_STROKE = new BasicStroke(6.0f);
	/** ���ߵĻ��� */
	protected static final BasicStroke SELECT_LINE_STROKE = new BasicStroke(3.0f);
	/** ��ǰ��Action����Ŀ */
	private List<ProjectBean> projectList;
	/** ��ǰ״̬ Ŀǰ����ƺ�����״̬���� */
	private String status = STATUS_DESIGNE;

	public ActionGraph() {
		setBackground(BG_COLOR);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProjectBean> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectBean> projectList) {
		this.projectList = projectList;
	}

	@Override
	protected void drawGraph(Graphics2D g2d) {
		if (projectList == null) {
			return;
		}
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;

		Map<String, Double> projectEndXMap = new HashMap<String, Double>();
		ActionBean bean = null;
		// ѭ��ÿ����Ŀ �����е��Ⱥ�˳���Ѿ���ǰ��ά�����ˣ���Щ�ҿ��Բ��ܣ�ֻҪ��˳�򻭣���û��
		for (ProjectBean project : projectList) {
			g2d.setColor(project.getColor());
			// ��͸��
			g2d.setComposite(ALPHA_COMPOSITE);
			// ������
			bean = project.getActionBeanByType(ActionBean.TYPE_START_GROUP);
			// ��Ҫ����������ֵ��ά����ϵ
			if (bean.getMode() == ActionBean.MODE_1) {
				x2 = x;
				y2 = 0;
			} else if (bean.getMode() == ActionBean.MODE_2) {
				x2 = x + bean.getTime() / (hnum / h_p);
				y2 = 0;
			} else if (bean.getMode() == ActionBean.MODE_3) {
				x2 = projectEndXMap.get(bean.getStart_beginScript());
				y2 = 0;
			}
			// ��Start User Action
			bean = project.getActionBeanByType(ActionBean.TYPE_START_VUSER);
			setActionStroke(g2d, bean);
			if (bean.getMode() == ActionBean.MODE_1) {
				// ͬʱ����
				x1 = x2;
				y1 = y2;
				x2 = x1;
				y2 = bean.getVuserCount() / (vnum / v_p);
				g2d.draw(new Line2D.Double(x1, h - y1, x2, h - y2));
			} else if (bean.getMode() == ActionBean.MODE_2) {
				// ����ʱ�������
				for (int i = bean.getUser(); i <= bean.getVuserCount(); i += bean.getUser()) {
					if (i == bean.getUser()) {
						x1 = x2;
						y1 = y2;
						x2 = x1;
						y2 = bean.getUser() / (vnum / v_p);
						g2d.draw(new Line2D.Double(x1, h - y1, x2, h - y2));
					} else {
						// ������
						x1 = x2;
						y1 = y2;

						x2 = x1 + bean.getTime() / (hnum / h_p);
						y2 = y1;
						g2d.draw(new Line2D.Double(x1, h - y1, x2, h - y2));
						// ����
						x1 = x2;
						y1 = y2;

						x2 = x1;
						y2 = i / (vnum / v_p);
						g2d.draw(new Line2D.Double(x1, h - y1, x2, h - y2));
					}
				}
			}
			// ������ʱ��
			bean = project.getActionBeanByType(ActionBean.TYPE_DUR);
			setActionStroke(g2d, bean);
			if (bean.getMode() == ActionBean.MODE_1) {
				// ����ֱ������
				/*
				 * ��������
				 */
				for (int i = 0; i < 2; i++) {
					x1 = x2 + 10;
					x2 = x1 + 10;

					g2d.draw(new Line2D.Double(x1, h - y2, x2, h - y2));
				}
				projectEndXMap.put(project.getName(), x2);
				return;
			} else if (bean.getMode() == ActionBean.MODE_2) {
				// �涨����ʱ��
				x1 = x2;
				y1 = y2;

				x2 = x1 + (bean.getDur_days() * 24 * 60 * 60 + bean.getTime()) / (hnum / h_p);
				y2 = y1;
				g2d.draw(new Line2D.Double(x1, h - y1, x2, h - y2));
			}

			// ��ֹͣ�û�
			bean = project.getActionBeanByType(ActionBean.TYPE_STOPVUSER);
			setActionStroke(g2d, bean);
			if (bean.getMode() == ActionBean.MODE_1) {
				// // ͬʱֹͣ
				x1 = x2;
				y1 = y2;

				x2 = x1;
				y2 = 0;
				g2d.draw(new Line2D.Double(x1, h - y1, x2, h - y2));
				projectEndXMap.put(project.getName(), x2);
			} else if (bean.getMode() == ActionBean.MODE_2) {
				// ����ʱ��ֹͣ
				for (int i = 0; i < bean.getVuserCount(); i += bean.getUser()) {
					// ������
					x1 = x2;
					y1 = y2;

					x2 = x1;
					y2 = y1 - bean.getUser() / (vnum / v_p);
					if (y2 < 0) {
						y2 = 0;
					}
					g2d.draw(new Line2D.Double(x1, h - y1, x2, h - y2));
					// ������
					if (i >= bean.getVuserCount() - bean.getUser()) {
						break;
					}
					x1 = x2;
					y1 = y2;

					x2 = x1 + bean.getTime() / (hnum / h_p);
					y2 = y1;
					g2d.draw(new Line2D.Double(x1, h - y1, x2, h - y2));
					projectEndXMap.put(project.getName(), x2);
				}
			}
		}
	}

	/**
	 * ��ѡ�е��߼Ӵ�
	 * 
	 * @param g2
	 * @param bean
	 */
	private void setActionStroke(Graphics2D g2, ActionBean bean) {
		if (!bean.isSelect()) {
			g2.setStroke(SELECT_LINE_STROKE);
		} else {
			g2.setStroke(DEFAULT_LINE_STROKE);
		}
	}

	@Override
	protected void calculateX() {
		if (projectList == null) {
			return;
		}
		if (projectList.size() == 0) {
			return;
		}
		// �����hnum ��vnum
		ProjectBean project = projectList.get(0);
		double[] values = caleMaxNum(project);
		max_Xvalue = (int) values[0];
		max_Yvalue = (int) values[1];

		for (int i = 1; i < projectList.size(); i++) {
			values = caleMaxNum(projectList.get(i));
			if (max_Xvalue < values[0]) {
				max_Xvalue = (int) values[0];
			}

			if (max_Yvalue < values[1]) {
				max_Yvalue = (int) values[1];
			}
		}

		if (projectList.size() == 0) {
			max_Yvalue = 0;
			max_Xvalue = 0;
		}

		// �������
		double num = w / h_p;
		if (num == 0) {
			return;
		}
		hnum = 1;
		while (num * hnum < max_Xvalue) {
			hnum++;
		}
	}

	@Override
	protected void calculateY() {
		double num = h / v_p;
		if (num == 0) {
			return;
		}
		vnum = .2;
		while (num * vnum < max_Yvalue) {
			vnum += .2;
		}
	}

	/**
	 * �����һ����Ŀ������λ�ã���Щ��Ŀ�����ڱ����Ŀ���н�����������У��������ǵ�����λ���ǲ�һ���ģ�
	 * 
	 * @param project
	 * @return
	 */
	private double[] caleMaxNum(ProjectBean project) {
		double vnum = 0;
		double hnum = 0;
		for (ActionBean bean : project.getActions()) {
			switch (bean.getType()) {
			case ActionBean.TYPE_START_GROUP:
				if (bean.getMode() == ActionBean.MODE_2) {
					hnum += bean.getTime();
				} else if (bean.getMode() == ActionBean.MODE_3) {
					for (ProjectBean pro : projectList) {
						if (pro.getName().equals(bean.getStart_beginScript())) {
							hnum += caleMaxNum(pro)[0];
							break;
						}
					}
				}
				break;
			case ActionBean.TYPE_INIT:
				break;
			case ActionBean.TYPE_START_VUSER:
				vnum = bean.getVuserCount();
				if (bean.getMode() == ActionBean.MODE_1) {
					break;
				}
				hnum += (bean.getVuserCount() / bean.getUser()) * bean.getTime();
				break;
			case ActionBean.TYPE_STOPVUSER:
				if (bean.getMode() == ActionBean.MODE_1) {
					break;
				}
				hnum += (bean.getVuserCount() / bean.getUser()) * bean.getTime();
				break;
			case ActionBean.TYPE_DUR:
				if (bean.getMode() == ActionBean.MODE_1) {
					break;
				}
				hnum += bean.getTime() + bean.getDur_days() * 24 * 60 * 60;
				break;
			}
		}
		return new double[] { hnum, vnum };
	}

	protected void drawV_mark(Graphics2D g2d, int fw, int fh) {
		String text;
		for (int i = 0, j = 0; i < w; i += h_p, j += 5) {
			g2d.setStroke(new BasicStroke(1f));
			g2d.setColor(Color.black);
			g2d.drawLine(x + j * h_p, h, x + j * h_p, h + 7);
			// �����ֵ�λ��
			g2d.setColor(Color.black);
			text = BeanUtil.timeToH((int) (j * hnum));
			g2d.drawString(text, x + j * h_p - fw / 2 * text.length(), h + 7 + fh);
			if (j == 0) {
				continue;
			}
			// ������
			g2d.setColor(Color.gray);
			g2d.setStroke(DESH_LINE_STROKE);
			g2d.drawLine(x + j * h_p, h, x + j * h_p, y);
		}
	}

	/**
	 * ������ ���̶� -- ���̶�ֵ
	 * 
	 * @param g2d
	 * @param fw
	 * @param fh
	 */
	protected void drawH_mark(Graphics2D g2d, int fw, int fh) {
		String text;
		for (int i = 0, j = 0; i < h; i += v_p, j += 5) {
			g2d.setStroke(DEFAULT_STROKE);
			g2d.setColor(Color.black);
			g2d.drawLine(x - 7, h - j * v_p, x, h - j * v_p);
			// �����ֵ�λ��
			g2d.setColor(Color.black);
			text = (int) (j * vnum) + "";
			g2d.drawString(text, x - 7 - fw * text.length(), h - j * v_p + fh / 3);
			if (i == 0) {
				continue;
			}
			// ������
			g2d.setStroke(DESH_LINE_STROKE);
			g2d.setColor(Color.gray);
			g2d.drawLine(x, h - j * v_p, getWidth(), h - j * v_p);
		}
	}

	public void firePropertyChange() {
		if (projectList == null) {
			return;
		}
		repaintImage();
	}

	public void reDraw() {
		calculateX();
		calculateY();
		firePropertyChange();
	}
}
