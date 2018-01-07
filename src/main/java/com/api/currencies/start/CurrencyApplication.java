package com.api.currencies.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/* Klasse waarmee de applicatie gestart kan worden
 */
@SpringBootApplication
@ComponentScan("com.api.currencies")
@EnableJpaRepositories("com.api.currencies")
public class CurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}
}
