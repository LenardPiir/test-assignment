package com.helmes.sectorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SectorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SectorApiApplication.class, args);
	}

}
