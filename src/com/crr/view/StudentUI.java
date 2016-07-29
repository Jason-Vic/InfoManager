package com.crr.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import com.crr.app.LoginUI;
import com.crr.database.StuDataBase;
import com.crr.entity.StuClass;

public class StudentUI {
	private JFrame frame = new JFrame("你是学生");
	private Container c = frame.getContentPane();
	private StuClass stu;
	private StuDataBase stuDataBase;

	public StudentUI(StuClass stuClass) throws Exception {
		stu = stuClass;
		stuDataBase = new StuDataBase();
		frame.setSize(370, 400);
		c.setLayout(null);
		initFrame();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void initFrame() {
		JLabel l1 = new JLabel("欢迎" + stu.getName() + "登录学校系统…");
		JLabel stunum = new JLabel("学号:   " + stu.getNum());
		JLabel name = new JLabel("姓名:   " + stu.getName());
		JLabel birth = new JLabel("生日:   " + stu.getBirth());
		JLabel nation = new JLabel("民族:   " + stu.getNation());
		JLabel location = new JLabel("籍贯:   " + stu.getLocation());
		JLabel major = new JLabel("专业:   " + stu.getMajor());
		JLabel score = new JLabel("成绩:   " + stu.getScore());
		JButton edit = new JButton("修改密码");
		JButton exit = new JButton("重新登录");

		l1.setBounds(50, 10, 300, 50);
		stunum.setBounds(80, 70, 300, 30);
		name.setBounds(80, 100, 300, 30);
		birth.setBounds(80, 130, 300, 30);
		nation.setBounds(80, 160, 300, 30);
		location.setBounds(80, 190, 300, 30);
		major.setBounds(80, 220, 300, 30);
		score.setBounds(80, 250, 300, 30);
		edit.setBounds(80, 310, 90, 30);
		exit.setBounds(185, 310, 90, 30);

		l1.setFont(new java.awt.Font("Dialog", 1, 20));
		stunum.setFont(new java.awt.Font("Dialog", 1, 20));
		name.setFont(new java.awt.Font("Dialog", 1, 20));
		birth.setFont(new java.awt.Font("Dialog", 1, 20));
		nation.setFont(new java.awt.Font("Dialog", 1, 20));
		location.setFont(new java.awt.Font("Dialog", 1, 20));
		major.setFont(new java.awt.Font("Dialog", 1, 20));
		score.setFont(new java.awt.Font("Dialog", 1, 20));

		c.add(l1, BorderLayout.NORTH);
		c.add(stunum);
		c.add(name);
		c.add(birth);
		c.add(nation);
		c.add(location);
		c.add(major);
		c.add(score);
		c.add(edit);
		c.add(exit);

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JFrame chpassframe = new JFrame("修改密码");
				Container chc = chpassframe.getContentPane();
				chc.setLayout(null);
				chpassframe.setSize(260, 200);

				JLabel op = new JLabel("原始密码:");
				JLabel np = new JLabel("新  密  码:");
				JLabel nnp = new JLabel("确认密码:");
				final JPasswordField optx = new JPasswordField();
				final JPasswordField nptx = new JPasswordField();
				final JPasswordField nnptx = new JPasswordField();
				JButton ok = new JButton("修改密码");
				JButton exit = new JButton("取消");

				op.setFont(new java.awt.Font("Dialog", 1, 14));
				np.setFont(new java.awt.Font("Dialog", 1, 14));
				nnp.setFont(new java.awt.Font("Dialog", 1, 14));

				op.setBounds(15, 10, 70, 30);
				optx.setBounds(90, 10, 140, 30);
				np.setBounds(15, 50, 70, 30);
				nptx.setBounds(90, 50, 140, 30);
				nnp.setBounds(15, 90, 70, 30);
				nnptx.setBounds(90, 90, 140, 30);
				ok.setBounds(20, 130, 100, 30);
				exit.setBounds(130, 130, 100, 30);

				chc.add(op);
				chc.add(np);
				chc.add(nnp);
				chc.add(optx);
				chc.add(nptx);
				chc.add(nnptx);
				chc.add(ok);
				chc.add(exit);

				chpassframe.setResizable(false);
				chpassframe.setLocationRelativeTo(null);
				chpassframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				chpassframe.setVisible(true);

				ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						if (String.valueOf(nptx.getPassword()).equals(String.valueOf(nnptx.getPassword())) != true) {
							JOptionPane.showMessageDialog(chpassframe.getContentPane(), "两次密码不一致!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
						} else if (String.valueOf(optx.getPassword()).equals(stu.getPassword()) != true) {
							JOptionPane.showMessageDialog(chpassframe.getContentPane(), "原始密码有误!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
						} else if (String.valueOf(nptx.getPassword()).trim().length() == 0
								|| String.valueOf(nnptx.getPassword()).trim().length() == 0) {
							JOptionPane.showMessageDialog(chpassframe.getContentPane(), "请输入正确格式的密码!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
						} else {
							stu.setPasswd(String.valueOf(nptx.getPassword()));
							try {
								stuDataBase.updatePasswd(stu.getId(), stu);
								JOptionPane.showMessageDialog(chpassframe.getContentPane(), "密码更新成功,请重新登陆!", "系统信息",
										JOptionPane.ERROR_MESSAGE);
								chpassframe.dispose();
								frame.dispose();
								new LoginUI();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				;

				exit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						chpassframe.dispose();

					}
				});
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				frame.dispose();
				try {
					new LoginUI();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
