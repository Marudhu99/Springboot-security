package com.amingoscode.SpringSecurity.serviceImpl;


import org.springframework.stereotype.Service;

import com.amingoscode.SpringSecurity.entity.Student;
import com.amingoscode.SpringSecurity.service.StudentService;


@Service
public class StudentServiceImplementation implements StudentService {

	@Override
	public Student getStudent(Integer studentId) {
		return Student.STUDENTS
				.stream()
				.filter(student->studentId.equals(student.getStudentId()))
				.findFirst()
				.orElseThrow(()-> new IllegalStateException("student " + studentId + " does not exist"));
	}
}
