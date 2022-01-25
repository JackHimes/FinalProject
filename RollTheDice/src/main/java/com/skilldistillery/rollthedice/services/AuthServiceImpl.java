package com.skilldistillery.rollthedice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("standard");
		userRepo.saveAndFlush(user);
		return user;
	}
	
	@Override
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
