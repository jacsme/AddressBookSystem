package com.hermoso.rest.model;

import java.util.List;

public class UserDetails {
	
	public UserDetails() {}
	public UserDetails(String firstName, String lastName, List<ContactDetails> contactDetails) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactDetails = contactDetails;
	}
	
	private String firstName;
	private String lastName;
	private List<ContactDetails> contactDetails;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<ContactDetails> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<ContactDetails> contactDetails) {
		this.contactDetails = contactDetails;
	}
}
