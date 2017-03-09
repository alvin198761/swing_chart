package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.alvin.swing.chart.PieGraph;
import org.alvin.swing.chart.bean.PieGraphPointBean;


public class PieDemo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PieDemo frame = new PieDemo();
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
	public PieDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		PieGraph graph = new PieGraph();
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
			contentPane.add(graph, BorderLayout.CENTER);
		}
		PieGraphPointBean bean = graph.addPie();
		bean.setValue(10);

		bean = graph.addPie();
		bean.setValue(20);

		bean = graph.addPie();
		bean.setValue(30);

		bean = graph.addPie();
		bean.setValue(40);
	}

}
