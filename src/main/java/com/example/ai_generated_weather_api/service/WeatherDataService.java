package com.example.ai_generated_weather_api.service;

import com.example.ai_generated_weather_api.entity.Weather;
import com.example.ai_generated_weather_api.exception.CityNotFoundException;
import com.example.ai_generated_weather_api.exception.WeatherDataServiceException;
import com.example.ai_generated_weather_api.repository.WeatherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherDataService {

  @Value("${openweathermap.api.key}")
  private String apiKey;

  private final RestTemplate restTemplate;
  private final WeatherRepository weatherRepository;

  @Autowired
  public WeatherDataService(RestTemplate restTemplate, WeatherRepository weatherRepository) {
    this.restTemplate = restTemplate;
    this.weatherRepository = weatherRepository;
  }

  public double getTemperatureByCity(String cityName) {
    String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=metric";
    try {
      WeatherDataResponse response = restTemplate.getForObject(apiUrl, WeatherDataResponse.class);
      if (response != null && response.getMain() != null) {
        double temperature = response.getMain().getTemp();
        saveOrUpdateWeatherData(cityName, temperature);
        return temperature;
      }
    } catch (HttpClientErrorException.NotFound notFoundException) {
      throw new CityNotFoundException("City not found or weather data unavailable for: " + cityName);
    } catch (Exception e) {
      throw new WeatherDataServiceException("Error while fetching weather data for: " + cityName + ". Error: " + e.getMessage());
    }
    throw new WeatherDataServiceException("Weather data unavailable for: " + cityName);
  }

  @Scheduled(fixedRate = 120000)
  public void updateWeatherData() {
    List<String> cities = weatherRepository.getAllCities();
    for (String city : cities) {
      try {
        System.out.println("Updating weather data for: " + city);
        double temperature = getTemperatureByCity(city);
        weatherRepository.updateTemperature(city, temperature);
      } catch (CityNotFoundException | WeatherDataServiceException e) {
        System.err.println("Failed to update weather data for: " + city + ". Error: " + e.getMessage());
      }
    }
  }

  private void saveOrUpdateWeatherData(String cityName, double temperature) {
    Weather weather = weatherRepository.findByCityName(cityName);
    if (weather != null) {
      weather.setTemperature(temperature);
    } else {
      weather = new Weather(null, cityName, temperature);
    }
    weatherRepository.save(weather);
  }
}
