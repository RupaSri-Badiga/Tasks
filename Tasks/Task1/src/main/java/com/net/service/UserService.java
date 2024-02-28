package com.net.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.entity.Users;
import com.net.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) { 
		
		this.userRepository = userRepository;
		
		}
		public void registerUser(Users user){ 
			
		userRepository.save(user);
		
		}
		public List<Users> findAll(){
			return userRepository.findAll();
			
		}
		
		public Users findByUsername(String username) {
			return userRepository.findByUsername(username);
			
		}

}
