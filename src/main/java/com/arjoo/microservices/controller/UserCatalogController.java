package com.arjoo.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.arjoo.microservices.model.User;

@RestController
@RequestMapping("/catalog")
public class UserCatalogController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public User getUserCatalog(@PathVariable("userId") String userId) {
		return restTemplate.getForObject("http://user-info-service/user/"+userId, User.class);
	}
}
