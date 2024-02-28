package com.net.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.net.entity.Users;
import com.net.repository.UserRepository;
import com.net.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService; 
	@Autowired
	private  UserRepository userRepository;
	
	public UserController(UserService userService, UserRepository userRepository) { 
	this.userService = userService;
	this.userRepository = userRepository;
	}
	
	@GetMapping("/")
	public String showLoginPage() 
	{ return "login";
	}
	
	@GetMapping("/home")
	public String showHomePage() 
	{ 
		
		return "home";
	}
	
	@PostMapping("/login")
	public String processLogin(@ModelAttribute Users user, Model model, HttpSession session,@RequestParam("username") String username, @RequestParam("password") String password) {
		 user = userRepository.findByUsername(username); 
		if(user!=null&&user.getPassword().equals(password)){ 
			model.addAttribute("user_name", user.getUsername());
			
			session.setAttribute("user_name", user.getUsername());
			return "home";

		} else {
			return "error";
		}
	}
	
	@GetMapping("/register")
	public String showRegisterPage() { 
	return "register";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String>registerUser(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password) {
		Users user = new Users(username, email, password);
		userService.registerUser(user);
		return ResponseEntity.ok("User registered successfully");
	}
	
	@GetMapping("/view-users")
	public String showRegisteredUsers(Model model) { 
		List<Users> users = userService.findAll();
		model.addAttribute("users", users);
	return "users";
	}
}
