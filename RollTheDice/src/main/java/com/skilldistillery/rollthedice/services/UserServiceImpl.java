package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> findAllUsers(String username) {
		List<User> resultUsers = userRepo.findAll();
		return resultUsers;
	}

	@Override
	public User findUserById(String username, int userId) {
		User result = null;
		Optional<User> userOptional = userRepo.findById(userId);
		if (userOptional.isPresent()) {
			User resultUser = userOptional.get();
			if (resultUser.getUsername().equals(username)) {
				result = resultUser;
			}
		}
		return result;
	}

	@Override
	public User createUser(String username, User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User updateUser(String username, User user) {
		int userId = user.getId();
		if (userRepo.existsById(userId)) {
			return userRepo.saveAndFlush(user);
		}		
		return null;
	}

	@Override
	public void destroyUser(String username, int userId) {
		userRepo.deleteById(userId);
	}

}
