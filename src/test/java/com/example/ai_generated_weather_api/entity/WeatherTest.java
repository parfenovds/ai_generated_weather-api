package com.example.ai_generated_weather_api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherTest {

  @Test
  public void createWeatherObject() {
    Weather weather = new Weather();
    assertNotNull(weather);
  }

  @Test
  public void getAndSetId() {
    Long id = 1L;
    Weather weather = new Weather();
    weather.setId(id);
    assertEquals(id, weather.getId());
  }

  @Test
  public void getAndSetCityName() {
    String cityName = "New York";
    Weather weather = new Weather();
    weather.setCityName(cityName);
    assertEquals(cityName, weather.getCityName());
  }

  @Test
  public void getAndSetTemperature() {
    double temperature = 25.5;
    Weather weather = new Weather();
    weather.setTemperature(temperature);
    assertEquals(temperature, weather.getTemperature(), 0.01);
  }

  // Add tests for other weather attributes as needed
}
