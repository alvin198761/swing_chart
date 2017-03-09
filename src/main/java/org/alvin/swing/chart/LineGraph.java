package org.alvin.swing.chart;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class LineGraph extends Base2DGraph {

	private static final long serialVersionUID = 1L;
	public static final int MAX_HPADDING = 18;
	public static final int MIN_HPADDING = 10;
	/** СȦ��ֱ�� */
	protected static final int POINT_MINI_SIZE = 3;
	/** ��Ȧ��ֱ�� */
	protected static final int POINT_MAX_SIZE = 5;
	/** ����ÿ������ */
	protected LinkedList<LineGraphPointBean> lineBeanList;
	/** ��Ϣ������ */
	protected LinePropertyChangeAction lineAction;

	public LineGraph() {
		lineBeanList = new LinkedList<LineGraphPointBean>();
		lineAction = new LinePropertyChangeAction();

		createDefault();
	}

	private void createDefault() {
		LineGraphPointBean bean = this.addLine();
		bean.addPoint(100);
		bean.addPoint(20);
		bean.addPoint(30);
		bean.addPoint(40);
		bean.addPoint(10);

		bean = this.addLine();
		bean.addPoint(700);
		bean.addPoint(30);
		bean.addPoint(70);
		bean.addPoint(40);
		bean.addPoint(50);

		bean = this.addLine();
		bean.addPoint(100);
		bean.addPoint(35);
		bean.addPoint(120);
		bean.addPoint(45);
		bean.addPoint(5);

		bean = this.addLine();
		bean.addPoint(100);
		bean.addPoint(35);
		bean.addPoint(120);
		bean.addPoint(45);
		bean.addPoint(5);

	}

	@Override
	protected void calculateX() {
		// ���ݵ�ǰ�����ֵ�͵�λ�̶ȣ����ÿ���̶ȵ�
		h_p = MAX_HPADDING;
		// ���ݵ�ǰ�Ŀ̶��ܻ���������
		int num = w / h_p;
		if (num <= 0) {
			return;
		}
		// �������������ܷ������ֵ��Ҫô��С���룬Ҫô��������
		hnum = 1;
		while (num * hnum < max_Xvalue) {
			if (h_p > MAX_HPADDING) {
				h_p++;
			} else {
				h_p = MIN_HPADDING;
				hnum++;
			}
			num = w / h_p;
		}
	}

	@Override
	protected void calculateY() {
		int num = h / v_p;
		if (num <= 0) {
			return;
		}
		vnum = 1;
		while (num * vnum < max_Yvalue) {
			vnum++;
		}
	}

	@Override
	protected void drawGraph(Graphics2D g2d) {
		// ����
		int px = 0;
		int pr = 0;
		float lineWidth;
		double x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		double pvalue = 0, value = 0, s = 0;
		LineGraphPointBean bean;
		Iterator<LineGraphPointBean> it = lineBeanList.iterator();
		while (it.hasNext()) {
			bean = it.next();
			// ���û���
			if (bean.select) {
				pr = POINT_MAX_SIZE >> 1;
				g2d.setStroke(BOLD_STROKE);
			} else {
				pr = POINT_MINI_SIZE >> 1;
				g2d.setStroke(DEFAULT_STROKE);
			}
			// �����ߵĿ��
			lineWidth = ((BasicStroke) g2d.getStroke()).getLineWidth() / 2;
			// ��ɫ
			g2d.setColor(bean.color);
			// ��λֵ�ȵ�λ����
			s = (double) v_p / vnum;
			// ����
			for (int j = 0; j < bean.points.size(); j++) {
				value = bean.points.get(j);
				pvalue = h - s * value - pr;
				// ��������λ��
				px = x + h_p * (j + 1) - pr;
				// ���û���
				if (bean.select) {
					g2d.fill(new Ellipse2D.Double(px, pvalue, POINT_MAX_SIZE, POINT_MAX_SIZE));
				} else {
					g2d.fill(new Ellipse2D.Double(px, pvalue, POINT_MINI_SIZE, POINT_MINI_SIZE));
				}
				// ����
				if (j == 0) {
					x1 = x;
					y1 = h;
					x2 = px + pr + lineWidth;
					y2 = pvalue + pr + lineWidth;
					// ������
					g2d.draw(new Line2D.Double(x1, y1, x2, y2));
					continue;
				}
				// ����
				x1 = x2;
				y1 = y2;
				x2 = px + pr + lineWidth;
				y2 = pvalue + pr + lineWidth;

				// ������
				g2d.draw(new Line2D.Double(x1, y1, x2, y2));
				x1 = x2;
				y1 = y2;
			}
		}
	}

	/**
	 * ���һ����
	 * 
	 * @param line
	 */
	public LineGraphPointBean addLine() {
		LineGraphPointBean line = new LineGraphPointBean();
		addLIne(line);
		return line;
	}

	protected void addLIne(LineGraphPointBean line) {
		this.lineBeanList.add(line);
		line.addPropertyChangeListener(lineAction);
		// TODO
		// ������ɫ
		line.setColor(colorManager.getRGB());
		// ����tooltip
		// Ĭ���ǲ�ѡ��
	}

	public void removeLine(LineGraphPointBean line) {
		this.lineBeanList.add(line);
		line.removePropertyChangeListener(lineAction);
		// ��ɫ�����˼�
		colorManager.setRGB(line.color);
	}

	@Override
	public void setGraphPath(String graphPath) {
		super.setGraphPath(graphPath);
		// ����������ͼ
		try {
			readLinePoint(graphPath);
			repaintImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readLinePoint(String path) throws IOException {
		String line;
		String[] splits;
		RandomAccessFile file = new RandomAccessFile(new File(path), "r");
		Map<String, LineGraphPointBean> map = new HashMap<String, LineGraphPointBean>();
		lineBeanList.clear();
		while ((line = file.readLine()) != null) {
			// �����ַ���
			splits = line.trim().split("\\s");
			if (splits == null || splits.length == 0) {
				continue;
			}
			if (map.containsKey(splits[0])) {
				map.get(splits[0]).addPoint(Double.parseDouble(splits[2]));
			} else {
				LineGraphPointBean bean = new LineGraphPointBean();
				bean.addPoint(Double.parseDouble(splits[2]));
				lineBeanList.add(bean);
			}
		}
	}

	class LinePropertyChangeAction implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String name = evt.getPropertyName();
			if (name.equals(LineGraphPointBean.PROPERTY_POINTS)) {
				// �������ֵ
				double p = (Double) evt.getNewValue();
				setMax_Yvalue((int) p);
			}
		}
	}

}
