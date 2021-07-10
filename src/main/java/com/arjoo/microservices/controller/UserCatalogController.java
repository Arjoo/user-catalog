package com.arjoo.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjoo.microservices.model.User;
import com.arjoo.microservices.services.UserInfoService;


@RestController
@RequestMapping("/catalog")
public class UserCatalogController {

	@Autowired
	private UserInfoService userInfo;
	
	@RequestMapping("/{userId}")
	public User getUserCatalog(@PathVariable("userId") String userId) {
		return userInfo.getUserInformation(userId);
	}
	
}
