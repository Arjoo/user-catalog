package com.arjoo.microservices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arjoo.microservices.model.User;
import com.arjoo.microservices.services.UserInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserInformationImpl implements UserInfoService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@HystrixCommand(fallbackMethod = "getFallbackUserInfo")
	public User getUserInformation(String userId) {
		return restTemplate.getForObject("http://user-info-service/user/"+userId, User.class);
	}
	
	public User getFallbackUserInfo(String userId) { 
		return new User("DefaultId", "Default User name");
	}

}
