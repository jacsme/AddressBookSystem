package com.hermoso.rest.model;

public class ContactDetails {
	
	public ContactDetails() {}
	public ContactDetails(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	private String phoneNumbers;
		
	public String getPhoneNumbers() {
		return phoneNumbers;
	}
	
	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
