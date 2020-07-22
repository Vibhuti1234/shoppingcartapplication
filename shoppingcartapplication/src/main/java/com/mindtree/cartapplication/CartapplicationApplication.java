package com.mindtree.cartapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement

public class CartapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartapplicationApplication.class, args);
	}

}
