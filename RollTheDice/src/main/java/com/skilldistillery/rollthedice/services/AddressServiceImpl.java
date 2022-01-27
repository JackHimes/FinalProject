package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.Address;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.AddressRepository;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Address> findAllAddresses(String username) {
		List<Address> resultAddresses = addressRepo.findAll();
		return resultAddresses;
	}

	@Override
	public Address findAddressById(String username, int addressId) {
		Address resultAddress = null;
		Optional<Address> addressOptional = addressRepo.findById(addressId);
		if (addressOptional.isPresent()) {
			resultAddress = addressOptional.get();
		}
		return resultAddress;
	}

	@Override
	public Address createAddress(String username, Address address) {
		return addressRepo.saveAndFlush(address);
	}

	@Override
	public Address updateAddress(String username, Address address, int addressId) {
		address.setId(addressId);

		User loggedInUser = userRepo.findByUsername(username);
		List<Address> usersAddresses = loggedInUser.getAddresses();
		Address homeAddress = loggedInUser.getHomeAddress();

		if (homeAddress.getId() == address.getId() || loggedInUser.getRole().equals("ROLE_ADMIN")) {
			if (addressRepo.existsById(addressId)) {
				return addressRepo.saveAndFlush(address);
			}
		} else {
			for (Address userAddress : usersAddresses) {
				if (userAddress.getId() == address.getId() || loggedInUser.getRole().equals("ROLE_ADMIN")) {
					if (addressRepo.existsById(addressId)) {
						return addressRepo.saveAndFlush(address);
					}
				}
			}
		}

		return null;
	}

	@Override
	public boolean destroyAddress(String username, int addressId) {
		boolean deleted = false;

		User loggedInUser = userRepo.findByUsername(username);
		List<Address> usersAddresses = loggedInUser.getAddresses();

		for (Address userAddress : usersAddresses) {
			if (userAddress.getId() == addressId || loggedInUser.getRole().equals("ROLE_ADMIN")) {
				deleted = true;
				addressRepo.deleteById(addressId);
				break;
			}
		}
		return deleted;
	}

}
