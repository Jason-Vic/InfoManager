package com.crr.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminDataBase {
	private Statement stmt;
	private ArrayList<String> list;

	public AdminDataBase() throws Exception {
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
			sql = "create table if not exists admin(NO int AUTO_INCREMENT,loginname varchar(20),passwd varchar(20),primary key(NO))";
			stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			list = search0();
			//如果是第一次打开软件则自动插入一条管理员帐号
			if (list.size() == 0) {
				insert();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//插入一条管理员帐号
	public void insert() throws SQLException {
		String sql = "insert into admin(loginname,passwd) values(\'admin\',\'admin\')";
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//查询管理员帐号返回集合
	public ArrayList<String> search0() throws SQLException {
		ArrayList<String> mmList = new ArrayList<String>();
		String sql = "select * from admin";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			mmList.add(rs.getString(2));
		}
		return mmList;
	}
	//查询管理员密码返回集合
	public ArrayList<String> search1() throws SQLException {
		ArrayList<String> mmList = new ArrayList<String>();
		String sql = "select * from admin";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			mmList.add(rs.getString(3));
		}
		return mmList;
	}
}
