package com.example.demo;

import java.util.Random;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 *
 * @author Stephane Nicoll
 */
@Component
public class DemoTask {

	private static final Random random = new Random();

	private final RestClient restClient;

	private final CustomerRepository customerRepository;

	public DemoTask(RestClient.Builder restClientBuilder, CustomerRepository customerRepository) {
		this.restClient = restClientBuilder.build();
		this.customerRepository = customerRepository;
	}

	@Scheduled(fixedRate = 3000)
	public void updateCustomer() {
		ResponseEntity<Void> bodilessEntity = this.restClient.get().uri("https://www.google.com/").retrieve().toBodilessEntity();
		addFakeLatency();
		Customer customer = new Customer(UUID.randomUUID().toString(), UUID.randomUUID().toString());
		this.customerRepository.save(customer);

	}

	private void addFakeLatency() {
		try {
			Thread.sleep(random.nextInt(1000));
		}
		catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}
}
