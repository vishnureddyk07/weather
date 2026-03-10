package com.example.weather;

import com.example.weather.model.User;
import com.example.weather.model.Weather;
import com.example.weather.repository.UserRepository;
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
	CommandLineRunner initDatabase(WeatherRepository weatherRepository, UserRepository userRepository) {
		return args -> {
			// Add sample users if database is empty
			if (userRepository.count() == 0) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setEmail("admin@weather.com");
				admin.setPassword("admin123");
				admin.setRole("ADMIN");
				userRepository.save(admin);
				
				User user = new User();
				user.setUsername("john");
				user.setEmail("john@example.com");
				user.setPassword("john123");
				user.setRole("USER");
				userRepository.save(user);
				
				System.out.println("Sample users created!");
			}
			
			// Add sample weather data if database is empty
			if (weatherRepository.count() == 0) {
				weatherRepository.save(new Weather("New York", 22.5, "Partly Cloudy", 65, 12.3));
				weatherRepository.save(new Weather("London", 18.0, "Rainy", 80, 15.5));
				weatherRepository.save(new Weather("Tokyo", 25.3, "Sunny", 55, 8.2));
				weatherRepository.save(new Weather("Sydney", 28.7, "Clear", 50, 10.0));
				weatherRepository.save(new Weather("Paris", 20.1, "Cloudy", 70, 11.5));
				System.out.println("Sample weather data loaded!");
			}
		};
	}
}

