package com.test.example.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {

@Id
private Integer studentId;

@Indexed
private String firstName;

@Indexed
private String lastName;

public Integer getStudentId() {
	return studentId;
}

public void setStudentId(Integer studentId) {
	this.studentId = studentId;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}




}