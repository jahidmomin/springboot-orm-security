package com.security.springboot.orm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.springboot.orm.model.User;
import com.security.springboot.orm.repo.UserRepo;

@RestController
public class TestControler {

	@Autowired
	private UserRepo repo;
	
	@PostMapping("/save")
	public void save(@RequestBody User user) {
		System.out.println(user);
		this.repo.save(user);
	}
	
}
