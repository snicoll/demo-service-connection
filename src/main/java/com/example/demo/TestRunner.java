package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class TestRunner implements ApplicationRunner {

	private final CustomerRepository repository;

	TestRunner(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(ApplicationArguments args) {
		Customer saved = repository.save(new Customer("Stéphane", "Nicoll"));
		System.out.println("Customer created with id '" + saved.getId() + "'");
	}

}
