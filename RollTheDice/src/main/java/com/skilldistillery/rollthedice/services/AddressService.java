package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.Address;

public interface AddressService {
	
	public List<Address> findAllAddresses(String username);
	
	public Address findAddressById(String username, int addressId);
	
	public Address createAddress(String username, Address address);
	
	public Address updateAddress(String username, Address address);
	
	public boolean destroyAddress();

}
