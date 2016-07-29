package com.crr.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import com.crr.app.LoginUI;
import com.crr.database.MajorDataBase;
import com.crr.database.StuDataBase;
import com.crr.entity.StuClass;

public class TeacherUI {
	private StuDataBase stuDataBase;
	private static MajorDataBase majorDataBase;
	private String thisMajor;

	public TeacherUI(String majorT) throws Exception {
		thisMajor = majorT;
		final JFrame mScoreFrame = new JFrame("学生成绩管理");
		stuDataBase = new StuDataBase();
		majorDataBase = new MajorDataBase();
		Container mm = mScoreFrame.getContentPane();
		JPanel jPanel = new JPanel();
		JButton tShow = new JButton("查看");
		JButton tAdd = new JButton("添加");
		JButton tEdit = new JButton("修改");
		JButton tSearch = new JButton("查询");
		JButton tReLogin = new JButton("重新登录");

		final JTable tTable;

		ArrayList<StuClass> tList = new ArrayList<StuClass>();

		
		//初始化教师界面 一个表格的数据适配和几个按钮的监控
		mScoreFrame.setSize(600, 400);
		try {
			tList = stuDataBase.searchMajor(thisMajor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] rowData = new Object[tList.size()][4];

		for (int i = 0; i < tList.size(); i++) {
			rowData[i][0] = tList.get(i).getId();
			rowData[i][1] = tList.get(i).getNum();
			rowData[i][2] = tList.get(i).getName();
			rowData[i][3] = tList.get(i).getMajor();
		}

		String[] columnNames = { "编号", "学号", "姓名", "专业" };
		tTable = new JTable(rowData, columnNames);
		tTable.getTableHeader().setReorderingAllowed(false);
		tTable.getTableHeader().setResizingAllowed(false);
		tTable.setRowHeight(30);
		tTable.updateUI();
		tTable.repaint();
		tTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(tTable);
		jScrollPane.updateUI();
		jScrollPane.repaint();
		mm.add(jScrollPane, BorderLayout.CENTER);
		tShow.setBounds(30, 230, 60, 30);
		jPanel.add(tShow);
		tAdd.setBounds(100, 230, 60, 30);
		jPanel.add(tAdd);
		tEdit.setBounds(170, 230, 60, 30);
		jPanel.add(tEdit);
		tSearch.setBounds(240, 230, 60, 30);
		jPanel.add(tSearch);
		tReLogin.setBounds(310, 230, 60, 30);
		jPanel.add(tReLogin);
		mm.add(jPanel, BorderLayout.SOUTH);

		mScoreFrame.setResizable(false);
		mScoreFrame.setLocationRelativeTo(null);
		mScoreFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mScoreFrame.setVisible(true);

		tShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mScoreFrame.getContentPane(), "请选中需要查看的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int sel = (int) tTable.getValueAt(row, 0);
					final JFrame frame = new JFrame("学生信息");
					Container c = frame.getContentPane();
					frame.setSize(220, 250);
					StuClass stuClass = null;
					try {
						stuClass = stuDataBase.searchOne(sel);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JLabel num = new JLabel("编号:   " + stuClass.getId());
					JLabel stunum = new JLabel("学号:   " + stuClass.getNum());
					JLabel name = new JLabel("姓名:   " + stuClass.getName());
					JLabel major = new JLabel("专业:   " + stuClass.getMajor());
					JLabel score = new JLabel("分数:   " + stuClass.getScore());
					JButton close = new JButton("关闭");

					num.setBounds(40, 20, 150, 30);
					stunum.setBounds(40, 50, 150, 30);
					name.setBounds(40, 80, 150, 30);
					major.setBounds(40, 110, 150, 30);
					score.setBounds(40, 140, 150, 30);
					close.setBounds(60, 180, 80, 30);

					num.setFont(new java.awt.Font("Dialog", 1, 15));
					stunum.setFont(new java.awt.Font("Dialog", 1, 15));
					name.setFont(new java.awt.Font("Dialog", 1, 15));
					major.setFont(new java.awt.Font("Dialog", 1, 15));
					score.setFont(new java.awt.Font("Dialog", 1, 15));

					c.add(num);
					c.add(stunum);
					c.add(name);
					c.add(major);
					c.add(score);
					c.add(close);
					c.setLayout(null);

					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

					close.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							frame.dispose();
						}
					});
				}
			}
		});

		tAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JFrame addFreame = new JFrame("录入成绩");
				final JTextField num = new JTextField();
				final JTextField name = new JTextField();
				final JTextField score = new JTextField();
				addFreame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				addFreame.repaint();
				score.repaint();
				Container addc = addFreame.getContentPane();
				addc.setLayout(null);
				addFreame.setSize(250, 200);
				JLabel numlb = new JLabel("学    号:");
				numlb.setBounds(10, 10, 50, 25);
				addc.add(numlb);
				num.setBounds(65, 10, 160, 25);
				addc.add(num);
				JLabel namelb = new JLabel("姓    名:");
				namelb.setBounds(10, 40, 50, 25);
				addc.add(namelb);
				name.setBounds(65, 40, 160, 25);
				addc.add(name);

				JLabel majorlb = new JLabel("专    业:");
				majorlb.setBounds(10, 70, 50, 25);
				addc.add(majorlb);

				JLabel majorlbT = new JLabel(thisMajor);
				majorlbT.setBounds(65, 70, 160, 25);
				addc.add(majorlbT);
				JLabel scorelb = new JLabel("分    数:");
				scorelb.setBounds(10, 100, 50, 25);
				addc.add(scorelb);
				score.setBounds(65, 100, 160, 25);
				addc.add(score);
				JButton ok = new JButton("添加");
				ok.setBounds(45, 135, 60, 30);
				addc.add(ok);
				JButton cancel = new JButton("取消");
				cancel.setBounds(135, 135, 60, 30);
				addc.add(cancel);
				addFreame.setLocationRelativeTo(null);
				addFreame.setResizable(false);
				addFreame.setVisible(true);

				ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String numS = num.getText();
						String nameS = name.getText();
						String birthS = "notSet";
						String mzS = "notSet";
						String jgS = "notSet";
						String majorS = thisMajor;
						String scoreS = score.getText();
						if ((numS.trim().length() != 0) && (nameS.trim().length() != 0) && (birthS.trim().length() != 0)
								&& (mzS.trim().length() != 0) && (jgS.trim().length() != 0)
								&& (majorS.trim().length() != 0) && (scoreS.trim().length() != 0)) {
							try {
								StuClass stuClass = new StuClass(numS, nameS, birthS, mzS, jgS, majorS, scoreS);
								stuDataBase.insert(stuClass);
								addFreame.dispose();
								mScoreFrame.dispose();
								new TeacherUI(thisMajor);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(addFreame.getContentPane(), "请填写正确的数据!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
						}
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

		tEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mScoreFrame.getContentPane(), "请选中需要修改的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					final int sel = (int) tTable.getValueAt(row, 0);
					final JFrame editFrame = new JFrame("修改学生信息");
					final JTextField num = new JTextField();
					final JTextField name = new JTextField();
					final JTextField score = new JTextField();
					StuClass stuClass = null;
					Container editc = editFrame.getContentPane();
					try {
						stuClass = stuDataBase.searchOne(sel);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					editc.setLayout(null);
					editFrame.setSize(250, 200);
					JLabel numlb = new JLabel("学    号:");
					JLabel namelb = new JLabel("姓    名:");
					JLabel majorlb = new JLabel("专    业:");
					JLabel majorlbT = new JLabel(thisMajor);
					JLabel scorelb = new JLabel("分    数:");
					JButton ok = new JButton("修改");
					JButton cancel = new JButton("取消");

					numlb.setBounds(10, 10, 50, 25);
					num.setBounds(65, 10, 160, 25);
					namelb.setBounds(10, 40, 50, 25);
					name.setBounds(65, 40, 160, 25);
					majorlb.setBounds(10, 70, 50, 25);
					majorlbT.setBounds(65, 70, 160, 25);
					scorelb.setBounds(10, 100, 50, 25);
					score.setBounds(65, 100, 160, 25);
					ok.setBounds(45, 135, 60, 30);
					cancel.setBounds(135, 135, 60, 30);

					num.setText((String) tTable.getValueAt(row, 1));
					name.setText((String) tTable.getValueAt(row, 2));
					score.setText(stuClass.getScore());

					editc.add(numlb);
					editc.add(num);
					editc.add(namelb);
					editc.add(name);
					editc.add(majorlb);
					editc.add(majorlbT);
					editc.add(scorelb);
					editc.add(score);
					editc.add(ok);
					editc.add(cancel);

					editFrame.setLocationRelativeTo(null);
					editFrame.setResizable(false);
					editFrame.setVisible(true);

					ok.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String numS = num.getText();
							String nameS = name.getText();
							String scoreS = score.getText();
							String majorS = thisMajor;

							if ((numS.trim().length() != 0) && (nameS.trim().length() != 0)
									&& (majorS.trim().length() != 0) && (scoreS.trim().length() != 0)) {
								try {
									StuClass stuClass = new StuClass(numS, nameS, majorS, scoreS);
									stuDataBase.updateScore(sel, stuClass);
									mScoreFrame.dispose();
									mScoreFrame.repaint();
									new TeacherUI(thisMajor);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(mScoreFrame.getContentPane(), "请填写正确的数据!", "系统信息",
										JOptionPane.ERROR_MESSAGE);
							}
							editFrame.dispose();
						}
					});

					cancel.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							editFrame.dispose();
						}
					});

				}
			}
		});

		tSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					final JFrame searchFreame = new JFrame("输入学生学号");
					final JTextField num = new JTextField();
					JButton ok = new JButton("查询");
					JButton cancel = new JButton("取消");
					Container addc = searchFreame.getContentPane();
					addc.setLayout(null);
					searchFreame.setSize(250, 120);
					num.setBounds(10, 10, 220, 25);
					addc.add(num);
					ok.setBounds(45, 50, 60, 30);
					addc.add(ok);
					cancel.setBounds(135, 50, 60, 30);
					addc.add(cancel);
					searchFreame.setLocationRelativeTo(null);
					searchFreame.setResizable(false);
					searchFreame.setVisible(true);
					// new SearchStuUI("1");
					ok.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (num.getText().trim().length() != 0) {
								ArrayList<StuClass> tList = new ArrayList<StuClass>();
								try {
									tList = stuDataBase.search(num.getText().trim(),thisMajor);
									if (tList.size() > 0) {
										searchFreame.dispose();
										new SearchStuUI(tList, 2);
									} else {
										searchFreame.dispose();
										JOptionPane.showMessageDialog(searchFreame.getContentPane(), "没有相关学生信息!",
												"系统信息", JOptionPane.ERROR_MESSAGE);
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(searchFreame.getContentPane(), "请填写正确的数据!", "系统信息",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					});
					cancel.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							searchFreame.dispose();
						}
					});
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		tReLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mScoreFrame.dispose();
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
