package com.crr;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

public class AdminUI {
	private JFrame mainFrame = new JFrame("你是管理员");
	private Container c = mainFrame.getContentPane();
	private JButton mTeacher = new JButton("老师管理");
	private JButton mStu = new JButton("学生管理");
	private JButton mMajor = new JButton("专业管理");

	private JFrame mTeaFrame = new JFrame("老师管理");

	private JFrame mStuFrame = new JFrame("学生管理");
	private MajorDataBase majorDataBase;

	public AdminUI() throws Exception {
		majorDataBase = new MajorDataBase();
		mainFrame.setSize(400, 250);
		c.setLayout(null);
		initMainFrame();
		initMainListener();
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	private void initMainFrame() {
		JLabel l1 = new JLabel("欢迎登录学校后台管理系统…");
		l1.setBounds(65, 20, 300, 50);
		l1.setFont(new java.awt.Font("Dialog", 1, 20));
		c.add(l1);
		mTeacher.setBounds(30, 100, 95, 50);
		mStu.setBounds(145, 100, 95, 50);
		mMajor.setBounds(260, 100, 95, 50);
		c.add(mTeacher);
		c.add(mStu);
		c.add(mMajor);
	}

	private void initMainListener() {
		mTeacher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		mStu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		mMajor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// mm.setLayout(null);
				initMajFrame();
			}
		});

	}

	private void initMajFrame() {
		final JFrame mMajFrame = new JFrame("专业管理");

		Container mm = mMajFrame.getContentPane();
		JPanel jPanel = new JPanel();
		JButton mmDel = new JButton("删除");
		JButton mmAdd = new JButton("添加");
		JButton mmEdit = new JButton("修改");
		final JFrame addFreame = new JFrame("添加专业");
		final JTable mmTable;
		final JTextField major = new JTextField();
		ArrayList<Integer> mmList0 = new ArrayList<Integer>();
		ArrayList<String> mmList1 = new ArrayList<String>();
		mMajFrame.setSize(300, 400);
		try {
			mmList0 = majorDataBase.search0();
			mmList1 = majorDataBase.search1();
			System.out.println("----2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] rowData = new Object[mmList1.size()][2];

		for (int i = 0; i < mmList1.size(); i++) {
			rowData[i][0] = mmList0.get(i);
			rowData[i][1] = mmList1.get(i);
		}

		String[] columnNames = { "编号", "专业" };
		mmTable = new JTable(rowData, columnNames);
		mmTable.setRowHeight(30);
		mmTable.updateUI();
		mmTable.repaint();
		mmTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(mmTable);
		jScrollPane.updateUI();
		jScrollPane.repaint();
		mm.add(jScrollPane, BorderLayout.CENTER);
		mmDel.setBounds(30, 230, 60, 30);
		jPanel.add(mmDel);
		mmAdd.setBounds(100, 230, 60, 30);
		jPanel.add(mmAdd);
		mmEdit.setBounds(170, 230, 60, 30);
		jPanel.add(mmEdit);
		mm.add(jPanel, BorderLayout.SOUTH);

		mMajFrame.setResizable(false);
		mMajFrame.setLocationRelativeTo(null);
		// mMajFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mMajFrame.setVisible(true);

		mmDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = mmTable.getSelectedRow();
				int sel = (int) mmTable.getValueAt(row, 0);
				System.out.println(sel + "-----------5");

				try {
					majorDataBase.delete(sel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mMajFrame.dispose();
				initMajFrame();
			}
		});

		mmAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addFreame
						.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				addFreame.repaint();
				major.repaint();
				Container addc = addFreame.getContentPane();
				addc.setLayout(null);
				addFreame.setSize(200, 120);
				major.setBounds(10, 10, 175, 25);
				addc.add(major);
				JButton ok = new JButton("添加");
				ok.setBounds(20, 50, 60, 30);
				addc.add(ok);
				JButton cancel = new JButton("取消");
				cancel.setBounds(110, 50, 60, 30);
				addc.add(cancel);
				addFreame.setLocationRelativeTo(null);
				addFreame.setResizable(false);
				addFreame.setVisible(true);

				ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String majorName = major.getText();
						if (majorName.trim().length() != 0) {
							System.out.println(majorName + "----1");
							try {
								majorDataBase.insert(majorName);
								mMajFrame.dispose();
								initMajFrame();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						addFreame.dispose();
					}
				});

				cancel.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						addFreame.dispose();
					}
				});
			}
		});

		mmEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}
