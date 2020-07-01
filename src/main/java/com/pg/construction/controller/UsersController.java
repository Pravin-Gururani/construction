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

import com.pg.construction.model.Users;
import com.pg.construction.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	UsersRepository usersRepository;

	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllusers() {
		try {
			List<Users> users = usersRepository.findAll();
				System.out.println(users.isEmpty());
			
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Users> getusersById(@PathVariable("id") Integer id) {
		Optional<Users> users = usersRepository.findById(id);

		if (users.isPresent()) {
			return new ResponseEntity<>(users.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<Users> createTutorial(@RequestBody Users new_users) {
		try {
			Users users = usersRepository
					.save(new Users(new_users.getUserId(),new_users.getName(),new_users.getPhone(),new_users.getPassword(),new_users.getLocation(),
							new_users.getImageUrl(),new_users.getEmail()));
			return new ResponseEntity<>(users, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUsers(@PathVariable("id") Integer id, @RequestBody Users newUsers) {
		Optional<Users> userData = usersRepository.findById(id);

		if (userData.isPresent()) {
			Users userTemp = userData.get();
			userTemp.setName(newUsers.getName());
			userTemp.setPhone(newUsers.getPhone());
			userTemp.setEmail(newUsers.getEmail());
			userTemp.setImageUrl(newUsers.getImageUrl());
			userTemp.setLocation(newUsers.getLocation());
			userTemp.setPassword(newUsers.getPassword());
			return new ResponseEntity<>(usersRepository.save(userTemp), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
