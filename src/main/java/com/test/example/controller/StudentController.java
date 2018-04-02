package com.test.example.controller;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.example.autoincrservices.AutoIncIdConfigService;
import com.test.example.domain.Student;
import com.test.example.repository.StudentRepository;
import com.test.example.util.CommonUtil;
import com.test.example.util.CustomResponse;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private AutoIncIdConfigService autoIncIdConfigService;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> save(@RequestBody Student student) {
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {
			student.setStudentId(autoIncIdConfigService.getNextSequence("studentSequence"));
			Student studentObj = studentRepository.save(student);
			customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", studentObj);
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					"INTERNAL_SERVER_ERROR");
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	
	@RequestMapping(value = "getByFirstName", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getByName(@RequestParam("name") String firstName) {
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {
		
			Student studentObj = studentRepository.findByFirstName(firstName);
			
			if(studentObj!=null) {
			customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", studentObj);
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
			}else
			{
				customResponse = commonUtil.getReponse(HttpStatus.NOT_FOUND, "NOT_FOUND", "NOT_FOUND");
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					"INTERNAL_SERVER_ERROR");
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	
	@RequestMapping(value = "getByFirstNameAndLastName", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getByFirstNameAndLastName(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {
		
			Student studentObj = studentRepository.findByFirstNameAndLastName(firstName,lastName);
			
			if(studentObj!=null) {
			customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", studentObj);
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
			}else
			{
				customResponse = commonUtil.getReponse(HttpStatus.NOT_FOUND, "NOT_FOUND", "NOT_FOUND");
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					"INTERNAL_SERVER_ERROR");
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	
	@RequestMapping(value = "getAllDetails", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getAllDetails() {
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {
		
			java.util.List<Student> studentObjList = studentRepository.findAll();
			
			if(studentObjList.size()>0) {
			customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", studentObjList);
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
			}else
			{
				customResponse = commonUtil.getReponse(HttpStatus.NOT_FOUND, "NOT_FOUND", "NOT_FOUND");
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					"INTERNAL_SERVER_ERROR");
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
