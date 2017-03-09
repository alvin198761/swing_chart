package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.alvin.swing.chart.LineGraph;
import org.alvin.swing.chart.LineGraphPointBean;


public class LineChartDemo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LineChartDemo frame = new LineChartDemo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LineChartDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		LineGraph lineGraph = new LineGraph();
		setContentPane(contentPane);
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.SOUTH);
		}
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.EAST);
		}
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.WEST);
		}
		{
			contentPane.add(lineGraph, BorderLayout.CENTER);
		}

		LineGraphPointBean bean = lineGraph.addLine();
		bean.addPoint(100);
		bean.addPoint(20);
		bean.addPoint(30);
		bean.addPoint(40);
		bean.addPoint(10);

	}

}
