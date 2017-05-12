package com.epam.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;

@SpringBootApplication
public class ReceiptApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptApplication.class, args);
	}
}
