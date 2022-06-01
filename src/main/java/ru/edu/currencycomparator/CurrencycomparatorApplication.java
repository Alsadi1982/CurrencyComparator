package ru.edu.currencycomparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencycomparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencycomparatorApplication.class, args);
	}

}
