package com.arjoo.microservices.services;

import com.arjoo.microservices.model.User;


public interface UserInfoService {

	public User getUserInformation(String userId);
}
