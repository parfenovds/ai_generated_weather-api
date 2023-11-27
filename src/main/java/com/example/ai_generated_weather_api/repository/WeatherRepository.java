package com.example.ai_generated_weather_api.repository;


import com.example.ai_generated_weather_api.entity.Weather;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
  @Query("SELECT w.cityName FROM Weather w")
  List<String> getAllCities();

  @Modifying
  @Transactional
  @Query("UPDATE Weather w SET w.temperature = :temperature WHERE w.cityName = :city")
  void updateTemperature(String city, double temperature);

  Weather findByCityName(String cityName);
}
