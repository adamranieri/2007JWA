package dev.ranieri.entities;

public class Student {
	
	// primary key to uniquely identify a student
	private int stuId;
	private String name;
	
	// foreign key id so that we can connect a student to a school
	private int sId;
	
	public Student() {
		super();
	}

	public Student(int stuId, String name, int sId) {
		super();
		this.stuId = stuId;
		this.name = name;
		this.sId = sId;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", name=" + name + ", sId=" + sId + "]";
	}
	
}
