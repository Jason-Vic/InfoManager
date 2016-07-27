package com.crr;

public class TeacherClass {
	private String Num;
	private String Name;
	private String Birth;
	private String Major;

	public TeacherClass(String num, String name, String birth, String major) {
		super();
		Num = num;
		Name = name;
		Birth = birth;
		Major = major;
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

}
