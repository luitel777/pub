package com.luitel.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.luitel777.pub")
public class PubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubApplication.class, args);
	}

}
