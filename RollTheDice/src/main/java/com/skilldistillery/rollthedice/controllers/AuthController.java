package com.skilldistillery.rollthedice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.services.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;

}
