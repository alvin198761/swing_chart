package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.alvin.swing.chart.ExecutantGraph;
import org.alvin.swing.chart.LineGraphPointBean;


public class LineChartDemo_02 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LineChartDemo_02 frame = new LineChartDemo_02();
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
	public LineChartDemo_02() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		ExecutantGraph executantGraph = new ExecutantGraph();
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
			contentPane.add(executantGraph, BorderLayout.CENTER);
		}

		LineGraphPointBean bean = executantGraph.addLine();
		bean.addPoint(10);
		bean.addPoint(20);
		bean.addPoint(30);
		bean.addPoint(40);
		bean.addPoint(30);
		bean.addPoint(20);
		bean.addPoint(10);
		bean.setSelect(true);

	}

}
