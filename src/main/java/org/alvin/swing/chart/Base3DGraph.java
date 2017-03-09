package org.alvin.swing.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public abstract class Base3DGraph extends BaseGraph {

	private static final long serialVersionUID = 1L;
	/** �����б�ǵ�y */
	protected int ly;
	/** �����б�ǵ�x */
	protected int lx;
	/** ���±�б�ǵ�y */
	protected int by;
	/** ���±�б�ǵ�x */
	protected int bx;
	/** ���б�ǵĿ�� */
	protected double lw;
	/** �±�б�ǵĸ߶ȶ� */
	protected double bh;

	public Base3DGraph() {
		addComponentListener(new Component3DGraphAction());
	}

	protected static final int LEFT_SCALE = 31;
	protected static final int BOTTOM_SCALE = 11;

	@Override
	protected final void repaintImage() {
		System.out.println("�������");
		if (oldWidth <= 0 || oldHeight <= 0) {
			return;
		}
		image = new BufferedImage(oldWidth, oldHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = image.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		// ����Ҫע��һ�£������������������ɳ�����������»�ͼ���ٶȼ���2�룬��Ҳû������������ô����
		g2d.setFont(new Font(g2d.getFont().getName(), Font.PLAIN, 10));

		FontMetrics fm = g2d.getFontMetrics();
		int fw = fm.charWidth('w');
		int fh = fm.getAscent();
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, oldWidth, oldHeight);
		// ��ɫ����
		g2d.setColor(BG_COLOR);
		// ���м�ı߿�
		Rectangle rect = new Rectangle(x, y, w, h);
		g2d.fill(rect);
		// �߿�
		g2d.setStroke(BOLD_STROKE);
		g2d.setColor(Color.GRAY);
		g2d.draw(rect);
		// ����ߵ�б��
		g2d.setStroke(DEFAULT_STROKE);
		GeneralPath path = new GeneralPath();
		path.moveTo(lx, ly);
		path.lineTo(x, y);
		path.lineTo(x, h);
		path.lineTo(bx, by);
		path.lineTo(lx, ly);
		path.closePath();
		g2d.setColor(Color.YELLOW.brighter());
		g2d.fill(path);

		g2d.setColor(Color.black);
		g2d.draw(path);
		// ���ײ���б��
		path = new GeneralPath();
		path.moveTo(bx, by);
		path.lineTo(x, h);
		path.lineTo(x + w, h);
		path.lineTo(bx + w, by);
		path.lineTo(bx, by);
		path.closePath();
		g2d.draw(path);
		// ���̶�
		drawH_mark(g2d, fw, fh);
		drawV_mark(g2d, fw, fh);
		// ����״ͼ
		drawGraph(g2d);
		// �ػ����
		repaint();
	}

	@Override
	protected void drawH_mark(Graphics2D g2d, int fw, int fh) {
		String text;
		int x = lx;
		int h = by;
		for (int i = ly, j = 0; i < h; i += v_p, j++) {
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
			if (j == 0) {
				continue;
			}
			// ������
			g2d.setStroke(DESH_LINE_STROKE);
			g2d.setColor(Color.gray);
			g2d.drawLine(x, h - j * v_p, x + (int) lw, h - j * v_p - (int) lw);
			g2d.drawLine(x + (int) lw, h - j * v_p - (int) lw, getWidth(), h - j * v_p - (int) lw);
		}
	}

	class Component3DGraphAction extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			if (getWidth() != oldWidth) {
				System.out.println("getWidth:" + getWidth());
				oldWidth = getWidth();
				double sw = oldWidth - text_padding_left;
				lw = sw / LEFT_SCALE;
				w = (int) (sw - lw);
				lx = text_padding_left;
				x = lx + (int) lw;
				bx = lx;
				calculateX();
			}
			if (getHeight() != oldHeight) {
				System.out.println("getHeight:" + getHeight());
				oldHeight = getHeight();
				double sh = oldHeight - text_padding_bottom;
				bh = sh / BOTTOM_SCALE;
				h = (int) (sh - bh);
				ly = y + (int) lw;
				by = ly + h;
				calculateY();
			}
			componentPropertyChanger();
			repaintImage();
		}
	}

}
