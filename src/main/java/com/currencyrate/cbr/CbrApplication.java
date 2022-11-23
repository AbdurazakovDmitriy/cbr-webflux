package com.currencyrate.cbr;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class CbrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbrApplication.class, args);
	}

}
