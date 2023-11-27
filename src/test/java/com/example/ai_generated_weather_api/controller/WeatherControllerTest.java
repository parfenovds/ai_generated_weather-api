package com.example.ai_generated_weather_api.controller;

import com.example.ai_generated_weather_api.service.WeatherDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WeatherControllerTest {

  @Mock
  private WeatherDataService weatherDataService;

  private WeatherController weatherController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    weatherController = new WeatherController(weatherDataService);
  }

  @Test
  void index_ReturnsCorrectViewName() {
    String viewName = weatherController.index();
    assertEquals("index", viewName);
  }

  @Test
  void getTemperature_ReturnsCorrectViewAndAddsAttributesToModel() {
    String cityName = "City";
    double temperature = 25.0;
    Model model = mock(Model.class);

    when(weatherDataService.getTemperatureByCity(cityName)).thenReturn(temperature);

    String viewName = weatherController.getTemperature(cityName, model);

    assertEquals("temperature", viewName);
    verify(model).addAttribute("cityName", cityName);
    verify(model).addAttribute("temperature", temperature);
  }
}