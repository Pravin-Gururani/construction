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
import com.pg.construction.model.Locations;
import com.pg.construction.repository.LocationsRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LocationsController {

	@Autowired
	LocationsRepository locationRepository;

	@GetMapping("/location")
	public ResponseEntity<List<Locations>> getAlllocation() {
		try {
			List<Locations> location = locationRepository.findAll();
//				System.out.println(location.isEmpty());
			
			if (location.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(location, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/location/{id}")
	public ResponseEntity<Locations> getlocationById(@PathVariable("id") Integer id) {
		Optional<Locations> location = locationRepository.findById(id);

		if (location.isPresent()) {
			return new ResponseEntity<>(location.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/location")
	public ResponseEntity<Locations> createTutorial(@RequestBody Locations new_location) {
		try {
			Locations location = locationRepository
					.save(new Locations(new_location.getLocationId(),new_location.getLocationName()));
			return new ResponseEntity<>(location, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
