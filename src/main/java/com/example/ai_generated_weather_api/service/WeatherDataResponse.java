package com.example.ai_generated_weather_api.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDataResponse {
  private String name;

  @JsonProperty("main")
  private WeatherMainData main;
}