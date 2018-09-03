package com.hermoso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hermoso.business.object.AddressBookBO;

/**
 * This is a configaration file that will instantiate the 
 * object needed as part of the injected dependency
 * @author Jack Lord Hermoso
 */
@Configuration
public class ServiceConfig {
	
	@Bean
	public AddressBookBO addressBookBO(){
		return new AddressBookBO();
	}
}
