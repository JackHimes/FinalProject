package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.Address;
import com.skilldistillery.rollthedice.entities.User;

public interface AddressService {
	
	public List<Address> findAllAddresses(String username);
	
	public Address findAddressById(String username, int addressId);
	
	public User createAddress(String username, Address address);
	
	public Address updateAddress(String username, Address address, int addressId);
	
	public boolean destroyAddress(String username, int addressId);

}
