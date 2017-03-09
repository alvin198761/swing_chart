package org.alvin.swing.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public abstract class Base2DGraph extends BaseGraph {

	private static final long serialVersionUID = 1L;

	public Base2DGraph() {
		this.addComponentListener(new ComponentAction());
	}

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
		g2d.fillRect(x, y, w, h);
		// �߿�
		g2d.setStroke(BOLD_STROKE);
		g2d.setColor(Color.black);
		g2d.drawRect(x, y, w, h);
		// ���̶�
		drawH_mark(g2d, fw, fh);
		drawV_mark(g2d, fw, fh);
		// ���㣬����,����״ͼ��
		drawGraph(g2d);
		// �ػ����
		repaint();
	}

	class ComponentAction extends ComponentAdapter {

		@Override
		public void componentResized(ComponentEvent e) {
			if (getWidth() != oldWidth) {
				oldWidth = getWidth();
				w = oldWidth - text_padding_left;
				calculateX();
			}
			if (getHeight() != oldHeight) {
				oldHeight = getHeight();
				h = oldHeight - text_padding_bottom;
				calculateY();
			}
			componentPropertyChanger();
			repaintImage();
		}
	}
}
