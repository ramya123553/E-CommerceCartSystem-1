package com.example.ecommerce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ecommerce.controller.CustomErrorController;

@SpringBootApplication(exclude = {CustomErrorController.class})
public class ECommerceCartSystem1Application {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceCartSystem1Application.class, args);
	}

}
