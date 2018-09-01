package com.hermoso.rest.controller;

import static com.hermoso.rest.constants.AddressBookTestConstants.NEW_CONTACT_REQUEST_JSON;
import static com.hermoso.rest.constants.AddressBookTestConstants.SUCCESS_ADDED_MESSAGE;
import static com.hermoso.rest.constants.AddressBookTestConstants.SUCCESS_STATUS;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hermoso.rest.model.StatusResponse;
import com.hermoso.rest.model.UserDetails;
import com.hermoso.rest.service.AddressBookService;
import com.hermoso.rest.util.UserDetailsUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressBookControllerTest {

	@MockBean
    private AddressBookService addressBookService;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
     	
    @Test
    public void testAddNewContact_Success() throws Exception {
    	
    	UserDetails userDetailsRequest = UserDetailsUtil.generateUserDetails();
    	StatusResponse statusResponse = new StatusResponse();
    	statusResponse.setMessage(SUCCESS_ADDED_MESSAGE);
    	statusResponse.setStatus(SUCCESS_STATUS);
    	
    	doReturn(statusResponse).when(addressBookService).addNewContact(userDetailsRequest);
        mvc.perform(post("/v1/addnewcontact")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(NEW_CONTACT_REQUEST_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testRemoveContact_Success() throws Exception {
    	
    	UserDetails userDetailsRequest = UserDetailsUtil.generateUserDetails();
    	StatusResponse statusResponse = new StatusResponse();
    	statusResponse.setMessage(SUCCESS_ADDED_MESSAGE);
    	statusResponse.setStatus(SUCCESS_STATUS);
    	
    	doReturn(statusResponse).when(addressBookService).removeContact(userDetailsRequest.getFirstName(), userDetailsRequest.getLastName(), userDetailsRequest.getContactDetails().get(0).getPhoneNumbers());
        mvc.perform(post("/v1/removecontact/" + userDetailsRequest.getFirstName() + "/" + userDetailsRequest.getLastName() + "/" + userDetailsRequest.getContactDetails().get(0).getPhoneNumbers())
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getAddressBookAllList_Success() throws Exception {
    	List<UserDetails> userDetailsListReport = UserDetailsUtil.generateUserDetailsList();
    	doReturn(userDetailsListReport).when(addressBookService).printAllContacts();
        mvc.perform(get("/v1/addressbookalllist")
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getAddressBookAllList_NoRecordFound() throws Exception {
		List<UserDetails> userDetailsListReport = new ArrayList<>();
		doReturn(userDetailsListReport).when(addressBookService).printAllContacts();
        mvc.perform(get("/v1/addressbookalllist")
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getAddressBookUniqueList_Success() throws Exception {
    	List<UserDetails> userDetailsListReport = UserDetailsUtil.generateUserDetailsList();
    	UserDetails userDetailsRequest = UserDetailsUtil.generateUserDetails();
    	doReturn(userDetailsListReport).when(addressBookService).printUniqueSet(userDetailsRequest.getFirstName(), userDetailsRequest.getLastName());
        mvc.perform(get("/v1/addressbookuniquelist/" + userDetailsRequest.getFirstName() + "/" + userDetailsRequest.getLastName())
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getAddressBookUniqueList_NoRecordFound() throws Exception {
    	List<UserDetails> userDetailsListReport = new ArrayList<>();
    	UserDetails userDetailsRequest = UserDetailsUtil.generateUserDetails();
    	doReturn(userDetailsListReport).when(addressBookService).printUniqueSet(userDetailsRequest.getFirstName(), userDetailsRequest.getLastName());
        mvc.perform(get("/v1/addressbookuniquelist/" + userDetailsRequest.getFirstName() + "/" + userDetailsRequest.getLastName())
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    
	
}
