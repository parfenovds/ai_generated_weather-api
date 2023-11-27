package com.example.ai_generated_weather_api.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

  @Mock
  private Model model;

  @InjectMocks
  private GlobalExceptionHandler globalExceptionHandler;

  @Test
  void handleCityNotFound_ReturnsErrorViewWithCorrectErrorMessage() {
    String errorMessage = "City not found";
    CityNotFoundException exception = new CityNotFoundException(errorMessage);

    globalExceptionHandler.handleCityNotFound(exception, model);

    verify(model).addAttribute("errorMessage", errorMessage);
  }

  @Test
  void handleWeatherDataServiceException_ReturnsErrorViewWithCorrectErrorMessage() {
    String errorMessage = "Weather data service error";
    WeatherDataServiceException exception = new WeatherDataServiceException(errorMessage);

    globalExceptionHandler.handleWeatherDataServiceException(exception, model);

    verify(model).addAttribute("errorMessage", errorMessage);
  }
}