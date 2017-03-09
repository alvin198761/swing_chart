/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.Toolkit;
import javax.swing.JFrame;
import org.alvin.swing.chart.Pie3D;

/**
 *
 * @author Administrator
 */
public class Pie3DDemo {
    
    
	private static void createGuiAndShow() {
		JFrame frame = new JFrame("");
		frame.getContentPane().add(new Pie3D());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
		int sh = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = 600;
		int h = 500;
		int x = (sw - w) / 2;
		int y = (sh - w) / 2 - 40;
		x = x > 0 ? x : 0;
		y = y > 0 ? y : 0;
		frame.setBounds(x, y, w, h);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createGuiAndShow();
	}
    
}
