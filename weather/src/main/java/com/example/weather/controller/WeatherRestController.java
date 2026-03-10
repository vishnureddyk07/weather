package com.example.weather.controller;

import com.example.weather.model.Weather;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/weather")
public class WeatherRestController {
    
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping
    public List<Weather> getAllWeather() {
        return weatherService.getAllWeather();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable Long id) {
        return weatherService.getWeatherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/city/{city}")
    public List<Weather> getWeatherByCity(@PathVariable String city) {
        return weatherService.getWeatherByCity(city);
    }
    
    @PostMapping
    public ResponseEntity<?> createWeather(@RequestBody Weather weather) {
        try {
            Weather savedWeather = weatherService.saveWeather(weather);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Weather data created successfully");
            response.put("weather", savedWeather);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating weather data: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteWeather(@PathVariable Long id) {
        weatherService.deleteWeather(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Weather data deleted successfully");
        return ResponseEntity.ok(response);
    }
}
