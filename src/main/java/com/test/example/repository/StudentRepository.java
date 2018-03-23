package com.test.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.example.domain.Student;

public interface StudentRepository  extends MongoRepository<Student, Integer>{

}
