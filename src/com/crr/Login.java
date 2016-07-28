package com.crr;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login {
	private JFrame frame = new JFrame("登录");
	private Container c = frame.getContentPane();
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton ok = new JButton("登陆");
	private JButton cancel = new JButton("取消");
	private JRadioButton btn1 = new JRadioButton("学生");
	private JRadioButton btn2 = new JRadioButton("老师");
	private JRadioButton btn3 = new JRadioButton("管理员");
	private ButtonGroup group = new ButtonGroup();

	public Login() {
		frame.setSize(350, 250);
		c.setLayout(new BorderLayout());
		initFrame();

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mUsername = username.getText();
				String mPasswd = String.valueOf(password.getPassword());
				// System.out.println("" + mUsername + " " + mPasswd);
				// if (mUsername.equals("admin") && mPasswd.equals("admin")) {
				try {
					new AdminUI();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				// }
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void initFrame() {
		// ����
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel lt = new JLabel("请登录");
		lt.setFont(new java.awt.Font("Dialog", 1, 20));
		titlePanel.add(lt);
		c.add(titlePanel, "North");

		// �в���
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		JLabel l1 = new JLabel("登陆名:");
		l1.setBounds(75, 20, 50, 20);
		JLabel l2 = new JLabel("密    码:");
		l2.setBounds(75, 60, 50, 20);
		JLabel l3 = new JLabel("角色:");
		l3.setBounds(75, 100, 50, 20);
		fieldPanel.add(l1);
		fieldPanel.add(l2);
		fieldPanel.add(l3);
		username.setBounds(125, 20, 140, 20);
		password.setBounds(125, 60, 140, 20);
		fieldPanel.add(username);
		fieldPanel.add(password);
		btn1.setBounds(120, 100, 60, 20);
		btn2.setBounds(180, 100, 60, 20);
		btn3.setBounds(240, 100, 80, 20);
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		fieldPanel.add(btn1);
		fieldPanel.add(btn2);
		fieldPanel.add(btn3);
		c.add(fieldPanel, "Center");

		// �ײ���ť
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		c.add(buttonPanel, "South");
	}

}
