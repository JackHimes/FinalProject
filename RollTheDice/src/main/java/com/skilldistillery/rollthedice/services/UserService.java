package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.GameEvent;
import com.skilldistillery.rollthedice.entities.User;

public interface UserService {
	
	public List<User> findAllUsers();
	
	public User findUserById(int userId);
	
	public User createUser(User user);
	
	public User updateUser(String username, User user, int userId);
	
	public boolean destroyUser(String username, int userId);
	
	GameEvent addGuestToGameEvent(String username, int geId);
	
	List<User> searchKeyword(String keyword);

}
