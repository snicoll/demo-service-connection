package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CustomerRepositoryTests {

	@Autowired
	private CustomerRepository repository;

	@Test
	void simpleTest() {
		Customer customer = new Customer("John", "Smith");
		assertThat(customer.getId()).isNull();
		Customer saved = repository.save(customer);
		assertThat(saved.getId()).isNotNull();
	}

}
