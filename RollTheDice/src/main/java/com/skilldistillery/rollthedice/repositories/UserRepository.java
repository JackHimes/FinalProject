package com.skilldistillery.rollthedice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
	List<User> findByUsernameContainsOrFirstNameContainsOrLastNameContains(String username, String firstName, String lastName);

}
