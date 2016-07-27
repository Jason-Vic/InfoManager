package com.crr;

import java.awt.Container;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class StudentUI {
	private JFrame frame = new JFrame("你是学生");
	private Container c = frame.getContentPane();

	public StudentUI() {
		frame.setSize(400, 250);
		c.setLayout(null);
		initFrame();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void initFrame() {
		JLabel l1 = new JLabel("欢迎登录学校后台管理系统…");
		l1.setBounds(65, 20, 300, 50);
		l1.setFont(new java.awt.Font("Dialog", 1, 20));
		c.add(l1);
	}
}
