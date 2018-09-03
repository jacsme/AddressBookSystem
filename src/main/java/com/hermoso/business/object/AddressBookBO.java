package com.hermoso.business.object;

import java.util.ArrayList;
import java.util.List;

import com.hermoso.rest.model.UserDetails;

/**
 * This is a singleton class that handle the data structure
 * of address book system
 * @author Jack Lord Hermoso
 *
 */
public class AddressBookBO {

	private static class AddressBookLoader {
		private static final List<UserDetails> INSTANCE = new ArrayList<>();
	}
	
	public List<UserDetails> getInstance() {
		return AddressBookLoader.INSTANCE;
	}
}
