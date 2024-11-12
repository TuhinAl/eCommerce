package com.tuhinal.ecommerce.order;

import com.tuhinal.ecommerce.OrderApplication;
import org.springframework.boot.SpringApplication;

public class TestOrderApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
