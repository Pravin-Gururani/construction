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

import com.pg.construction.model.Commissions;
import com.pg.construction.repository.CommissionsRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CommissionsController {

	@Autowired
	CommissionsRepository commissionsRepository;

	@GetMapping("/commissions")
	public ResponseEntity<List<Commissions>> getAllcommissions() {
		try {
			List<Commissions> commissions = commissionsRepository.findAll();
//				System.out.println(commissions.isEmpty());
			
			if (commissions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(commissions, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/commissions/{id}")
	public ResponseEntity<Commissions> getcommissionsById(@PathVariable("id") Integer id) {
		Optional<Commissions> commissions = commissionsRepository.findById(id);

		if (commissions.isPresent()) {
			return new ResponseEntity<>(commissions.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/commissions")
	public ResponseEntity<Commissions> createTutorial(@RequestBody Commissions new_commissions) {
		try {
			Commissions commissions = commissionsRepository
					.save(new Commissions(new_commissions.getCommissionId(),new_commissions.getBookingId(),new_commissions.getCommissionAmount(),
							new_commissions.getCommissionPaid(),new_commissions.getCommissionStatus()));
			return new ResponseEntity<>(commissions, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
