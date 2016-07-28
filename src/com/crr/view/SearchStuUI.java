package com.crr.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.crr.entity.StuClass;

public class SearchStuUI {
	public SearchStuUI(ArrayList<StuClass> tList, int tag) throws Exception {

		JTable tTable;
		JPanel jPanel = new JPanel();
		JButton close = new JButton("关闭");
		final JFrame searchFrame = new JFrame("查找学生");
		Container sm = searchFrame.getContentPane();

		if (tag == 1) {
			searchFrame.setSize(600, 400);

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

			String[] columnNames = { "编号", "学号", "姓名", "出生日期", "民族", "籍贯",
					"专业", "成绩" };
			tTable = new JTable(rowData, columnNames);
		} else {
			searchFrame.setSize(600, 400);

			Object[][] rowData = new Object[tList.size()][4];

			for (int i = 0; i < tList.size(); i++) {
				rowData[i][0] = tList.get(i).getId();
				rowData[i][1] = tList.get(i).getNum();
				rowData[i][2] = tList.get(i).getName();
				rowData[i][3] = tList.get(i).getMajor();
			}

			String[] columnNames = { "编号", "学号", "姓名", "专业" };
			tTable = new JTable(rowData, columnNames);
		}

		tTable.setRowHeight(30);
		tTable.updateUI();
		tTable.repaint();
		tTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(tTable);
		jScrollPane.updateUI();
		jScrollPane.repaint();
		sm.add(jScrollPane, BorderLayout.CENTER);
		close.setBounds(30, 230, 60, 30);
		jPanel.add(close);
		sm.add(jPanel, BorderLayout.SOUTH);

		searchFrame.setResizable(false);
		searchFrame.setLocationRelativeTo(null);
		// mMajFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		searchFrame.setVisible(true);

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchFrame.dispose();
			}
		});
	}
}
