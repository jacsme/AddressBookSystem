package com.hermoso.rest.controller;


import static com.hermoso.rest.constants.AddressBookConstants.ADD_CONTACTS_URI;
import static com.hermoso.rest.constants.AddressBookConstants.APPLICATION_JSON_API_VALUE;
import static com.hermoso.rest.constants.AddressBookConstants.PRINT_ALL_CONTACTS_URI;
import static com.hermoso.rest.constants.AddressBookConstants.PRINT_UNIQUE_CONTACTS_URI;
import static com.hermoso.rest.constants.AddressBookConstants.REMOVE_CONTACTS_URI;
import static com.hermoso.rest.constants.AddressBookConstants.NO_RECORD_FOUND_MESSAGE;
import static com.hermoso.rest.constants.AddressBookConstants.SUCCESS_STATUS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hermoso.rest.model.StatusResponse;
import com.hermoso.rest.model.UserDetails;
import com.hermoso.rest.service.AddressBookService;

/**
 * This is the main controller of address book System
 * that will handle all the request or user inputs
 * @author Jack Lord Hermoso
 *
 */
@RestController
public class AddressBookController {
	
	@Autowired
	private AddressBookService addressBookService;

	@RequestMapping(value = ADD_CONTACTS_URI, method = RequestMethod.POST, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<StatusResponse> addNewContact(@RequestBody UserDetails contactListRequest) {
		StatusResponse statusReponse = addressBookService.addNewContact(contactListRequest);
		return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(statusReponse);
    }
	
	@RequestMapping(value = REMOVE_CONTACTS_URI + "/{firstName:.+}/{lastName:.+}/{phoneNumber:.+}", method = RequestMethod.POST, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<StatusResponse> removeContact(@PathVariable("firstName") String firstName, 
    		@PathVariable("lastName") String lastName, @PathVariable("phoneNumber") String phoneNumber) {
		StatusResponse statusReponse = addressBookService.removeContact(firstName, lastName, phoneNumber);
		return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(statusReponse);
    }
	
	@RequestMapping(value = PRINT_ALL_CONTACTS_URI, method = RequestMethod.GET, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAddressBookAllList() {
		List<UserDetails> userDetailsList = addressBookService.printAllContacts();
		StatusResponse statusReponse = new StatusResponse();
		if(userDetailsList.isEmpty()) {
			statusReponse.setMessage(NO_RECORD_FOUND_MESSAGE);
			statusReponse.setStatus(SUCCESS_STATUS);
		}
        return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(userDetailsList.isEmpty() ? statusReponse : userDetailsList );
        		
    }
	
	@RequestMapping(value = PRINT_UNIQUE_CONTACTS_URI + "/{firstName:.+}/{lastName:.+}", method = RequestMethod.GET, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getAddressBookUniqueList(@PathVariable("firstName") String firstName, 
    		@PathVariable("lastName") String lastName) {
		List<UserDetails> userDetailsList = addressBookService.printUniqueSet(firstName, lastName);
		StatusResponse statusReponse = new StatusResponse();
		if(userDetailsList.isEmpty()) {
			statusReponse.setMessage(NO_RECORD_FOUND_MESSAGE);
			statusReponse.setStatus(SUCCESS_STATUS);
		}
		return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(userDetailsList.isEmpty() ? statusReponse : userDetailsList ); 
    }
}
