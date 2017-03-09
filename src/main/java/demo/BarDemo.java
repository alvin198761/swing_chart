package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.alvin.swing.chart.ColumnBeanManager;
import org.alvin.swing.chart.ColumnGraph;
import org.alvin.swing.chart.ColumnGraphPointBean;


public class BarDemo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarDemo frame = new BarDemo();
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
	public BarDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		ColumnGraph columnGraph = new ColumnGraph();
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
			contentPane.add(columnGraph, BorderLayout.CENTER);
		}

		ColumnBeanManager manager;
		ColumnGraphPointBean bean;

		// ////////////////
		manager = columnGraph.addColumnManager();
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(30d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(34d);
		bean.setSelect(true);
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(34d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(36d);
		bean.setSelect(true);

		// ////////////////
		manager = columnGraph.addColumnManager();

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(67d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(67d);
		bean.setSelect(true);
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(63d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(17d);
		bean.setSelect(true);

		// ////////////////
		manager = columnGraph.addColumnManager();
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(27d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(87d);
		bean.setSelect(true);
		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(47d);
		bean.setSelect(true);

		bean = new ColumnGraphPointBean();
		manager.addColumn(bean);
		bean.setValue(100d);
		bean.setSelect(true);
	}
}
