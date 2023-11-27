package com.example.ai_generated_weather_api.service;

import com.example.ai_generated_weather_api.entity.Weather;
import com.example.ai_generated_weather_api.exception.CityNotFoundException;
import com.example.ai_generated_weather_api.exception.WeatherDataServiceException;
import com.example.ai_generated_weather_api.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WeatherDataServiceTest {

  @MockBean
  private RestTemplate restTemplate;

  @MockBean
  private WeatherRepository weatherRepository;

  private WeatherDataService weatherDataService;

  @BeforeEach
  public void setUp() {
    weatherDataService = new WeatherDataService(restTemplate, weatherRepository);
  }

  @Test
  public void testGetTemperatureByCity_Success() {
    String cityName = "TestCity";
    WeatherDataResponse weatherDataResponse = new WeatherDataResponse();
    WeatherMainData main = new WeatherMainData();
    main.setTemp(25.5);
    weatherDataResponse.setMain(main);

    when(restTemplate.getForObject(anyString(), eq(WeatherDataResponse.class))).thenReturn(weatherDataResponse);

    double temperature = weatherDataService.getTemperatureByCity(cityName);

    assertEquals(25.5, temperature);
    verify(weatherRepository, times(1)).save(any(Weather.class));
  }

  @Test
  public void testGetTemperatureByCity_CityNotFoundException() {
    String cityName = "NonExistingCity";

    when(restTemplate.getForObject(anyString(), eq(WeatherDataResponse.class))).thenThrow(HttpClientErrorException.NotFound.class);

    assertThrows(CityNotFoundException.class, () -> weatherDataService.getTemperatureByCity(cityName));
    verify(weatherRepository, never()).save(any(Weather.class));
  }

  @Test
  public void testGetTemperatureByCity_WeatherDataServiceException() {
    String cityName = "CityWithError";

    when(restTemplate.getForObject(anyString(), eq(WeatherDataResponse.class))).thenThrow(RuntimeException.class);

    assertThrows(WeatherDataServiceException.class, () -> weatherDataService.getTemperatureByCity(cityName));
    verify(weatherRepository, never()).save(any(Weather.class));
  }

  @Test
  public void testUpdateWeatherData_WeatherDataServiceException() {
    List<String> cities = new ArrayList<>();
    cities.add("CityWithError");

    when(weatherRepository.getAllCities()).thenReturn(cities);
    when(restTemplate.getForObject(anyString(), any())).thenThrow(RuntimeException.class);

    weatherDataService.updateWeatherData();

    verify(weatherRepository, never()).updateTemperature(anyString(), anyDouble());
  }
}