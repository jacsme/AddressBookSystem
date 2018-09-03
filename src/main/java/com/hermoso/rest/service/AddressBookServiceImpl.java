package com.hermoso.rest.service;

import static com.hermoso.rest.constants.AddressBookConstants.CONTACT_EXIST_MESSAGE;
import static com.hermoso.rest.constants.AddressBookConstants.ERROR_ADDED_MESSAGE;
import static com.hermoso.rest.constants.AddressBookConstants.ERROR_REMOVE_MESSAGE;
import static com.hermoso.rest.constants.AddressBookConstants.ERROR_STATUS;
import static com.hermoso.rest.constants.AddressBookConstants.NO_RECORD_FOUND_MESSAGE;
import static com.hermoso.rest.constants.AddressBookConstants.SUCCESS_ADDED_MESSAGE;
import static com.hermoso.rest.constants.AddressBookConstants.SUCCESS_REMOVE_MESSAGE;
import static com.hermoso.rest.constants.AddressBookConstants.SUCCESS_STATUS;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hermoso.business.object.AddressBookBO;
import com.hermoso.rest.model.ContactDetails;
import com.hermoso.rest.model.StatusResponse;
import com.hermoso.rest.model.UserDetails;

/**
 * This is the main logic of the system
 * that handle the manipulation of data 
 * @author Jack Lord Hermoso
 *
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {

	@Autowired
	@Qualifier("addressBookBO")
	private AddressBookBO addressBookBusinessObject;
	
	/**
	 * This is to add new contact in the list
	 */
	@Override
	public StatusResponse addNewContact(UserDetails userDetails) {
		StatusResponse statusReponse = new StatusResponse();
		boolean addedItem = false;
		try {
			List<UserDetails> addressBookBO = addressBookBusinessObject.getInstance();
			List<UserDetails> resultUserDetails = addressBookBO.stream()
				    .filter(addressbook -> addressbook.getFirstName().equals(userDetails.getFirstName()) && addressbook.getLastName().equals(userDetails.getLastName()))
				    .collect(Collectors.toList());
			
			if (resultUserDetails.isEmpty()) {
				addressBookBO.add(userDetails);
				statusReponse.setMessage(SUCCESS_ADDED_MESSAGE);
				statusReponse.setStatus(SUCCESS_STATUS);
			}else {
				
				StringBuilder existingPhoneNumbers = convertContactDetails(resultUserDetails);
				
				List<ContactDetails> existingPhoneNumbersList = resultUserDetails.get(0).getContactDetails();
				List<ContactDetails> phoneNumbersRequest = userDetails.getContactDetails();
				
				for(ContactDetails contactDetailsList : phoneNumbersRequest) {
					if (!existingPhoneNumbers.toString().contains(contactDetailsList.getPhoneNumbers())) {
						existingPhoneNumbersList.add(contactDetailsList);
						addedItem = true;
					}
				}
				
				if (addedItem) {
					statusReponse.setMessage(SUCCESS_ADDED_MESSAGE);
					statusReponse.setStatus(SUCCESS_STATUS);
				}else {
					statusReponse.setMessage(CONTACT_EXIST_MESSAGE);
					statusReponse.setStatus(SUCCESS_STATUS);
				}
			}
		}catch (Exception e) {
			statusReponse.setMessage(ERROR_ADDED_MESSAGE + " -- " + e.getMessage());
			statusReponse.setStatus(ERROR_STATUS);
		}
		
		return statusReponse;
	}

	/**
	 * This is refactored method that handle the convertion of 
	 * list to string builder
	 * @param resultUserDetails
	 * @return
	 */
	private StringBuilder convertContactDetails(List<UserDetails> resultUserDetails) {
		StringBuilder existingPhoneNumbers = new StringBuilder();
		List<ContactDetails> intList = resultUserDetails.get(0).getContactDetails();
		for (ContactDetails contactDetails : intList) {
			existingPhoneNumbers.append(contactDetails.getPhoneNumbers() + ", ");
		}
		return existingPhoneNumbers;
	}
	
	/**
	 * This is to remove the existing contact 
	 * in the list
	 */
	@Override
	public StatusResponse removeContact(String firstName, String lastName, String phoneNumber) {
		StatusResponse statusReponse = new StatusResponse();
		try {
			List<UserDetails> addressBookBO = addressBookBusinessObject.getInstance();
			
			List<UserDetails> resultUserDetails = addressBookBO.stream()
				    .filter(addressbook -> addressbook.getFirstName().equals(firstName) && addressbook.getLastName().equals(lastName))
				    .collect(Collectors.toList());
			
			if (resultUserDetails.isEmpty()) {
				statusReponse.setMessage(NO_RECORD_FOUND_MESSAGE);
				statusReponse.setStatus(SUCCESS_STATUS);
			}else {
				List<ContactDetails> existingPhoneNumbersList = resultUserDetails.get(0).getContactDetails();
				List<ContactDetails> phoneNumberRequest = resultUserDetails.get(0).getContactDetails().stream()
					    .filter(phoneNumberReq -> phoneNumberReq.getPhoneNumbers().equals(phoneNumber))
					    .collect(Collectors.toList());
				
				existingPhoneNumbersList.removeIf(phoneNumberList -> phoneNumberRequest.contains(phoneNumberList));
				if (existingPhoneNumbersList.isEmpty()) {
					addressBookBO.removeIf(userDetailsList -> resultUserDetails.contains(userDetailsList)); 
				}
				statusReponse.setMessage(SUCCESS_REMOVE_MESSAGE);
				statusReponse.setStatus(SUCCESS_STATUS);
			}
		}catch (Exception e) {
			statusReponse.setMessage(ERROR_REMOVE_MESSAGE + " -- " + e.getMessage());
			statusReponse.setStatus(ERROR_STATUS);
		}
		return statusReponse;
	}

	/**
	 * This is a method that handle to generate 
	 * a list of all the contact
	 */
	@Override
	public List<UserDetails> printAllContacts() {
		List<UserDetails> addressBookBO = addressBookBusinessObject.getInstance();
		return addressBookBO;
	}

	/**
	 * This is a method that handle the retrieval of
	 * a partyicular contact
	 */
	@Override
	public List<UserDetails> printUniqueSet(String firstName, String lastName) {
		List<UserDetails> addressBookBO = addressBookBusinessObject.getInstance();
		List<UserDetails> resultUserDetails = addressBookBO.stream()
			    .filter(addressbook -> addressbook.getFirstName().equals(firstName) && addressbook.getLastName().equals(lastName))
			    .collect(Collectors.toList());
		
		return resultUserDetails;
	}
}
