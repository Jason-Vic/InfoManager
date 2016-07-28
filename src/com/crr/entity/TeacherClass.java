package com.crr.entity;

public class TeacherClass {
	private int Id;
	private String Num;
	private String Name;
	private String Birth;
	private String Major;
	private String Passwd;

	public TeacherClass(int id, String num, String name, String birth, String major, String passwd) {
		super();
		Id = id;
		Num = num;
		Name = name;
		Birth = birth;
		Major = major;
		Passwd = passwd;
	}

	public TeacherClass(String num, String name, String birth, String major) {
		super();
		Num = num;
		Name = name;
		Birth = birth;
		Major = major;
	}

	public int getId() {
		return Id;
	}

	public String getNum() {
		return Num;
	}

	public String getName() {
		return Name;
	}

	public String getBirth() {
		return Birth;
	}

	public String getMajor() {
		return Major;
	}

	public String getPasswd() {
		return Passwd;
	}

}
