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
	public List<User> findAllUsers() {
		List<User> resultUsers = userRepo.findAll();
		return resultUsers;
	}

	@Override
	public User findUserById(int userId) {
		User result = null;
		Optional<User> userOptional = userRepo.findById(userId);
		if (userOptional.isPresent()) {
			User resultUser = userOptional.get();
				result = resultUser;
		}
		return result;
	}

	@Override
	public User createUser(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User updateUser(String username, User user, int userId) {
//		Optional<User> userToChange = userRepo.findById(userId);
		User loggedInUser = userRepo.findByUsername(username);
//		
//		if (userToChange.isPresent() && (loggedInUser.getId() == userId || loggedInUser.getRole().equals("ROLE_ADMIN"))) {
//			user.setId(userId);
//			return userRepo.saveAndFlush(user);
//		}
//		return null;
//		
		
		user.setId(userId);	
		if (loggedInUser.equals(user) || loggedInUser.getRole().equals("ROLE_ADMIN")) {
			if (userRepo.existsById(userId)) {
				return userRepo.saveAndFlush(user);
			}				
		}
		return null;
	}

	@Override
	public boolean destroyUser(String username, int userId) {
		User loggedInUser = userRepo.findByUsername(username);
		boolean deleted = false;
		if (loggedInUser.getId() == userId || loggedInUser.getRole().equals("ROLE_ADMIN")) {
			if (userRepo.existsById(userId)) {
				deleted = true;
				userRepo.deleteById(userId);
			}				
		}
		return deleted;
	}

}
