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
			// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
			// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			System.out.println("成功加载MySQL驱动程序");
			// 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url);
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			stmt = conn.createStatement();
			sql = "create table if not exists major(NO int AUTO_INCREMENT,name varchar(20),primary key(NO))";
			int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
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
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		System.out.println("编号\t专业");
		while (rs.next()) {
			mmList.add(rs.getString(2));
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2));// 入如果返回的是int类型可以用getInt()
		}
		return mmList;
	}

	public ArrayList<Integer> search0() throws SQLException {
		ArrayList<Integer> mmList = new ArrayList<Integer>();
		String sql = "select * from major";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		System.out.println("编号\t专业");
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
