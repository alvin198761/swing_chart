package org.alvin.swing.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * java���ִ��ϵ���
 * 
 * @author ��ֲ��
 * 
 */
public class VerticalTextLabel extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final Font font = new JLabel().getFont();
	private String text;

	public VerticalTextLabel() {
		setPreferredSize(new Dimension(25, 25));
	}

	public void setText(String text) {
		this.text = text;
		repaint();
	}

	@Override
	public final void update(Graphics g) {
		paintComponent(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.setFont(font);
		Graphics2D g2d = (Graphics2D) g;
		double w = g2d.getFontMetrics().bytesWidth(text.getBytes(), 0, text.length());
		g2d.translate(this.getWidth() >> 1, this.getHeight() >> 1);
		g2d.rotate(Math.PI / 2);
		String str = "";
		if (w > getHeight() && text.length() > 5) {
			str = text.substring(0, 5) + "...";
		} else {
			str = text;
		}
		g2d.drawString(str, -30, 2);
	}

}
