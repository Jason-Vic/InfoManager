package com.crr.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

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

import com.crr.app.LoginUI;
import com.crr.database.MajorDataBase;
import com.crr.database.StuDataBase;
import com.crr.database.TeaDataBase;
import com.crr.entity.StuClass;
import com.crr.entity.TeacherClass;

public class AdminUI {
	private JFrame mainFrame = new JFrame("你是管理员");
	private Container c = mainFrame.getContentPane();
	private JButton mTeacher = new JButton("老师管理");
	private JButton mStu = new JButton("学生管理");
	private JButton mMajor = new JButton("专业管理");
	private JButton mReLog = new JButton("重新登录");
	private static MajorDataBase majorDataBase;
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

	public static Vector<String> getMyVector() {
		Vector<String> strings = new Vector<>();
		ArrayList<String> list = null;
		try {
			list = majorDataBase.search1();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			strings.addElement(list.get(i));
		}
		return strings;
	}

	private void initMainFrame() {
		JLabel l1 = new JLabel("欢迎登录学校后台管理系统…");
		l1.setBounds(65, 20, 300, 50);
		l1.setFont(new java.awt.Font("Dialog", 1, 20));
		c.add(l1);
		mTeacher.setBounds(30, 100, 95, 50);
		mStu.setBounds(145, 100, 95, 50);
		mMajor.setBounds(260, 100, 95, 50);
		mReLog.setBounds(60, 165, 260, 40);
		c.add(mTeacher);
		c.add(mStu);
		c.add(mMajor);
		c.add(mReLog);
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

		mReLog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.dispose();
				try {
					new LoginUI();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

		mmDel.setBounds(30, 230, 60, 30);
		mmAdd.setBounds(100, 230, 60, 30);
		mmEdit.setBounds(170, 230, 60, 30);

		mm.add(jScrollPane, BorderLayout.CENTER);
		jPanel.add(mmDel);
		jPanel.add(mmAdd);
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
				if (row == -1) {
					JOptionPane.showMessageDialog(mMajFrame.getContentPane(), "请选中需要删除的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int sel = (int) mmTable.getValueAt(row, 0);
						majorDataBase.delete(sel);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mMajFrame.dispose();
					initMajFrame();
				}
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

				JButton ok = new JButton("添加");
				JButton cancel = new JButton("取消");

				major.setBounds(10, 10, 220, 25);
				ok.setBounds(45, 50, 60, 30);
				cancel.setBounds(135, 50, 60, 30);

				addc.add(major);
				addc.add(ok);
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
							try {
								addFreame.dispose();
								majorDataBase.insert(majorName);
								mMajFrame.dispose();
								initMajFrame();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(mMajFrame.getContentPane(), "请输入正确的数据!", "系统信息",
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

					JButton ok = new JButton("修改");
					JButton cancel = new JButton("取消");

					emajor.setBounds(10, 10, 220, 25);
					ok.setBounds(45, 50, 60, 30);
					cancel.setBounds(135, 50, 60, 30);

					editc.add(emajor);
					editc.add(ok);
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
		JScrollPane jScrollPane = new JScrollPane(tTable);

		tTable.setRowHeight(30);
		tTable.updateUI();
		tTable.repaint();
		tTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jScrollPane.updateUI();
		jScrollPane.repaint();

		mm.add(jScrollPane, BorderLayout.CENTER);
		tDel.setBounds(30, 230, 60, 30);
		tAdd.setBounds(100, 230, 60, 30);
		tEdit.setBounds(170, 230, 60, 30);
		tSearch.setBounds(240, 230, 60, 30);

		jPanel.add(tDel);
		jPanel.add(tAdd);
		jPanel.add(tEdit);
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
				if (row == -1) {
					JOptionPane.showMessageDialog(mTeaFrame.getContentPane(), "请选中需要删除的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int sel = (int) tTable.getValueAt(row, 0);
						teaDataBase.delete(sel);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mTeaFrame.dispose();
					initTeaFrame();
				}
			}
		});

		tAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addFreame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				addFreame.repaint();
				major.repaint();
				Container addc = addFreame.getContentPane();
				addc.setLayout(null);
				addFreame.setSize(250, 200);

				JLabel numlb = new JLabel("工    号:");
				JLabel namelb = new JLabel("姓    名:");
				JLabel birthlb = new JLabel("生    日:");
				JLabel majorlb = new JLabel("专    业:");
				JButton ok = new JButton("添加");
				JButton cancel = new JButton("取消");
				final JComboBox<String> majorBox = new JComboBox<String>(getMyVector());

				numlb.setBounds(10, 10, 50, 25);
				num.setBounds(65, 10, 160, 25);
				namelb.setBounds(10, 40, 50, 25);
				name.setBounds(65, 40, 160, 25);
				birthlb.setBounds(10, 70, 50, 25);
				birth.setBounds(65, 70, 160, 25);
				majorlb.setBounds(10, 100, 50, 25);
				majorBox.setBounds(65, 100, 160, 25);
				ok.setBounds(45, 135, 60, 30);
				cancel.setBounds(135, 135, 60, 30);

				addc.add(numlb);
				addc.add(num);
				addc.add(namelb);
				addc.add(name);
				addc.add(birthlb);
				addc.add(birth);
				addc.add(majorlb);
				addc.add(majorBox);
				addc.add(ok);
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
						String majorS = majorBox.getSelectedItem().toString().trim();
						if ((numS.trim().length() != 0) && (nameS.trim().length() != 0) && (birthS.trim().length() != 0)
								&& (majorBox.getSelectedItem().toString().trim().length() != 0)) {
							try {
								TeacherClass teacherClass = new TeacherClass(numS, nameS, birthS, majorS);
								teaDataBase.insert(teacherClass);
								addFreame.dispose();
								mTeaFrame.dispose();
								initTeaFrame();
							} catch (SQLException e1) {
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
					JOptionPane.showMessageDialog(mTeaFrame.getContentPane(), "请选中需要修改的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					final int sel = (int) tTable.getValueAt(row, 0);
					final JFrame editFrame = new JFrame("修改老师信息");
					final JTextField num = new JTextField();
					final JTextField name = new JTextField();
					final JTextField birth = new JTextField();
					Container editc = editFrame.getContentPane();
					editc.setLayout(null);
					editFrame.setSize(250, 200);

					JLabel numlb = new JLabel("工    号:");
					JLabel namelb = new JLabel("姓    名:");
					JLabel birthlb = new JLabel("生    日:");
					JLabel majorlb = new JLabel("专    业:");
					JButton ok = new JButton("修改");
					JButton cancel = new JButton("取消");
					final JComboBox<String> majorBox = new JComboBox<String>(getMyVector());

					numlb.setBounds(10, 10, 50, 25);
					num.setBounds(65, 10, 160, 25);
					namelb.setBounds(10, 40, 50, 25);
					name.setBounds(65, 40, 160, 25);
					birthlb.setBounds(10, 70, 50, 25);
					birth.setBounds(65, 70, 160, 25);
					majorlb.setBounds(10, 100, 50, 25);
					majorBox.setBounds(65, 100, 160, 25);
					ok.setBounds(45, 135, 60, 30);
					cancel.setBounds(135, 135, 60, 30);

					num.setText((String) tTable.getValueAt(row, 1));
					name.setText((String) tTable.getValueAt(row, 2));
					birth.setText((String) tTable.getValueAt(row, 3));

					editc.add(numlb);
					editc.add(num);
					editc.add(namelb);
					editc.add(name);
					editc.add(birthlb);
					editc.add(birth);
					editc.add(majorlb);
					editc.add(majorBox);
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
							String birthS = birth.getText();
							String majorS = majorBox.getSelectedItem().toString();
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

		tSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					final JFrame searchFreame = new JFrame("输入老师名字");
					final JTextField name = new JTextField();
					JButton ok = new JButton("查询");
					JButton cancel = new JButton("取消");
					Container addc = searchFreame.getContentPane();
					addc.setLayout(null);
					searchFreame.setSize(250, 120);
					name.setBounds(10, 10, 220, 25);
					addc.add(name);
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
							if (name.getText().trim().length() != 0) {
								ArrayList<TeacherClass> tList = new ArrayList<TeacherClass>();
								try {
									tList = teaDataBase.search(name.getText().trim());
									if (tList.size() > 0) {
										searchFreame.dispose();
										new SearchTeaUI(tList);
									} else {
										searchFreame.dispose();
										JOptionPane.showMessageDialog(searchFreame.getContentPane(), "没有相关老师信息!",
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
	}

	private void initStuFrame() throws SQLException {
		final JFrame mStuFrame = new JFrame("学生管理");
		Container mm = mStuFrame.getContentPane();
		JPanel jPanel = new JPanel();
		JButton tDel = new JButton("删除");
		JButton tAdd = new JButton("添加");
		JButton tEdit = new JButton("修改");
		JButton tSearch = new JButton("查询");

		final JTable tTable;
		ArrayList<StuClass> tList = new ArrayList<StuClass>();

		mStuFrame.setSize(600, 400);
		tList = stuDataBase.search();
		Object[][] rowData = new Object[tList.size()][7];

		for (int i = 0; i < tList.size(); i++) {
			rowData[i][0] = tList.get(i).getId();
			rowData[i][1] = tList.get(i).getNum();
			rowData[i][2] = tList.get(i).getName();
			rowData[i][3] = tList.get(i).getBirth();
			rowData[i][4] = tList.get(i).getNation();
			rowData[i][5] = tList.get(i).getLocation();
			rowData[i][6] = tList.get(i).getMajor();
		}

		String[] columnNames = { "编号", "学号", "姓名", "出生日期", "民族", "籍贯", "专业" };
		tTable = new JTable(rowData, columnNames);
		tTable.setRowHeight(30);
		tTable.updateUI();
		tTable.repaint();
		tTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(tTable);
		jScrollPane.updateUI();
		jScrollPane.repaint();

		tDel.setBounds(30, 230, 60, 30);
		tAdd.setBounds(100, 230, 60, 30);
		tEdit.setBounds(170, 230, 60, 30);
		tSearch.setBounds(240, 230, 60, 30);

		mm.add(jScrollPane, BorderLayout.CENTER);
		jPanel.add(tDel);
		jPanel.add(tAdd);
		jPanel.add(tEdit);
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
				if (row == -1) {
					JOptionPane.showMessageDialog(mStuFrame.getContentPane(), "请选中需要删除的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int sel = (int) tTable.getValueAt(row, 0);
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
			}
		});

		tAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JTextField num = new JTextField();
				final JTextField name = new JTextField();
				final JTextField birth = new JTextField();
				final JTextField mz = new JTextField();
				final JTextField jg = new JTextField();
				final JFrame addFreame = new JFrame("添加学生信息");
				addFreame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				addFreame.repaint();
				Container addc = addFreame.getContentPane();
				addc.setLayout(null);
				addFreame.setSize(250, 280);

				JLabel numlb = new JLabel("学    号:");
				JLabel namelb = new JLabel("姓    名:");
				JLabel birthlb = new JLabel("生    日:");
				JLabel mzlb = new JLabel("民    族:");
				JLabel jglb = new JLabel("籍    贯:");
				JLabel zylb = new JLabel("专    业:");
				JButton ok = new JButton("添加");
				JButton cancel = new JButton("取消");
				final JComboBox<String> majorBox = new JComboBox<String>(getMyVector());

				numlb.setBounds(10, 10, 50, 25);
				num.setBounds(65, 10, 160, 25);
				namelb.setBounds(10, 40, 50, 25);
				name.setBounds(65, 40, 160, 25);
				birthlb.setBounds(10, 70, 50, 25);
				birth.setBounds(65, 70, 160, 25);
				mzlb.setBounds(10, 100, 50, 25);
				mz.setBounds(65, 100, 160, 25);
				jglb.setBounds(10, 130, 50, 25);
				jg.setBounds(65, 130, 160, 25);
				zylb.setBounds(10, 160, 50, 25);
				majorBox.setBounds(65, 160, 160, 25);
				ok.setBounds(45, 200, 60, 30);
				cancel.setBounds(135, 200, 60, 30);

				addc.add(numlb);
				addc.add(num);
				addc.add(namelb);
				addc.add(name);
				addc.add(birthlb);
				addc.add(birth);
				addc.add(mzlb);
				addc.add(mz);
				addc.add(jglb);
				addc.add(jg);
				addc.add(zylb);
				addc.add(majorBox);
				addc.add(ok);
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
						String majorS = majorBox.getSelectedItem().toString();
						String scoreS = "notSet";

						if ((numS.trim().length() != 0) && (nameS.trim().length() != 0) && (birthS.trim().length() != 0)
								&& (mzS.trim().length() != 0) && (jgS.trim().length() != 0)
								&& (majorS.trim().length() != 0) && (scoreS.trim().length() != 0)) {
							try {
								StuClass stuClass = new StuClass(numS, nameS, birthS, mzS, jgS, majorS, scoreS);
								stuDataBase.insert(stuClass);
								addFreame.dispose();
								mStuFrame.dispose();
								initStuFrame();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							addFreame.dispose();
							JOptionPane.showMessageDialog(mStuFrame.getContentPane(), "请填写正确的数据!", "系统信息",
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
					JOptionPane.showMessageDialog(mStuFrame.getContentPane(), "请选中需要修改的数据!", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				} else {
					final JTextField num = new JTextField();
					final JTextField name = new JTextField();
					final JTextField birth = new JTextField();
					final JTextField mz = new JTextField();
					final JTextField jg = new JTextField();
					final int sel = (int) tTable.getValueAt(row, 0);
					final JFrame editFrame = new JFrame("修改学生信息");
					Container addc = editFrame.getContentPane();
					addc.setLayout(null);
					editFrame.setSize(250, 280);
					JLabel numlb = new JLabel("学    号:");
					JLabel namelb = new JLabel("姓    名:");
					JLabel birthlb = new JLabel("生    日:");
					JLabel mzlb = new JLabel("民    族:");
					JLabel jglb = new JLabel("籍    贯:");
					JLabel zylb = new JLabel("专    业:");
					JButton ok = new JButton("修改");
					JButton cancel = new JButton("取消");
					final JComboBox<String> majorBox = new JComboBox<String>(getMyVector());

					numlb.setBounds(10, 10, 50, 25);
					num.setBounds(65, 10, 160, 25);
					namelb.setBounds(10, 40, 50, 25);
					name.setBounds(65, 40, 160, 25);
					birthlb.setBounds(10, 70, 50, 25);
					birth.setBounds(65, 70, 160, 25);
					mzlb.setBounds(10, 100, 50, 25);
					mz.setBounds(65, 100, 160, 25);
					jglb.setBounds(10, 130, 50, 25);
					jg.setBounds(65, 130, 160, 25);
					zylb.setBounds(10, 160, 50, 25);
					majorBox.setBounds(65, 160, 160, 25);
					ok.setBounds(45, 200, 60, 30);
					cancel.setBounds(135, 200, 60, 30);

					num.setText(String.valueOf(tTable.getValueAt(row, 1)));
					name.setText((String) tTable.getValueAt(row, 2));
					birth.setText((String) tTable.getValueAt(row, 3));
					mz.setText((String) tTable.getValueAt(row, 4));
					jg.setText((String) tTable.getValueAt(row, 5));

					addc.add(numlb);
					addc.add(num);
					addc.add(namelb);
					addc.add(name);
					addc.add(birthlb);
					addc.add(birth);
					addc.add(mzlb);
					addc.add(mz);
					addc.add(jglb);
					addc.add(jg);
					addc.add(zylb);
					addc.add(majorBox);
					addc.add(ok);
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
							String majorS = majorBox.getSelectedItem().toString();
							if ((numS.trim().length() != 0) && (nameS.trim().length() != 0)
									&& (birthS.trim().length() != 0) && (mzS.trim().length() != 0)
									&& (jgS.trim().length() != 0) && (majorS.trim().length() != 0)) {
								try {
									StuClass stuClass = new StuClass(numS, nameS, birthS, mzS, jgS, majorS);
									stuDataBase.update(sel, stuClass);
									editFrame.dispose();
									mStuFrame.dispose();
									initStuFrame();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								editFrame.dispose();
								JOptionPane.showMessageDialog(mStuFrame.getContentPane(), "请填写正确的数据!", "系统信息",
										JOptionPane.ERROR_MESSAGE);
							}
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
					ok.setBounds(45, 50, 60, 30);
					cancel.setBounds(135, 50, 60, 30);

					addc.add(num);
					addc.add(ok);
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
									tList = stuDataBase.searchM(num.getText().trim());
									if (tList.size() > 0) {
										searchFreame.dispose();
										new SearchStuUI(tList, 1);
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
	}
}
