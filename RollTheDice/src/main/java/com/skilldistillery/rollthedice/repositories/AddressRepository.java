package com.skilldistillery.rollthedice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.Address;

public interface AddressRepository  extends JpaRepository<Address, Integer>{

}
