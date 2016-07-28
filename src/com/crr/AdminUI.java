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
import javax.swing.JOptionPane;
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
	private TeaDataBase teaDataBase;
	private StuDataBase stuDataBase;

	public AdminUI() throws Exception {
		majorDataBase = new MajorDataBase();
		teaDataBase = new TeaDataBase();
		stuDataBase = new StuDataBase();
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
				initTeaFrame();
			}
		});

		mStu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					initStuFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				addFreame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				addFreame.repaint();
				major.repaint();
				Container addc = addFreame.getContentPane();
				addc.setLayout(null);
				addFreame.setSize(250, 120);
				major.setBounds(10, 10, 220, 25);
				addc.add(major);
				JButton ok = new JButton("添加");
				ok.setBounds(45, 50, 60, 30);
				addc.add(ok);
				JButton cancel = new JButton("取消");
				cancel.setBounds(135, 50, 60, 30);
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
				int row = mmTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mMajFrame.getContentPane(), "请选中需要修改的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					final int sel = (int) mmTable.getValueAt(row, 0);
					final JFrame editFrame = new JFrame("修改专业");
					final JTextField emajor = new JTextField();
					Container editc = editFrame.getContentPane();
					editc.setLayout(null);
					editFrame.setSize(250, 120);
					emajor.setBounds(10, 10, 220, 25);
					editc.add(emajor);
					JButton ok = new JButton("修改");
					ok.setBounds(45, 50, 60, 30);
					editc.add(ok);
					JButton cancel = new JButton("取消");
					cancel.setBounds(135, 50, 60, 30);
					editc.add(cancel);
					editFrame.setLocationRelativeTo(null);
					editFrame.setResizable(false);
					editFrame.setVisible(true);

					ok.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String majorName = emajor.getText();
							if (majorName.trim().length() != 0) {
								System.out.println(majorName + "----1");
								try {
									majorDataBase.update(sel, majorName);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								mMajFrame.dispose();
								initMajFrame();
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
	}

	private void initTeaFrame() {
		final JFrame mTeaFrame = new JFrame("老师管理");

		Container mm = mTeaFrame.getContentPane();
		JPanel jPanel = new JPanel();
		JButton tDel = new JButton("删除");
		JButton tAdd = new JButton("添加");
		JButton tEdit = new JButton("修改");
		JButton tSearch = new JButton("查询");
		final JFrame addFreame = new JFrame("添加老师信息");
		final JTable tTable;
		final JTextField num = new JTextField();
		final JTextField name = new JTextField();
		final JTextField birth = new JTextField();
		final JTextField major = new JTextField();
		ArrayList<TeacherClass> tList = new ArrayList<TeacherClass>();

		mTeaFrame.setSize(600, 400);
		try {
			tList = teaDataBase.search();
			System.out.println("----2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] rowData = new Object[tList.size()][5];

		for (int i = 0; i < tList.size(); i++) {
			rowData[i][0] = tList.get(i).getId();
			rowData[i][1] = tList.get(i).getNum();
			rowData[i][2] = tList.get(i).getName();
			rowData[i][3] = tList.get(i).getBirth();
			rowData[i][4] = tList.get(i).getMajor();
		}

		String[] columnNames = { "编号", "工号", "姓名", "出生日期", "专业" };
		tTable = new JTable(rowData, columnNames);
		tTable.setRowHeight(30);
		tTable.updateUI();
		tTable.repaint();
		tTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(tTable);
		jScrollPane.updateUI();
		jScrollPane.repaint();
		mm.add(jScrollPane, BorderLayout.CENTER);
		tDel.setBounds(30, 230, 60, 30);
		jPanel.add(tDel);
		tAdd.setBounds(100, 230, 60, 30);
		jPanel.add(tAdd);
		tEdit.setBounds(170, 230, 60, 30);
		jPanel.add(tEdit);
		tSearch.setBounds(240, 230, 60, 30);
		jPanel.add(tSearch);
		mm.add(jPanel, BorderLayout.SOUTH);

		mTeaFrame.setResizable(false);
		mTeaFrame.setLocationRelativeTo(null);
		// mMajFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mTeaFrame.setVisible(true);

		tDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tTable.getSelectedRow();
				int sel = (int) tTable.getValueAt(row, 0);
				System.out.println(sel + "-----------5");

				try {
					teaDataBase.delete(sel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mTeaFrame.dispose();
				initTeaFrame();
			}
		});

		tAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addFreame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				addFreame.repaint();
				major.repaint();
				Container addc = addFreame.getContentPane();
				addc.setLayout(null);
				addFreame.setSize(250, 200);
				JLabel numlb = new JLabel("工    号:");
				numlb.setBounds(10, 10, 50, 25);
				addc.add(numlb);
				num.setBounds(65, 10, 160, 25);
				addc.add(num);
				JLabel namelb = new JLabel("姓    名:");
				namelb.setBounds(10, 40, 50, 25);
				addc.add(namelb);
				name.setBounds(65, 40, 160, 25);
				addc.add(name);
				JLabel birthlb = new JLabel("生    日:");
				birthlb.setBounds(10, 70, 50, 25);
				addc.add(birthlb);
				birth.setBounds(65, 70, 160, 25);
				addc.add(birth);
				JLabel majorlb = new JLabel("专    业:");
				majorlb.setBounds(10, 100, 50, 25);
				addc.add(majorlb);
				major.setBounds(65, 100, 160, 25);
				addc.add(major);
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
						String birthS = birth.getText();
						String majorS = major.getText();
						if ((numS.trim().length() != 0) && (nameS.trim().length() != 0) && (birthS.trim().length() != 0)
								&& (majorS.trim().length() != 0)) {
							try {
								TeacherClass teacherClass = new TeacherClass(numS, nameS, birthS, majorS);
								teaDataBase.insert(teacherClass);
								mTeaFrame.dispose();
								initTeaFrame();
								;
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(mTeaFrame.getContentPane(), "请填写正确的数据!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
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

		tEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mTeaFrame.getContentPane(), "请选中需要修改的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					final int sel = (int) tTable.getValueAt(row, 0);
					final JFrame editFrame = new JFrame("修改老师信息");
					final JTextField num = new JTextField();
					final JTextField name = new JTextField();
					final JTextField birth = new JTextField();
					final JTextField major = new JTextField();
					Container editc = editFrame.getContentPane();
					editc.setLayout(null);
					editFrame.setSize(250, 200);
					JLabel numlb = new JLabel("工    号:");
					numlb.setBounds(10, 10, 50, 25);
					editc.add(numlb);
					num.setBounds(65, 10, 160, 25);
					editc.add(num);
					JLabel namelb = new JLabel("姓    名:");
					namelb.setBounds(10, 40, 50, 25);
					editc.add(namelb);
					name.setBounds(65, 40, 160, 25);
					editc.add(name);
					JLabel birthlb = new JLabel("生    日:");
					birthlb.setBounds(10, 70, 50, 25);
					editc.add(birthlb);
					birth.setBounds(65, 70, 160, 25);
					editc.add(birth);
					JLabel majorlb = new JLabel("专    业:");
					majorlb.setBounds(10, 100, 50, 25);
					editc.add(majorlb);
					major.setBounds(65, 100, 160, 25);
					editc.add(major);
					JButton ok = new JButton("修改");
					ok.setBounds(45, 135, 60, 30);
					editc.add(ok);
					JButton cancel = new JButton("取消");
					cancel.setBounds(135, 135, 60, 30);
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
							String birthS = birth.getText();
							String majorS = major.getText();
							if ((numS.trim().length() != 0) && (nameS.trim().length() != 0)
									&& (birthS.trim().length() != 0) && (majorS.trim().length() != 0)) {
								try {
									TeacherClass teacherClass = new TeacherClass(numS, nameS, birthS, majorS);
									teaDataBase.update(sel, teacherClass);
									mTeaFrame.dispose();
									initTeaFrame();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(mTeaFrame.getContentPane(), "请填写正确的数据!", "系统信息",
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
	}

	private void initStuFrame() throws SQLException {
		final JFrame mStuFrame = new JFrame("学生管理");

		Container mm = mStuFrame.getContentPane();
		JPanel jPanel = new JPanel();
		JButton tDel = new JButton("删除");
		JButton tAdd = new JButton("添加");
		JButton tEdit = new JButton("修改");
		JButton tSearch = new JButton("查询");
		final JFrame addFreame = new JFrame("添加学生信息");
		final JTable tTable;
		final JTextField num = new JTextField();
		final JTextField name = new JTextField();
		final JTextField birth = new JTextField();
		final JTextField mz = new JTextField();
		final JTextField jg = new JTextField();
		final JTextField major = new JTextField();
		final JTextField score = new JTextField();
		final JTextField passwd = new JTextField();
		ArrayList<StuClass> tList = new ArrayList<StuClass>();

		mStuFrame.setSize(600, 400);
		tList = stuDataBase.search();
		System.out.println("----2");
		Object[][] rowData = new Object[tList.size()][8];

		for (int i = 0; i < tList.size(); i++) {
			rowData[i][0] = tList.get(i).getId();
			rowData[i][1] = tList.get(i).getNum();
			rowData[i][2] = tList.get(i).getName();
			rowData[i][3] = tList.get(i).getBirth();
			rowData[i][4] = tList.get(i).getNation();
			rowData[i][5] = tList.get(i).getLocation();
			rowData[i][6] = tList.get(i).getMajor();
			rowData[i][7] = tList.get(i).getScore();
		}

		String[] columnNames = { "编号", "学号", "姓名", "出生日期", "民族", "籍贯", "专业", "成绩" };
		tTable = new JTable(rowData, columnNames);
		tTable.setRowHeight(30);
		tTable.updateUI();
		tTable.repaint();
		tTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(tTable);
		jScrollPane.updateUI();
		jScrollPane.repaint();
		mm.add(jScrollPane, BorderLayout.CENTER);
		tDel.setBounds(30, 230, 60, 30);
		jPanel.add(tDel);
		tAdd.setBounds(100, 230, 60, 30);
		jPanel.add(tAdd);
		tEdit.setBounds(170, 230, 60, 30);
		jPanel.add(tEdit);
		tSearch.setBounds(240, 230, 60, 30);
		jPanel.add(tSearch);
		mm.add(jPanel, BorderLayout.SOUTH);

		mStuFrame.setResizable(false);
		mStuFrame.setLocationRelativeTo(null);
		// mMajFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mStuFrame.setVisible(true);

		tDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tTable.getSelectedRow();
				int sel = (int) tTable.getValueAt(row, 0);
				System.out.println(sel + "-----------5");

				try {
					stuDataBase.delete(sel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mStuFrame.dispose();
				try {
					initStuFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		tAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addFreame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				addFreame.repaint();
				major.repaint();
				Container addc = addFreame.getContentPane();
				addc.setLayout(null);
				addFreame.setSize(250, 300);
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
				JLabel birthlb = new JLabel("生    日:");
				birthlb.setBounds(10, 70, 50, 25);
				addc.add(birthlb);
				birth.setBounds(65, 70, 160, 25);
				addc.add(birth);
				JLabel mzlb = new JLabel("民    族:");
				mzlb.setBounds(10, 100, 50, 25);
				addc.add(mzlb);
				mz.setBounds(65, 100, 160, 25);
				addc.add(mz);
				JLabel jglb = new JLabel("籍    贯:");
				jglb.setBounds(10, 130, 50, 25);
				addc.add(jglb);
				jg.setBounds(65, 130, 160, 25);
				addc.add(jg);
				JLabel zylb = new JLabel("专    业:");
				zylb.setBounds(10, 160, 50, 25);
				addc.add(zylb);
				major.setBounds(65, 160, 160, 25);
				addc.add(major);
				JLabel scorelb = new JLabel("成    绩:");
				scorelb.setBounds(10, 190, 50, 25);
				addc.add(scorelb);
				score.setBounds(65, 190, 160, 25);
				addc.add(score);
				JButton ok = new JButton("添加");
				ok.setBounds(45, 230, 60, 30);
				addc.add(ok);
				JButton cancel = new JButton("取消");
				cancel.setBounds(135, 230, 60, 30);
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
						String birthS = birth.getText();
						String mzS = mz.getText();
						String jgS = jg.getText();
						String majorS = major.getText();
						String scoreS = score.getText();

						if ((numS.trim().length() != 0) && (nameS.trim().length() != 0) && (birthS.trim().length() != 0)
								&& (mzS.trim().length() != 0) && (jgS.trim().length() != 0)
								&& (majorS.trim().length() != 0) && (scoreS.trim().length() != 0)) {
							try {
								StuClass stuClass = new StuClass(numS, nameS, birthS, mzS, jgS, majorS, scoreS);
								stuDataBase.insert(stuClass);
								mStuFrame.dispose();
								initStuFrame();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(mStuFrame.getContentPane(), "请填写正确的数据!", "系统信息",
									JOptionPane.ERROR_MESSAGE);
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

		tEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(mStuFrame.getContentPane(), "请选中需要修改的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					final int sel = (int) tTable.getValueAt(row, 0);
					final JFrame editFrame = new JFrame("修改学生信息");
					Container addc = editFrame.getContentPane();
					addc.setLayout(null);
					editFrame.setSize(250, 300);
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
					JLabel birthlb = new JLabel("生    日:");
					birthlb.setBounds(10, 70, 50, 25);
					addc.add(birthlb);
					birth.setBounds(65, 70, 160, 25);
					addc.add(birth);
					JLabel mzlb = new JLabel("民    族:");
					mzlb.setBounds(10, 100, 50, 25);
					addc.add(mzlb);
					mz.setBounds(65, 100, 160, 25);
					addc.add(mz);
					JLabel jglb = new JLabel("籍    贯:");
					jglb.setBounds(10, 130, 50, 25);
					addc.add(jglb);
					jg.setBounds(65, 130, 160, 25);
					addc.add(jg);
					JLabel zylb = new JLabel("专    业:");
					zylb.setBounds(10, 160, 50, 25);
					addc.add(zylb);
					major.setBounds(65, 160, 160, 25);
					addc.add(major);
					JLabel scorelb = new JLabel("成    绩:");
					scorelb.setBounds(10, 190, 50, 25);
					addc.add(scorelb);
					score.setBounds(65, 190, 160, 25);
					addc.add(score);
					JButton ok = new JButton("添加");
					ok.setBounds(45, 230, 60, 30);
					addc.add(ok);
					JButton cancel = new JButton("取消");
					cancel.setBounds(135, 230, 60, 30);
					addc.add(cancel);
					editFrame.setLocationRelativeTo(null);
					editFrame.setResizable(false);
					editFrame.setVisible(true);

					ok.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String numS = num.getText();
							String nameS = name.getText();
							String birthS = birth.getText();
							String mzS = mz.getText();
							String jgS = jg.getText();
							String majorS = major.getText();
							String scoreS = score.getText();
							if ((numS.trim().length() != 0) && (nameS.trim().length() != 0)
									&& (birthS.trim().length() != 0) && (mzS.trim().length() != 0)
									&& (jgS.trim().length() != 0) && (majorS.trim().length() != 0)
									&& (scoreS.trim().length() != 0)) {
								try {
									StuClass stuClass = new StuClass(numS, nameS, birthS, mzS, jgS, majorS, scoreS);
									stuDataBase.update(sel, stuClass);
									mStuFrame.dispose();
									initStuFrame();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(mStuFrame.getContentPane(), "请填写正确的数据!", "系统信息",
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
	}

}
