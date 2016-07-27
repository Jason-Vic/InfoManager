package com.crr;

public class StuClass {
	private String Num;
	private String Name;
	private String Birth;
	private String Nation;
	private String Location;
	private String Major;
	private String Score;
	private String Password;

	public StuClass(String num, String name, String birth, String nation,
			String location, String major, String score, String password) {
		super();
		Num = num;
		Name = name;
		Birth = birth;
		Nation = nation;
		Location = location;
		Major = major;
		Score = score;
		Password = password;
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

	public String getNation() {
		return Nation;
	}

	public String getLocation() {
		return Location;
	}

	public String getMajor() {
		return Major;
	}

	public String getScore() {
		return Score;
	}

	public String getPassword() {
		return Password;
	}

}