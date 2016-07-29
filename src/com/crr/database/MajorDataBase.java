package com.crr.database;

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
			// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
			// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			// 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url);
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			stmt = conn.createStatement();
			sql = "create table if not exists major(NO int AUTO_INCREMENT,name varchar(20),primary key(NO))";
			stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			ArrayList<Integer> mmList = search0();
			// 第一次打开软件插入一个初始专业
			if (mmList.size() == 0) {
				insertInit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 初始插入专业
	private void insertInit() throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into major(name) values('HelloWorld')";
		stmt.executeUpdate(sql);
	}

	// 插入一个新专业
	public void insert(String majorName) throws SQLException {
		String sql = "insert into major(name) values('" + majorName + "')";
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 查询专业返回集合
	public ArrayList<String> search1() throws SQLException {
		ArrayList<String> mmList = new ArrayList<String>();
		String sql = "select * from major";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			mmList.add(rs.getString(2));
		}
		return mmList;
	}
	//查询专业id返回集合
	public ArrayList<Integer> search0() throws SQLException {
		ArrayList<Integer> mmList = new ArrayList<Integer>();
		String sql = "select * from major";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			mmList.add(rs.getInt(1));
		}
		return mmList;
	}

	public void delete(int sel) throws SQLException {
		String sql = "delete from major where NO=" + sel;
		stmt.execute(sql);
	}

	public void update(int sel, String majorName) throws SQLException {
		String sql = "update major set name='" + majorName + "' where NO=" + sel;
		stmt.execute(sql);
	}

}
