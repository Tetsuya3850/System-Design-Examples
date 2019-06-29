package com.example.cache;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(BookRepository bookRepository) {
		return (args) -> {
			bookRepository.save(new Book("9783161484100", "Some Book"));
		};
	}
}
