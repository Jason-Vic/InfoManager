package com.crr.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.crr.entity.TeacherClass;

public class TeaDataBase {
	private Statement stmt;

	public TeaDataBase() throws Exception {
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
			sql = "create table if not exists teacher(NO int AUTO_INCREMENT,teacherNum varchar(20),"
					+ "name varchar(20)," + "birthday varchar(20),zy varchar(20),password varchar(20),primary key(NO))";
			stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(TeacherClass teacherClass) throws SQLException {
		String sql = "insert into teacher(teacherNum,name,birthday,zy,password) values('" + teacherClass.getNum()
				+ "','" + teacherClass.getName() + "','" + teacherClass.getBirth() + "','" + teacherClass.getMajor()
				+ "','123456')";
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<TeacherClass> search() throws SQLException {
		ArrayList<TeacherClass> tList = new ArrayList<TeacherClass>();
		String sql = "select * from teacher";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			TeacherClass teacherClass = new TeacherClass(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6));
			tList.add(teacherClass);
		}
		return tList;
	}

	public void delete(int sel) throws SQLException {
		String sql = "delete from teacher where NO=" + sel;
		stmt.execute(sql);
	}

	public void update(int sel, TeacherClass teacherClass) throws SQLException {
		String sql = "update teacher set teacherNum='" + teacherClass.getNum() + "',name='" + teacherClass.getName()
				+ "',birthday='" + teacherClass.getBirth() + "',zy='" + teacherClass.getMajor() + "' where NO=" + sel;
		stmt.execute(sql);
	}

	//返回老师专业的集合
	public ArrayList<String> searchMajor() throws SQLException {
		ArrayList<String> tList = new ArrayList<String>();
		String sql = "select * from teacher";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			tList.add(rs.getString(5));
		}
		return tList;
	}

	public ArrayList<String> searchUser() throws SQLException {
		ArrayList<String> tList = new ArrayList<String>();
		String sql = "select * from teacher";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			tList.add(rs.getString(2));
		}
		return tList;
	}

	public ArrayList<String> searchPasswd() throws SQLException {
		ArrayList<String> tList = new ArrayList<String>();
		String sql = "select * from teacher";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			tList.add(rs.getString(6));
		}
		return tList;
	}

	public ArrayList<TeacherClass> search(String trim) throws SQLException {
		ArrayList<TeacherClass> tList = new ArrayList<TeacherClass>();
		String sql = "select * from teacher where name like '%" + trim + "%'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			TeacherClass teacherClass = new TeacherClass(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6));
			tList.add(teacherClass);
		}
		return tList;
	}

}
