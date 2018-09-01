package com.hermoso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hermoso.business.object.AddressBookBO;

@Configuration
public class ServiceConfig {
	
	@Bean
	public AddressBookBO addressBookBO(){
		return new AddressBookBO();
	}
}
