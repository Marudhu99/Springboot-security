package com.amingoscode.SpringSecurity.entity;

import java.util.Arrays;
import java.util.List;

public class Student {

	private Integer studentId;
	private String studentName;

	public Student() {

	}

	public Student(Integer studentId, String studentName) {
		this.studentId = studentId;
		this.studentName = studentName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public static final List<Student> STUDENTS = Arrays.asList(
			new Student(1,"Marudhu"),
			new Student(2,"Arul"),
			new Student(3,"Sarath"),
			new Student(4,"Ayyappan")
	);

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + "]";
	}
}
