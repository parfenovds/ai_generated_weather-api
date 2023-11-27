package com.example.ai_generated_weather_api.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CityNotFoundException.class)
  public String handleCityNotFound(CityNotFoundException ex, Model model) {
    model.addAttribute("errorMessage", ex.getMessage());
    return "error";
  }

  @ExceptionHandler(WeatherDataServiceException.class)
  public String handleWeatherDataServiceException(WeatherDataServiceException ex, Model model) {
    model.addAttribute("errorMessage", ex.getMessage());
    return "error";
  }
}