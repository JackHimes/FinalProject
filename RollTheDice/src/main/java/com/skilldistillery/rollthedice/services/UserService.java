package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.User;

public interface UserService {
	
	public List<User> findAllUsers(String username);
	
	public User findUserById(String username, int userId);
	
	public User createUser(String username, User user);
	
	public User updateUser(String username, User user);
	
	public void destroyUser(String username, int userId);

}
