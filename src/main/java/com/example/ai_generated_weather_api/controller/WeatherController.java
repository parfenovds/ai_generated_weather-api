package com.example.ai_generated_weather_api.controller;

import com.example.ai_generated_weather_api.service.WeatherDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
  private final WeatherDataService weatherDataService;

  public WeatherController(WeatherDataService weatherDataService) {
    this.weatherDataService = weatherDataService;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/temperature")
  public String getTemperature(@RequestParam String cityName, Model model) {
    double temperature = weatherDataService.getTemperatureByCity(cityName);
    model.addAttribute("cityName", cityName);
    model.addAttribute("temperature", temperature);
    return "temperature";
  }
}