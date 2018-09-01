package com.hermoso.business.object;

import java.util.ArrayList;
import java.util.List;

import com.hermoso.rest.model.UserDetails;


public class AddressBookBO {

	private static class AddressBookLoader {
		private static final List<UserDetails> INSTANCE = new ArrayList<>();
	}
	
	public List<UserDetails> getInstance() {
		return AddressBookLoader.INSTANCE;
	}
}
