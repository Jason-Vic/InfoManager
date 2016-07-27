package com.crr;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MajorDataBase {
	private Statement stmt;

	public MajorDataBase() throws Exception {
		Connection conn = null;
		String sql;
		String url = "jdbc:mysql://localhost:3306/manager?user=root";

		try {
			// ֮����Ҫʹ������������䣬����ΪҪʹ��MySQL����������������Ҫ��������������
			// ����ͨ��Class.forName�������ؽ�ȥ��Ҳ����ͨ����ʼ������������������������ʽ������
			Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
			System.out.println("�ɹ�����MySQL��������");
			// һ��Connection����һ�����ݿ�����
			conn = DriverManager.getConnection(url);
			// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
			stmt = conn.createStatement();
			sql = "create table if not exists major(NO int AUTO_INCREMENT,name varchar(20),primary key(NO))";
			int result = stmt.executeUpdate(sql);// executeUpdate���᷵��һ����Ӱ����������������-1��û�гɹ�
		} catch (SQLException e) {
			System.out.println("MySQL��������");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void insert(String name) throws SQLException {
		String sql = "insert into major(name) values('" + name + "')";
		try {
			int result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	ArrayList<String> search1() throws SQLException {
		ArrayList<String> mmList = new ArrayList<String>();
		String sql = "select * from major";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		System.out.println("���\tרҵ");
		while (rs.next()) {
			mmList.add(rs.getString(2));
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2));// ��������ص���int���Ϳ�����getInt()
		}
		return mmList;
	}

	public ArrayList<Integer> search0() throws SQLException {
		ArrayList<Integer> mmList = new ArrayList<Integer>();
		String sql = "select * from major";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		System.out.println("���\tרҵ");
		while (rs.next()) {
			mmList.add(rs.getInt(1));
		}
		return mmList;
	}

	void delete(int sel) throws SQLException {
		String sql = "delete from major where NO=" + sel;
		System.out.println(sql);
		stmt.execute(sql);
	}
	
	
	void update(){
		
	}

}
