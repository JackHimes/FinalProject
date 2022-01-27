package com.skilldistillery.rollthedice.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.entities.Address;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.services.AddressService;
import com.skilldistillery.rollthedice.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class AddressController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("addresses")
	public List<Address> findAllAddresses(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return addressService.findAllAddresses(principal.getName());
	}
	
	@GetMapping("addresses/{addressId}")
	public Address findAddressById(HttpServletRequest req, HttpServletResponse res, @PathVariable int addressId, Principal principal) {
		Address resultAddress = addressService.findAddressById(principal.getName(), addressId);
		if (resultAddress == null) {
			res.setStatus(404);
		}
		return resultAddress;
	}
	
	@PostMapping("addresses")
	public User createAddress(HttpServletRequest req, HttpServletResponse res,  @RequestBody Address address, Principal principal) {
		User addressUser = null;
		try {
			addressUser = addressService.createAddress(principal.getName(), address);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(address.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid JSON for New Address");
			res.setStatus(400);
		}
		return addressUser;
	}
	
	@PutMapping("addresses/{addressId}")
	public Address updateAddress(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int addressId, @RequestBody Address address) {
		Address updatedAddress = addressService.updateAddress(principal.getName(), address, addressId);
		if (updatedAddress != null) {
			res.setStatus(201);
		} else {
			res.setStatus(400);
			System.err.println("Invalid address, address not updated");
		}
		return updatedAddress;
	}
	
	@DeleteMapping("addresses/{addressId}")
	public void destroyAddress(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int addressId) {
		boolean deleted = addressService.destroyAddress(principal.getName(), addressId);
		if (deleted) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
			System.err.println("Error deleting address");
		}
	}

}
