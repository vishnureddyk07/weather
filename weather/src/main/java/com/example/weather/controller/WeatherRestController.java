package com.example.weather.controller;

import com.example.weather.model.Weather;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public Weather createWeather(@RequestBody Weather weather) {
        return weatherService.saveWeather(weather);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeather(@PathVariable Long id) {
        weatherService.deleteWeather(id);
        return ResponseEntity.ok().build();
    }
}
