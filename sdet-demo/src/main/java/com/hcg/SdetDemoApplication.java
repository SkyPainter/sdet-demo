package com.hcg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SdetDemoApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SdetDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Starting up the SDET Demo Project");
	}
}
