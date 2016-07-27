package com.crr;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Hello {

	public Hello() {

		JFrame f = new JFrame();

		Object[][] playerInfo = {
				{ "阿呆", new Integer(66), new Integer(32), new Integer(98),
						new Boolean(false), new Boolean(false) },
				{ "阿瓜", new Integer(85), new Integer(69), new Integer(154),
						new Boolean(true), new Boolean(false) }, };

		String[] Names = { "姓名", "语文", "数学", "总分", "及格", "作弊" };

		JTable table = new JTable(playerInfo, Names);
		table.setPreferredScrollableViewportSize(new Dimension(550, 30));

		JScrollPane scrollPane = new JScrollPane(table);

		f.getContentPane().add(scrollPane, BorderLayout.CENTER);
		f.getContentPane().add(new JButton("TEST"), BorderLayout.NORTH);
		// f.getContentPane().add(table,BorderLayout.CENTER);,问为什么把上面的这句改成这样就不能显示表头啊？

		f.setTitle("Simple Table");
		f.pack();
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String args[]) {

		Hello b = new Hello();
	}
}
