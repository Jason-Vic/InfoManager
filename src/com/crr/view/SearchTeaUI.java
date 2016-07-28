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
import javax.swing.WindowConstants;

import com.crr.entity.TeacherClass;

public class SearchTeaUI {
	public SearchTeaUI(ArrayList<TeacherClass> tList) {
		final JFrame mTeaFrame = new JFrame("老师管理");
		JButton close = new JButton("关闭");
		Container mm = mTeaFrame.getContentPane();
		JPanel jPanel = new JPanel();
		final JTable tTable;
		mTeaFrame.setSize(600, 400);
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
		close.setBounds(30, 230, 60, 30);
		jPanel.add(close);
		mm.add(jPanel, BorderLayout.SOUTH);

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mTeaFrame.dispose();
			}
		});

		mTeaFrame.setResizable(false);
		mTeaFrame.setLocationRelativeTo(null);
		mTeaFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mTeaFrame.setVisible(true);
	}
}
