package com.skilldistillery.rollthedice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
