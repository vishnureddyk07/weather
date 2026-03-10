package com.example.weather.service;

import com.example.weather.model.Weather;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    
    @Autowired
    private WeatherRepository weatherRepository;
    
    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }
    
    public Optional<Weather> getWeatherById(Long id) {
        return weatherRepository.findById(id);
    }
    
    public List<Weather> getWeatherByCity(String city) {
        return weatherRepository.findByCity(city);
    }
    
    public Optional<Weather> getLatestWeatherByCity(String city) {
        return weatherRepository.findFirstByCityOrderByRecordedAtDesc(city);
    }
    
    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }
    
    public void deleteWeather(Long id) {
        weatherRepository.deleteById(id);
    }
}
