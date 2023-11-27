package com.example.ai_generated_weather_api.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocation {
  private double lat;
  private double lon;
}