package com.crr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StuDataBase {
	public StuDataBase() throws Exception {
		Connection conn = null;
		String sql;
		String url = "jdbc:mysql://localhost:3306/manager?+"
				+ "user=root&useUnicode=true&characterEncoding=UTF8";

		try {
			// ֮����Ҫʹ������������䣬����ΪҪʹ��MySQL����������������Ҫ��������������
			// ����ͨ��Class.forName�������ؽ�ȥ��Ҳ����ͨ����ʼ������������������������ʽ������
			Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
			System.out.println("�ɹ�����MySQL��������");
			// һ��Connection����һ�����ݿ�����
			conn = DriverManager.getConnection(url);
			// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
			Statement stmt = conn.createStatement();
			sql = "create table if not exists student(NO char(20),name varchar(20),primary key(NO))";
			int result = stmt.executeUpdate(sql);// executeUpdate���᷵��һ����Ӱ����������������-1��û�гɹ�
			if (result != -1) {
				System.out.println("�������ݱ�ɹ�");
				// sql =
				// "insert into student(NO,name) values('2012001','ZhangShan')";
				// result = stmt.executeUpdate(sql);
				// sql =
				// "insert into student(NO,name) values('2012002','LiSi')";
				// result = stmt.executeUpdate(sql);
				sql = "select * from student";
				ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
				System.out.println("ѧ��\t����");
				while (rs.next()) {
					System.out
							.println(rs.getString(1) + "\t" + rs.getString(2));// ��������ص���int���Ϳ�����getInt()
				}
			} else {
				System.out.println("111");
			}
		} catch (SQLException e) {
			System.out.println("MySQL��������");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
}
