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

import com.pg.construction.model.Bookings;
import com.pg.construction.repository.BookingsRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BookingsController {

	@Autowired
	BookingsRepository bookingsRepository;

	@GetMapping("/bookings")
	public ResponseEntity<List<Bookings>> getAllbookings() {
		try {
			List<Bookings> bookings = bookingsRepository.findAll();
//				System.out.println(bookings.isEmpty());
			
			if (bookings.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookings, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/bookings/{id}")
	public ResponseEntity<Bookings> getbookingsById(@PathVariable("id") Integer id) {
		Optional<Bookings> bookings = bookingsRepository.findById(id);

		if (bookings.isPresent()) {
			return new ResponseEntity<>(bookings.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/bookings")
	public ResponseEntity<Bookings> createTutorial(@RequestBody Bookings new_bookings) {
		try {
			Bookings bookings = bookingsRepository
					.save(new Bookings(new_bookings.getBookingId(),new_bookings.getBookingFrom(),new_bookings.getBookingTo(),new_bookings.getStatus(),
							new_bookings.getDaysWorked(),new_bookings.getUserId(),new_bookings.getEmployeeId()));
			return new ResponseEntity<>(bookings, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
