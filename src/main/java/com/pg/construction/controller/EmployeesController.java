package com.pg.construction.controller;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pg.construction.model.Employees;
import com.pg.construction.repository.EmployeesRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeesController {

	@Autowired
	EmployeesRepository employeeRepository;

	@GetMapping("/employee")
	public ResponseEntity<List<Employees>> getAllemployee() {
		try {
			List<Employees> employee = employeeRepository.findAll();
				System.out.println(employee.isEmpty());
			
			if (employee.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employee, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employees> getemployeeById(@PathVariable("id") Integer id) {
		Optional<Employees> employee = employeeRepository.findById(id);

		if (employee.isPresent()) {
			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/employee")
	public ResponseEntity<Employees> createTutorial(@RequestBody Employees new_employee) {
		try {
			Employees employee = employeeRepository
					.save(new Employees(new_employee.getEmployeeId(),new_employee.getName(),
							new_employee.getPhone(),new_employee.getEmail(),new_employee.getAge(),
							new_employee.getExperience(),new_employee.isVerified(),new_employee.getOccupation(),
							new_employee.getPassword(),new_employee.getAddressId(),new_employee.getImage(),
							new_employee.getAvailability(),new_employee.getJobStartDate(),new_employee.getAadharFront(),
							new_employee.getAadharBack(),new_employee.getCommissionRate()));
			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
