package com.crr.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crr.entity.StuClass;

public class StuDataBase {
	private Statement stmt;

	public StuDataBase() throws Exception {
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
			sql = "create table if not exists student(NO int AUTO_INCREMENT,stunum varchar(20),"
					+ "name varchar(20),"
					+ "birthday varchar(20),mz varchar(20),jg varchar(20),zy varchar(20),score varchar(20),passwd varchar(20),primary key(NO))";
			int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(StuClass stuClass) throws SQLException {
		String sql = "insert into student(stunum,name,birthday,mz,jg,zy,passwd,score) values('"
				+ stuClass.getNum()
				+ "','"
				+ stuClass.getName()
				+ "','"
				+ stuClass.getBirth()
				+ "','"
				+ stuClass.getNation()
				+ "','"
				+ stuClass.getLocation()
				+ "','"
				+ stuClass.getMajor()
				+ "','123456','" + stuClass.getScore() + "')";
		try {
			int result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<StuClass> search() throws SQLException {
		ArrayList<StuClass> tList = new ArrayList<StuClass>();
		String sql = "select * from student";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		System.out.println("编号\t专业");
		while (rs.next()) {
			StuClass stuClass = new StuClass(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9));
			tList.add(stuClass);
			System.out.println(rs.getInt(1) + "\t" + rs.getString(3));// ��������ص���int���Ϳ�����getInt()
		}
		return tList;
	}

	public void delete(int sel) throws SQLException {
		String sql = "delete from student where NO=" + sel;
		System.out.println(sql);
		stmt.execute(sql);
	}

	public void update(int sel, StuClass stuClass) throws SQLException {
		String sql = "update student set stunum='" + stuClass.getNum()
				+ "',name='" + stuClass.getName() + "',birthday='"
				+ stuClass.getBirth() + "',mz='" + stuClass.getNation()
				+ "',jg='" + stuClass.getLocation() + "',zy='"
				+ stuClass.getMajor() + "',score='" + stuClass.getScore()
				+ "' where NO=" + sel;
		System.out.println(sql);
		stmt.execute(sql);
	}

	public void updateScore(int sel, StuClass stuClass) throws SQLException {
		String sql = "update student set stunum='" + stuClass.getNum()
				+ "',name='" + stuClass.getName() + "',zy='"
				+ stuClass.getMajor() + "',score='" + stuClass.getScore()
				+ "' where NO=" + sel;
		System.out.println(sql);
		stmt.execute(sql);
	}

	public void updatePasswd(int sel, StuClass stuClass) throws SQLException {
		String sql = "update student set stunum='" + stuClass.getNum()
				+ "',name='" + stuClass.getName() + "',birthday='"
				+ stuClass.getBirth() + "',mz='" + stuClass.getNation()
				+ "',jg='" + stuClass.getLocation() + "',zy='"
				+ stuClass.getMajor() + "',score='" + stuClass.getScore()
				+ "',passwd='" + stuClass.getPassword() + "' where NO=" + sel;
		System.out.println(sql);
		stmt.execute(sql);
	}

	public ArrayList<String> searchUser() throws SQLException {
		ArrayList<String> tList = new ArrayList<String>();
		String sql = "select * from student";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			tList.add(rs.getString(2));
			System.out.println(rs.getString(2) + "\t" + rs.getString(9));// ��������ص���int���Ϳ�����getInt()
		}
		return tList;
	}

	public ArrayList<String> searchPasswd() throws SQLException {
		ArrayList<String> tList = new ArrayList<String>();
		String sql = "select * from student";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			tList.add(rs.getString(9));
		}
		return tList;
	}

	public StuClass searchOne(int sel) throws SQLException {
		StuClass stuClass = null;
		String sql = "select * from student";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			stuClass = new StuClass(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9));
		}
		return stuClass;
	}

	public ArrayList<StuClass> search(String trim) throws Exception {
		ArrayList<StuClass> tList = new ArrayList<StuClass>();
		String sql = "select * from student where stunum='"+trim+"'";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			tList.add(new StuClass(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9)));
		}
		return tList;
	}

}
