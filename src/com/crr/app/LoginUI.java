package com.crr.app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crr.database.AdminDataBase;
import com.crr.database.StuDataBase;
import com.crr.database.TeaDataBase;
import com.crr.entity.StuClass;
import com.crr.view.*;

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


/*
 * 登录界面
 */
public class LoginUI {
	private JFrame frame = new JFrame("登录");
	private Container c = frame.getContentPane();
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton ok = new JButton("登陆");
	private JButton cancel = new JButton("取消");
	private JRadioButton stu = new JRadioButton("学生");
	private JRadioButton tercher = new JRadioButton("老师");
	private JRadioButton adminer = new JRadioButton("管理员");
	private ButtonGroup group = new ButtonGroup();

	private boolean state = false;
	private AdminDataBase adminDataBase;
	private TeaDataBase teaDataBase;
	private StuDataBase stuDataBase;

	private ArrayList<String> usernameList;   //保存从数据库中得到的帐号表
	private ArrayList<String> passwdList;     //保存从数据库中得到的密码表
	private ArrayList<String> majorList;      //保存从数据库中得到的专业表

	public LoginUI() throws Exception {
		adminDataBase = new AdminDataBase();  //初始化数据库
		teaDataBase = new TeaDataBase();
		stuDataBase = new StuDataBase();

		frame.setSize(350, 250);
		c.setLayout(new BorderLayout());
		initFrame();

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mUsername = username.getText();
				String mPasswd = String.valueOf(password.getPassword());
				ArrayList<StuClass> tList = new ArrayList<StuClass>();
				if (mUsername.trim().length() != 0 && mPasswd.trim().length() != 0) {
					//如果学生选项被选中，后面的老师/管理员不再重复
					if (stu.isSelected()) {  
						try {
							usernameList = stuDataBase.searchUser();
							passwdList = stuDataBase.searchPasswd();
							tList = stuDataBase.search();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//比较填写的帐号密码和数据库中是否一致
						for (int i = 0; i < usernameList.size(); i++) {
							if (mUsername.equals(usernameList.get(i)) && mPasswd.equals(passwdList.get(i))) {
								try {
									//新建学生界面
									new StudentUI(tList.get(i));
									state = true;   //登录是否成功状态位
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								frame.dispose();  //登录界面释放
							}
						}
						//如果登录失败
						if (!state) {
							JOptionPane.showMessageDialog(frame.getContentPane(), "账号或密码错误!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
						}
						frame.repaint();  //界面重绘
					} else if (tercher.isSelected()) {
						try {
							usernameList = teaDataBase.searchUser();
							passwdList = teaDataBase.searchPasswd();
							majorList = teaDataBase.searchMajor();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						for (int i = 0; i < usernameList.size(); i++) {

							if (mUsername.equals(usernameList.get(i)) && mPasswd.equals(passwdList.get(i))) {
								try {
									new TeacherUI(majorList.get(i));
									state = true;
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								frame.dispose();
							}
						}
						if (!state) {
							JOptionPane.showMessageDialog(frame.getContentPane(), "账号或密码错误!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
						}
						frame.repaint();
					} else {
						try {
							usernameList = adminDataBase.search0();
							passwdList = adminDataBase.search1();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						for (int i = 0; i < usernameList.size(); i++) {
							if (mUsername.equals(usernameList.get(i)) && mPasswd.equals(passwdList.get(i))) {
								try {
									new AdminUI();
									state = true;
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								frame.dispose();
							}
						}
						if (!state) {
							JOptionPane.showMessageDialog(frame.getContentPane(), "账号或密码错误!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
						}
						frame.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(frame.getContentPane(), "请填写正确的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
					frame.repaint();
				}
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
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel lt = new JLabel("请登录");
		lt.setFont(new java.awt.Font("Dialog", 1, 20));
		titlePanel.add(lt);
		c.add(titlePanel, "North");

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
		username.setText("admin");
		password.setBounds(125, 60, 140, 20);
		password.setText("admin");
		fieldPanel.add(username);
		fieldPanel.add(password);
		stu.setBounds(120, 100, 60, 20);
		tercher.setBounds(180, 100, 60, 20);
		adminer.setBounds(240, 100, 80, 20);
		adminer.setSelected(true);
		group.add(stu);
		group.add(tercher);
		group.add(adminer);
		fieldPanel.add(stu);
		fieldPanel.add(tercher);
		fieldPanel.add(adminer);
		c.add(fieldPanel, "Center");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		c.add(buttonPanel, "South");
	}

}
