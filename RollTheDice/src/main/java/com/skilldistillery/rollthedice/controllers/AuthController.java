package com.skilldistillery.rollthedice.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.services.AuthService;

@RestController
@CrossOrigin({"*", "http://localhost:4200"})
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	//temporary, delete later
	@GetMapping("usertest")
	public User userTest() {
		return authService.findUserByUsername("admin");
	}
	
	@RequestMapping(path = "/register")
	public User register(@RequestBody User user, HttpServletResponse res) {

		if (user == null) {
			res.setStatus(400);
		}

		user = authService.register(user);

		return user;
	}

	@RequestMapping(path = "/authenticate")
	public User authenticate(Principal principal) {
		return authService.findUserByUsername(principal.getName());
	}
}
