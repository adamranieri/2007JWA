package dev.ranieri.entities;

public class Student {
	
	// unique to every instance of student
	// unique way to identify a student
	// Primary key
	private int studentId;
	
	private String name;
	
	// A way to identify what school this student is part of
	// Foreign Key
	private int schoolId;

	public Student() {
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", schoolId=" + schoolId + "]";
	}
	
}
