package com.hermoso.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hermoso.rest.model.StatusResponse;
import com.hermoso.rest.model.UserDetails;


@Service
public interface AddressBookService {
	
	public StatusResponse addNewContact(UserDetails userDetails);
	public StatusResponse removeContact(String firstName, String lastName, String phoneNumber);
	public List<UserDetails> printAllContacts();
	public List<UserDetails> printUniqueSet(String firstName, String lastName);
	
}
