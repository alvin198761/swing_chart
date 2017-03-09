package org.alvin.swing.chart;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pie3D extends JPanel {
	private int angleStart = 0;

	public Pie3D() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						angleStart += 1;
						repaint();
						Thread.sleep(30);
					}
				} catch (Exception e) {
				}
			}
		}).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int w = 380;
		int h = (int) (w * 0.618);
		// int angleStart = 290;
		int angleExtent = 75;

		Arc2D topArc = new Arc2D.Double(30, 30, w, h, angleStart, angleExtent, Arc2D.PIE);
		g2d.setColor(Color.decode("#FF7321").darker());
		drawShadow(g2d, topArc);
		g2d.setColor(Color.decode("#FF7321"));
		g2d.fill(topArc);

		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
		g2d.setComposite(ac);
		topArc = new Arc2D.Double(30, 30, w, h, angleStart + angleExtent, 30, Arc2D.PIE);
		g2d.setColor(Color.decode("#D718A5").darker());
		drawShadow(g2d, topArc);
		g2d.setColor(Color.decode("#D718A5"));
		g2d.fill(topArc);
	}

	private void drawShadow(Graphics2D g2d, Arc2D arc) {
		int shadowDapth = 20;
		Arc2D bottomArc = new Arc2D.Double(arc.getX(), arc.getY() + shadowDapth, arc.getWidth(), arc.getHeight(), arc.getAngleStart(), arc.getAngleExtent(), Arc2D.PIE);

		Point2D topCenter = new Point2D.Double(arc.getCenterX(), arc.getCenterY());
		Point2D topLeft = new Point2D.Double(arc.getStartPoint().getX(), arc.getStartPoint().getY());
		Point2D topRight = new Point2D.Double(arc.getEndPoint().getX(), arc.getEndPoint().getY());

		Point2D bottomCenter = new Point2D.Double(bottomArc.getCenterX(), bottomArc.getCenterY());
		Point2D bottomLeft = new Point2D.Double(bottomArc.getStartPoint().getX(), bottomArc.getStartPoint().getY());
		Point2D bottomRight = new Point2D.Double(bottomArc.getEndPoint().getX(), bottomArc.getEndPoint().getY());

		double[] xpoints = { topCenter.getX(), topLeft.getX(), topRight.getX(), bottomCenter.getX(), bottomLeft.getX(), bottomRight.getX() };
		double[] ypoints = { topCenter.getY(), topLeft.getY(), topRight.getY(), bottomCenter.getY(), bottomLeft.getY(), bottomRight.getY() };

		GeneralPath leftWall = new GeneralPath();
		leftWall.moveTo(xpoints[0], ypoints[0]);
		leftWall.lineTo(xpoints[1], ypoints[1]);
		leftWall.lineTo(xpoints[4], ypoints[4]);
		leftWall.lineTo(xpoints[3], ypoints[3]);
		leftWall.closePath();

		GeneralPath rightWall = new GeneralPath();
		rightWall.moveTo(xpoints[0], ypoints[0]);
		rightWall.lineTo(xpoints[2], ypoints[2]);
		rightWall.lineTo(xpoints[5], ypoints[5]);
		rightWall.lineTo(xpoints[3], ypoints[3]);
		rightWall.closePath();

		// ����ֱ𻭣��еĵط���������ߣ�ʹ��area����������
		Area area = new Area(bottomArc);
		area.add(new Area(leftWall));
		area.add(new Area(rightWall));
		g2d.fill(area);
	}

	public static Color getColorFromHex(String hex) {
		try {
			return new Color(Integer.valueOf(hex.substring(1), 16));
		} catch (Exception e) {
			return Color.BLACK;
		}
	}

}
