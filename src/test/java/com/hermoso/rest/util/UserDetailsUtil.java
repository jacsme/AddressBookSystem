package com.hermoso.rest.util;

import java.util.ArrayList;
import java.util.List;

import com.hermoso.rest.model.ContactDetails;
import com.hermoso.rest.model.UserDetails;

public class UserDetailsUtil {
	
	public static UserDetails generateUserDetailsDelete() {
    	UserDetails contactListRequest = new UserDetails();
    	contactListRequest.setFirstName("John");
    	contactListRequest.setLastName("Hermoso");
    	List<ContactDetails> contactDetailsList = new ArrayList<>();
    	ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setPhoneNumbers("0421844784");
    	contactDetailsList.add(contactDetails);
    	contactListRequest.setContactDetails(contactDetailsList);
    	
    	return contactListRequest;
    }
	
	public static UserDetails generateUserDetailsUniqueSearch() {
    	UserDetails contactListRequest = new UserDetails();
    	contactListRequest.setFirstName("Rachael");
    	contactListRequest.setLastName("Hermoso");
    	List<ContactDetails> contactDetailsList = new ArrayList<>();
    	ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setPhoneNumbers("0421844754");
    	contactDetailsList.add(contactDetails);
    	contactListRequest.setContactDetails(contactDetailsList);
    	
    	return contactListRequest;
    }
	
	public static UserDetails generateUserDetailsNew() {
    	UserDetails contactListRequest = new UserDetails();
    	contactListRequest.setFirstName("Josua");
    	contactListRequest.setLastName("Hermoso");
    	List<ContactDetails> contactDetailsList = new ArrayList<>();
    	ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setPhoneNumbers("0421844795");
    	contactDetailsList.add(contactDetails);
    	contactListRequest.setContactDetails(contactDetailsList);
    	
    	return contactListRequest;
    }
	
	public static UserDetails generateUserDetails() {
    	UserDetails contactListRequest = new UserDetails();
    	contactListRequest.setFirstName("Jack Lord");
    	contactListRequest.setLastName("Hermoso");
    	List<ContactDetails> contactDetailsList = new ArrayList<>();
    	ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setPhoneNumbers("0421844795");
    	contactDetailsList.add(contactDetails);
    	contactListRequest.setContactDetails(contactDetailsList);
    	
    	return contactListRequest;
    }
	
	public static UserDetails generateUserDetailsNoRecord() {
    	UserDetails contactListRequest = new UserDetails();
    	contactListRequest.setFirstName("Jack Lord 3");
    	contactListRequest.setLastName("Hermoso 3");
    	List<ContactDetails> contactDetailsList = new ArrayList<>();
    	ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setPhoneNumbers("0421844795");
    	contactDetailsList.add(contactDetails);
    	contactListRequest.setContactDetails(contactDetailsList);
    	
    	return contactListRequest;
    }
    
    public static List<UserDetails> generateUserDetailsList() {
    	List<UserDetails> userDetailsList = new ArrayList<>();
    	
    	UserDetails contactListRequest = new UserDetails();
    	contactListRequest.setFirstName("Jack Lord");
    	contactListRequest.setLastName("Hermoso");
    	List<ContactDetails> contactDetailsList = new ArrayList<>();
    	ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setPhoneNumbers("0421844795");
    	contactDetailsList.add(contactDetails);
    	
    	UserDetails contactListRequest2 = new UserDetails();
    	contactListRequest2.setFirstName("John");
    	contactListRequest2.setLastName("Hermoso");
    	List<ContactDetails> contactDetailsList2 = new ArrayList<>();
    	ContactDetails contactDetails2 = new ContactDetails();
    	contactDetails2.setPhoneNumbers("0421844784");
    	contactDetailsList2.add(contactDetails);
    	
    	UserDetails contactListRequest3 = new UserDetails();
    	contactListRequest3.setFirstName("Rachael");
    	contactListRequest3.setLastName("Hermoso");
    	List<ContactDetails> contactDetailsList3 = new ArrayList<>();
    	ContactDetails contactDetails3 = new ContactDetails();
    	contactDetails3.setPhoneNumbers("0421844754");
    	contactDetailsList3.add(contactDetails);
    	
    	contactListRequest.setContactDetails(contactDetailsList);
    	contactListRequest2.setContactDetails(contactDetailsList2);
    	contactListRequest3.setContactDetails(contactDetailsList3);
    	
    	userDetailsList.add(contactListRequest);
    	userDetailsList.add(contactListRequest2);
    	userDetailsList.add(contactListRequest3);
    	
    	return userDetailsList;
    }
}
