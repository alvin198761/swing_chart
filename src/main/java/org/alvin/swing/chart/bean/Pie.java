package org.alvin.swing.chart.bean;
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import org.alvin.swing.chart.GeometryUtil;

public class Pie {
    private Arc2D arc; // ����Ļ�������Բ�ϵ�һ����������Բ��һ����.
    private Area frontSite;
    private Area leftSite;
    private Area rightSite;
    private Color color;
    private Pie selectedPie;

    private Point2D arcMiddle;
    private Point2D labelPosition;

    private double value;
    private int shadowDepth;
    private double selectedShiftDis; // ��ѡ�еı�ͼ�������������ƶ��ľ���

    public Pie(Arc2D arc, Color color, double value) {
        this(arc, color, value, 10, 30);
    }

    public Pie(Arc2D arc, Color color, double value, int shadowDepth, double selectedShiftDis) {
        this.arc = arc;
        this.color = color;
        this.value = value;
        this.selectedShiftDis = selectedShiftDis;
        this.shadowDepth = shadowDepth;

        Arc2D arcBottom = new Arc2D.Double(arc.getX(), arc.getY() + shadowDepth, arc.getWidth(),
            arc.getHeight() + 0, arc.getAngleStart(), arc.getAngleExtent(), Arc2D.CHORD);
        Point2D[] topPs = getPointsOfArc(arc);
        Point2D[] bottomPs = getPointsOfArc(arcBottom);

        // Front site
        GeneralPath font = new GeneralPath();
        font.moveTo(topPs[1].getX(), topPs[1].getY());
        font.lineTo(topPs[2].getX(), topPs[2].getY());
        font.lineTo(bottomPs[2].getX(), bottomPs[2].getY());
        font.lineTo(bottomPs[1].getX(), bottomPs[1].getY());
        font.closePath();
        this.frontSite = new Area(arcBottom);
        this.frontSite.add(new Area(font));

        // Left site
        GeneralPath left = new GeneralPath();
        left.moveTo(topPs[0].getX(), topPs[0].getY());
        left.lineTo(topPs[1].getX(), topPs[1].getY());
        left.lineTo(bottomPs[1].getX(), bottomPs[1].getY());
        left.lineTo(topPs[0].getX(), topPs[0].getY() + 3);
        left.closePath();
        this.leftSite = new Area(left);

        // Right site
        GeneralPath right = new GeneralPath();
        right.moveTo(topPs[0].getX(), topPs[0].getY());
        right.lineTo(topPs[2].getX(), topPs[2].getY());
        right.lineTo(bottomPs[2].getX(), bottomPs[2].getY());
        right.lineTo(topPs[0].getX(), topPs[0].getY() + 3);
        right.closePath();
        this.rightSite = new Area(right);

        arcMiddle = calculateArcMiddle();

        // Label position: ���֮�Ĵ�
        Point2D c = getPieCenter();
        // Point2D m = getChordMiddle();
        Point2D m = arcMiddle;
        double dis = GeometryUtil.distanceOfPoints(c, m) * 0.8;
        labelPosition = GeometryUtil.extentPoint(c, m, dis);
    }

    // ȡ��Arc�ϵ������㣬�ڶ�Arc: center, left, right.
    public static Point2D[] getPointsOfArc(Arc2D arc) {
        Point2D center = new Point2D.Double(arc.getCenterX(), arc.getCenterY());
        Point2D left = arc.getStartPoint();
        Point2D right = arc.getEndPoint();
        Point2D[] points = new Point2D[] { center, left, right };

        return points;
    }

    public Pie getSelectedPie() {
        if (selectedPie == null) {
            selectedPie = createSeletecdPie();
        }

        return selectedPie;
    }

    private Pie createSeletecdPie() {
        // �����߷����ƶ�selectedShiftDis����λ
        Point2D c = getPieCenter();
        Point2D m = getChordMiddle();
        Point2D p = GeometryUtil.extentPoint(c, m, selectedShiftDis);

        double deltaX = p.getX() - c.getX();
        double deltaY = p.getY() - c.getY();
        double x = arc.getX() + deltaX;
        double y = arc.getY() + deltaY;

        Arc2D shiftArc = (Arc2D) arc.clone();
        shiftArc.setFrame(x, y, arc.getWidth(), arc.getHeight());

        return new Pie(shiftArc, color, value, shadowDepth, selectedShiftDis);
    }

    public Arc2D getArc() {
        return arc;
    }

    public Area getFrontSite() {
        return frontSite;
    }

    public Area getLeftSite() {
        return leftSite;
    }

    public Area getRightSite() {
        return rightSite;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point2D getLabelPosition() {
        return labelPosition;
    }

    public void setLabelPosition(Point2D labelPosition) {
        this.labelPosition = labelPosition;
    }

    public double getValue() {
        return value;
    }

    public String getLabel() {
        return value + "%";
    }

    // �ҵ����ĵ�
    public Point2D getChordMiddle() {
        return GeometryUtil.middlePoint(arc.getStartPoint(), arc.getEndPoint());
    }

    // ��ͼ��Բ��
    public Point2D getPieCenter() {
        return new Point2D.Double(arc.getCenterX(), arc.getCenterY());
    }

    // ���ϵ����ĵ�
    public Point2D getArcMiddle() {
        return arcMiddle;
    }

    private Point2D calculateArcMiddle() {
        // ����һ���µĻ�������չ�Ƕ�Ϊ��ǰ����һ��
        return new Arc2D.Double(arc.getX(), arc.getY(), arc.getWidth(), arc.getHeight(),
            arc.getAngleStart(), arc.getAngleExtent() / 2, Arc2D.PIE).getEndPoint();
    }
}
