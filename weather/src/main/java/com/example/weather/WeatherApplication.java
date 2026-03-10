package com.example.weather;

import com.example.weather.model.Weather;
import com.example.weather.repository.WeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(WeatherRepository repository) {
		return args -> {
			// Add sample weather data if database is empty
			if (repository.count() == 0) {
				repository.save(new Weather("New York", 22.5, "Partly Cloudy", 65, 12.3));
				repository.save(new Weather("London", 18.0, "Rainy", 80, 15.5));
				repository.save(new Weather("Tokyo", 25.3, "Sunny", 55, 8.2));
				repository.save(new Weather("Sydney", 28.7, "Clear", 50, 10.0));
				repository.save(new Weather("Paris", 20.1, "Cloudy", 70, 11.5));
				System.out.println("Sample weather data loaded!");
			}
		};
	}
}

