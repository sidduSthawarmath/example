package com.test.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.example.app.AddressRepository;
import com.test.example.autoincrservices.AutoIncIdConfigService;
import com.test.example.domain.Customer;
import com.test.example.repository.CustomerDao;
import com.test.example.repository.CustomerRepository;
import com.test.example.util.CommonUtil;
import com.test.example.util.CustomResponse;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AutoIncIdConfigService autoIncIdConfigService;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CommonUtil commonUtil;

	Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	/*
	 * { "id": "10", "firstName": "Mahesh", "lastName": "xyz", "address": [{
	 * "addressId": 18, "streetName": "1st G", "locality": "Rajaji nagar", "city":
	 * "Bangalore" }]
	 */

	// save database
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> saveCustomer(@RequestBody Customer customer) {
		LOG.debug("START saveCustomer");
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {
			customer.setId(String.valueOf(autoIncIdConfigService.getNextSequence("customerSequence")));

			customer.getAddress().forEach(address -> {
				address.setAddressId(autoIncIdConfigService.getNextSequence("addressId"));
				addressRepository.save(address);

			});

			Customer customerObj = customerRepository.save(customer);
			customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", customerObj);
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception in save customer:{}", e.getMessage());
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					"INTERNAL_SERVER_ERROR");
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.debug("END saveCustomer");
		return responseEntity;
	}
	

	// Update record to database
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public ResponseEntity<CustomResponse> updateCustomer(@RequestBody Customer customer) {
		LOG.debug("START saveCustomer");
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {

			// Customer customerObj = customerRepository.findOne(customer.getId());

			// if (customerObj != null) {
			customer.getAddress().forEach(address -> {
				addressRepository.save(address);
			});
			
			Customer customerObj = customerRepository.save(customer);
			customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", customerObj);
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
			/*
			 * } else { customResponse = commonUtil.getReponse(HttpStatus.NOT_FOUND,
			 * "NOT_FOUND", "NOT_FOUND"); responseEntity = new
			 * ResponseEntity<CustomResponse>(customResponse, HttpStatus.NOT_FOUND); }
			 */
		} catch (Exception e) {
			LOG.error("Exception in save customer:{}", e.getMessage());
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					e.getMessage());
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.debug("END saveCustomer");
		return responseEntity;
	}

	
	// find all records from database
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> findAllCustomer() {
		LOG.debug("START findAllCustomer");
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {

			List<Customer> customerObjList = customerRepository.findAll();
			if (customerObjList.size() > 0) {
				customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", customerObjList);
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);

			} else {
				customResponse = commonUtil.getReponse(HttpStatus.NOT_FOUND, "NOT_FOUND", "NOT_FOUND");
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			LOG.error("Exception in find all customer:{}", e.getMessage());
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					"INTERNAL_SERVER_ERROR");
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.debug("END findAllCustomer");
		return responseEntity;
	}

	
	
	@RequestMapping(value = "findAllUsingProjection", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> findAllUsingProjection() {
		LOG.debug("START findAllUsingProjection");
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {

			List<Customer> customerObjList = customerRepository.findAllUsingProjection();
			// List<Customer> customerObjList = customerRepository.findByFirstName();
			if (customerObjList.size() > 0) {
				customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", customerObjList);
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);

			} else {
				customResponse = commonUtil.getReponse(HttpStatus.NOT_FOUND, "NOT_FOUND", "NOT_FOUND");
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			LOG.error("Exception in find all customer:{}", e.getMessage());
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					e.getMessage());
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.debug("END findAllUsingProjection");
		return responseEntity;
	}

	
	
	@RequestMapping(value = "findByFirstName", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> findByFirstName() {
		LOG.debug("START findByFirstName");
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {

			List<Customer> customerObjList = customerDao.getCustDetByName();

			if (customerObjList.size() > 0) {
				customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", customerObjList);
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
			} else {
				customResponse = commonUtil.getReponse(HttpStatus.NOT_FOUND, "NOT_FOUND", "NOT_FOUND");
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			LOG.error("Exception in find all customer by name:{}", e.getMessage());
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					e.getMessage());
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.debug("END findByFirstName");
		return responseEntity;
	}

	@RequestMapping(value = "findByLastName", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse> getByFirstName(@RequestParam("lastName") String lastName) {
		LOG.debug("START findByFirstName");
		ResponseEntity<CustomResponse> responseEntity = null;
		CustomResponse customResponse = null;
		try {

			List<Customer> customerObjList = customerRepository.getByLastNames(lastName);

			if (customerObjList.size() > 0) {
				customResponse = commonUtil.getReponse(HttpStatus.CREATED, "SUCCESS", customerObjList);
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
			} else {
				customResponse = commonUtil.getReponse(HttpStatus.NOT_FOUND, "NOT_FOUND", "NOT_FOUND");
				responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			LOG.error("Exception in find all customer by name:{}", e.getMessage());
			customResponse = commonUtil.getReponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
					e.getMessage());
			responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.debug("END findByFirstName");
		return responseEntity;
	}
}
