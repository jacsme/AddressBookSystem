package com.hermoso.rest.service;

import static com.hermoso.rest.constants.AddressBookTestConstants.CONTACT_EXIST_MESSAGE;
import static com.hermoso.rest.constants.AddressBookTestConstants.NO_RECORD_FOUND_MESSAGE;
import static com.hermoso.rest.constants.AddressBookTestConstants.SUCCESS_ADDED_MESSAGE;
import static com.hermoso.rest.constants.AddressBookTestConstants.SUCCESS_REMOVE_MESSAGE;
import static com.hermoso.rest.constants.AddressBookTestConstants.SUCCESS_STATUS;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hermoso.business.object.AddressBookBO;
import com.hermoso.rest.model.StatusResponse;
import com.hermoso.rest.model.UserDetails;
import com.hermoso.rest.util.UserDetailsUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressBookServiceImplTest {
	
	@InjectMocks
    @Spy
	private AddressBookBO addressBookBusinessObject = new AddressBookBO();
	
	@InjectMocks
	private AddressBookService addressBookServiceImpl = new AddressBookServiceImpl();
	
	@Mock
	private UserDetails userDetails;
	
	@Mock
	private UserDetails userDetailsNew;
	
	@Mock
	private UserDetails userDetailsUniqueSearch;
	
	@Mock
	private UserDetails userDetailsNoRecord;
	
	@Mock
	private UserDetails userDetailsDelete;
	
	@Mock
	private List<UserDetails> userDetailsList;
	
	@Mock
	private List<UserDetails> addressBookBO;
	
	@Before
    public void setup() {
		userDetails = UserDetailsUtil.generateUserDetails();
		userDetailsNew = UserDetailsUtil.generateUserDetailsNew();
		userDetailsUniqueSearch = UserDetailsUtil.generateUserDetailsUniqueSearch(); 
		userDetailsNoRecord = UserDetailsUtil.generateUserDetailsNoRecord();
		userDetailsDelete = UserDetailsUtil.generateUserDetailsDelete();
		
		userDetailsList = UserDetailsUtil.generateUserDetailsList();
		addressBookBO = addressBookBusinessObject.getInstance();
		if(addressBookBO.isEmpty()) {
			addressBookBO.addAll(userDetailsList);
		}
    }
	
	@Test
	public void testAddNewContact_Success() {
		StatusResponse response = addressBookServiceImpl.addNewContact(userDetailsNew);
	    assertEquals(response.getMessage(), SUCCESS_ADDED_MESSAGE);
	    assertEquals(response.getStatus(), SUCCESS_STATUS);
	}
	
	@Test
	public void testAddNewContact_ContactExist() {
		given(addressBookBusinessObject.getInstance()).willReturn(addressBookBO);
		StatusResponse response = addressBookServiceImpl.addNewContact(userDetails);
		
	    assertEquals(response.getMessage(), CONTACT_EXIST_MESSAGE);
	    assertEquals(response.getStatus(), SUCCESS_STATUS);
	}
	
	@Test
	public void testRemoveContact_Success() {
		given(addressBookBusinessObject.getInstance()).willReturn(addressBookBO);
		StatusResponse response = addressBookServiceImpl.removeContact(userDetailsDelete.getFirstName(), userDetailsDelete.getLastName(), userDetailsDelete.getContactDetails().get(0).getPhoneNumbers());
		
	    assertEquals(response.getMessage(), SUCCESS_REMOVE_MESSAGE);
	    assertEquals(response.getStatus(), SUCCESS_STATUS);
	}
	
	@Test
	public void testRemoveContact_NoRecordFound() {
		given(addressBookBusinessObject.getInstance()).willReturn(addressBookBO);
		StatusResponse response = addressBookServiceImpl.removeContact(userDetailsNoRecord.getFirstName(), userDetailsNoRecord.getLastName(), userDetailsNoRecord.getContactDetails().get(0).getPhoneNumbers());
		
	    assertEquals(response.getMessage(), NO_RECORD_FOUND_MESSAGE);
	    assertEquals(response.getStatus(), SUCCESS_STATUS);
	}
	
	@Test
	public void testPrintAllContacts_Success() {
		given(addressBookBusinessObject.getInstance()).willReturn(addressBookBO);
		List<UserDetails> response = addressBookServiceImpl.printAllContacts();
	    assertEquals(response.size(), 4);
	}
	
	@Test
	public void testPrintUniqueSet_Success() {
		given(addressBookBusinessObject.getInstance()).willReturn(addressBookBO);
		List<UserDetails> response = addressBookServiceImpl.printUniqueSet(userDetailsUniqueSearch.getFirstName(), userDetailsUniqueSearch.getLastName());
		assertEquals(response.size(), 1);
	}
}

