package org.alvin.swing.chart;

import org.alvin.swing.chart.bean.TransactionInfoBean;
import org.alvin.swing.chart.bean.PointInfoBean;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


/**
 * ����ͼ
 * 
 * @author ��ֲ��
 * 
 */
public class TransactionGraph extends LineGraph {

	private static final long serialVersionUID = 1L;
	/** ��Ϣ�������� */
	public static final String PROPERTY_SELECT = "property_select";
	public static final String PROPERTY_ADDTRANSACTION = "property_addtransaction";
	/** ������ */
	protected static final int MAX_POINT_NUM = TransactionInfoBean.MAX_LNEGTH;
	/** ѡ�к�ı�����ɫ */
	protected static final Color SELECT_COLOR = Color.LIGHT_GRAY;
	// /** ���񱳾���ɫ */
	protected static final Color GRID_BG_COLOR = Color.pink.brighter();
	/** ���� */
	private LinkedList<TransactionInfoBean> transactions;
	/** �Ƿ�ѡ�� */
	private boolean selected = false;
	/** �Ƿ���� */
	private boolean clean = false;
	/** �ǲ��ǷŴ�����ʾ��������ϵ� */
	private boolean main = false;
	/** Ŀǰ�Ƿ�Ϊ����״̬ */
	private boolean run = false;
	/** �������¼���ʶ */
	private boolean actionFlag = false;

	public TransactionGraph() {
		transactions = new LinkedList<TransactionInfoBean>();
		clean = true;
		initMenu();
		setBackground(BG_COLOR);
		gridBg = GRID_BG_COLOR;
	}

	/**
	 * ���ⷽ�� ��ͼ�������һ������
	 * 
	 * @param tif
	 */
	public void addTransaction(TransactionInfoBean tif) {
		if (clean) {
			clean = false;
		}
		transactions.add(tif);
		firePropertyChange(PROPERTY_ADDTRANSACTION, true, false);
	}

	@Override
	protected void drawGraph(Graphics2D g2d) {
		if (clean) {
			return;
		}

		if (!run) {
			return;
		}

		int index, size;
		double x1, y1, x2, y2, value;
		int half_max = POINT_MAX_SIZE >> 1;
		int half_min = POINT_MINI_SIZE >> 1;

		Point2D tempPoint = null;
		PointInfoBean pointBean = null;
		Iterator<TransactionInfoBean> it;
		List<PointInfoBean> listPoints;
		TransactionInfoBean transaction;
		List<Double> values = new ArrayList<Double>();

		it = transactions.iterator();
		while (it.hasNext()) {
			transaction = it.next();
			listPoints = transaction.getPoints();
			g2d.setColor(transaction.getDataColor());

			size = transaction.getArrayCount();
			// ��array��list������ȡarrayLen�����ݣ�����ͼ
			float space = 1 + ((float) listPoints.size()) / size;
			size += listPoints.size();
			size = size > MAX_POINT_NUM ? MAX_POINT_NUM : size;
			tempPoint = null;
			//
			for (int j = 0; j < size; ++j) {
				index = (int) (space * j + 0.5);
				// ������xλ��
				if (index < transaction.getArrayCount()) {
					// ...��array[sample]
					pointBean = transaction.getArray()[index];
					value = pointBean.getValue();
				} else {
					// ...��list.get(sample)
					if (index > transaction.getPoints().size() - 1) {
						break;
					}
					pointBean = transaction.getPoints().get(index);
					value = pointBean.getValue();
				}
				values.add(value);
			}
			// ��������
			double p = h_p;
			if (size == MAX_POINT_NUM) {
				p = (max_Xvalue / (hnum / h_p)) / (values.size() - 1);
			}
			if (transaction.isSelected()) {
				g2d.setStroke(BOLD_STROKE);
			} else {
				g2d.setStroke(DEFAULT_STROKE);
			}
			// �����ߵĿ��
			float lineWidth = ((BasicStroke) g2d.getStroke()).getLineWidth() / 2;
			for (int j = 0; j < values.size(); j++) {
				x2 = x + p * j + lineWidth;
				value = values.get(j);
				// ����yֵ
				y2 = h - v_p / vnum * value + lineWidth;
				if (transaction.isSelected()) {
					g2d.fill(new Ellipse2D.Double(x2 - half_max, y2 - half_max, POINT_MAX_SIZE, POINT_MAX_SIZE));
				} else {
					g2d.fill(new Ellipse2D.Double(x2 - half_min, y2 - half_min, POINT_MINI_SIZE, POINT_MINI_SIZE));
				}
				// ��һ��������
				if (tempPoint == null) {
					tempPoint = new Point2D.Double(x2, y2);
					continue;
				}

				// ����
				x1 = tempPoint.getX();
				y1 = tempPoint.getY();
				g2d.draw(new Line2D.Double(x1, y1, x2, y2));
				tempPoint = new Point2D.Double(x2, y2);
			}
		}
	}

	public LinkedList<TransactionInfoBean> getTransactions() {
		return transactions;
	}

	private void initMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem menuItem = new JMenuItem("���");
		popupMenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean = !clean;
				repaintImage();
			}
		});
	}

	public boolean isActionFlag() {
		return actionFlag;
	}

	public boolean isClean() {
		return clean;
	}

	public boolean isMain() {
		return main;
	}

	public boolean isRun() {
		return run;
	}

	public boolean isSelected() {
		return selected;
	}

	public void reset() {
		this.transactions.clear();
	}

	public void setActionFlag(boolean actionFlag) {
		this.actionFlag = actionFlag;
	}

	public void setClean(boolean clean) {
		this.clean = clean;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if (selected && mode.equals(MODE_DRAW)) {
			getParent().setBackground(SELECT_COLOR);
			getParent().getParent().setBackground(SELECT_COLOR);
			this.setBackground(SELECT_COLOR);
		} else {
			this.setBackground(BG_COLOR);
			getParent().setBackground(BG_COLOR);
			getParent().getParent().setBackground(BG_COLOR);
		}
		getParent().repaint();
		getParent().getParent().repaint();
		firePropertyChange(PROPERTY_SELECT, true, false);
		if (selected) {
			repaintImage();
			return;
		}
		for (TransactionInfoBean tr : this.transactions) {
			tr.setSelected(false);
		}
		repaintImage();
	}

	public void reDraw() {
		repaintImage();
	}
}
