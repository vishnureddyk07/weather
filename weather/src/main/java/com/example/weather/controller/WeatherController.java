package com.example.weather.controller;

import com.example.weather.model.Weather;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping("/")
    public String index(Model model) {
        List<Weather> weatherList = weatherService.getAllWeather();
        model.addAttribute("weatherList", weatherList);
        model.addAttribute("weather", new Weather());
        return "index";
    }
    
    @PostMapping("/weather/add")
    public String addWeather(@ModelAttribute Weather weather) {
        weatherService.saveWeather(weather);
        return "redirect:/";
    }
    
    @GetMapping("/weather/delete/{id}")
    public String deleteWeather(@PathVariable Long id) {
        weatherService.deleteWeather(id);
        return "redirect:/";
    }
}
