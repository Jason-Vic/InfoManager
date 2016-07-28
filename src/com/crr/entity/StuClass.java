package com.crr.entity;

public class StuClass {
	private int Id;
	private String Num;
	private String Name;
	private String Birth;
	private String Nation;
	private String Location;
	private String Major;
	private String Score;
	private String Password;

	public StuClass(int id, String num, String name, String birth, String nation, String location, String major,
			String score, String password) {
		super();
		Id = id;
		Num = num;
		Name = name;
		Birth = birth;
		Nation = nation;
		Location = location;
		Major = major;
		Score = score;
		Password = password;
	}

	public StuClass(String num, String name, String birth, String nation,
			String location, String major, String score) {
		super();
		Num = num;
		Name = name;
		Birth = birth;
		Nation = nation;
		Location = location;
		Major = major;
		Score = score;
	}
	
	public StuClass(String numS, String nameS, String majorS, String scoreS) {
		Num = numS;
		Name = nameS;
		Major = majorS;
		Score = scoreS;
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
	
	public void setPasswd(String passwd){
		Password = passwd;
	}

}
