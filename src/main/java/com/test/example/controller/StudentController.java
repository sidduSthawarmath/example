package com.test.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.example.domain.Student;
import com.test.example.repository.StudentRepository;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@RequestMapping(value = "save", method = RequestMethod.GET)
	public void save() {

		Student std = new Student();
		std.setStudentId(1);
		std.setStudentName("sasada");
		studentRepository.save(std);
	}

}
