package com.bagavathi.springboot.firstrestapi.user;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {
	

	private Logger logger=LoggerFactory.getLogger(getClass());
	private UserDetailsRepository repository;
	
	@Autowired
	public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	//Anything which we code here will get executed at the startup
	public void run(String... args) throws Exception {
		logger.info(Arrays.toString(args));
		repository.save(new UserDetails("Bagavathi","Developer"));
		repository.save(new UserDetails("Krishna","Developer"));
		repository.save(new UserDetails("Archu","Tester"));
		repository.save(new UserDetails("Ravi","Manager"));
		repository.save(new UserDetails("Krish","AssociateManager"));
		//List<UserDetails> users=repository.findAll();
		
		
		List<UserDetails> users=repository.findByRole("Developer");
		users.forEach(user -> logger.info(user.toString()));
	}

}
