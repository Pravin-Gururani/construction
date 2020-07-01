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

import com.pg.construction.model.Ratings;
import com.pg.construction.repository.RatingsRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RatingsController {

	@Autowired
	RatingsRepository ratingsRepository;

	@GetMapping("/ratings")
	public ResponseEntity<List<Ratings>> getAllratings() {
		try {
			List<Ratings> ratings = ratingsRepository.findAll();
//				System.out.println(ratings.isEmpty());
			
			if (ratings.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(ratings, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ratings/{id}")
	public ResponseEntity<Ratings> getratingsById(@PathVariable("id") Integer id) {
		Optional<Ratings> ratings = ratingsRepository.findById(id);

		if (ratings.isPresent()) {
			return new ResponseEntity<>(ratings.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/ratings")
	public ResponseEntity<Ratings> createTutorial(@RequestBody Ratings new_ratings) {
		try {
			Ratings ratings = ratingsRepository
					.save(new Ratings(new_ratings.getRatingId(),new_ratings.getRating(),new_ratings.getUserId(),new_ratings.getEmployeeId()));
			return new ResponseEntity<>(ratings, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
