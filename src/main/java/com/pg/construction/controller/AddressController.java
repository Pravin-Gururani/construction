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

import com.pg.construction.model.Address;
import com.pg.construction.repository.AddressRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AddressController {

	@Autowired
	AddressRepository addressRepository;

	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAllAddress() {
		try {
			List<Address> address = addressRepository.findAll();
//				System.out.println(address.isEmpty());
			
			if (address.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(address, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable("id") Integer id) {
		Optional<Address> address = addressRepository.findById(id);

		if (address.isPresent()) {
			return new ResponseEntity<>(address.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/address")
	public ResponseEntity<Address> createTutorial(@RequestBody Address new_address) {
		try {
			Address address = addressRepository
					.save(new Address(new_address.getBuildingName(),new_address.getCity(),new_address.getPostalCode(),new_address.getState(),new_address.getStreet()));
			return new ResponseEntity<>(address, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
