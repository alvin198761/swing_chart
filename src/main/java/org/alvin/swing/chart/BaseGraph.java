package org.alvin.swing.chart;

import org.alvin.swing.chart.bean.LegendBean;
import org.alvin.swing.chart.bean.PropertiesBean;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JPanel;

public abstract class BaseGraph extends JPanel {

	private static final long serialVersionUID = 1L;
	/*** �ճ�����д�� */
	protected static final int text_padding_left = 30;
	protected static final int text_padding_bottom = 25;
	/*** ���� */
	protected static final BasicStroke DESH_LINE_STROKE = new BasicStroke(.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] { 1.5f }, .0f);
	/*** ���� */
	protected static final BasicStroke BOLD_STROKE = new BasicStroke(2.0f);
	/*** Ĭ�����ߵĻ��� */
	protected static final BasicStroke DEFAULT_STROKE = new BasicStroke(1.0f);
	/** ��ɫ���� */
	public static final ColorManager colorManager = new ColorManager();
	/** ģʽ�ĳ��� */
	public static final String MODE_DRAW = "mode_draw";
	public static final String MODE_PRIVIEW = "mode_priview";
	/** Ĭ�ϱ�����ɫ */
	protected static final Color BG_COLOR = Color.WHITE;
	/*** ������ */
	protected int v_p = 5;
	/*** ������ */
	protected int h_p = 15;
	/*** ����̶����� */
	protected double hnum = 1;
	/*** ����̶����� */
	protected double vnum = 1;
	/*** ��ͼ��x�� */
	protected int x = text_padding_left;
	/*** ��ͼ��y�� */
	protected int y = 0;
	/*** ����Ŀ�� */
	protected int w = 0;
	/*** ����ĸ߶� */
	protected int h = 0;
	/*** ˫����ͼ */
	protected BufferedImage image;
	/*** ���Xֵ */
	protected int max_Xvalue;
	/*** ���Yֵ */
	protected int max_Yvalue;
	/*** ԭ���Ŀ�� */
	protected int oldWidth = 0;
	/*** ԭ���ĸ߶� */
	protected int oldHeight = 0;
	/** ��ǰ�����ģʽ */
	protected String mode = MODE_DRAW;
	/** ��� ����ɫ */
	protected Color gridBg = BG_COLOR;
	/** ��ͼ���ļ���·�� */
	protected String graphPath;
	/** ���� */
	private LinkedList<PropertiesBean> propertiesList;
	/** ˵�� */
	private LinkedList<LegendBean> legendList;

	public BaseGraph() {
		// this.addComponentListener(new ComponentAction());
	}

	@Override
	protected final void paintComponent(Graphics g) {
		if (image == null) {
			return;
		}
		g.drawImage(image, 0, 0, this);
		// System.out.println("��ͼ����");
	}

	/**
	 * ���ڼ���X��࣬�̶�
	 */
	protected abstract void calculateX();

	/**
	 * ���ڼ���Y��࣬�̶�
	 */
	protected abstract void calculateY();

	/**
	 * ������ͼ�ϻ��㣬����,����״ͼ,��ͼ��
	 * 
	 * @param g2d
	 */
	protected abstract void drawGraph(Graphics2D g2d);

	/**
	 * ����һ���鷽���������ڽ���ı��ʱ�����
	 */
	protected void componentPropertyChanger() {

	}

	/**
	 * ����һ���鷽�����õ�ͼ�����Լ��� ,�������ֻ���ڽ������л���ʵ��
	 */
	public LinkedList<PropertiesBean> getPropertyList() {
		if (propertiesList == null) {
			return propertiesList;
		}
		if (this instanceof LineGraph) {
			propertiesList = createLinePropertiesList();
		}
		if (this instanceof ColumnGraph) {
			propertiesList = createColumPropertiesList();
		}
		if (this instanceof PieGraph) {
			propertiesList = createPiePropertiesList();
		}
		return propertiesList;
	}

	private LinkedList<PropertiesBean> createPiePropertiesList() {
		return null;
	}

	private LinkedList<PropertiesBean> createColumPropertiesList() {
		return null;
	}

	private LinkedList<PropertiesBean> createLinePropertiesList() {
		return null;
	}

	/**
	 * ����һ���鷽�����õ�ͼ��������˵�� ,�������ֻ���ڽ������л���ʵ��
	 */
	public LinkedList<LegendBean> getChartLegenList() {
		legendList = new LinkedList<LegendBean>();
		LegendBean bean = new LegendBean();
		bean.setAvg(1);
		bean.setColor(Color.red);
		bean.setMax(100);
		bean.setMeascurement("#pass");
		bean.setName("aaa");
		legendList.add(bean);
		return legendList;
	}

	protected abstract void repaintImage();

	// {
	// // System.out.println("�������");
	// if (oldWidth <= 0 || oldHeight <= 0) {
	// return;
	// }
	// image = new BufferedImage(oldWidth, oldHeight, BufferedImage.TYPE_3BYTE_BGR);
	// Graphics2D g2d = image.createGraphics();
	// g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	// g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
	// // ����Ҫע��һ�£������������������ɳ�����������»�ͼ���ٶȼ���2�룬��Ҳû������������ô����
	// g2d.setFont(new Font(g2d.getFont().getName(), Font.PLAIN, 10));
	//
	// FontMetrics fm = g2d.getFontMetrics();
	// int fw = fm.charWidth('w');
	// int fh = fm.getAscent();
	// g2d.setColor(getBackground());
	// g2d.fillRect(0, 0, oldWidth, oldHeight);
	// // ����
	// g2d.setColor(gridBg);
	// g2d.fillRect(x, y, w, h);
	// // �߿�
	// g2d.setStroke(BOLD_STROKE);
	// g2d.setColor(Color.black);
	// g2d.drawRect(x, y, w, h);
	// // ���̶�
	// drawH_mark(g2d, fw, fh);
	// drawV_mark(g2d, fw, fh);
	// // ���㣬����,����״ͼ��
	// drawGraph(g2d);
	// // �ػ����
	// repaint();
	// }

	/**
	 * ������ ���̶� | ���̶�ֵ
	 * 
	 * @param g2d
	 * @param fw
	 * @param fh
	 */
	protected void drawV_mark(Graphics2D g2d, int fw, int fh) {
		String text;
		for (int i = 0, j = 0; i < w; i += h_p, j++) {
			g2d.setStroke(DEFAULT_STROKE);
			g2d.setColor(Color.black);
			if (j % 5 != 0) {
				g2d.drawLine(x + j * h_p, h, x + j * h_p, h + 5);
				continue;
			}
			g2d.drawLine(x + j * h_p, h, x + j * h_p, h + 7);
			// �����ֵ�λ��
			text = (int) (j * hnum) + "";
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
		for (int i = 0, j = 0; i < h; i += v_p, j++) {
			g2d.setStroke(DEFAULT_STROKE);
			g2d.setColor(Color.black);
			if (j % 5 != 0) {
				g2d.drawLine(x, h - j * v_p, x - 5, h - j * v_p);
				continue;
			}
			g2d.drawLine(x - 7, h - j * v_p, x, h - j * v_p);
			// �����ֵ�λ��
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

	@Override
	public final void update(Graphics g) {
		this.paintComponent(g);
	}

	public int getMax_Xvalue() {
		return max_Xvalue;
	}

	public void setMax_Xvalue(int maxXvalue) {
		if (maxXvalue <= max_Xvalue) {
			return;
		}
		max_Xvalue = maxXvalue;
		calculateX();
	}

	public int getMax_Yvalue() {
		return max_Yvalue;
	}

	public void setMax_Yvalue(int maxYvalue) {
		if (maxYvalue <= max_Yvalue) {
			return;
		}
		max_Yvalue = maxYvalue;
		calculateY();
	}

	public String getGraphPath() {
		return graphPath;
	}

	public void setGraphPath(String graphPath) {
		this.graphPath = graphPath;
	}

	public void reDraw() {
		repaintImage();
	}

}
