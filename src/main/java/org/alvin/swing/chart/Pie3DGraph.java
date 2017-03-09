package org.alvin.swing.chart;

import org.alvin.swing.chart.bean.PieGraphPointBean;
import org.alvin.swing.chart.bean.Pie;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;


/**
 * ����3DЧ���ı�ͼ
 * 
 * @author Biao
 */
public class Pie3DGraph extends Base2DGraph {

	private static final long serialVersionUID = 1L;
	private PieGraphBeanPropertyChangeAction propertyChangeAction = new PieGraphBeanPropertyChangeAction();
	/** ԲȦ��ֱ�������ı��� */
	protected static final float R_SCALE = .95f;
	/** ͼ�����Ķ��� */
	protected LinkedList<PieGraphPointBean> pieList = new LinkedList<PieGraphPointBean>();
	/** һ������ */
	private Pie[] pies;

	/** ֱ�� */
	protected double r = 0;
	/** ��� x */
	double startX = 0;
	/** ��� y */
	double startY = 0;

	// int x = 50;
	// int y = 50;
	// int w = 380;
	// int h = (int) (w * 0.618); // �ƽ�ָ�

	private int shadowDepth = 8;
	private int shiftAngle = -30;
	// �������ѡ�е�Arc, -1Ϊû��ѡ��
	private int selectedPieIndex = -1;

	public Pie3DGraph() {
		// ȡ�����ѡ�еı�ͼ���±�
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// selectedPieIndex = -1;
				for (int i = 0; i < pies.length; ++i) {
					if (pies[i].getArc().contains(e.getX(), e.getY())) {
						if (selectedPieIndex == i) {
							selectedPieIndex = -1;
							break;
						}
						selectedPieIndex = i;
						break;
					}
				}
				repaintImage();
			}
		});
		createDefault();
	}

	@Override
	protected void drawH_mark(Graphics2D g2d, int fw, int fh) {
		// �����̶�
	}

	@Override
	protected void drawV_mark(Graphics2D g2d, int fw, int fh) {
		// �����̶�
	}

	private void createDefault() {
		PieGraphPointBean bean = this.addPie();
		bean.setValue(10);

		bean = this.addPie();
		bean.setValue(20);

		bean = this.addPie();
		bean.setValue(30);

		bean = this.addPie();
		bean.setValue(40);
	}

	public PieGraphPointBean addPie() {
		PieGraphPointBean bean = new PieGraphPointBean();
		pieList.add(bean);
		bean.addPropertyChangeListener(propertyChangeAction);
		bean.setColor(colorManager.getRGB());
		return bean;
	}

	public void removePie(PieGraphPointBean bean) {
		colorManager.setRGB(bean.getColor());
		pieList.remove(bean);
		bean.removePropertyChangeListener(propertyChangeAction);
	}

	public static Pie[] createPies(int x, int y, int w, int h, int shadowDepth, double shiftAngle, List<PieGraphPointBean> datas) {
		double sum = 0;
		for (PieGraphPointBean ppb : datas) {
			sum += ppb.getValue();
		}

		// ��ʼ��Pies
		double arcAngle = 0;
		double startAngle = shiftAngle;
		int size = datas.size();
		double value;
		Pie[] pies = new Pie[size];
		for (int i = 0; i < size; ++i) {
			value = datas.get(i).getValue();
			arcAngle = value * 360 / sum; // ʹ�ðٷֱȼ���Ƕ�
			if (i + 1 == size) {
				arcAngle = 360 + shiftAngle - startAngle; // ��֤�պ�
				arcAngle = arcAngle > 0 ? arcAngle : 0;
			}

			Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, arcAngle, Arc2D.PIE);
			pies[i] = new Pie(arc, datas.get(i).getColor(), value, shadowDepth, 30);
			startAngle += arcAngle;
		}

		return pies;
	}

	private void drawPies(Graphics2D g2d, Pie[] pies, int selectedIndex) {
		int startIndex = 0; // �ӵڼ�����ͼ��ʼ����
		int endIndex = pies.length; // Ҫ���ı�ͼ������.
		boolean closed = (endIndex - startIndex == pies.length) ? true : false;
		boolean selected = (selectedIndex >= startIndex && selectedIndex < endIndex) ? true : false;
		FontMetrics metrics = g2d.getFontMetrics();

		// һ���Ի�����3DЧ����Ȼ���ٻ��Ʊ�ͼ��Ч���Ȼ��Ʊ�ͼ��ͬʱ���ƺ�
		for (int i = startIndex; i < endIndex; ++i) {
			if (i != selectedIndex) {
				Pie p = pies[i];
				g2d.setColor(p.getColor().darker());
				g2d.fill(p.getFrontSite());
			}
		}

		// ���û�бպ�ʱ����ѡ�еĲ��ǵ�һ�飬���һ�黭����
		if (!closed && selectedIndex != startIndex) {
			g2d.setColor(pies[startIndex].getColor().darker());
			g2d.fill(pies[startIndex].getLeftSite());
		}

		// ���û�бպ�ʱ����ѡ�еĲ������һ�飬�����һ�黭����
		if (!closed && selectedIndex + 1 != endIndex) {
			g2d.setColor(pies[endIndex - 1].getColor().darker());
			g2d.fill(pies[endIndex - 1].getRightSite());
		}

		// �б�ͼ��ѡ��
		if (selected) {
			int prevIndex = selectedIndex > startIndex ? (selectedIndex - 1) : endIndex - 1;
			int nextIndex = (selectedIndex + 1) >= endIndex ? startIndex : (selectedIndex + 1);

			// ǰһ������ǽ
			g2d.setColor(pies[prevIndex].getColor().darker());
			g2d.fill(pies[prevIndex].getRightSite());
			// ��һ������ǽ
			g2d.setColor(pies[nextIndex].getColor().darker());
			g2d.fill(pies[nextIndex].getLeftSite());
		}

		// ����ٻ��Ʊ�ͼ�����沿�֣��Ѳ���Ҫ�Ĳ������ص�
		for (int i = startIndex; i < endIndex; ++i) {
			if (i != selectedIndex) {
				Pie p = pies[i];
				g2d.setColor(p.getColor());
				g2d.fill(p.getArc());

				int sw = metrics.stringWidth(p.getLabel()) / 2;
				int sh = (metrics.getAscent()) / 2;
				int x = (int) (p.getLabelPosition().getX() - sw);
				int y = (int) (p.getLabelPosition().getY() + sh);
				g2d.setColor(Color.BLACK);
				g2d.drawString(p.getLabel(), x, y);
			}
		}

		// ���Ʊ�ѡ�еı�ͼ
		if (selected) {
			Pie p = pies[selectedIndex].getSelectedPie();
			g2d.setColor(p.getColor().darker());
			g2d.fill(p.getFrontSite());
			g2d.fill(p.getLeftSite());
			g2d.fill(p.getRightSite());
			g2d.setColor(p.getColor());
			g2d.fill(p.getArc());

			int sw = metrics.stringWidth(p.getLabel()) / 2;
			int sh = (metrics.getAscent()) / 2;
			int x = (int) (p.getLabelPosition().getX() - sw);
			int y = (int) (p.getLabelPosition().getY() + sh);
			g2d.setColor(Color.BLACK);
			g2d.drawString(p.getLabel(), x, y);
		}
	}

	

	@Override
	protected void calculateX() {

	}

	@Override
	protected void calculateY() {
		double num = Math.min(w, h);
		double value = num * R_SCALE;
		r = value;
		startX = x + (w - r) / 2;

		int height = (int) (num * 0.618); // �ƽ�ָ�
		startY = (h - height) / 2;
		pies = createPies((int) startX, (int) startY, (int) num, height, shadowDepth, shiftAngle, pieList);
	}

	@Override
	protected void drawGraph(Graphics2D g2d) {
		if (pies != null) {
			drawPies(g2d, pies, selectedPieIndex);
		}
	}

	class PieGraphBeanPropertyChangeAction implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String name = evt.getPropertyName();
			if (name.equals(PieGraphPointBean.PROPERTY_VALUE)) {
				calculateY();
			}
		}
	}
}
