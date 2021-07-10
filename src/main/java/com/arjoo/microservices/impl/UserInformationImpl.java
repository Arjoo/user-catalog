package com.arjoo.microservices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arjoo.microservices.model.User;
import com.arjoo.microservices.services.UserInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserInformationImpl implements UserInfoService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@HystrixCommand(fallbackMethod = "getFallbackUserInfo",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="2000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
			})
	public User getUserInformation(String userId) {
		return restTemplate.getForObject("http://user-info-service/user/"+userId, User.class);
	}
	
	public User getFallbackUserInfo(String userId) { 
		return new User("DefaultId", "Default User name");
	}

}
